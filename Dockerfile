FROM tomcat:9

RUN rm -rf /usr/local/tomcat/webapps/*


RUN mkdir /data
VOLUME /data