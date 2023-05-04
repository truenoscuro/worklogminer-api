FROM openjdk:17.0.2
RUN groupadd -r 3digits
RUN useradd -r 3digits -g 3digits
USER 3digits:3digits
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} proyecto-base-springboot.jar
ENTRYPOINT ["java","-jar","/proyecto-base-springboot.jar"]
EXPOSE 8080
EXPOSE 8443