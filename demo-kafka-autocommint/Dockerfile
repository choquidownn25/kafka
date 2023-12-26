FROM openjdk:19

WORKDIR /app/demo-kafka-autocommit

COPY ./pom.xml /app
COPY ./demo-kafka-autocommit/.mvn ./.mvn
COPY ./demo-kafka-autocommit/mvnw .
COPY ./demo-kafka-autocommit/pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
#RUN ./mvnw dependency:go-offline

COPY ./demo-kafka-autocommit/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:19

WORKDIR /app

COPY --from=builder /app/demo-kafka-autocommit/target/demo-kafka-autocommint-1.0.1.jar .
EXPOSE 9096

CMD ["java", "-jar", "demo-kafka-autocommint-1.0.1.jar"]