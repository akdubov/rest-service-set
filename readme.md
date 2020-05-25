Предварительные требования: 

	1) OpenJDK 1.8
	2) Maven 3.6.0 и выше
	3) Установленный и запущенный CodeReadyContainer 4.3 (CRC) и выше + установленный Istion service mesh
	4) Docker 19.0 и выше; должен иметь возможность поключиться к репозиторию образов CRC


Установка:

	1) Запустить CodeReadyContainer, убедиться что он работает, зайти в него администратором
        2) В Istio service mesh member roll прописать проект rest-service-test 
        2) Запустить файл install.sh из корневой директории проекта, дождаться развертывания проекта в среде CRC
        3) проверка успешного развертывания проекта:

	   целевой сервис:	
           curl http://istio-ingressgateway-istio-system.apps-crc.testing/getServiceResult

	   вспомогательные сервисы (сервисы-клиенты в кластере CRC для целевого сервиса)	
	   curl http://rest-client-regular-rest-service-test.apps-crc.testing/callFarService	
	   curl http://rest-client-privileged-rest-service-test.apps-crc.testing/callFarService

Проверка:
	В проекте SAOP UI сделаны несколько testcase'ов, эмулирцующих различные варианты вызова целевого сервиса 

	Внешние вызовы:	
	ExternalSimple          - вызов целевого сервиса (SoapUI клиент) на общих основаниях
        ExternalWithWIPHeader   - вызов целевого сервиса со специальным header'ом (client-group: vip) 
        ExterenalWithMatchParam - вызов целевого сервиса со специальным QueryParam (callerServiceName=vip-client-service)
        ExternalWithSpecialURI  - вызов целевого сервиса с альтернативным URL (/reserved/getServiceResult вместо /getServiceResult)
 

	Внутренние вызовы:
	InternalCRC-Regular     - вызов целевого сервиса (внутри кластера OpenShift)  внутринним сервисом на общих основаниях
	InternalCRC-Privileged  - вызов целевого сервиса другим внутренним сервисом с "привилегированными" возможностями (POD label: rest-client-privileged)
 
