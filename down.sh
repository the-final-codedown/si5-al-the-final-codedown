#!/bin/bash

if [[ $1 ]]; then
  docker-compose -f docker-compose."$1".yml down
else
  docker-compose -f docker-compose.yml -f docker-compose.micro.yml -f docker-compose.hybrid.yml down
fi
