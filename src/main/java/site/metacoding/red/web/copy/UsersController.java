package site.metacoding.red.web.copy;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.domain.users.UsersDao;
import site.metacoding.red.web.dto.request.users.JoinDto;
import site.metacoding.red.web.dto.request.users.LoginDto;

@RequiredArgsConstructor //DI
@Controller
public class UsersController {
   
	private final HttpSession session; //스프링이 서버시작시 IOC 컨테이너에 보관함
	private final UsersDao usersDao; //composition
	
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
   
   @PostMapping("/login") // 로그인만 예외로 select인데 post로 함
//  public String login(LoginDto loginDto, HttpServletRequest request) {
	public String login(LoginDto loginDto) {

	   Users usersPS = usersDao.login(loginDto);
	   if(usersPS != null) { // 로그인 인증 됨
//		   HttpSession session = request.getSession();
		   session.setAttribute("principal", usersPS);
		   return "redirect:/";
	   }else {	// 로그인 인증 안됨
		   return "redirect:/loginForm";
	   }
   }
   
   @PostMapping("/join")
   public String join(JoinDto joinDto) {
      usersDao.insert(joinDto);
      return "redirect:/loginForm";
   }

   @GetMapping("/loginForm")
   public String loginForm() {
      return "users/loginForm";
   }
   
   @GetMapping("/joinForm")
   public String joinForm() {
      return "users/joinForm";
   }
}