package com.wipro.bank.main;
import com.wipro.bank.bean.TransferBean;
import com.wipro.bank.service.BankService;

public class BankMain {
	public static void main(String[] args) {
		BankService bankService=new BankService();
		System.out.println(bankService.checkBalance("1234567890"));
		TransferBean transferBean=new TransferBean();
		transferBean.setFromAccountNumber("1234567890");
		transferBean.setAmount(840000);
		transferBean.setToAccountNumber("1234567891");
		transferBean.setDateOfTransaction(
				new java.sql.Date(new java.util.Date().getTime())
		);
		System.out.println(bankService.transfer(transferBean));
	}

}
