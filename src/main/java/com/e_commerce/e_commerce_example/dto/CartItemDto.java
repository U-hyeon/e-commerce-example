package com.e_commerce.e_commerce_example.dto;

import jakarta.validation.constraints.NotNull;

public class CartItemDto {
    @NotNull(message = "상품 아이디는 필수 입력 값입니다.")
    private Long itemId;

    @NotNull(message = "최소 1개 이상 담아주세요")
    private int amount;

    public @NotNull(message = "상품 아이디는 필수 입력 값입니다.") Long getItemId() {
        return itemId;
    }

    public void setItemId(@NotNull(message = "상품 아이디는 필수 입력 값입니다.") Long itemId) {
        this.itemId = itemId;
    }

    @NotNull(message = "최소 1개 이상 담아주세요")
    public int getAmount() {
        return amount;
    }

    public void setAmount(@NotNull(message = "최소 1개 이상 담아주세요") int amount) {
        this.amount = amount;
    }
}
