#!/bin/bash

cp ../tfc-cap-updater/src/proto/tfc-cap-updater.proto src/main/protobuf/tfc-cap-updater.proto
cp ../tfc-transfer-validator/src/proto/tfc-transfer-validator.proto src/main/protobuf/tfc-transfer-validator.proto
sbt compile