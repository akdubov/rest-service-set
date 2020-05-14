#! /bin/bash

docker build -t rest-service-test/rest-client-regular:latest ./

docker tag rest-service-test/rest-client-regular $CRC_REGISTRY/rest-service-test/rest-client-regular

docker push $CRC_REGISTRY/rest-service-test/rest-client-regular

oc apply -f yaml/01_deployment-server.yml -n rest-service-test

#
oc apply -f yaml/02_service-server.yml -n rest-service-test

oc expose svc/rest-client-regular -n rest-service-test



