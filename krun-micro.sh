#!/bin/bash

cd kube-scripts || exit
kubectl apply -f account.yaml
kubectl apply -f dump.yaml
kubectl apply -f profile.yaml
kubectl apply -f rolling-history.yaml
kubectl apply -f savings.yaml
kubectl patch deployment transfer --patch "$(cat transfer-micro-patch.yaml)"
cd ..