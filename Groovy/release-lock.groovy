Map<String, String> envVars = [:]
envVars.putAll(System.getenv())

final jarFileName = 'jenkins-build-jars-KeepBuilds-1.0.0.0.jar'
assert jarFileName, 'DSL provided an empty jar file name.'
final toolsHomeDir = System.getenv('Jenkins_Build_Scripts_HOME')
assert toolsHomeDir, 'Env var "Jenkins_Build_Scripts_HOME" is not set.'
final jarFileFullPath = "${toolsHomeDir}/JARs/${jarFileName}"

// Check that the jar exists.
final jarFile = new File(jarFileFullPath)
assert jarFile.exists(), "File ${jarFile} was not found."
println "Preparing to call ${jarFile}"

final jarClassName = 'com.ibm.i2group.common.Main'
println "Using provided class name ${jarClassName}."
this.class.classLoader.rootLoader.addURL(jarFile.toURL())
final clz = Class.forName(jarClassName)
assert clz, "Unable to obtain a reference to class ${jarClassName}"

// Declare a list of the command-line arguments that we start with.
final List<String> rawArguments = [
    '--file',
    './bld/usagereport.xml',
    '--keepJob',
    '${keepJob}',
    '--description',
    '${description}',
    '--usernameENV',
    'COMPONENTS_PRINCIPAL',
    '--passwordENV',
    'COMPONENTS_PASSWORD',
]

// Resolve environment variables within arguments.
final List<String> resolvedArgList = []
rawArguments.each { rawArg ->
    final resolvedArg = new StringBuilder(rawArg)
    envVars.each { envName, envValue ->
        final searchPattern = '${' + envName.toLowerCase() + '}'
        int index
        while( (index = resolvedArg.toString().toLowerCase().indexOf(searchPattern)) >= 0 ) {
            resolvedArg.replace(index, index+searchPattern.size(), envValue)
        }
    }
    resolvedArgList.add(resolvedArg.toString())
}
final String[] resolvedArgs = resolvedArgList as String[]

import java.util.concurrent.Callable
import java.lang.reflect.Modifier

if (Callable.isAssignableFrom(clz)) {
    println "Creating new ${clz.simpleName}(${resolvedArgs})"
    final Callable<Integer> instanceToBeCalled = clz.newInstance(resolvedArgs)
    println "Invoking call() method..."
    final int result = instanceToBeCalled.call().intValue()
    if (result != 0) {
        throw new Exception("call() returned failure code ${result}")
    }
}
else {
    println "${clz.simpleName} does not implement Callable<Integer>" +
            " - looking for static void main(String[]) method."
    final mainMethod = clz.getMethod('main', [resolvedArgs.class] as Class<?>[])
    if (mainMethod && Modifier.isStatic(mainMethod.modifiers)) {
        println "Invoking ${clz.simpleName}.main(${resolvedArgs})"
        clz.newInstance().main(resolvedArgs)
    }
    else {
        throw new IllegalStateException("JAR file ${jarFile}" +
                " class ${clz.simpleName} cannot be executed." +
                System.getProperty('line.separator') +
                "It must EITHER implement Callable<Integer>" +
                " and have a constructor that takes String[]," +
                " OR it must have a static main(String[]) method.")
    }
}
