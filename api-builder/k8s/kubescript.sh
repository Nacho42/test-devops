kubectl config view
kubectl get nodes -o wide
kubectl delete deployment api-builder -n ci --ignore-not-found=true
kubectl apply -f namespace.yaml
kubectl apply -f secrets.yaml
kubectl apply -f db-volumes.yaml
kubectl apply -f db.yaml
kubectl apply -f service.yaml
kubectl apply -f ingress-rules.yaml
kubectl get ingress api-builder-ingress -n ci
kubectl get services api-builder -n ci
kubectl get pods -n ci