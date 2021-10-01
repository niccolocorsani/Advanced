pipeline {
environment {
    registry = "niccocorsa/it-corsani"
}
  agent {
     docker{
        image "maven:3.6.3-jdk-11"
         }
    }
   stages {
    /*  stage('Build') {
		  steps {
			script {
				def appVersion = readMavenPom().getVersion()
                    currentBuild.displayName = "${appVersion} #${env.BUILD_NUMBER}"
                    //currentBuild.description = "The best description."
					}
			sh 'mvn clean install -Pprod -DskipTests'
			echo 'ooooooooooo'
			//archiveArtifacts 'operations-backend/target/operations-backend.jar'
		  }
	  }*/
	  /*stage('Quality Gate') {
        sleep 30
        def qualitygate = waitForQualityGate()
        if (qualitygate.status != "OK") {
            error "Pipeline aborted due to quality gate failure: ${qualitygate.status}"
        }
	}*/
      stage('Image') {
         steps {
            script {
                image = docker.build registry + ":1"

        		docker.withRegistry('', 'credentialjenkins') { ///credential jenkins è la credenziale inserita su Manage Jenkins -- Credential
					image.push("latest")
        		}
            }
         }
      }


   }
   post {
      failure {
      			echo 'failure'

      }
      fixed {
      			echo 'fixed'

      }
   }
}
