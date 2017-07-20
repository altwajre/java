package com.company.app;

import nu.xom.Document;
import nu.xom.Element;

/*
create xml document below

<?xml version="1.0"?>
<root>Hello World!</root>
 */
public class App
{
    public static void main( String[] args )
    {
        // Create an element object
        Element root = new Element("root");
        root.appendChild("Hello World!");

        // Create a document object
        Document doc = new Document(root);
        String result = doc.toXML();

        System.out.println(result);
    }
}
/*
output:
<?xml version="1.0"?>
<root>Hello World!</root>
 */
