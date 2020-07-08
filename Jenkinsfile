node{
    stage('SCM Checkout'){
        git 'https://github.com/Mamadou59/jjoules-maven-plugin'
    }
    stage('Compile-Install'){
        def mvnHome = tool name: 'maven-3', type: 'maven'
        sh "${mvnHome}/bin/mvn clean install"
    }
}
