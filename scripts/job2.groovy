node
{
    docker.withRegistry('https://dtrlb-oe3ko7eumonjw.eastus.cloudapp.azure.com/','dtr-login'){
       // dtr-login is a login ID in credentials 
        stage "syncing files"
        git 'https://github.com/sysgain/example-voting-app.git'

        stage "Building and pushing Vote Image"
        def vote_img = docker.build('ddcadmin/voting-app-vote','./vote').push('latest')

        stage "Building and pushing Worker Image"
        def worker_img = docker.build('ddcadmin/voting-app-worker','./worker').push('latest')

        stage "Building and pushing Result Image"
        def result_img = docker.build('ddcadmin/voting-app-result','./result').push('latest')
    }
}