package com.mbc.sharetravel_spring.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mbc.sharetravel_spring.domain.Img;
import com.mbc.sharetravel_spring.domain.Product;
import com.mbc.sharetravel_spring.repository.MarketRepository;
import com.mbc.sharetravel_spring.service.MarketService;

@RestController
@CrossOrigin(origins = "*")
public class MarketController {
	
	@Autowired
	private MarketRepository marketRepository;
	
	@Autowired
	private MarketService marketService;
		
	@PostMapping("/marketsell/upload")
	
	public ResponseEntity<?> upload(MultipartFile[] files, Product product) {
		System.out.println(product);
		try {
			
			if (product.getImages() == null) {
	            product.setImages(new ArrayList<>());
	        }
			
            // 파일 저장
            for (MultipartFile file : files) {
                String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                Path path = Paths.get("upload/" + uniqueFileName);
                Files.createDirectories(path.getParent());
                Files.write(path, file.getBytes());

                Img img = new Img();
                img.setImg(uniqueFileName);
                img.setProduct(product);
                
                product.getImages().add(img);
            }

            // 데이터베이스 저장
            marketRepository.save(product);

            return new ResponseEntity<>("상품이 등록되었습니다.", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("상품 등록에 실패하셨습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }	
		
	}
	
	@GetMapping("/market")
	public ResponseEntity<?> marketList() {
		List<Product> productList = marketService.getProductList();
		
		return new ResponseEntity<>(productList,HttpStatus.OK);
	}
	
}
