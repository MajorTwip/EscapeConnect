#!/bin/bash

echo ""
echo "Dieses Script installiert EscapeConnect"
echo ""
echo ""
echo "Folgendes wird erledigt:"
echo ""
echo "1. Allgemeines update/upgrade mittels apt-get update && apt-get upgrade"
echo "2. Installieren von docker mittels curl -sSL https://get.docker.com | sh"
echo "3. Erstelle Ordner /escapeconnect und Unterordner"
echo "4. Starte einen MQTT-Broker in einem Dockercontainer (mosquitto)"
echo "5. Starte EscapeConnect"
echo ""
echo "Jetzt starten? "
read -r -p "[J] Ja     [n] Nein   " response
if [[ "$response" =~ ^([jJyY])+$ ]]
then
    sudo apt-get update -y
    sudo apt-get upgrade -y
    sudo apt-get install curl wget
    
    
    sudo curl -sSL https://get.docker.com | sh
    sudo usermod -aG docker pi
    sudo systemctl start docker
    sudo systemctl enable docker
    
    
    sudo mkdir /data
    sudo mkdir /data/escapeconnect
    sudo mkdir /data/mqtt_conf/
    sudo touch /data/mqtt_conf/mosquitto.conf
    
    
    sudo docker rm MQTT EscapeConnect
    sudo docker run -d -p 1883:1883 -p 9001:9001 -v /data/mqtt_conf/:/mosquitto/config/ --name MQTT --restart always eclipse-mosquitto
    sudo docker run -d -p 80:8080 -v /data/escapeconnect/:/data/ --name EscapeConnect --restart always majortwip/escapeconnect:arm32v7
fi




