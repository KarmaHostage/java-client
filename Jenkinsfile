pipeline {
    agent any

    environment {
        CI = 'true'
    }
    stages {
        stage('Build') {
            steps {
                sh './ci/build.sh'
            }
        }
        stage('Deploy') {
              when { tag "release-*" }
              steps {
                  echo 'Deploying only because this commit is tagged...'
                  sh './ci/deploy.sh'
           }
        }
    }
}
