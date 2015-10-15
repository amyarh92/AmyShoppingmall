package com.example.coupang.amyshoppingmall;

import java.util.Date;

/**
 * Created by coupang on 2015. 8. 20..
 */
public class Product {
    private Long productId;
    private String title;
    private Long originPrice;
    private Long salePrice;
    private String imageUrl;
    private Date saleStartAt;
    private Date saleEndAt;
    private Date createdAt;
    private Date modifiedAt;
    private Long categoryId;


    public Product(Long productId, String title, Long originPrice, Long salePrice, String imageUrl, Long categoryId) {
        this.productId = productId;
        this.title = title;
        this.originPrice = originPrice;
        this.salePrice = salePrice;
        this.imageUrl = imageUrl;
        this.categoryId = categoryId;
    }
    public Long getProdcutId() {
        return productId;
    }

    public void setProdcutId(Long productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(Long originPrice) {
        this.originPrice = originPrice;
    }

    public Long getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Long salePrice) {
        this.salePrice = salePrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getSaleStartAt() {
        return saleStartAt;
    }

    public void setSaleStartAt(Date saleStartAt) {
        this.saleStartAt = saleStartAt;
    }

    public Date getSaleEndAt() {
        return saleEndAt;
    }

    public void setSaleEndAt(Date saleEndAt) {
        this.saleEndAt = saleEndAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

}
