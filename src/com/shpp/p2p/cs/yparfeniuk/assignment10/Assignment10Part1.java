/*
 * This program is designed to solve equations
 * This program also works with only one formula without other arguments
 * Also this program has a basic check of the entered arguments
 * */
package com.shpp.p2p.cs.yparfeniuk.assignment10;

import java.util.ArrayList;
import java.util.HashMap;

/* Warning!!!
 * this program only basically checks the entered arguments
 * and warns about elementary errors. In this version don't use '(' ')'
 * To check the work, enter the correct data in the format
 * -a + b ^ 3 - 1,9 / 6.9
 * a = 5
 * b = -7
 */
public class Assignment10Part1 {

    private static final ArrayList<Character> mathOperations = new ArrayList<>(); // all supported actions

    /**
     * Values and operations in formula
     */
    private static final ArrayList<Character> formulaOperations = new ArrayList<>(); // all operations in the formula
    private static final ArrayList<Double> formulaValues = new ArrayList<>(); // all values in the formula

    public static void main(String[] args) {
        addSupportedActions(); // create ArrayList with all supported actions
        if (args.length >= 1 && checkArguments(args)) {
            String formula = args[0]; // first arguments is formula
            HashMap<String, Double> variables = new HashMap<>(); // other arguments is variables

            for (int i = 1; i < args.length; i++) { // convert argument to HashMap
                String[] dataInArgument = args[i].split("=", 2);
                variables.put(dataInArgument[0], Double.parseDouble(dataInArgument[1]));
            }
            System.out.println(calculate(formula, variables)); // print answer
        } else {
            System.out.println("Please enter correct arguments");
        }
    }

    /**
     * This method add all supported actions to ArrayList in order of priority
     */
    private static void addSupportedActions() {
        mathOperations.add('^');
        mathOperations.add('/');
        mathOperations.add('*');
        mathOperations.add('-');
        mathOperations.add('+');
    }

