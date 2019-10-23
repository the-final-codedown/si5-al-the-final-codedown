#!/bin/bash
git submodule foreach bash prepare.sh
docker-compose run --rm start_dependencies
docker-compose up -d app cap