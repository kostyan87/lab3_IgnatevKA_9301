package Map;

import data_structures.LinkedList;
import data_structures.Stack;

import java.util.Objects;

public class RBTreeMap<K extends Comparable<K>, V> implements Map<K, V> {

    public Node<K, V> root = Node.nil;

    public RBTreeMap() {
        Node.nil.color = true;
    }

    @Override
    public boolean isEmpty() {
        return  (root == Node.nil);
    }

    @Override
    public int getSize() {
        int size = 0;
        RBTreeMap.Node<K, V> current = root;
        Stack<Node> stack = new Stack();

        if (isEmpty()) return 0;

        stack.push(current);

        while (stack.getSize() > 0)
        {
            current = stack.pop();
            size++;
            if (current.rightChild != RBTreeMap.Node.nil) stack.push(current.rightChild);
            if (current.leftChild != RBTreeMap.Node.nil) stack.push(current.leftChild);
        }

        return size;
    }

    @Override
    public void insert(K key, V value) {
        Node<K, V> z = new Node<K, V>(key, value);
        Node<K, V> y = Node.nil;
        Node<K, V> x = root;

        while (x != Node.nil) {
            y = x;
            if (z.key.compareTo(x.key) < 0)
                x = x.leftChild;
            else
                x = x.rightChild;
        }
        z.parent = y;
        if (y == Node.nil)
            root = z;
        else if (z.key.compareTo(y.key) < 0)
            y.leftChild = z;
        else y.rightChild = z;
        z.leftChild = Node.nil;
        z.rightChild = Node.nil;
        z.color = false;
        RBTreeUtils.fixedUp(this, z);
    }

    @Override
    public void remove(K key) {
        Node<K, V> z = find(key);
        Node x;
        Node y = z;
        boolean originalColor = y.color;

        if (z.leftChild == Node.nil) {
            x = z.rightChild;
            RBTreeUtils.transplant(this, z, z.rightChild);
        }
        else if (z.rightChild == Node.nil) {
            x = z.leftChild;
            RBTreeUtils.transplant(this, z, z.leftChild);
        }
        else {
            y = RBTreeUtils.treeMin(z.rightChild);
            originalColor = y.color;
            x = y.rightChild;

            if (y.parent == z)
                x.parent = y;
            else {
                RBTreeUtils.transplant(this, y, y.rightChild);
                y.rightChild = z.rightChild;
                y.rightChild.parent = y;
            }

            RBTreeUtils.transplant(this, z, y);
            y.leftChild = z.leftChild;
            y.leftChild.parent = y;
            y.color = z.color;
        }

        if (originalColor == true)
            RBTreeUtils.deleteFixup(this, x);
    }

    @Override
    public RBTreeMap.Node<K, V> find(K key) {
        Node<K, V> x = root;
        while (x != Node.nil)
        {
            if (x.key == key) return x;
            if (x.key.compareTo(key) > 0) x = x.leftChild;
            else x = x.rightChild;
        }
        throw new RuntimeException("There is no element with such a key in map");
    }

    @Override
    public void clear() {
        while (root != Node.nil) remove(root.key);
    }

    @Override
    public LinkedList<K> getKeys() {
        LinkedList<K> keysArray = new LinkedList<>();
        RBTreeMap.Node<K, V> current = root;
        Stack<Node<K, V>> stack = new Stack();

        stack.push(current);

        while (stack.getSize() > 0)
        {
            current = stack.pop();
            keysArray.pushBack(current.key);
            if (current.rightChild != RBTreeMap.Node.nil) stack.push(current.rightChild);
            if (current.leftChild != RBTreeMap.Node.nil) stack.push(current.leftChild);
        }

        return keysArray;
    }

    @Override
    public LinkedList<V> getValues() {
        LinkedList<V> valuesArray = new LinkedList<>();
        RBTreeMap.Node<K, V> current = root;
        Stack<Node> stack = new Stack();

        stack.push(current);

        while (stack.getSize() > 0)
        {
            current = stack.pop();
            valuesArray.pushBack(current.value);
            if (current.rightChild != RBTreeMap.Node.nil) stack.push(current.rightChild);
            if (current.leftChild != RBTreeMap.Node.nil) stack.push(current.leftChild);
        }

        return valuesArray;
    }

    @Override
    public boolean containsKey(K key) {
        Node<K, V> x = root;
        while (x != Node.nil)
        {
            if (x.key == key) return true;
            if (x.key.compareTo(key) > 0) x = x.leftChild;
            else x = x.rightChild;
        }
        return false;
    }

    @Override
    public void printMap() {
        System.out.println(RBTreeUtils.mapToString(this));
    }

    public static class Node<K, V> {
        public V value;
        public final K key;
        public boolean color; // true - (black) or false - (red)
        public Node<K, V> leftChild;
        public Node<K, V> rightChild;
        public Node<K, V> parent;
        public static Node nil = new Node(null, null);

        public Node(K key, V value) {
            this.color = false;
            this.key = key;
            this.value = value;
            this.leftChild = nil;
            this.rightChild = nil;
            this.parent = nil;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    ", color=" + color +
                    '}';
        }
    }
}
