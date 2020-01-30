package Reservation;
import java.util.*;
import java.io.*;

public class ReservationDriver {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static String adminId = "admin";
	static String adminPwd = "root";
	
	static HashMap<Integer, ReservationC> reservations = new HashMap<Integer, ReservationC>();
	static HashMap<Integer, Train> trains = new HashMap<Integer, Train>(){
	
		{
			put(1111, new Train(1111, "Pandian Exp", 50, 100, 99, 88));
			put(2222, new Train(2222, "Vaigai Exp", 60, 110, 89, 78));
			put(3333, new Train(3333, "Cheran Exp", 70, 120, 79, 68));
			put(4444, new Train(4444, "Chozlan Exp", 80, 130, 69, 58));
			put(5555, new Train(5555, "Chennai Exp", 90, 140, 59, 48));
			
			
		}
	};
	
	static HashMap<String, User> users = new HashMap<String, User>(){
		{
			put("karthic",new User("karthic", "tgk", Gender.MALE, 24));
			put("Vengatesh",new User("Vengatesh", "veng", Gender.MALE, 26));
			put("Anand",new User("Anand", "anan", Gender.MALE, 27));
			put("Balaji",new User("Balaji", "bala", Gender.MALE, 23));
			put("Siva",new User("Siva", "siva", Gender.MALE, 28));
		}
	};
	
	
	public static void main(String args[]) throws IOException{
		System.out.println("===============WELCOME TO TAMILNADU RAILWAY RESERVATION===============");
		System.out.println("======================================================================");
		
		int choice = 0;
		System.out.println("");
		while(true){
			System.out.println("Main Menu \n 1. Admin Mode\n 2. User Mode\n 3. Exit");
			choice = Integer.parseInt(bf.readLine());
			switch(choice){
			case 1: 
				adminMode();
				break;
			case 2 : 
				userMode();
				System.out.println("Entering user mode...!");
				break;
			case 3 :
				System.out.println("Exiting from Main Menu...!");
				return;
				
			default :
				System.out.println("Invalid Menu...!");
				break;
			
			}
		}
	}


	private static void userMode() throws IOException {
		System.out.println("Entering into User Mode...!");
		int ch = 0;
		while(true){
			System.out.println("User Menu : \n1. Display Trains \n2. Reserve \n3. Cancel Reservation \n4. Exit");
			ch = Integer.parseInt(bf.readLine());
			switch (ch) {
			case 1:
				System.out.println("Available Trains : ");
				displayTrains();
				break;
			case 2:
				System.out.println("Enter Reservation Details : ");
				reserve();
				break;
			case 3:
				
				break;
			case 4:
				System.err.println("Exit from user Mode...!");
				return;
			default:
				System.err.println("Please choose correct menu...!");
				break;
			}
		}
	}


	private static void reserve() throws IOException{
		
		
		boolean flag = true;
		while(flag){
		
			System.out.print("Train no : ");
			int trainNO = Integer.parseInt(bf.readLine());
			if(trains.containsKey(trainNO)){
				
				flag = false;
				ReservationC reser = new ReservationC();
				Random rand = new Random(); 
				int pnr = rand.nextInt(100000);
				reser.setTrainNO(trainNO);
				reser.setPnr(pnr);
				System.out.print("Enter number of Passenger :");
				reser.setNoOfPass(Integer.parseInt(bf.readLine()));
				HashMap<String, Passenger> passengers = new HashMap<String, Passenger>();
				for(int i=0;i<reser.getNoOfPass();i++){
					System.out.println("Enter Passenger Detail "+(i+1));
					Passenger pass = Passenger.getInstance().createPassenger(bf);
					passengers.put(pass.name, pass);
				}
				
				Train tr = trains.get(reser.getTrainNO());
				System.out.println("First Class Avail : "+tr.fSheet+" Fare : "+tr.fFare);
				System.out.println("Second Class Avail : "+tr.sSheet+" Fare  : "+tr.sFare);
				System.out.print("Choose Class Type (F/S): ");
				
				int totalFare = (bf.readLine().charAt(0) == 'F')?reser.getNoOfPass()*tr.fFare:reser.getNoOfPass()*tr.sFare;
				System.out.println("Total Fare : "+totalFare);
				reser.setTotalFare(totalFare);
				System.out.print("Source place : ");
				reser.setSource(bf.readLine());
				System.out.print("Destinatin place :");
				reser.setDest(bf.readLine());
				
				reservations.put(pnr, reser);
				System.out.println("Reservation Completed ...!");
				System.out.println("===============================================");
				tr.fSheet = tr.fSheet-reser.getNoOfPass();
				System.out.println("Available sheet :"+tr.fSheet);
				
				
			}else {
				System.err.println("Train Number not present..!");
			}
			
		}
		
	
		
		
		
	}


