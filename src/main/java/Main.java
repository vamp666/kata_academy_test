import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;

import static java.lang.Integer.*;

public class Main {

    public static void main(String[] arg){
        Scanner reader = new Scanner(System.in);
        System.out.println("Введите простое математическое выражение между двумя натуральными числами не больше 10:");
        String expression = reader.nextLine();

        if(!expression.matches("^..?.?.?\s[+/*-]\s..?.?.?\s?$")){
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("Загружена строка неправильного вида.");
                System.exit(0);
            }
        }

        boolean isLatin = false;
        List<String> latin = Arrays.asList("I", "V", "X", "L", "C");

        for (String substring : latin){
            if (expression.contains(substring)){
                isLatin = true;
                break;
            }
        }


        boolean isArabic = false;
        List<String> arabic = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "0");
        for (String substring : arabic){
            if (expression.contains(substring)){
                isArabic = true;
                break;
            }
        }

        if((isArabic) && (isLatin)){
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("Выражение может включать отдельно либо арабские, либо римские цифры. " +
                        "Невозможно использовать оба вида записи одновременно.");
                System.exit(0);
            }
        }


        if(isArabic) {
            try {
                parseInt(expression.split(" ")[0]);
                parseInt(expression.split(" ")[2]);
            } catch (NumberFormatException e) {
                System.out.println("Неправильный формат переменных выражения.");
                System.exit(0);
            }


            int num1 = parseInt(expression.split(" ")[0]);
            int num2 = parseInt(expression.split(" ")[2]);

            if ((num1 > 10 || num1 < 1) & (num2 > 10 | num2 < 1)) {
                try {
                    throw new IOException();
                } catch (IOException e) {
                    System.out.println("Оба числа выходят за пределы интервала от 1 до 10.");
                    System.exit(0);
                }
            }

            if (num1 > 10 || num1 < 1) {
                try {
                    throw new IOException();
                } catch (IOException e) {
                    System.out.println("Первое число выходит за пределы интервала от 1 до 10.");
                    System.exit(0);
                }
            }

            if (num2 > 10 || num2 < 1) {
                try {
                    throw new IOException();
                } catch (IOException e) {
                    System.out.println("Второе число выходит за пределы интервала от 1 до 10.");
                    System.exit(0);
                }
            }

            System.out.println();
            System.out.println("Результат выражения:");
            System.out.println(calc(expression));
        }


        if(isLatin){

            List<String> latin2 = Arrays.asList("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX",
                    "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII",
                    "XIX", "XX", "XXX","XL", "L", "LX" , "LXX", "LXXX", "XC", "C");
            List<Integer> arabic2 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
                    20, 30, 40, 50, 60, 70, 80, 90, 100);

            List<String> latin3 = latin2.subList(0,10);
            String num1 = expression.split(" ")[0];
            String num2 = expression.split(" ")[2];

            if(!latin3.contains(num1) || !latin3.contains(num2)){
                try {
                    throw new ArrayIndexOutOfBoundsException();

                } catch (ArrayIndexOutOfBoundsException e) {

                    System.out.println("Значения должны быть в интервале от I до X");
                    System.exit(0);
                }
            }



            Digit changedNum1 = Digit.valueOf(num1);
            Digit changedNum2 = Digit.valueOf(num2);
            String translatedExpression = changedNum1.getArabic() + " " + expression.split(" ")[1] + " "
                    + changedNum2.getArabic();
            String resultInArabic = calc(translatedExpression);

            if (parseInt(resultInArabic) < 1) {
                try {
                    throw new NumberFormatException();
                } catch (NumberFormatException e) {
                    System.out.println("Невозможно получить отрицательное число или ноль в римской системе записи.");
                    System.exit(0);
                }
            }


            int nearest = 0;
            long value = 2L * MAX_VALUE;
            int intResultInArabic = parseInt(resultInArabic);
            for(int i : arabic2)
                if (value > Math.abs(intResultInArabic - i)){
                    value = Math.abs(intResultInArabic - i);
                    nearest = i;}
            int diff = parseInt(resultInArabic) - nearest;

            String resultInLatin = latin2.get(arabic2.indexOf(nearest));

            if(diff > 0){
                resultInLatin = resultInLatin + latin2.get(arabic2.indexOf(diff));
            }

            if(diff < 0){
                resultInLatin = latin2.get(arabic2.indexOf(Math.abs(diff))) + resultInLatin;
            }


            System.out.println();
            System.out.println("Результат выражения:");
            System.out.println(resultInLatin);
        }
        }





    public static String calc(String input){
        String result = " ";
        int divRes;
        String[] row = input.split(" ");

        switch (row[1]){
            case "*":
                result = String.valueOf(parseInt(row[0]) * parseInt(row[2]));
                break;
            case "/":
                divRes = (int) Math.floor(parseInt(row[0]) / parseInt(row[2]));
                result = String.valueOf(divRes);
                break;
            case "+":
                result = String.valueOf(parseInt(row[0]) + parseInt(row[2]));
                break;
            case "-":
                result = String.valueOf(parseInt(row[0]) - parseInt(row[2]));
                break;
        }
        return result;
    }

}