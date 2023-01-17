kubectl apply -f namespace.yaml
kubectl apply -f service-account.yaml
kubectl apply -f volume.yaml
kubectl apply -f service.yaml
kubectl apply -f ingress-rules.yaml
kubectl get pods -n ci