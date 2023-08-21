#!/bin/bash

minikube stop

minikube delete

minikube start --memory 8192 --cpus 4
#minikube start --memory 6000 --cpus 2
# minikube start

sleep 30

eval $(minikube -p minikube docker-env)
