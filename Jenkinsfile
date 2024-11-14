pipeline {
    agent none
    environment {
        AWS_REGION = 'us-east-1'  // AWS region for your public ECR
        ECR_REPO_URI = 'public.ecr.aws/e6x0h8x3/varsha-aws-registry'  // Public ECR repository URI
        IMAGE_TAG = 'latest'
    }

    stages {
        stage('Maven Build') {
            agent {
                docker {
                    image 'maven:3.9.4'
                    args '-u root'  // Ensure root access for installing tools
                }
            }
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Install AWS CLI and Docker') {
            agent {
                docker {
                    image 'ubuntu:20.04'  // Using a base Ubuntu image
                    args '-u root'  // Running with root privileges
                }
            }
            steps {
                sh '''
                apt-get update -y
                apt-get install -y awscli docker.io
                aws --version
                docker --version
                '''
            }
        }

        stage('Docker Build') {
            agent any
            steps {
                sh 'docker build -t ${ECR_REPO_URI}:${IMAGE_TAG} .'
            }
        }

        stage('Docker Login to Public ECR') {
            agent any
            steps {
                script {
                    sh '''
                    aws ecr-public get-login-password --region ${AWS_REGION} | docker login --username AWS --password-stdin ${ECR_REPO_URI}
                    '''
                }
            }
        }

        stage('Docker Push to Public ECR') {
            agent any
            steps {
                sh 'docker push ${ECR_REPO_URI}:${IMAGE_TAG}'
            }
        }
    }
}
