package selenide;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
public class SelenideTestCase {

    @Before
    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "http://localhost/";

    }

    @Test
    public void SelenideLogIn() {


        open("http://localhost/");
        open("http://localhost/");
        $(By.id("registerId")).click();
        $(By.id("registerId")).isDisplayed();
        $(By.xpath("//*[text()='" + " Log-in " + "']")).click();
        SelenideElement by1 = $(By.xpath("//*[contains(text(),'Register')]"));
        by1.click();
        $(By.xpath("//*[contains(text(),'Full name')]")).sendKeys("prova");
    }
}



