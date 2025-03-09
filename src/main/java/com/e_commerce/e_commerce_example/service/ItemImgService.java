package com.e_commerce.e_commerce_example.service;

import com.e_commerce.e_commerce_example.entity.ItemImg;
import com.e_commerce.e_commerce_example.repository.ItemImgRepository;
import com.e_commerce.e_commerce_example.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
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
    private final ItemRepository itemRepository;
    @Value("${itemImgLocation}") // load from properties
    private String itemImgLocation;

    private final ItemImgRepository itemImgRepository;
    private final FileService fileService;

    public ItemImgService(ItemImgRepository itemImgRepository, FileService fileService, ItemRepository itemRepository) {
        this.itemImgRepository = itemImgRepository;
        this.fileService = fileService;
        this.itemRepository = itemRepository;
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

    public void updateItemImg(Long itemImgId, MultipartFile itemImgFile) throws Exception {
        if (!itemImgFile.isEmpty()) { // 상품이미지 수정했을 경우
            // 기존 상품이미지 정보 가져오기
            ItemImg savedItemImg = itemImgRepository.findById(itemImgId)
                    .orElseThrow(EntityNotFoundException::new); // 기존 이미지 없을 경우 exception
            // 기존 이미지 파일 삭제
            if (!StringUtils.isEmpty(savedItemImg.getImgName())) {
               fileService.deleteFile(itemImgLocation + "/" + savedItemImg.getImgName());
            }
            // 새로운 이미지 저장
            String originalImgName = itemImgFile.getOriginalFilename();
            String imgName = fileService.uploadFile(itemImgLocation, originalImgName, itemImgFile.getBytes());
            String imgUrl = "/images/item/" + imgName;
            savedItemImg.updateItemImg(originalImgName, imgName, imgUrl);
        }
    }
}
