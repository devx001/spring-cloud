FROM openjdk:8
VOLUME /tmp
EXPOSE 9002
ADD ./build/libs/zuul-service-0.0.1-SNAPSHOT.jar zuul-service.jar
ENTRYPOINT ["java", "-jar", "/zuul-service.jar"]