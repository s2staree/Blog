package site.metacoding.red.web.dto.response.boards;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PagingDto {
	
	private Integer totalCount;	// 총 게시글 수
	private Integer totalPage;	// 총 페이지 수
	private Integer currentPage;	// 현재 페이지 번호
	private boolean isLast; // 마지막 페이지. getter가 만들어지면 isLast() 이름으로 만들어짐.
	private boolean isFirst; // 첫 페이지. getter가 만들어지면 isFirst() 이름으로 만들어짐.
	
	private Integer blockCount;	// (상수) 한 페이지의 페이지 넘버 수(총 5) 1-5, 6-10
	private Integer currentBlock; // (변수) 한 페이지에 보여질 게시글 수 => startPageNum, lastPageNum 결정됨
	private Integer startPageNum;	// (변수) 1 -> 6 -> 11
	private Integer lastPageNum; // (변수) 5 -> 10 -> 15
	
	public void makeBlockInfo() {
		this.blockCount = 5;

		this.currentBlock = currentPage / blockCount;
		this.startPageNum = 1 + blockCount * currentBlock;
		this.lastPageNum = 5 + blockCount * currentBlock;

		if (totalPage < lastPageNum) {
			this.lastPageNum = totalPage;
		}
	}
}
