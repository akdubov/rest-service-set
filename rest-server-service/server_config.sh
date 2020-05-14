#! /bin/bash

oc apply -f yaml/01_deployment-server.yml -n rest-service-test

oc apply -f yaml/02_service-server.yml -n rest-service-test

oc apply -f yaml/03_ingress-gateway.yaml -n rest-service-test

oc apply -f yaml/04_ingress-destinationRule.yaml -n rest-service-test

oc apply -f yaml/05_ingress-virtualService.yaml -n rest-service-test

