package dms.PageObjects;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import dms.AbstractComponents.AbstractComponent;

public class DepartmentsPage extends AbstractComponent{

	WebDriver driver;

	public DepartmentsPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(xpath="//a[@href='/department']")
	WebElement departmentMenu;
	@FindBy(xpath="//img[@alt='create organization']")
	WebElement createDepartmentIcon;
	@FindBy(xpath="//div[@class='modal-content']//input[@type='text']")
	WebElement departmentName;
	@FindBy(xpath="//div[@class='modal-content']//span[@class='input-group-append']")
	WebElement addDepartment;

	@FindBy(xpath="(//h5)[2]")
	WebElement folderHeading;
	
	
	@FindBy(xpath="(//img[@alt='create department'])[1]")
	WebElement createFolderIcon;
	
	@FindBy(xpath="//label[contains(text(),'Name')]//following-sibling::input[1]")
	WebElement folderName;
	
	@FindBy(xpath="//button[contains(text(),'Save')]")
	WebElement save;

	@FindBy(xpath="(//div[@class='ibox-content scrollable-dept'])[1]//label")
	List<WebElement> depTree;

	@FindBy(xpath="//div[@id='primaryTree']//label")
	List<WebElement> folders;
	
	@FindBy(xpath="//div[@id='primaryTree']//div[contains(@class,'more-option')]")
	List<WebElement> optionsIcon; 

	@FindBy(xpath="//div[contains(text(),'Permissions')]")
	WebElement permission;
	
	@FindBy(xpath="//select[@name='account']")
	WebElement selectdesignation;
	@FindBy(xpath="//div[@class='modal-body']//td[1]")
	List<WebElement> permissionUser;
	@FindBy(xpath="//td[1]/input[@type='checkbox']")
	List<WebElement> checkbox;
	@FindBy(xpath="//div[contains(text(),'Share')]")
	WebElement share;
	@FindBy(xpath="//div[contains(text(),'Edit')]")
	WebElement edit;

	@FindBy(xpath="//button[contains(text(),'Share')]")
	WebElement sharebtn;
	//Document screen
	@FindBy(xpath="(//img[@alt='create department'])[2]")
	WebElement createDocIcon;
	
	@FindBy(xpath="//label[contains(text(),'Title')]//following-sibling::input[1]")
	WebElement docTitle;
	@FindBy(xpath="//select[@id='owner']")
	WebElement docowner;
	@FindBy(xpath="//select[@id='document type']")
	WebElement doctype;
	@FindBy(xpath="//label[contains(text(),'Version')]//following-sibling::input[1]")
	WebElement docversion;
	@FindBy(xpath="//button[contains(text(),'Browse')]")
	WebElement browsedoc;
	@FindBy(xpath="//div[@id='primaryDocumentTree']//tr")
	List<WebElement> documents;
	
	@FindBy(xpath="//div[@id='primaryDocumentTree']//tr//i")
	List<WebElement> documentoptions;
	
	public void departmentsmenu() throws InterruptedException
	{
		elementWait(departmentMenu);
		Thread.sleep(3000);
		departmentMenu.click();
		Thread.sleep(2000);
	}
	public void createDepartment(String depName) throws InterruptedException
	{
		Thread.sleep(3000);
		elementWait(createDepartmentIcon);
		createDepartmentIcon.click();
		elementWait(departmentName);
		departmentName.sendKeys(depName);
	}
	public void addDepart() throws InterruptedException
	{
		Thread.sleep(3000);
		elementWait(addDepartment);
		addDepartment.click();
	}
	public String departmentAlert() throws InterruptedException
	{
		Thread.sleep(3000);
		Alert alt=driver.switchTo().alert();
		String msg=alt.getText();
		alt.accept();
		return msg;
	}

	public boolean selMainDepartment(String maindep) throws InterruptedException
	{
		Thread.sleep(3000);
		elementsWait(depTree);
		for(int i=0;i<depTree.size();i++)
		{
			String depname=depTree.get(i).getText();
			if(depname.contains(maindep))
			{
				depTree.get(i).click();
				return true;
			}
		}
		return false;
	}
	public void selSubDepartment(String subdep) throws InterruptedException
	{
		Thread.sleep(3000);
		elementsWait(depTree);
		int subcount=depTree.size();
		for(int i=0;i<subcount;i++)
		{
			String sdepname=depTree.get(i).getText();
			if(sdepname.contains(subdep))
			{
				depTree.get(i).click();
				Thread.sleep(2000);
			}
		}
	}

	public String verifyFolderHead() throws InterruptedException
	{
		Thread.sleep(3000);
		elementWait(folderHeading);
		String heading=folderHeading.getText();
		return heading;
	}
	
