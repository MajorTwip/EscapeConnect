#!/bin/bash
cp target/*.war ./ROOT.war
cp schemas/api.yaml swagger-dist/api.yaml
docker build -t majortwip/escapeconnect:latest .
echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
docker push majortwip/escapeconnect
docker build -t majortwip/escapeconnect:arm32v7 -f Dockerfile-arm32v7 .
docker push majortwip/escapeconnect