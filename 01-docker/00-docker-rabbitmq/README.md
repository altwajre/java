# docker rabbitmq

https://docs.docker.com/samples/library/rabbitmq/

docker run -d --hostname my-rabbit --name some-rabbit -p 5672:5672 rabbitmq:3-management

>  RabbitMQ Management

http://localhost:5672

## cleanup

- Force delete all containers
docker rm -f $(docker ps -a -q)
- Force delete all images
docker rmi -f $(docker images -q)
