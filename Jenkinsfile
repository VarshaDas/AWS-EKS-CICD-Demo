pipeline {
    agent any
    environment {
        DOCKER_IMAGE = "${DOCKERHUB_USERNAME}/spring-boot-app"
    }
    stages {
        stage('Checkout') {
            steps {
                git credentialsId: "${GITHUB_CREDENTIALS_ID}", url: "${GITHUB_REPO_URL}"
            }
        }
        stage('Build') {
            steps {
                sh './mvnw clean install'
            }
        }
        stage('Docker Build') {
            steps {
                script {
                    sh 'docker build -t $DOCKER_IMAGE .'
                }
            }
        }
        stage('Docker Push') {
            steps {
                script {
                    sh '''
                    echo $DOCKERHUB_PAT | docker login --username $DOCKERHUB_USERNAME --password-stdin
                    docker push $DOCKER_IMAGE
                    '''
                }
            }
        }
    }
}
