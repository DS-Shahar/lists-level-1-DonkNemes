import java.util.*;

public class Main {

    public static Node<Integer> arrayToList(int[] arr) { // EX1
        if (arr == null || arr.length == 0) {
            return null;
        }

        Node<Integer> l1 = new Node<>(arr[0]);
        Node<Integer> current = l1;

        for (int i = 1; i < arr.length; i++) {
            Node<Integer> nextNode = new Node<>(arr[i]);
            current.setNext(nextNode);
            current = nextNode;
        }

        return l1;
    }

    public static void printList(Node<Integer> list) { // EX2
        if (list == null) {
            return;
        }
        System.out.println(list.getValue());
        printList(list.getNext());
    }

    public static void printListReverse(Node<Integer> list) { // EX3
        if (list == null) {
            return;
        }
        printListReverse(list.getNext());
        System.out.println(list.getValue());
    }

    public static Node<Integer> createList() { // EX4
        Scanner reader = new Scanner(System.in);
        int num = reader.nextInt();
        if (num == -1) return null;

        Node<Integer> l1 = new Node<>(num);
        Node<Integer> current = l1;

        while (true) {
            num = reader.nextInt();
            if (num == -1) break;

            Node<Integer> nextNode = new Node<>(num);
            current.setNext(nextNode);
            current = nextNode;
        }

        return l1;
    }

    public static boolean isValueInList(Node<Integer> list, int num) { // EX5
        if (list == null) {
            return false;
        }
        if (list.getValue() == num) {
            return true;
        }
        return isValueInList(list.getNext(), num);
    }

    public static Node<Integer> removeFirstOccurrence(Node<Integer> list, int num) { // EX6
        if (list == null) {
            return null;
        }
        if (list.getValue() == num) {
            return list.getNext();
        }

        Node<Integer> current = list;
        while (current.getNext() != null) {
            if (current.getNext().getValue() == num) {
                current.setNext(current.getNext().getNext());
                return list;
            }
            current = current.getNext();
        }

        return list;
    }

    public static Node<Integer> removeNodeAtPosition(Node<Integer> list, int pos) { // EX7
        if (list == null || pos < 1) {
            return list;
        }
        if (pos == 1) {
            return list.getNext();
        }

        Node<Integer> current = list;
        int count = 1;

        while (current.getNext() != null && count < pos - 1) {
            current = current.getNext();
            count++;
        }

        if (current.getNext() != null) {
            current.setNext(current.getNext().getNext());
        }

        return list;
    }

    public static <T> boolean areAllValuesInList(Node<T> l1, Node<T> l2) { // EX8
        if (l1 == null) {
            return true;
        }

        if (!isValueInList(l2, l1.getValue())) {
            return false;
        }

        return areAllValuesInList(l1.getNext(), l2);
    }

    public static void printCommonValues(Node<Integer> l1, Node<Integer> l2) { // EX9
        if (l1 == null) {
            return;
        }

        if (isValueInList(l2, l1.getValue())) {
            System.out.println(l1.getValue());
        }

        printCommonValues(l1.getNext(), l2);
    }

    public static Node<Integer> getCommonValues(Node<Integer> l1, Node<Integer> l2) { // EX10
        if (l1 == null) {
            return null;
        }

        Node<Integer> rest = getCommonValues(l1.getNext(), l2);

        if (isValueInList(l2, l1.getValue())) {
            return new Node<>(l1.getValue(), rest);
        }

        return rest;
    }

    public static Node<Integer> removeValuesInL2FromL1(Node<Integer> l1, Node<Integer> l2) { // EX11
        if (l1 == null) {
            return null;
        }

        Node<Integer> rest = removeValuesInL2FromL1(l1.getNext(), l2);

        if (isValueInList(l2, l1.getValue())) {
            return rest;
        }

        return new Node<>(l1.getValue(), rest);
    }

    public static void main(String[] args) {
        Node<Integer> list1 = new Node<>(1, new Node<>(2, new Node<>(3, null)));
        Node<Integer> list2 = new Node<>(3, new Node<>(2, new Node<>(4, null)));

        System.out.println("Print List:");
        printList(list1);

        System.out.println("\nPrint List Reverse:");
        printListReverse(list1);

        System.out.println("\nAre all values in list1 in list2?");
        System.out.println(areAllValuesInList(list1, list2));

        System.out.println("\nCommon values:");
        printCommonValues(list1, list2);

        System.out.println("\nNew list with common values:");
        Node<Integer> commonValues = getCommonValues(list1, list2);
        printList(commonValues);

        System.out.println("\nList1 after removing values in List2:");
        Node<Integer> updatedList1 = removeValuesInL2FromL1(list1, list2);
        printList(updatedList1);
    }
}
