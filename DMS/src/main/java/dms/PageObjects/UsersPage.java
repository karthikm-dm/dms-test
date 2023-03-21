package dms.PageObjects;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import dms.AbstractComponents.AbstractComponent;

public class UsersPage extends AbstractComponent{

	WebDriver driver;
	
	public UsersPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(xpath="//a[@href='/userlisting']")
	WebElement usersmenu;
	@FindBy(xpath="//img[@alt='Create Designation']")
	WebElement createUserIcon;
	@FindBy(xpath="//label[contains(text(),'First')]/following-sibling::input[1]")
	WebElement fName;
	@FindBy(xpath="//label[contains(text(),'Last')]/following-sibling::input[1]")
	WebElement lName;
	@FindBy(xpath="//label[contains(text(),'Username')]/following-sibling::input[1]")
	WebElement usName;
	@FindBy(xpath="//label[contains(text(),'Primary')]/following-sibling::input[1]")
	WebElement primEmail;
	@FindBy(xpath="//select[@name='Designation']")
	WebElement designationDropdown;
	
	@FindBy(xpath="//div[@aria-owns='multiselect-options']")
	WebElement departmentDropdown;
	@FindBy(xpath="//li[contains(@id,'multiselect')]")
	List<WebElement> departmentList;
	
	@FindBy(xpath="(//select[@name='Country'])[1]")
	WebElement usertype;
	@FindBy(xpath="//button[contains(text(),'Save')]")
	WebElement savebtn;
	
	
	@FindBy(xpath="//td[1]")
	List<WebElement> usernames;
	@FindBy(xpath="//td[3]")
	List<WebElement> designames;
	@FindBy(xpath="//td[4]")
	List<WebElement> depnames;
	@FindBy(xpath="//button[@type='button']")
	List<WebElement> pages;
	@FindBy(xpath="(//li[@class='PaginationControl'])[3]")
	WebElement next;

	
	public void usersMenu()
	{
		elementWait(usersmenu);
		usersmenu.click();
	}
	public void createUser(String fname,String lname,String username,String pemail)
	{
		elementWait(createUserIcon);
		createUserIcon.click();
		elementWait(fName);
		fName.sendKeys(fname);
		lName.sendKeys(lname);
		usName.sendKeys(username);
		primEmail.sendKeys(pemail);
		
	}
	public void selDesignation(String desig) throws InterruptedException
	{
		Thread.sleep(2000);
		Select sel=new Select(designationDropdown);
		sel.selectByVisibleText(desig);
	}
	public void selDepartment(String depart)
	{
		departmentDropdown.click();
		for(int i=0;i<departmentList.size();i++)
		{
			String dname=departmentList.get(i).getText();
			if(dname.equals(depart))
			{
				departmentList.get(i).click();
			}
		}
	}
	public void selUserType(String userType)
	{
		Select sel=new Select(usertype);
		sel.selectByVisibleText(userType);
	}
	public void saveUser()
	{
		elementWait(savebtn);
		savebtn.click();
	}
	public String userAlert() throws InterruptedException
	{
		Thread.sleep(3000);
		Alert alt=driver.switchTo().alert();
		String msg=alt.getText();
		alt.accept();
		return msg;
	}
	
	public boolean verifyUsers(String name,String desig,String depart) throws InterruptedException
	{
		
		elementsWait(usernames);
		int j=0;
		int i=0;
		while(j<pages.size())
		{
			int namecount=0;
			while(i<10)
			{
				Thread.sleep(500);
				String uname =  usernames.get(i).getText();
				String desname=designames.get(i).getText();
				String departname=depnames.get(i).getText();
				if((uname.contains(name))&&(desname.contains(desig))&&(departname.contains(depart)))
				{
					//
					System.out.println(name+"-"+depart);
					namecount=1;
					return true;
					
				}
				else
				{
					i++;		
				}
			}
			if((namecount==0) && (next.isEnabled()))
			{
				next.click();
				i=0;
			}	
			j++;		
		}
		return false;
	}
	
}
