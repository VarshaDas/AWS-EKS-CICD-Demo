#!groovy
pipeline {

    agent none
    environment {
        AWS_REGION = 'us-east-1'  // Replace with the region of your public ECR
        ECR_REPO_URI = 'public.ecr.aws/e6x0h8x3/varsha-aws-registry'  // Public ECR repository URI
        IMAGE_TAG = 'latest'
    }

    stages {
        stage('Maven Install') {
            agent {
                docker {
                    image 'maven:3.9.4'
                    args '-u root'  // Ensure you have root access to install aws-cli
                }
            }
            steps {
//                sh 'apt-get update && apt-get install -y awscli'  // Install aws-cli
                sh 'mvn clean install'
            }
        }


//       stage('Docker Build') {
//           agent any
//           steps {
//               sh 'docker build -t varshadas23/varsha-springboot-eks:latest .'
//           }
//       }
//
//       stage('Docker Push') {
//           agent any
//           steps {
//               script {
//                   // Log in to Docker using the Personal Access Token securely
//                   sh '''
//                    echo $DOCKER_TOKEN | docker login --username varshadas23 --password-stdin
//                    docker push varshadas23/varsha-springboot-eks:latest
//                    '''
//               }
//           }
//       }

       stage('Docker Build') {
           agent any
           steps {
               sh 'docker build -t ${ECR_REPO_URI}:${IMAGE_TAG} .'
           }
       }

       stage('Docker Login to Public ECR') {
           agent {
               docker {
                   image 'amazon/aws-cli'
               }
           }
           steps {
               script {
                   // Log in to public AWS ECR
                   sh '''
                    aws ecr-public get-login-password --region ${AWS_REGION} | docker login --username AWS --password-stdin ${ECR_REPO_URI}
                    '''
               }
           }
       }

       stage('Docker Push to Public ECR') {
           agent any
           steps {
               script {
                   // Push the Docker image to public AWS ECR
                   sh 'docker push ${ECR_REPO_URI}:${IMAGE_TAG}'
               }
           }
       }
   }
 }