
# README

#### INFORMATION:
* PROJECT'S NAME: GATEWAYS
* PRODUCED BY: YAMIL MARTINEZ MENGUAL
* Spring boot version 3.1.0
* Required JDK 17

#### DATABASE INFORMATION:
H2 database is an open source, embedded and in memory relational database management system.
It is written in Java and provides a client/server application. It stores data in system memory instead of disk.

Open: http://localhost:8080/h2-console/
user: gateway
password: gateway

# Getting Started

#### How to compile the program
* In the root folder of the application, raise a console and run “mvn package”,
  requirement to have installed Maven or with a programming IDE run the program.


####  Run Program
* java -jar gateway.jar --server.port=8080.

#### Create Gateway:
  URL endpoint local server: http://localhost:8080/api/gateway/create
* Method: POST
* Request example:
  {
  "serialNumber":"NF231",
  "name":"gateway1",
  "address":"192.168.1.1",
  "devices":[
  {
  "idDevice": 1,
  "uid": "323",
  "vendor": "F323",
  "createdAt": null,
  "status": true
  },{
  "idDevice": 2,
  "uid": "324",
  "vendor": "F324",
  "createdAt": null,
  "status": true
  }

  ]
  }
* Response example:
  {
  "idGateway": 1,
  "serialNumber": "NF231",
  "name": "gateway1",
  "address": "192.168.1.1",
  "devices": [
  {
  "idDevice": 1,
  "uid": "323",
  "vendor": "F323",
  "createdAt": "2023-06-21",
  "status": true
  },
  {
  "idDevice": 2,
  "uid": "324",
  "vendor": "F324",
  "createdAt": "2023-06-21",
  "status": true
  }
  ]
  }
#### List Gateway:
  URL endpoint local server: http://localhost:8080/api/gateway/
* Method: GET
* Request example:
* Response example:
  [
  {
  "idGateway": 1,
  "serialNumber": "NF231",
  "name": "gateway1",
  "address": "192.168.1.1",
  "devices": [
  {
  "idDevice": 1,
  "uid": "323",
  "vendor": "F323",
  "createdAt": "2023-06-21",
  "status": true
  },
  {
  "idDevice": 3,
  "uid": "328",
  "vendor": "F328",
  "createdAt": "2023-06-21",
  "status": false
  }
  ]
  }
  ]
#### Find Gateway By Id:
  URL endpoint local server: http://localhost:8080/api/gateway/1
* Method: GET
* Request example:
* Response example:
  {
  "idGateway": 1,
  "serialNumber": "NF231",
  "name": "gateway1",
  "address": "192.168.1.1",
  "devices": [
  {
  "idDevice": 1,
  "uid": "323",
  "vendor": "F323",
  "createdAt": "2023-06-21",
  "status": true
  },
  {
  "idDevice": 3,
  "uid": "328",
  "vendor": "F328",
  "createdAt": "2023-06-21",
  "status": false
  }
  ]
  }
* #### Delete a Device from the Gateway by IdGateway and IdDevice:
  URL endpoint local server: http://localhost:8080/api/gateway/delete/1/2
* Method: DELETE
* Request example:
* Response example:
  {
  "code": "400",
  "name": "Argument not valid",
  "description": "Gateway selected have not that Device",
  "source": "com.gateway.gatewaybackend.controller.GatewayController"
  }
#### Add Device to Gateway by IdGateway and IdDevice:
  URL endpoint local server: http://localhost:8080/api/gateway/1/add-device/3
* Method: PUT
* Request example:
* Response example:
  {
  "code": "400",
  "name": "Argument not valid",
  "description": "Device you are trying to add is already on the Gateway",
  "source": "com.gateway.gatewaybackend.controller.GatewayController"
  }
#### Create Device:
 URL endpoint local server: http://localhost:8080/api/device/create
* Method: POST
* Request example:
  {
  "uid":"328",
  "vendor":"F328",
  "status":false
  }
* Response example:
  {
  "idDevice": 3,
  "uid": "328",
  "vendor": "F328",
  "createdAt": "2023-06-21",
  "status": false
  }
#### List Devices:
  URL endpoint local server: http://localhost:8080/api/device/
* Method: GET
* Request example:
* Response example:
  [
  {
  "idDevice": 1,
  "uid": "323",
  "vendor": "F323",
  "createdAt": "2023-06-21",
  "status": true
  },
  {
  "idDevice": 2,
  "uid": "324",
  "vendor": "F324",
  "createdAt": "2023-06-21",
  "status": true
  },
  {
  "idDevice": 3,
  "uid": "328",
  "vendor": "F328",
  "createdAt": "2023-06-21",
  "status": false
  }
  ]

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.0/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.0/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.1.0/reference/htmlsingle/#web)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.1.0/reference/htmlsingle/#data.sql.jpa-and-spring-data)
* [Validation](https://docs.spring.io/spring-boot/docs/3.1.0/reference/htmlsingle/#io.validation)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Validation](https://spring.io/guides/gs/validating-form-input/)

