package com.e_commerce.e_commerce_example.dto;

import com.e_commerce.e_commerce_example.entity.OrderItem;

public class OrderItemDto {
    public OrderItemDto(OrderItem orderItem, String imgUrl) {
        this.itemNm = orderItem.getItem().getItemName();
        this.amount = orderItem.getAmount();
        this.orderPrice = orderItem.getOrderPrice();
        this.imgUrl = imgUrl;
    }

    private String itemNm;// 상품명
    private int amount; // 상품수량
    private int orderPrice; // 주문금액
    private String imgUrl; // 상품이미지 경로

    public String getItemNm() {
        return itemNm;
    }

    public void setItemNm(String itemNm) {
        this.itemNm = itemNm;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
