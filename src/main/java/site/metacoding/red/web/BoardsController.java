package site.metacoding.red.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.web.dto.request.boards.WriteDto;
import site.metacoding.red.web.dto.response.boards.MainDto;
import site.metacoding.red.web.dto.response.boards.PagingDto;

@RequiredArgsConstructor
@Controller
public class BoardsController {

	private final HttpSession session;
	private final BoardsDao boardsDao;
	// @PostMapping("/boards/{id}/delete")
	// @PostMapping("/boards/{id}/update")
	
	@PostMapping("/boards/{id}/delete")
	public String deleteBoards(@PathVariable Integer id) {
		Users principal = (Users) session.getAttribute("principal");
		Boards boardsPS = boardsDao.findById(id);
		
		// 비정상 요청 체크
		if(boardsPS != null) {	// if는 비정상 로직을 타게 해서 걸러내는 필터 역할을 하는 게 좋다.
			return "redirect:/boards/" + id;
		}	// if + else문으로 짜면 분리가 안되므로 if문만 짜는게 분리가 편함!
		
		// 인증 체크 (로그인 되었는지?)
		if (principal == null) {
			return "redirect:/loginForm";
		}
		
		// 권한 체크 (세션 "principal.getId()"와 "boardsPS"의 "userId"를 비교, 같아야 삭제 가능)
		if(principal.getId() != boardsPS.getUsersId()) {
			return "redirect:/boards/"+id;
		}

		boardsDao.delete(id);
		return "redirect:/";
	}
	
	@PostMapping("/boards")
	public String writeBoards(WriteDto writeDto) {
		// 1번 세션에 접근해서 세션값을 확인한다. 그때 Users로 다운캐스팅하고 키값은 principal로 한다.
		Users principal = (Users) session.getAttribute("principal");
		
		// 2번 pricipal null인지 확인하고 null이면 loginForm 리다이렉션해준다.
		if(principal == null) {
			return "redirect:/loginForm";
		}
		
		// 3번 BoardsDao에 접근해서 insert 메서드를 호출한다.
		// 조건 : dto를 entity로 변환해서 인수로 담아준다.
		// 조건 : entity에는 세션의 principal에 getId가 필요하다.
		boardsDao.insert(writeDto.toEntity(principal.getId()));
		
		return "redirect:/";
	}
	
	// http://localhost:8000/
	// http://localhost:8000/?page=0
	@GetMapping({"/", "/boards"})
	public String getBoardList(Model model, Integer page) { // 0->0, 1->10, 2->20
		if(page == null) page = 0;
		int startNum = page * 3;

		List<MainDto> boardsList = boardsDao.findAll(startNum);
		PagingDto paging = boardsDao.paging(page);
		
		// paging.set 머시기로 dto 완성하기!
		paging.makeBlockInfo();

//		final int blockCount = 5;
//		
//		int currentBlock = page / blockCount;
//		int startPageNum = 1 + blockCount * currentBlock;
//		int lastPageNum = 5 + blockCount * currentBlock;
//		
//		if (paging.getTotalPage() < lastPageNum) {
//			lastPageNum = paging.getTotalPage();
//		}
//		
//		paging.setBlockCount(blockCount);
//		paging.setCurrentBlock(currentBlock);
//		paging.setStartPageNum(startPageNum);
//		paging.setLastPageNum(lastPageNum);
		
		model.addAttribute("boardsList", boardsList);
		model.addAttribute("paging", paging);
		
		return "boards/main";
	}
	
	@GetMapping("/boards/{id}")
	public String getBoardDetail(@PathVariable Integer id, Model model) {
		model.addAttribute("boards", boardsDao.findById(id));
		return "boards/detail";
	}
	
	@GetMapping("/boards/writeForm")
	public String writeForm() {
		Users principal = (Users) session.getAttribute("principal");
		if(principal == null) {
			return "redirect:/loginForm";
		}
		
		return "boards/writeForm";
	}
}
