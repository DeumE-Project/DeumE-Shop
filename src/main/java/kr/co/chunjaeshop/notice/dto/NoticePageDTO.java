package kr.co.chunjaeshop.notice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NoticePageDTO {
    private int page; // 현재 페이지
    private int maxPage; // 전체 필요한 페이지 갯수
    private int startPage; // 현재 페이지 기준 시작 페이지 값
    private int endPage; // 현재 페이지 기준 마지막 페이지 값
    private int totalPage; // 전체 페이지의 수
    private int pageLimit; // 한 페이지의 게시물 최대 갯수
}
