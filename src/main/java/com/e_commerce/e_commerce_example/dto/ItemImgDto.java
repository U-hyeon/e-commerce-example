package com.e_commerce.e_commerce_example.dto;

import com.e_commerce.e_commerce_example.entity.ItemImg;
import org.modelmapper.ModelMapper;

public class ItemImgDto {
    private Long id;
    private String imgName;
    private String originalImgName;
    private String imgUrl;
    private String repImgYn;

    private static ModelMapper modelMapper = new ModelMapper();
    public static ItemImgDto of(ItemImg itemImg) {
        return modelMapper.map(itemImg, ItemImgDto.class);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getOriginalImgName() {
        return originalImgName;
    }

    public void setOriginalImgName(String originalImgName) {
        this.originalImgName = originalImgName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getRepImgYn() {
        return repImgYn;
    }

    public void setRepImgYn(String repImgYn) {
        this.repImgYn = repImgYn;
    }

    public static ModelMapper getModelMapper() {
        return modelMapper;
    }

    public static void setModelMapper(ModelMapper modelMapper) {
        ItemImgDto.modelMapper = modelMapper;
    }
}
