import groovy.json.*

LINE_FEED = "\r\n";
BOUNDARY = "===" + System.currentTimeMillis() + "===";

String getMessageBody(Map body) {
    def json = JsonOutput.toJson(body)
    // println json
    return json
}

Map getChartRequestBody() {
    Map payload = [
        properties: ["CHART1": "my anbp chart"],
        accessDimensionValues: [
            "SD-SL": "CON",
            "SD-SC": "OSI"
        ]
    ]
    return payload
}

URLConnection submitPostRequest() {
    def baseUrl = 'http://localhost:9082/opal/'
    def endpointUrl = 'api/v1/infostore/charts/$generateChartId';
    def url = baseUrl + endpointUrl;
    URLConnection connection = new URL(url).openConnection();
    connection.setDoOutput(true)
    connection.setDoInput(true)

    // creates a unique boundary based on time stamp
    connection.setRequestProperty("Content-Type","multipart/form-data; boundary=" + BOUNDARY);
    connection.setRequestProperty("Accept-Language", "en-US")

    // auth header
    def encodedCred = "Jenny:Jenny".bytes.encodeBase64().toString()
    def basicToken = "Basic $encodedCred"
    connection.setRequestProperty("Authorization", basicToken)

    connection.setRequestMethod("POST")

    def body = getMessageBody(getChartRequestBody())
    try {
        OutputStream out = request.getOutputStream();
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"));
        addFormField(writer, "chartDetail", body);
    } catch (Exception ex) {
        println ex.getMessage()
        println "URL: ${connection.getURL()}"
    }
    return connection
}

void addFormField(Writer writer, String name, String value) {
    writer.append("--" + BOUNDARY).append(LINE_FEED);
    writer.append("Content-Disposition: form-data; name=\"" + name + "\"")
            .append(LINE_FEED);
    writer.append("Content-Type: text/plain; charset=" + "UTF-8").append(LINE_FEED);
    writer.append(LINE_FEED);
    writer.append(value).append(LINE_FEED);
    writer.flush();
}



void handleResponse(URLConnection connection) {
    // connection.connect()
    println connection.dump()
    println connection.getURL()
    try {
        def responseCode = connection.getResponseCode();
        println responseCode
        if (responseCode.equals(200)) {
            def response = connection.getInputStream().getText();
            println "Content-Type: ${connection.getContentType()}"
            if (connection.getContentType() == 'text/html') {
                println response
            } else {
                def parsed = new JsonSlurper().parseText(response);
                println "SUCCESS: thread \"${Thread.currentThread().getName()}\" successfully called ${connection.getURL()}"
                println "Response: ${parsed}"
            }
        } else {
            println "~~~~~~~~~~~~~~~~~"
            println "ERROR: thread \"${Thread.currentThread().getName()}\" while calling ${connection.getURL()}"
            println "Response code: ${responseCode}"
            def error
            try {
                error = connection.getErrorStream().getText();

            } catch (NullPointerException ex) {}
            println "Error: ${error}"
            println "~~~~~~~~~~~~~~~~~"
        }
    } catch (IOException ex) {
        println ex.getMessage();
        println ex.printStackTrace();
        println "URL: ${connection.getURL()}"
    }
}

def request = submitPostRequest()
handleResponse(request)