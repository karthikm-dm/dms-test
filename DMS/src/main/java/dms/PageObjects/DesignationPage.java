package dms.PageObjects;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dms.AbstractComponents.AbstractComponent;

public class DesignationPage extends AbstractComponent{
	
	WebDriver driver;
	public DesignationPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(xpath="//a[@href='/designation']")
	WebElement designationIcon;
	@FindBy(xpath="//img[@alt='Create Designation']")
	WebElement createDesignationIcon;
	@FindBy(xpath="//div[@class='modal-content']//input[@id='designation-name']")
	WebElement designationName;
	@FindBy(xpath="//div[@class='modal-content']//textarea[@id='description']")
	WebElement description;
	@FindBy(xpath="//button[contains(text(),'Add')]")
	WebElement addBtn;
	
	@FindBy(xpath="//td[1]")
	List<WebElement> designation;
	@FindBy(xpath="//button[@type='button']")
	List<WebElement> pages;
	@FindBy(xpath="(//li[@class='PaginationControl'])[3]")
	WebElement next;
	@FindBy(xpath="//td[3]/img")
	List<WebElement> privilegeIcon;
	@FindBy(xpath="//input[@value='Departments']")
	WebElement departmentsPrivilege;
	@FindBy(xpath="//input[@value='Projects']")
	WebElement projectsPrivilege;
	public void designationMenu()
	{
		elementWait(designationIcon);
		designationIcon.click();
	}
	public void createDesigIcon()
	{
		elementWait(createDesignationIcon);
		createDesignationIcon.click();
	}
	public void createDesignation(String desigName,String desc)
	{
		elementWait(designationName);
		designationName.sendKeys(desigName);
		description.sendKeys(desc);
		//addBtn.click();
	}
	public boolean verifyDesignation(String desigName) throws InterruptedException
	{
		int j=0;
		int i=0;
		elementsWait(designation);
		while(j<pages.size())
		{
			int namecount=0;
			while(i<10)
			{
				Thread.sleep(500);
				String name =  designation.get(i).getText();
				if(name.contains(desigName))
				{
					//
					Thread.sleep(1000);
					privilegeIcon.get(i).click();
					Thread.sleep(3000);
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
	public void selPrivilege()
	{
		elementWait(departmentsPrivilege);
		departmentsPrivilege.click();
		projectsPrivilege.click();
	}

	public String designationAlert() throws InterruptedException
	{
		Thread.sleep(3000);
		Alert alt=driver.switchTo().alert();
		String msg=alt.getText();
		alt.accept();
		return msg;
	}
	
}

