package kr.co.chunjaeshop.product.service;

import kr.co.chunjaeshop.product.dto.ProductDTO;
import kr.co.chunjaeshop.product.dto.ProductDetailImgUpdateDTO;
import kr.co.chunjaeshop.product.dto.ProductMainImgUpdateDTO;

import java.util.List;

public interface ProductService {


    // 남원우


    // 최경락
    int productSave(ProductDTO productDTO);
    ProductDTO findByProductIdx(Integer sellerIdx, Integer productIdx);
    ProductDTO findByProductIdx2(Integer sellerIdx, Integer productIdx);
    boolean productInfoUpdate(ProductDTO productDTO);
    ProductDTO findMainImg(Integer sellerIdx, Integer productIdx);
    boolean productImgUpdate(ProductMainImgUpdateDTO productMainImgUpdateDTO);
    ProductDTO findDetailImg(Integer sellerIdx, Integer productIdx);
    boolean productDetailImgUpdate(ProductDetailImgUpdateDTO productDetailImgUpdateDTO);

    // 이무현


    // 유지호
    int countMyProductCnt(Integer sellerIdx);




    // 변재혁
    ProductDTO getProductInformationByProductIdx(Integer productIdx);
    List<ProductDTO> getProductListForMainPage();
}
