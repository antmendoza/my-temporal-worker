#!/bin/bash

kubectl delete deployment my-workers

kubectl apply -f deployment.yaml