import groovy.util.slurpersupport.GPathResult
import groovy.xml.XmlUtil
import groovy.xml.StreamingMarkupBuilder

String topologyXml = "topology.xml"
GPathResult topology = new XmlSlurper().parse(new File(topologyXml))

def opalApplications = topology.applications.application.find { it.@name == "opal-server" }
println opalApplications.getClass()
println opalApplications.size()
println opalApplications.@name

boolean isDiscoOrCombinedWar(GPathResult war) {
    return (war.@target == "opal-services-is") || (war.@target == "opal-services-is-daod")
}