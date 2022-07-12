package javaBasic;

public class Topic_05_Reference_Casting {

	String studentName;

	public String getStudentName() {
		return studentName;

	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;

	}

	public void showStudentName() {

		System.out.println("Student Name = " + studentName);

	}

	public static void main(String[] args) {

		Topic_05_Reference_Casting firstStudentName = new Topic_05_Reference_Casting();
		Topic_05_Reference_Casting secondStudentName = new Topic_05_Reference_Casting();

		firstStudentName.setStudentName("nguyen van A");
		secondStudentName.setStudentName("nguyen van B");

		firstStudentName.showStudentName();

		secondStudentName.showStudentName();

		firstStudentName = secondStudentName;
		firstStudentName.showStudentName();

		secondStudentName.showStudentName();
		secondStudentName.setStudentName("nguyen van C");

		firstStudentName.showStudentName();

		secondStudentName.showStudentName();

	}

}
