// GitHub webhook test



pipeline {
    agent any

    stages {

        stage('Compile') {
            steps {
                bat '''
                    mvn clean compile
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

        stage('Package') {
            steps {
                bat '''
                    mvn package -DskipTests
                '''
            }
        }


        stage('Show Build Information') {
            steps {
                 echo "Job Name: ${env.JOB_NAME}"
                 echo "Build Number: ${env.BUILD_NUMBER}"
                 echo "Workspace: ${env.WORKSPACE}"
            }
        }


    }
}