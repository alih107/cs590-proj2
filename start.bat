minikube start
minikube kubectl apply -f postgres-secret.yml
minikube kubectl apply -f services-configmap.yml
minikube kubectl apply -f postgres-create-db-job.yml
minikube kubectl apply -f postgres-deploy.yml
minikube kubectl apply -f creditcard-service-deploy.yml
minikube kubectl apply -f banking-service-deploy.yml
minikube kubectl apply -f paypal-service-deploy.yml
minikube kubectl apply -f transaction-service-deploy.yml
minikube kubectl apply -f payment-service-deploy.yml
minikube kubectl apply -f auth-service-deploy.yml
minikube kubectl apply -f api-service-deploy.yml
