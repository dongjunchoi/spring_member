package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.repository.UserDao;

@Service("userService")
public class UserService implements UserServiceI {
	
	@Resource(name = "userDao")
	private UserDao dao;
	
	@Override
	public List<UserVo> selectAllUser() {
		// TODO Auto-generated method stub
		return dao.selectAllUser();
	}

	@Override
	public UserVo selectUser(String userid) {
		// TODO Auto-generated method stub
		return dao.selectUser(userid);
	}

	@Override
	public Map<String, Object> selectPagingUser(PageVo vo) {
		Map<String, Object> map = new HashMap<String,Object>();
		
		List<UserVo> userList = dao.selectPagingUser(vo);
		int userCnt = dao.selectAllUserCount();
		
		map.put("userList", userList);
		map.put("userCnt", userCnt);
		return map;
	}

	@Override
	public int modifyUser(UserVo userVo) {
		// TODO Auto-generated method stub
		return dao.modifyUser(userVo);
	}

	@Override
	public int registUser(UserVo userVo) {
		// TODO Auto-generated method stub
		return dao.registUser(userVo);
	}

	@Override
	public int deleteUser(String userid) {
		// TODO Auto-generated method stub
		return dao.deleteUser(userid);
	}

	@Override
	public int selectAllUserCount() {
		// TODO Auto-generated method stub
		return dao.selectAllUserCount();
	}

	@Override
	public Map<String, Object> searchIdPagingUser(PageVo vo) {
		Map<String, Object> map = new HashMap<String,Object>();
		
		List<UserVo> userList = dao.searchIdPagingUser(vo);
		int userCnt = dao.searchIdUserCount(vo);
		
		map.put("userList", userList);
		map.put("userCnt", userCnt);
		return map;
	}

	@Override
	public Map<String, Object> searchNamePagingUser(PageVo vo) {
		Map<String, Object> map = new HashMap<String,Object>();
		
		List<UserVo> userList = dao.searchNamePagingUser(vo);
		int userCnt = dao.searchNameUserCount(vo);
		
		map.put("userList", userList);
		map.put("userCnt", userCnt);
		return map;
	}

	@Override
	public Map<String, Object> searchAliasPagingUser(PageVo vo) {
		Map<String, Object> map = new HashMap<String,Object>();
		
		List<UserVo> userList = dao.searchAliasPagingUser(vo);
		int userCnt = dao.searchAliasUserCount(vo);
		
		map.put("userList", userList);
		map.put("userCnt", userCnt);
		return map;
	}

	

}
