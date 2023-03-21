package dms.Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import dms.TestComponents.BaseTest;

public class LoginTest extends BaseTest{
	
	@Test
	public void logintest() throws InterruptedException
	{
		loginpg.login("karthikm@datamatica.uk", "Dm@12345");
		Assert.assertEquals(loginpg.verifyHomepage(),"Dashboard");
		loginpg.profileIcon();
		loginpg.logout();
	}

}
