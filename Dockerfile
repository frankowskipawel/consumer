FROM alpine
#COPY . /consumer
RUN apk update && apk add \
&& apk --no-cache add openjdk11 --repository=http://dl-cdn.alpinelinux.org/alpine/edge/community \
&& apk add maven && apk add git