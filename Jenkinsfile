pipeline {
    agent any
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
                    sh 'docker build -t rodr1gocosta/ordem-servico:tagname .'
                }
            }
        }
        stage('Enviar imagem Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', '30e1a593-c8d9-4b87-8061-1bac3fd438bc') {
                        sh 'docker push docker rodr1gocosta/ordem-servico:tagname'
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
