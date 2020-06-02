FROM maven:3.6.0-jdk-11-slim AS build

COPY src /home/jjoules-plugin/src
COPY pom.xml /home/jjoules-plugin

RUN mvn -f /home/jjoules-plugin/pom.xml clean install
workdir /home/jjoules-plugin

CMD mvn jjoules:runtest

