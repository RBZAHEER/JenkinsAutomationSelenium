pipeline{
    agent any

    stages{
        stage('Checkout'){
            steps   {
                checkout scm
            }
        }

        stage('Run Testng Suite'){
            steps{
                bat 'mvn clean test -DsuiteXmlFile=testng.xml'
            }
        }
    }
}