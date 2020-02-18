#!/bin/bash

cd kube-scripts || exit
kubectl apply -f mongo.yaml
kubectl apply -f zookeeper.yaml
kubectl apply -f broker.yaml
kubectl apply -f cap.yaml
kubectl apply -f transfer.yaml
cd ..