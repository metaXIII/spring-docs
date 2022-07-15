pipeline {
    agent any
    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }
    stages {
        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                git url: 'https://github.com/metaXIII/spring-docs.git', credentialsId: '1'
                script {
                if (isUnix()) {
                    sh 'mvn clean install -f "./Hateoas/Building a Hypermedia-Driven RESTful Web Service"'
                    sh 'mvn clean install -f "./Hateoas/Building REST services with Spring"'
                }
                 else {
                    bat 'mvn clean install -f ".\\Hateoas\\Building a Hypermedia-Driven RESTful Web Service"'
                    bat 'mvn clean install -f ".\\Hateoas/Building REST services with Spring"'
                }
            }
        }
            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    junit '**/target/surefire-reports/*.xml'
                    archiveArtifacts '**/target/*.war'
                }
            }
        }
    }
}
