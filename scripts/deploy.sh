#!/bin/bash
cp target/*.war /usr/local/tomcat/webapps/ROOT.war
docker build -t majortwip/escapeconnect:latest .
echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
docker push majortwip/escapeconnect