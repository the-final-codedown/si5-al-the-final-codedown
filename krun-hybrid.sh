#!/bin/bash

cd kube-scripts || exit
kubectl apply -f bank.yaml
kubectl patch deployment transfer --patch "$(cat transfer-hybrid-patch.yaml)"
cd ..