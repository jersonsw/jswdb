FROM openjdk:8-jdk-alpine as build
WORKDIR /workspace/app
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY core core
COPY data-adapter data-adapter
COPY scraping-adapter scraping-adapter
COPY main main
RUN chmod +x mvnw
RUN ./mvnw install -DskipTests
RUN mkdir -p main/target/dependency && (cd main/target/dependency; jar -xf ../*.jar)

FROM openjdk:8-jdk-alpine
RUN addgroup -S jswdb && adduser -S jswdb -G jswdb
USER jswdb
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/main/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","io.inouty.jswdb.main.Application"]
