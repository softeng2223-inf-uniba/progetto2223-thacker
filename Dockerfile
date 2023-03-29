FROM openjdk:19-jdk-slim
RUN mkdir /app
COPY ./build/libs/battleship-all.jar /app
WORKDIR /app
ENTRYPOINT ["java", "-jar", "battleship-all.jar"]