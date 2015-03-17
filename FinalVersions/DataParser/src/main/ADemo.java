package main;

public class ADemo {

	public static void main(String[] args)
	{
		int myint = 4;
		if (myint == 4)
		{
			System.err.print("Y");
		}
		Integer i = Integer.valueOf(4);
		if (myint == i)
		{
			System.err.print("e");
		}
		Integer other = new Integer(4);
		if (other == i)
		{
			System.err.println("s");
		}
		if (other == i)
		{
			System.err.println("s?");
		}
		Integer other2 = Integer.valueOf(4);
		if (other2 == i)
		{
			System.err.println("s!");
		}
		Integer other3 = new Integer(4);
		if (other3 == other2)
		{
			System.err.println("s#");
		}

		
	}
	
}
