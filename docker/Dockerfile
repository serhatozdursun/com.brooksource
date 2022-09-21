FROM ubuntu:22.04

RUN apt update -y

WORKDIR app
COPY . /app/
RUN apt install -y wget unzip
ARG DEBIAN_FRONTEND=noninteractive



RUN apt-get update
RUN apt-get install -y --no-install-recommends openjdk-18-jdk
RUN apt-get install -y --no-install-recommends maven
RUN apt-get install -y --no-install-recommends firefox
RUN apt-get install -y --no-install-recommends git
RUN git clone https://github.com/onurkasaburi/com.brooksource.git --branch master
RUN cd com.brooksource &&\
    mvn clean install