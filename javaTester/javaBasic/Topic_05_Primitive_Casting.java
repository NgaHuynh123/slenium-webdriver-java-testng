package javaBasic;

public class Topic_05_Primitive_Casting {

	public static void main(String[] args) {
//	implicit: ngam dinh . ko mat du lieu
//		byte bNumber = 126;
//		System.out.println(bNumber);
//		short sNumber = bNumber;
//		System.out.println(sNumber);
//		int iNumber = sNumber;
//		System.out.println(iNumber);
//		long lNumber = iNumber;
//		System.out.println(lNumber);
//		float fNumber = lNumber;
//		System.out.println(fNumber);
//		double dNumber = fNumber;
//		System.out.println(dNumber);
//		explicit: tuong minh. mat data
		
		
		double dNumber = 654215956666667788f;
		System.out.println(dNumber);
		int iNumber =  (int) dNumber;
		
		System.out.println(iNumber);
		float fNumber = (float) dNumber;
		System.out.println(fNumber);
		

	}

}
