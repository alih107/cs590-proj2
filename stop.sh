kubectl delete -f api-service-deploy.yml
kubectl delete -f auth-service-deploy.yml
kubectl delete -f banking-service-deploy.yml
kubectl delete -f creditcard-service-deploy.yml
kubectl delete -f gateway-ingress-deploy.yml
kubectl delete -f payment-service-deploy.yml
kubectl delete -f paypal-service-deploy.yml
kubectl delete -f postgres-create-db-job.yml
kubectl delete -f postgres-deploy.yml
kubectl delete -f postgres-secret.yml
kubectl delete -f secret-service.yml
kubectl delete -f services-configmap.yml
kubectl delete -f product-service-deploy.yml
kubectl delete -f order-service-deploy.yml
kubectl delete -f transaction-service-deploy.yml

minikube stop