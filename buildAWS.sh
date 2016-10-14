#!/usr/bin/env bash
mvn clean package
echo "[INFO] ---------------------  Copying artifact to Tomcat ----------------------"
sudo cp target/crest-backend-1.0-SNAPSHOT.jar /usr/share/tomcat7/webapps/
echo "[INFO] ---------------- Successfully copied artifact to Tomcat ----------------"