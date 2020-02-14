#!/bin/bash

if [[ $1 == "micro" ]]; then
  docker-compose -f docker-compose.yml -f docker-compose."$1".yml stop account dump profile rollinghistory savings || true
elif [[ $1 == "hybrid" ]]; then
  docker-compose -f docker-compose.yml -f docker-compose."$1".yml stop bank || true
else
  docker-compose -f docker-compose.yml -f docker-compose.micro.yml -f docker-compose.hybrid.yml down
fi
