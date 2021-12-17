package GoGlobalProject.APIApp.SeleniumTests;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdminTests {

    private WebDriver driver;

    @Before
    public void SetupDriver(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\TimKu\\OneDrive\\Bureaublad\\Semester 3\\Individuel Project\\Go-Global\\GoGlobalAPIApp\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://localhost:3000/Admin");
    }

    @After
    public void DestroyWebDriver(){
        driver.quit();
    }

    @Test
    void An_AdminPostTest_ExpectingSucceed() throws InterruptedException{
        //Arrange
        SetupDriver();
        Thread.sleep(2000);
        driver.findElement(By.name("add-admin-btn")).click();
        driver.findElement(By.name("name")).sendKeys("John");
        driver.findElement(By.name("email")).sendKeys("John@doe.com");
        driver.findElement(By.name("password")).sendKeys("DoePW");

        driver.findElement(By.name("post-btn")).click();
        Thread.sleep(1000);

        //Act
        String actual = driver.findElement(By.className("Toastify__toast-body")).getText();
        Thread.sleep(4000);

        //Assert
        assertEquals("Admin succesfully created!", actual);
        DestroyWebDriver();
    }

    @Test
    void An_AdminPostTest_ExpectingFail() throws InterruptedException{
        //Arrange
        SetupDriver();
        Thread.sleep(2000);
        driver.findElement(By.name("add-admin-btn")).click();
        driver.findElement(By.name("name")).sendKeys("John");
        driver.findElement(By.name("email")).sendKeys("John@doe.com");
        driver.findElement(By.name("password")).sendKeys("DoePW");

        driver.findElement(By.name("post-btn")).click();
        Thread.sleep(1000);

        //Act
        String actual = driver.findElement(By.className("Toastify__toast-body")).getText();
        Thread.sleep(4000);

        //Assert
        assertEquals("Another admin with this email already exists!", actual);
        DestroyWebDriver();
    }

    @Test
    void An_AdminGetAllTest() throws InterruptedException{
        //Arrange
        SetupDriver();
        Thread.sleep(2000);

        //Act
        String actual_title = driver.getTitle();

        //Assert
        assertEquals("Admin",actual_title);
        DestroyWebDriver();
    }
}
