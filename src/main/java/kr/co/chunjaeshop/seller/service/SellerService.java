package kr.co.chunjaeshop.seller.service;


import kr.co.chunjaeshop.admin.dto.AdminPageDTO;
import kr.co.chunjaeshop.cart.dto.CartDetailDTO;
import kr.co.chunjaeshop.seller.dto.SellerDTO;
import kr.co.chunjaeshop.order_product.dto.OrderProductDTO;
import kr.co.chunjaeshop.pagination.dto.PageDTO;
import kr.co.chunjaeshop.product.dto.ProductDTO;
import kr.co.chunjaeshop.security.RegisterFormDTO;
import kr.co.chunjaeshop.seller.dto.SellDashBoardDTO;


import java.util.List;

public interface SellerService {
    // 남원우


    // 최경락


    // 이무현
    List<SellerDTO> getNotRecognizedList();

    void updateRecognize(int i, String id);

    AdminPageDTO notRecognizedSellerPagingParam(int page);

    List<SellerDTO> getNotRecognizedSellerList(int page);

    void insertRejectReason(String reason, String id);

    List<SellerDTO> getRejectSellerList(int page);

    AdminPageDTO rejectSellerPagingParam(int page);

    List<SellerDTO> getRecognizedSellerList(int page);

    AdminPageDTO recognizedSellerPagingParam(int page);

    SellerDTO getInfoBySellerId(String id);


    // 유지호
    SellerDTO mySellerInfoByIdx(Integer sellerIdx);

    int getMyTotalRev(Integer sellerIdx);

    int getDateRev(Integer sellerIdx);

    int avgRev(Integer sellerIdx);

    PageDTO pagingParam(int page, Integer sellerIdx);

    List<ProductDTO> productPagingListWithSearch(Integer sellerIdx, int page, String searchField, String searchWord);

    PageDTO pagingSearchParam(int page, Integer sellerIdx, String searchField, String searchWord);

    List<OrderProductDTO> sellProductManage(Integer sellerIdx, Integer productIdx, int page, String searchField, String searchWord);

    PageDTO orderManagePagingParm(int page, Integer sellerIdx, Integer productIdx);

    PageDTO orderManageSearchPagingParm(int page, Integer sellerIdx, Integer productIdx, String searchField, String searchWord);

    List<SellDashBoardDTO> monthlySalesList(Integer sellerIdx);

    List<SellDashBoardDTO> categorySales(Integer sellerIdx);

    List<SellDashBoardDTO> bestSellCount(Integer sellerIdx);

    List<SellDashBoardDTO> bestSellRev(Integer sellerIdx);

    void updateStatus(Integer orderIdx, String updatedStatus);

    // 변재혁
    boolean sellerRegister(RegisterFormDTO registerFormDTO);
    boolean idDuplicationCheck(String id);
    int increaseSellerIncome(List<CartDetailDTO> cartDetailDTOList, Integer sellerIdx);


}