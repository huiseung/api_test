from locust import HttpUser, task, between

class RestTemplateVirtualUser(HttpUser):
    @task
    def get_resttemplate_virtual(self):
        self.client.get("/resttemplate-virtual")
