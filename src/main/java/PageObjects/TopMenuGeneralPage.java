package PageObjects;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by DeBeers on 14.11.2016.
 */
public class TopMenuGeneralPage extends BasePageObject {

    @FindBy(xpath = ".//*[contains(text(),'Поиск')]")
    public WebElement searchLink;

    @FindBy(xpath = "//*[@class='menu_lang']/a")
    public WebElement switchLanguageLink;

    @FindBy(xpath = "//*[@class='a1' and @href='/ru/electronics/']")
    public WebElement electronicsMainCategory;

    @FindBy(xpath = ".//a[contains(text(),'Закладки')]")
    public WebElement favouritesLink;


    public TopMenuGeneralPage switchLocalization(){
        $(switchLanguageLink).shouldBe(Condition.visible).click();
        return this;
    }

    public ElectronicsCatPage clickOnElectronicsMainCategoryLink(){
        $(electronicsMainCategory).shouldBe(Condition.visible).click();
        return new ElectronicsCatPage(driver);
    }

    public SearchMainPage clickOnSearchTopMenuLink(){
        $(searchLink).shouldBe(Condition.visible).click();
        return new SearchMainPage(driver);
    }

    public TopMenuGeneralPage(WebDriver driver) {
        super(driver);
    }

    public void init(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

}
