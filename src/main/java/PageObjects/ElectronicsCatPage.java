package PageObjects;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by DeBeers on 15.11.2016.
 */
public class ElectronicsCatPage extends TopMenuGeneralPage {

    @FindBy(xpath = "//a[@href='/ru/electronics/']")
    public WebElement electronicsLink;


    public ElectronicsCatPage checkForCategoryLinkPresent(){
       assert $(electronicsLink).shouldBe(Condition.visible).isDisplayed();
        return this;
    }

    public ElectronicsCatPage(WebDriver driver) {
        super(driver);
    }
}
