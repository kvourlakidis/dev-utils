import java.io.BufferedReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import groovy.json.*

public class MultipartUtility {
    private final String boundary;
    private static final String LINE_FEED = "\r\n";
    private URLConnection httpConn;
    private String charset;
    private OutputStream outputStream;
    private PrintWriter writer;
 
    public MultipartUtility(String requestURL, String charset)
            throws IOException {
        this.charset = charset;
         
        // creates a unique boundary based on time stamp
        boundary = System.currentTimeMillis();
         
        URL url = new URL(requestURL);
        httpConn = url.openConnection();
        httpConn.setDoOutput(true);
        httpConn.setRequestMethod("POST")

        def encodedCred = "Jenny:Jenny".bytes.encodeBase64().toString()
        def basicToken = "Basic $encodedCred"
        httpConn.setRequestProperty("Authorization", basicToken)

        httpConn.setRequestProperty("Content-Type",
                "multipart/form-data; boundary=" + boundary);
        outputStream = httpConn.getOutputStream();
        writer = new PrintWriter(new OutputStreamWriter(outputStream, charset),
                true);
    }
 
    public void addFormField(String name, String value) {
        writer.append("--" + boundary).append(LINE_FEED);
        writer.append("Content-Disposition: form-data; name=\"" + name + "\"")
                .append(LINE_FEED);
        writer.append("Content-Type: text/plain; charset=" + charset).append(
                LINE_FEED);
        writer.append(LINE_FEED);
        writer.append(value).append(LINE_FEED);
        writer.flush();
    }
 
    public void addFilePart(String fieldName, File uploadFile)
            throws IOException {
        String fileName = uploadFile.getName();
        writer.append("--" + boundary).append(LINE_FEED);
        writer.append(
                "Content-Disposition: form-data; name=\"" + fieldName
                        + "\"; filename=\"" + fileName + "\"")
                .append(LINE_FEED);
        writer.append(
                "Content-Type: "
                        + URLConnection.guessContentTypeFromName(fileName))
                .append(LINE_FEED);
        writer.append("Content-Transfer-Encoding: binary").append(LINE_FEED);
        writer.append(LINE_FEED);
        writer.flush();
 
        FileInputStream inputStream = new FileInputStream(uploadFile);
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        outputStream.flush();
        inputStream.close();
         
        writer.append(LINE_FEED);
        writer.flush();    
    }
 
    public void addHeaderField(String name, String value) {
        writer.append(name + ": " + value).append(LINE_FEED);
        writer.flush();
    }
     
    public List<String> finish() throws IOException {
        List<String> response = new ArrayList<String>();
 
        writer.append(LINE_FEED).flush();
        writer.append("--" + boundary + "--").append(LINE_FEED);
        writer.close();
 
        println "URL: ${httpConn.getURL()}"
        int status = httpConn.getResponseCode();
        println "Response code: ${status}"
        if (status == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    httpConn.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                response.add(line);
            }
            reader.close();
            httpConn.disconnect();
        } else {
            throw new IOException("Server returned non-OK status: " + status);
        }
 
        return response;
    }
}

String getMessageBody(Map body) {
    def json = JsonOutput.toJson(body)
    // println json
    return json
}

Map getChartRequestBody(String name) {
    Map payload = [
        properties: ["CHART1": name],
        accessDimensionValues: [
            "SD-SL": "CON",
            "SD-SC": "OSI"
        ]
    ]
    return payload
}

void submitChart(String chartName, String imageFile, String binaryFile, String textFile) {
    println "Submitting chart with name: ${chartName}"
    println "Chart image: ${imageFile}"
    println "Binary file: ${binaryFile}"
    println "Extracted text: ${textFile}"

    def baseUrl = 'http://localhost:9082/opal/'
    def endpointUrl = 'api/v1/infostore/charts/$generateChartId'
    def url = baseUrl + endpointUrl
    def charset = "UTF-8"
    try {
        MultipartUtility multipart = new MultipartUtility(url, charset)

        multipart.addFormField("chartDetail", getMessageBody(getChartRequestBody(chartName)));
        multipart.addFilePart("image", new File(imageFile));
        multipart.addFilePart("binary", new File(binaryFile));
        multipart.addFilePart("extractedText", new File(textFile));

        List<String> response = multipart.finish();

        println "Server replied:"
        for (String line : response) {
            println line
        }
    } catch(IOException ex) {
        System.err.println(ex);
    }
}

submitChart(
    "test chart 1", 
    '/Users/kyrilosvourlakidis/Downloads/chart downloads/chart1.png',
    '/Users/kyrilosvourlakidis/Downloads/chart downloads/chart1.anb',
    '/Users/kyrilosvourlakidis/Downloads/chart downloads/chart1.txt'
)

submitChart(
    "test chart 2", 
    '/Users/kyrilosvourlakidis/Downloads/chart downloads/chart2.png',
    '/Users/kyrilosvourlakidis/Downloads/chart downloads/chart2.anb',
    '/Users/kyrilosvourlakidis/Downloads/chart downloads/chart2.txt'
)

submitChart(
    "test chart 3", 
    '/Users/kyrilosvourlakidis/Downloads/chart downloads/chart3.png',
    '/Users/kyrilosvourlakidis/Downloads/chart downloads/chart3.anb',
    '/Users/kyrilosvourlakidis/Downloads/chart downloads/chart3.txt'
)

submitChart(
    "test chart 4", 
    '/Users/kyrilosvourlakidis/Downloads/chart downloads/chart4.png',
    '/Users/kyrilosvourlakidis/Downloads/chart downloads/chart4.anb',
    '/Users/kyrilosvourlakidis/Downloads/chart downloads/chart4.txt'
)

