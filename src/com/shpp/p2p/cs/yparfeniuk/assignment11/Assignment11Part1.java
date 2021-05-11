/*
 * This program is designed to solve equations
 * This program also works with only one formula without other arguments
 * Also this program has a basic check of the entered arguments
 * You can use sin, cos, tan, atan, log10, log2, sqrt and '()'
 * */
package com.shpp.p2p.cs.yparfeniuk.assignment11;

import java.util.ArrayList;
import java.util.HashMap;

/* Warning!!!
 * This program implements my own algorithm, so in some cases errors are possible
 * This program only basically checks the entered arguments
 * and warns about elementary errors.
 * To check the work, enter the correct data in the format
 * -a + cos(b ^ (8 - 1,9) / 6.9)
 * a = -5
 * b = 7
 * Yes, some methods can be decomposed at first glance, but do not do it!
 * These methods do the simple job and are not subject to decomposition
 */
public class Assignment11Part1 {
    /**
     * All supported operations and functions
     */
    private static final ArrayList<Character> mathOperations = new ArrayList<>();
    private static final ArrayList<String> functions = new ArrayList<>();

    /**
     * Values and operations in formula
     */
    private static final ArrayList<Character> formulaOperations = new ArrayList<>(); // all operations in the formula
    private static final ArrayList<String> formulaParse = new ArrayList<>(); // all values in the formula

    /**
     * Values and operations in simple formula (from base formula)
     */
    private static final ArrayList<Character> minFormulaOperations = new ArrayList<>();
    private static final ArrayList<String> minFormula = new ArrayList<>();

    /**
     * Positions for open and close brackets position in formula
     */
    private static final ArrayList<Integer> openBrackets = new ArrayList<>();
    private static final ArrayList<Integer> closeBrackets = new ArrayList<>();

    private static int positionInMinFormula; //position minFormula in formula

    public static void main(String[] args) {
        addSupportedActions(); // create ArrayList with all supported actions
        if (args.length >= 1 && checkArguments(args)) {
            parseFormula(args[0]);
            HashMap<String, Double> variables = new HashMap<>(); // other arguments is variables

            for (int i = 1; i < args.length; i++) { // convert argument to HashMap
                String[] dataInArgument = args[i].split("=", 2);
                variables.put(dataInArgument[0], Double.parseDouble(dataInArgument[1]));
            }
            System.out.println(calculate(variables)); // print answer
        } else {
            System.out.println("Please enter correct arguments");
        }
    }

    /**
     * This method parse formula to ArrayList
     *
     * @param formula equation
     */
    private static void parseFormula(String formula) {
        String value = "";
        for (int i = 0, formulaLength = formula.length(); i < formulaLength; i++) {
            char formulaElement = formula.charAt(i); // the tested element of the formula
            if (Character.isAlphabetic(formulaElement)) {
                if (i < formulaLength - 1 && Character.isAlphabetic(formula.charAt(i + 1))) {
                    i = ifFunction(formula, i);
                } else {
                    formulaParse.add(isNegative(formula, i) ? "-" + formulaElement : Character.toString(formulaElement));
                }
            } else if (Character.isDigit(formulaElement) || (formulaElement == '.' && value.length() != 0)) {
                value = isNegative(formula, i) ? "-" + formula.charAt(i) : value + formulaElement;
            } else if (i != 0 && i != formulaLength - 1 &&
                    isFormulaOperations(value.length(), i, formulaElement, formula.charAt(i - 1))) {
                formulaOperations.add(formulaElement);
            }
            value = ifNumberOver(formula, formulaLength, i, value);
            ifBrackets(formulaElement, formula, i);
        }
        checkBrackets();
    }

    /**
     * This method recognizes brackets in formula
     *
     * @param formula equation
     * @param i       step in formula
     * @return place where function ended
     */
    private static int ifFunction(String formula, int i) {
        int j = 2;
        String function = "" + formula.charAt(i) + formula.charAt(i + 1) + formula.charAt(i + 2);

        if (functions.contains(function)) {
            formulaParse.add(isNegative(formula, i) ? "-" + function : function);
        } else if (functions.contains(function += formula.charAt(i + ++j))) {
            formulaParse.add(isNegative(formula, i) ? "-" + function : function);
        } else if (functions.contains(function += formula.charAt(i + ++j))) {
            formulaParse.add(isNegative(formula, i) ? "-" + function : function);
        } else {
            System.out.println("Error!! function = " + function);
            System.exit(0);
        }
        return i + j;
    }

