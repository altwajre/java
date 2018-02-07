package com.company.app;

import nu.xom.Document;
import nu.xom.Element;

/*
Appending children

<?xml version="1.0"?>
<ListBox><Item>1</Item><Item>2</Item><Item>3</Item></ListBox>
 */
public class App
{
    public static void main( String[] args )
    {
        Element root = new Element("ListBox");

        for(int i = 1; i <= 3; i++){
            Element item = new Element("Item");
            item.appendChild(Integer.toString(i));
            root.appendChild(item);
        }

        Document document = new Document(root);
        System.out.println(document.toXML());
    }
}

/*
output:
<?xml version="1.0"?>
<ListBox><Item>1</Item><Item>2</Item><Item>3</Item></ListBox>
 */
