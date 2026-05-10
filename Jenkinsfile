pipeline {

    agent any

    parameters {

        choice(
            name: 'browser',
            choices: ['chrome', 'firefox'],
            description: 'Browser'
        )

        booleanParam(
            name: 'isRemote',
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

                sh '/usr/local/bin/docker compose up -d --scale firefox=2 --scale chrome=2'
            }
        }

        stage('Wait For Grid') {

            steps {

                sh 'sleep 10'
            }
        }

        stage('Run Tests') {

            steps {

                sh """
                    gradle clean test \
                    -Dbrowser=${params.browser} \
                    -DisRemote=${params.isRemote}
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
                fingerprint: true,
                allowEmptyArchive: true
            )
        }
    }
}