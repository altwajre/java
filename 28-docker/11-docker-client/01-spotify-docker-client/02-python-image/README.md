# spotify docker client

> Run App

- run app to build and start `myservice` container
- `docker ps` to verify the `myservice` is running, should see below

```
$ docker ps
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS               NAMES
7c0175895874        myservice           "python app.py"     6 seconds ago       Up 4 seconds                            boring_mclean
```

- `curl http://localhost:80` to verify

## docker command

> python

Repeatedly run, list, stop remove container

```
             host:container
docker run -p 80:5000 -d myservice
docker ps
docker stop 7c0175895874
docker rm -f 7c0175895874 # optional
```

```
mkdir dockerrun
cd dockerrun/

vi app.py
from flask import Flask
app = Flask(__name__)
@app.route('/')
def hello_world():
    return 'Hello World from python flask!\n'
if __name__ == '__main__':
    app.run(host="0.0.0.0", debug=True)

vi Dockerfile
FROM python:3.4-alpine
ADD . /code
WORKDIR /code
RUN pip install flask
CMD ["python", "app.py"]

docker build -t myservice . # -t for friendly name `myservice`

             host:container
docker run -p 80:5000 -d myservice

curl http://localhost:80

docker ps

docker ps -a
```

## Cleanup

```
# List all running containers
docker ps
# List all containers includes stopped ones
docker ps -a
# List all images
docker images

# Stop container
docker stop commerce-product-8080

# Delete all containers
docker rm $(docker ps -a -q)
# Force delete all containers
docker rm -f $(docker ps -a -q)

# Delete all images
docker rmi $(docker images -q)
# Force delete all images
docker rmi -f $(docker images -q)

# Force delete a container with `Container ID`
docker rm -f 940599f61fc1
# Delete an image
docker rmi myservice
```