	public void createFolder() throws InterruptedException
	{
		Thread.sleep(3000);
		elementWait(createFolderIcon);
		createFolderIcon.click();
		Thread.sleep(2000);
	}
	public void addFolder(String foldername) throws InterruptedException
	{
		Thread.sleep(3000);
		elementWait(folderName);
		folderName.sendKeys(foldername);
	}
	public void saveFolder() throws InterruptedException
	{
		Thread.sleep(2000);
		save.click();
		Thread.sleep(2000);
	}
	public String folderAlert() throws InterruptedException
	{
		Thread.sleep(3000);
		Alert alt=driver.switchTo().alert();
		String msg=alt.getText();
		alt.accept();
		return msg;
	}
	public boolean selFolder(String newfol) throws InterruptedException
	{
		Thread.sleep(3000);
		elementsWait(folders);
		for(int i=0;i<folders.size();i++)
		{
			String folname=folders.get(i).getText();
			if(folname.contains(newfol))
			{
				folders.get(i).click();
				Thread.sleep(500);
				optionsIcon.get(i-1).click();
				Thread.sleep(3000);
				return true;
			}
		}
		return false;
	}
	public void permission() throws InterruptedException
	{
		Thread.sleep(2000);
		elementWait(permission);
		permission.click();
		Thread.sleep(2000);
		
	}
	public void selPermissionDesig(String desig) throws InterruptedException
	{
		Thread.sleep(3000);
		Select sel=new Select(selectdesignation);
		sel.selectByVisibleText(desig);
	}
	public void selUser(String username) throws InterruptedException
	{
		Thread.sleep(3000);
		//elementsWait(permissionUser);
		int subcount=permissionUser.size();
		for(int i=0;i<subcount;i++)
		{
			String user=permissionUser.get(i).getText();
			if(user.contains(username))
			{
				Thread.sleep(2000);
				checkbox.get(i).click();
				break;
			}
		}
	}
	public void savePermission() throws InterruptedException
	{
		Thread.sleep(2000);
		elementWait(save);
		save.click();
	}
	public String permissionAlert() throws InterruptedException
	{
		Thread.sleep(3000);
		Alert alt=driver.switchTo().alert();
		String msg=alt.getText();
		alt.accept();
		Thread.sleep(1000);
		return msg;
	}
	
	//login to another acc
	public boolean verifyfolderpermission(String newfol) throws InterruptedException
	{
		Thread.sleep(3000);
		elementsWait(folders);
		for(int i=0;i<folders.size();i++)
		{
			String folname=folders.get(i).getText();
			if(folname.contains(newfol))
			{
				folders.get(i).click();
				return true;
			}
		}
		return false;
	}
	public void sharefolder() throws InterruptedException
	{
		Thread.sleep(3000);
		elementWait(share);
		share.click();
		Thread.sleep(2000);
	}
	public void shareBtn() throws InterruptedException
	{
		Thread.sleep(3000);
		elementWait(sharebtn);
		sharebtn.click();
	}
	public boolean createDocument(String newfol) throws InterruptedException
	{
		Thread.sleep(3000);
		elementsWait(folders);
		for(int i=0;i<folders.size();i++)
		{
			String folname=folders.get(i).getText();
			if(folname.contains(newfol))
			{
				folders.get(i).click();
				Thread.sleep(500);
				return true;
			}
		}
		return false;
	}
	public void createDocumentIcon() throws InterruptedException
	{
		Thread.sleep(3000);
		elementWait(createDocIcon);
		createDocIcon.click();
		Thread.sleep(2000);
	}
	public void fillDocument(String dtitle,String downer) throws InterruptedException
	{
		Thread.sleep(2000);
		elementWait(docTitle);
		docTitle.clear();
		docTitle.sendKeys(dtitle);
		
		Select sel=new Select(docowner);
		sel.selectByVisibleText(downer);
		
//		Select seltype=new Select(doctype);
//		seltype.selectByVisibleText(dtype);
//		(,String dtype)		
		
	}
	public void saveDocument(String dversion) throws InterruptedException
	{
		Thread.sleep(1000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,600)");
		elementWait(docversion);
		docversion.clear();
		docversion.sendKeys(dversion);
	}
	public void uploadDoc(String doclocation) throws Exception
	{
		Thread.sleep(2000);
		elementWait(browsedoc);
		browsedoc.click();
		Thread.sleep(3000);
		
		Robot robot = new Robot();

		// Press the "CTRL" and "V" keys to paste the file path into the file input field
		StringSelection stringSelection = new StringSelection(doclocation); 
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);

		// Press the "ENTER" key to submit the file
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,600)");
		Thread.sleep(2000);
	}
	public String documentAlert() throws InterruptedException
	{
		Thread.sleep(3000);
		Alert alt=driver.switchTo().alert();
		String msg=alt.getText();
		alt.accept();
		return msg;
	}
	public boolean verifyDocument(String docname) throws InterruptedException
	{
		Thread.sleep(3000);
		elementsWait(documents);
		for(int i=0;i<documents.size();i++)
		{
			String dname=documents.get(i).getText();
			if(dname.contains(docname))
			{
				documents.get(i).getText();		
				return true;
			}
		}
		return false;
	}
	
	public boolean documentActions(String docname) throws InterruptedException
	{
		Thread.sleep(3000);
		elementsWait(documents);
		for(int i=0;i<documents.size();i++)
		{
			String dname=documents.get(i).getText();
			if(dname.contains(docname))
			{
				documentoptions.get(i).click();
				Thread.sleep(1000);
				return true;
			}
		}
		return false;
	}
	public void saveBtn() throws InterruptedException
	{
		Thread.sleep(1000);
		elementWait(save);
		save.click();
		Thread.sleep(3000);
	}
	public void editOption() throws InterruptedException
	{
		elementWait(edit);
		edit.click();
		Thread.sleep(2000);
	}
}
