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

import dms.PageObjects.DepartmentsPage;
import dms.TestComponents.BaseTest;

public class DepartmentsTest extends BaseTest{
	
	DataFormatter formatter=new DataFormatter();
	//Adding main department
	
	@Test(priority = 1,dataProvider = "data")
	public void addDepartment(String pemail,String ppass,String semail,String spass,String maindept,String subdept,String pfolder,String desig,String username,String sfolder,String doctitle,String docowner,String docversion,String docpath,String docname) throws InterruptedException
	{
		loginpg.login(pemail,ppass);
		log.info("Logged in");
		DepartmentsPage departpg=new DepartmentsPage(driver);
		//creating main department
		
		departpg.departmentsmenu();
		
//		departpg.createDepartment("Test1dep");
//		departpg.addDepart();
//		Assert.assertEquals(departpg.departmentAlert(),"Department created successfully");
		
		//creating sub department
		Assert.assertTrue(departpg.selMainDepartment(maindept));
		departpg.createDepartment(subdept);
		departpg.addDepart();
		Assert.assertEquals(departpg.departmentAlert(),"Department created successfully");
		log.info("sub department added");
		//creating folder under sub department
		departpg.selMainDepartment(maindept);
		departpg.selSubDepartment(subdept);  //sub dept
		Assert.assertEquals(departpg.verifyFolderHead(),subdept);
		
		departpg.createFolder();
		departpg.addFolder(pfolder);
		departpg.saveFolder();
		Assert.assertEquals(departpg.folderAlert(),"Folder created successfully");
		log.info("Department folder created");
		departpg.selFolder(pfolder);
		departpg.permission();
		departpg.selPermissionDesig(desig);
		departpg.selUser(username);
		departpg.savePermission();
		Assert.assertEquals(departpg.permissionAlert(),"Permission added successfully");
		log.info("Folder permission added");
	}
	@Test(priority = 2,dataProvider = "data")
	public void verifyFolderPermission(String pemail,String ppass,String semail,String spass,String maindept,String subdept,String pfolder,String desig,String username,String sfolder,String doctitle,String docowner,String docversion,String docpath,String docname) throws InterruptedException
	{
		loginpg.login(semail,spass);
		DepartmentsPage departpg=new DepartmentsPage(driver);
		departpg.departmentsmenu();
		departpg.selMainDepartment(maindept);
		departpg.selSubDepartment(subdept);
		Assert.assertEquals(departpg.verifyFolderHead(),subdept);
		Assert.assertTrue(departpg.verifyfolderpermission(pfolder));
		log.info("folder permission verified");
	}
	@Test(priority = 3,dataProvider = "data")
	public void folderShare(String pemail,String ppass,String semail,String spass,String maindept,String subdept,String pfolder,String desig,String username,String sfolder,String doctitle,String docowner,String docversion,String docpath,String docname) throws InterruptedException
	{
		loginpg.login(pemail,ppass);
		DepartmentsPage departpg=new DepartmentsPage(driver);
		departpg.departmentsmenu();
		departpg.selMainDepartment(maindept);
		departpg.selSubDepartment(subdept);
		Assert.assertEquals(departpg.verifyFolderHead(),subdept);
		
		departpg.createFolder();
		departpg.addFolder(sfolder);
		departpg.saveFolder();
		Assert.assertEquals(departpg.folderAlert(),"Folder created successfully");
		log.info("Folder created");
		Assert.assertTrue(departpg.selFolder(sfolder));
		departpg.sharefolder();
		departpg.selPermissionDesig(desig);
		departpg.selUser(username);
		departpg.shareBtn();
		Assert.assertEquals(departpg.permissionAlert(),"Folder shared successfully");
		log.info("Folder shared");
	}
	@Test(priority = 4,dataProvider = "data")
	public void verifyFolderShare(String pemail,String ppass,String semail,String spass,String maindept,String subdept,String pfolder,String desig,String username,String sfolder,String doctitle,String docowner,String docversion,String docpath,String docname) throws InterruptedException
	{
		loginpg.login(semail,spass);
		DepartmentsPage departpg=new DepartmentsPage(driver);
		departpg.departmentsmenu();
		departpg.selMainDepartment(maindept);
		departpg.selSubDepartment(subdept);
		Assert.assertEquals(departpg.verifyFolderHead(),subdept);
		Assert.assertTrue(departpg.verifyfolderpermission(sfolder));
		log.info("Folder share verified");
	}
	@Test(priority = 5,dataProvider = "data")
	public void addDocument(String pemail,String ppass,String semail,String spass,String maindept,String subdept,String pfolder,String desig,String username,String sfolder,String doctitle,String docowner,String docversion,String docpath,String docname) throws Exception
	{
		loginpg.login(pemail,ppass);
		DepartmentsPage departpg=new DepartmentsPage(driver);
		departpg.departmentsmenu();
		departpg.selMainDepartment(maindept); //main dep
		departpg.selSubDepartment(subdept); //sub dep
		Assert.assertEquals(departpg.verifyFolderHead(),subdept);
		Assert.assertTrue(departpg.createDocument(pfolder));	//folder
		departpg.createDocumentIcon();
		departpg.fillDocument(doctitle,docowner);
		departpg.saveDocument(docversion);
		departpg.uploadDoc(docpath);
		departpg.saveFolder();
		Assert.assertEquals(departpg.documentAlert(),"Documents saved successfully");
		
		Assert.assertTrue(departpg.verifyDocument(docname));
		log.info("Document added");
		Assert.assertTrue(departpg.documentActions(docname));
		departpg.permission();
		departpg.selPermissionDesig(desig);
		departpg.selUser(username);
		departpg.savePermission();
		Assert.assertEquals(departpg.permissionAlert(),"Permission added successfully");
		log.info("Document permission added");
	}
	@Test(priority = 6,dataProvider = "data")
	public void verifyDocumentsPermission(String pemail,String ppass,String semail,String spass,String maindept,String subdept,String pfolder,String desig,String username,String sfolder,String doctitle,String docowner,String docversion,String docpath,String docname) throws InterruptedException
	{
		loginpg.login(semail,spass);
		DepartmentsPage departpg=new DepartmentsPage(driver);
		departpg.departmentsmenu();
		departpg.selMainDepartment(maindept);
		departpg.selSubDepartment(subdept);
		Assert.assertEquals(departpg.verifyFolderHead(),subdept);
		Assert.assertTrue(departpg.createDocument(pfolder));
		Assert.assertTrue(departpg.verifyDocument(docname));
		log.info("Department document permission verified");
	}
	
	
	@Test(priority = 7,dataProvider = "data")
	public void documentShare(String pemail,String ppass,String semail,String spass,String maindept,String subdept,String pfolder,String desig,String username,String sfolder,String doctitle,String docowner,String docversion,String docpath,String docname) throws Exception
	{
		loginpg.login(pemail,ppass);
		DepartmentsPage departpg=new DepartmentsPage(driver);
		departpg.departmentsmenu();
		departpg.selMainDepartment(maindept);
		departpg.selSubDepartment(subdept);
		Assert.assertEquals(departpg.verifyFolderHead(),subdept);
		Assert.assertTrue(departpg.createDocument(sfolder));
		departpg.createDocumentIcon();
		departpg.fillDocument(doctitle,docowner);
		departpg.saveDocument(docversion);
		departpg.uploadDoc(docpath);
		departpg.saveFolder();
		Assert.assertEquals(departpg.documentAlert(),"Documents saved successfully");
		
		//verify document is present
		Assert.assertTrue(departpg.verifyDocument(docname));
		log.info("Document added");
		Assert.assertTrue(departpg.documentActions(docname));
		
		
		departpg.sharefolder();
		departpg.selPermissionDesig(desig);
		departpg.selUser(username);
		departpg.saveBtn();
		Assert.assertEquals(departpg.permissionAlert(),"Document shared successfully");
		log.info("Document shared");
	}
	@Test(priority = 8,dataProvider = "data")
	public void verifyDocumentShare(String pemail,String ppass,String semail,String spass,String maindept,String subdept,String pfolder,String desig,String username,String sfolder,String doctitle,String docowner,String docversion,String docpath,String docname) throws InterruptedException
	{
		loginpg.login(semail,spass);
		DepartmentsPage departpg=new DepartmentsPage(driver);
		departpg.departmentsmenu();
		departpg.selMainDepartment(maindept);
		departpg.selSubDepartment(subdept);
		Assert.assertEquals(departpg.verifyFolderHead(),subdept);
		Assert.assertTrue(departpg.createDocument(sfolder));
		Assert.assertTrue(departpg.verifyDocument(docname));
		log.info("Department document share verified");
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
	
	
	@DataProvider(name="data")
	public Object[][] getData() throws Exception 
	{
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/dms/Resources/dmsdata.xlsx");
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		XSSFSheet sheet= wb.getSheetAt(0);
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
