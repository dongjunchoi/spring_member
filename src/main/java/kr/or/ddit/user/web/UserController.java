package kr.or.ddit.user.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;
import kr.or.ddit.user.service.UserServiceI;
import kr.or.ddit.user.util.FileUtil;

@RequestMapping("user")
@Controller
public class UserController {
	
	
	@Resource(name = "userService")
	private UserService userService;
	
	
	@RequestMapping(path = "login",method = RequestMethod.GET)
	public String login() {
		 
		return "user/login";
		
		
	}
	
	
	@RequestMapping(path = "login",method = RequestMethod.POST)
	public String loginUser(String userid,String pass, HttpSession session, Model model) {
		UserVo user = userService.selectUser(userid);
		
		
		if( user !=null && pass.equals(user.getPass())) {
			session.setAttribute("S_USER", user); 
			return "redirect:/user/pagingUser";
			
		}else {
			model.addAttribute("userid", userid);
			return "user/login";
		}
		
	}
	
	@RequestMapping(path = "logout",method = RequestMethod.GET)
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/user/login";
		
		
	}
	
	
	
	@RequestMapping(path = "pagingUser",method = RequestMethod.GET)
	public String loginUser(String page, String pageSize,String keyword, String searchType, String ps, Model model) {
		
		
		if(ps==null) {
			ps ="";
		}
		
		int lastPage = 0;
	
		
		int pageNo = page == null ?  1 : Integer.parseInt(page);
		int pageSizeNo = ps==null||ps=="" ? 5:Integer.parseInt(ps); 
		
		if(pageSize == null) {
			pageSize = "";
		}
		
		if(searchType == null) {
			searchType = "";
		}
		
		if(keyword == null) {
			keyword = "";
		}
		
		PageVo vo = new PageVo(pageNo,pageSizeNo);
		vo.setKeyword(keyword);
		
		Map<String, Object> map = null;
		
		if(searchType.equals("i")){
			map = userService.searchIdPagingUser(vo);
		}else if(searchType.equals("n")){
			map = userService.searchNamePagingUser(vo);
		}else if(searchType.equals("a")){
			map = userService.searchAliasPagingUser(vo);
		}else{
			map = userService.selectPagingUser(vo);
		} 
		
		
		
		List<UserVo> userList = (List<UserVo>)map.get("userList");
		int userCnt = (int)map.get("userCnt");
		int pagination = (int)Math.ceil((double)userCnt/pageSizeNo);
		
		
		
		
		int startPage = 1;
		int endPage = pagination;
		if((pageNo-2)>2) {
			if(pageNo==pagination||pageNo==pagination-1||pageNo==pagination-3) {
				startPage = pagination-4;
			}else{
				startPage = pageNo-2;
			}
			if(startPage+4<pagination) {
				endPage = startPage+4;
			}
		}
		if((pageNo+2)<pagination-1) {
			if(pageNo==1) {
				endPage = pageNo+4;
			}else if(pageNo == 2) {
				endPage = pageNo+3;
			}else if(pageNo == 4){
				endPage = pageNo+1;
			}else {
				endPage = pageNo+2;
			}
			if(endPage-4>pageNo) {
				startPage = endPage-4;
			}
		}
		
		model.addAttribute("userList", userList);
		model.addAttribute("pagination", pagination);
		model.addAttribute("pageVo", vo);
		model.addAttribute("pageSizeStr", pageSize);
		model.addAttribute("searchType", searchType);
		model.addAttribute("keyword", keyword);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("ps", ps);
		
		return "user/memberList";
		
	}
	
	
	@RequestMapping(path = "user",method = RequestMethod.GET)
	public String user(String userid, Model model) {
		
		model.addAttribute("user", userService.selectUser(userid));
		
		return "user/user";
		
		
	}
	
	@RequestMapping(path = "registUser",method = RequestMethod.GET)
	public String registUserform() {
		
	
		
		return "user/registUser";
		
		
	}
	
	
	@RequestMapping(path = "registUser",method = RequestMethod.POST)
	public String registUser(UserVo userVo, Model model,MultipartFile profile,RedirectAttributes ra) {

		
		if("".equals(profile.getOriginalFilename())) {
			userVo.setFilename("");
			userVo.setRealfilename("");
		}else {

			try {
				String fileExtension = FileUtil.getFileExtension(profile.getOriginalFilename());
				String realFileName = "d:/upload/" + UUID.randomUUID().toString()+fileExtension;
				
				profile.transferTo(new File(realFileName));
				userVo.setFilename(profile.getOriginalFilename());
				
				userVo.setRealfilename(realFileName);
				
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		 
		int insertCnt = 0;
		
		try {
			insertCnt = userService.registUser(userVo);
		} catch (Exception e) {
			insertCnt= 0;
		}
		
		 
		
		if(insertCnt==1) {
			ra.addAttribute("userid", userVo.getUserid());
			return "redirect:/user/user";
		}else {
			model.addAttribute("user", userVo);
			return "user/registUser";
		}
				
	}
	
	@RequestMapping(path = "userModify",method = RequestMethod.GET)
	public String userModifyform(String userid, Model model) {
		
		model.addAttribute("user", userService.selectUser(userid));
	
		
		return "user/userModify";
		
		
	}
	
	
	@RequestMapping(path = "userModify",method = RequestMethod.POST)
	public String userModify(UserVo userVo, Model model,MultipartFile profile,RedirectAttributes ra) {
		
		UserVo vo = userService.selectUser(userVo.getUserid());
		
		
		if(userVo.getFilename()==null) {
			userVo.setFilename(vo.getFilename());
			
			if(userVo.getFilename()==null) {
				userVo.setFilename("");
			}
		}
		
		
		if(userVo.getRealfilename()==null) {
			userVo.setRealfilename(vo.getRealfilename());
			
			if(userVo.getRealfilename()==null) {
				userVo.setRealfilename("");
			}
		}
		
		if("".equals(profile.getOriginalFilename())) {
			
		}else {
			try {
				String fileExtension = FileUtil.getFileExtension(profile.getOriginalFilename());
				String realFileName = "d:/upload/" +UUID.randomUUID().toString()+fileExtension;
				
				profile.transferTo(new File(realFileName));
				userVo.setFilename(profile.getOriginalFilename());
				
				userVo.setRealfilename(realFileName);
				
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		int updateCnt = 0;
		
		try {
			updateCnt = userService.modifyUser(userVo);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			updateCnt= 0;
		}
		
		 
		
		if(updateCnt==1) {
			ra.addAttribute("userid", userVo.getUserid());
			return "redirect:/user/user";
		}else {
			model.addAttribute("user", userVo);
			return "user/userModify";
		}
		
		
	}
	
	
	@RequestMapping(path = "deleteUser",method = RequestMethod.POST)
	public String deleteUser(String userid, Model model) {
		
		int deleteCnt = 0;
		
		try {
			deleteCnt= userService.deleteUser(userid);			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			deleteCnt=0;
		}
		
		
		if(deleteCnt==1) {
			return "redirect:/user/pagingUser";
		}else {
			model.addAttribute("user", userService.selectUser(userid));
			return "user/user";
		}
	
		
	}
	
	@RequestMapping("profile")
	public void profile(HttpServletResponse resp, String userid, HttpServletRequest req) {

		resp.setContentType("image");
		
		
		
		UserVo userVo = userService.selectUser(userid);
		
		String path = "";
		if(userVo.getRealfilename() == null) {
			path = req.getServletContext().getRealPath("/image/unknown.png");
		}else {
		
			path = userVo.getRealfilename();
		}
		
		try {
			
			FileInputStream fis = new FileInputStream(path);
			ServletOutputStream sos = resp.getOutputStream();
			
			byte[] buff = new byte[512];
			while(fis.read(buff)!=-1) {
				
				sos.write(buff);
				
			}
			
			
			fis.close();
			sos.flush();
			sos.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	@RequestMapping("/profileDownload")
	public String fileDownload(String userid, Model model) {
	
		
		UserVo userVo = userService.selectUser(userid);
		model.addAttribute("realFilename", userVo.getRealfilename());
		model.addAttribute("filename", userVo.getFilename());
		
		
		return "fd";
	}

	@RequestMapping(path = "userCheck",method = RequestMethod.POST)
	public String userCheck(String userid, Model model) {
		int cnt = 1;
		
		UserVo userVo = userService.selectUser(userid);
		
		if(userVo == null) {
			cnt = 0;
		}
		
		model.addAttribute("cnt", cnt);
	
		
		return "jsonView";
		
		
	}

	
	
	
	
}
