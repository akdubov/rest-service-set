#! /bin/bash

echo $CRC_REGISTRY

docker build -t rest-service-test/rest-service-server:latest ./

docker tag rest-service-test/rest-service-server $CRC_REGISTRY/rest-service-test/rest-service-server

docker push $CRC_REGISTRY/rest-service-test/rest-service-server

oc apply -f yaml/01_deployment-server.yml -n rest-service-test

#
oc apply -f yaml/02_service-server.yml -n rest-service-test

oc expose svc/rest-service-server -n rest-service-test



