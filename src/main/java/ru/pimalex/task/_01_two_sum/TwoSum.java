package ru.pimalex.task._01_two_sum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * #01 Two Sum
 * <p>
 * Верните индексы двух чисел из массива целых чисел nums,
 * которые при суммировании дают заданное целое число target.
 * Предполагается, что для каждого входного значения существует
 * только одно решение, и нельзя использовать один и тот же элемент
 * дважды. Ответ можно возвращать в любом порядке.
 * <p>
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 */
public class TwoSum {

    public static void main(String[] args) throws IllegalAccessException {

        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        System.out.println(Arrays.toString(twoSum04(nums, target)));
        System.out.println(Arrays.toString(twoSum03(nums, target)));
        System.out.println(Arrays.toString(twoSum02(nums, target)));
        System.out.println(Arrays.toString(twoSum01(nums, target)));
    }

    //два вложенных цикла, простое решение, сложность O(n*n)
    public static int[] twoSum01(int[] nums, int target) throws IllegalAccessException {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[]{i, j};
                }
            }
        }
        //если не нашли решение, то бросим исключение или пустой массив
        throw new IllegalAccessException("No solution for defined input data!!!");
    }

    //два вложенных цикла, простое решение, сложность O(n*n)
    public static int[] twoSum02(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[]{i, j};
                }
            }
        }
        //если не нашли решение, то вернем пустой массив
        return new int[]{};
    }

    // чтобы уйти от сложности O(n*n)? нужен другой подход -
    // использовать мапу, где сложность О(1)
    public static int[] twoSum03(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        //заполним мапу
        for (int i = 0; i < nums.length; i++) {
            //key - число в массиве, value - индекс
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int requiredNumber = target - nums[i];
            if (map.containsKey(requiredNumber) && map.get(requiredNumber) != i) {
                return new int[]{i, map.get(requiredNumber)};
            }
        }
        return new int[]{};
    }

    //Оказывается, мы можем сделать это за один проход. Пока мы итерируем и вставляем
    // элементы в хеш-таблицу, мы также оглядываемся назад, чтобы проверить, существует
    // ли уже дополнение текущего элемента в хеш-таблице. Если он существует, мы нашли
    // решение и немедленно возвращаем индексы.
    //Временная сложность: О(n). Проходим по списку, содержащему n элементы только один раз.
    // Каждый поиск в таблице стоит всего О(1) время.
    public static int[] twoSum04(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        // In case there is no solution, we'll just return null
        return null;
    }
}
