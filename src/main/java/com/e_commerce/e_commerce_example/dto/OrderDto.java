package com.e_commerce.e_commerce_example.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class OrderDto {
    @NotNull(message = "상품 아이디는 필수 입력 값입니다.")
    private Long itemId;

    @Min(value = 1, message = "최소 주문 수량은 1개 입니다.")
    @Max(value = 999, message = "최대 주문 수량은 999개 입니다.")
    private int amount;

    public @NotNull(message = "상품 아이디는 필수 입력 값입니다.") Long getItemId() {
        return itemId;
    }

    public void setItemId(@NotNull(message = "상품 아이디는 필수 입력 값입니다.") Long itemId) {
        this.itemId = itemId;
    }

    @Min(value = 1, message = "최소 주문 수량은 1개 입니다.")
    @Max(value = 999, message = "최대 주문 수량은 999개 입니다.")
    public int getAmount() {
        return amount;
    }

    public void setAmount(@Min(value = 1, message = "최소 주문 수량은 1개 입니다.") @Max(value = 999, message = "최대 주문 수량은 999개 입니다.") int amount) {
        this.amount = amount;
    }
}
