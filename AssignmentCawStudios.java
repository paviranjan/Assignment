package miscellaneous;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;

public class AssignmentCawStudios 
{

    public static void main(String[] args) {
    	WebDriverManager.firefoxdriver().setup();
		WebDriver driver= new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        try {
            driver.get("https://testpages.herokuapp.com/styled/tag/dynamic-table.html");

            // Clicks on the "Table Data" button to display the input text box.
            driver.findElement(By.xpath("//summary[normalize-space()='Table Data']")).click();
            
            Thread.sleep(5000);
            // Inserts the provided data into the input text box.
        	WebElement InputTextBox = driver.findElement(By.xpath("//textarea[@id='jsondata']"));
        	InputTextBox.clear();
        	InputTextBox.sendKeys("[{\"name\" : \"Bob\", \"age\" : 20, \"gender\": \"male\"}, {\"name\": \"George\", \"age\" : 42, \"gender\": \"male\"}, {\"name\": \"Sara\", \"age\" : 42, \"gender\": \"female\"}, {\"name\": \"Conor\", \"age\" : 40, \"gender\": \"male\"}, {\"name\": \"Jennifer\", \"age\" : 42, \"gender\": \"female\"}]");

            // Click on the "Refresh Table" button
        	WebElement RefreshTable = driver.findElement(By.xpath("//button[@id='refreshtable']"));
    		RefreshTable.click();
    		Thread.sleep(5000);
    		
            // Finds all the rows in the table
            List<WebElement> list = driver.findElements(By.xpath("//table[@id='dataTable']/tbody/tr"));

            String expectedData = "[{\"name\":\"Bob\",\"age\":20,\"gender\":\"male\"},{\"name\":\"George\",\"age\":42,\"gender\":\"male\"},{\"name\":\"Sara\",\"age\":42,\"gender\":\"female\"},{\"name\":\"Conor\",\"age\":40,\"gender\":\"male\"},{\"name\":\"Jennifer\",\"age\":42,\"gender\":\"female\"}]";

            // Now assert the data you have stored with the data that is populated in the UI table. Both data should match.
            for (int i = 0; i < list.size(); i++) {
                String actualData = list.get(i).getText();
                assert actualData.equals(expectedData) : "Data in actualData " + (i + 1) + " does not match the expected data.";
            }

            System.out.println("All data in the table matches the expected data.");

        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}