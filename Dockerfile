FROM openjdk:17
ADD target/RHOProducer-1.jar rhoproducer.jar
ENTRYPOINT ["java","-jar","rhoproducer.jar"]