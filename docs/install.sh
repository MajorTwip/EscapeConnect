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

read -p "[J] Ja     [n] Nein" -n 1 -r
if [[ $answer =~ ^[YyJj]$ ]]
then
    sudo apt-get update -y
    sudo apt-get upgrade -y
    sudo apt-get install curl wget -y
    sudo curl -sSL https://get.docker.com | sh
    sudo usermod -aG docker pi
    sudo mkdir /data
    sudo mkdir /data/escapeconnect
    sudo touch /data/mqtt_conf/
    sudo mkdir /data/mqtt_conf
    docker run -d -p 1883:1883 -p 9001:9001 -v /data/mqtt_conf/:/mosquitto/config/ --name MQTT --restart always eclipse-mosquitto
    docker run -d -p 80:8080 -v /data/escapeconnect/:/data/ --name EscapeConnect --restart always majortwip/escapeconnect
fi



