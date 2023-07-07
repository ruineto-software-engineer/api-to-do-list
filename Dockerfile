FROM openjdk

ADD target/spring-boot-redis.jar spring-boot-redis.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "spring-boot-redis.jar"]