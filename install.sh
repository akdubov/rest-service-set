#!/bin/bash
echo "Start installation 'rest-service-test'..."

eval $(crc oc-env)

ISTIO_MEMBERS=$(oc get smmr default -n istio-system -o jsonpath='{.spec.members}')
echo ISTIO MEMBERS NAMESPACE: $ISTIO_MEMBERS
sleep 5

CRC_REGISTRY=default-route-openshift-image-registry.apps-crc.testing
export CRC_REGISTRY

docker login -u kubeadmin -p $(oc whoami -t) $CRC_REGISTRY

DEL_RES="$(oc get project.project.openshift.io/rest-service-test --show-kind --ignore-not-found)"
if [ ${#DEL_RES} != 0 ]; 
then 
   oc delete project rest-service-test

   DEL_RES="$(oc get project.project.openshift.io/rest-service-test --show-kind --ignore-not-found)"
   echo "Waiting for delete project 'rest-service-test'..."
   while [ ${#DEL_RES} != 0 ]
   do
       echo "Waiting...";
       sleep 3;
       DEL_RES="$(oc get project.project.openshift.io/rest-service-test --show-kind --ignore-not-found)";
   done && echo "Finish waiting for project deletion.\n";
fi

echo "Create project 'rest-service-test'"
oc new-project rest-service-test

mvn clean install

# формируем docker-образ и загружаем в кластер OpenShift
cd ./rest-client-privileged
docker build -t rest-service-test/rest-client-privileged:latest ./
docker tag rest-service-test/rest-client-privileged $CRC_REGISTRY/rest-service-test/rest-client-privileged
docker push $CRC_REGISTRY/rest-service-test/rest-client-privileged

cd ../rest-client-regular
docker build -t rest-service-test/rest-client-regular:latest ./
docker tag rest-service-test/rest-client-regular $CRC_REGISTRY/rest-service-test/rest-client-regular
docker push $CRC_REGISTRY/rest-service-test/rest-client-regular

cd ../rest-server-service
docker build -t rest-service-test/rest-server-service:latest ./
docker tag rest-service-test/rest-server-service $CRC_REGISTRY/rest-service-test/rest-server-service
docker push $CRC_REGISTRY/rest-service-test/rest-server-service

cd ..

# развертывание сервисов
oc apply -f ./rest-client-privileged/yaml/01_deployment-server.yml -n rest-service-test
oc apply -f ./rest-client-privileged/yaml/02_service-server.yml -n rest-service-test
# маршрутизация/доступность сервиса 
oc expose svc/rest-client-privileged -n rest-service-test

oc apply -f ./rest-client-regular/yaml/01_deployment-server.yml -n rest-service-test
oc apply -f ./rest-client-regular/yaml/02_service-server.yml -n rest-service-test
oc expose svc/rest-client-regular -n rest-service-test

# целевой (серверный) сервис
oc apply -f ./rest-server-service/yaml/01_deployment-server.yml -n rest-service-test
oc apply -f ./rest-server-service/yaml/02_service-server.yml -n rest-service-test
oc apply -f ./rest-server-service/yaml/03_ingress-gateway.yaml -n rest-service-test
oc apply -f ./rest-server-service/yaml/04_mesh-virtualService.yaml -n rest-service-test
oc apply -f ./rest-server-service/yaml/06_mesh-destinationRule.yaml -n rest-service-test

echo "finish"