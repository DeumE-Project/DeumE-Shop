package kr.co.chunjaeshop.product.service;

import kr.co.chunjaeshop.product.dto.ProductDTO;
import kr.co.chunjaeshop.product.dto.ProductDetailImgUpdateDTO;
import kr.co.chunjaeshop.product.dto.ProductMainImgUpdateDTO;
import kr.co.chunjaeshop.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;




    // 남원우


    // 최경락
    @Override
    public int productSave(ProductDTO productDTO) {
        int saveResult = productRepository.productSave(productDTO);
        return saveResult;
    }


    public ProductDTO findByProductIdx(Integer sellerIdx, Integer productIdx) {

        return productRepository.findByProductIdx(sellerIdx, productIdx);
    }

    @Override
    public ProductDTO findByProductIdx2(Integer sellerIdx, Integer productIdx) {

        return productRepository.findByProductIdx2(sellerIdx, productIdx);
    }
    @Override
    public boolean productInfoUpdate(ProductDTO productDTO) {
        int result = productRepository.productInfoUpdate(productDTO);
        return result > 0;
    }
    @Override
    public ProductDTO findMainImg(Integer sellerIdx, Integer productIdx) {

        return productRepository.findMainImg(sellerIdx, productIdx);
    }
    @Override
    public boolean productImgUpdate(ProductMainImgUpdateDTO productMainImgUpdateDTO) {

        int result = productRepository.productImgUpdate(productMainImgUpdateDTO);
        log.info(productMainImgUpdateDTO);
        return  result > 0;

    }
    @Override
    public ProductDTO findDetailImg(Integer sellerIdx, Integer productIdx) {
        return productRepository.findDetailImg(sellerIdx, productIdx);
    }
    @Override
    public boolean productDetailImgUpdate(ProductDetailImgUpdateDTO productDetailImgUpdateDTO) {
        int result = productRepository.productDetailImgUpdate(productDetailImgUpdateDTO);
        return result >0;
    }

    /*@Override
    public ProductDTO productInfoUpdate(Integer sellerIdx, Integer productIdx) {
        return productRepository.productInfoUpdate(sellerIdx,productIdx);
    }*/


    // 이무현


    // 유지호

    @Override
    public int countMyProductCnt(Integer sellerIdx) {
        int myCount = productRepository.countMyProductCnt(sellerIdx);
        return myCount;
    }




    // 변재혁
    private final ProductRepository pRepo;

    @Override
    public ProductDTO getProductInformationByProductIdx(Integer productIdx) {
        ProductDTO result = pRepo.getProductInformationByProductIdx(productIdx);
        return result;
    }

    @Override
    public List<ProductDTO> getProductListForMainPage() {
        return pRepo.getProductListForMainPage();
    }
}
