kubectl config view
kubectl get nodes -o wide
kubectl delete deployment builder -n ci --ignore-not-found=true
kubectl apply -f namespace.yaml
kubectl apply -f service-account.yaml
kubectl apply -f volume.yaml
kubectl apply -f service.yaml
kubectl apply -f ingress-rules.yaml
kubectl get ingress builder-ingress -n ci
kubectl get services builder -n ci
kubectl get pods -n ci