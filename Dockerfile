FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
WORKDIR .
COPY /build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
ENV DATABASE_URL jdbc:postgresql://roundhouse.proxy.rlwy.net:34012/railway
ENV DATABASE_USERNAME postgres
ENV DATABASE_PASSWORD 64-EE-Dd*E2B3d3-ACf-a2A66FD4ggCb
EXPOSE 34012
