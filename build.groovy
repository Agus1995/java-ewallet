pipeline{
  environment {
    registry = "agusw/walletforex"
    registryCredential = 'docker_agus'
    dockerImage = ''
  }
  agent any
  stages {
    stage('Checkout Source') {
      steps{
        script {
          checkout scm: [$class: 'GitSCM', userRemoteConfigs: [[credentialsId: 'kumo-gitlab-cred',url: env.gitlabSourceRepoHttpUrl]], branches: [[name: env.gitlabSourceBranch]]], poll: false
        }
      }
    }
    stage('Build artifact') {
      steps{
        sh "mvn clean install"
      	sh "mvn sonar:sonar -Dsonar.projectKey=wallet-forex -Dsonar.host.url=http://159.89.227.122:9000"
      }
    }
    stage('Build Image') {
      steps{
        script {
          dockerImage = docker.build registry + ":$BUILD_NUMBER"
        }
      }
    }
    stage('Tag & Push Image') {
      steps{
        script {
          docker.withRegistry( '', registryCredential ) {
            dockerImage.push()
            dockerImage.push('latest')
          }
        }
      }
    }
    stage('Remove Unused Docker Image') {
      steps{
        sh "docker rmi $registry:$BUILD_NUMBER"
      }
    }
    stage('Deploy and Run App') {
      steps{
        sshagent(credentials : ['543699f1-e549-4e48-b3db-7ab4456ee495']) {
            sh 'ssh -o StrictHostKeyChecking=no root@104.248.147.193 "docker stop walletforex || true  && docker container rm walletforex || true"'
            sh 'ssh -o StrictHostKeyChecking=no root@104.248.147.193 "docker pull $registry || true"'
            sh 'ssh -o StrictHostKeyChecking=no root@104.248.147.193 "mkdir -p /apps/wallet-forex"'
            sh 'scp -o StrictHostKeyChecking=no app.env root@104.248.147.193:/apps/wallet-forex/app.env'
            sh 'ssh -o StrictHostKeyChecking=no root@104.248.147.193 "cd /apps/wallet-api && docker run -p 9191:9191 -d --env-file=app.env --name=walletforex $registry"'
        }
      }
    }
  }
}