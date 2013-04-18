package examples.unittesting;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

public class Stack {
	List<Integer> elements = new ArrayList<Integer>();

	public boolean isEmpty() {
		return elements.size() == 0;
	}

	public void push(int element) {
		elements.add(0, element);
	}

	public int pop() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		int element = elements.get(0);
		elements.remove(0);
		return element;
	}
}
