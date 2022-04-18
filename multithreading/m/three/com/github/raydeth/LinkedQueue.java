package com.github.raydeth;

import java.util.AbstractQueue;
import java.util.Iterator;

public class LinkedQueue<T> extends AbstractQueue<T> {

    private class Node {
        T item;
        Node next;
    }

    private volatile Node first;
    private volatile Node last;
    private volatile int size;

    @Override
    public synchronized boolean isEmpty() {
        return size == 0;
    }

    @Override
    public synchronized int size() {
        return size;
    }

    @Override
    public synchronized boolean offer(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }

        Node oldLast = last;

        last = new Node();
        last.item = item;
        last.next = null;

        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }

        size++;

        return true;
    }

    @Override
    public synchronized T poll() {
        if (isEmpty()) {
            throw new RuntimeException("Queue underflow");
        }

        T item = first.item;
        first = first.next;

        size--;

        if (isEmpty()) {
            last = null;
        }

        return item;
    }

    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue underflow");
        }

        return first.item;
    }

    public Iterator<T> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<T> {

        private volatile Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T item;
            synchronized (current) {
                item = current.item;
                current = current.next;
            }
            return item;
        }
    }
}
