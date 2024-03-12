### Images: 
Docker images are template that define the container and its dependencies
### Containers
Containers are runtime environments created from docker images
### Docker Engine
The docker engine is the runtime that runs and manages containers
### Dockerfile
A dockerfile is a file that contains instructions to build a docker image

### Command to create a docker image
```./mvnw spring-boot:build-image "-Dspring-boot.build-image.imageName=georgekibe/jobapplicationwebsiteimage"```

### Push image to docker hub
```docker push georgekibe/jobapplicationwebsiteimage```

## Useful docker commands
- Pull Image ```docker pull <image>```
- Push Image ```docker push <username/image>```
- Run a docker container from image. i for interactive mode. d for detach mode which allows running other commands in terminal. p allows port mapping.```docker run -it -d -p host-port>:<container>```
- Stop container ```docker stop <container>```
- Start a container ```docker start <container> ```
- Delete a stopped container ```docker rm <container-id/container-name>```
- Remove an image from localstoarge ```docker rmi <image-id/image-name>```
- Get all running containers ```docker ps ```
- Get all conatiners ```docker ps a```
- List all images on host ```docker images```
- Access a running container ```docker exec -t <container-name/container-id> bash```
- Build a docker imager ```docker build -t <username/image>```
- Get logs of a runing container ```docker logs <container-name/container-id>```
- Get information of a particular container ```docker inspect <container-name/container-id>```