@Library('paac@may19') _

String appRepo = "https://github.com/spring-projects/spring-petclinic.git"
String appBranch = "master"
String appCred = ""

pipeline {
    agent {
        label 'SlaveA'
    }
    
    environment { 
        deploymentStage = "dev"
    }
    stages {
        stage('Clone') {
            steps {
                gitCli(
                    url: appRepo,
                    branch: appBranch,
                    credentialsId: appCred
                )
                script {
                    globalPipelineSettingTemp.appRepo.url = appRepo
                }
            }
        }
        stage('build') {
            steps {
                codeBaseBuildCli 'mvn'
            }
        }

    }
}