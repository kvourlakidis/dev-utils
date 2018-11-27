import groovy.json.*
//
// Utility functions for HTTP requests
//
public class HttpHelpers {
    //
    // Globals
    //
    static class Constants {
        static final String USER = "Jenny"
        static final String PASSWORD = "Jenny"
        static String COOKIE = "" // get from server
        //
        // Content-Type
        //
        static final String CONTENT_TYPE_APPLICATION_JSON = "application/json"
        static final String CONTENT_TYPE_LOGGING = "application/ibmi2logging.v1+json"
        static final String CONTENT_TYPE_SUBMIT  = "application/ibmi2records.v1+json"
        static final String CONTENT_TYPE_ACQUIRE = "application/ibmi2acquire.v1+json"
        //
        // URLs
        //
        static final String LOG_API   = "api/v1/core/log" // POST
        static final String JOBS_API  = "api/v1/jobs" // GET
        static final String USERS_API = "api/v1/users/${USER}" // GET
        static final String SUBMIT    = "api/v1/infostore/records/submit" // POST
        static final String ACQUIRE   = "api/v1/connectors/%s/%s"
        static final String BASE_URL_OPAL  = "http://localhost:9082/opal/"
        static final String BASE_URL_OPALDAOD  = "http://localhost:9082/opaldaod/"
        //
        static final String ENCODING = "UTF-8"
        static final String LANGUAGE = "en-US"
    }

    static URLConnection createPostRequest(String url, String body, String contentType) {
        URLConnection request = new URL(url).openConnection();
        request.setDoOutput(true)
        request.setRequestProperty("Content-Type", contentType)
        request.setRequestProperty("Accept-Language", Constants.LANGUAGE)
        setAuthHeaders(request)
        request.setRequestMethod("POST")
        try {
            request.getOutputStream().write(body.getBytes(Constants.ENCODING));
        } catch (Exception ex) {
            println ex.getMessage()
            println "URL: ${request.getURL()}"
        }
        return request
    }

    static URLConnection createGetRequest(String url) {
        URLConnection request = new URL(url).openConnection();
        request.setRequestProperty("Accept-Language", Constants.LANGUAGE)
        setAuthHeaders(request)
        request.setRequestMethod("GET")
        // connection.setConnectTimeout(10_000)
        return request
    }

    static void setAuthHeaders(request) {
        if (!Constants.COOKIE) {
            def encodedCred = "${Constants.USER}:${Constants.PASSWORD}".bytes.encodeBase64().toString()
            def basicToken = "Basic $encodedCred"
            request.setRequestProperty("Authorization", basicToken)
        } else {
            request.setRequestProperty("Cookie", Constants.COOKIE)
        }
    }

    static void handleResponse(request) {
        // request.connect()
        // println request.dump()
        try {
            def responseCode = request.getResponseCode();
            // println responseCode
            if (responseCode.equals(200)) {
                def response = request.getInputStream().getText();
                def parsed = new JsonSlurper().parseText(response);
                println "SUCCESS: thread \"${Thread.currentThread().getName()}\" successfully called ${request.getURL()}"
                println "Response: ${parsed}"
            } else {
                println "~~~~~~~~~~~~~~~~~"
                println "ERROR: thread \"${Thread.currentThread().getName()}\" while calling ${request.getURL()}"
                println "Response code: ${responseCode}"
                println "~~~~~~~~~~~~~~~~~"
                try {
                    def response = request.getErrorStream().getText();
                    println "Response: ${response}"
                } catch (NullPointerException ex) {
                    // do nothing (no response body)
                }
            }
        } catch (IOException ex) {
            println ex.getMessage();
            println "URL: ${request.getURL()}"
        }
        // set the Cookie
        if (request.getHeaderField("Set-Cookie")) {
            Constants.COOKIE = request.getHeaderField("Set-Cookie")
        }
    }

    static String getMessageBody(body) {
        def json = JsonOutput.toJson(body)
        // println json
        return json
    }
}