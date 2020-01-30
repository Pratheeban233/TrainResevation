import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Driver {

	static String adminName = "admin";
	static String adPwd = "root";
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static HashMap<Integer, Customer> custList = new HashMap<Integer, Customer>(){
		{
			put(1122, new Customer(11, 1122, "Karthic", encryptPwd("abCD12"), 10000));
			put(1222, new Customer(12, 1222, "Ravi", encryptPwd("cdEF34"), 20000));
			put(1322, new Customer(13, 1322, "Ganesh", encryptPwd("efGH56"), 30000));
			put(1422, new Customer(14, 1422, "Ram", encryptPwd("efIJ78"), 40000));
		}
	};
		
	public static void main(String args[]) throws NumberFormatException, IOException{
		
	System.out.println("======================= Banking Application =========================");
	int ch = -1;
	while(true){
		System.out.println("Main Menu : \n1. Admin Mode 2. User Mode 3. Exit ");
		ch = Integer.parseInt(bf.readLine());
		switch (ch) {
		case 1:
			adminMode();
			break;
		case 2:
			userMode();
			break;
		case 3:
			System.err.println("exit from Application");
			return;

		default:
			System.err.println("Please choose correct one");
			break;
		}
	}
		
		
	}

	private static void userMode() throws IOException {
		System.out.println("User Mode : \n");
		int ch = 0;
		while(true){
			System.out.println("User Menu : \n1. Login \n2. Create new \n3. Exit");
			ch = Integer.parseInt(bf.readLine());
			switch (ch) {
			case 1:
				login();
				break;
			case 2:
				createNew();
				break;
			case 3:
				System.out.println("Exiting from User Menu...!");
				return;
				
			default:
				System.err.println("Choose currect options...!");
				break;
			}
		}
		
		
		
		
		
			
	}

	
	// Authentication
	private static void login() throws IOException {
		System.out.println("Enter user name :");
		String uname = bf.readLine();
		System.out.println("Enter Password : ");
		String pwd = bf.readLine();
		boolean uflag = false;
		Customer cus = null;
		for(Customer c : custList.values()){
			if(c.name.compareTo(uname)==0 && decrypte(c.enpwd).compareTo(pwd)==0){
				uflag = true;
				cus = c;
			}
		}
		if(uflag){
			System.out.println("You are logged in ..!");
			int ch = 0;
			while(true){
				System.out.println("User Menu : \n1. ATM Withdraw \n2. Deposit \n3. Money Transfer \n4. Account Hisory \n5. Password Change \n6. Exit");
				ch = Integer.parseInt(bf.readLine());
				switch(ch) {
				case 1:
					System.out.println("ATMWithdraw");
					ATMWithdraw(cus);
					break;
				case 2:
					System.out.println("Deposit");
					Deposit(cus);
					break;
				case 3:
					System.out.println("moneyTransfer");
					moneyTransfer(cus);
					break;
				case 4: 
					System.out.println("Account History");
					transHistroy(cus);
					break;
					
				case 5: 	
					pwdChage(cus);
					break;
				case 6:
					System.out.println("exit");
					return;
				default:
					break;
				}
			}			
		}else {
			System.err.println("User name and Password not exist..!");
			return;
		}
		
		
		
		
			
		
	}

	private static boolean pwdChage(Customer cus) throws IOException {
		System.out.println("Enter your current Password");
		String cpwd = bf.readLine();
		
		if(cpwd.compareTo(decrypte(cus.enpwd))==0){
			System.out.println("Enter new Password : ");
			String npwd = bf.readLine();
			if(pwdValidation(npwd)){
				System.out.println("Re-type the new Password : ");
				if(bf.readLine().compareTo(npwd)==0){
					npwd = encryptPwd(npwd);
				
					 if(cus.pwdhis.contains(npwd)){
							System.err.println("New Password should not same as last three password...!");
							return false;
					 }
					 cus.enpwd = npwd;
					 cus.pwdCh++;
					 if(cus.pwdhis.size()>=2){
						 cus.pwdhis.poll();
						 cus.pwdhis.add(npwd);
					 }else{
						 cus.pwdhis.add(npwd);
					 }
					 // Password change fee
					 if(cus.pwdCh%5 == 0 && !cus.trans.isEmpty()){
						// except for the highest thee balance account
						 ArrayList<Customer>cr =  sortCustomer();
						 if(cr.get(2).balance>cus.balance){
						 
							 Transaction tr = new Transaction();
							 tr.transAmt = 10;
							 cus.transId++;
							 tr.transId = cus.transId;
							 tr.transBal = cus.balance -=10;
							 tr.transType = Type.PasswordFee;
							 tr.fromAc = 0;
							 tr.toAc = 0;
							 
							 cus.trans.add(tr);
						 }
					 }
					 
					 
					 System.out.println("Password changed successfully...!");
					 return true;
				}else {
					System.err.println("password Mismatched...!");
				}
				
			}else {
				System.out.println("Password required atleast 2 upper case 2 lower case and 2 numeric value length should be greater than 6");
			}
		}else {
			System.err.println("Please give current password...!");
		}
		
		return false;
	}

	private static void transHistroy(Customer cus) {
		System.out.println("Mini StateMent:");
		for(Transaction tr : cus.trans){
			System.out.println(tr);
		}
		
	}

	private static void moneyTransfer(Customer cus) throws NumberFormatException, IOException {

		if(cus.trans.size()%5 == 0 &&  !cus.trans.isEmpty()){
			System.err.println("For every Five transactions you have to change password ...!");
			if(!pwdChage(cus)){
				return;
			}
		}
		// Maintenance check
		if(cus.trans.size()%10==0 && !cus.trans.isEmpty()){
			cus = maintananceFee(cus);
		}
		System.out.println("Your Balance details \n"+cus.balance);
		
		System.out.println("Enter Account number for Money Transfer");
		int acno = Integer.parseInt(bf.readLine());
		if(custList.containsKey(acno)){
			System.out.println("Enter Transfer amount : ");
			int amt = Integer.parseInt(bf.readLine());
			if(amt<=cus.balance-1000){
				Transaction trFr = new Transaction();
				trFr.transAmt = amt;
				trFr.fromAc = 0;
				trFr.toAc = acno;
				trFr.transType = Type.Transfer;
				trFr.transBal = cus.balance -=amt;
				cus.transId++;
				trFr.transId = cus.transId;	
				cus.trans.add(trFr);
				
				Transaction trTo = new Transaction();
				trTo.transAmt = amt;
				trTo.fromAc = cus.acno;
				trTo.toAc = 0;
				trTo.transType = Type.Transfer;
				trTo.transBal = custList.get(acno).balance +=amt;
				custList.get(acno).transId++;
				trTo.transId = custList.get(acno).transId;	
				cus.trans.add(trTo);
				
				System.out.println("Amount "+amt+" Transfer to account ->"+acno);
				System.out.println("Your Balance is -> "+cus.balance);
			
			}else {
				System.err.println("Cannot transfer insufficient balance. Maitain atleast 1000 in your balance...!");
			}
		}else {
			System.err.println("Account does not exist...");
		}
		
		
		
	}

	private static void Deposit(Customer cus) throws NumberFormatException, IOException {

		if(cus.trans.size()%5 == 0 &&  !cus.trans.isEmpty()){
			System.err.println("For every Five transactions you have to change password ...!");
			if(!pwdChage(cus)){
				return;
			}
		}
		
		// Maintenance check
		if(cus.trans.size()%10==0 && !cus.trans.isEmpty()){
			cus = maintananceFee(cus);
		}
		System.out.println("Your Balance details \n"+cus.balance);
		
		System.out.println("Enter amount to deposit ");
		int amt = Integer.parseInt(bf.readLine());
		if(amt >0){
			Transaction tr = new Transaction();
			tr.transAmt = amt;
			tr.fromAc = 0;
			tr.toAc = 0;
			tr.transType = Type.Deposit;
			cus.balance +=amt;
			cus.transId++;
			tr.transId = cus.transId;	
			cus.trans.add(tr);
			System.out.println("Amount "+amt+" Deposited to your account");
			System.out.println("Your Balance is -> "+cus.balance);
			
		}else {
			System.err.println("invalid amount ...!");
		}
	}
	
	
	private static Customer maintananceFee(Customer cus){
	
		Transaction tr = new Transaction();
		 tr.transAmt = 1000;
		 cus.transId++;
		 tr.transId = cus.transId;
		 tr.transBal = cus.balance -=1000;
		 tr.transType = Type.MaintananceFee;
		 tr.fromAc = 0;
		 tr.toAc = 0;
		 
		 cus.trans.add(tr);	
	
	
		 return cus;
	}

	private static void ATMWithdraw(Customer cus) throws NumberFormatException, IOException {
		
		if(cus.trans.size()%5 == 0 && !cus.trans.isEmpty()){
			System.err.println("For every Five transactions you have to change password ...!");
			if(!pwdChage(cus)){
				return;
			}
		}
		// Maintenance check
		if(cus.trans.size()%10==0 && !cus.trans.isEmpty()){
			cus = maintananceFee(cus);
		}
		
		System.out.println("Your Balance details \n"+cus.balance);
		
		System.out.println("\n Enter amount to withdraw : ");
		int amt = Integer.parseInt(bf.readLine());
		if(cus.balance-1000>=amt  ){
			Transaction tr = new Transaction();
		
			tr.transAmt = amt;
			tr.fromAc = 0;
			tr.toAc = 0;
			tr.transType = Type.ATM_Withdraw;
			cus.balance -= amt;
			cus.transId++;
			tr.transId = cus.transId;	
			cus.trans.add(tr);
			System.out.println("Please collect your Money : "+amt);
			System.out.println("Your Balance is -> "+cus.balance);
			
		}else {
			System.err.println("You shold kept minimum balance 1000...!");
		}
		
		
		
		
	}

	private static void adminMode() throws NumberFormatException, IOException {
		//System.out.println("");
		System.out.println("Enter admin name : ");
		if(adminName.compareTo(bf.readLine())==0){
			System.out.println("enter password : ");
			if(adPwd.compareTo(bf.readLine())==0){
				int ch = -1;
				while(true){
					System.out.println("Admin Menu : \n1. Create new User \n2. display Customers List \n0. Logout");
					ch = Integer.parseInt(bf.readLine());
					switch (ch) {
					case 1:
						createNew();
						break;
					case 2:
						displayCustomer();
						break;
					case 0:
						System.err.println("exit from Admin Mode");
						return;					
						
					default:
						System.err.println("Please choose correct one");
						break;
					}
				}
				
			}else {
				System.err.println("incorrect password...!");
				return;
			}
		}else {
			System.err.println("Incorrect User name\n");
			return;
		}
	}

	private static ArrayList<Customer> sortCustomer(){
		ArrayList<Customer> cr = new ArrayList<Customer>();
		for(Customer c : custList.values()){
			cr.add(c);
		}
		
		
		Collections.sort(cr);
		return cr;
	}
	
	private static void displayCustomer() throws NumberFormatException, IOException {
		
		if(!custList.isEmpty()){
			System.out.println("Enter Number of Customer to display: ");
			int cnt = Integer.parseInt(bf.readLine());
			// Sort the customer by balance 
			ArrayList<Customer>cr =  sortCustomer();
			int cou = 1;
			for(Customer cust : cr){
				System.out.println("Customer 1:"+cou+"\n"+cust);
				if(cnt==cou)
					break;
				cou++;
				
			}
		}else {
			System.out.println("No customers");
		}
		
	}

	private static void createNew() throws IOException {
		System.out.println("Creating new User");
		System.out.println("enter name of the customer : ");
		String name = bf.readLine();
		System.out.println("enter password : ");
		String pwd = bf.readLine();
		if(pwdValidation(pwd)){
			System.out.println("Re-type password : ");
			if(bf.readLine().compareTo(pwd)==0){
			
				// Encryption 
				pwd = encryptPwd(pwd);
				
				Random rand = new Random();
				
				int custId = rand.nextInt(100);
				int acno = rand.nextInt(10000);
				
				int balance = 10000;
				
				custList.put(custId, new Customer(custId, acno, name, pwd, balance));
				System.out.println("new Customer created ");
				System.out.println("Customer Details : \n"+custList.get(custId));
			}else {
				System.out.println("Password mismatch");
			}

		}else {
			System.out.println("Password required atleast 2 upper case 2 lower case and 2 numeric value length should be greater than 6");
		}
		
			
	}

	private static boolean pwdValidation(String pwd) {
		StringBuffer st = new StringBuffer(pwd);
		int lower = 0, upper =0, num = 0;
		for(int i=0;i<pwd.length();i++){
			if(pwd.charAt(i)>='a' && pwd.charAt(i)<= 'z'){
				lower++;
			}else if(pwd.charAt(i)>='A' && pwd.charAt(i)<= 'Z'){
				upper++;
			}else if(pwd.charAt(i)>='0' && pwd.charAt(i)<= '9'){
				num++;
			}
		}
		
		if(lower>=2 && upper>= 2 && num >= 2 && pwd.length()>=6){
			return true;
		}else {
			return false;
		}
		
	}

	private static String encryptPwd(String pwd) {
		StringBuffer str1 = new StringBuffer(pwd);
		//System.out.println(str1);
		
		for(int i=0;i<pwd.length();i++){
			if(pwd.charAt(i) == 'z'){
				str1.setCharAt(i, 'a');
			}else if(pwd.charAt(i) == 'Z'){
				str1.setCharAt(i, 'A');
			}else if(pwd.charAt(i) == '9'){
				str1.setCharAt(i, '0');
			}else {
				char ch = str1.charAt(i);
				ch =  (char) (ch+1);
				str1.setCharAt(i, ch);
			}
		}
		return str1.toString();	
	}
	
	private static String decrypte(String enpwd) {
		StringBuffer str1 = new StringBuffer(enpwd);
		//System.out.println(str1);
		
		for(int i=0;i<str1.length();i++){
		if(str1.charAt(i) == 'a'){
			str1.setCharAt(i, 'z');
		}else if(str1.charAt(i) == 'A'){
			str1.setCharAt(i, 'Z');
		}else if(str1.charAt(i) == '0'){
			str1.setCharAt(i, '9');
		}else {
			char ch = str1.charAt(i);
			ch =  (char) (ch-1);
			str1.setCharAt(i, ch);
		}
		}
		return str1.toString();
	}

}
