pipeline {
    agent any

    tools {
        maven 'sonarmaven'
    }

    environment {
        SONARQUBE_SERVER = 'sonarqube'
        SONAR_TOKEN = credentials('sonar-token')  // Make sure this corresponds to your SonarQube token stored in Jenkins credentials
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat '''
                mvn clean install
                '''
            }
        }

        stage('Test') {
            steps {
                bat '''
                mvn test
                '''
            }
        }

        stage('SonarAnalysis') {
            steps {
                bat '''
                mvn clean verify sonar:sonar \
                -Dsonar.projectKey=swati-maven-asg2 \
                -Dsonar.projectName="swati-maven-asg2" \
                -Dsonar.host.url=http://localhost:9000 \
                -Dsonar.sources=. \
                -Dsonar.test.inclusions=src/test/java/**/*.java \
                -Dsonar.exclusions=**/src/main/java/**/* \
                -Dsonar.token=%SONAR_TOKEN%  // Use the Sonar token securely stored in Jenkins
                '''
            }
        }
    }

    post {
        success {
            echo "Successfully executed"
        }
        failure {
            echo "Did not work"
        }
    }
}
