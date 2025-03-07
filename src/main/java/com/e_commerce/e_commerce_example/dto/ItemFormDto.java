package com.e_commerce.e_commerce_example.dto;

import com.e_commerce.e_commerce_example.constant.ItemSellStatus;
import com.e_commerce.e_commerce_example.entity.Item;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class ItemFormDto {
    private Long id;

    @NotBlank(message="상품명은 필수 입력 값입니다.")
    private String itemName;

    @NotNull(message="가격은 필수 입력 값입니다.")
    private Integer price;

    @NotBlank(message="상품설명은 필수 입력 값입니다.")
    private String itemDetail;

    @NotNull(message="재고는 필수 입력 값입니다.")
    private Integer stockNumber;

    private ItemSellStatus itemSellStatus;

    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();

    private List<Long> itemImgIds = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    public Item createItem() {
        return modelMapper.map(this, Item.class);
    }

    public static ItemFormDto of(Item item) {
        return modelMapper.map(item, ItemFormDto.class);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "상품명은 필수 입력 값입니다.") String getItemName() {
        return itemName;
    }

    public void setItemName(@NotBlank(message = "상품명은 필수 입력 값입니다.") String itemName) {
        this.itemName = itemName;
    }

    public @NotNull(message = "가격은 필수 입력 값입니다.") Integer getPrice() {
        return price;
    }

    public void setPrice(@NotNull(message = "가격은 필수 입력 값입니다.") Integer price) {
        this.price = price;
    }

    public @NotBlank(message = "상품설명은 필수 입력 값입니다.") String getItemDetail() {
        return itemDetail;
    }

    public void setItemDetail(@NotBlank(message = "상품설명은 필수 입력 값입니다.") String itemDetail) {
        this.itemDetail = itemDetail;
    }

    public @NotNull(message = "재고는 필수 입력 값입니다.") Integer getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(@NotNull(message = "재고는 필수 입력 값입니다.") Integer stockNumber) {
        this.stockNumber = stockNumber;
    }

    public ItemSellStatus getItemSellStatus() {
        return itemSellStatus;
    }

    public void setItemSellStatus(ItemSellStatus itemSellStatus) {
        this.itemSellStatus = itemSellStatus;
    }

    public List<ItemImgDto> getItemImgDtoList() {
        return itemImgDtoList;
    }

    public void setItemImgDtoList(List<ItemImgDto> itemImgDtoList) {
        this.itemImgDtoList = itemImgDtoList;
    }

    public List<Long> getItemImgIds() {
        return itemImgIds;
    }

    public void setItemImgIds(List<Long> itemImgIds) {
        this.itemImgIds = itemImgIds;
    }

    public static ModelMapper getModelMapper() {
        return modelMapper;
    }

    public static void setModelMapper(ModelMapper modelMapper) {
        ItemFormDto.modelMapper = modelMapper;
    }
}
