#! /bin/bash

docker build -t rest-service-test/rest-client-privileged:latest ./

docker tag rest-service-test/rest-client-privileged $CRC_REGISTRY/rest-service-test/rest-client-privileged

docker push $CRC_REGISTRY/rest-service-test/rest-client-privileged

oc apply -f yaml/01_deployment-server.yml -n rest-service-test

#
oc apply -f yaml/02_service-server.yml -n rest-service-test

oc expose svc/rest-client-privileged -n rest-service-test



