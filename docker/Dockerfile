FROM gradle:7.6.0-jdk17 AS builder

WORKDIR /opt/app

# Only copy dependency-related files
COPY build.gradle settings.gradle /opt/app/

# Only download dependencies (to cache)
# Doesn't show the expected build failure since no source code has been copied yet
RUN gradle clean build --no-daemon > /dev/null 2>&1 || true

COPY . .

RUN gradle build --no-daemon -x test

################################################################################

FROM openjdk:17 AS runner
VOLUME /tmp

COPY --from=builder /opt/app/build/libs/*-SNAPSHOT.jar /app.jar

ENV PROFILE="default"
ENV JOB_PARAMETER_CUSTOMER_FILE="default"

ENTRYPOINT java -jar -Dspring.profiles.active=${PROFILE} /app.jar "customerFile=file:/opt/app/${JOB_PARAMETER_CUSTOMER_FILE}"
