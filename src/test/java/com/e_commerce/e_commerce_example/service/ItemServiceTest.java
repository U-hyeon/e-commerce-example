package com.e_commerce.e_commerce_example.service;

import com.e_commerce.e_commerce_example.constant.ItemSellStatus;
import com.e_commerce.e_commerce_example.dto.ItemFormDto;
import com.e_commerce.e_commerce_example.entity.Item;
import com.e_commerce.e_commerce_example.entity.ItemImg;
import com.e_commerce.e_commerce_example.repository.ItemImgRepository;
import com.e_commerce.e_commerce_example.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
class ItemServiceTest {
    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemImgRepository itemImgRepository;

    List<MultipartFile> createMultiPartFiles() throws Exception {
        List<MultipartFile> multipartFiles = new ArrayList<>();

        for(int i=0; i<5; i++) {
            String path = "/Users/Shared/picture/";
            String imageName = "image" + i + ".jpg";
            MockMultipartFile multipartFile = new MockMultipartFile(path, imageName, "image/jpeg", new byte[]{1,2,3,4});
            multipartFiles.add(multipartFile);
        }
        return multipartFiles;
    }

    @Test
    @DisplayName("상품 등록 테스트")
    @WithMockUser(username = "admin", roles = "ADMIN")
    void saveItem() throws Exception {
        ItemFormDto itemFormDto = new ItemFormDto();
        itemFormDto.setItemName("테스트상품");
        itemFormDto.setItemSellStatus(ItemSellStatus.SELL);
        itemFormDto.setItemDetail("테스트상품입니다");
        itemFormDto.setPrice(1000);
        itemFormDto.setStockNumber(100);

        List<MultipartFile> multipartFileList = createMultiPartFiles();
        Long itemId = itemService.saveItem(itemFormDto, multipartFileList);

        List<ItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdAsc(itemId);
        Item item = itemRepository.findById(itemId).orElseThrow(EntityNotFoundException::new);

        // 테스트 결과 확인
        assertEquals(itemFormDto.getItemName(), item.getItemName());
        assertEquals(itemFormDto.getItemSellStatus(), item.getItemSellStatus());
        assertEquals(itemFormDto.getItemDetail(), item.getItemDetail());
        assertEquals(itemFormDto.getPrice(), item.getPrice());
        assertEquals(itemFormDto.getStockNumber(), item.getStockNumber());
        assertEquals(multipartFileList.get(0).getOriginalFilename(), itemImgList.get(0).getOriginalImgName());
    }

}