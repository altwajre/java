// https://developers.google.com/maps/documentation/geocoding/intro
String base = 'https://maps.googleapis.com/maps/api/geocode/xml?'
def encoded = ['2 Avenue de Lafayette', 'Boston', 'MA'].collect {
    URLEncoder.encode(it, 'UTF-8')
}.join(',')
println encoded
String qs = "address=$encoded"
// .toURL() will go to the url and get the response
println "$base$qs".toURL().text
// new XmlSlurper().parse() will go to the url, get the response and parse it
def root = new XmlSlurper().parse("$base$qs")
def loc = root.result[0].geometry.location
println loc // 42.3541882-71.0612790
