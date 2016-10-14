#!/usr/bin/env bash
mvn clean package
echo "[INFO] ---------------------  Copying artifact to Tomcat ----------------------"
sudo nohup java -jar ./target/crest-backend-1.0-SNAPSHOT.jar &
echo "[INFO] ---------------- Successfully copied artifact to Tomcat ----------------"