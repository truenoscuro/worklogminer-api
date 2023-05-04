pipeline {
    agent any
    stages {
        stage("Compile & Sonar Analysis") {
          parallel {
            stage("Maven compile") {
              agent any
              steps {
                withMaven(
                  jdk: 'openjdk-17.0.2'
                ) {
                  bat "mvn clean compile"
                }
              }
            }
            stage("SonarQube Analysis") {
              agent any
              steps {
                withSonarQubeEnv('SonarQube - 3digits') {
                  bat 'C:\\sonarqube-9.5.0.56709\\bin\\sonar-scanner-4.7.0.2747-windows\\bin\\sonar-scanner.bat -Dproject.settings=sonar-project.properties -Dsonar.java.binaries=.'
                }
                script {
                  def qualitygate = waitForQualityGate()
                  if (qualitygate.status != "OK") {
                    error "Pipeline aborted due to quality gate coverage failure: ${qualitygate.status}"
                  }
                }
              }
            }
          }
          
        }
        stage("Maven build") {
            steps {
                withMaven(
                    jdk: 'openjdk-17.0.2'
                ) {
                    bat "mvn clean install"
                }
            }
        }
        stage("Deploy in REDWOOD") {
            when {
                branch 'develop';
            }
            steps {
                bat 'C:\\DESARROLLO\\SERVIDORES\\springboot\\worklogminer-api\\worklogminer-api.exe stop'
                bat 'rm C:\\DESARROLLO\\SERVIDORES\\springboot\\worklogminer-api\\worklogminer-api.jar'
                bat 'cp target/worklogminer-api.jar C:\\DESARROLLO\\SERVIDORES\\springboot\\worklogminer-api\\worklogminer-api.jar'
                bat 'C:\\DESARROLLO\\SERVIDORES\\springboot\\worklogminer-api\\worklogminer-api.exe start'
            }
        }
    }
}
