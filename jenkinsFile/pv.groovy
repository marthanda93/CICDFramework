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
        deployment = "dev"
    }
    stages {
        stage('Parallel Stage') {
            when {
                branch 'master'
            }
            failFast true
            parallel {
                stage('Branch A') {
                    agent {
                        label "for-branch-a"
                    }
                    steps {
                        echo "On Branch A"
                    }
                }
                stage('Branch B') {
                    agent {
                        label "for-branch-b"
                    }
                    steps {
                        echo "On Branch B"
                    }
                }
                stage('Branch C') {
                    agent {
                        label "for-branch-c"
                    }
                    stages {
                        stage('Nested 1') {
                            steps {
                                echo "In stage Nested 1 within Branch C"
                            }
                        }
                        stage('Nested 2') {
                            steps {
                                echo "In stage Nested 2 within Branch C"
                            }
                        }
                    }
                }
            }
        }


        stage('Shell build') {
            steps {
                ex_git(
                    url: "https://github.com/marthanda93/spring-petclinic.git",
                    branch: "master",
                    credentialsId: ""
                )
            }
        }

    }
}