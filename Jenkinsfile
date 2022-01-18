import groovy.transform.Field

@Field static commandOptions = [
        "pet" : ["--add", "--upload-image"],
        "all"  : ["--run"],
]

static String getOption() {
    return [
            $class              : 'CascadeChoiceParameter',
            choiceType          : 'PT_CHECKBOX',
            name                : 'Option',
            referencedParameters: 'Command',
            script              : [
                    $class        : 'GroovyScript',
                    fallbackScript: [classpath: [], sandbox: false, script: ''],
                    script        : [classpath: [], sandbox: false, script: getOptionScript()],
            ]
    ]
}

static def getOptionScript() {
    def list = ["switch (Command) {"]
    for (Set<Map.Entry<String, List<String>>> entry : commandOptions.entrySet()) {
        list.add("case '${entry.key}' : return ['${entry.value.join("','")}']")
    }
    list.add("}")
    list.join("\n")
}


node {

    properties([
            disableConcurrentBuilds(),
            buildDiscarder(logRotator(numToKeepStr: '5')),
            parameters([
                    booleanParam(name: 'EnableANSIColor', defaultValue: true),
                    choice(name: 'Environment', choices: ['PROD', 'TEST', 'PROD']),
                    choice(name: 'Command', choices: commandOptions.keySet().toList()),
                    getOption(),
            ])
    ])


    ansiColor('xterm') {
        withEnv(["JAVA_HOME=${tool 'JDK17'}", "M2_HOME=${tool 'mvn_3.8.4'}",
                 "PATH=${tool 'mvn_3.8.4'}/bin:${tool 'JDK17'}/bin:${env.PATH}"]) {

            stage("Clone") {
                git "https://github.com/KonstantinKshnyakin/educational-api-testapp.git"
            }

            stage("Build") {
                sh "mvn clean package"
            }

            stage("Test") {
                def launch = "java -jar ./target/Launcher.jar -eac ${params.EnableANSIColor} -e ${params.Environment} ${params.Command} ${params.Option.replace(",", " ")}"
                echo launch
                sh launch
            }
        }
    }
}
