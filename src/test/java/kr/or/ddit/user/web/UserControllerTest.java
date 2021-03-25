package kr.or.ddit.user.web;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.test.comfig.WebTestConfig;

@ContextConfiguration(locations = {"classpath:/kr/or/ddit/config/spring/application-context.xml",
"classpath:/kr/or/ddit/config/spring/root-context.xml"})
@WebAppConfiguration		//�뒪�봽留� �솚寃쎌쓣 Web湲곕컲�쓽 appliction Context濡� �깮�꽦
@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest extends WebTestConfig{

	@Test
	public void loginTest() throws Exception {

		MvcResult mvcResult = mockMvc.perform(get("/user/login")).andExpect(status().isOk())
										   .andExpect(view().name("user/login"))
										   .andDo(print())
										   .andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		
		assertEquals("user/login", mav.getViewName());
	
	}
	
	@Test
	public void loginSuccessTest() throws Exception {

		MvcResult mvcResult = mockMvc.perform(post("/user/login").param("userid", "brown").param("pass", "brownPass"))
//										   .andExpect(status().isOk())
										   .andExpect(status().is3xxRedirection())
										   .andExpect(view().name("redirect:/user/pagingUser"))
										   .andDo(print())
										   .andReturn();
	}
	
	@Test
	public void pagingUserTest() throws Exception {

		MvcResult mvcResult = mockMvc.perform(get("/user/pagingUser")).andExpect(status().isOk())
										   .andExpect(view().name("user/memberList"))
										   .andDo(print())
										   .andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		
		assertEquals("user/memberList", mav.getViewName());
	
	}
	
	@Test
	public void registUserTest() throws Exception {

		MvcResult mvcResult = mockMvc.perform(get("/user/registUser")).andExpect(status().isOk())
										   .andExpect(view().name("user/registUser"))
										   .andDo(print())
										   .andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		
		assertEquals("user/registUser", mav.getViewName());
	
	}
	
	@Test
	public void userModifyTest() throws Exception {

		MvcResult mvcResult = mockMvc.perform(get("/user/userModify").param("userid", "brown")).andExpect(status().isOk())
										   .andExpect(view().name("user/userModify"))
										   .andDo(print())
										   .andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		
		assertEquals("user/userModify", mav.getViewName());
	
	}
	

}
