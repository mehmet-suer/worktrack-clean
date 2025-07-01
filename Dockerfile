FROM eclipse-temurin:21-jdk-alpine

# Non-root kullanıcı oluştur
RUN addgroup -S appgroup && adduser -u 1000 -S appuser -G appgroup

# Uygulama klasörünü oluşturup yetki ver
RUN mkdir -p /app && chown -R appuser:appgroup /app

WORKDIR /app

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

RUN chown -R appuser:appgroup /app

USER appuser
ENTRYPOINT ["java","-jar","app.jar"]
