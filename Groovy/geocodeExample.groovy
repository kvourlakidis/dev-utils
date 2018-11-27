String baseUrl = 'https://maps.googleapis.com/maps/api/geocode/xml?'
def encoded = ['2 Avenue de Lafayette', 'Boston', 'MA']
    .collect { URLEncoder.encode(it, 'UTF-8')}
    .join(',')
String qs = "address=$encoded"
println "$baseUrl$qs"
String key = 'AIzaSyDHvD5NslD978dyN263Ijlefl4RdLWE9mY'

def root = new XmlSlurper().parse("$baseUrl$qs&key=$key")

def loc = root.result[0].geometry.location

println "$loc.lat $loc.lng"