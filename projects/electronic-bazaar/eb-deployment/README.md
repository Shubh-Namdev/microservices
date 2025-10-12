# Minikube Deployment for Electronic-Bazaar Microservices

This package contains Kubernetes manifests to run the Electronic-Bazaar services on Minikube.

## Contents

- k8s/mysql-deployment.yaml
- k8s/auth-deployment.yaml
- k8s/product-deployment.yaml
- k8s/customer-deployment.yaml
- k8s/order-deployment.yaml
- k8s/payment-deployment.yaml
- k8s/gateway-deployment.yaml
- k8s/ingress.yaml

## Prerequisites

- minikube
- kubectl
- docker (for building images)

## Steps

1. Start minikube and enable ingress:

```bash
minikube start --memory=6g --cpus=3
minikube addons enable ingress
```

2. Point your terminal to minikube's Docker daemon so images are built into minikube:

```bash
eval $(minikube docker-env)
```

3. Build docker images for each service (run from the repo root where service folders exist):

```bash
docker build -t auth-service:latest ./auth-service
docker build -t product-service:latest ./product-service
docker build -t customer-service:latest ./customer-service
docker build -t order-service:latest ./order-service
docker build -t payment-service:latest ./payment-service
docker build -t api-gateway:latest ./gateway
```

4. Apply the manifests:

```bash
kubectl apply -f k8s/
```

5. Add host entry to access via browser (on Mac/Linux edit /etc/hosts as root, on Windows edit C:\\Windows\\System32\\drivers\\etc\\hosts):

```
127.0.0.1 electronicbazaar.local
```

6. Wait for pods to be ready:

```bash
kubectl get pods -w
```

7. Access the gateway UI (swagger) via:

```
http://electronicbazaar.local/swagger-ui/index.html
```

## Notes

- The manifests use a single MySQL instance named `mysql` and a single database `electronic_bazaar`.
- Replace `replace_this_with_a_very_long_secret` in the Deployment env vars with a secure secret or use Kubernetes Secrets for production.
- If you change image tags, update the `image:` values in the YAML files.

