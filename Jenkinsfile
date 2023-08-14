pipeline {
    agent any

    environment {
        DOCKER_IMAGE_NAME = "rodr1gocosta/ordem-servico:tagname"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Criar imagem Docker') {
            steps {
                script {
                    sh 'docker build -t rodr1gocosta/ordem-servico:v1 .'
                }
            }
        }
        stage('Enviar imagem Docker Hub') {
            steps {
                script {
                    withDockerRegistry(credentialsId: '30e1a593-c8d9-4b87-8061-1bac3fd438bc', url: 'https://hub.docker.com/u/rodr1gocosta') {
                        sh 'docker push rodr1gocosta/ordem-servico:v1'
                    }
                    
                }
            }
        }
    }
    post {
        always {
            echo 'Pipeline completed!'
        }
    }
}
