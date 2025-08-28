import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.InputMismatchException;

public class AdvancedCalculator {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DecimalFormat df = new DecimalFormat("0.##########");

    public static void main(String[] args) {
        displayWelcomeMessage();
        
        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    performBasicArithmetic();
                    break;
                case 2:
                    performScientificCalculation();
                    break;
                case 3:
                    performUnitConversion();
                    break;
                case 4:
                    running = false;
                    System.out.println("\nThank you for using the Advanced Calculator. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        
        scanner.close();
    }

    private static void displayWelcomeMessage() {
        System.out.println("====================================");
        System.out.println("      ADVANCED CONSOLE CALCULATOR    ");
        System.out.println("====================================");
        System.out.println("This calculator supports:");
        System.out.println("- Basic arithmetic operations");
        System.out.println("- Scientific calculations");
        System.out.println("- Unit conversions");
        System.out.println("====================================\n");
    }

    private static void displayMainMenu() {
        System.out.println("\nMAIN MENU:");
        System.out.println("1. Basic Arithmetic Operations");
        System.out.println("2. Scientific Calculations");
        System.out.println("3. Unit Conversions");
        System.out.println("4. Exit");
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }

    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }

    private static void performBasicArithmetic() {
        System.out.println("\nBASIC ARITHMETIC OPERATIONS:");
        System.out.println("1. Addition (+)");
        System.out.println("2. Subtraction (-)");
        System.out.println("3. Multiplication (*)");
        System.out.println("4. Division (/)");
        System.out.println("5. Modulus (%)");
        
        int operation = getIntInput("Select operation: ");
        if (operation < 1 || operation > 5) {
            System.out.println("Invalid operation selected.");
            return;
        }
        
        double num1 = getDoubleInput("Enter first number: ");
        double num2 = getDoubleInput("Enter second number: ");
        double result = 0;
        String operationSymbol = "";
        
        switch (operation) {
            case 1:
                result = num1 + num2;
                operationSymbol = "+";
                break;
            case 2:
                result = num1 - num2;
                operationSymbol = "-";
                break;
            case 3:
                result = num1 * num2;
                operationSymbol = "*";
                break;
            case 4:
                if (num2 == 0) {
                    System.out.println("Error: Division by zero is not allowed.");
                    return;
                }
                result = num1 / num2;
                operationSymbol = "/";
                break;
            case 5:
                result = num1 % num2;
                operationSymbol = "%";
                break;
        }
        
        System.out.println("\nResult: " + df.format(num1) + " " + operationSymbol + " " + 
                          df.format(num2) + " = " + df.format(result));
    }

    private static void performScientificCalculation() {
        System.out.println("\nSCIENTIFIC CALCULATIONS:");
        System.out.println("1. Square Root (√x)");
        System.out.println("2. Exponentiation (x^y)");
        System.out.println("3. Natural Logarithm (ln x)");
        System.out.println("4. Power of 10 (10^x)");
        System.out.println("5. Sine (sin x)");
        System.out.println("6. Cosine (cos x)");
        System.out.println("7. Tangent (tan x)");
        
        int operation = getIntInput("Select operation: ");
        if (operation < 1 || operation > 7) {
            System.out.println("Invalid operation selected.");
            return;
        }
        
        double result = 0;
        double input = 0;
        String operationName = "";
        
        switch (operation) {
            case 1:
                input = getDoubleInput("Enter number to find square root: ");
                if (input < 0) {
                    System.out.println("Error: Cannot calculate square root of a negative number.");
                    return;
                }
                result = Math.sqrt(input);
                operationName = "√" + df.format(input);
                break;
            case 2:
                double base = getDoubleInput("Enter base: ");
                double exponent = getDoubleInput("Enter exponent: ");
                result = Math.pow(base, exponent);
                operationName = df.format(base) + "^" + df.format(exponent);
                break;
            case 3:
                input = getDoubleInput("Enter number to find natural logarithm: ");
                if (input <= 0) {
                    System.out.println("Error: Natural logarithm is only defined for positive numbers.");
                    return;
                }
                result = Math.log(input);
                operationName = "ln(" + df.format(input) + ")";
                break;
            case 4:
                input = getDoubleInput("Enter exponent for 10^x: ");
                result = Math.pow(10, input);
                operationName = "10^" + df.format(input);
                break;
            case 5:
                input = getDoubleInput("Enter angle in degrees for sin(x): ");
                result = Math.sin(Math.toRadians(input));
                operationName = "sin(" + df.format(input) + "°)";
                break;
            case 6:
                input = getDoubleInput("Enter angle in degrees for cos(x): ");
                result = Math.cos(Math.toRadians(input));
                operationName = "cos(" + df.format(input) + "°)";
                break;
            case 7:
                input = getDoubleInput("Enter angle in degrees for tan(x): ");
                // Check for 90 + 180n degrees where tan is undefined
                if ((input - 90) % 180 == 0) {
                    System.out.println("Error: Tangent is undefined for " + input + " degrees.");
                    return;
                }
                result = Math.tan(Math.toRadians(input));
                operationName = "tan(" + df.format(input) + "°)";
                break;
        }
        
        System.out.println("\nResult: " + operationName + " = " + df.format(result));
    }

    private static void performUnitConversion() {
        System.out.println("\nUNIT CONVERSIONS:");
        System.out.println("1. Temperature");
        System.out.println("2. Length");
        System.out.println("3. Weight/Mass");
        System.out.println("4. Currency (USD to EUR)");
        
        int category = getIntInput("Select conversion category: ");
        if (category < 1 || category > 4) {
            System.out.println("Invalid category selected.");
            return;
        }
        
        switch (category) {
            case 1:
                temperatureConversion();
                break;
            case 2:
                lengthConversion();
                break;
            case 3:
                weightConversion();
                break;
            case 4:
                currencyConversion();
                break;
        }
    }

