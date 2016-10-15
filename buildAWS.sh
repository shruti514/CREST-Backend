#!/usr/bin/env bash
mvn clean package
echo "[INFO] ---------------------  Copying artifact to Tomcat ----------------------"
mvn spring-boot:run
echo "[INFO] ---------------- Successfully copied artifact to Tomcat ----------------"