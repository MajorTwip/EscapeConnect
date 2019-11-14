#!/bin/bash

echo ""
echo "Dieses Script installiert EscapeConnect"
echo ""
echo ""
echo "Folgendes wird erledigt:"
echo ""
echo "- Änderung des gerätenamens (hostname)"
echo "- Allgemeines update/upgrade mittels apt-get update && apt-get upgrade"
echo "- Installieren von docker mittels curl -sSL https://get.docker.com | sh"
echo "- Erstelle Ordner /escapeconnect und Unterordner"
echo "- Starte einen MQTT-Broker in einem Dockercontainer (mosquitto)"
echo "- Starte EscapeConnect"
echo ""
echo "Jetzt starten? "
read -r -p "[J] Ja     [n] Nein   " response
if [[ "$response" =~ ^([jJyY])+$ ]]
then
        hostname -b escapeconnect
        cp /etc/hosts /etc/hosts.bak && sed 's/raspberrypi/escapeconnect/' /etc/hosts.bak > /etc/hosts


    apt-get update -y
    apt-get upgrade -y
    apt-get install curl wget


    curl -sSL https://get.docker.com | sh
    usermod -aG docker pi
    systemctl start docker
    systemctl enable docker


    mkdir /data
    mkdir /data/escapeconnect
    mkdir /data/mqtt_conf/
    touch /data/mqtt_conf/mosquitto.conf


    docker stop MQTT EscapeConnect
    docker rm MQTT EscapeConnect
    docker run -d -p 1883:1883 -p 9001:9001 -v /data/mqtt_conf/:/mosquitto/config/ --name MQTT --restart always eclipse-mosquitto
    docker run -d -p 80:8080 -v /data/escapeconnect/:/data/ --name EscapeConnect --restart always majortwip/escapeconnect:arm32v7
fi

localip=$(ip route get 1.2.3.4 | awk '{print $7}')
gateway=$(ip route get 1.2.3.4 | awk '{print $3}')
dns=$(resolvconf -l | grep nameserver | awk '{print $2}' | tr '\n' ' ')



echo ""
echo ""
echo "Ihre IP ist $localip, die Ihres Routers $gateway und sie nutzen folgende IPs als DNS: $dns"
echo "Um Problemen vorzubeugen sollte EscapeConnect die IP nicht wechseln, da sonst die Rätsel den Kontakt verlieren"
echo "Falls Sie wissen, wie auf dem Router eine Fixe IP zuzuweisen, tun Sie dies bitte"
echo "Falls nicht, können die aktuellen Werte fixiert werden. Dies funktioniert vermutlich, kann jedoch nicht garantiert werden"

echo "Werte fixieren? "

read -r -p "[J] Ja     [n] Nein   " response
if [[ "$response" =~ ^([jJyY])+$ ]]
then
    F="/etc/dhcpcd.conf"
    echo "" >> $F
    echo "interface eth0" >> $F
    echo "static ip_address=$localip/24" >> $F
    echo "static routers=$gateway" >> $F
    echo "static domain_name_servers=$dns" >> $F


    echo "" >> $F
    echo "interface wlan0" >> $F
    echo "static ip_address=$localip/24" >> $F
    echo "static routers=$gateway" >> $F
    echo "static domain_name_servers=$dns" >> $F


fi

echo ""
echo ""
echo "Die Installation ist fast beendet"
echo "besuchen Sie nun http://$localip/setup.html um die Installation abzuschliessen"
echo "Je nach Gerät könnte auch http://escapeconnect.local/setup.html funktionieren"
echo ""
echo "Danke dass sie Escapeconnect nutzen"
echo ""
echo "Mit der Entertaste wird der Rasperry Pi nun neu starten"
read
reboot
