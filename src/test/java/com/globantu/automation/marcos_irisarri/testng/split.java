package com.globantu.automation.marcos_irisarri.testng;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class split {

	public static void main(String[] args) throws ParseException {
		String val = "$2,430";
		String val2 = NumberFormat.getCurrencyInstance(Locale.US).format(21502);
		Number val3 = NumberFormat.getCurrencyInstance(Locale.US).parse(val);
		System.out.println(val2);
		System.out.println(val3);
	}

}
