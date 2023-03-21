package dms.Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import dms.PageObjects.DesignationPage;
import dms.TestComponents.BaseTest;

public class CreateDesignation extends BaseTest{
	
	@Test
	public void AddDesignation() throws InterruptedException
	{
		loginpg.login("karthikm@datamatica.uk", "Dm@12345");
		DesignationPage desigpg=new DesignationPage(driver);
		desigpg.designationMenu();
		//desigpg.createDesigIcon();
		Thread.sleep(3000);
		//desigpg.createDesignation("designation", "desc");
		Assert.assertEquals(desigpg.designationAlert(),"Designation created successfully");
		Assert.assertTrue(desigpg.verifyDesignation("DMS"));
		desigpg.selPrivilege();
		
	}
	
}
