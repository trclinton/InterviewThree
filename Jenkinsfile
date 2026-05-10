pipeline {

    agent any

    parameters {

        choice(
            name: 'BROWSER',
            choices: ['chrome', 'firefox'],
            description: 'Browser'
        )

        booleanParam(
            name: 'REMOTE',
            defaultValue: true,
            description: 'Remote Execution'
        )
    }

    environment {

        PATH = "/opt/homebrew/bin:/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin"
    }

    stages {

        stage('Checkout') {

            steps {

                checkout scm
            }
        }

        stage('Verify Docker') {

            steps {

                sh 'which docker'
                sh 'docker --version'
                sh 'docker ps'
            }
        }

        stage('Start Selenium Grid') {

            steps {

                sh '/usr/local/bin/docker compose up -d --scale firefox=2'
            }
        }

        stage('Wait For Grid') {

            steps {

                sh 'sleep 15'
            }
        }

        stage('Run Tests') {

            steps {

                sh """
                    gradle clean test \
                    -Dbrowser=${params.BROWSER} \
                    -DisRemote=${params.REMOTE}
                """
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

            sh '/usr/local/bin/docker compose down'

            archiveArtifacts(
                    artifacts: 'screenshots/*.png',
                    fingerprint: true
            )
        }
    }
}