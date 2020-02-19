#!/bin/bash

[[ ! $1 ]] && echo Please provide the argument "micro" or "hybrid" && exit

for i in $(cat < "$1".env); do
  export $i
done

sbt gatling-it:test