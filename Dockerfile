# N00b (a.k.a. me) documentation:
#
# Build with:
# 	docker build -t="tmoerman:sourire" .
# 
# Run with:
# 	docker run -d -p 9999:8080 tmoerman:sourire
#
# Test with:
# 	http://192.168.59.103:9999/molecule/OCCc1c%28C%29%5Bn%2B%5D%28%3Dcs1%29Cc2cnc%28C%29nc%28N%292?render-comment=Vitamin%20B1
# 

FROM ubuntu:14.04

MAINTAINER Thomas Moerman (https://twitter.com/thomasjmoerman)

RUN apt-get -y update
RUN sudo apt-get install -y openjdk-7-jdk wget git

RUN wget -O /bin/lein https://raw.github.com/technomancy/leiningen/stable/bin/lein
RUN chmod 755 /bin/lein

ENV JAVA_HOME /usr/lib/jvm/java-7-openjdk-amd64
ENV LEIN_ROOT yes

RUN lein version

WORKDIR /data

# Clone the git repo and build sourire standalone jar
RUN git clone https://github.com/tmoerman/sourire.git && \
    cd /data/sourire && \
    lein create-standalone

# Move the build artifact to bin and remove the temporary files
RUN mv /data/sourire/target/sourire-*-standalone.jar /data/sourire.jar && \
    cd /data && \
    rm -rf sourire

# State this in the docker file as info for the operator (`docker inspect <id>`)
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "sourire.jar", ":port", "8080"]
cmd [""]