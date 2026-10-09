
pipeline {
    agent any

    parameters {
        choice(
            name: 'ENVIRONMENT',
            choices: ['dev', 'test', 'prod'],
            description: 'Select the build environment'
        )
    }

    stages {
        stage('Show Build Information') {
            steps {
                echo "Job Name: ${env.JOB_NAME}"
                echo "Build Number: ${env.BUILD_NUMBER}"
                echo "Workspace: ${env.WORKSPACE}"
                echo "Selected Environment: ${params.ENVIRONMENT}"
            }
        }

        stage('Compile') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Package') {
            steps {
                bat 'mvn package -DskipTests'
            }
            post {
                success {
                    archiveArtifacts artifacts: 'target/*.jar',
                                     fingerprint: true
                }
            }
        }
    }

    post {
        success {
            echo 'System of Record pipeline completed successfully!'
        }

        failure {
            echo 'System of Record pipeline failed. Check the Console Output.'
        }

        always {
            echo 'Pipeline execution finished.'
        }
    }
}
