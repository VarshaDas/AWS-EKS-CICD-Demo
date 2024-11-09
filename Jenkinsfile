#!groovy
pipeline {
    agent none
   stages {
       stage('Maven Install') {
           agent {
               docker {
                   image 'maven:3.9.4'
               }
           }
           steps {
               sh 'mvn clean install'
           }
       }

       stage('Docker Build') {
           agent any
           steps {
               sh 'docker build -t varsha-springboot-eks:latest .'
           }
       }

       stage('Docker Push') {
           agent any
           steps {
               script {
                   // Log in to Docker using the Personal Access Token securely
                   sh '''
                    echo $DOCKER_TOKEN | docker login --username varshadas23 --password-stdin
                    docker push varsha-springboot-eks:latest
                    '''
               }
           }
       }
   }
 }