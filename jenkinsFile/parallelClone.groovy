@Library('paac@may19') _

// String appRepo = "https://github.com/spring-projects/spring-petclinic.git"
String appRepo = "https://github.com/django/django.git"
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
                stage('OpsRepo') {
                    steps {
                        script {
                            globalPipelineSetting.opsRepo.path = gitCli(globalPipelineSetting.opsRepo, 'parent')
                        }
                    }
                }
            }
        }
        stage('Yaml Generator') {
            steps {
                kubectl(
                    'create',
                    'nspace', 
                    [deploymentStage:env.deploymentStage, application:'application', kubeKind:'namespace', opsRepoPath:globalPipelineSetting.opsRepo.path]
                )
            }
        }

    }
}