# Deploying workers in kubernetes

- Optional, setup minikube

`./0_minikube-setup.sh`

- Create jar

`./1_build-jar.sh`

- Build docker image

`./2_build-docker-img.sh`

- Deploy workers

`./3_kube-apply-deployment.sh`