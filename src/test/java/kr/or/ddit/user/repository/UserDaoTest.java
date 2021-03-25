package kr.or.ddit.user.repository;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.test.comfig.ModelTestConfig;
import kr.or.ddit.user.model.UserVo;

public class UserDaoTest extends ModelTestConfig{

	@Resource(name="userDao")
	private UserDao userDao;
	
	@Test
	public void getUserTest() {
		/***Given***/
		String userid = "brown";

		/***When***/
	 	UserVo userVo = userDao.selectUser(userid);

		/***Then***/
	 	assertEquals("브라운", userVo.getUsernm());
	}
	
	
	@Test
	public void selectAllUserTest() {
		/***Given***/
		/***When***/
	 	List<UserVo> userVoList = userDao.selectAllUser();

		/***Then***/
	 	assertEquals(78, userVoList.size());
	}
	
	@Test
	public void selectPagingUserTest() {
		/***Given***/
		PageVo pageVo = new PageVo(1, 5);

		/***When***/
		List<UserVo> userVoList = userDao.selectPagingUser(pageVo);

		/***Then***/
	 	assertEquals(5, userVoList.size());
	}
	
	@Test
	public void searchIdPagingUserTest() {
		/***Given***/
		PageVo pageVo = new PageVo(1, 5);
		pageVo.setKeyword("b");
		/***When***/
		List<UserVo> userVoList = userDao.searchIdPagingUser(pageVo);

		/***Then***/
	 	assertEquals(5, userVoList.size());
	}
	
	
	@Test
	public void searchIdUserCountTest() {
		/***Given***/
		PageVo pageVo = new PageVo(1, 5);
		pageVo.setKeyword("b");
		/***When***/
		int userList = userDao.searchIdUserCount(pageVo);

		/***Then***/
	 	assertEquals(8, userList);
	}
	
	@Test
	public void searchNamePagingUserTest() {
		/***Given***/
		PageVo pageVo = new PageVo(1, 5);
		pageVo.setKeyword("브");
		/***When***/
		List<UserVo> userVoList = userDao.searchNamePagingUser(pageVo);

		/***Then***/
	 	assertEquals(4, userVoList.size());
	}
	
	@Test
	public void searchNameUserCountTest() {
		/***Given***/
		PageVo pageVo = new PageVo(1,5);
		pageVo.setKeyword("브");
		/***When***/
		int userList = userDao.searchNameUserCount(pageVo);

		/***Then***/
	 	assertEquals(4, userList);
	}
	

	@Test
	public void selectAliasPagingUserTest() {
		/***Given***/
		PageVo pageVo = new PageVo(1, 5);
		pageVo.setKeyword("달");
		/***When***/
		List<UserVo> userVoList = userDao.searchAliasPagingUser(pageVo);

		/***Then***/
	 	assertEquals(4, userVoList.size());
	}
	
	@Test
	public void searchAliasUserCountTest() {
		/***Given***/
		PageVo pageVo = new PageVo(1,5);
		pageVo.setKeyword("달");
		/***When***/
		int userList = userDao.searchAliasUserCount(pageVo);

		/***Then***/
	 	assertEquals(4, userList);
	}
	
	
	
	@Test
	public void selectAllUserCountTest() {
		/***Given***/
	

		/***When***/
	 	int userCnt = userDao.selectAllUserCount();

		/***Then***/
	 	assertEquals(78, userCnt);
	}
	
	@Test
	public void registUserTest() {
		/***Given***/
		UserVo uservo = new UserVo();
		uservo.setUserid("a006");
		uservo.setUsernm("생성이름");
		uservo.setPass("1233567");
		uservo.setAlias("생성별명");
		uservo.setAddr1("생성주소1");
		uservo.setAddr2("생성주소2");
		uservo.setZipcode("1234");
		uservo.setFilename("생성파일이름");
		uservo.setRealfilename("찐생성파일이름");
		uservo.setReg_dt(new Date());

		/***When***/
	 	int cnt = userDao.registUser(uservo);

		/***Then***/
	 	assertEquals(1, cnt);
	}
	
	
	
	@Test
	public void modifyUserTest() {
		/***Given***/
		UserVo uservo = new UserVo();
		uservo.setUserid("a006");
		uservo.setUsernm("수정이름");
		uservo.setPass("1233567");
		uservo.setAlias("수정별명");
		uservo.setAddr1("수정주소1");
		uservo.setAddr2("수정주소2");
		uservo.setZipcode("1234");
		uservo.setFilename("수정파일이름");
		uservo.setRealfilename("찐수정파일이름");
		uservo.setReg_dt(new Date());

		/***When***/
	 	int cnt = userDao.modifyUser(uservo);

		/***Then***/
	 	assertEquals(1, cnt);
	}
	
	
	
	@Test
	public void deleteUserTest() {
		/***Given***/
		String userid = "a006";

		/***When***/
		int cnt = userDao.deleteUser(userid);

		/***Then***/
	 	assertEquals(1, cnt);
	}

}
