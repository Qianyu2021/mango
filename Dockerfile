
FROM openjdk:21
VOLUME /tmp
EXPOSE 8081

#copy the JAR file to the working directiory of the image which is by default /
#the copied JAR file will be renamed to mango.jar
COPY target/Mangos-Blog-0.0.1-SNAPSHOT.jar mango.jar
ENTRYPOINT ["java","-jar","/mango.jar"]

