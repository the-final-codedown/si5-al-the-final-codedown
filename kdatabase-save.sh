#!/bin/bash

kubectl exec -ti deployment/mongo -- mongodump --host=mongo --port=27017 --out=/home --db=tfc
POD=$(kubectl get pod -l run=mongo -o jsonpath="{.items[0].metadata.name}")
kubectl cp  "${POD}":home/tfc dump/tfc
