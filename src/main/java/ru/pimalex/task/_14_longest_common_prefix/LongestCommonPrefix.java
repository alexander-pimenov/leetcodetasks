package ru.pimalex.task._14_longest_common_prefix;

import com.google.common.collect.Lists;
import com.google.common.primitives.Chars;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <a href="https://leetcode.com/problems/longest-common-prefix/">14. Longest Common Prefix</a>
 * Напишите функцию, которая находит самую длинную строку общего префикса среди массива строк.
 * Если общего префикса нет, вернуть пустую строку "".
 * <p>
 * Example 1:
 * Input: strs = ["flower","flow","flight"]
 * Output: "fl"
 * <p>
 * Example 2:
 * Input: strs = ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 * <p>
 * Сложность n*m - вложенный цикл, но и учитывается количество символов в слове.
 */
public class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] strs1 = new String[]{"flower", "flow", "flight"};
        System.out.println(longestCommonPrefix01(strs1));

        String[] strs2 = new String[]{"dog", "racecar", "docar"};
        System.out.println(longestCommonPrefix02(strs2));

        String[] strs3 = new String[]{"sabrikosina", "sabrina", "sabula"};
        System.out.println(longestCommonPrefix02(strs3));
    }

    public static String longestCommonPrefix01(String[] strings) {
        System.out.printf("получили массив: %s%n", List.of(strings));
        //защита от NPE
        if (strings == null || strings.length == 0) {
            return "";
        }

        String result = strings[0]; //самый первый элемент массива

        for (int i = 0; i < strings.length; i++) {
            System.out.printf("%d круг внешнего цикла. проверяем слово под индексом %d = %s%n", i, i, strings[i]);
            while (strings[i].indexOf(result) != 0) {
                System.out.printf("    внутренний цикл. проверяем слово под индексом %d = %s%n", i, strings[i]);
                //уменьшаем на 1 символ справа
                result = result.substring(0, result.length() - 1);
                System.out.printf("        result = %s%n", result);
                if (result.isEmpty()) {
                    return "";
                }
            }
        }
        return result;
    }

    public static String longestCommonPrefix02(String[] strings) {
        System.out.printf("получили массив: %s%n", List.of(strings));
        StringBuilder result = new StringBuilder();
        Arrays.sort(strings);
        //после сортировки слова станут так:
        //[flight, flow, flower] -> т.е. по убыванию. и нам остается только
        //сравнивать совпадения символов
        //flight
        //flow
        //flower

        char[] first = strings[0].toCharArray();
        char[] last = strings[strings.length - 1].toCharArray();

        //эти две строки только для логгирования - first
        List<Character> firstListChars = strings[0].chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        System.out.printf("  получили массив символов первого слова: %s%n", firstListChars);

        //эти две строки только для логгирования - last
        var listListChars = IntStream.range(0, last.length).mapToObj(i -> last[i])
                .collect(Collectors.toList());
        System.out.printf("  получили массив символов последнего слова: %s%n", listListChars);
        for (int i = 0; i < first.length; i++) {
            if (first[i] != last[i]) {
                System.out.println("не совпадают символы. выходим из цикла");
                break;
            }
            result.append(first[i]);
            System.out.printf("получаем результат result = %s%n", result);
        }
        return result.toString();
    }

    public static List<Character> arraysOfCharsToListOfCharacters(char[] chars) {
        return IntStream.range(0, chars.length).mapToObj(i -> chars[i])
                .collect(Collectors.toList());
    }

    public static List<Character> arraysOfCharsToListOfCharactersWithGuava(char[] chars) {
        return Lists.newLinkedList(Chars.asList(chars));
    }
}
