import java.util.ArrayList;

enum Type{
	ATM_Withdraw, Deposit, Transfer, PasswordFee, MaintananceFee;
}



public class Transaction /*implements Comparable<Transaction>*/{

	int transId;
	int transAmt;
	int transBal;
	Type transType;
	int toAc;
	int fromAc;
	//ArrayList<Transaction> transHist;
	
	
	
	public  String toString(){
		String transfer = "";
		if(transType == Type.Transfer){
			if(toAc != 0){
				transfer = "Money Transfer to "+toAc+"\n";
			}
			else {
				transfer = "Money Transfer from "+fromAc+"\n";
			}
		}
		
		return " Trans Id : "+transId+" \ntrans Amount : "+transAmt+"\n Type : "+transType+"\n Balance : "+transBal+"\n"+transfer;
	}



	/*@Override
	public int compareTo(Transaction o) {
		// TODO Auto-generated method stub
		return 0;
	}*/
	
}
