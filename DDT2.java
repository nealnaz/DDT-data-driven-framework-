package facebook.com;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import jxl.read.biff.BiffException;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import jxl.Sheet;
import jxl.Workbook;

public class DDT2 {
	WebDriver driver;
	Workbook wb;
	Sheet sh1;
	int numrow;
	String username;
	String password;
	 
	@BeforeTest
	public void Setup()
	 
	{
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\zubair\\Desktop\\NewAutomation\\chromedriver.exe");
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("http://www.facebook.com");
	driver.manage().timeouts().implicitlyWait(1,TimeUnit.MINUTES);
	}
	 
	@Test(dataProvider="testdata")
	public void TestFireFox(String uname,String password1)
	 
	{
	 
	driver.findElement(By.id("email")).clear();
	driver.findElement(By.id("email")).sendKeys(uname);
	driver.findElement(By.id("pass")).clear();
	driver.findElement(By.id("pass")).sendKeys(password1);
	}
	 
	@DataProvider(name="testdata")
	public Object[][] TestDataFeed(){
	 
	try {
	 
	// load workbook
	wb=Workbook.getWorkbook (new File("C:\\Users\\zubair\\eclipse-workspace\\DDT framework\\x\\Testdata.xls.xls"));
	 
	// load sheet in my case I am referring to first sheet only
	sh1= wb.getSheet(0);
	 
	// get number of rows so that we can run loop based on this
	numrow=  sh1.getRows();
	}
	catch (Exception e)
	 
	{
	e.printStackTrace();
	}
	 
	// Create 2 D array and pass row and columns
	Object [][] facebookdata=new Object[numrow][sh1.getColumns()];
	 
	// This will run a loop and each iteration  it will fetch new row
	for(int i=0;i<numrow;i++){
	 
	// Fetch first row username
	facebookdata[i][0]=sh1.getCell(0,i).getContents();
	// Fetch first row password
	facebookdata[i][1]=sh1.getCell(1,i).getContents();
	 
	}
	 
	// Return 2d array object so that test script can use the same
	return facebookdata;
	}
	 
	@AfterTest
	public void QuitTC(){
	 
	// close browser after execution
	driver.close();
	}

}
