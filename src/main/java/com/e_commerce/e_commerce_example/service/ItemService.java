package com.e_commerce.e_commerce_example.service;

import com.e_commerce.e_commerce_example.dto.ItemFormDto;
import com.e_commerce.e_commerce_example.entity.Item;
import com.e_commerce.e_commerce_example.entity.ItemImg;
import com.e_commerce.e_commerce_example.repository.ItemImgRepository;
import com.e_commerce.e_commerce_example.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
//@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemImgService itemImgService;
    private final ItemImgRepository itemImgRepository;

    public ItemService(ItemRepository itemRepository, ItemImgService itemImgService, ItemImgRepository itemImgRepository) {
        this.itemRepository = itemRepository;
        this.itemImgService = itemImgService;
        this.itemImgRepository = itemImgRepository;
    }

    public Long saveItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws Exception {
        // register item
        Item item = itemFormDto.createItem();
        itemRepository.save(item);

        // register image
        for(int i=0; i<itemImgFileList.size(); i++) {
            ItemImg itemImg = new ItemImg();
            itemImg.setItem(item);
            if(i==0) { // 첫번째 이미지를 대표이미지로 설정
                itemImg.setRepresentImgFlag("Y");
            } else {
                itemImg.setRepresentImgFlag("N");
            }
            itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
        }

        return item.getId();
    }
}
