package PageObjects;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Created by DeBeers on 18.11.2016.
 */
public class FavouritesPage extends TopMenuGeneralPage {

    @FindBy(xpath = ".//*[@id='mnu_fav_id']")
    public WebElement favCounter;

    public String checkAddTitle(String title){
       return $(By.xpath("//*[contains(text(),'" + title + "')]")).shouldBe(Condition.visible).getText();
    }

    public String checkPrice(String title) {

        if (checkForElementsCount(title) == 8) {

            return $(By.xpath("//*[contains(text(),'" + title + "')]/ancestor::tr[1]/td[8]"))
                    .shouldBe(Condition.visible).getText().substring(0, 3);

        } else if (checkForElementsCount(title) == 7){

            return $(By.xpath("//*[contains(text(),'" + title + "')]/ancestor::tr[1]/td[7]"))
                    .shouldBe(Condition.visible).getText().substring(0, 3);

        } else if (checkForElementsCount(title) == 6){

            return $(By.xpath("//*[contains(text(),'" + title + "')]/ancestor::tr[1]/td[6]"))
                    .shouldBe(Condition.visible).getText().substring(0, 3);
        } else return null;
    }

    public int checkForElementsCount(String title){
        try{
           return $$(By.xpath("//*[contains(text(),'" + title + "')]/ancestor::tr[1]/td")).size();
        }catch (NoSuchElementException e)
        {log.error("No elements found by such title");}

        return 0;
    }

    public FavouritesPage(WebDriver driver) {
        super(driver);
    }
}
