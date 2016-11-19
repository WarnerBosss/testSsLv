package Tests;

import Entities.ShortResult;
import PageObjects.FavouritesPage;
import PageObjects.MainPage;
import PageObjects.ResultPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.Set;
import java.util.stream.Collectors;

import static Helpers.GeneralHelpers.scrollPageUp;

/**
 * Created by DeBeers on 15.11.2016.
 */
public class TestSsLv extends BaseTest {

    @Test
    public void test() throws InterruptedException, AWTException {

        String searchPhrase   = "Computer";
        String sellType       = "Продажа";
        String minimalPrice   = "160";
        String maximalPrice   = "300";
        String undercatChoose = "Продают";
        int addsToAddCounter  = 3;

        MainPage mainPage = new MainPage(driver);

        Set<ShortResult> choosenAdds = mainPage
                .switchLocalization()
                .clickOnElectronicsMainCategoryLink()
                .checkForCategoryLinkPresent()
                .clickOnSearchTopMenuLink()
                .checkForSearchPageIsPresent()
                .enterSearchPhrase(searchPhrase)
                .selectUnderCategory(undercatChoose)
                .clickOnSearchButton()
                .checkForResultPage()
                .sortByPrice()
                .selectSellType(sellType)
                .clickOnExtendedSearchLink()
                .checkForSearchPageIsPresent()
                .setMinimalPrice(minimalPrice)
                .setMaximalPrice(maximalPrice)
                .selectUnderCategory(undercatChoose)
                .clickOnSearchButton()
                .checkForResultPage()
                .grabAllAddsResults().stream().limit(addsToAddCounter)
                .collect(Collectors.toSet());

        ResultPage resultPage = new ResultPage(driver);
        resultPage
                .selectAdds(choosenAdds)
                .clickOnAddToFavoritesLink()
                .clickOnAddToFavoritesAllertOKButton();

        Assert.assertEquals(resultPage.checkFavCounter(), addsToAddCounter, "Incorrect value in favourites counter:: ");
        scrollPageUp();

        FavouritesPage favPage = resultPage.clickOnFavoritesLink();

        choosenAdds.stream().forEach(x -> {
            Assert.assertTrue(favPage.checkAddTitle(x.getTitleName()).contains(x.getTitleName()), "Title is incorrect");
            Assert.assertEquals(favPage.checkPrice(x.getTitleName()), x.getPriceValue().substring(0, 3), "Price missmatch");
        });
    }
}
