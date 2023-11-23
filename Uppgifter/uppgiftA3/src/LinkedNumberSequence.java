// LinkedNumberSequence.java

/****************************************************************

LinkedNumberSequence represents a sequence of real numbers.
Such a sequence is defined by the interface NumberSequence.
The class uses linked nodes to store the numbers in the sequence.

Author
Fadil Galjic

****************************************************************/

public class LinkedNumberSequence implements NumberSequence
{
	private class Node
	{
		public double number;
		public Node next;

		public Node (double number)
		{
			this.number = number;
			next = null;
		}
	}

	// the first node in the node-sequence
    private Node first;

    // create the sequence
    public LinkedNumberSequence (double[] numbers)
    {
		if (numbers.length < 2)
		    throw new IllegalArgumentException("not a sequence");

        first = new Node(numbers[0]);
        Node n = first;
		for (int i = 1; i < numbers.length; i++)
		{
			n.next = new Node(numbers[i]);
			n = n.next;
		}
	}

    // toString returns the character string representing this
    // sequence
	public String toString ()
	{
		String s = "";
		Node n = first;
		while (n.next != null)
		{
		    s = s + n.number + ", ";
		    n = n.next;
		}
		s = s + n.number;

		return s;
	}

    // add code here
	@Override
	public int length() {
		int len = 0;
		Node n = first;
		while(n != null) {
			len++;
			n = n.next;
		}
		return len;
	}

	@Override
	public double upperBound() {
		Node n = first;
		double upper = n.number;
		while(n.next != null) {
			upper = Math.max(n.number, n.next.number);
			n = n.next;
		}
		return upper;
	}

	@Override
	public double lowerBound() {
		Node n = first;
		double lower = n.number;
		while(n.next != null) {
			lower = Math.min(n.number, n.next.number);
			n = n.next;
		}
		return lower;
	}

	@Override
	public double numberAt(int position) throws IndexOutOfBoundsException {
		if (position < 0 || position >= this.length())
			throw new IndexOutOfBoundsException("Index not available");
		Node n = first;
		for(int i = 0; i < position-1; i++) {
			if (n == null)
				throw new IndexOutOfBoundsException("Index not available");
			n = n.next;
		}
		return n.number;
	}

	@Override
	public int positionOf(double number) {
		Node n = first;
		int foundIndex = -1;
		int searchIndex = 0;

		while (n != null) {
			if (n.number == number) {
				foundIndex = searchIndex;
			}
			searchIndex++;
			n = n.next;
		}
		return foundIndex;
	}

	@Override
	public boolean isIncreasing() {
		Node n = first;
		boolean increasing = true;
		while (n.next != null) {
			if(n.next.number < n.number) {
				increasing = false;
				break;
			}
			n = n.next;
		}
		return increasing;
	}

	@Override
	public boolean isDecreasing() {
		Node n = first;
		boolean decreasing = true;
		while (n.next != null) {
			if(n.next.number > n.number) {
				decreasing = false;
				break;
			}
			n = n.next;
		}
		return decreasing;
	}

	@Override
	public boolean contains(double number) {
		Node n = first;
		boolean containsNumber = false;
		while (n != null) {
			if(n.number == number) {
				containsNumber = true;
				break;
			}
			n = n.next;
		}
		return containsNumber;
	}

	@Override
	public void add(double number) {
		Node n = first;
		while (n.next != null) n = n.next;
		n.next = new Node(number);
	}

	@Override
	public void insert(int position, double number) throws IndexOutOfBoundsException {
		if (position < 0 || position >= this.length()) {
			throw new IndexOutOfBoundsException("Index not available.");
		}
		Node node = new Node(number);
		if (position == 0) {
			node.next = first;
			first = node;
		}
		else {
			Node n = first;
			for (int i = 1; i < position; i++)
				n = n.next;
			node.next = n.next;
			n.next = node;
		}
	}

	@Override
	public void removeAt(int position) throws IndexOutOfBoundsException, IllegalStateException {
		if (position < 0 || position >= this.length()) {
			throw new IndexOutOfBoundsException("Index not available.");
		}
		Node n = first;
		if(n.next.next == null) {
			throw new IllegalStateException("Only two numbers in sequence.");
		}
		for (int i = 1; i < position; i++)
			n = n.next;
		n.next = n.next.next;
		n.next.next = null;
	}

	@Override
	public double[] asArray() {
		double [] numArray = new double[length()];
		int index = 0;
		Node n = first;
		while (n != null) {
			numArray[index] = n.number;
			n = n.next;
			index++;
		}
		return numArray;
	}
}