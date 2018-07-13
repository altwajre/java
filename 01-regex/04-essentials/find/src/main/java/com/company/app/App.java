package com.company.app;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
  public static void main(String[] args) {
    findHtmlAttributeValue();

  }

  private static void findHtmlAttributeValue() {
    String target = "order_id=";
//        String target = "Shopper_ID=";

    String regex = String.format("\\s+%s\"([^\"]+)", target);
//        String regex = "\\s+order_id='([^']+)";
    String input = "<html><body><title order_id=\"cool\"></body></html>";
//        String input = "<html><body><return>&lt; Shopper_ID=\"1366898\"/&gt;</return></body></html>";
//        String regex = "\\Gdog";
//        String input = "dogdog";

    Pattern pattern = Pattern.compile(regex);

    Matcher matcher = pattern.matcher(input);

    String targetValue = null;
    boolean found = false;
    while (matcher.find()) {
      System.out.format("I found the text $%s$ starting at index %d and ending at index %d.%n",
          matcher.group(), matcher.start(), matcher.end());
      targetValue = matcher.group(1);
      found = true;
    }

    if (!found) {
      System.out.format("No match found.%n");
    } else {
      System.out.println("#order_id=" + targetValue);
    }
  }
}
/*
I found the text $ order_id="cool$ starting at index 18 and ending at index 33.
#order_id=cool
 */
