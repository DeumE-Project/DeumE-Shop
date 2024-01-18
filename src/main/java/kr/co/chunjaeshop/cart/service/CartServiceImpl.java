package kr.co.chunjaeshop.cart.service;

import kr.co.chunjaeshop.cart.dto.*;
import kr.co.chunjaeshop.cart.repository.CartRepository;
import kr.co.chunjaeshop.product.dto.ProductDTO;
import kr.co.chunjaeshop.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class CartServiceImpl implements CartService {
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    @Override
    public CartResult addToCart(AddToCartForm addToCartForm) {
        // 해당 상품에 대한 판매자 idx 가져오기
        ProductDTO productDTO = productRepository.getProductInformationByProductIdx(addToCartForm.getProductIdx());

        if (productDTO == null) {
            return CartResult.FAILED;
        }

        int resultCount = -1;
        CartResult cartResult = null;

        addToCartForm.setSellerIdx(productDTO.getSellerIdx());
        addToCartForm.setProductPrice(productDTO.getProductPrice());

        // 해당 판매자의 해당 상품이 이미 카트에 저장됐는지 체크
        CartDTO cartInformation = cartRepository.getCartInformation(addToCartForm.getCustomerIdx(), productDTO.getSellerIdx());
        if (cartInformation != null && cartInformation.getCartDetailDTOList().size() > 0) {
            //parameterMap.put("cartIdx", cartInformation.getCartIdx());
            addToCartForm.setCartIdx(cartInformation.getCartIdx());

            List<CartDetailDTO> cartDetailDTOList = cartInformation.getCartDetailDTOList();
            CartDetailDTO cartDetailDTO = cartDetailDTOList
                    .stream()
                    .filter(dto -> dto.getProductIdx() == addToCartForm.getProductIdx())
                    .findFirst()
                    .orElse(null);
            if (cartDetailDTO != null) {
                // 카트에 이미 해당 상품이 담겨있으므로, cart_detail 에서 수량 업데이트
                // 필요한 것 => cart_idx, product_idx, buyCount
                resultCount = cartRepository.updateCartDetailProductInfo(addToCartForm);
                cartResult = CartResult.ALREADY;
            } else {
                // 카드에 해당 상품이 없으므로, cart_detail 새롭게 담아주기
                // 필요한 것 => cart_idx, product_idx, buy_count, product_price
                resultCount = cartRepository.insertNewCartDetail(addToCartForm);
                cartResult = CartResult.NEW_CART_DETAIL;
            }
        } else {
            // 카트가 존재하지도 않으므로, 카트 먼저 만들어주고, cart_detail 에 상품 담아주기
            // 1. 카트 만들어주기 (필요한 거: customer_idx, seller_idx)
            int subResultCount1 = -1;
            int subResultCount2 = -1;
            subResultCount1 = cartRepository.insertNewCart(addToCartForm);

            // 2. 카드 디에테일에 새로 담아주기
            subResultCount2 = cartRepository.insertNewCartDetail(addToCartForm);
            resultCount = (subResultCount1 == 1 && subResultCount2 == 1) ? 1 : -1;
            cartResult = CartResult.NEW_CART;
        }
        return (resultCount == 1) ?  cartResult : CartResult.FAILED;
    }

    @Override
    public List<CartDTO> getAllMyCartList(Integer customerIdx) {
        return cartRepository.getAllMyCartList(customerIdx);
    }

    @Override
    public CartDetailResult changeCartDetailBuyCount(ChangeCartDetailDTO changeCartDetailDTO) {
        int result = cartRepository.changeCartDetailBuyCount(changeCartDetailDTO);
        if (result == 1) {
            return CartDetailResult.CHANGED;
        } else {
            return CartDetailResult.FAILED;
        }
    }

    @Override
    public CartDTO getSpecificCart(Integer customerIdx, Integer cartIdx) {
        return cartRepository.getSpecificCart(customerIdx, cartIdx);
    }

    @Override
    public void deleteCartAndCartDetail(Integer cartIdx) {
        cartRepository.deleteCartDetail(cartIdx);
        cartRepository.deleteCart(cartIdx);
    }
}
