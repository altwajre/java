package com.company.app;
import java.util.ArrayList;
import java.util.List;

class Context{  // Context
    public Context(String input){ this.Input = input; }
    public String Input;
    public int Output;
}
abstract class Expression{  // Abstract Expression
    public void Interpret(Context context){
        if(context.Input.length() == 0) return;
        if(context.Input.startsWith(nine())){
            context.Output += 9 * multiplier();
            context.Input = context.Input.substring(2);
        }
        else if(context.Input.startsWith(four())){
            context.Output += 4 * multiplier();
            context.Input = context.Input.substring(2);
        }
        else if(context.Input.startsWith(five())){
            context.Output += 5 * multiplier();
            context.Input = context.Input.substring(1);
        }
        while(context.Input.startsWith(one())){
            context.Output += 1 * multiplier();
            context.Input = context.Input.substring(1);
        }
    }
    public abstract String one();
    public abstract String four();
    public abstract String five();
    public abstract String nine();
    public abstract int multiplier();
}
class ThousandExpression extends Expression{  // Terminal Expression
    @Override
    public String one() { return "M"; }
    @Override
    public String four() { return " "; }
    @Override
    public String five() { return " "; }
    @Override
    public String nine() { return " "; }
    @Override
    public int multiplier() { return 1000; }
}
class HundredExpression extends Expression{  // Terminal Expression
    @Override
    public String one() { return "C"; }
    @Override
    public String four() { return "CD"; }
    @Override
    public String five() { return "D"; }
    @Override
    public String nine() { return "CM"; }
    @Override
    public int multiplier() { return 100; }
}
class TenExpression extends Expression{  // Terminal Expression
    @Override
    public String one() { return "X"; }
    @Override
    public String four() { return "XL"; }
    @Override
    public String five() { return "L"; }
    @Override
    public String nine() { return "XC"; }
    @Override
    public int multiplier() { return 10; }
}
class OneExpression extends Expression{  // Terminal Expression
    @Override
    public String one() { return "I"; }
    @Override
    public String four() { return "IV"; }
    @Override
    public String five() { return "V"; }
    @Override
    public String nine() { return "IX"; }
    @Override
    public int multiplier() { return 1; }
}
public class App
{
    public static void main( String[] args )
    {
        List<Expression> tree = new ArrayList<Expression>();
        tree.add(new ThousandExpression());
        tree.add(new HundredExpression());
        tree.add(new TenExpression());
        tree.add(new OneExpression());
        String roman = "MCMXXVIII";
        final Context context = new Context(roman);
        tree.forEach(e -> e.Interpret(context));
        System.out.format("%s = %s\n", roman, context.Output);
    }
}
/*
Definition
Given a language, define a representation for its grammar along with an interpreter that uses the representation to
interpret sentences in the language.

output:
MCMXXVIII = 1928
 */
