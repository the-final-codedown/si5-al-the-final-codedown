#!/bin/bash

[[ ! $1 ]] && echo Please provide the argument "micro" or "hybrid" && exit
ARCH=$1

docker-compose -f docker-compose.yml -f docker-compose."$ARCH".yml down
