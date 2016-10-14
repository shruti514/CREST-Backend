#!/usr/bin/env bash
mvn clean package
echo "------------------ Copying artifact to tomcat --------------------"
sudo cp target/crest-backend-1.0-SNAPSHOT.jar /usr/share/tomcat7/webapps/
echo "------------- Successfully copied artifact to tomcat -------------"