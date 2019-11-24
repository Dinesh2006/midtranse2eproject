package tests;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import pageObjects.PgMidtransPaymentFlow;

import resources.BaseTest;
import resources.ExcelUtility;

public class MidtransPaymentFlowTest extends BaseTest{

	@Test
	public void successPaymentFlow() throws IOException, InterruptedException
	{
		String nameofCurrMethod = new Object() {} .getClass().getEnclosingMethod().getName();
		ExcelUtility util=new ExcelUtility();
		ArrayList<String> data=util.getData(nameofCurrMethod);
		driver.get(prop.getProperty("url"));
		PgMidtransPaymentFlow l=new PgMidtransPaymentFlow(driver,test);
		l.clickButtonBuy();
		l.clickButtonCheckOut();
		l.clickButtonContinue();
		l.clickLinkCreditCard();
		l.enterCardNumber(data.get(2));
		l.enterExpiryDate(data.get(3));
		l.enterCVV(data.get(4));
		l.clickButtonPayNow();
		l.enterPassword(data.get(5));
		l.clickButtonOK();
	}


	@Test 
	public void failedPaymentFlow() throws IOException, InterruptedException {
		String nameofCurrMethod = new Object() {} .getClass().getEnclosingMethod().getName();
		ExcelUtility util=new ExcelUtility();
		ArrayList<String> data=util.getData(nameofCurrMethod);
		driver.get(prop.getProperty("url"));
		PgMidtransPaymentFlow l=new PgMidtransPaymentFlow(driver,test);
		l.clickButtonBuy();
		l.clickButtonCheckOut();
		l.clickButtonContinue();
		l.clickLinkCreditCard();
		l.enterCardNumber(data.get(2));
		l.enterExpiryDate(data.get(3));
		l.enterCVV(data.get(4));
		l.verifyErrorMsg();
	}

}
