package javaBasic;

import org.testng.annotations.Test;

public class Topic_02_Data_Type_Exercise {
	int number;
	
	@Test
	public void TC_01() {
		
		int a  = 6;
		int b = 2 ;
		
	System.out.println("Tong = " + (a + b) );
	System.out.println("Hieu = " + (a - b));
	System.out.println("Tich = " + (a * b));
	System.out.println("Thuong = " + (a / b));
	System.out.println("ttt = " + (++a) );	
		
		
	}
		
		@Test
		public void TC_02() {
			
			float width = 7.5f;
			float height = 3.8f;
			System.out.println("Rectangle S = " + (width * height));
			System.out.println("Rectangle C = " + (width * height)/2);
			
			
			
		}
		
		
		
		@Test 
		public void TC_03() {
			
			String name = "Automation Testing";
			
			System.out.println(name);
			
			
			
		}
		
		
		
	}
	
	
	
	
	
	
	
	


