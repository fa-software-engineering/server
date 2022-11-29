FROM maven:3.8.6-eclipse-temurin-17-alpine as build

ENV HOME=/usr/app

RUN mkdir -p $HOME

WORKDIR $HOME

ADD . $HOME

RUN mvn package -Dmaven.test.skip -f pom.xml

FROM eclipse-temurin:17

COPY --from=build /usr/app/target/server-1.0-SNAPSHOT.jar /app/server.jar

ENTRYPOINT java -jar /app/server.jar