#!/bin/bash

POD=$(kubectl get pod -l run=mongo -o jsonpath="{.items[0].metadata.name}")
kubectl cp dump/tfc "${POD}":home/
kubectl exec -ti deployment/mongo -- mongorestore --host=mongo --port=27017 --db=tfc /home/tfc