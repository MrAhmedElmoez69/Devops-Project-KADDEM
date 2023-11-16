FROM openjdk:8
EXPOSE 8092
ADD  target/khaddem-4.0.jar khaddem-4.0.jar
ENTRYPOINT ["java" , "-jar" , "khaddem-4.0.jar" ]
