FROM openjdk:17-jdk-slim

VOLUME /tmp

# 更新包列表，安装python3, pip3，以及必要的依赖
RUN apt-get update && apt-get install -y python3 python3-pip && apt-get install -y gcc && apt-get install -y g++

# 安装Python库
RUN pip3 install pymysql gensim nltk transformers flair

# 由于NLTK额外需要下载数据集，所以我们也要下载这些数据集
RUN python3 -c "import nltk; nltk.download('vader_lexicon')"

COPY target/Talkative-1.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]

