
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
                bat '"C:/Users/jeetu/AppData/Local/Programs/DockerDesktop/resources/bin/docker.exe" version'
            }
        }

        stage('Docker Image Build') {
            steps {
                bat '"C:/Users/jeetu/AppData/Local/Programs/DockerDesktop/resources/bin/docker.exe" build -t system-of-record:1.0 -t system-of-record:%BUILD_NUMBER% .'
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

                        "C:/Users/jeetu/AppData/Local/Programs/DockerDesktop/resources/bin/docker.exe" tag system-of-record:1.0 jeetendrajs/system-of-record:1.0
                        if errorlevel 1 exit /b 1

                        "C:/Users/jeetu/AppData/Local/Programs/DockerDesktop/resources/bin/docker.exe" tag system-of-record:%BUILD_NUMBER% jeetendrajs/system-of-record:%BUILD_NUMBER%
                        if errorlevel 1 exit /b 1

                        "C:/Users/jeetu/AppData/Local/Programs/DockerDesktop/resources/bin/docker.exe" push jeetendrajs/system-of-record:1.0
                        if errorlevel 1 exit /b 1

                        "C:/Users/jeetu/AppData/Local/Programs/DockerDesktop/resources/bin/docker.exe" push jeetendrajs/system-of-record:%BUILD_NUMBER%
                        if errorlevel 1 exit /b 1

                        "C:/Users/jeetu/AppData/Local/Programs/DockerDesktop/resources/bin/docker.exe" logout
                    '''
                }
            }
        }

        stage('Deploy to Docker') {
            steps {
                bat '''
                    @echo off
                    set "DOCKER=C:/Users/jeetu/AppData/Local/Programs/DockerDesktop/resources/bin/docker.exe"

                    "%DOCKER%" pull jeetendrajs/system-of-record:%BUILD_NUMBER%
                    if errorlevel 1 exit /b 1

                    "%DOCKER%" rm -f sor-app-dockerhub-v3 >nul 2>&1

                    "%DOCKER%" run -d --name sor-app-dockerhub-v3 -p 8082:8081 -e JWT_SECRET=VGhpc0lzQVNlY3JldEtleUZvckRldk9ubHlOb3RGb3JQcm9kdWN0aW9uMTIzNDU2Nzg5MA== -e "SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3306/sor_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true" jeetendrajs/system-of-record:%BUILD_NUMBER%
                    if errorlevel 1 exit /b 1

                    echo Deployment command completed.
                '''
            }
        }
    }

    post {
        success {
            echo 'System of Record pipeline and deployment completed successfully!'
        }

        failure {
            echo 'Pipeline or deployment failed. Check the Console Output.'
        }

        always {
            echo 'Pipeline execution finished.'
        }
    }
}
