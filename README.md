# Todo App

Todo App created using Spring Boot + HTMX + Thymeleaf + Bootstrap + MySQL

## Running the app

### Prerequisites

1. Docker Desktop / Docker Compose should be installed.
2. Docker should be running.
3. `docker-compose --help` command should work.
4. Java 17 should be installed.
5. `$JAVA_HOME` environment variable should point to the Java 17 installation.
6. `java --version` and `javac --version` should print Java 17 version.

### Steps

1. Start MySQL server inside docker container

	```shell
	docker-compose up -d
	```

2. Start Spring Boot Application\
	Start the application using below command
	```shell
	./gradlew bootRun
	```
 
	Or, from IDE run the `main` method of `HtmxTodoApplication` class.

3. Open `localhost:8080/todos` in the browser.

## Stopping the app

### Steps

1. Stop Spring Boot Application\
	If Spring Boot application is running in terminal, stop it using `Ctrl-C` key. If the application is running using IDE, stop it using **Stop** button.

2. Stop MySQL Server

	```shell
	docker-compose down
	```
