import java.util.Scanner;

public class Check {

	public static void main(String[] args) {
		String enpwd = encryptPwd("Success");
		
		System.out.println("Encrypted pwd is :"+enpwd);
		
		String deStr = decrypte(enpwd);
		System.out.println("Decrypted pwd is :"+deStr);
		
		Scanner scn = new Scanner(System.in);
		System.out.println("ener num: ");
		int in = scn.nextInt();
		System.out.println();
		System.out.println("enter name :");
		String st = scn.nextLine();
		st = scn.nextLine();
		System.out.println("name = "+st+" num = "+in);
		
		
		
		
		
		
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

	private static String encryptPwd(String pwd) {
		StringBuffer str1 = new StringBuffer(pwd);
		System.out.println(str1);
		
		if(!pwd.isEmpty() || pwd != null){
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
			
		}else {
			System.err.println("Empty Password ");
			return null;
		}	
	}
	
	
}


