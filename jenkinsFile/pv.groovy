@Library('paac@may19') _

String appRepo = "https://github.com/spring-projects/spring-petclinic.git"
String appBranch = "master"
String appCred = ""

String opsRepo = "git@github.com:marthanda93/ops.git"
String opsBranch = "master"
String opsCred = "GithubSSh"

pipeline {
    agent {
        label 'SlaveA'
    }
    
    environment { 
        deploymentStage = "dev"
    }
    stages {
        stage('Parallel Stage') {
            when {
                expression {  globalPipelineSetting.deploymentStages.findResults{ k, v -> env.deploymentStage in v ? k : null } == ['development'] }
            }
            failFast true
            parallel {
                stage('AppRepo') {
                    steps {
                        gitCli(
                            url: appRepo,
                            branch: appBranch,
                            credentialsId: appCred
                        )
                    }
                }
                stage('AppRepo') {
                    steps {
                        gitCli(globalPipelineSetting.opsRepo, 'parent')
                    }
                }
            }
        }
        stage('After clone') {
            steps {
                println "------"
            }
        }

    }
}