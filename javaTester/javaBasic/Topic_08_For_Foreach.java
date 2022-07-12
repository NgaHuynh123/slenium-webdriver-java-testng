package javaBasic;

import java.util.Scanner;

import org.testng.annotations.Test;

public class Topic_08_For_Foreach {
	Scanner scanner = new Scanner(System.in);

	// @Test
	public void TC_01() {
//		int i = scanner.nextInt();

		for (int i = 1; i < 10; i++) {
			System.out.print(i + " ");
		}

	}

//@Test
	public void TC_02() {

		for (int i = 5; i <= 9; i++) {
			System.out.print(i + " ");
		}

	}

	//@Test
	public void TC_03() {

		for (int i = 1; i <= 10; i++) {
			if (i % 2 == 0) {
				System.out.println(i);
			}

		}
	}

//	@Test
	public void TC_04() {
		int a = 0;
		
		for(int i= 5; i<= 9; i++) {
			System.out.print((a +=i) + " ");
			
			
//	t = 0, i= 5
//			t = t + i 5
//			t= t+i+1 5+6
//			t= t+i+ 2  11+ 7
//			t= t+i+3
//			t+=i
			
			
			
			
			
		}
	
		
		
		
		
		
}
	
	
/* @Test */
	public void TC_05(){
		
		int a = 0;
		for(int i= 1; i<= 10; i++) {
			
			
			if(i % 2 != 0) {
				System.out.println(a+=i);
				
				
				
			}
			
			
		}
	}
	
	
	
//	@Test
	public void TC_06() {
		
		for(int i= 1; i<= 20; i++) {
			
			if(i % 3 == 0) {
				
			System.out.print(i + " ");	
				
				
				
				
				
			}
			
		}
		
		
		
		
		
	}
	

	@Test
	public void TC_07() {
		int a = 1;
	for(int i= 1; i<= 5; i++)	{
		System.out.print((a*=i) + " ");	
		
		
		
	}
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}