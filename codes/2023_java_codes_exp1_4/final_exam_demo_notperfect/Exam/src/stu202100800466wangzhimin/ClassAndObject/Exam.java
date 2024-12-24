package stu202100800466wangzhimin.ClassAndObject;

public class Exam {
	String subject;
	Person person;

	public Exam(String subject,Person person) {
		this.subject = subject;
		this.person = person;
	}
	
	public void getScore() {
		person.getScore(subject);
	}
	

}