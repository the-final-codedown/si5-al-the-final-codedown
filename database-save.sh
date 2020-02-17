#!/bin/bash

docker run --rm --network "${PWD##*/}"_backend -v "$(pwd)"/dump:/home mongo:4.2.2-bionic mongodump --host=mongo --port=27017 --out=/home --db=tfc
