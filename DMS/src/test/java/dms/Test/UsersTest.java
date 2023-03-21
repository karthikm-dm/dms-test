package dms.Test;

import org.testng.Assert;
import org.testng.annotations.Test;
import dms.PageObjects.UsersPage;
import dms.TestComponents.BaseTest;

public class UsersTest extends BaseTest{
	
	@Test
	public void addUser() throws InterruptedException
	{
		loginpg.login("karthikm@datamatica.uk", "Dm@12345");
		UsersPage userpg=new UsersPage(driver);
		userpg.usersMenu();
//		userpg.createUser("abc", "def", "abcdef", "demo@yopmail.com");
//		userpg.selDesignation("Test");
//		userpg.selDepartment("Test1");
//		userpg.selUserType("Internal");
		//userpg.saveUser();
		Assert.assertEquals(userpg.userAlert(),"User created successfully");
		Assert.assertTrue(userpg.verifyUsers("Test user","Test","Test1"));
	}
	

}
