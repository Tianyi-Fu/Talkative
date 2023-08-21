import pymysql
from gensim.corpora import Dictionary
from gensim.models import LdaModel
from gensim.parsing.preprocessing import preprocess_string
from nltk.sentiment import SentimentIntensityAnalyzer
import nltk
import json
from transformers import AutoTokenizer, AutoModelForSequenceClassification, pipeline
from transformers import pipeline
from flair.models import TextClassifier
from flair.data import Sentence

print("——————————————————————————————————————————————————————————————————————————————————————————————————————")
print("——————————————————————————————————————————————————————————————————————————————————————————————————————")
# Download the vader_lexicon data
nltk.download("vader_lexicon")

# Create a sentiment analyzer using NTLK's Vader
analyzer = SentimentIntensityAnalyzer()
# Create a sentiment analyzer using distilbert-base-uncased-finetuned-sst-2-english
tokenizer = AutoTokenizer.from_pretrained("distilbert-base-uncased-finetuned-sst-2-english")
model = AutoModelForSequenceClassification.from_pretrained("distilbert-base-uncased-finetuned-sst-2-english")
bert_pipeline = pipeline('sentiment-analysis', model=model, tokenizer=tokenizer)

# Load a pre-trained sentiment analysis model from Flair
classifier = TextClassifier.load('en-sentiment')


def classify_sentiment(polarity, polarity_threshold=0.05):
    if polarity >= polarity_threshold:
        return "Positive"
    elif polarity <= -polarity_threshold:
        return "Negative"
    else:
        return "Neutral"


def classify_sentiment_bert(review: str):
    result = bert_pipeline(review)[0]
    return result['label']


def classify_sentiment_ntlk(review: str):
    scores = analyzer.polarity_scores(review)
    return classify_sentiment(scores['compound'])


def classify_sentiment_flair(review):
    if not review.strip():  # 如果review只是空格或为空
        return "Neutral"
    sentence = Sentence(review)
    classifier.predict(sentence)
    if sentence.labels:
        return sentence.labels[0].value
    else:
        return "Neutral"


def fetch_transcripts_from_db():
    """Fetch the transcripts from the chat_record table and return them."""
    connection = connect_to_db()
    try:
        with connection.cursor() as cursor:
            sql = "SELECT chat_record_id, transcript FROM chat_record"
            cursor.execute(sql)
            return cursor.fetchall()
    finally:
        connection.close()


def process_transcript_for_review(transcript):
    """Process the chat transcript to extract messages from the Customer."""
    review = ""
    try:
        chat_entries = json.loads(transcript)
        human_interaction_started = False
        for entry in chat_entries:
            # Check if a real human (not Talkative) is speaking
            if any(key not in ["Talkative", "Customer"] for key in entry.keys()):
                human_interaction_started = True
            # If human interaction has started, process the Customer's messages
            if human_interaction_started and "Customer" in entry:
                review += entry["Customer"] + " "
    except Exception as e:
        print(f"Error processing transcript: {e}")
    return review.strip()


def get_reviews():
    chat_records = fetch_transcripts_from_db()
    reviews = []
    for record in chat_records:
        review = process_transcript_for_review(record['transcript'])
        reviews.append({
            "chat_record_id": record["chat_record_id"],
            "review": review
        })
    return reviews


def train_lda(reviews):
    # Preprocess the reviews
    texts = [preprocess_string(review) for review in reviews]

    # Create a dictionary representation of the documents
    dictionary = Dictionary(texts)

    # Create a Bag of Words representation of the documents
    corpus = [dictionary.doc2bow(text) for text in texts]

    # Train the model on the corpus.
    ldamodel = LdaModel(corpus, id2word=dictionary, num_topics=10)

    return ldamodel, dictionary


def assign_topic_to_review(ldamodel, dictionary, review):
    review = preprocess_string(review)
    bow = dictionary.doc2bow(review)
    topic_distribution = ldamodel.get_document_topics(bow)
    main_topic_id, _ = max(topic_distribution, key=lambda x: x[1])
    main_topic_words = ldamodel.show_topic(main_topic_id)
    # Take the top 3 keywords
    main_topic_readable = ", ".join([f"{word} ({prob:.2f})" for word, prob in main_topic_words[:3]])
    return main_topic_readable


def get_sentiments_and_topics(reviews):
    ldamodel, dictionary = train_lda(reviews)
    results = []
    for review in reviews:
        result = {
            "Review": review,
            "NTLK": classify_sentiment_ntlk(review),
            "Flair": classify_sentiment_flair(review),
            "BERT": classify_sentiment_bert(review),  # 这里添加BERT的输出
            "Topic": assign_topic_to_review(ldamodel, dictionary, review)
        }
        results.append(result)
    return results


def save_results_to_json(results, filename):
    with open(filename, 'w') as f:
        json.dump(results, f, indent=4)


# Database configuration
DB_CONFIG = {
    'host': 'My_db',
    'port': 3306,
    'user': 'root',
    'password': 'Fty5005669',
    'db': 'talkative',
    'charset': 'utf8mb4',
    'cursorclass': pymysql.cursors.DictCursor
}



def connect_to_db():
    """Connect to the MySQL database and return the connection."""
    connection = pymysql.connect(**DB_CONFIG)
    return connection


def ensure_chat_record_exists(chat_record_id):
    connection = connect_to_db()
    try:
        with connection.cursor() as cursor:

            sql = "SELECT 1 FROM chat_record WHERE chat_record_id = %s"
            cursor.execute(sql, (chat_record_id,))
            result = cursor.fetchone()
            if not result:
                sql = "INSERT INTO chat_record (chat_record_id) VALUES (%s)"
                cursor.execute(sql, (chat_record_id,))
                connection.commit()
    finally:
        connection.close()


def save_review_to_db(review_data):
    chat_record_id = review_data.get('chat_record_id', None)
    ensure_chat_record_exists(chat_record_id)
    connection = connect_to_db()
    try:
        with connection.cursor() as cursor:
            # First check if the record already exists
            sql_check = "SELECT 1 FROM review_analysis WHERE chat_record_id = %s"
            cursor.execute(sql_check, (chat_record_id,))
            exists = cursor.fetchone()
            if not exists:
                # Inserting data into review_analysis table if not exists
                sql_insert = """
                    INSERT INTO review_analysis (
                        review_text, sentiment_ntlk, sentiment_flair, sentiment_bert, topic, chat_record_id
                    )
                    VALUES (%s, %s, %s, %s, %s, %s)
                """
                cursor.execute(sql_insert, (
                    review_data['Review'],
                    review_data['NTLK'],
                    review_data['Flair'],
                    review_data['BERT'],
                    review_data['Topic'],
                    chat_record_id
                ))
                connection.commit()
    finally:
        connection.close()


def main():
    try:
        reviews_data = get_reviews()
        results = get_sentiments_and_topics([data['review'] for data in reviews_data])
        print("——————————————————————————————————————————————————————————————————————————————————————————————————————")
        print("——————————————————————————————————————————————————————————————————————————————————————————————————————")
        for idx, result in enumerate(results):
            result['chat_record_id'] = reviews_data[idx]['chat_record_id']
            save_review_to_db(result)
    except Exception as e:
        print(f"Error: {e}")
    import traceback
    print(traceback.format_exc())


if __name__ == '__main__':
    main()
