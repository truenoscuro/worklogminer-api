node{
    stage("git dowlowad"){
        git url: "https://github.com/truenoscuro/worklogminer-api.git"
    }
    parallel([
        sonar:{
            def tolSonar = tool 'sonar'
            withSonarQubeEnv('prueba'){
                sh "${tolSonar}/bin/sonar-scanner -Dproject.settings=sonar-project.properties -Dsonar.java.binaries=."
            }
           /*  def qualitygate = waitForQualityGate()
            if (qualitygate.status != "OK") {
                error "Pipeline aborted due to quality gate coverage failure: ${qualitygate.status}"
            } */
        },
        maven:{
            withMaven{
                sh "mvn clean compile"
            }
        },
    ])
    stage('maven buil'){
       withMaven{
                sh "mvn clean install"
            } 
    }
    stage('desploy redwood'){
        echo "esto no se ase"
    }
    stage('docker deploy'){
        def container = docker.build('prova','.').run("-u root -p 8080:8080 -p 8443:8443")
        sleep(1000)
        container.stop()
    }
    
}
