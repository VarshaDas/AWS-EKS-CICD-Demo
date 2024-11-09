# Spring Boot Application CI/CD Pipeline with Jenkins

This project demonstrates a **CI/CD pipeline** for a Spring Boot application using Jenkins. The pipeline automates the steps to build, package, create a Docker image, and push it to a Docker registry.

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
