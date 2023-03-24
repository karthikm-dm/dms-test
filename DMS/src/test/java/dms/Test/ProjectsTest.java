package dms.Test;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dms.PageObjects.ProjectsPage;
import dms.TestComponents.BaseTest;

public class ProjectsTest extends BaseTest {
	
	DataFormatter formatter=new DataFormatter();
	
	@Test(priority = 1,dataProvider = "data")
	public void addProjects(String pemail,String ppass,String semail,String spass,String projname,String projtype,String projrole,String projuser,String pfolder,String desig,String username,String sfolder,String doctitle,String docowner,String docversion,String docpath,String docname) throws Exception
	{
		loginpg.login(pemail,ppass);
		ProjectsPage projpg=new ProjectsPage(driver);
		projpg.projectMenu();
		projpg.createProjectIcon();
		projpg.createProject(projname,projtype);
		projpg.save();
		Assert.assertEquals(projpg.alert(),"Project created successfully.");
		log.info("Project created");
		
		Assert.assertTrue(projpg.selProject(projname));
		projpg.addProjRole(projrole);
		projpg.save();
		Assert.assertEquals(projpg.alert(),"Role created successfully");
		log.info("Project Role added");
		Assert.assertTrue(projpg.verifyProjectRole(projrole));
		projpg.addprojUsers(projrole);
		projpg.chooseUserDropdown();
		Assert.assertTrue(projpg.chooseUser(projuser));
		projpg.chooseUserDropdown();
		projpg.save();
		Assert.assertEquals(projpg.alert(),"User added successfully");
		log.info("Project user added");
		Assert.assertTrue(projpg.verifyProjectUsers(projuser));
		projpg.createFolder();
		projpg.addFolder(pfolder);
		projpg.saveFolder();
		Assert.assertEquals(projpg.alert(),"Folder created successfully.");
		log.info("Project folder created");
		Assert.assertTrue(projpg.selFolder(pfolder));
		projpg.permission();
		projpg.selPermissionDesig(projrole);
		projpg.selUser(projuser);
		projpg.save();
		Assert.assertEquals(projpg.alert(),"Permission added successfully");
		log.info("Folder permission added");
	}

	
	@Test(priority = 2,dataProvider = "data")
	public void verifyFolderPermission(String pemail,String ppass,String semail,String spass,String projname,String projtype,String projrole,String projuser,String pfolder,String desig,String username,String sfolder,String doctitle,String docowner,String docversion,String docpath,String docname) throws InterruptedException
	{
		loginpg.login(semail,spass);
		ProjectsPage projpg=new ProjectsPage(driver);
		projpg.projectMenu();
		Assert.assertTrue(projpg.selProject(projname));
		log.info("Project selected");
		Assert.assertTrue(projpg.selFolder(pfolder));
		log.info("Project folder permission verified");
	}
	@Test(priority = 3,dataProvider = "data")
	public void folderShare(String pemail,String ppass,String semail,String spass,String projname,String projtype,String projrole,String projuser,String pfolder,String desig,String username,String sfolder,String doctitle,String docowner,String docversion,String docpath,String docname) throws InterruptedException
	{
		loginpg.login(pemail,ppass);
		ProjectsPage projpg=new ProjectsPage(driver);
		projpg.projectMenu();
		Assert.assertTrue(projpg.selProject(projname));//projectname
		projpg.createFolder();
		projpg.addFolder(sfolder);
		projpg.saveFolder();
		Assert.assertEquals(projpg.alert(),"Folder created successfully.");
		log.info("Folder added");
		Assert.assertTrue(projpg.selFolder(sfolder));// project foldername to share
		projpg.share();
		projpg.selPermissionDesig(projrole);
		projpg.selUser(projuser);
		projpg.shareBtn();
		Assert.assertEquals(projpg.alert(),"Folder shared successfully");
		log.info("Folder shared");
	}
	@Test(priority = 4,dataProvider = "data")
	public void verifyFolderShare(String pemail,String ppass,String semail,String spass,String projname,String projtype,String projrole,String projuser,String pfolder,String desig,String username,String sfolder,String doctitle,String docowner,String docversion,String docpath,String docname) throws InterruptedException
	{
		loginpg.login(semail,spass);
		ProjectsPage projpg=new ProjectsPage(driver);
		projpg.projectMenu();
		Assert.assertTrue(projpg.selProject(projname));
		Assert.assertTrue(projpg.selFolder(sfolder));
		log.info("Project folder share verified");
	}

