#!/bin/bash

docker run --rm --network "${PWD##*/}"_backend -v "$(pwd)"/dump:/home mongo:4.2.2-bionic mongorestore --drop --host=mongo --port=27017 --db=tfc /home/tfc