pipeline{
    environment {
        registry = "agusw/walletforex"
        registryCredential = 'docker_agus'
        dockerImage = ''
    }
    agent any
    stages {
        stage('Deploy and Run App') {
            steps{
                sshagent(credentials : ['543699f1-e549-4e48-b3db-7ab4456ee495']) {
                    sh 'ssh -o StrictHostKeyChecking=no root@104.248.147.193 "docker stop walletforex || true  && docker container rm walletforex || true"'
                    sh 'ssh -o StrictHostKeyChecking=no root@104.248.147.193 "docker pull $registry || true"'
                    sh 'ssh -o StrictHostKeyChecking=no root@104.248.147.193 "mkdir -p /apps/wallet-forex"'
                    sh 'scp -o StrictHostKeyChecking=no app.env root@104.248.147.193:/apps/wallet-forex/app.env'
                    sh 'ssh -o StrictHostKeyChecking=no root@104.248.147.193 "cd /apps/wallet-forex && docker run -p 9191:9191 -d --env-file=app.env --name=walletforex $registry"'
                }
            }
        }
    }
}