    /**
     * This method checks all arguments and optimizes them
     *
     * @param args an array of arguments
     * @return True if all arguments have passed the basic test
     */
    private static boolean checkArguments(String[] args) {
        for (int i = 0; i < args.length; i++) {
            args[i] = args[i].replaceAll(" ", "").replaceAll(",", "."); // optimize argument
            for (int j = 0, argLength = args[i].length(); j < argLength; j++) {
                char element = args[i].charAt(j);
                if (checkData(element, args[i], i, j, argLength)) {
                    System.out.println(element);
                    System.out.println("Argument " + (i + 1) + " (first argument is 1) is not correct!!!");
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * This method checks the data in the argument
     *
     * @param element   element in the argument
     * @param arg       argument
     * @param i         argument number
     * @param j         position in the argument
     * @param argLength argument length
     * @return True if the argument not passed the base check
     */
    private static boolean checkData(char element, String arg, int i, int j, int argLength) {
        return (!(mathOperations.contains(element) || (element == '.' && j != argLength - 1 &&
                Character.isDigit(arg.charAt(j - 1)) && Character.isDigit(arg.charAt(j + 1))) ||
                (element == '=' && i != 0 && j == 1 && j != argLength - 1 && Character.isAlphabetic(arg.charAt(0)) &&
                        (Character.isDigit(arg.charAt(j + 1)) || arg.charAt(j + 1) == '-' || arg.charAt(j + 1) == '+'))
                || Character.isDigit(element) || Character.isAlphabetic(element)));
    }

    /**
     * This method performs alternately operations in the formula
     * and returns the result of operations
     *
     * @param formula   equation
     * @param variables HashMap with variables
     * @return result
     */
    private static double calculate(String formula, HashMap<String, Double> variables) {
        formulaData(formula, variables);
        while (formulaValues.size() != 1) {
            for (char symbol : mathOperations) {
                if (formulaOperations.contains(symbol)) {
                    int position = formulaOperations.indexOf(symbol);
                    performAnAction(symbol, position, formulaValues.get(position), formulaValues.get(position + 1));
                    formulaOperations.remove(position);
                    formulaValues.remove(position + 1);
                    break;
                }
            }
        }
        return formulaValues.get(0); // result
    }

    /**
     * This method performs operations on numbers a and b
     *
     * @param symbol   mathematical operation
     * @param position position first number in formula
     * @param a        first number in formula (previous mathematical operation)
     * @param b        next number in formula (after mathematical operation)
     */
    private static void performAnAction(char symbol, int position, double a, double b) {
        switch (symbol) {
            case '^':
                formulaValues.set(position, Math.pow(a, b));
                break;
            case '+':
                formulaValues.set(position, a + b);
                break;
            case '-':
                formulaValues.set(position, a - b);
                break;
            case '*':
                formulaValues.set(position, a * b);
                break;
            case '/':
                if (b != 0) {
                    formulaValues.set(position, a / b);
                    break;
                }
                System.out.println("Division by zero!!!");
                System.exit(0); // stop program
        }
    }

    /**
     * This method converts the formula to data and operators
     *
     * @param formula   equation
     * @param variables HashMap with variables
     */
    private static void formulaData(String formula, HashMap<String, Double> variables) {
        String value = "";
        for (int i = 0, formulaLength = formula.length(); i < formulaLength; i++) {
            char formulaElement = formula.charAt(i); // the tested element of the formula
            if (Character.isAlphabetic(formulaElement) && checkFormulaValues(formulaElement, variables)) {
                formulaValues.add(isNegative(formula, i) ? -variables.get(Character.toString(formulaElement))
                        : variables.get(Character.toString(formulaElement)));
            } else if (Character.isDigit(formulaElement) || (formulaElement == '.' && value.length() != 0)) {
                value = isNegative(formula, i) ? "" + formula.charAt(i - 1) + formula.charAt(i) : value +formulaElement;
            } else if (i != 0 && isFormulaOperations(value.length(), i, formulaElement, formula.charAt(i - 1))) {
                formulaOperations.add(formulaElement);
            }
            value = ifNumberOver(formula, formulaLength, i, value);
        }
    }

    /**
     * This method check element in formula
     *
     * @param formulaElement some element in formula
     * @param variables      HashMap with all other arguments
     * @return True if variables contain formulaElement
     */
    private static boolean checkFormulaValues(char formulaElement, HashMap<String, Double> variables) {
        if (!variables.containsKey(Character.toString(formulaElement))) {
            System.out.println("No data for item \"" + formulaElement + "\" was found");
            System.exit(0); // stop program
        }
        return true;
    }

    /**
     * This method check if formulaElement is mathOperations
     *
     * @param valueLength     length some digit in formula
     * @param i               step in the formula
     * @param formulaElement  element in formula
     * @param previousElement previous element in formula
     * @return True if is formulaElement is mathOperations
     */
    private static boolean isFormulaOperations(int valueLength, int i, char formulaElement, char previousElement) {
        if (i != 0 && mathOperations.contains(formulaElement) && mathOperations.contains(previousElement) &&
                !(formulaElement == '-' || formulaElement == '+')) {
            System.out.println("No data for item \"" + previousElement + "\" and \"" + formulaElement + "\" was found");
            System.exit(0); // stop program
        }
        return ((valueLength == 0) && (i != 0) && mathOperations.contains(formulaElement) &&
                !((formulaElement == '+' || formulaElement == '-') && mathOperations.contains(previousElement)));
    }

    /**
     * This method check if value is over (if value == 1.5 from formula 'x + 1.5 + 2' value is over)
     * Value is over only if the value contains the number completely
     *
     * @param formula       equation
     * @param formulaLength length of formula
     * @param i             step in the formula
     * @param value         number
     * @return empty value if number is over or value if not
     */
    private static String ifNumberOver(String formula, int formulaLength, int i, String value) {
        if (value.length() != 0 && ((i == formulaLength - 1) ||
                (!Character.isDigit(formula.charAt(i + 1)) && (formula.charAt(i + 1) != '.')))) {
            formulaValues.add(Double.parseDouble(value));
            return ""; // new value
        }
        return value; // continue to write values
    }

    /**
     * This method checks whether this value is negative
     *
     * @param formula equation
     * @param i       step in the formula
     * @return True if value is negative
     */
    private static boolean isNegative(String formula, int i) {
        return (i != 0 && formula.charAt(i - 1) == '-' &&
                (i == 1 || mathOperations.contains(formula.charAt(i - 2))));
    }
}
