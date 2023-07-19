import sys
import json

data = sys.argv[1]


def extract_messages(data):
    result = {}
    messages = json.loads(data)['messages']

    for message in messages:
        sender_name = message['sender_name']
        message_content = message['message']
        result[sender_name] = message_content

    return result


message_data = extract_messages(data)
print(message_data)