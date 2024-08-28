import requests

# Step definitions for BDD scenarios

def validLogin():
    response = requests.post('https://example.com/api/login', json={'username': 'validUser', 'password': 'validPass'})
    assert response.status_code == 200
    assert 'Welcome' in response.text

def invalidLogin():
    response = requests.post('https://example.com/api/login', json={'username': 'invalidUser', 'password': 'invalidPass'})
    assert response.status_code == 401
    assert 'Error' in response.text

def emptyLogin():
    response = requests.post('https://example.com/api/login', json={'username': '', 'password': ''})
    assert response.status_code == 401
    assert 'Error' in response.text

def emptyInputValidation():
    response = requests.post('https://example.com/api/login', json={'username': '   ', 'password': '   '})
    assert response.status_code == 401
    assert 'Error' in response.text

def alphanumericValidation():
    response = requests.post('https://example.com/api/login', json={'username': 'user123', 'password': 'pass123'})
    assert response.status_code == 200
    assert 'Welcome' in response.text