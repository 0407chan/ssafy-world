package com.ssafy.ssafyworld.dao;

import java.util.List;
 
import javax.inject.Inject;
 
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
 
import com.ssafy.ssafyworld.dto.UserVO;
 
@Repository
public class UserDAOImpl implements UserDAO {
 
    @Inject
    @Autowired
    private SqlSession sqlSession;
    
    private static final String Namespace = "com.ssafy.ssafyworld.mapper.userMapper";
    
    @Override
    public List<UserVO> selectUser() throws Exception {
    	System.out.println(sqlSession);
    	List<UserVO> list=sqlSession.selectList(Namespace+".selectUser");
    	System.out.println(list);
    	return null;
//        return sqlSession.selectList(Namespace+".selectUser");
    }
 
}
