package PageObjects;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by DeBeers on 14.11.2016.
 */
public class SearchMainPage extends TopMenuGeneralPage {

    @FindBy(xpath = "//*[@id='ptxt']")
    public WebElement searchPhraseField;

    @FindBy(xpath = ".//*[@name='cid_0']")
    public WebElement categorySelect;

    @FindBy(xpath = "//td/select[@name='sid']")
    public WebElement undercategorySelect;

    @FindBy(xpath = ".//*[@id='category_tr']//select")
    public WebElement subCategorySelect;

    @FindBy(xpath = "//*[@id='s_region_select']")
    public Select regionSelect;

    @FindBy(xpath = ".//*[@name='topt[8][min]']")
    public WebElement minimalPriceField;

    @FindBy(xpath = ".//*[@name='topt[8][max]']")
    public WebElement maximalPriceField;


    @FindBy(xpath = ".//*[@id='sbtn']")
    public WebElement searchButton;

    @FindBy(xpath = ".//*[@id='page_main']/tbody/tr/td/div/div[1]/h2/b")
    public WebElement background;


    public SearchMainPage enterSearchPhrase(String phrase){
        assert phrase != null;
        $(searchPhraseField).shouldBe(Condition.visible).sendKeys(phrase);
        background.click();
        return this;
    }

    public void selectCategory(String category){
        assert category != null;
        $(categorySelect).shouldBe(Condition.visible).selectOption(category);
    }

    public void selectSubCategory(String category){
        assert category != null;
        $(subCategorySelect).shouldBe(Condition.visible).selectOption(category);
    }

    public SearchMainPage selectUnderCategory(String category){
        assert category != null;
        $(undercategorySelect).shouldBe(Condition.visible).selectOption(category);
        return this;
    }

    public ResultPage clickOnSearchButton(){
        $(searchButton).shouldBe(Condition.visible).click();
        return new ResultPage(driver);
    }

    public SearchMainPage setMinimalPrice(String minimalPrice){
        $(minimalPriceField).shouldBe(Condition.visible).sendKeys(minimalPrice);
        return this;
    }

    public SearchMainPage setMaximalPrice(String maximalPrice){
        $(maximalPriceField).shouldBe(Condition.visible).sendKeys(maximalPrice);
        return this;
    }

    public SearchMainPage checkForSearchPageIsPresent(){
        assert $(searchButton).shouldBe(Condition.visible).isDisplayed();
        return this;
    }

    public SearchMainPage(WebDriver driver) {
        super(driver);
    }
}