	@Test(priority = 5,dataProvider = "data")
	public void addDocumentPermission(String pemail,String ppass,String semail,String spass,String projname,String projtype,String projrole,String projuser,String pfolder,String desig,String username,String sfolder,String doctitle,String docowner,String docversion,String docpath,String docname) throws Exception
	{
		loginpg.login(pemail,ppass);
		ProjectsPage projpg=new ProjectsPage(driver);
		projpg.projectMenu();
		Assert.assertTrue(projpg.selProject(projname));
		
		Assert.assertTrue(projpg.createDocument(pfolder));//sel the folder
		projpg.createDocumentIcon();
		projpg.fillDocument(doctitle,docowner);
		projpg.docVersion(docversion);
		projpg.uploadDoc(docpath);
		projpg.save();
		Assert.assertEquals(projpg.alert(),"Documents saved successfully");
		Assert.assertTrue(projpg.verifyDocument(docname));
		log.info("Project document added");
		Assert.assertTrue(projpg.documentActions(docname));
		projpg.permission();
		projpg.selPermissionDesig(projrole);
		projpg.selUser(projuser);
		projpg.save();
		Assert.assertEquals(projpg.alert(),"Permission added successfully");
		log.info("Project document permission added");
	}
	@Test(priority = 6,dataProvider = "data")
	public void verifyDocumentPermission(String pemail,String ppass,String semail,String spass,String projname,String projtype,String projrole,String projuser,String pfolder,String desig,String username,String sfolder,String doctitle,String docowner,String docversion,String docpath,String docname) throws InterruptedException
	{
		loginpg.login(semail,spass);
		ProjectsPage projpg=new ProjectsPage(driver);
		projpg.projectMenu();
		Assert.assertTrue(projpg.selProject(projname));
		Assert.assertTrue(projpg.createDocument(pfolder));//foldername
		Assert.assertTrue(projpg.verifyDocument(docname));
		log.info("Project document permission verified");
	}
	@Test (priority= 7,dataProvider = "data")
	public void documentShare(String pemail,String ppass,String semail,String spass,String projname,String projtype,String projrole,String projuser,String pfolder,String desig,String username,String sfolder,String doctitle,String docowner,String docversion,String docpath,String docname) throws Exception
	{
		loginpg.login(pemail,ppass);
		ProjectsPage projpg=new ProjectsPage(driver);
		projpg.projectMenu();
		Assert.assertTrue(projpg.selProject(projname));
		Assert.assertTrue(projpg.createDocument(sfolder));
		projpg.createDocumentIcon();
		projpg.fillDocument(doctitle,docowner);
		projpg.docVersion(docversion);
		projpg.uploadDoc(docpath);
		projpg.save();
		Assert.assertEquals(projpg.alert(),"Documents saved successfully");
		Assert.assertTrue(projpg.verifyDocument(docname));
		log.info("Project document added");
		Assert.assertTrue(projpg.documentActions(docname));
		projpg.share();
		projpg.selPermissionDesig(projrole);
		projpg.selUser(projuser);
		projpg.save();
		Assert.assertEquals(projpg.alert(),"Document shared successfully");
		log.info("Project document shared");
	}
	@Test(priority= 8,dataProvider = "data")
	public void verifyDocumentShare(String pemail,String ppass,String semail,String spass,String projname,String projtype,String projrole,String projuser,String pfolder,String desig,String username,String sfolder,String doctitle,String docowner,String docversion,String docpath,String docname) throws InterruptedException
	{
		loginpg.login(semail,spass);
		ProjectsPage projpg=new ProjectsPage(driver);
		projpg.projectMenu();
		Assert.assertTrue(projpg.selProject(projname));
		Assert.assertTrue(projpg.selFolder(sfolder));
		Assert.assertTrue(projpg.verifyDocument(docname));
		log.info("Project document share verified");
	}
	
	@DataProvider(name="data")
	public Object[][] getData() throws Exception 
	{
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/dms/Resources/dmsdata.xlsx");
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		XSSFSheet sheet= wb.getSheetAt(1);
		int rowCount=sheet.getPhysicalNumberOfRows();
		XSSFRow row=sheet.getRow(1);
		int colCount=row.getLastCellNum();
		Object data[][]=new Object[rowCount-1][colCount];
		for(int i=0;i<(rowCount-1);i++)
		{
			row=sheet.getRow(i+1);
			for(int j=0;j<colCount;j++)
			{
				XSSFCell cell=row.getCell(j);
				data[i][j]=formatter.formatCellValue(cell);
			}
		}
		return data;	
	}	

}
