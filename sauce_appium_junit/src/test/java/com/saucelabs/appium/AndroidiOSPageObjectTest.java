package com.saucelabs.appium;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

import com.saucelabs.appium.page_object.AndroidiOsApiDemosListViewScreenSimple;
import com.saucelabs.appium.page_object.android.ApiDemosListViewScreenByAllPossible;
import com.saucelabs.appium.page_object.android.ApiDemosListViewScreenChaided;
import com.saucelabs.appium.page_object.android.ApiDemosListViewScreenSimple;
import com.saucelabs.appium.page_object.ios.TestAppScreenSimple;

/**
 * Please read about Page Object design pattern here:
 *  https://code.google.com/p/selenium/wiki/PageObjects
 *  
 *  Please look at:
 *  {@link ApiDemosListViewScreenSimple}
 *  {@link ApiDemosListViewScreenChaided}
 *  {@link ApiDemosListViewScreenByAllPossible}
 *  {@link TestAppScreenSimple}
 *
 */
public class AndroidiOSPageObjectTest {
	
	private WebDriver driver;
	//private TestAppScreenSimple uiTestApp;
	private AndroidiOsApiDemosListViewScreenSimple uiTestApp;
	//@Before
	public void setUp() throws Exception {
        File appDir = new File(System.getProperty("user.dir"), 
        		"../../../apps/TestApp/build/release-iphonesimulator");
        File app = new File(appDir, "TestApp.app");
	    DesiredCapabilities capabilities = new DesiredCapabilities();
	    capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
	    capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0");
	    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone Simulator");
	    capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
	    
	    uiTestApp = new AndroidiOsApiDemosListViewScreenSimple();
	    driver = new IOSDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);

		PageFactory.initElements(new AppiumFieldDecorator(driver), uiTestApp);
	}
	
	 @Before
	public void setUp2() throws Exception {
		 File classpathRoot = new File(System.getProperty("user.dir"));
	        File appDir = new File(classpathRoot, "../../../apps/ApiDemos/bin");
	    
	    File app = new File(appDir, "ApiDemos-debug.apk");
	    DesiredCapabilities capabilities = new DesiredCapabilities();
	    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
	    capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
	    driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        
	    uiTestApp = new AndroidiOsApiDemosListViewScreenSimple();
	    //This time out is set because test can be run on slow Android SDK emulator
		PageFactory.initElements(new AppiumFieldDecorator(driver, 5, TimeUnit.SECONDS), 
				uiTestApp);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
	
	/**
	 * Page Object best practice is to describe interactions with target 
	 * elements by methods. These methods describe business logic of the page/screen.
	 * Here test interacts with lazy instantiated elements directly.
	 * It was done so just for obviousness
	 */

	 
 

	
	


	@Test
	public void androidOrIOSFindByElementsTest(){
		System.out.println("uiTestApp.androidOriOsTextViews.size()="+uiTestApp.androidOriOsTextViews.size());
		Assert.assertNotEquals(0, uiTestApp.androidOriOsTextViews.size());
	}

	@Test
	public void androidOrIOSFindByElementTest(){
		System.out.println("uiTestApp.androidOriOsTextView.getText()="+uiTestApp.androidOriOsTextView.getText());
		Assert.assertNotEquals(null, uiTestApp.androidOriOsTextView.getText());
	}
	
	//@Test
	public void checkThatElementWasNotFoundByAndroidUIAutomator(){
		NoSuchElementException nsee = null;
		try{
			uiTestApp.androidUIAutomatorView.getText();
		}
		catch (Exception e){
			nsee = (NoSuchElementException) e;
		}
		Assert.assertNotNull(nsee);
	}

	//@Test
	public void checkThatElementsWereNotFoundByAndroidUIAutomator(){
		Assert.assertEquals(0, uiTestApp.androidUIAutomatorViews.size());
	}
 
   

	//@Test
	public void areRemoteElementsTest(){
		Assert.assertNotEquals(0, uiTestApp.remoteElementViews.size());
	}

	//@Test
	public void isRemoteElementTest(){
		Assert.assertNotEquals(null, uiTestApp.remotetextVieW.getText());
	}
 

	 
	 

}
