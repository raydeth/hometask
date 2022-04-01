#!/bin/bash

SERVER_IP=18.212.131.131

sh /Applications/IntelliJ\ IDEA.app/Contents/plugins/maven/lib/maven3/bin/mvn -f ./pom.xml clean install

scp -i "~/.ssh/awsdemo.pem" ./target/product-*-SNAPSHOT.war ec2-user@$SERVER_IP:/tmp/tomcat/webapps/

ssh -i "~/.ssh/awsdemo.pem" -tt ec2-user@$SERVER_IP << EOF
  cd /tmp/tomcat/bin/
  ./catalina.sh stop
  ./catalina.sh start
EOF