    /**
     * This method recognizes brackets in formula
     *
     * @param formulaElement some element in formula
     * @param formula        equation
     * @param i              step in formula
     */
    private static void ifBrackets(char formulaElement, String formula, int i) {
        if (formulaElement == '(') {
            openBrackets.add(isNegative(formula, i) ? -(formulaParse.size() + formulaOperations.size())
                    : formulaParse.size() + formulaOperations.size());
        } else if (formulaElement == ')') {
            closeBrackets.add(formulaParse.size() + formulaOperations.size());
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

        functions.add("sin");
        functions.add("cos");
        functions.add("tan");
        functions.add("atan");
        functions.add("log10");
        functions.add("log2");
        functions.add("sqrt");
    }

    /**
     * This method check and optimize all arguments
     *
     * @param args an array of arguments
     * @return True if all arguments have passed the basic checking
     */
    private static boolean checkArguments(String[] args) {
        for (int i = 0; i < args.length; i++) {
            args[i] = args[i].replaceAll(" ", "").replaceAll(",", "."); // optimize argument
            for (int j = 0, argLength = args[i].length(); j < argLength && i != 0; j++) {
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
     * This method check numbers of brackets
     */
    private static void checkBrackets() {
        if (openBrackets.size() != closeBrackets.size()) {
            System.out.println("Different numbers of brackets!!!");
            System.exit(0); // stop program
        }
    }

    /**
     * This method performs alternately operations in the formula
     * and returns the result of operations
     *
     * @param variables HashMap with variables
     * @return result
     */
    private static String calculate(HashMap<String, Double> variables) {
        substituteValues(variables);
        while (formulaParse.size() != 1) {
            if (openBrackets.size() != 0) {
                int b = closeBrackets.get(0);
                int a = getOpenBrackets(b - 1);
                getMinFormula(Math.abs(a), b);
                if (Math.abs(a) < b && Math.abs(a + 1) != b) {
                    decideEqualize(minFormula, minFormulaOperations);
                }
                int positionBracket = openBrackets.indexOf(a);
                if (functions.contains(modulElement(formulaParse.get(positionInMinFormula)))) {
                    decideFunction(positionInMinFormula, formulaParse.get(positionInMinFormula),
                            Double.parseDouble(minFormula.get(0)));
                    changePositionBrackets(positionBracket);
                } else {
                    formulaParse.set((positionInMinFormula == 0 && a == 0 ? 0 : positionInMinFormula + 1), ((a >= 0) ?
                            minFormula.get(0) : "" + (Double.parseDouble(minFormula.get(0)) * -1)));
                }
                openBrackets.remove(positionBracket);
                closeBrackets.remove(0);
                minFormula.clear();
                minFormulaOperations.clear();
            } else {
                decideEqualize(formulaParse, formulaOperations);
            }
        }
        return formulaParse.get(0); // result
    }

    /**
     * This method change brackets position
     *
     * @param indexOf from which position to change
     */
    private static void changePositionBrackets(int indexOf) {
        int i = 0;
        int length = closeBrackets.size();

        while (i != length) {
            closeBrackets.set(i, closeBrackets.get(i) - 1);
            if (i >= indexOf) {
                openBrackets.set(i, openBrackets.get(i) > 0 ? openBrackets.get(i) - 1 : openBrackets.get(i) + 1);
            }
            i++;
        }
    }

    /**
     * This method performs the function and inserts the result
     *
     * @param position place for result
     * @param function some function
     * @param element  value for function
     */
    private static void decideFunction(int position, String function, Double element) {
        double answer = 0.0;
        switch (modulElement(function)) {
            case "sin":
                answer = Math.sin(element);
                break;
            case "cos":
                answer = Math.cos(element);
                break;
            case "tan":
                answer = Math.tan(element);
                break;
            case "atan":
                answer = Math.atan(element);
                break;
            case "log10":
                answer = Math.log10(element);
                break;
            case "log2":
                answer = Math.log(element) / Math.log(2);
                break;
            case "sqrt":
                answer = Math.sqrt(element);
                break;
        }
        formulaParse.set(position, function.charAt(0) == '-' ? "" + (-1 * answer) : "" + answer);
        formulaParse.remove(position + 1);
    }

    /**
     * This method looking for the simplest equation and write to ArrayList
     *
     * @param a start position
     * @param b finish position
     */
    private static void getMinFormula(int a, int b) {
        if (a == b) {
            System.out.println("Error formula!!!");
            System.exit(0); // stop program
        }
        int i = 0, mathInFormula = 0;
        int operationDeleted = 0, elementsDeleted = 0;

        while (i < b) {
            if (functions.contains(modulElement(formulaParse.get(i - mathInFormula - elementsDeleted)))) {
                if (i >= a) {
                    minFormula.add(formulaParse.get(i - mathInFormula - elementsDeleted));
                }
            } else {
                if (i >= a) {
                    minFormula.add(formulaParse.get(i - mathInFormula - elementsDeleted));
                }
                ++i;
                if (i >= a && i < b) {
                    minFormulaOperations.add(formulaOperations.get(mathInFormula - operationDeleted));
                    formulaOperations.remove(mathInFormula - operationDeleted);
                    operationDeleted++;
                    changePositionBrackets(i);
                }
                ++mathInFormula;
            }
            if (minFormula.size() == 1) {
                positionInMinFormula = (i - mathInFormula - 1) >= 0 ? i - mathInFormula - 1 : 0;
            } else if (minFormula.size() > 1) {
                formulaParse.remove(i - mathInFormula - elementsDeleted);
                elementsDeleted++;
                changePositionBrackets(i);
            }
            i++;
        }
    }

    /**
     * This method finds the nearest open bracket
     *
     * @param i the position of the closed bracket
     * @return position open bracket
     */
    private static int getOpenBrackets(int i) {
        if (openBrackets.contains(i) || openBrackets.contains(-i)) {
            return (openBrackets.contains(i) ? i : -i);
        } else if (i <= -1) {
            System.out.println("No find open brackets!!!");
            System.exit(0); // stop program
        }
        return getOpenBrackets(--i);
    }

    /**
     * This method decide some equalize from formula
     *
     * @param equalize   some equalize
     * @param operations operations for equalize
     */
    private static void decideEqualize(ArrayList<String> equalize, ArrayList<Character> operations) {
        while (equalize.size() != 1) {
            for (char symbol : mathOperations) {
                if (operations.contains(symbol)) {
                    int position = operations.indexOf(symbol);
                    performAnAction(symbol, position, Double.parseDouble(equalize.get(position)),
                            Double.parseDouble(equalize.get(position + 1)), equalize);
                    operations.remove(position);
                    equalize.remove(position + 1);
                    break;
                }
            }
        }
    }

    /**
     * This method performs operations on numbers a and b
     *
     * @param symbol   mathematical operation
     * @param position position first number in formula
     * @param a        first number in formula (previous mathematical operation)
     * @param b        next number in formula (after mathematical operation)
     */
    private static void performAnAction(char symbol, int position, double a, double b, ArrayList<String> equalize) {
        switch (symbol) {
            case '^':
                equalize.set(position, Double.toString(Math.pow(a, b)));
                break;
            case '+':
                equalize.set(position, Double.toString(a + b));
                break;
            case '-':
                equalize.set(position, Double.toString(a - b));
                break;
            case '*':
                equalize.set(position, Double.toString(a * b));
                break;
            case '/':
                if (b != 0) {
                    equalize.set(position, Double.toString(a / b));
                    break;
                }
                System.out.println("Division by zero!!!");
                System.exit(0); // stop program
        }
    }

    /**
     * This method converts the formula to data
     *
     * @param variables HashMap with variables
     */
    private static void substituteValues(HashMap<String, Double> variables) {
        for (String element : formulaParse) {
            char symbol = element.charAt(element.length() - 1);

            if (!functions.contains(modulElement(element)) && Character.isAlphabetic(symbol)
                    && checkFormulaValues(symbol, variables)) {
                double value = variables.get(Character.toString(symbol));

                formulaParse.set(formulaParse.indexOf(element),
                        Double.toString((element.charAt(0) == '-') ? -value : value));
            }
        }
    }

    /**
     * This method return element without minus
     *
     * @param element some element from formula
     * @return element without minus
     */
    private static String modulElement(String element) {
        if (element.charAt(0) == '-') {
            String newElement = "";
            int elementLength = element.length() - 1;
            while (elementLength != 0) {
                newElement = element.charAt(elementLength) + newElement;
                elementLength--;
            }
            return newElement;
        }
        return element;
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
        return ((valueLength == 0) && (i != 0) && mathOperations.contains(formulaElement) && previousElement != '(' &&
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
            formulaParse.add(value);
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
                (i == 1 || mathOperations.contains(formula.charAt(i - 2)) || formula.charAt(i - 2) == '('));
    }
}