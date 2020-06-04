# User Access Manager

Demo Spring boot application for give access for users in a team hierarchy

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

Your deployment environment should have 
```
Java 8+
MySQL Database Server 8+
MySQL Workbench
Maven
Postman REST client

```

### Installing

Please follow below installation steps


```
1. Unzip user-access-manager.zip into your local file diretory
2. Browse user-access-manager\src\main\resources\application.properties file and open with a text editor
3. Uppdate below properies with your MySql instance credentials and save.
		spring.datasource.username:your-mysql-instance-username
		spring.datasource.password:your-mysql-instanc-password
4. Browse user-access-manager\db_scripts\users_db.sql  
5. Open with MySql workbench and execute the script
6. Open the terminal and go to the user-access-manager root directory
7. Run below set of commands to run the application
		mvn clean install 
		mvn spring-boot:run
8. Open postman rest client and call APIs as mentioned in APIs section 
```


##APIs
Rest API to insert user access for a given user in USERACCESS table.

```
URL: http://localhost:8081/user-access-manager/api/access/add
Method: POST
Headers:
	Content-Type:application/json
Body:
	{
	"empId":"Tessa"
	}
```

Rest API to insert all users access in USERACCESS table.

```
URL: http://localhost:8081/user-access-manager/api/access/addAll
Method: POST
Headers:
	Content-Type:application/json
Body:
	{
	"empList":["Tessa","Dosson","Ricky","Aaron","Bob"]
	}
```

## Running the tests


### Break down into end to end tests


### And coding style tests


## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Java](https://www.oracle.com/sg/java/technologies/javase-downloads.html) - Used programming language
* [Maven](https://maven.apache.org/) - Dependency Management
* [MySQL](https://www.mysql.com/) - Used database server
* [MySQL Workbench](https://www.mysql.com/products/workbench/) - Used database designer client
* [Spring Boot](http://start.spring.io/) - Used bakend framework


## Further implementations

validate duplicate user accesses

## Contributing



## Versioning


## Authors

* **Dinesh Madushanka**



## License



## Acknowledgments

# User-Access-Manager
# User-Access-Manager
