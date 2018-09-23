#!/bin/bash
while ! exec 6<>/dev/tcp/${DATABASE_HOST}/${DATABASE_PORT}; do
    echo "Checking database connectivity at ${DATABASE_PORT}..."
    sleep 10
done

java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=container -jar /biodiversity-api.jar