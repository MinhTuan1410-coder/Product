/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Dto;

import model.Dto.CategoryDto;
import model.Dto.AccountDto;
import java.sql.Date;

/**
 *
 * @author Trần Minh Tuấn
 */
public class ProductDto {

    private String productId;
    private String productName;
    private String productImage;
    private String brief;
    private Date postedDate;
    private CategoryDto type;
    private AccountDto account;
    private String unit;
    private int price;
    private int discount;

    public ProductDto() {
    }

    public ProductDto(String productId, String productName, String productImage, String brief, Date postedDate, CategoryDto type, AccountDto account, String unit, int price, int discount) {
        this.productId = productId;
        this.productName = productName;
        this.productImage = productImage;
        this.brief = brief;
        this.postedDate = postedDate;
        this.type = type;
        this.account = account;
        this.unit = unit;
        this.price = price;
        this.discount = discount;
    }

    public ProductDto(String productId, String productName, String productImage, String brief, CategoryDto type, AccountDto account, String unit, int price, int discount) {
        this.productId = productId;
        this.productName = productName;
        this.productImage = productImage;
        this.brief = brief;
        this.type = type;
        this.account = account;
        this.unit = unit;
        this.price = price;
        this.discount = discount;
    }
    

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public CategoryDto getType() {
        return type;
    }

    public void setType(CategoryDto type) {
        this.type = type;
    }

    public AccountDto getAccount() {
        return account;
    }

    public void setAccount(AccountDto account) {
        this.account = account;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getFinalPrice() {    
        return price - (price * discount / 100);
    }

    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", productName=" + productName + ", productImage=" + productImage + ", brief=" + brief + ", postedDate=" + postedDate + ", type=" + type + ", account=" + account + ", unit=" + unit + ", price=" + price + ", discount=" + discount + '}';
    }

}
