package com.wipro.bank.service;

import com.wipro.bank.bean.TransferBean;

import com.wipro.bank.dao.BankDAO;
import com.wipro.bank.util.InsufficientFundsException;

public class BankService {
public String checkBalance(String accountNumber) {

BankDAO bankdao=new BankDAO();

if(bankdao.validateAccount(accountNumber)) {
float balance=bankdao.findBalance(accountNumber);
return "BALANCE IS:"+balance;
}else {
return "ACCOUNT NUMBER IS INVALID";
}
}

public String transfer(TransferBean transferBean) {
if(transferBean==null) {
return "INVALID";
}
BankDAO dao=new BankDAO();
if(!dao.validateAccount(transferBean.getFromAccountNumber()) || !dao.validateAccount(transferBean.getToAccountNumber())){
return "INVALID ACCOUNT";
}
try {
float fromBalance=dao.findBalance(transferBean.getFromAccountNumber());
if(fromBalance < transferBean.getAmount()) {
throw new InsufficientFundsException();
}
boolean debited=dao.updateBalance(transferBean.getFromAccountNumber(),fromBalance - transferBean.getAmount());
float toBalance=dao.findBalance(transferBean.getToAccountNumber());
boolean credited=dao.updateBalance(transferBean.getToAccountNumber(),toBalance + transferBean.getAmount());
boolean transaction=dao.transferMoney(transferBean);

if(debited && credited && transaction) {
return "SUCCESS";
}

}catch(InsufficientFundsException e) {
e.printStackTrace();return null;
}
return null;

}
}