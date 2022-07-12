package javaBasic;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Topic_06_Condition_Statement_Exercise {

	WebDriver driver;
	Scanner scanner = new Scanner(System.in);

//	@Test
	public void TC_01() {

		int number = scanner.nextInt();
//		int afterNumber = number % 2;
//		System.out.println(afterNumber);
//		
//		boolean status = afterNumber == 0;
//		System.out.println(status);

		if (number % 2 == 0) {
			System.out.println("so: " + number + " la so chan");
		} else {
			System.out.println("so: " + number + " la so le");
		}

	}

//	@Test
	public void TC_02() {

//		int a = 2;
//		int b = 3;
//		if(a>=b) {
//			System.out.println("a is greater than or equals b");
//		}else {
//			System.out.println("a is less than or equals b");
//		}
		int a = scanner.nextInt();
		int b = scanner.nextInt();
		if (a > b) {
			System.out.println(a + " is greater than or " + b);
		} else if (a == b) {
			System.out.println(a + " is equal to " + b);
		} else {
			System.out.println(a + " is less than " + b);
		}

	}

//	@Test
	public void TC_03() {
		String firstName = "nguyen van A";
		String secondName = "nguyen van B";
		if (firstName.equals(secondName)) {
			System.out.println("2 nguoi cung ten");
		} else {
			System.out.println("2 nguoi khac ten");
		}

	}


//	@Test
	public void TC_04() {
		int firstNumber = scanner.nextInt();
		int secondNumber = scanner.nextInt();
		int thirstNumber = scanner.nextInt();
		if(firstNumber < secondNumber && secondNumber < thirstNumber ) {
			System.out.println(thirstNumber + " is the gratest Number ");
		}else if(firstNumber < secondNumber ) {
			System.out.println(secondNumber + " is the gratest Number ");
		}else {
			System.out.println(firstNumber + " is the gratest Number ");
		}
		
	}

//	@Test
	public void TC_05() {
		int a = scanner.nextInt();
		if (a >= 10 && a <= 100) {
			System.out.println(a + " nam trong [10, 100] ");
		}else {
			System.out.println(a + " khong nam trong [10, 100] ");
		}
	}
//	@Test
	public void TC_06() {
		float studentPoint = scanner.nextFloat();
		if(studentPoint >= 0 && studentPoint < 6) {
			System.out.println("diem D");
			
		}else if(studentPoint >= 6 && studentPoint < 7.5) {
			System.out.println("diem C");
		}else if(studentPoint >= 7.5 && studentPoint < 8.5) {
			System.out.println("diem B");
		}else if(studentPoint >= 8.5 && studentPoint <= 10){
			System.out.println("diem A");
		}else {
			System.out.println("please reenter a valid value");
		}
			
	
	
		
}
	@Test
	public void TC_07() {
		int month = scanner.nextInt();
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			System.out.println("co 31 ngay");
		}else if(month == 2) {
			System.out.println("co 28 or 29 ngay");
		} else if(month == 4 || month == 6 || month == 9|| month == 11){
			System.out.println("co 30 ngay");
		}
	else {
		System.out.println("please enter the valid month");
	}
}
}
