package Data_Structures.LinkedList;

/*
Write a java program for a Generic Single-LinkedList Implementation.
*/
/*
Author: Sayantan Paul
*/
@SuppressWarnings({"rawtypes"})
public class Gen_LinkedList extends SingleLinkedList {
    public static void main(String[] args) {
        SingleLinkedList<String> country = new SingleLinkedList<>();

        country.addAtFront("India");
        country.addAtFront("USA");
        country.addAtFront("Brazil");
        country.addAtFront("Japan");
        country.addAtFront("Canada");
        country.addAtFront("China");
        country.addAtEnd("Florida");
        country.addAtEnd("Pakistan");

        country.printList();
        System.out.println();
        System.out.println("Removing From End.");
        country.removeFromEnd();
        country.printList();
        System.out.println();
        System.out.println("Removing From Beginning.");
        country.removeFromBeg();
        country.printList();
        System.out.println();
        System.out.println("Adding Germany at position 3");
        country.addAt_Pos("Germany", 3);
        country.printList();
        System.out.println();
        System.out.println("Removing from position 4");
        country.removeFrom_pos(4);
        country.printList();


    }
}

@SuppressWarnings({"rawtypes", "unchecked"})
class SingleLinkedList<T extends Comparable<T>> {

    private node start = null;
    private node end = null;

    public void addAtFront(T data) {
        node p = new node(data);
        if (start == null) {
            p.setNext(null);
            start = p;
            end = p;
        } else {
            p.setNext(start);
            start = p;
        }
    }

    public void addAtEnd(T data) {
        node p = new node(data);
        p.setNext(null);

        if (start == null) {
            System.out.println("List is empty. So, inserting at the beginning.... ");
            start = p;
            end = p;
        } else {
            end.setNext(p);
            end = p;
        }
    }

    public void addAt_Pos(T data, int pos) {
        node p = new node(data);
        int count = 0;
        node t = start;
        while (t.getNext() != null) {
            count++;
            t = t.getNext();
        }

        if (pos > count + 1) {
            System.out.println("Position is out of range.");
        } else if (pos == 1) {
            addAtFront(data);
        } else if (pos == count + 1) {
            addAtEnd(data);
        } else {
            node q = start;
            int i = 1;
            while (i <= pos - 2) {
                q = q.getNext();
                i++;
            }
            p.setNext(q.getNext());
            q.setNext(p);
        }
    }

    public void removeFromBeg() {
        if (start == null)
            System.out.println("List is empty, hence cannot delete any info.");
        else if (start.getNext() == null) {
            start = null;
            end = null;
        } else {
            node q = start;
            start = start.getNext();
            q.setNext(null);
        }
    }

    public void removeFromEnd() {
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
    }

    public void removeFrom_pos(int pos) {
        if (start == null)
            System.out.println("List is empty, hence cannot delete any info.");
        else if (start.getNext() == null) {
            start = null;
            end = null;
        } else {
            int count = 0;
            node t = start;

            while (t.getNext() != null) {
                count++;
                t = t.getNext();
            }
            if (pos > count)
                System.out.println("Position Out Of Range. Cannot delete info.");
            else if (pos == 1)
                removeFromBeg();
            else if (pos == count)
                removeFromEnd();
            else {

                t = start;
                int i = 1;
                while (i < pos - 1) {
                    t = t.getNext();
                    i++;
                }
                t.setNext(t.getNext().getNext());
            }
        }
    }

    public void printList() {
        node p = start;
        while (p != null) {
            System.out.print(p.toString() + " --> ");
            p = p.getNext();
        }
        System.out.print("end-of-list");
    }
}

@SuppressWarnings({"rawtypes"})
class node<T extends Comparable<T>> {
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
