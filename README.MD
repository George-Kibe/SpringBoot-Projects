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
- Run a docker container from image. i for interactive mode. d for detach mode which allows running other commands in terminal. p allows port mapping.```docker run -it -d -p <host-port>:<container-port> <container-name>```
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
  
## Working with docker, Java and Postgresql
A summary of commands to use in setting up docker running java spring boot application to run with postgres database
- Installing Postgres Image and setting password to use. -e allows to set environmental variables. ```docker run -d --name db -e POSTGRES_PASSWORD=password postgres```
- Adding PgAdmin ```docker run -d --name pgadmin -e PGADMIN_DEFAULT_EMAIL=admin@admin.com -e PGADMIN_DEFAULT_PASSWORD=password dpage/pgadmin4```
- Exec allows to execute commands in a docker container. it allows use of an interactive console.
  Ping is used to check reachability/connectivity of a network. ```docker exec -it pgadmin ping db```
- ```docker rm -f db pgadmin```
Using Networks
- Create a network ```docker network create my-network```
- Rerunning the postgres docker image associating it to created network.
  ```docker run -d --name db --network my-network -e POSTGRES_PASSWORD=password postgres```
- Rerunning the pgadmin docker image associating it to created network.
  ```docker run -d --name pgadmin --network my-network -e PGADMIN_DEFAULT_EMAIL=admin@admin.com -e PGADMIN_DEFAULT_PASSWORD=password dpage/pgadmin4```
- Rerun the ping command to check connectivity. ```docker exec -it pgadmin ping db```
  
## RabbitMQ configurations- Running from docker
```
  rabbitmq:
    image: rabbitmq:3-management
    container_name: rabbitmq
    ports: 
      - 5672:5672
      - 15672:15672
    networks:
      - postgres
```
### Running from the terminal
```
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```

## Packaging commands
1. To package using maven ```./mvnw package``` or ```./mvnw clean package```
2. To verify jar file ```jar -tf  target/companyMs-0.0.1-SNAPSHOT.jar```
3. To run the jar file ```java -jar companyMs-0.0.1-SNAPSHOT.jar```
   
## Building individual images for all microservices
Navigate to each of the folders and run 
```./mvnw spring-boot:build-image "-Dspring-boot.build-image.imageName=georgekibe/servicereg" ```
Docker push to your account
Format is docker push georgekibe/<imageName>
```docker push georgekibe/jobapplicationwebsiteimage```

## Working with kubernetes and minikube
launch minikube dashboard
```minikube dashboard```
Creating a pod
```kubectl apply -f first-pod.yml```
Viewing all pods
```kubectl get pods```
View Everything
```kubectl get all```
Creating a service
```kubectl apply -f first-service.yml```
Viewing all services
```kubectl get service``` or ```kubectl get services```

Deleting all deployments
```kubectl delete deployments --all --all-namespaces```

Delete all the services
```kubectl delete services --all --all-namespaces```

Delete all Pods
```kubectl delete pods --all --all-namespaces```

Delete all replicasets
```kubectl delete replicasets --all --all-namespaces```

Using Postgres on Kubernetes
```kubectl exec -it postgres-0 -- psql -U george```