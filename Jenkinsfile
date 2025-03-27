pipeline {
    agent any

    tools {
        maven 'Maven-3.9.9'  // Chọn Maven version đã cài trên Jenkins
    }

    stages {
        stage('Checkout Code') {
            steps {
                git 'https://github.com/tranquangthuan1211/test_jenkins.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn -Dtest=com.example.demo.* test'
            }
        }

        stage('Upload Test Results') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }

        stage('Run Code Coverage') {
            steps {
                sh 'mvn verify'  // Chạy JaCoCo
            }
        }

        stage('Upload Coverage Report') {
            steps {
                jacoco execPattern: '**/target/jacoco.exec'
            }
        }
    }
}
