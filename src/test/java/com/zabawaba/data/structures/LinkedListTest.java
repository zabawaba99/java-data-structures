package com.zabawaba.data.structures;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

import com.zabawaba.data.strcutures.LinkedList;

public class LinkedListTest {

	@Test
	public void testAdd() {
		LinkedList<Integer> l = new LinkedList<>();
		l.add(1);

		assertThat(l.get(0), is(1));
	}

	@Test
	public void testAddIndex() {
		LinkedList<Integer> l = new LinkedList<>();
		l.add(1);
		assertThat(l.getFirst(), is(1));
		assertThat(l.getLast(), is(1));
		l.add(2);
		assertThat(l.get(1), is(2));
		assertThat(l.getLast(), is(2));

		l.add(0, 32);
		assertThat(l.getFirst(), is(32));
		assertThat(l.get(1), is(1));
		l.add(2, 44);
		assertThat(l.getLast(), is(44));
		l.add(22);
		assertThat(l.getLast(), is(22));
	}

	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testAddIndexOutOfBounds() {
		LinkedList<Integer> l = new LinkedList<>();
		l.add(1234567, 1);
	}

	@Test
	public void testAddAll() {
		LinkedList<Integer> l = new LinkedList<>();
		l.add(1);

		assertThat(l.get(0), is(1));

		ArrayList<Integer> c = new ArrayList<>();
		c.add(123);
		c.add(222);
		l.addAll(c);

		assertThat(l.size(), is(3));
		assertThat(l.get(1), is(123));
		assertThat(l.get(2), is(222));
	}

	@Test
	public void testClear() {
		LinkedList<Integer> l = new LinkedList<>();
		l.add(32);
		l.add(1);
		l.add(144);

		l.clear();
		assertThat(l.size(), is(equalTo(0)));

		try {
			l.get(1);
			fail("Should have received ArrayIndexOutOfBoundsException");
		} catch(ArrayIndexOutOfBoundsException e) {

		}

		assertThat(l.getFirst(), is(nullValue()));
		assertThat(l.getLast(), is(nullValue()));
	}

	@Test
	public void testIterator() {
		ArrayList<Integer> collection = new ArrayList<>();
		collection.add(312);
		collection.add(222);
		collection.add(987654);

		LinkedList<Integer> l = new LinkedList<>();
		for(int i = 0; i < collection.size(); i++)
			l.add(collection.get(i));

		Iterator<Integer> itr = l.iterator();
		for(int i = 0; i < collection.size(); i++) {
			assertThat(itr.hasNext(), is(equalTo(true)));
			assertThat(itr.next(), is(equalTo(collection.get(i))));
		}
		assertThat(itr.hasNext(), is(equalTo(false)));
	}

	@Test
	public void testReverseIterator() {
		ArrayList<Integer> collection = new ArrayList<>();
		collection.add(312);
		collection.add(222);
		collection.add(987654);

		LinkedList<Integer> l = new LinkedList<>();
		for(int i = 0; i < collection.size(); i++)
			l.add(collection.get(i));

		Iterator<Integer> itr = l.reverseIterator();
		for(int i = collection.size() - 1; i >= 0; i--) {
			assertThat(itr.hasNext(), is(equalTo(true)));
			assertThat(itr.next(), is(equalTo(collection.get(i))));
		}
		assertThat(itr.hasNext(), is(equalTo(false)));
	}

	@Test
	public void testRemove() {
		LinkedList<Integer> l = new LinkedList<>();
		l.add(32);
		l.add(1);
		l.add(222);
		l.add(144);

		l.remove(0);
		assertThat(l.size(), is(equalTo(3)));
		assertThat(l.getFirst(), is(equalTo(1)));

		l.remove(2);
		assertThat(l.size(), is(equalTo(2)));
		assertThat(l.getLast(), is(equalTo(222)));
	}

	@Test
	public void testToArray() {
		ArrayList<Object> collection = new ArrayList<>();
		collection.add(312);
		collection.add(222);
		collection.add(987654);

		LinkedList<Object> l = new LinkedList<>();
		for(int i = 0; i < collection.size(); i++)
			l.add(collection.get(i));

		Object[] arr = l.toArray();
		assertThat(arr.length, is(equalTo(collection.size())));
		for(int i = 0; i < collection.size(); i++) {
			assertThat(arr[i], is(equalTo(collection.get(i))));
		}
	}

}
