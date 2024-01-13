package kr.co.chunjaeshop.seller.service;


import kr.co.chunjaeshop.admin.dto.NotRecognizePageDTO;
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

    NotRecognizePageDTO notRecognizedSellerPagingParam(int page);

    List<SellerDTO> getNotRecognizedSellerList(int page);

    void insertRejectReason(String reason, String id);

    // 유지호
    SellerDTO mySellerInfoByIdx(Integer sellerIdx);

    int getMyTotalRev(Integer sellerIdx);

    int getDateRev(Integer sellerIdx);

    /*int getDateRevLast(Integer sellerIdx, String lastMonth);*/

    /*List<ProductDTO> myProduct(Integer sellerIdx);*/

    int avgRev(Integer sellerIdx);

    /*List<ProductDTO> productPagingList(Integer sellerIdx, int page);*/


    PageDTO pagingParam(int page, Integer sellerIdx);

    List<ProductDTO> productPagingListWithSearch(Integer sellerIdx, int page, String searchField, String searchWord);

    PageDTO pagingSearchParam(int page, Integer sellerIdx, String searchField, String searchWord);

    List<OrderProductDTO> sellProductManage(Integer sellerIdx, Integer productIdx, int page, String searchField, String searchWord);

    PageDTO orderManagePagingParm(int page, Integer sellerIdx, Integer productIdx);

    PageDTO orderManageSearchPagingParm(int page, Integer sellerIdx, Integer productIdx, String searchField, String searchWord);

    List<SellDashBoardDTO> monthlySalesList(Integer sellerIdx);

    List<SellDashBoardDTO> categorySales(Integer sellerIdx);

    // 변재혁
    boolean sellerRegister(RegisterFormDTO registerFormDTO);
    boolean idDuplicationCheck(String id);



}