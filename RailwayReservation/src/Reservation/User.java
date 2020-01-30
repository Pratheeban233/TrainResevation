package Reservation;

import java.io.BufferedReader;
import java.io.IOException;

enum Gender{
	MALE,FEMALE,TRANSGENDER;
}
public class User {
	//String id;
	String userName;
	String pwd;
	Gender gender;
	int age;

	public static User getInstance(){
		return new User();
	}
	
	User(){
		
	}
	
	User(String userName, String pwd, Gender gender, int age){
		this.userName = userName;
		this.pwd = pwd;
		this.gender = gender;
		this.age = age;
	}
	

	@Override
	public String toString() {	
	
		return " User Name : "+userName+"\n password : "+pwd+"\n gender : "+gender+"\n age : "+age;
	}
	
	public User creatUser(BufferedReader bf) throws IOException{
		
		System.out.print("Enter User Name :");
		userName = bf.readLine();
		System.out.print("\nEnter Password : ");
		pwd = bf.readLine();
		System.out.print("\nEnter age : ");
		age = Integer.parseInt(bf.readLine());
		System.out.print("\nEnter Gender(M/F) : ");
		gender = (bf.readLine().charAt(0) == 'M') ? Gender.MALE:Gender.FEMALE;
		User user = new User(userName, pwd, gender, age);
		
		return user;
		
	}
}
