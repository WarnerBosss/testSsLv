package PageObjects;

import Entities.ShortResult;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.*;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Created by DeBeers on 14.11.2016.
 */
public class ResultPage extends TopMenuGeneralPage {

    @FindBy(xpath = ".//*[@class='filter_sel']")
    public WebElement sellTypeDropDown;

    @FindBy(xpath = ".//a[contains(text(),'Цена')]")
    public WebElement priceFilter;

    @FindBy(xpath = ".//b[contains(text(),'Результат поиска')]")
    public WebElement resultPageID;

    @FindBy(xpath = ".//*[contains(text(),'Расширенный поиск')]")
    public WebElement extendedSearchLink;

    @FindBy(xpath = ".//a[@class='am']")
    public List<WebElement> listOfAddsTitles;

    @FindBy(xpath = ".//a[@title='Закладки']")
    public WebElement favoritesLink;

    @FindBy(xpath = ".//*[@id='a_fav_sel']")
    public WebElement addToFavoritesLink;

    @FindBy(xpath = ".//*[@id='alert_ok']")
    public WebElement addToFavoritesAllertOKButton;

    @FindBy(xpath = ".//*[@id='mnu_fav_id']")
    public WebElement favCounter;

    public Set<ShortResult> grabAllAddsResults(){

        Set<ShortResult> results = new HashSet<>();
        $$(listOfAddsTitles)
                .stream()
                .map(SelenideElement::getText)
                .collect(Collectors.toList())
                .forEach(q -> {
                    String cut = q.substring(0, 15); // reduce title to 15 symbols

                    if(getAddCheckBoxByName(cut) != null){

                        ShortResult add = new ShortResult();
                        add.setTitleName(cut);
                        add.setCheckBox(getAddCheckBoxByName(cut));
                        add.setPrice(getPriceByName(cut));
                        add.setPriceValue(getPriceByName(cut).getText());
                        results.add(add);
                    }
                    else System.out.println("This is another type of add:: \n" + q);
                });

        return results;
    }

    public FavouritesPage clickOnFavoritesLink(){
        $(favoritesLink).shouldBe(Condition.visible).click();
        return new FavouritesPage(driver);
    }

    public ResultPage clickOnAddToFavoritesLink(){
        $(addToFavoritesLink).shouldBe(Condition.visible).click();
        return this;
    }

    public ResultPage clickOnAddToFavoritesAllertOKButton(){
        $(addToFavoritesAllertOKButton).click();
        return this;
    }

    public int checkFavCounter(){
      return Integer.parseInt($(favCounter).shouldBe(Condition.visible).getText().substring(2, 3));
    }

    public WebElement getAddCheckBoxByName(String title){
        assert title != null;
        try{
            return $(By.xpath(".//*[contains(text(),'" + title + "')]//ancestor::tr[1]//input")).toWebElement();
        }catch (NoSuchElementException e){
            log.info("There are some strange elements exsist, but, we are going on!):: \n" + e.getMessage());
            return null;
        }
    }

    public WebElement getPriceByName(String title){
        return $(By.xpath(".//*[contains(text(),'" + title + "')]/ancestor::tr/td/a[@class='amopt']"));
    }

    public ResultPage selectSellType(String sellType) {
        assert sellType != null;
        $(sellTypeDropDown).shouldBe(Condition.visible).selectOption(sellType);
        return this;
    }

    public ResultPage sortByPrice() {
        $(priceFilter).shouldBe(Condition.visible).click();
        return this;
    }

    public SearchMainPage clickOnExtendedSearchLink() {
        $(extendedSearchLink).shouldBe(Condition.visible).click();
        return new SearchMainPage(driver);
    }

    public ResultPage checkForResultPage() {
        assert $(resultPageID).shouldBe(Condition.visible).isDisplayed();
        return this;
    }

    public ResultPage selectAdds(Set<ShortResult> collection) {
        collection.stream().forEach(q-> $(q.getCheckBox()).click());
        return this;
    }

    public ResultPage(WebDriver driver) {
        super(driver);
    }
}
