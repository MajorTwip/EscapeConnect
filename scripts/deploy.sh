#!/bin/bash
cp target/*.war ./ROOT.war
docker build -t majortwip/escapeconnect:latest .
echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
docker push majortwip/escapeconnect