#!/bin/bash

docker build majortwip/escapeconnect
echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
docker push