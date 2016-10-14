#!/usr/bin/env bash
mvn clean package
sudo cp target/crest-backend-1.0-SNAPSHOT.jar /usr/share/tomcat7/webapps/