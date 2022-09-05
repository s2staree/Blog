package site.metacoding.red.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.users.Users;

@RequiredArgsConstructor
@Controller
public class BoardsController {
   
	private final HttpSession session;
   //@PostMapping("boards/{id}/delete")
   //@PostMapping("boards/{id}/update")
   
   @GetMapping({"/","/boards"})
   public String getBoardList() {
      return "boards/main";
   }
   
   @GetMapping("/boards/{id}")
   public String getBoardList(@PathVariable Integer id) {
      return "boards/detail";
   }
   
   @GetMapping("/boards/writeForm")
   public String writeForm() {
	  Users principal = (Users) session.getAttribute("principal");
	  if(principal == null) {
		  return "redirect:/loginForm";	// (로그인) 인증만 필요한 코드. loginForm으로 가는 메서드가 이미 있으므로 redirect(통신 재요청)한다.
	  }else {
		  return "boards/writeForm";	// 뷰를 찾아서 응답해주는 코드. 데이터 요청(통신 요청X)
	  }
   }
}