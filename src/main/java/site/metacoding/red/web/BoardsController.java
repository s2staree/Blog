package site.metacoding.red.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.web.dto.request.boards.WriteDto;
import site.metacoding.red.web.dto.response.boards.MainDto;

@RequiredArgsConstructor
@Controller
public class BoardsController {
   
	private final HttpSession session;
	private final BoardsDao boardsDao;
   //@PostMapping("boards/{id}/delete")
   //@PostMapping("boards/{id}/update")

	@PostMapping("/boards")	// boards 테이블에 insert 요청이 들어오면
	public String writeBoards(WriteDto writeDto) {
		// 1. 세션에 접근해서 세션값을 확인한다. 그때 Users로 다운캐스팅하고 키값은 principal로 한다.
		// 2. principal이 null인지 확인하고 null이면 loginForm을 redirection해준다.
		// 3. BoardsDao에 접근해서 insert 메서드를 호출한다.
		// 조건: Dto를 Entity로 변환해서 인수로 담아준다.
		// 조건: Entity에는 세션의 principal에 getId가 필요하다.
		
		Users principal = (Users) session.getAttribute("principal");
		
		// 인증 코드
		if(principal == null) {
			return "redirect:/loginForm";
		}
		
		// 본 코드(핵심 코드). return시 밑 코드는 어차피 실행이 안되므로 else문 사용 안해도 됨(더 깔끔!)
		boardsDao.insert(writeDto.toEntity(principal.getId()));
		return "redirect:/";
	}
   
	
	
   @GetMapping({"/","/boards"})
   public String getBoardList(Model model, Integer page) {
	   if(page == null) page = 0;
	   int startNum = page *10;
	   List<MainDto> boardsList = boardsDao.findAll(startNum);
	   model.addAttribute("boardsList", boardsList);
      return "boards/main";
   }
   
   @GetMapping("/boards/{id}")
   public String getBoardList(@PathVariable Integer id, Model model) {
	   model.addAttribute("boards", boardsDao.findById(id));
      return "boards/detail";
   }
   
   @GetMapping("/boards/writeForm")
   public String writeForm() {
	  Users principal = (Users) session.getAttribute("principal");
	  
		// 인증 코드
	  if(principal == null) {
		  return "redirect:/loginForm";	// (로그인) 인증만 필요한 코드. loginForm으로 가는 메서드가 이미 있으므로 redirect(통신 재요청)한다.
	  }
	  
		// 본 코드(핵심 코드). return시 밑 코드는 어차피 실행이 안되므로 else문 사용 안해도 됨(더 깔끔!)
	  return "boards/writeForm";	// 뷰를 찾아서 응답해주는 코드. 데이터 요청(통신 요청X)
   }
}