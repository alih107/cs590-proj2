minikube kubectl delete -f api-service-deploy.yml
minikube kubectl delete -f auth-service-deploy.yml
minikube kubectl delete -f banking-service-deploy.yml
minikube kubectl delete -f creditcard-service-deploy.yml
minikube kubectl delete -f gateway-ingress-deploy.yml
minikube kubectl delete -f payment-service-deploy.yml
minikube kubectl delete -f paypal-service-deploy.yml
minikube kubectl delete -f postgres-create-db-job.yml
minikube kubectl delete -f postgres-deploy.yml
minikube kubectl delete -f postgres-secret.yml
minikube kubectl delete -f services-configmap.yml
minikube kubectl delete -f transaction-service-deploy.yml

minikube stop