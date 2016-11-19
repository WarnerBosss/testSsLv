package Entities;

import org.openqa.selenium.WebElement;

/**
 * Created by DeBeers on 17.11.2016.
 */
public class ShortResult {

    private WebElement title;
    private WebElement price;
    private WebElement checkBox;
    private String titleName;
    private String priceValue;


    public ShortResult(String priceValue, WebElement title, WebElement price, WebElement checkBox, String titleName) {
        this.priceValue = priceValue;
        this.title = title;
        this.price = price;
        this.checkBox = checkBox;
        this.titleName = titleName;
    }

    public String getPriceValue() {
        return priceValue;
    }

    public void setPriceValue(String priceValue) {
        this.priceValue = priceValue;
    }

    public ShortResult(){}

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public WebElement getTitle() {
        return title;
    }

    public void setTitle(WebElement title) {
        this.title = title;
    }

    public WebElement getPrice() {
        return price;
    }

    public void setPrice(WebElement price) {
        this.price = price;
    }

    public WebElement getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(WebElement checkBox) {
        this.checkBox = checkBox;
    }

}
