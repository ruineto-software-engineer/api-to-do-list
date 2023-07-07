# api-to-do-list
Spring boot app to do list (docker container) connecting to redis (docker container)

This is a simple spring-boot app that connects to redis to store and retrive the data.
Here Redis is used both as database and as well as caching system.

## Step 1 Create spring-boot-app
Create a spring-boot app with `spring-boot-starter-web`, `spring-boot-starter-data-redis`, `jedis`, `lombok` and others dependencies.
**Jedis** is the java client used to connect to redis instance.

Add the below properties to application.properties file
```
server.port=8085
spring.redis.host=redis
spring.redis.port=6379
```

Provide a JedisConnectionFactory which is used for RedisTemplate as shown below. Place this code on package config, on file RedisConfig
```java
@Bean
public JedisConnectionFactory jedisConnectionFactory() {
	RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
	config.setHostName(host);
	config.setPort(port);
	return new JedisConnectionFactory(config);
}
	
@Bean
public RedisTemplate<String, Object> redisTemplate(){
	RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
	redisTemplate.setConnectionFactory(jedisConnectionFactory());
	redisTemplate.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
	return redisTemplate;
}
 ```
Use this redisTemplate to do all the hashOperations like put, get etc

## Step 2 Create a Dockerfile for spring-boot-app
Create a Dockerfile as shown below
 ```
FROM openjdk

ADD target/spring-boot-redis.jar spring-boot-redis.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "spring-boot-redis.jar"]
 ```

## Step 3 Create a docker-compose file
Create a docker-compose.yml file as shown below. Here the app is binded to redis instance
 ```yml
 version: '3'
services:
  app:
    build: .
    ports:
     - "8080:8080"
    links:
      - redis
  redis:
    image: redis
    ports:
     - "6379:6379"
```

## Step 4 Execute
To execute this app<br/>

    mvn clean install
  
    docker-compose build
  
    docker-compose up

## Documentation
Hit the url http://localhost:8080/swagger-ui/index.html with server up, to go the swagger doc.

## Endpoint List
### GET http://localhost:8080/
Hit the following endpoint to get a simple string to check the app
**Response**:
```json
string
```

### POST http://localhost:8080/tasks/
Hit the following endpoint to post a record
**Payload**:
```json
{
  "id": 0,
  "title": "string",
  "description": "string",
  "status": "string"
}
```

### GET http://localhost:8080/tasks/
Hit the following endpoint to get all tasks
**Response**:
```json
[
  {
    "id": 0,
    "title": "string",
    "description": "string",
    "status": "string"
  }
]
```

### GET http://localhost:8080/tasks/{id}
Hit the following endpoint to get all tasks
**Response**:
```json
{
  "id": 0,
  "title": "string",
  "description": "string",
  "status": "string"
}
```

### PUT http://localhost:8080/tasks/{id}
Hit the following endpoint to get all tasks
**Payload**:
```json
{
  "id": 0,
  "title": "string",
  "description": "string",
  "status": "string"
}
```
**Response**:
```json
string
```

### DELETE http://localhost:8080/tasks/{id}
Hit the following endpoint to get all tasks
**Response**:
```json
string
```

## Useful docker-compose commands
- `docker-compose build`
- `docker-compose up -d` (Detached)
- `docker-compose down` (stop)
- `docker-compose restart` (restart the services)