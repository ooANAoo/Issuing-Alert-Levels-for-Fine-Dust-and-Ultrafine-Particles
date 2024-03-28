FROM amazoncorretto:21
ARG JAR_FILE=build/libs/docker-0.0.1-SNAPSHOT.jar
COPY build/libs/vga-price-tracker-0.0.1-SNAPSHOT.jar vga-price-tracker.jar
ENTRYPOINT ["java","-Djava.net.preferIPv4Stack=true","-jar","/vga-price-tracker.jar"]