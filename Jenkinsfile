def project1 = 'mvn clean install -f "./Hateoas/Building a Hypermedia-Driven RESTful Web Service"'
def project2 = 'mvn clean install -f "./Hateoas/Building REST services with Spring"'
pipeline {
    agent any
    tools {
        maven "maven"
    }
    stages {
        stage("checkout") {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                script {
                    if (isUnix()) {
                        sh "${project1}"
                        sh "${project2}"
                    } else {
                        bat "${project1}"
                        bat "${project2}"
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
