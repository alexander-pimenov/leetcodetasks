package ru.pimalex.task;

/**
 * #09 Palindrome Number
 * <p>
 * Если данное число x является палиндромом, вернуть true, в противном случае вернуть false.
 * <p>
 * Example 1:
 * Input: x = 121
 * Output: true
 * Explanation: 121 reads as 121 from left to right and from right to left.
 * <p>
 * Example 2:
 * Input: x = -121
 * Output: false
 * Explanation: From left to right, it reads -121. From right to left, it becomes 121-.
 * Therefore, it is not a palindrome. Т.е. все отрицательные числа не являются палиндромом.
 * <p>
 * Example 3:
 * Input: x = 10
 * Output: false
 * Explanation: Reads 01 from right to left. Therefore, it is not a palindrome.
 */
public class PalindromeNumber {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            int x = i % 10;
            int y = i / 10;
            System.out.printf(" деление %d на 10 -> %d%n", i, y);
            // вывода остатка от деления на 10
            System.out.printf(" вывода остатка от деления на 10 : (%d %% 10) = %d%n", i, x);
            //хочу здесь посмотреть, какой остаток при делении по модулю на 10
            //Можно экранировать знак процента, добавив перед ним еще один знак процента.
            if (x == 0) {
                System.out.printf(" бинго (%d %% 10) = %d%n", i, x);
            }
        }
        System.out.println("----------");
        System.out.println(isPalindrome(111));
    }

    public static boolean isPalindrome(int x) {
        // 153
        // 351
        //return 153 == 351 - false
        // 151
        // 151
        //return 151 == 151 - true

        //обработка ситуации с отрицательным числом
        if (x < 0) {
            return false;
        }
        if (x % 10 == 0 && x != 0) {
            return false;
        }

        int invertedNumber = 0;
        //сделаем копию входящего числа, чтоб не потерять
        int copyOfX = x;

        //здесь мы просто разворачиваем число наоборот, почти как в ReverseInteger
        while (copyOfX != 0) {
            //invertedNumber * 10 - добавить нолик в конце
            //copyOfX % 10 - остаток от деления на 10
            //copyOfX / 10 - отнимаем нолик в конце
            invertedNumber = invertedNumber * 10 + copyOfX % 10;
            System.out.printf("  --- invertedNumber = %d%n", invertedNumber);
            copyOfX = copyOfX / 10;
            System.out.printf("  === copyOfX = %d%n", copyOfX);
        }
        System.out.println("развернутое число - " + invertedNumber);
        return invertedNumber == x;
    }
}
