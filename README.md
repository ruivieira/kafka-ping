# ![ping](docs/ping.png) kafka-ping

`kafka-ping` is a REST server to send ad-hoc messages to Apache Kafka topics.

## Example

```shell
$ curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"foo":"bar","baz":"qux"}' \
  http://<KAFKA-PING>:8080/<TOPIC>
```

Sends a message with `{"foo":"bar","baz":"qux"}` to the topic `<TOPIC>`.

## Usage

### Locally

To use locally, simply run `docker-compose up`.

### OpenShift

Deploy using:

```shell
$ oc new-app ruivieira/kafka-ping \
    -e BROKER_URL=<BROKER_URL>:9092
```

## Build

Build the container using

```shell
$ mvn package
$ docker build -f src/main/docker/Dockerfile.native -t ruivieira/kafka-ping .
```