package com.zabawaba.data.strcutures;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class LinkedList<E> implements Iterable<E>{

	public class Node {
		public E value;
		public Node next;

		public Node(E value) {
			this.value = value;
		}
	}

	private Node head;
	private Node tail;
	private int size;

	public void add(E value) {
		Node toAdd = new Node(value);

		if (head == null) {
			head = toAdd;
		}

		if (tail != null) {
			tail.next = toAdd;
		}

		tail = toAdd;
		size++;
	}

	public void add(int index, E e) {
		if (index < 0 || index > size) {
			throw new ArrayIndexOutOfBoundsException(index);
		}

		Node current = head;
		for(int i = 0; i < index; i++) {
			current = current.next;
		}

		Node replace = new Node(e);
		if (current != null) {
			replace.next = current;
			current.next = replace;
		}

		if (index == size-1) {
			tail = replace;
		}
		if (index == 0) {
			head = replace;
		}
		size++;
	}

	public void addAll(Collection<E> c) {
		Iterator<E> itr = c.iterator();
		while(itr.hasNext())
			add(itr.next());
	}

	public void clear() {
		Node next = head;
		Node previous = head;
		while(next != null) {
			previous = next;
			next = previous.next;

			previous.next = null;
			previous = null;
		}

		head = null;
		tail = null;

		size = 0;
	}

	public boolean contains(E e) {
		Node current = head;
		while(current != null) {
			if (current.value == e) {
				return true;
			}
			current = current.next;
		}

		return false;
	}

	@Override
	public Iterator<E> iterator() {
		return createIterator(false);
	}

	public Iterator<E> reverseIterator() {
		return createIterator(true);
	}

	private Iterator<E> createIterator(final boolean reverse) {
		final ArrayList<E> values = new ArrayList<>();
		Node n = head;
		while(n != null) {
			values.add(n.value);
			n = n.next;
		}
		return new Iterator<E>() {
			private int index = reverse ? values.size()-1 : 0;

			@Override
			public boolean hasNext() {
				if (reverse) {
					return index >= 0;
				}
				return index < values.size();
			}

			@Override
			public E next() {
				E e = values.get(index);
				if (reverse) {
					index--;
				} else {
					index++;
				}
				return e;
			}

			@Override
			public void remove() {
				LinkedList.this.remove(index);
			}
		};
	}

	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new ArrayIndexOutOfBoundsException(index);
		}

		Node current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current.value;
	}

	public E getFirst() {
		if (head == null) {
			return null;
		}
		return head.value;
	}

	public E getLast() {
		if (tail == null){
			return null;
		}
		return tail.value;
	}

	public E remove(int index) {
		if (index < 0 || index >= size) {
			throw new ArrayIndexOutOfBoundsException(index);
		}

		Node current = head;
		Node previous = head;
		for (int i = 0; i < index; i++) {
			previous = current;
			current = current.next;
		}

		if (previous != null)
			previous.next = current.next;

		if (index == size-1)
			tail = previous;

		if (index == 0)
			head = current.next;

		size--;
		return current.value;
	}

	//toArray
	public Object[] toArray() {
		Object[] arr = new Object[size];

		Node current = head;
		int index = 0;
		while (current != null) {
			arr[index] = current.value;
			current = current.next;
			index++;
		}

		return arr;
	}

	public int size() {
		return size;
	}
}
