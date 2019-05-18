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
            }
        }
        stage('build') {
            steps {
                codeBaseBuildCli 'mvn'
            }
        }

    }
}