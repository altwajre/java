package com.company.app;

import java.util.EnumSet;
import java.util.Set;

enum Style{
    BOLD, ITALIC, UNDERLINE, STRIKETHROUGH
}
class Text{
    // Any Set could be passed in, but EnumSet is clearly best
    public void applyStyles(Set<Style> styles){
        System.out.println(styles);
    }
}
public class App
{
    public static void main( String[] args )
    {
        Text text = new Text();
        text.applyStyles(EnumSet.of(Style.BOLD, Style.ITALIC));
    }
}
/*
output:
[BOLD, ITALIC]
 */