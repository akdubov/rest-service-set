#! /bin/bash

echo $CRC_REGISTRY

docker build -t rest-service-test/rest-service-server:latest ./

docker tag rest-service-test/rest-service-server $CRC_REGISTRY/rest-service-test/rest-service-server

docker push $CRC_REGISTRY/rest-service-test/rest-service-server

./server_config.sh

oc expose svc/rest-service-server -n rest-service-test