#!/bin/bash
echo "Start init..."

eval $(crc oc-env)

docker login -u kubeadmin -p $(oc whoami -t) default-route-openshift-image-registry.apps-crc.testing

oc delete project rest-service-test

DEL_RES="$(oc get project.project.openshift.io/rest-service-test --show-kind --ignore-not-found)"
echo "Waiting for delete project 'rest-service-test'...";
while [ ${#DEL_RES} != 0 ];
    do
        echo "Waiting...";
        sleep 3;
        DEL_RES="$(oc get project.project.openshift.io/rest-service-test --show-kind --ignore-not-found)";
#        echo "DEBUG. Available symbols" : ${#DEL_RES};
    done && echo "Finish waiting for project deletion.\n";

#echo "Wait for project being deleted"

echo "Create new project"

oc new-project rest-service-test

CRC_REGISTRY=default-route-openshift-image-registry.apps-crc.testing
export CRC_REGISTRY

mvn clean install

cd ./rest-server-service

./server-deploy.sh

cd ../rest-client-regular
 
./client-regular-deploy.sh 

cd ../rest-client-privileged
 
./client-privileged-deploy.sh 


echo "finish"