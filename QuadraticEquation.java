import java.util.InputMismatchException;
import java.util.Scanner;
import java.lang.ArithmeticException;
import java.lang.Math;
import java.io.IOException;

public class QuadraticEquation {
    // Замiнив тип даних на клас обгортку, щоб було значення null, якщо дискримiнант менше нуля
    static private Double a, b, c, D, x1, x2;
    static private Scanner scanner;

    public static void main(String args []) throws IOException {
        try {
            if (runProgram()) {
                inputCoeficients();
                calculateValues();
            }
        } catch (InputMismatchException e) { // Помилка вводу чисел
            errorInProgram("!!! Error input (a, b, c - double numbers) !!!");
        } catch (ArithmeticException s) { // Якщо вiдбудеться дiлення на нуль (це не квадратне рiвняння)
            errorInProgram("!!!  Division by zero (а != 0, it's not quadratic equation!) !!!");
        } finally { // Зробив цей блок, щоб у будь-якому разi було закриття потоку вводу i онулення посилання на об'єкт вводу
            scanner.close();
            scanner = null;
            System.out.println("Program end!");
        }
    }

    // ====================================================================================================

    public static boolean runProgram() { // Запуск програми (залежить вiд користувача)
        scanner = new Scanner(System.in);
        System.out.println("Solve quadratic equation: a * x ^ 2 + b * x + c = 0");
        System.out.print("Input \"yes\" for run program or any for close program: ");
        return scanner.nextLine().equals("yes"); // Вихiд з програми у разi бажання користувача (str != c)
    }

    public static Double inputDouble(String messageString) { // Увiд дiйсного числа
        System.out.print(messageString);
        return scanner.nextDouble();
    }

    public static void inputCoeficients() { // Увiд коефiцiєнтiв
        a = inputDouble("Input value a: ");
        if (a == 0) { // Це вже не квадратне рiвняння + буде дiлення на нуль
            throw new ArithmeticException();
        }
        b = inputDouble("Input value b: ");
        c = inputDouble("Input value c: ");
    }
    
    public static void calculateValues() { // Розрахунок усiх значень й їх вивiд
        D = (b * b) - (4 * a * c);
        if (D >= 0) {
            Double sqrtD = Math.sqrt(D), a2 = a * 2; // Економiя ресурсiв для розрахунку
            x1 = (-b + sqrtD) / a2;
            x2 = (-b - sqrtD) / a2;
        } else { // Якщо дискримiнант вiд'ємний, то коренiв не iснує, вiдповiдно: null
            x2 = x1 = null;
        }
        System.out.println("Discriminant: " + D);
        System.out.println("x1: " + x1);
        System.out.println("x2: " + x2);
    }

    public static void errorInProgram(String messageString) { // Якщо сталася помилка виводиться повiдомлення й онулюються усi значення
        a = b = c = D = x1 = x2 = null;
        System.out.println("!!! " + messageString + " !!!");
    }
}
