pipeline {
    agent {
        docker 'docker:19.03.12'  // Using a Docker image with Docker installed
    }

    environment {
        DOCKER_IMAGE = "${DOCKERHUB_USERNAME}/spring-boot-app"
    }
    stages {
        stage('Checkout') {
            steps {
                git credentialsId: "${GITHUB_CREDENTIALS_ID}", url: "${GITHUB_REPO_URL}"
            }
        }

        stage('Set Permissions') {
            steps {
                sh 'chmod +x mvnw'  // Ensure executable permissions for mvnw
            }
        }

        stage('Build') {
            steps {
                sh './mvnw clean install'  // Build with Maven
            }
        }

        stage('Docker Build') {
            steps {
                script {
                    sh "docker build -t ${DOCKER_IMAGE} ."  // Use the environment variable
                }
            }
        }

        stage('Docker Push') {
            steps {
                script {
                    // Log in to Docker using the Personal Access Token securely
                    sh '''
                    echo $DOCKERHUB_PAT | docker login --username $DOCKERHUB_USERNAME --password-stdin
                    docker push $DOCKER_IMAGE
                    '''
                }
            }
        }
    }
}
