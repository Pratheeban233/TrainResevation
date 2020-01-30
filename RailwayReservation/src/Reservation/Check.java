package Reservation;

import java.math.BigDecimal;	
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Check {

	
	
	public static void main(String[] args) {

//		int i = -100;
//		System.out.println((i>0)?i:-i);
//		
//		BigInteger l = new BigInteger("9908765445") ;
//		BigInteger m = new BigInteger("5");
//		//l.add(BigInteger.valueOf(5));
//		l.multiply(BigInteger.valueOf(5));
//		System.out.println(l);
//		
//		System.out.println(fun(8));
		List<Check> cc = new ArrayList<Check>();
		while(true) {
			Check c = new Check();
			cc.add(c);
			System.out.println("Runnint....!"+  cc.size());
		}

	}

	private static BigInteger fun(int i) {
		BigInteger b = new BigInteger(String.valueOf(i));
		
		b.multiply(BigInteger.valueOf(5));
		return b;
	}

}
