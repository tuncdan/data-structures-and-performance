/**
 * 
 */
package src.textgen;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
	}

	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		try {
			list1.remove(3);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {

		}

		try {
			list1.remove(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {

		}
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
		boolean result = list1.add(1);
		assertEquals("Add: check result is correct ", true, result);
		assertEquals("Add: check element is correct ", (Integer)1, list1.get(3));
		assertEquals("Add: check size is correct ", 4, list1.size());

		try {
			list1.add(null);
			fail("Check null element");
		}
		catch (NullPointerException e) {

		}
	}

	/** Test the size of the list */
	@Test
	public void testSize()
	{
		assertEquals("Size: check size is correct ", 3, list1.size());
		list1.add(1);
		assertEquals("Size: check size is correct ", 4, list1.size());
		list1.remove(0);
		assertEquals("Size: check size is correct ", 3, list1.size());
		list1.remove(1);
		assertEquals("Size: check size is correct ", 2, list1.size());
	}

	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
		list1.add(1, 1);
		assertEquals("Add: check element is correct ", (Integer)1, list1.get(1));
		assertEquals("Add: check next element is correct ", (Integer)21, list1.get(2));
		assertEquals("Add: check size is correct ", 4, list1.size());

		try {
			list1.add(1, null);
			fail("Check null element");
		}
		catch (NullPointerException e) {

		}

		try {
			list1.add(-1, 1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {

		}

		try {
			list1.add(5, 1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {

		}
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    Integer result = list1.set(0, 1);
		assertEquals("Set: check result is correct ", 65, result.intValue());
	    Integer newValue = list1.get(0);
		assertEquals("Set: check new value is correct ", 1, newValue.intValue());
		Integer nextValue = list1.get(1);
		assertEquals("Set: check next value is correct ", 21, nextValue.intValue());
		Integer lastValue = list1.get(2);
		assertEquals("Set: check last value is correct ", 42, lastValue.intValue());

		try {
			list1.set(-1, 1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {

		}

		try {
			list1.set(3, 1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {

		}
	}

//	@Test
//	public void testGetNode()
//	{
//		LLNode<Integer> firstNode =  list1.getNode(0);
//		assertEquals("Check data", (Integer)65, firstNode.data);
//		assertEquals("First node should be pointed by head node", list1.head, firstNode.prev);
//
//		LLNode<Integer> lastNode =  list1.getNode(2);
//		assertEquals("Check data", (Integer)42, lastNode.data);
//		assertEquals("Last node should point to tail node", list1.tail, lastNode.next);
//
//		LLNode<Integer> middleNode =  list1.getNode(1);
//		assertEquals("Check data", (Integer)21, middleNode.data);
//		assertEquals("Middle node should pointed by first node", firstNode.next, middleNode);
//		assertEquals("Middle node should point to last node", middleNode.next, lastNode);
//		assertEquals("Middle node should point to first node", middleNode.prev, firstNode);
//		assertEquals("Last node should point to middle node", lastNode.prev, middleNode);
//
//		try {
//			list1.getNode(-1);
//			fail("Check out of bounds");
//		}
//		catch (IndexOutOfBoundsException e) {
//
//		}
//
//		try {
//			list1.getNode(3);
//			fail("Check out of bounds");
//		}
//		catch (IndexOutOfBoundsException e) {
//
//		}
//	}
}