	private static void adminMode() throws IOException{
		System.out.println("Entering into Admin Mode");
		
		
		System.out.print("Enter Admin user ID : ");
		if(bf.readLine().equals(adminId)){
			System.out.print("Enter admin Password : ");
			if(bf.readLine().equals(adminPwd)){
				int choice = 0;
				while(true){
					System.out.println("Admin Menu : \n1. Add User \n2. Remove User \n3. Add Train \n4. Remove Train \n"+
							"5. Display Users \n6. Display Train \n7. Change Password \n8. Display Reservation \n9. Conform Reservation \n10. Exit");
					choice = Integer.parseInt(bf.readLine());
					switch (choice) {
					case 1:
						System.out.println("Enter user details for add new User");
						addUser(bf);
						break;
					case 2 :
						System.out.println("Enter user name for remove ");
						String uName = bf.readLine();
						
						if(users.containsKey(uName)){
							System.out.print("Do you want to remove "+uName+"Y/N : ");
							if(bf.readLine().charAt(0)=='Y'){
								users.remove(uName);
								System.out.println(uName+" removed from User List..!");
							}else {
								System.out.println("Not Removed...!");
							}
							
						}else {
							System.err.println("Uesr name not present ...!");
						}
						break;
					case 3 : 
						System.out.println("Enter Train Details for Add  new Train ");
						Train train = Train.getInstance().addNewTrain(bf);
						trains.put(train.trainNo, train);
						break;
					case 4 : 
						System.out.println("Enter Train number to remove ...!");
						int tno = Integer.parseInt(bf.readLine());
						if(trains.containsKey(tno)){
							System.out.println("Train "+tno+": do you want to remove ? Y/N");
							if(bf.readLine().charAt(0)=='Y'){
								trains.remove(tno);
								System.out.println("Train "+tno+" removed from Train list..!");
							}
						}else {
							System.out.println("Train number is not present...!");
						}
						break;
					case 5 : 
						System.out.println("Users List :");
						int count = 0;
						if(!users.isEmpty()){
							for(User user : users.values()){
								System.out.println("User "+(++count)+"\n"+user);
							}
						}else {
							System.err.println("No one user Present in App..!");
						}
						break;
						
					case 6:
						System.out.println("Train List : ");
						displayTrains();
						break;
					case 7:
						System.out.println("Enter user name for change password");
						String uname = bf.readLine();
						if(users.containsKey(uname)){
							
							System.out.println("User Details : \n"+users.get(uname));

							System.out.println("****Enter new password***");
							String pwd = bf.readLine();
							users.get(uname).pwd= pwd;
							System.out.println("Password Changed...!");
							
						}else {
							System.out.println("User name incorrect...!");
						}
								
						break;
					case 10 : 
						System.out.println("Exit from Admin mode..!");
						break;
					default:
						System.out.println("Invalid Option...!");
						break;
					}
				}		
			}else {
				System.err.println("Incorrct Password...!");
				return;
			}
		}else {
			System.err.println("Incorrect Admin ID...!");
			return;
		}
		
		
		
	}


	private static void displayTrains() {
		if(!trains.isEmpty()){
			int cnt = 0;
			for(Train tr: trains.values()){
				System.out.println("Train : "+(++cnt)+"\n"+tr);
			}
		}else {
			System.err.println("No train in list...!");
		}
		
	}


	private static void addTrain() throws IOException {
		
		
	}


	private static void addUser(BufferedReader bf2) throws IOException {
		
		User user = User.getInstance().creatUser(bf);
		users.put(user.userName, user);
		System.out.println("User added Successfully...!");
		
	}
}
