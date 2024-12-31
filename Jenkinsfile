pipeline {
    agent any

    tools {
        maven 'sonarmaven' 
    }

    environment {
        SONARQUBE_SERVER = 'sonarqube'
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

        stage('SonarAnalysis') {
            environment {
                SONAR_TOKEN = credentials('sonar-token') 
            }
            steps {
                bat '''
                mvn clean verify sonar:sonar \
                -Dsonar.projectKey=swati-maven-asg2 \
                -Dsonar.projectName='swati-maven-asg2' \
                -Dsonar.host.url=http://localhost:9000 \
                -Dsonar.sources=.
                -Dsonar.token=sqp_5c07786769ea680df828c240117fbb0472ce12c3
                
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
