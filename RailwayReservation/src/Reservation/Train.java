package Reservation;

import java.io.BufferedReader;
import java.io.IOException;

public class Train {

	int trainNo;
	String tName;
	int fSheet;
	int sSheet;
	int fFare;
	int sFare;
	
	public static Train getInstance(){
		return new Train();
	}
	
	Train(int trainNo, String tName, int fSheet, int sSheet, int fFare, int sFare){
		this.trainNo = trainNo;
		this.tName = tName;
		this.fSheet = fSheet;
		this.sSheet = sSheet;
		this.fFare = fFare;
		this.sFare = sFare;
		
	}
	
	Train(){
		
	}
	
	@Override
	public String toString() {
		
		return " Train No : "+trainNo+"\n Train Name : "+tName+"\n First Class Sheet : "+fSheet+
				"\n Second Class Sheet : "+sSheet+"\n First Class Fare : "+fFare+
				"\n Second Class Fare : "+sFare;
	}
	
	public  int getInt(BufferedReader bf) throws IOException{
		return Integer.parseInt(bf.readLine());
	}
	
	public Train addNewTrain(BufferedReader bf) throws IOException{
		
		System.out.print("Train Number : ");
		trainNo = getInt(bf);
		System.out.print("Train Name : ");
		tName = bf.readLine();
		System.out.print("First Class Sheet :");
		fSheet = getInt(bf);
		System.out.print("Second Class Sheet :");
		sSheet = getInt(bf);
		System.out.print("First Class Fare :");
		fFare = getInt(bf);
		System.out.print("Second Class Fare :");
		sFare = getInt(bf);		
		
		return new Train(trainNo, tName, fSheet, sSheet, fFare, sFare);
	}
}
