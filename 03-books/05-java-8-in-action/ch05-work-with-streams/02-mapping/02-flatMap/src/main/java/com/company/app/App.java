package com.company.app;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
the flatMap method lets you replace each value of a stream with another stream and then concatenates all the generated
streams into a single stream.
 */
public class App
{
    public static void main( String[] args )
    {
        String[] words = {"Hello", "World"};
        List<String> result = Arrays.stream(words)
                .map(word -> word.split(""))
                .flatMap(Arrays::stream) // map each array with the contents of the stream.
                .distinct()
                .collect(Collectors.toList());
        System.out.println(result);
    }
}
/*
output:
[H, e, l, o, W, r, d]
 */