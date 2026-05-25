**PREREQUISITES**

-java 21
-Postgresql
-Dbeaver or pgAdmin4
-Annotation processing enabled in IDE for lombok

**Features**

Backend Framework: Spring boot( Web, Data JPA)
Language: Java 21
Build Tool: Maven
Database: Postgresql
Testing: Mockito
Other Tools: Lombok

**Installation**

1) Clone my repository
2)Create empty Postgresql database with
name: postgres
pass:postgres
user:postgres


**Building**
Either use IDE or use maven .
Build with ./mvnw clean install
Run with ./mvnw spring-boot:run

**Running Tests**
./mvnw test

**API Documentation**

let’s have a base link: 


_METHOD	ENDPOINT	DESCRIPTION_

POST
http://localhost:8080/pkf/rdv/client/create	
This is for client creation. The requestparams are 
email:String
telephone: String
name: String
surname: String


POST	
http://localhost:8080/pkf/rdv/responsible/create	
This is for responsible creation. The requestparams are 
email:String
telephone: String
name: String
surname: String

POST	
http://localhost:8080/pkf/rdv/service/create	 
This is to create a service. The request param is name: String

POST
http://localhost:8080/pkf/rdv/service/{service_id}/{responsible_id}	
This is to assign a particular responsible to a particular service. The pathvariables are service_id: Long, responsible_id: Long

POST	
http://localhost:8080/pkf/rdv/create/{service_id}/{responsible_id}/{client_id}	
This is to create a rendezvous. The path variables are all of type Long. The request params are date: LocalDate(dd-MM-yyyy), motif: String, time: LocalTime(HH:mm:ss)
