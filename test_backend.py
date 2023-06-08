import json
import requests

u = "http://localhost:8080/"
token = None

def make_api_request(endpoint, method="GET", authenticated=True, data=None):
    return requests.request(method, u + endpoint, headers={
        "Content-Type": "application/json" if method == "POST" else None,
        "Authorization": f"Bearer {token}" if authenticated else None
    }, data=json.dumps(data) if method == "POST" else None)

def login(email, password):
    return make_api_request("auth/login", "POST", False, {
        "email": email,
        "password": password
    }).json()["accessToken"]

def create_chat_request(vendor):
    return make_api_request("v1/chats/request", "POST", True, {
        "vendorId": vendor
    })

token = login("test1@test.cc", "test1")
print(create_chat_request(1).text)