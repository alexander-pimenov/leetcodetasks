package ru.pimalex.task;

import lombok.Data;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * #02 Add two numbers
 * <p>
 * Вам даны два непустых связанных списка, представляющих два неотрицательных
 * целых числа. Цифры хранятся в обратном порядке, и каждый из их узлов содержит
 * одну цифру. Добавьте два числа и верните сумму в виде связанного списка.
 * <p>
 * Вы можете предположить, что эти два числа не содержат начальных нулей,
 * кроме самого числа 0.
 * <p>
 * Ввод: l1 = [2,4,3], l2 = [5,6,4]
 * Ввод: l1 = [2 -> 4 -> 3], l2 = [5 -> 6 -> 4]
 * Вывод: [7,0,8]
 * Вывод: [7 -> 0 -> 8]
 * Объяснение: 342 + 465 = 807.
 * <p>
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class AddTwoNumbers {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
        ListNode listNode = addTwoNumbers(l1, l2);
        System.out.println(listNode);


        System.out.println("---здесь вспомнил, как с помощью queue обойти дерево и разложить его в список---");
        List<ListNode> listNodes = getListListNodeWithoutChildrenFromRootNode(l1);
        System.out.println(listNodes);
        List<Integer> collect = listNodes.stream().map(ListNode::getVal).collect(Collectors.toList());
        System.out.println(collect);

    }

    //сложность этого решения O(n)
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode result = new ListNode(0);
        //хранитель десяток (при сложении запоминаем единичку, если сумма чисел больше 10)
        int dozenCarrier = 0;
        //указатели на ноды
        ListNode pointer1 = l1, pointer2 = l2, currentNode = result;

        //идем по циклу пока поинтеры не равны NULL
        while (pointer1 != null || pointer2 != null) {
            //вводим переменную
            int pointer1Value = (pointer1 == null) ? 0 : pointer1.val;
            int pointer2Value = (pointer2 == null) ? 0 : pointer2.val;

            int sum = pointer1Value + pointer2Value + dozenCarrier;
            dozenCarrier = sum / 10;
            //в текущую ноду сохраним число, но помним что оно должно быть мень 10
            //т.е. остаток от деления на 10
            currentNode.next = new ListNode(sum % 10);
            //затем текущий каррентнод перекидываем на следующий нод
            currentNode = currentNode.next;
            //перекидываем поинтеры
            if (pointer1 != null) {
                pointer1 = pointer1.next;
            }
            if (pointer2 != null) {
                pointer2 = pointer2.next;
            }
        }
        //обработаем ситуацию если в хранителе десяток остается 1
        if (dozenCarrier > 0) {
            currentNode.next = new ListNode(dozenCarrier);
        }
        return result.next;
    }

    private static List<ListNode> getListListNodeWithoutChildrenFromRootNode(
            ListNode rootNode) {
        ListNode item = new ListNode(rootNode);
        List<ListNode> resultList = new ArrayList<>();
        Queue<ListNode> queue = new ArrayDeque<>();
        queue.add(item);
        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            resultList.add(node);
            if (node.getNext() != null) {
                queue.add(node.getNext());
            }
        }
        List<ListNode> sortedList =
                resultList.stream()
                        .peek(e -> e.setNext(null))
                        //.sorted(Comparator.comparing(ListNode::getVal)) // можем даже отсортировать
                        .collect(Collectors.toList());
        return sortedList;
    }
}

@Data
class ListNode {
    //значение в ноде
    int val;
    //ссылка на следующую ноду
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    //конструктор копирования
    public ListNode(ListNode node) {
        this.val = node.val;
        this.next = node.next;
    }
}
