package javaBasic;

import java.util.Scanner;

import org.testng.annotations.Test;

public class Topic_07_Schwitch_Case {
	Scanner scanner = new Scanner (System.in);
	
//	@Test
	public void TC_01_(){
		
		int number = scanner.nextInt();
		
		switch (number) {
		case 1 :
			System.out.println("one");
			break;
		case 2 :
			System.out.println("two");
			break;
		case 3 :
			System.out.println("three");
			break;
		case 4 :
			System.out.println("four");
			break;
		case 5 :
			System.out.println("five");
			break;
		case 6 :
			System.out.println("six");
			break;
		case 7 :
			System.out.println("seven");
			break;
		case 8 :
			System.out.println("eight");
			break;
		case 9 :
			System.out.println("nine");
			break;
		case 10 :
			System.out.println("ten");
			break;
		default:
			System.out.println("please enter a valid value");
			break;
		}
		
		
		
	}
	
	
	
	@Test
	public void TC_02_() {
		int month = scanner.nextInt();
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 10:
		case 12:
			System.out.println(" thang nay co 31 ngay ");
			break;
		case 2: 
			System.out.println(" thang nay co 28 ngay ");
			break;
		 
		case 4 : 
		case 6 : 
		case 9 : 
		case 11 : 
			System.out.println(" thang nay co 30 ngay ");
			break;	
		default:
			System.out.println(" please enter a valid value ");
			break;
		}
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
