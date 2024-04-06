package src.textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		head = new LLNode<>(null);
		tail = new LLNode<>(null);
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element)
	{
		LLNode<E> newNode = new LLNode<>(element);
		appendItem(newNode, tail.prev);
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		LLNode node = getNode(index);

		if (node == null)
			return null;

		return (E)node.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element)
	{
		LLNode<E> newNode = new LLNode<>(element);
		LLNode<E> node = getNode(index);
		appendItem(newNode, node.prev);
	}


	/** Return the size of the list */
	public int size() 
	{
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		LLNode<E> node = getNode(index);
		if (node == null)
			return null;

		E oldData = node.data;
		node.prev.next = node.next;
		node.next.prev = node.prev;
		--size;
		return oldData;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		LLNode<E> node = getNode(index);
		if (node == null)
			return null;

		E oldData = node.data;
		node.data = element;
		return oldData;
	}

	private LLNode<E> getNode(int index)
	{
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		}

		LLNode<E> pointer = head.next;

		while (pointer.next != null && index > 0) {
			pointer = pointer.next;
			--index;
		}

        return pointer;
    }

	private void appendItem(LLNode<E> newNode, LLNode<E> prev)
	{
		newNode.prev = prev;
		newNode.next = prev.next;
		prev.next = newNode;
		newNode.next.prev = newNode;
		++size;
	}
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	public LLNode(E e)
	{
		this(e, null, null);
	}

	public LLNode(E e, LLNode<E> prev, LLNode<E> next) {
		this.data = e;
		this.prev = prev;
		this.next = next;
	}
}
