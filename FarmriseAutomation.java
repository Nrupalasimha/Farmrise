/*******************************************
	 * Method Name		: FarmriseAutomation
	 * Author			: Nrupala
	 * Date Created		: 21/01/2019
	 * Date Reviewed	: NA
	 * Application Name : Climate Farm Rise
	 ******************************************* 
	 */
package com.Farmrise;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.MobileCapabilityType;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.jasper.tagplugins.jstl.core.Url;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FarmriseAutomation {

	public static void main(String[] args) throws Exception {
		
		DesiredCapabilities cap = null;
		AndroidDriver<AndroidElement> driver = null;
		Dimension dim = null;
		Dimension size1 = null;
		
		try {		
			cap = new DesiredCapabilities();
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, "POCO F1");//Give Device name of your phone
		//	cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		//	cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0");//Give platform version of your phone
			
			
			/*launching the Farmrise app by providing package name and activity name*/
			cap.setCapability("appPackage", "com.climate.farmrise");
			cap.setCapability("appActivity", "com.climate.farmrise.SplashScreen");
			cap.setCapability(MobileCapabilityType.NO_RESET, "true");
					
			URL url=new URL("http://127.0.0.1:4723/wd/hub");
		    driver=new AndroidDriver<>(url, cap);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			/*Verifying the scenario1 : Home screen->Access Weather details->timings in the horizantal scroll from "now" to 23 hours*/
			driver.findElement(By.id("com.climate.farmrise:id/checkWeatherDetails")).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
					
			dim = driver.manage().window().getSize();
			int height = dim.getHeight();
			int width = dim.getWidth();
			
			int n=1;
			while(n<=5)
			{
			driver.swipe(660, 1000, 60, 1000, 3000);
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			n=n+1;
			}
			
			System.out.println("Scenario1 Executed");

			/*Verifying the scenario2 : More Tab->Government schemes->scroll till load more schemes and tapping on search and input "scheme"*/ 
			driver.findElement(By.id("com.climate.farmrise:id/action_more")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.id("com.climate.farmrise:id/more_govtSchemes")).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);		
				
			size1 = driver.manage().window().getSize();
			System.out.println(size1);
			int sy = (int)(size1.height*0.8);
			int ey = (int)(size1.height *0.01);
			int sx = size1.width/2;
			int ex = size1.width/2;
			
			driver.swipe(sx,sy,ex,ey, 3000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			//Scrolled till bottom but i dint find "Load more schemes" button hence could not verify it
			
			driver.findElement(By.id("android:id/search_button")).click();
			
			//Entering the text "scheme" in the search field
			driver.findElement(By.id("android:id/search_src_text")).sendKeys("scheme");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.pressKeyCode(AndroidKeyCode.KEYCODE_SEARCH);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			System.out.println("Scenario2 executed");
			System.out.println("**********************");

		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}

