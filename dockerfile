FROM tomcat:latest
LABEL name=tennis
COPY /target/tennis.war /usr/local/tomcat/webapps/tennis.war