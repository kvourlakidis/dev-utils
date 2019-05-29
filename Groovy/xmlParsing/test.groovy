import groovy.util.slurpersupport.GPathResult
import groovy.xml.XmlUtil
import groovy.xml.StreamingMarkupBuilder

void doStuff() {
    String topologyXml = "topology.xml"
    String chartStoreId = "chart-store"
    String chartStoreType = "chart-store"
    String chartStoreLocation = "/Users/kyrilosvourlakidis/i2Analyze/data/chart/main"

    GPathResult topology = new XmlSlurper().parse(new File(topologyXml))

    // there should be a helper method to do this
    def opalApplications = topology.applications.application.findAll { it.@name == "opal-server" }
    boolean overWriteTopology = false;

    opalApplications.forEach { application ->

        // there should be a helper method to do this
        def discoWars = application.wars.war.findAll { isDiscoOrCombinedWar(it) }
        discoWars.forEach { war ->
            def fileStoreIds = war.'file-store-ids'.'file-store-id'
            def chartStoreIdRequired = fileStoreIds.find { it.@value == chartStoreId }.isEmpty()
            if (chartStoreIdRequired) {
                war.'file-store-ids'.appendNode {
                    'file-store-id'('value': chartStoreId)
                }
                overWriteTopology = true
            }
        }
        if (!discoWars.isEmpty()) {
            def fileStores = application.'file-stores'.'file-store'
            def chartStoreRequired = fileStores.find { it.@id == chartStoreId }.isEmpty()
            if (chartStoreRequired) {
                application.'file-stores'.appendNode {
                    'file-store'('location': chartStoreLocation, 'id': chartStoreId, 'type': chartStoreType)
                }
                overWriteTopology = true
            }
        }
    }
    if (overWriteTopology) {
        println "Added chart file store to topology."
        writeXMLFile(topologyXml, topology)
    }
}

public static File makeFile(def file) {
    file = asFile(file)
    def parentFile = file.getParentFile()
    if (parentFile) {
        parentFile.mkdirs()
    }
    return file
}

public static File asFile(def file) {
    if (!(file instanceof File)) file = new File(file)
    return file
}

def writeXMLFile(def file, def rootNode, String encoding = "UTF-8") {
    file = makeFile(file)
    file.withWriter(encoding) { out ->
        XmlUtil.serialize(new StreamingMarkupBuilder().bind { mkp.yield rootNode }, out)
    }
}

boolean isDiscoOrCombinedWar(GPathResult war) {
    return (war.@target == "opal-services-is") || (war.@target == "opal-services-is-daod")
}

doStuff()

