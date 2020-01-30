package Reservation;

import java.io.BufferedReader;
import java.io.IOException;

public class Passenger {

	String name;
	int age;
	Gender pGen;
	//String fatherName;
	public static Passenger getInstance(){
		return new Passenger();
	}
	Passenger(){
		
	}
	
	Passenger(String name, int age, Gender pGen){
		this.name = name;
		this.age = age;
		this.pGen = pGen;
		
	}
	
	@Override
	public String toString() {
	
		return " Passenger Name : "+name+"\n age : "+age+"\n Gender : "+pGen;
	}
	
	public Passenger createPassenger(BufferedReader bf) throws IOException{
		System.out.print("Passanger Name : ");
		name = bf.readLine();
		System.out.print("Age : ");
		age = Integer.parseInt(bf.readLine());
		System.out.print("\nEnter Gender(M/F) : ");
		pGen = (bf.readLine().charAt(0) == 'M') ? Gender.MALE:Gender.FEMALE;
		
		return new Passenger(name, age, pGen);
		
	}
}
