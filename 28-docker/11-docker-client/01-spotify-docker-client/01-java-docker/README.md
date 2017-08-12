# java docker

> Dockerfile

`test/resources/dockerDirectory/Dockerfile`

> create docker image

```
public class DockerTest {
  @Before
  public void setUp() throws Exception {
    final DockerClient docker = DefaultDockerClient.fromEnv().build();

    final String dockerDirectory = Resources.getResource("dockerDirectory").getPath();
    docker.build(Paths.get(dockerDirectory), "local-customer");
  }
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
