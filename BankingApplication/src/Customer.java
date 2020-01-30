import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Customer implements Comparable<Customer>{

	int custId;
	int acno;
	String name;
	String enpwd;
	Queue<String> pwdhis = new LinkedList<String>();
	int balance;
	ArrayList<Transaction> trans = new ArrayList<>();
	int transId = 0;
	int pwdCh = 0;
	
	
	
	
	Customer(int custId, int acno, String name, String enpwd, int balance){
		this.custId = custId;
		this.acno = acno;
		this.name = name;
		this.balance = balance;
		this.enpwd = enpwd;
		this.pwdhis.add(enpwd);
		
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


	public String toString(){
		String oldPwd = "Last 3 Passwords : ";
		if(!pwdhis.isEmpty()){
			for(String pwd : pwdhis){
				oldPwd += decrypte(pwd)+"  ";
			}
		}else {
			oldPwd += "No Passwords";
		}
		
		return "Name : "+this.name+"\nCustomer Id : "+this.custId+"\nAccont No : "+acno+"\nBalance : "+balance
				+"\nEncrypted Password : "+decrypte(enpwd)+"\n"+oldPwd+"\n";
	}


	@Override
	public int compareTo(Customer o) {
		return o.balance - this.balance;
	}
}
