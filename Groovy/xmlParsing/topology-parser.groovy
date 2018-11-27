import groovy.util.slurpersupport.GPathResult;

void validateOptions(def serverOption, def warOption, def collectionOption) {
    String topologyXml = "topology.xml"
    GPathResult topology = new XmlSlurper().parse(new File(topologyXml))
    GPathResult apps = topology.applications.application
    GPathResult wars = apps.wars.war
    GPathResult collections = wars.'solr-collection-ids'.'solr-collection-id'

    println topology.getClass()
    println apps.getClass()
    println wars.getClass()
    println collections.getClass()
    
    Closure validate = { GPathResult element, String errorMsg -> 
        if (element.isEmpty()) {
            println errorMsg
            System.exit(0)
        }
    }

    if (serverOption) {
        apps = apps.findAll { it.@name == serverOption }
        wars = apps.wars.war
        collections = wars.'solr-collection-ids'.'solr-collection-id'
        validate(apps, "The specified server $serverOption is not defined in the topology.")
    }
    if (warOption) {
        wars = wars.findAll { it.@name == warOption }
        collections = wars.'solr-collection-ids'.'solr-collection-id'
        if (serverOption) {
            validate(wars, "The specified WAR $warOption was not found in the server $serverOption.")
        } else {
            validate(wars, "The specified war $warOption is not defined in the topology.")
        }
    }
    if (collectionOption) {
        collections = collections.findAll { it.'@collection-id' == collectionOption }
        if (serverOption && warOption) {
            validate(collections, "The specified collection $collectionOption was not found in the server $serverOption.")
        } else if (serverOption) {
            validate(collections, "The specified collection $collectionOption was not found in the server $serverOption.")
        } else if (warOption) {
            validate(collections, "The specified collection $collectionOption was not found in the WAR $warOption.")
        } else {
            validate(collections, "The specified collection $collectionOption is not defined in the topology.")
        }
    }
}

validateOptions("opal-server", "opal-services-is", "daod_index")