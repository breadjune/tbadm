package kr.co.metaboss.dto.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Search {

    private int page;          // 현재 페이지 번호
    private int recodeSize;    // 페이지당 출력할 데이터 개수
    private int pageSize;      // 화면 하단에 출력할 페이지 개수
    private String vendor;    // 검색할 업체
    private String keyword;    // 검색할 키워드
    private String sortType;   // 정렬 유형 (ASC, DESC)
    private String sortColumn; // 정렬 컬럼

    public Search() {
        this.page = 1;
        this.recodeSize = 10;
        this.pageSize = 10;
        this.keyword = "";
        this.sortColumn = "";
    }

    public int getOffset() {
        return (page -1) * recodeSize;
    }
}
