kubectl apply -f namespace.yaml
kubectl apply -f secrets.yaml
kubectl apply -f db-volumes.yaml
kubectl apply -f db.yaml
sleep 10
kubectl apply -f service.yaml
kubectl apply -f ingress-rules.yaml
kubectl get pods -n ci