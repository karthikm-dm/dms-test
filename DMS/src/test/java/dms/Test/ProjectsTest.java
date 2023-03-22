package dms.Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import dms.PageObjects.ProjectsPage;
import dms.TestComponents.BaseTest;

public class ProjectsTest extends BaseTest {
	
	
	@Test(priority = 1)
	public void addProjects() throws Exception
	{
		loginpg.login("karthikm@datamatica.uk", "Dm@12345");
		ProjectsPage projpg=new ProjectsPage(driver);
		projpg.projectMenu();
		projpg.createProjectIcon();
		projpg.createProject("proj111", "Dev");
		projpg.save();
		Assert.assertEquals(projpg.alert(),"Project created successfully.");
		Assert.assertTrue(projpg.selProject("proj111"));
		projpg.addProjRole("QA");
		projpg.save();
		Assert.assertEquals(projpg.alert(),"Role created successfullyy");
		Assert.assertTrue(projpg.verifyProjectRole("QA"));
		projpg.addprojUsers("QA");
		projpg.chooseUserDropdown();
		Assert.assertTrue(projpg.chooseUser("testuser1"));
		projpg.chooseUserDropdown();
		projpg.save();
		Assert.assertEquals(projpg.alert(),"User added successfully");
		Assert.assertTrue(projpg.verifyProjectUsers("testuser1"));
		projpg.createFolder();
		projpg.addFolder("projFolder1");
		projpg.saveFolder();
		Assert.assertEquals(projpg.alert(),"Folder created successfully.");
		Assert.assertTrue(projpg.selFolder("projFolder1"));
		projpg.permission();
		projpg.selPermissionDesig("QA");
		projpg.selUser("testuser1");
		projpg.save();
		Assert.assertEquals(projpg.alert(),"Permission added successfully");
	}

	
	@Test(priority = 2)
	public void verifyFolderPermission() throws InterruptedException
	{
		loginpg.login("testuser1@yopmail.com", "Ksfe@123");
		ProjectsPage projpg=new ProjectsPage(driver);
		projpg.projectMenu();
		Assert.assertTrue(projpg.selProject("proj111"));
		Assert.assertTrue(projpg.selFolder("projFolder1"));
	}
	@Test(priority = 3)
	public void folderShare() throws InterruptedException
	{
		loginpg.login("karthikm@datamatica.uk", "Dm@12345");
		ProjectsPage projpg=new ProjectsPage(driver);
		projpg.projectMenu();
		Assert.assertTrue(projpg.selProject("proj111"));//projectname
		projpg.createFolder();
		projpg.addFolder("sharefolder");
		projpg.saveFolder();
		Assert.assertEquals(projpg.alert(),"Folder created successfully.");
	
		Assert.assertTrue(projpg.selFolder("sharefolder"));// project foldername to share
		projpg.share();
		projpg.selPermissionDesig("QA");
		projpg.selUser("testuser1");
		projpg.shareBtn();
		Assert.assertEquals(projpg.alert(),"Folder shared successfully");
		
	}
	@Test(priority = 4)
	public void verifyFolderShare() throws InterruptedException
	{
		loginpg.login("testuser1@yopmail.com", "Ksfe@123");
		ProjectsPage projpg=new ProjectsPage(driver);
		projpg.projectMenu();
		Assert.assertTrue(projpg.selProject("proj111"));
		Assert.assertTrue(projpg.selFolder("sharefolder"));
	}

	@Test(priority = 5)
	public void addDocumentPermission() throws Exception
	{
		loginpg.login("karthikm@datamatica.uk", "Dm@12345");
		ProjectsPage projpg=new ProjectsPage(driver);
		projpg.projectMenu();
		Assert.assertTrue(projpg.selProject("proj111"));
		
		Assert.assertTrue(projpg.createDocument("projFolder1"));//sel the folder
		projpg.createDocumentIcon();
		projpg.fillDocument("doc1","KarthikM");
		projpg.docVersion("v1");
		projpg.uploadDoc("C:\\Users\\DM Administrator\\Documents\\testdatas.xls");
		projpg.save();
		Assert.assertEquals(projpg.alert(),"Documents saved successfully");
		Assert.assertTrue(projpg.verifyDocument("testdatas"));
		Assert.assertTrue(projpg.documentActions("testdatas"));
		projpg.permission();
		projpg.selPermissionDesig("QA");
		projpg.selUser("testuser1");
		projpg.save();
		Assert.assertEquals(projpg.alert(),"Permission added successfully");
		
	}
	@Test(priority = 6)
	public void verifyDocumentPermission() throws InterruptedException
	{
		loginpg.login("testuser1@yopmail.com", "Ksfe@123");
		ProjectsPage projpg=new ProjectsPage(driver);
		projpg.projectMenu();
		Assert.assertTrue(projpg.selProject("proj111"));
		Assert.assertTrue(projpg.createDocument("projFolder1"));//foldername
		Assert.assertTrue(projpg.verifyDocument("testdatas"));
	}
	@Test (priority= 7)
	public void documentShare() throws Exception
	{
		loginpg.login("karthikm@datamatica.uk", "Dm@12345");
		ProjectsPage projpg=new ProjectsPage(driver);
		projpg.projectMenu();
		Assert.assertTrue(projpg.selProject("proj111"));
		Assert.assertTrue(projpg.createDocument("sharefolder"));
		projpg.createDocumentIcon();
		projpg.fillDocument("doc1","KarthikM");
		projpg.docVersion("v1");
		projpg.uploadDoc("C:\\Users\\DM Administrator\\Documents\\testdatas.xls");
		projpg.save();
		Assert.assertEquals(projpg.alert(),"Documents saved successfully");
		Assert.assertTrue(projpg.verifyDocument("testdatas"));
		Assert.assertTrue(projpg.documentActions("testdatas"));
		projpg.share();
		projpg.selPermissionDesig("QA");
		projpg.selUser("testuser1");
		projpg.save();
		Assert.assertEquals(projpg.alert(),"Document shared successfully");
	}
	@Test(priority= 8)
	public void verifyDocumentShare() throws InterruptedException
	{
		loginpg.login("testuser1@yopmail.com", "Ksfe@123");
		ProjectsPage projpg=new ProjectsPage(driver);
		projpg.projectMenu();
		Assert.assertTrue(projpg.selProject("proj111"));
		Assert.assertTrue(projpg.selFolder("sharefolder"));
		Assert.assertTrue(projpg.verifyDocument("testdatas"));
	}
	

}
