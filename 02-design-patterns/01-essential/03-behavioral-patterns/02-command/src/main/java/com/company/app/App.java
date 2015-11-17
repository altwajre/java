package com.company.app;

import java.util.ArrayList;
import java.util.List;

public class App
{
    interface ICommand{  // Command interface
        void execute();
        void unExecute();
    }
    static class CalculatorCommand implements ICommand{  // Concrete Command
        private char operator;
        private int operand;
        private Calculator calculator;
        public CalculatorCommand(Calculator calculator, char operator, int operand){
            this.calculator = calculator;
            this.operator = operator;
            this.operand = operand;
        }
        public void execute() {
            calculator.operation(operator, operand);
        }
        public void unExecute() {
            calculator.operation(undo(operator), operand);
        }
        private char undo(char operator){
            switch (operator){
                case '+': return '-';
                case '-': return '+';
                case '*': return '/';
                case '/': return '*';
                default: throw new IllegalArgumentException("operator");
            }
        }
    }
    static class Calculator{  // Receiver
        private int current = 0;
        public void operation(char operator, int operand){
            switch (operator){
                case '+': current += operand; break;
                case '-': current -= operand; break;
                case '*': current *= operand; break;
                case '/': current /= operand; break;
            }
            System.out.format("Current value = %s (following %s %s)\n", current, operator, operand);
        }
    }
    static class User{  // Invoker
        private Calculator calculator = new Calculator();
        private List<ICommand> commands = new ArrayList<ICommand>();
        private int current = 0;
        public void redo(int levels){
            System.out.format("\n---- Redo %s levels \n", levels);
            for(int i = 0; i < levels; i++){
                if(current < commands.size() - 1){
                    commands.get(current++).execute();
                }
            }
        }
        public void undo(int levels){
            System.out.format("\n---- Undo %s levels \n", levels);
            for(int i = 0; i < levels; i++){
                if(current > 0){
                    commands.get(--current).unExecute();
                }
            }
        }
        public void compute(char operator, int operand){
            ICommand command = new CalculatorCommand(calculator, operator, operand);
            command.execute();
            commands.add(command);
            current++;
        }
    }
    public static void main( String[] args )
    {
        User user = new User();
        user.compute('+', 100);
        user.compute('-', 50);
        user.compute('*', 10);
        user.compute('/', 2);
        user.undo(4);
        user.redo(3);
    }
}
