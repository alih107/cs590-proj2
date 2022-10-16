# cs590-proj2
CS 590 Software Architecture Mini project 2

1. Replace **whoiscan** in yml files to your DockerHub **username**
2. `docker login`
3. Go to each service folder
4. `docker build -t [your_username]/api-service:0.1 .`
5. `docker push [your_username]/api-service:0.1`
6. Go to k8s folder in terminal
7. Execute commands from `start.sh`
8. Make sure **postgres-service** is running before applying **postgres-create-db-job.yml** file
9. Import `cs590-proj2.postman_collection.json` into Postman
10. Order of requests:
- Register
- Login
- Put access token into Authorization header as bearer token
- Create product
- Place order

12. You can go to postgres-service using kubectl exec -it [podname] bash
13. You can get podname using `kubectl get pods`
13. psql -U admin
14. The rest you get it