import requests
import json

with open('whisky.json') as json_data:
    data = json.load(json_data)
    print(data)

body = json.dumps(data)
print(body)

url = "http://localhost:8080/whiskies"
headers = {'Content-type': 'application/json'}
response = requests.post(url, data=body, headers=headers)
