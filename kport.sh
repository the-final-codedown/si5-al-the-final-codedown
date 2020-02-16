#!/bin/bash

kubectl port-forward service/mongo 27017:27017 &
kubectl port-forward service/transfer 50052:50052 &
kubectl port-forward service/cap 50051:50051 &
