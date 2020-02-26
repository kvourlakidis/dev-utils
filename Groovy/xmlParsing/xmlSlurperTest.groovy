import groovy.util.slurpersupport.GPathResult
import groovy.xml.XmlUtil
import groovy.xml.StreamingMarkupBuilder

String xmlFile = "law-enforcement-schema.xml"

GPathResult rootElement = new XmlSlurper().parse(new File(xmlFile))

println rootElement.getClass()
// println rootElement.attributes()
// println rootElement.namespaceURI()
// println rootElement.parent() == rootElement

Closure opalChartEntityType = {
    EntityType(Description: "Opal Chart", DisplayName: "Opal Chart", Icon:"Anbchart", Id: "CHART") {
        PropertyGroupTypes()
        PropertyTypes() {
            PropertyType( Description: "The file name of the chart", SemanticTypeId: "", Position: 0, DisplayName: "Name", LogicalType: "SINGLE_LINE_STRING", MaximumLengthChars: 255, Id: "CHART1", Mandatory: true) {
                PossibleValues()
            }
        }
    }
}

println opalChartEntityType.getClass()

def opalChartLabelDefinition = {
    LabelDefinition(ItemTypeId:"CHART") {
        LabelParts() {
            LabelPart(Value:"CHART1", LabelPartType:"PROPERTY_TYPE")
        }
    }
}


def entityTypesRoot = rootElement.ItemTypes.EntityTypes
def chartType = entityTypesRoot.EntityType.find { it.@'Id' == "CHART" }
if (chartType.isEmpty()) {
    entityTypesRoot.appendNode(opalChartEntityType)
}

rootElement.LabelingSchemes.LabelingScheme.forEach { labelingScheme -> 
    def labelDefinitions = labelingScheme.LabelDefinitions
    def chartLabel = labelDefinitions.LabelDefinition.find { it.@ItemTypeId == "CHART" }
    if (chartLabel.isEmpty()) {
        labelDefinitions.appendNode(opalChartLabelDefinition)
    }
}

def version = rootElement.Version.text().toLong()
rootElement.Version = version + 1

writeXMLFile("law-enforcemenet-schema-copy.xml", rootElement, "UTF-8")

public static def writeXMLFile(def file, def rootNode, String encoding = "UTF-8") {
    file = makeFile(file)
    file.withWriter(encoding) { out ->
        XmlUtil.serialize(new StreamingMarkupBuilder().bind{ mkp.yield rootNode }, out)
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