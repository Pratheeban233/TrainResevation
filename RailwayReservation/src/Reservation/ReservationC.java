package Reservation;

import java.util.HashMap;

public class ReservationC {

	private int pnr;
	private HashMap<String, Passenger> passengers;
	//private Train trainDetails;
	private String source;
	private String dest;
	private int totalFare;
	private String status;
	private int noOfPass;
	private int trainNO;
	private String classType;
	
	
	public int getPnr() {
		return pnr;
	}
	public void setPnr(int pnr) {
		this.pnr = pnr;
	}
	public HashMap<String, Passenger> getPassengers() {
		return passengers;
	}
	public void setPassengers(HashMap<String, Passenger> passengers) {
		this.passengers = passengers;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDest() {
		return dest;
	}
	public void setDest(String dest) {
		this.dest = dest;
	}
	public int getTotalFare() {
		return totalFare;
	}
	public void setTotalFare(int totalFare) {
		this.totalFare = totalFare;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getNoOfPass() {
		return noOfPass;
	}
	public void setNoOfPass(int noOfPass) {
		this.noOfPass = noOfPass;
	}
	public int getTrainNO() {
		return trainNO;
	}
	public void setTrainNO(int trainNO) {
		this.trainNO = trainNO;
	}
	public String getClassType() {
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
	
}
