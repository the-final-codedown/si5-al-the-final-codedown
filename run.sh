#!/bin/bash

[[ ! $1 ]] && echo Please provide the argument "micro" or "hybrid" && exit
ARCH=$1

docker-compose run --rm start_dependencies
#sh database-restore.sh
docker-compose -f docker-compose.yml -f docker-compose."$ARCH".yml up -d
docker-compose -f docker-compose.yml -f docker-compose."$ARCH".yml logs -f --tail 20