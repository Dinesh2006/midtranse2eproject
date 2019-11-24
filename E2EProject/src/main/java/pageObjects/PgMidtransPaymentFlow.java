package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import resources.BaseTest;
import resources.ExtentReporterNG;


import org.openqa.selenium.support.FindBy;

public class PgMidtransPaymentFlow {


	public WebDriver driver;
	public ExtentTest test;
	public static Logger log =LogManager.getLogger(BaseTest.class.getName());
	WebDriverWait wait;

	public PgMidtransPaymentFlow(WebDriver driver,ExtentTest test) {
		this.driver=driver;
		this.test=test;
		PageFactory.initElements(driver, this);
		wait=new WebDriverWait(driver,30);
	}

	@FindBy(xpath="//a[@class='btn buy']")
	WebElement btn_buy;

	@FindBy(xpath="//div[@class='cart-checkout']")
	WebElement btn_CartCheckOut;

	@FindBy(xpath="//span[text()='Continue']/../..")
	WebElement btn_Continue;

	@FindBy(xpath="//div[text()='Credit Card']")
	WebElement lnk_CreditCard;

	@FindBy(xpath="//input[@name='cardnumber']")
	WebElement txt_CardNumber;

	@FindBy(xpath="//label[text()='Expiry date']/../input")
	WebElement txt_ExpiryDate;

	@FindBy(xpath="//label[text()='CVV']/../input")
	WebElement txt_CVV;

	@FindBy(xpath="//span[text()='Pay Now']/../..")
	WebElement btn_PayNow;

	@FindBy(xpath="//iframe[@id='snap-midtrans']")
	WebElement frame_PopUpContinue;

	@FindBy(xpath="//iframe[contains(@src,'api.sandbox.veritrans')]")
	WebElement frame_Password;

	@FindBy(xpath="//input[@type='password']")
	WebElement inputTxt_Password;

	@FindBy(xpath="//button[@name='ok']")
	WebElement btn_OK;

	@FindBy(xpath="//span[text()='Thank you for your purchase.']")
	WebElement txt_FinalMSG;

	@FindBy(xpath="//span[text()='Invalid card number']")
	WebElement txt_InvalidCardNumber;

	public void verifyErrorMsg() {
		Assert.assertTrue(txt_InvalidCardNumber.isDisplayed(), "Error Message not displayed for invalid card number");
		test.log(LogStatus.PASS, "Successfully displayed error Message for invalid card number");
	}

	public void enterPassword(String password) throws InterruptedException {
		Thread.sleep(10000);
		driver.switchTo().frame(frame_Password);
		Assert.assertTrue(inputTxt_Password.isEnabled(), "Password field is not enabled to enter password");
		inputTxt_Password.sendKeys(password);
		test.log(LogStatus.PASS, "Successfully entered password as "+password);
	}

	public void clickButtonOK() {
		Assert.assertTrue(btn_OK.isDisplayed(), "OK button is not displayed");
		btn_OK.click();
		test.log(LogStatus.PASS, "Successfully clicked on OK button");
		driver.switchTo().defaultContent();
	}

	public void verifyFinalMessage() {
		wait.until(ExpectedConditions.visibilityOf(txt_FinalMSG));
		Assert.assertTrue(txt_FinalMSG.isDisplayed(), "Not returned to Home Page");
		test.log(LogStatus.PASS, "Successfully returned to Home page after transaction");
	}
	public void clickButtonPayNow() {
		Assert.assertTrue(btn_PayNow.isDisplayed(), "Pay Now button is not dislayed");
		btn_PayNow.click();
		test.log(LogStatus.PASS, "Successfully clicked on Pay Now Button");
		//driver.switchTo().defaultContent();
	}

	public void enterCVV(String CVV) {
		Assert.assertTrue(txt_CVV.isEnabled(), "CVV field is not  enabled");
		txt_CVV.sendKeys(CVV);
		test.log(LogStatus.PASS, "Successfully entered CVV as "+ CVV);
	}

	public void enterExpiryDate(String expiryDate) {
		Assert.assertTrue(txt_ExpiryDate.isEnabled(), "Expiry field is not  enabled");
		txt_ExpiryDate.sendKeys(expiryDate);
		test.log(LogStatus.PASS, "Successfully entered expiryDate as "+ expiryDate);
	}

	public void enterCardNumber(String cardNumber) {
		Assert.assertTrue(txt_CardNumber.isEnabled(), "Card Number is not  enabled");
		txt_CardNumber.sendKeys(cardNumber);
		test.log(LogStatus.PASS, "Successfully entered Card Number as "+ cardNumber);
	}

	public void clickButtonBuy() {
		Assert.assertTrue(btn_buy.isDisplayed(), "Buy button is not  displayed");
		btn_buy.click();
		test.log(LogStatus.PASS, "Successfully clicked on Buy Button");
	}

	public void clickButtonCheckOut() {
		wait.until(ExpectedConditions.visibilityOf(btn_CartCheckOut));
		Assert.assertTrue(btn_CartCheckOut.isDisplayed(), "Check out button is not  displayed");
		btn_CartCheckOut.click();
		test.log(LogStatus.PASS, "Successfully clicked on Check Out Button");
	}

	public void clickButtonContinue() {
		driver.switchTo().frame(frame_PopUpContinue);
		Assert.assertTrue(btn_Continue.isDisplayed(), "Continue button is not  displayed");
		btn_Continue.click();
		test.log(LogStatus.PASS, "Successfully clicked on Continue");
	}

	public void clickLinkCreditCard() {
		wait.until(ExpectedConditions.elementToBeClickable(lnk_CreditCard));
		Assert.assertTrue(lnk_CreditCard.isDisplayed(), "Credit Card option is not  displayed");
		lnk_CreditCard.click();
		test.log(LogStatus.PASS, "Successfully clicked on Credit Card option");
	}

}
