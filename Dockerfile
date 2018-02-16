FROM java:8

VOLUME /volume

ENV TOKEN ${TOKEN}
ENV LOG_FILE /volume/api.log
ENV DB_URL ${DB_URL}
ENV DB_USERNAME ${DB_USERNAME}
ENV DB_PASSWORD ${DB_PASSWORD}

ADD target/web.jar web.jar

RUN bash -c 'touch /web.jar'

ENTRYPOINT ["java","-Xms32M","-Xmx32M","-jar","/web.jar"]

