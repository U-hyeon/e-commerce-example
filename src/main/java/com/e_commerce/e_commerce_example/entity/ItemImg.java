package com.e_commerce.e_commerce_example.entity;

import jakarta.persistence.*;

@Entity
@Table(name="item_img")
public class ItemImg extends BaseEntity {
    @Id
    @Column(name="item_img_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String imgName; // 이미지명

    private String originalImgName; // 원본 이미지 파일명

    private String imgUrl; // 이미지 경로

    private String representImgFlag; // 대표이미지 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;

    public void updateItemImg(String originalImgName, String imgName, String imgUrl) {
        this.originalImgName = originalImgName;
        this.imgName = imgName;
        this.imgUrl = imgUrl;
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

    public String getRepresentImgFlag() {
        return representImgFlag;
    }

    public void setRepresentImgFlag(String representImgFlag) {
        this.representImgFlag = representImgFlag;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
