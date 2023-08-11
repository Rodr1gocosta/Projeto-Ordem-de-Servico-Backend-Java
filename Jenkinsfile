pipeline {
    agent any

    environment {
        DOCKER_IMAGE_NAME = "rodr1gocosta/ordem-servico"
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
                    docker.build(DOCKER_IMAGE_NAME, '.')
                }
            }
        }
        stage('Enviar imagem para Docker Hub') {
            steps {
                script {
                    dockerImage.push()
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
