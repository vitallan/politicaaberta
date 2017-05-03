FROM java:8

VOLUME /volume

ENV LOG_FILE /volume/api.log
ENV DB_URL ${DB_URL}
ENV DB_USERNAME ${DB_USERNAME}
ENV DB_PASSWORD ${DB_PASSWORD}

ADD target/api.jar api.jar

RUN bash -c 'touch /api.jar'

ENTRYPOINT ["java","-Xms64M","-Xmx64M","-jar","/api.jar"]
