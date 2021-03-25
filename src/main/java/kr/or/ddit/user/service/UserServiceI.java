package kr.or.ddit.user.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;

public interface UserServiceI {
	//전체 사용자 정보 조회
	List<UserVo> selectAllUser();
		
	//userid에 해당하는 사용자 한명의 정보 조회
	UserVo selectUser(String userid);

	//user 페이징 처리 후 조회
	Map<String, Object> selectPagingUser(PageVo vo);
	
	//아이디 검색 페이징 처리 후 조회
	Map<String, Object> searchIdPagingUser(PageVo vo);
	
	//이름검색 페이징 처리 후 조회
	Map<String, Object> searchNamePagingUser(PageVo vo);
	
	//별명검색 페이징 처리 후 조회
	Map<String, Object> searchAliasPagingUser(PageVo vo);
	
	//사용자 정보 수정
	int modifyUser(UserVo userVo);
	
	//사용자 정보 등록
	int registUser(UserVo userVo);

	//사용자 삭제
	int deleteUser(String userid);
	
	int selectAllUserCount();
}
