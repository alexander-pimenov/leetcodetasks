package ru.pimalex.task._07_reverse_integer;

/**
 * #07 Reverse integer
 * <p>
 * Инвертирование целого числа.
 * Дано знаковое 32-битное целое число x. Верните x с перевернутыми цифрами.
 * Если обращение x приведет к выходу за пределы диапазона знаковых 32-битных целых
 * чисел [-231, 231 - 1], то верните 0.
 * Предполагается, что в данной среде нельзя хранить 64-битные целые числа
 * (знаковые или беззнаковые). Т.е. если число выходит за длину интежера, то
 * вернуть 0.
 * <p>
 * Input: x = 123
 * Output: 321
 * Example 2:
 * <p>
 * Input: x = -123
 * Output: -321
 * Example 3:
 * <p>
 * Input: x = 120
 * Output: 21
 */
public class ReverseInteger {
    public static void main(String[] args) {
        int x = -2147483648;
        System.out.println(reverse(x));
    }

    public static int reverse(int x) {
        int invertedNumber = 0;
        while (x != 0) {
            int lastDig = x % 10;
            System.out.println(" lastDig = " + lastDig);
            x = x / 10;
            System.out.println(" new x = " + x);
            //Обработаем ситуацию, когда число после переворота будет больше чем интежер
            //возвращаем 0
            //(pop > 7) и (pop < -8) - это связано с последними цифрами в MIN_VALUE и MAX_VALUE
            //в классе Integer у целого числа int. От -2,147,483,648 до +2,147,483,647
            if (invertedNumber > Integer.MAX_VALUE / 10
                    || (invertedNumber == Integer.MAX_VALUE / 10 && lastDig > 7)) {
                System.out.println(" very BIG result = " + invertedNumber);
                return 0;
            }
            if (invertedNumber < Integer.MIN_VALUE /10
                    || (invertedNumber == Integer.MIN_VALUE / 10 && lastDig < -8)) {
                System.out.println(" very SMALL result = " + invertedNumber);
                return 0;
            }

            invertedNumber = invertedNumber * 10 + lastDig;
            System.out.println(" new result = " + invertedNumber);
        }
        return invertedNumber;
    }
}
