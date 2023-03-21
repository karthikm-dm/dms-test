package dms.PageObjects;

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

public class ProjectsPage extends AbstractComponent{
	
	WebDriver driver;
	public ProjectsPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(xpath="//a[@href='/projectList']")
	WebElement projetsMenu;
	@FindBy(xpath="(//a[@href='#'])[2]")
	WebElement createProjIcon;
	
	@FindBy(xpath="//label[contains(text(),'name')]//following-sibling::input[1]")
	WebElement projectName;
	@FindBy(tagName="select")
	WebElement projectType;
	
	//save
	@FindBy(xpath="//button[contains(text(),'Save')]")
	WebElement savebtn;
	
	@FindBy(xpath="//div[@id='primaryTree']")
	List<WebElement> projectsList;
	
	@FindBy(xpath="//img[@alt='Create project role']")
	WebElement createProjRoleIcon;
	
	@FindBy(xpath="//label[contains(text(),'Role')]//following-sibling::input[1]")
	WebElement projRole;
	
	@FindBy(xpath="//select[@id='role']")
	WebElement selRole;
	
	@FindBy(xpath="//div[@aria-owns='multiselect-options']")
	WebElement chooseuser;
	@FindBy(xpath="//li[contains(@id,'multiselect')]")
	List<WebElement> userlist; 
	
	@FindBy(xpath="//img[@alt='Create Project User']")
	WebElement createProjUserIcon;
	
	@FindBy(xpath="(//div[contains(@class,'ibox')])[2]//tr")
	List<WebElement> projroles; 		
	@FindBy(xpath="(//div[contains(@class,'ibox')])[4]//tr")
	List<WebElement> projusers; 
	
	
	//folders
	@FindBy(xpath="(//img[@alt='create department'])[1]")
	WebElement createFolderIcon;
	
	@FindBy(xpath="//label[contains(text(),'Name')]//following-sibling::input[1]")
	WebElement folderName;
	
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
	@FindBy(xpath="//button[contains(text(),'Share')]")
	WebElement sharebtn;
	
	//Document screen
	
	@FindBy(xpath="//img[@alt='Add a file']")
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
	
	public void projectMenu() throws InterruptedException
	{
		Thread.sleep(3000);
		elementWait(projetsMenu);
		projetsMenu.click();
		Thread.sleep(2000);
	}
	public void createProjectIcon() throws InterruptedException
	{
		Thread.sleep(3000);
		elementWait(createProjIcon);
		createProjIcon.click();
		Thread.sleep(2000);
	
	}
	public void createProject(String pname,String ptype) throws InterruptedException
	{
		Thread.sleep(3000);
		elementWait(projectName);
		projectName.sendKeys(pname);
		Select sel=new Select(projectType);
		sel.selectByVisibleText(ptype);
	}
	public void  save() throws InterruptedException
	{
		Thread.sleep(2000);
		savebtn.click();
	}
	public boolean selProject(String pname) throws InterruptedException
	{
		Thread.sleep(3000);
		elementsWait(projectsList);
		Thread.sleep(1000);
		for(int i=0;i<projectsList.size();i++)
		{
			String depname=projectsList.get(i).getText();
			if(depname.contains(pname))
			{
				projectsList.get(i).click();
				return true;
			}
		}
		return false;
	}
	public void addProjRole(String prole) throws InterruptedException
	{
		Thread.sleep(3000);
		elementWait(createProjRoleIcon);
		createProjRoleIcon.click();
		Thread.sleep(2000);
		elementWait(projRole);
		projRole.sendKeys(prole);
	}
	public boolean verifyProjectRole(String prole) throws InterruptedException
	{
		Thread.sleep(3000);
		elementsWait(projroles);
		for(int i=0;i<projroles.size();i++)
		{
			String depname=projroles.get(i).getText();
			if(depname.contains(prole))
			{
				projroles.get(i).click();
				return true;
			}
		}
		return false;
	}
	
	public void addprojUsers(String prole) throws InterruptedException
	{
		Thread.sleep(3000);
		elementWait(createProjUserIcon);
		createProjUserIcon.click();
		Thread.sleep(2000);
		Select sel=new Select(selRole);
		sel.selectByVisibleText(prole);
	}
	public void chooseUserDropdown() throws InterruptedException
	{
		Thread.sleep(3000);
		elementWait(chooseuser);
		chooseuser.click();
	}
	public boolean chooseUser(String username) throws Exception
	{
		Thread.sleep(3000);
		elementsWait(userlist);
		for(int i=0;i<userlist.size();i++)
		{
			String usern=userlist.get(i).getText();
			if(usern.contains(username))
			{
				userlist.get(i).click();
				return true;
			}
		}
		return false;
	}
	public boolean verifyProjectUsers(String username) throws InterruptedException
	{
		Thread.sleep(3000);
		elementsWait(projusers);
		for(int i=0;i<projusers.size();i++)
		{
			String depname=projusers.get(i).getText();
			if(depname.contains(username))
			{
				projusers.get(i).click();
				return true;
			}
		}
		return false;
	}
	// PROJECT FOLDER 
	
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
		Thread.sleep(3000);
		elementWait(savebtn);
		savebtn.click();
		Thread.sleep(2000);
	}
	public String alert() throws InterruptedException
	{
		Thread.sleep(3000);
		Alert alt=driver.switchTo().alert();
		String msg=alt.getText();
		alt.accept();
		return msg;
	}
	public boolean selFolder(String newfol) throws InterruptedException
	{
		elementsWait(folders);
		Thread.sleep(3000);
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
		Thread.sleep(3000);
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
		elementsWait(permissionUser);
		int subcount=permissionUser.size();
		for(int i=0;i<subcount;i++)
		{
			String user=permissionUser.get(i).getText();
			if(user.contains(username))
			{
				Thread.sleep(2000);
				checkbox.get(i).click();
				Thread.sleep(2000);
				break;
			}
		}
	}
	public void share() throws InterruptedException
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
	
	//Documents 
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
				Thread.sleep(1000);
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
		Thread.sleep(3000);
	}
	public void fillDocument(String dtitle,String downer) throws InterruptedException
	{
		Thread.sleep(3000);
		elementWait(docTitle);
		docTitle.sendKeys(dtitle);
		
		Select sel=new Select(docowner);
		sel.selectByVisibleText(downer);	
	}
	public void docVersion(String dversion) throws InterruptedException
	{
		Thread.sleep(1000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,600)");
		Thread.sleep(2000);
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
	
}
