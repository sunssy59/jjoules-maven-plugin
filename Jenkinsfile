node{
    stage('SCM Checkout'){
        git 'https://github.com/Mamadou59/jjoules-maven-plugin'
    }
    stage('Compile-Install'){
        sh 'mvn clean install'
    }
}
