# Spring Boot Application CI/CD Pipeline with Jenkins

This project demonstrates a **CI/CD pipeline** for a Spring Boot application using Jenkins. The pipeline automates the steps to build, package, create a Docker image, and push it to a Docker registry.


docker run -d -p 8080:8080 -p 50000:50000 --name jenkins --network jenkins -v jenkins_home:/var/jenkins_home -v /var/run/docker.sock:/var/run/docker.sock jenkins/jenkins


reakdown:
docker run: This command is used to create and run a new container from a specified image.

-d: Runs the container in detached mode (in the background), so you can continue using the terminal for other tasks.

-p 8080:8080: Maps port 8080 on the host machine to port 8080 in the container. This is the port Jenkins uses to serve its web interface. You can access Jenkins by going to http://localhost:8080 on your browser.

-p 50000:50000: Maps port 50000 on the host to port 50000 in the container. This is used for communication between Jenkins master and its agents (e.g., if you're using a Jenkins agent for building jobs).

--name jenkins: Assigns a name (jenkins) to the running container. This makes it easier to reference the container later.

--network jenkins: Specifies that the container should be connected to a network called jenkins. You can create a custom network if you need to manage multiple containers in the same network.

-v jenkins_home:/var/jenkins_home: Mounts a Docker volume (jenkins_home) to persist Jenkins data (like jobs, configurations, and build histories) at /var/jenkins_home in the container. This ensures your Jenkins data isn't lost if the container is stopped or removed.

-v /var/run/docker.sock:/var/run/docker.sock: This is a critical part. It mounts the Docker socket from the host (/var/run/docker.sock) into the container. This allows the Jenkins container to communicate with the Docker engine on the host, enabling Jenkins to run Docker commands (e.g., building Docker images) directly from inside the Jenkins container.

jenkins/jenkins: Specifies the image to use to create the container. This uses the official Jenkins image from Docker Hub (jenkins/jenkins).

In Summary:
This command runs Jenkins in a Docker container with the following key points:

It maps ports 8080 (web interface) and 50000 (agent communication) to the host.
It ensures Jenkins data is stored persistently by mounting a volume.
It gives Jenkins the ability to run Docker commands by mounting the Docker socket.
It connects Jenkins to a custom Docker network (jenkins).
This setup is typically used in a CI/CD pipeline where Jenkins itself needs to be able to run Docker commands inside the container (such as building Docker images).







---

## Features
1. **GitHub Integration**:
   - Code is fetched from a GitHub repository whenever changes are pushed.

2. **Build Automation**:
   - Builds the Spring Boot application using Maven (`mvnw clean install`).

3. **Docker Integration**:
   - Builds a Docker image for the application.
   - Pushes the image to Docker Hub (or any other container registry if configured).

4. **Environment Configuration**:
   - Jenkins fetches sensitive environment variables (e.g., credentials, repository URLs) securely.

---

## Prerequisites
1. **Jenkins Setup**:
   - Jenkins server with required plugins installed:
     - Git
     - Docker
     - Pipeline
     - Credentials Binding
   - Docker installed on the Jenkins agent.

2. **GitHub Repository**:
   - The repository should contain:
     - A `Jenkinsfile` in the root directory.
     - A `Dockerfile` in the root directory.
     - Application code (e.g., Spring Boot source files, `pom.xml`, `mvnw`).

3. **Docker Hub**:
   - A valid Docker Hub account or any container registry.

4. **Environment Variables in Jenkins**:
   Set the following variables in Jenkins as either global environment variables or credentials:
   - `GITHUB_CREDENTIALS_ID`: Credential ID for GitHub access.
   - `GITHUB_REPO_URL`: URL of your GitHub repository.
   - `DOCKERHUB_USERNAME`: Your Docker Hub username.
   - `DOCKERHUB_PAT`: Docker Hub Personal Access Token.

---

## Jenkinsfile
The pipeline is defined in the `Jenkinsfile`. It consists of the following stages:

1. **Checkout**:
   - Pulls the code from the GitHub repository.

2. **Build**:
   - Runs `mvnw clean install` to build the Spring Boot application.

3. **Docker Build**:
   - Creates a Docker image using the `Dockerfile`.

4. **Docker Push**:
   - Logs in to Docker Hub using the PAT and pushes the Docker image.

---

## Dockerfile
The `Dockerfile` is used to containerize the Spring Boot application. Here’s an example:

```dockerfile
# Base image
FROM openjdk:17-jdk-slim

# Copy jar file into the container
COPY target/spring-boot-app.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]
