package kr.or.ddit.user.repository;

import java.util.List;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;

public interface UserDaoI {
	
	//전체 사용자 정보 조회
	List<UserVo> selectAllUser();
	
	//userid에 해당하는 사용자 한명의 정보 조회
	UserVo selectUser(String userid);

	// 사용자 페이징 조회
	List<UserVo> selectPagingUser(PageVo vo);
	
	//유저 전체 수 조회
	int selectAllUserCount();
	
	//아이디검색 페이징 조회
	List<UserVo> searchIdPagingUser(PageVo vo);
	
	//아이디검색 유저수 조회
	int searchIdUserCount(PageVo vo);
	
	//이름검색 페이징
	List<UserVo> searchNamePagingUser(PageVo vo);
	
	//이름검색 유저수
	int searchNameUserCount(PageVo vo);
	
	//별명검색 유저수 조회
	List<UserVo> searchAliasPagingUser(PageVo vo);
	
	//별명검색 유저수
	int searchAliasUserCount(PageVo vo);
	
	//사용자 정보 수정
	int modifyUser(UserVo userVo);
	
	//사용자 정보 등록
	int registUser(UserVo userVo);

	//사용자 정보 삭제
	int deleteUser(String userid);
	
}
