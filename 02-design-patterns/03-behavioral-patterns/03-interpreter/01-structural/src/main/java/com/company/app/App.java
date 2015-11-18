package com.company.app;

import java.util.ArrayList;

class Context{
}
abstract class AbstractExpression{
    public abstract void Interpret(Context context);
}
class TerminalExpression extends AbstractExpression{
    @Override
    public void Interpret(Context context) {
        System.out.println("Called Terminal.Interpret()");
    }
}
class NonterminalExpression extends AbstractExpression{
    @Override
    public void Interpret(Context context) {
        System.out.println("Called Nonterminal.Interpret()");
    }
}
public class App
{
    public static void main( String[] args )
    {
        Context context = new Context();
        // Populate 'abstract syntax tree'
        ArrayList<AbstractExpression> list = new ArrayList<AbstractExpression>();
        list.add(new TerminalExpression());
        list.add(new NonterminalExpression());
        list.add(new TerminalExpression());
        list.add(new TerminalExpression());
        for(AbstractExpression exp : list){
            exp.Interpret(context);
        }
    }
}
/*
Definition
Given a language, define a representation for its grammar along with an interpreter that uses the representation to
interpret sentences in the language.

output:
Called Terminal.Interpret()
Called Nonterminal.Interpret()
Called Terminal.Interpret()
Called Terminal.Interpret()
 */
