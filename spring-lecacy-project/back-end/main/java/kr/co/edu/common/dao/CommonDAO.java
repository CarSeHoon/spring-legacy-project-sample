package kr.co.edu.common.dao;

import java.util.List; 

import org.apache.ibatis.annotations.Mapper;

import kr.co.edu.common.vo.Test;
//dao는 쿼리 날리고 service에서 데이터를 가공한다. controller에서는 request와 response 담당
@Mapper
public interface CommonDAO {
	
	public List<Test> testSelectList();
}
