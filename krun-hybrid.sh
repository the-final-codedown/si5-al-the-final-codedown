#!/bin/bash

cd kube-scripts || exit
kubectl apply -f bank.yaml
cd ..