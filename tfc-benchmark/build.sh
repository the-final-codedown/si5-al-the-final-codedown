#!/bin/bash

cp ../tfc-cap-updater/proto/tfc/cap/updater/tfc-cap-updater.proto src/main/protobuf/tfc-cap-updater.proto
cp ../tfc-transfer-validator/proto/tfc-transfer-validator.proto src/main/protobuf/tfc-transfer-validator.proto
sbt compile