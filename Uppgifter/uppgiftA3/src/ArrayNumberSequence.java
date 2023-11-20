// ArrayNumberSequence.java

/****************************************************************

ArrayNumberSequence represents a sequence of real numbers.
Such a sequence is defined by the interface NumberSequence.
The class uses an array to store the numbers in the sequence.

Author
Fadil Galjic

****************************************************************/

public class ArrayNumberSequence implements NumberSequence
{
	// numbers in the sequence
    private double[] numbers;

    // create the sequence
    public ArrayNumberSequence (double[] numbers)
    {
		if (numbers.length < 2)
		    throw new IllegalArgumentException("not a sequence");

		this.numbers = new double[numbers.length];
		for (int i = 0; i < numbers.length; i++)
		    this.numbers[i] = numbers[i];
	}

    // toString returns the character string representing this
    // sequence
	public String toString()
	{
		String s = "";
		for (int i = 0; i < numbers.length - 1; i++)
		    s = s + numbers[i] + ", ";
		s = s + numbers[numbers.length - 1];

		return s;
	}

    // add code here
	@Override
	public int length() {
		return numbers.length;
	}

	@Override
	public double upperBound() {
		double upper = numbers[0];
		for (int i = 1; i < numbers.length; i++) {
			upper = Math.max(numbers[i], upper);
		}
		return upper;
	}

	@Override
	public double lowerBound() {
		double lower = numbers[0];
		for (int i = 1; i < numbers.length; i++) {
			lower = Math.min(numbers[i], lower);
		}
		return lower;
	}

	@Override
	public double numberAt(int position)
			throws IndexOutOfBoundsException {
		if (position >= numbers.length || position < 0)
			throw new IndexOutOfBoundsException("Index not available.");
		return numbers[position];
	}

	@Override
	public int positionOf(double number) {
		int pos = -1;
		for (int i = 0; i < numbers.length; i++) {
			if(numbers[i] == number) {
				pos = i;
				break;
			}
		}
		return pos;
	}

	@Override
	public boolean isIncreasing() {
		boolean increasing = true;
		for (int i = 0; i < numbers.length - 1; i++) {
			if (numbers[i+1] < numbers[i]) {
				increasing = false;
				break;
			}
		}
		return increasing;
	}

	@Override
	public boolean isDecreasing() {
		boolean decreasing = true;
		for (int i = 0; i < numbers.length - 1; i++) {
			if (numbers[i+1] > numbers[i]) {
				decreasing = false;
				break;
			}
		}
		return decreasing;
	}

	@Override
	public boolean contains(double number) {
		boolean containsNumber = false;
		for (double n : numbers) {
            if (n == number) {
                containsNumber = true;
                break;
            }
		}
		return containsNumber;
	}

	@Override
	public void add(double number) {
		double[] newSequence = new double[numbers.length + 1];
		System.arraycopy(numbers, 0, newSequence, 0, numbers.length);
		newSequence[numbers.length] = number;
		this.numbers = newSequence;
	}

	@Override
	public void insert(int position, double number)
			throws IndexOutOfBoundsException {
		if(position >= numbers.length || position < 0)
			throw new IndexOutOfBoundsException("Index not available.");
		double[] newSequence = new double[numbers.length + 1];
		int numIndex = 0;
		for (int i = 0; i < newSequence.length; i++) {
			if (i == position) {
				newSequence[i] = number;
				continue;
			}
			newSequence[i] = numbers[numIndex];
			numIndex++;
		}
		this.numbers = newSequence;
	}

	@Override
	public void removeAt(int position)
			throws IndexOutOfBoundsException, IllegalStateException {
		if (numbers.length == 2)
			throw new IllegalStateException("Only 2 numbers in sequence.");
		if (position >= numbers.length ||position < 0)
			throw new IndexOutOfBoundsException("Index not available.");
		double[] newSequence = new double[numbers.length - 1];
		int index = 0;
		for (int i = 0; i < numbers.length; i++) {
			if (i == position) continue;
			newSequence[index++] = numbers[i];
		}
		this.numbers = newSequence;
	}

	@Override
	public double[] asArray() {
		return numbers;
	}
}