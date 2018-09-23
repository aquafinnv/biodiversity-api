FROM java:8
ARG JAR_FILE
ADD target/${JAR_FILE} biodiversity-api.jar
ADD src/main/docker/wrapper.sh wrapper.sh
RUN bash -c 'chmod +x /wrapper.sh'
RUN bash -c 'touch /biodiversity-app.jar'
ENTRYPOINT ["/bin/bash", "/wrapper.sh"]