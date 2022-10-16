minikube start
kubectl apply -f postgres-secret.yml
kubectl apply -f services-configmap.yml
kubectl apply -f secret-service.yml
kubectl apply -f postgres-deploy.yml
kubectl apply -f postgres-create-db-job.yml
kubectl apply -f creditcard-service-deploy.yml
kubectl apply -f banking-service-deploy.yml
kubectl apply -f paypal-service-deploy.yml
kubectl apply -f transaction-service-deploy.yml
kubectl apply -f order-service-deploy.yml
kubectl apply -f payment-service-deploy.yml
kubectl apply -f auth-service-deploy.yml
kubectl apply -f product-service-deploy.yml
kubectl apply -f api-service-deploy.yml
minikube tunnel