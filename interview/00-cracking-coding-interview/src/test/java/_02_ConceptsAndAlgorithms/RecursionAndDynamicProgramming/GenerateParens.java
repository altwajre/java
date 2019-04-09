package _02_ConceptsAndAlgorithms.RecursionAndDynamicProgramming;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
  Q: print all valid (i.e., properly opened and closed) combinations of n-pairs of parentheses.

 */
public class GenerateParens {

    @Test
    public void Test() {
        testGenerateParensInsert();

        testGenerateParens();
    }

    /*
      A: insert "()" after each '('

      call stack: "" ones are dup, and sets won't take the dups
      4 return "(())()(), ()(())(), ()()(()), | (()())(), ((()))(), "(())(())", | "(())(())", ()(()()), ()((())), |
                (()()()), ((())()), (()(())), | (()(())), ((()())), (((())))
      3 return "()()(), (())(), ()(()), (()()), ((()))" 5
      2 return "()(), (())" 2
      1 return "()" because prev is an empty string
      0 return "" because remaining == 0

     */
    static Set<String> generateParensInsert(int remaining){
        Set<String> set = new HashSet<String>();
        if(remaining == 0){
            set.add("");
        }
        else{
            Set<String> prev = generateParensInsert(remaining - 1);
            for(String str : prev){
                for(int i = 0; i < str.length(); i++){
                    if(str.charAt(i) == '('){
                        String s = insertInside(str, i);
                        /*
                        Add s to set if it is not already in there. Note:
                        HashSet automatically checks for duplicate before adding
                         */
                        set.add(s);
                    }
                }
                set.add("()" + str);
            }
        }
        return set;
    }
    static String insertInside(String str, int leftIndex){
        String left = str.substring(0, leftIndex + 1);
        String right = str.substring(leftIndex + 1, str.length());
        return left + "()" + right;
    }

    static void addParen(ArrayList<String> list, int leftRem, int rightRem, char[] str, int count){
        if(leftRem < 0 || rightRem < leftRem) return;  // invalid state
        if(leftRem == 0 && rightRem == 0){  // all out of left and right parentheses
            String s = String.copyValueOf(str);
            list.add(s);
        }
        else{
            if(leftRem > 0){  // try a left paren, if there are some available
                str[count] = '(';
                addParen(list, leftRem - 1, rightRem, str, count + 1);
            }
            if(rightRem > leftRem){  // try a right paren, if there's a matching left
                str[count] = ')';
                addParen(list, leftRem, rightRem - 1, str, count + 1);
            }
        }
    }
    static ArrayList<String> generateParens(int count){
        char[] str = new char[count*2];
        ArrayList<String> list = new ArrayList<String>();
        addParen(list, count, count, str, 0);
        return list;
    }

    /*
    output:
    ((()))
    (()())
    (())()
    ()(())
    ()()()
    5
     */
    private static void testGenerateParens() {
        List<String> list = generateParens(3);
        for(String s : list){
            System.out.println(s);
        }
        System.out.println(list.size());
    }

    /*
    output:
    ()()()
    ()(())
    (()())
    (())()
    ((()))
    5
     */
    private static void testGenerateParensInsert() {
        Set<String> list = generateParensInsert(3);
        for(String s : list){
            System.out.println(s);
        }
        System.out.println(list.size());
    }
}
/*
output:
()()()
()(())
(()())
(())()
((()))
5
((()))
(()())
(())()
()(())
()()()
5
 */
