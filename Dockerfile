#install Spring Boot artifact
FROM ubuntu:16.04

# Install Updates
RUN apt-get update -y && \
     apt-get upgrade -y && \
     apt-get install -y software-properties-common && \
     apt-add-repository -y ppa:openjdk-r/ppa && \
     apt-get update -y && \
     rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/* /var/cache/*

# Install Packages
RUN apt-get -qq update -y && \
    apt-get -q install -y \
        wget \
        openssh-server \
        openjdk-8-jdk \
        && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/* /var/cache/*

EXPOSE 8080

ADD /target/DevOpsDemo-0.2.1-SNAPSHOT.jar DevOpsDemo-0.2.1-SNAPSHOT.jar
RUN sh -c 'touch /DevOpsDemo-0.2.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/DevOpsDemo-0.2.1-SNAPSHOT.jar"]