package com.wipro.bank.util;

public class InsufficientFundsException extends Exception {
@Override
public String toString() {
	return "INSUFFICIENT FUNDS";
}

}
