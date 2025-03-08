package com.e_commerce.e_commerce_example.service;

import com.e_commerce.e_commerce_example.entity.ItemImg;
import com.e_commerce.e_commerce_example.repository.ItemImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

@Service
//@RequiredArgsConstructor
@Transactional
public class ItemImgService {
    @Value("${itemImgLocation}") // load from properties
    private String itemImgLocation;

    private final ItemImgRepository itemImgRepository;
    private final FileService fileService;

    public ItemImgService(ItemImgRepository itemImgRepository, FileService fileService) {
        this.itemImgRepository = itemImgRepository;
        this.fileService = fileService;
    }

    public void saveItemImg(ItemImg itemImg, MultipartFile itemImgFile) throws Exception {
        String originalImgName = itemImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        // upload file
        if (!StringUtils.isEmpty(originalImgName)) {
            imgName = fileService.uploadFile(itemImgLocation, originalImgName, itemImgFile.getBytes());
            imgUrl = "/images/item/" + imgName;
        }

        // save image data
        itemImg.updateItemImg(originalImgName, imgName, imgUrl);
        itemImgRepository.save(itemImg);
    }
}
