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
	
}
