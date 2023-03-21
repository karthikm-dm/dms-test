package dms.Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import dms.TestComponents.BaseTest;

public class LoginTest extends BaseTest{
	
	@Test
	public void logintest() throws InterruptedException
	{
		loginpg.login("karthikm@datamatica.uk", "Dm@12345");
		log.info("login success");
		Assert.assertEquals(loginpg.verifyHomepage(),"Dashboard");
		log.info("homepage verified");
		loginpg.profileIcon();
		loginpg.logout();
		log.info("logged out successfully");
	}

}
