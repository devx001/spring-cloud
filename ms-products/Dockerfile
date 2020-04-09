FROM openjdk:8
VOLUME /tmp
ADD ./build/libs/ms-products-0.0.1-SNAPSHOT.jar ms-products.jar
ENTRYPOINT ["java", "-jar", "/ms-products.jar"]