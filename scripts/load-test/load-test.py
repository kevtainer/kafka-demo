from locust import HttpLocust, TaskSet, task
from faker import Faker
from random import choice
from random import randint
import os

fake = Faker()

class UserBehavior(TaskSet):
    def on_start(self):
        """ on_start is called when a Locust start before any task is scheduled """
        print('Starting')

    @task
    def load(self):
        self.client.get('/customer')
        self.client.post('/customer/create', {"firstName":fake.first_name(), "lastName":fake.last_name(), "email":fake.email()})

class WebsiteUser(HttpLocust):
    task_set = UserBehavior
    min_wait = 1000
    max_wait = 5000