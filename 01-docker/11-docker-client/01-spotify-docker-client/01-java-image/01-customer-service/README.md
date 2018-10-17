# one service docker

> Run Test

`CustomerVerticleTest`

## Docker - manual steps

> build image

```
$ docker build -t local-customer .
$ docker images
REPOSITORY          TAG                 IMAGE ID            CREATED              SIZE
local-customer      latest              26ea0a1d2895        About a minute ago   319MB
java                8-jre               e44d62cf8862        6 months ago         311MB
```

> run container

```
$ docker run -p 8081:8080 -d local-customer
5295f1b99e80582e130ce3622dde7919d2cdbc213af8c52f5f150c85141e8210
$ docker ps
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS                    NAMES
5295f1b99e80        local-customer      "sh -c 'exec java ..."   41 seconds ago      Up 42 seconds       0.0.0.0:8081->8080/tcp   zealous_galileo
```

> test

http://localhost:8081/api/customers

```
[
{
id: 0,
name: "Tom",
age: 18
},
{
id: 1,
name: "Dick",
age: 28
}
]
```