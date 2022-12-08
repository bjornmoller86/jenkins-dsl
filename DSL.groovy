import groovy.json.JsonSlurper

def reposJSON = new JsonSlurper().parseText(readFileFromWorkspace('repos.json'))

reposJSON.repos.each {
    createPipeline(it)
}

void createPipeline(it) {

    def jobname = it.jobname
    def gitrepo = it.gitrepo
    def desc = it.description
    pipelineJob(jobname) {
        description(desc)
         parameters {
             choiceParam('mina val', ['val 1 (default)', 'val igen 2', 'denharda 3'], 'my description')
        }
        definition {
            cpsScm {
                scm {
                    git {
                        remote {
                            url(gitrepo)
                        }
                    }
                }
            }
        }
    }
}
