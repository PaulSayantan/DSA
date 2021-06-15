package Data_Structures.Stack.Stack2;


/*
Write a java program for Generic Stack Implementation using LinkedList
*/
/*
Author: Sayantan Paul
Regd. No: 1841012233
CSE-E 4th Sem 2020
*/

@SuppressWarnings({"rawtypes"})
public class Stack_LinkedList extends Stack {

    public static void main(String[] args) {
        Stack<Integer> integerStack = new Stack<>();

        integerStack.push(13);
        integerStack.push(34);
        integerStack.push(65);
        integerStack.push(14);

        integerStack.peek();

        integerStack.pop();

        integerStack.search(65);

        integerStack.search(14);

        System.out.println("----------------------------------------------------------------");

        Stack<String> stringStack = new Stack<>();

        stringStack.push("India");
        stringStack.push("Australia");
        stringStack.push("China");
        stringStack.push("Germany");
        stringStack.push("Japan");
        stringStack.push("Indonesia");

        stringStack.peek();


        stringStack.search("China");
        stringStack.search("England");
        stringStack.search("Indonesia");
    }

}


@SuppressWarnings({"unchecked", "rawtypes"})
class Stack<T> extends SingleLinkedList {

    protected void push(T data) {
        System.out.println("\nPushed data into stack: " + data.toString());
        addAtFront(data);
        System.out.println("Stack now: ");
        printList();
    }

    protected void peek() {
        System.out.println("\nPeek into the stack: " + getStart());
    }

    protected void pop() {
        removeFromEnd();
        System.out.println("\nAfter Pop Operation\nStack now: ");
        printList();
    }

    protected void search(T data) {
        searchElement(data);
    }

}


@SuppressWarnings({"unchecked", "rawtypes"})
class SingleLinkedList<T> {

    private node start = null;
    private node end = null;
    private int size = 0;

    protected void addAtFront(T data) {
        node p = new node(data);
        if (start == null) {
            p.setNext(null);
            start = p;
            end = p;
        } else {
            p.setNext(start);
            start = p;
        }
        size++;
    }

    protected void removeFromEnd() {
        if (start == null)
            System.out.println("List is empty, hence cannot delete any info.");
        else if (start.getNext() == null) {
            start = null;
            end = null;
        } else {
            node q = start;
            while (q.getNext().getNext() != null) {
                q = q.getNext();
            }
            q.setNext(null);
            end = q;
        }
        size--;
    }

    protected String getStart() {
        return start.getData().toString();
    }

    protected void searchElement(T data) {
        node t = start;
        int index = 0;
        int item_count = 0;
        while (t != null) {
            if (t.getData() == data) {
                System.out.println("\nData: " + data + " found in stack at " + ((index + 1 > 1) ? (index + 1) + " below from top" : "the top"));
                item_count++;
            }
            t = t.getNext();
            index++;
        }
        if (item_count == 0)
            System.out.println("Data: " + data + " not found in stack");
    }

    protected void printList() {
        node p = start;
        while (p != null) {
            System.out.println("|__" + p.toString() + "__|");
            p = p.getNext();
        }
        System.out.print("end");
    }

}


@SuppressWarnings({"rawtypes"})
class node<T> {
    private T data;
    private node next;

    public node(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public node getNext() {
        return next;
    }

    public void setNext(node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "{" +
                "data=" + data +
                '}';
    }
}
