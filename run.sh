#!/bin/bash

docker-compose run --rm start_dependencies
docker-compose up -d app cap transfer