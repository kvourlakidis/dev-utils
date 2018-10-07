import java.text.ParseException;

public class BigInteger {
	static final int INITIAL_LENGTH = 10;
	static final char NEGATION_CHAR = '-';
	static final char SPACE = ' ';
	static char INIT_CHAR = '0';
	static final char[] LEGAL_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'}; 

	private boolean isNegative;
	private char[] container;

	private int length() {
		int l = container.length;
		char lastChar = container[l-1];
		while (lastChar == '0') {
			l--;
			lastChar = container[l-1];
		}	
		return l;
	}

	private int getIntByIndex(int index) {
		if (index < length())
			return parseCharAsInt(container[index]);
		else
			return 0;
	}

	private static int parseCharAsInt(char c) {
		switch (c) {
			case('0'):
				return 0;
			case('1'):
				return 1;
			case('2'):
				return 2;
			case('3'):
				return 3;
			case('4'):
				return 4;
			case('5'):
				return 5;
			case('6'):
				return 6;
			case('7'):
				return 7;
			case('8'):
				return 8;
			case('9'):
				return 9;
			default:
				throw new RuntimeException();
		}
	}

	private char parseIntAsChar(int i) {
		switch (i) {
			case(0):
				return '0';
			case(1):
				return '1';
			case(2):
				return '2';
			case(3):
				return '3';
			case(4):
				return '4';
			case(5):
				return '5';
			case(6):
				return '6';
			case(7):
				return '7';
			case(8):
				return '8';
			case(9):
				return '9';
			default:
				throw new RuntimeException();
		}
	}

	private static boolean isLegalChar(char test) {
		for (char legal : LEGAL_CHARS)
			if (test == legal) 
				return true;	
		return false;	
	}

	private static char parseChar(char in) throws ParseException {
		if (isLegalChar(in))
			return in;
		throw new ParseException("cannot parse " + Character.toString(in), 1);	
	}
	
	public BigInteger() {
		container = new char[INITIAL_LENGTH];	
		isNegative = false;
	}

	public BigInteger(int size){
		container = new char[size];
		isNegative = false;	
	}

	public BigInteger(String s) throws ParseException {
		if (s.charAt(0) == NEGATION_CHAR) {
			container = new char[s.length() - 1];
			isNegative = true;	

			for (int i=1; i < s.length(); i++) {
				container[i - 1] = parseChar(s.charAt(i));
			}
		} else {
			container = new char[s.length()];
			isNegative = false;

			for (int i=0; i < s.length(); i++) {
				container[i] = parseChar(s.charAt(i));	
			}
		}	
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (isNegative)
			sb.append(NEGATION_CHAR);
		for (char c : container) 
			sb.append(c);
		return sb.toString(); 
	}

	public static void main(String[] args) {
		try {
			BigInteger x1 = new BigInteger();
			BigInteger x2 = new BigInteger("1234567890");
			BigInteger x3 = new BigInteger("-1234567789");		
			System.out.println(x1);
			System.out.println(x2);
			System.out.println(x3);
			x1.add(x2);
			System.out.println(x1);
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
	}

	public void add(BigInteger other) {
		int carryOn = 0;
		char[] result;
		if (other.length() >= this.length()) {
			int length = other.length() + 1;
			result = new char[length];
		} else {
			int length = this.length() + 1;
			result = new char[length];
		}
		for (int i=0; i<other.length(); i++) {
			int a = this.getIntByIndex(i);
			int b = other.getIntByIndex(i);
			int c = a + b + carryOn;
			if (c > 9) {
				carryOn = 1;
				c = c - 10;
			} else {
				carryOn = 0;
			}
			result[i] = parseIntAsChar(c);
		}
		container = result;
	}
}
