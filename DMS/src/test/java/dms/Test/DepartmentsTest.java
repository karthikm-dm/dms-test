package dms.Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import dms.PageObjects.DepartmentsPage;
import dms.TestComponents.BaseTest;

public class DepartmentsTest extends BaseTest{
	
	//Adding main department
	
	@Test(priority = 1)
	public void addDepartment() throws InterruptedException
	{
		loginpg.login("karthikm@datamatica.uk", "Dm@12345");
		DepartmentsPage departpg=new DepartmentsPage(driver);
		//creating main department
		
		departpg.departmentsmenu();
		
//		departpg.createDepartment("Test1dep");
//		departpg.addDepart();
//		Assert.assertEquals(departpg.departmentAlert(),"Department created successfully");
		
		//creating sub department
		Assert.assertTrue(departpg.selMainDepartment("demo"));
		departpg.createDepartment("TestDemo");
		departpg.addDepart();
		Assert.assertEquals(departpg.departmentAlert(),"Department created successfully");
		
		//creating folder under sub department
		departpg.selMainDepartment("demo");
		departpg.selSubDepartment("TestDemo");  //sub dept
		Assert.assertEquals(departpg.verifyFolderHead(),"TestDemo");
		
		departpg.createFolder();
		departpg.addFolder("folderTest");
		departpg.saveFolder();
		Assert.assertEquals(departpg.folderAlert(),"Folder created successfully");
		departpg.selFolder("folderTest");
		departpg.permission();
		departpg.selPermissionDesig("Test");
		departpg.selUser("testuser1");
		departpg.savePermission();
		Assert.assertEquals(departpg.permissionAlert(),"Permission added successfully");
	}
	@Test(priority = 2)
	public void verifyFolderPermission() throws InterruptedException
	{
		loginpg.login("testuser1@yopmail.com", "Ksfe@123");
		DepartmentsPage departpg=new DepartmentsPage(driver);
		departpg.departmentsmenu();
		departpg.selMainDepartment("demo");
		departpg.selSubDepartment("TestDemo");
		Assert.assertEquals(departpg.verifyFolderHead(),"TestDemo");
		Assert.assertTrue(departpg.verifyfolderpermission("folderTest"));
	}
	@Test(priority = 3)
	public void folderShare() throws InterruptedException
	{
		loginpg.login("karthikm@datamatica.uk", "Dm@12345");
		DepartmentsPage departpg=new DepartmentsPage(driver);
		departpg.departmentsmenu();
		departpg.selMainDepartment("demo");
		departpg.selSubDepartment("TestDemo");
		Assert.assertEquals(departpg.verifyFolderHead(),"TestDemo");
		
		departpg.createFolder();
		departpg.addFolder("folderShare");
		departpg.saveFolder();
		Assert.assertEquals(departpg.folderAlert(),"Folder created successfully");
		Assert.assertTrue(departpg.selFolder("folderShare"));
		departpg.sharefolder();
		departpg.selPermissionDesig("Test");
		departpg.selUser("testuser1");
		departpg.shareBtn();
		Assert.assertEquals(departpg.permissionAlert(),"Folder shared successfully");
	}
	@Test(priority = 4)
	public void verifyFolderShare() throws InterruptedException
	{
		loginpg.login("testuser1@yopmail.com", "Ksfe@123");
		DepartmentsPage departpg=new DepartmentsPage(driver);
		departpg.departmentsmenu();
		departpg.selMainDepartment("demo");
		departpg.selSubDepartment("TestDemo");
		Assert.assertEquals(departpg.verifyFolderHead(),"TestDemo");
		Assert.assertTrue(departpg.verifyfolderpermission("folderShare"));
	}
	@Test(priority = 5)
	public void addDocument() throws Exception
	{
		loginpg.login("karthikm@datamatica.uk", "Dm@12345");
		DepartmentsPage departpg=new DepartmentsPage(driver);
		departpg.departmentsmenu();
		departpg.selMainDepartment("demo"); //main dep
		departpg.selSubDepartment("TestDemo"); //sub dep
		Assert.assertEquals(departpg.verifyFolderHead(),"TestDemo");
		Assert.assertTrue(departpg.createDocument("folderTest"));	//folder
		departpg.createDocumentIcon();
		departpg.fillDocument("doctest","KarthikM");
		departpg.saveDocument("v1");
		departpg.uploadDoc("C:\\Users\\DM Administrator\\Documents\\testdatas.xls");
		departpg.saveFolder();
		Assert.assertEquals(departpg.documentAlert(),"Documents saved successfully");

		Assert.assertTrue(departpg.verifyDocument("testdatas.xls"));
		Assert.assertTrue(departpg.documentActions("testdatas.xls"));
		departpg.permission();
		departpg.selPermissionDesig("Test");
		departpg.selUser("testuser1");
		departpg.savePermission();
		Assert.assertEquals(departpg.permissionAlert(),"Permission added successfully");
		
	}
	@Test(priority = 6)
	public void verifyDocumentsPermission() throws InterruptedException
	{
		loginpg.login("testuser1@yopmail.com", "Ksfe@123");
		DepartmentsPage departpg=new DepartmentsPage(driver);
		departpg.departmentsmenu();
		departpg.selMainDepartment("demo");
		departpg.selSubDepartment("TestDemo");
		Assert.assertEquals(departpg.verifyFolderHead(),"TestDemo");
		Assert.assertTrue(departpg.createDocument("folderTest"));
		Assert.assertTrue(departpg.verifyDocument("testdatas.xls"));
	}
	
	
	@Test(priority = 7)
	public void documentShare() throws Exception
	{
		loginpg.login("karthikm@datamatica.uk", "Dm@12345");
		DepartmentsPage departpg=new DepartmentsPage(driver);
		departpg.departmentsmenu();
		departpg.selMainDepartment("demo");
		departpg.selSubDepartment("TestDemo");
		Assert.assertEquals(departpg.verifyFolderHead(),"TestDemo");
		Assert.assertTrue(departpg.createDocument("folderShare"));
		departpg.createDocumentIcon();
		departpg.fillDocument("doctest","KarthikM");
		departpg.saveDocument("v1");
		departpg.uploadDoc("C:\\Users\\DM Administrator\\Documents\\testdatasample.xlsx");
		departpg.saveFolder();
		Assert.assertEquals(departpg.documentAlert(),"Documents saved successfully");

		//verify document is present
		Assert.assertTrue(departpg.verifyDocument("testdatasample.xlsx"));
		Assert.assertTrue(departpg.documentActions("testdatasample.xlsx"));
		
		
		departpg.sharefolder();
		departpg.selPermissionDesig("Test");
		departpg.selUser("testuser1");
		departpg.saveBtn();
		Assert.assertEquals(departpg.permissionAlert(),"Document shared successfully");
	}
	@Test(priority = 8)
	public void verifyDocumentShare() throws InterruptedException
	{
		loginpg.login("testuser1@yopmail.com", "Ksfe@123");
		DepartmentsPage departpg=new DepartmentsPage(driver);
		departpg.departmentsmenu();
		departpg.selMainDepartment("demo");
		departpg.selSubDepartment("TestDemo");
		Assert.assertEquals(departpg.verifyFolderHead(),"TestDemo");
		Assert.assertTrue(departpg.createDocument("folderShare"));
		Assert.assertTrue(departpg.verifyDocument("testdatasample.xlsx"));
	}
//	@Test
//	public void documentEdit() throws Exception
//	{
//		loginpg.login("karthikm@datamatica.uk", "Dm@12345");
//		DepartmentsPage departpg=new DepartmentsPage(driver);
//		departpg.departmentsmenu();
//		departpg.selMainDepartment("demo");
//		departpg.selSubDepartment("TestDemo");
//		Assert.assertEquals(departpg.verifyFolderHead(),"TestDemo");
//		Assert.assertTrue(departpg.verifyDocument("testdatasample.xlsx"));
//		Assert.assertTrue(departpg.documentActions("testdatasample.xlsx"));
//		departpg.editOption();
//		
//	}
}
