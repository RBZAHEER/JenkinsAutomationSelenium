pipeline{
    agent any

    stages{
        stage('Checkout'){
            steps   {
                checkout scm
            }
        }

        stage('Run Docker Grid'){
            steps{
                bat 'docker compose up -d'
            }
        }
        stage('Check Docker Containers') {

                    steps {

                        bat 'docker ps'

                    }
        }
        stage('Run Testng Suite'){
            steps{
                bat 'mvn clean test -DsuiteXmlFile=testng.xml'
            }
        }
    }

    post{

        success{
            echo 'Automation test passed'
        }
        failure{
            echo 'Automation test failed'
        }

       always{
            archiveArtifacts artifacts: 'reports/**, screenshots/**',
                             allowEmptyArchive: true
            echo 'Pipeline Execution Completed'

       }
    }
}