package dao;

import java.sql.*;
import java.util.*;
import util.DBUtil;
import vo.*;


public class SubjectDao {
	//CRBD
	
	//1) 과목 목록 // 10개씩
	public ArrayList<Subject> selectSubjectListByPage(int beginRow, int rowPerPage) throws Exception {
		ArrayList<Subject> list = new ArrayList<>();
		
		//db 접속
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();

		
		//sql 결과셋을 리스트에 저장
		PreparedStatement ListStmt = conn.prepareStatement("SELECT subject_no subjectNo, subject_name subjectName, subject_time subjectTime, updatedate, createdate FROM subject WHERE subject_no BETWEEN ? AND ?");
		ListStmt.setInt(1, beginRow);
		ListStmt.setInt(2, rowPerPage);

		ResultSet ListRs = ListStmt.executeQuery();

		
		
		while(ListRs.next()){
		 	Subject s = new Subject();
		 	
		 	s.setSubjectNo(ListRs.getInt("subjectNo"));
		 	s.setSubjectName(ListRs.getString("subjectName"));
		 	s.setSubjectTime(ListRs.getInt("subjectTime"));
		 	s.setUpdatedate(ListRs.getString("updatedate"));
		 	s.setCreatedate(ListRs.getString("createdate"));

		 	list.add(s);
		 	}
		
			
		return list;
	}
	//2) 과목 추가
	public int insertSubject(SubjectDao subject) throws Exception {
		
		int row = 0;
		DBUtil dbutil = new DBUtil();
		Class.forName("org.mariadb.jdbc.Driver");
		Connection conn = dbutil.getConnection();
		String insertSubjectSql ="";
		return row;
	}
	//3) 과목 삭제
	public int deleteSubject(int subjectNo) throws Exception {
		int row = 0;
		DBUtil dbutil = new DBUtil();
		Connection conn = dbutil.getConnection();
		return row;
	}
	
	//4) 과목 수정
	public int updateSubject(SubjectDao subject) throws Exception {
		int row = 0;
		DBUtil dbutil = new DBUtil();
		Connection conn = dbutil.getConnection();
		//Prepared Statement
		return row;
	}
	
	
	//5) 과목 하나 상세
	public SubjectDao selectSubjectOne(int subjectNo) throws Exception {
		SubjectDao subject = null;
		DBUtil dbutil = new DBUtil();
		Connection conn = dbutil.getConnection();
		//Prepared Statement
		//ResultSet
		return subject;
	}
	
	//6) 과목 전체 row 수
	public int selectSubfictCnt() throws Exception {
		int row = 0;
		DBUtil dbutil = new DBUtil();
		Connection conn = dbutil.getConnection();
		//Prepared Statement
		//ResultSet
		return row;
	}
}
