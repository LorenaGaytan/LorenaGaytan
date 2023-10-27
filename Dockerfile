FROM tomcat:10.1.12-jre17-temurin-jammy

COPY /target/schoolmanager.war /usr/local/tomcat/webapps

CMD ["catalina.sh", "run"]