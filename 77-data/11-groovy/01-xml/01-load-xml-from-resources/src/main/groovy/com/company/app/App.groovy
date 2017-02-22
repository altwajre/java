package com.company.app

class App {
    static void main(String... args) {
        loadFromResources()

        parseString()

        parseLargeXmlString()

    }

    private static void parseLargeXmlString() {
        println "#parseLargeXmlString"
        def input = '''
<soap:Envelope id="111" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema">
  <soap:Body id="222">
    <GetAccountXmlResponse id="#333" xmlns="#Bonsai">
      <ResultCode>0</ResultCode>
      <AccountXml>
        <Bonsai>
          <Bonsai ResourceID="db443212-f883-11e6-adb7-005056953ce3">
            <Tree TreeID="1861">
              <Transition TreeID="1852" />
              <Transition TreeID="1870" />
              <Transition TreeID="3211" />
            </Tree>
          </Bonsai>
        </Bonsai>
      </AccountXml>
    </GetAccountXmlResponse>
  </soap:Body>
</soap:Envelope>'''

        def xmlDoc = new XmlSlurper().parseText(input)
        println xmlDoc.Body.@id
        println xmlDoc.Body.GetAccountXmlResponse.@id
        println xmlDoc.Body.GetAccountXmlResponse.AccountXml.Bonsai.Bonsai.@ResourceID
        println xmlDoc.Body.GetAccountXmlResponse.AccountXml.Bonsai.Bonsai.Tree.Transition.size()
        println xmlDoc.Body.GetAccountXmlResponse.AccountXml.Bonsai.Bonsai.Tree.Transition[0].@TreeID
    }

    private static void parseString() {
        println '#parseString'
        def input = '''
        <staff ID="123">
            <department name="sales">
                <employee>
                    <firstName>Orlando</firstName>
                    <lastName>Boren</lastName>
                    <age>24</age>
                </employee>
                <employee>
                    <firstName>Diana</firstName>
                    <lastName>Colgan</lastName>
                    <age>28</age>
                </employee>
            </department>
        </staff>'''

        def xmlDoc = new XmlSlurper().parseText(input)

        println xmlDoc.@ID // attribute
        println xmlDoc.department.size() // Size
        println xmlDoc.department.@name // attribute
        println xmlDoc.department.employee[0] // child
        println xmlDoc.department.employee[0].firstName // value
    }

    private static void loadFromResources() {
        println '#loadFromResources'
        def xmlDoc = new XmlSlurper().parse('src/main/resources/xml/employee-data.xml')  // Creating the XmlSlurper object
        println xmlDoc.@ID // attribute
        println xmlDoc.department.size() // Size
        println xmlDoc.department.@name // attribute
        println xmlDoc.department.employee[0] // child
        println xmlDoc.department.employee[0].firstName // value
    }
}
/*
output:
#loadFromResources
123
1
sales
OrlandoBoren24
Orlando
#parseString
123
1
sales
OrlandoBoren24
Orlando
#parseLargeXmlString
222
#333
db443212-f883-11e6-adb7-005056953ce3
6
1852
 */
