#!/bin/bash

sh kport.sh
kubectl port-forward service/bank 8081:8081 &
