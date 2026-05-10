pipeline {

    agent any

    environment {

        BROWSER = "firefox"
        REMOTE = "true"
    }

    stages {

        stage('Checkout') {

            steps {

                checkout scm
            }
        }

        stage('Start Selenium Grid') {

            steps {

                sh 'docker compose up -d --scale firefox=2'
            }
        }

        stage('Run Tests') {

            steps {

                sh '''
                    gradle clean test \
                    -Dbrowser=${BROWSER} \
                    -DisRemote=${REMOTE}
                '''
            }
        }

        stage('Publish Report') {

            steps {

                publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'reports',
                    reportFiles: 'ExtentReport.html',
                    reportName: 'Extent Report'
                ])
            }
        }
    }

    post {

        always {

            sh 'docker compose down'

            archiveArtifacts artifacts: 'screenshots/*.png',
                    fingerprint: true
        }
    }
}