FROM eclipse-temurin:21-jdk-alpine AS build
LABEL authors="ferry"
ARG DOCKER_USER=0
RUN addgroup -S "$DOCKER_USER" && adduser -S "$DOCKER_USER" -G "$DOCKER_USER"
USER "$DOCKER_USER"
WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY inventory-service/pom.xml inventory-service/pom.xml
COPY inventography-entity/pom.xml inventography-entity/pom.xml
COPY order-service/pom.xml order-service/pom.xml
COPY inventography-utils/pom.xml inventography-utils/pom.xml
RUN ./mvnw -pl inventory-service -am dependency:go-offline

COPY inventory-service inventory-service
COPY inventography-entity inventography-entity
COPY order-service order-service
COPY inventography-utils inventography-utils
RUN ./mvnw -pl inventory-service -am package -DskipTests
RUN mkdir -p inventory-service/target/dependency && (cd inventory-service/target/dependency; jar -xf ../*.jar)

FROM eclipse-temurin:21-jre-alpine
ARG DOCKER_USER=0
ARG DEPENDENCY=/workspace/app/inventory-service/target/dependency

RUN addgroup -S "$DOCKER_USER" && adduser -S "$DOCKER_USER" -G "$DOCKER_USER"
USER "$DOCKER_USER"

EXPOSE 8002
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-Dspring.profiles.active=mydocker","-cp","app:app/lib/*","com.example.inventoryservice.InventoryServiceApplication"]
