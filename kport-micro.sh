#!/bin/bash

sh kport.sh
kubectl port-forward service/account 8081:8081 &
kubectl port-forward service/savings 8082:8082 &
kubectl port-forward service/profile 8083:8083 &
kubectl port-forward service/rolling-history 8084:8084 &
kubectl port-forward service/dump 8085:8085 &
