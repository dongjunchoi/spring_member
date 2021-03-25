package kr.or.ddit.user.repository;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;

@Repository("userDao")
public class UserDao implements UserDaoI {

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate template;
	
	
	@Override
	public List<UserVo> selectAllUser() {

		
		
		return template.selectList("users.selectAllUser");
	}

	@Override
	public UserVo selectUser(String userid) {

		
		return template.selectOne("users.selectUser",userid);
	}

	@Override
	public List<UserVo> selectPagingUser(PageVo vo) {

		
		return template.selectList("users.selectPagingUser",vo);
	}

	@Override
	public int selectAllUserCount() {

		return template.selectOne("users.selectAllUserCount");
	}

	@Override
	public int modifyUser(UserVo userVo) {

		
		return template.update("users.modifyUser", userVo);
	}

	@Override
	public int registUser(UserVo userVo) {

		
		return template.insert("users.registUser", userVo);
	}

	@Override
	public int deleteUser(String userid) {

		
		return template.delete("users.deleteUser", userid);
	}

	@Override
	public List<UserVo> searchIdPagingUser(PageVo vo) {

		
		return template.selectList("users.searchIdPagingUser",vo);
	}

	@Override
	public int searchIdUserCount(PageVo vo) {

		return template.selectOne("users.searchIdUserCount",vo);
	}

	@Override
	public List<UserVo> searchNamePagingUser(PageVo vo) {
	
		
		return template.selectList("users.searchNamePagingUser",vo);
	}

	@Override
	public int searchNameUserCount(PageVo vo) {

		return template.selectOne("users.searchNameUserCount",vo);
	}

	@Override
	public List<UserVo> searchAliasPagingUser(PageVo vo) {

		return template.selectList("users.searchAliasPagingUser",vo);
	}

	@Override
	public int searchAliasUserCount(PageVo vo) {
	
		return template.selectOne("users.searchAliasUserCount",vo);
	}

	
}
