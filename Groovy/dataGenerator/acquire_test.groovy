import java.text.NumberFormat
import HttpHelpers
import static HttpHelpers.Constants as Constants
//
// main
//
println "start"
// submitPeople(1, 1)
// 50.times { submitAcquireSeeds((it + 1) * 1000) }
1000.times { submitAcquireSeeds(500) }
println "end"

//
// end of main
//

def submitAcquireSeeds(int nRecords) {
    println '------------------------------------------------'
    println "creating seed payload with ${nRecords} records"
    def startTime = System.currentTimeMillis()
    def jsonPayload = HttpHelpers.getMessageBody(getAcquireSeedsBodyMap(nRecords))
    def url = Constants.BASE_URL_OPALDAOD + String.format(Constants.ACQUIRE, "fake-https-connector-1", "t1")
    def acquireRequest = HttpHelpers.createPostRequest(
        url,
        jsonPayload,
        Constants.CONTENT_TYPE_ACQUIRE)
    println "time taken to create request: ${ NumberFormat.getNumberInstance(Locale.US).format(System.currentTimeMillis() - startTime) } ms"
    println "sending request to: ${url}"
    startTime = System.currentTimeMillis()
    HttpHelpers.handleResponse(acquireRequest)
    println "time taken to process request: ${ NumberFormat.getNumberInstance(Locale.US).format(System.currentTimeMillis() - startTime) } ms"
    println '------------------------------------------------'
}
def getAcquireSeedsBodyMap(int numEntities) {
    def conditions = [
        id: 'id1',
        logicalType: 'SINGLE_LINE_STRING',
        value: '123'
    ]
    def seeds = [
        entities: [],
        links: []
    ]
    numEntities.times {
        seeds.entities += getSeedEntity()
    }
    def payload = [
        conditions: conditions,
        seeds: seeds
    ]
    return [ payload: payload ]
}
def getSeedEntity() {
    def uniqueID = UUID.randomUUID().toString()
    return [
        itemTypeId: 'ET5',
        label: 'John Smith',
        originIds: [
            [   
                originIdType: 'OI.DAOD',
                originIdKeys: [ 'example-connector', 'ET5', uniqueID ]
            ]
        ],
        properties: [
            PER4: "John",
            PER6: "Smith",
            PER9: "1971-12-31",
            PER12: 45,
            PER18: "10.5",
            PER71: "1.8"
        ],
        accesDimensionValues: [
            [ dimensionId: "SD-SL", ids: [ 'UC' ] ],
            [ dimensionId: "SD-SC", ids: [ 'OSI', 'HI' ] ]
        ]
    ]
}