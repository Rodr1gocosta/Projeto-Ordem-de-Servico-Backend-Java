pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                echo 'Clonar o repositório Git'
                git 'https://github.com/Rodr1gocosta/Projeto-Ordem-de-Servico-Backend-Java.git'
            }
        }
        stage('Test') {
            steps {
                 echo 'Iniciando Teste'
             }
        }
    }
}