    private static void temperatureConversion() {
        System.out.println("\nTEMPERATURE CONVERSION:");
        System.out.println("1. Celsius to Fahrenheit");
        System.out.println("2. Fahrenheit to Celsius");
        System.out.println("3. Celsius to Kelvin");
        System.out.println("4. Kelvin to Celsius");
        
        int conversion = getIntInput("Select conversion type: ");
        if (conversion < 1 || conversion > 4) {
            System.out.println("Invalid conversion selected.");
            return;
        }
        
        double temp = getDoubleInput("Enter temperature to convert: ");
        double result = 0;
        String fromUnit = "";
        String toUnit = "";
        
        switch (conversion) {
            case 1:
                result = (temp * 9/5) + 32;
                fromUnit = "°C";
                toUnit = "°F";
                break;
            case 2:
                result = (temp - 32) * 5/9;
                fromUnit = "°F";
                toUnit = "°C";
                break;
            case 3:
                result = temp + 273.15;
                fromUnit = "°C";
                toUnit = "K";
                break;
            case 4:
                result = temp - 273.15;
                fromUnit = "K";
                toUnit = "°C";
                break;
        }
        
        System.out.println("\nResult: " + df.format(temp) + fromUnit + " = " + 
                          df.format(result) + toUnit);
    }

    private static void lengthConversion() {
        System.out.println("\nLENGTH CONVERSION:");
        System.out.println("1. Meters to Feet");
        System.out.println("2. Feet to Meters");
        System.out.println("3. Kilometers to Miles");
        System.out.println("4. Miles to Kilometers");
        System.out.println("5. Centimeters to Inches");
        System.out.println("6. Inches to Centimeters");
        
        int conversion = getIntInput("Select conversion type: ");
        if (conversion < 1 || conversion > 6) {
            System.out.println("Invalid conversion selected.");
            return;
        }
        
        double length = getDoubleInput("Enter length to convert: ");
        double result = 0;
        String fromUnit = "";
        String toUnit = "";
        
        switch (conversion) {
            case 1:
                result = length * 3.28084;
                fromUnit = "m";
                toUnit = "ft";
                break;
            case 2:
                result = length / 3.28084;
                fromUnit = "ft";
                toUnit = "m";
                break;
            case 3:
                result = length * 0.621371;
                fromUnit = "km";
                toUnit = "mi";
                break;
            case 4:
                result = length / 0.621371;
                fromUnit = "mi";
                toUnit = "km";
                break;
            case 5:
                result = length * 0.393701;
                fromUnit = "cm";
                toUnit = "in";
                break;
            case 6:
                result = length / 0.393701;
                fromUnit = "in";
                toUnit = "cm";
                break;
        }
        
        System.out.println("\nResult: " + df.format(length) + fromUnit + " = " + 
                          df.format(result) + toUnit);
    }

    private static void weightConversion() {
        System.out.println("\nWEIGHT/MASS CONVERSION:");
        System.out.println("1. Kilograms to Pounds");
        System.out.println("2. Pounds to Kilograms");
        System.out.println("3. Grams to Ounces");
        System.out.println("4. Ounces to Grams");
        
        int conversion = getIntInput("Select conversion type: ");
        if (conversion < 1 || conversion > 4) {
            System.out.println("Invalid conversion selected.");
            return;
        }
        
        double weight = getDoubleInput("Enter weight to convert: ");
        double result = 0;
        String fromUnit = "";
        String toUnit = "";
        
        switch (conversion) {
            case 1:
                result = weight * 2.20462;
                fromUnit = "kg";
                toUnit = "lb";
                break;
            case 2:
                result = weight / 2.20462;
                fromUnit = "lb";
                toUnit = "kg";
                break;
            case 3:
                result = weight * 0.035274;
                fromUnit = "g";
                toUnit = "oz";
                break;
            case 4:
                result = weight / 0.035274;
                fromUnit = "oz";
                toUnit = "g";
                break;
        }
        
        System.out.println("\nResult: " + df.format(weight) + fromUnit + " = " + 
                          df.format(result) + toUnit);
    }

    private static void currencyConversion() {
        // Using a fixed exchange rate for simplicity
        // In a real application, you would fetch current rates from an API
        final double USD_TO_EUR_RATE = 0.85;
        final double EUR_TO_USD_RATE = 1.18;
        
        System.out.println("\nCURRENCY CONVERSION (using approximate rates):");
        System.out.println("1. USD to EUR");
        System.out.println("2. EUR to USD");
        
        int conversion = getIntInput("Select conversion type: ");
        if (conversion < 1 || conversion > 2) {
            System.out.println("Invalid conversion selected.");
            return;
        }
        
        double amount = getDoubleInput("Enter amount to convert: ");
        double result = 0;
        String fromCurrency = "";
        String toCurrency = "";
        
        switch (conversion) {
            case 1:
                result = amount * USD_TO_EUR_RATE;
                fromCurrency = "USD";
                toCurrency = "EUR";
                break;
            case 2:
                result = amount * EUR_TO_USD_RATE;
                fromCurrency = "EUR";
                toCurrency = "USD";
                break;
        }
        
        System.out.println("\nResult: " + df.format(amount) + " " + fromCurrency + " = " + 
                          df.format(result) + " " + toCurrency);
        System.out.println("Note: For accurate currency conversion, please use a financial service with live rates.");
    }
}