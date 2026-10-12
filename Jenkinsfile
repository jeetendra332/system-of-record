
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

        stage('Docker Check') {
            steps {
                bat '"C:\\Users\\jeetu\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin\\docker.exe" version'
            }
        }

        stage('Docker Image Build') {
            steps {
                bat '"C:\\Users\\jeetu\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin\\docker.exe" build -t system-of-record:1.0 .'
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-credentials',
                    usernameVariable: 'DOCKERHUB_USERNAME',
                    passwordVariable: 'DOCKERHUB_PASSWORD'
                )]) {
                    bat '''
                        @echo off
                        powershell -NoProfile -ExecutionPolicy Bypass -Command "$env:DOCKERHUB_PASSWORD | & 'C:/Users/jeetu/AppData/Local/Programs/DockerDesktop/resources/bin/docker.exe' login -u $env:DOCKERHUB_USERNAME --password-stdin"
                        if errorlevel 1 exit /b 1

                        "C:\\Users\\jeetu\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin\\docker.exe" tag system-of-record:1.0 jeetendrajs/system-of-record:1.0
                        if errorlevel 1 exit /b 1

                        "C:\\Users\\jeetu\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin\\docker.exe" push jeetendrajs/system-of-record:1.0
                        if errorlevel 1 exit /b 1

                        "C:\\Users\\jeetu\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin\\docker.exe" logout
                    '''
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
