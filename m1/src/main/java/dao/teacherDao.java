package dao;

import java.sql.*;
import java.util.*;
import util.DBUtil;
import vo.*;

public class teacherDao {
	/*
	//원래 쿼리
	 SELECT t.teacher_no, t.teacher_id, t.teacher_name, 
			ts.subject_no, s.subject_name, GROUP_CONCAT(s.subject_name)	
	 	FROM teacher t INNER JOIN teacher_subject 
			ON t.teacher_no = ts.teacher_no 
				INNER join subject s
					 on ts.subject_no = s.subject_no
		GROUP BY t.teacher_no, t.teacher_id, t.teacher_name
		LIMIT 0, 10;
		
	 * issue: 교과목이 연결되지 않은 강사는 출력되지 않는다 inner join -> outer join
	 * teacherList.jsp + 화면캡쳐 + 샘플데이터 추가
	 //변경된 쿼리
	  => SELECT t.teacher_no, t.teacher_id, t.teacher_name, ts.subject_no, s.subject_name, 
		  GROUP_CONCAT(s.subject_name) subject FROM teacher t 
		  	LEFT JOIN teacher_subject ts 
		  		ON t.teacher_no = ts.teacher_no  
		  	LEFT JOIN subject s 
		  		on ts.subject_no = s.subject_no 
		  GROUP BY t.teacher_no, t.teacher_id, t.teacher_name 
		  LIMIT ?, ?";
	 
	*/
	
	//sql문
		public ArrayList<HashMap<String, Object>> selectTeacherListByPage(int beginRow, int rowPerPage) throws Exception{
		
	//db 접속
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();

	
	//sql 결과셋을 해시맵 리스트에 저장
		String listSql = "SELECT t.teacher_no, t.teacher_id, t.teacher_name, ts.subject_no, s.subject_name, GROUP_CONCAT(s.subject_name) subject FROM teacher t left JOIN teacher_subject ts ON t.teacher_no = ts.teacher_no  LEFT join subject s on ts.subject_no = s.subject_no GROUP BY t.teacher_no, t.teacher_id, t.teacher_name LIMIT ?, ?";
		PreparedStatement ListStmt = conn.prepareStatement(listSql);
		ListStmt.setInt(1, beginRow);
		ListStmt.setInt(2, rowPerPage);
	
		ResultSet ListRs = ListStmt.executeQuery();
	
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		while(ListRs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("teacher_no", ListRs.getInt("t.teacher_no"));
			m.put("teacher_id", ListRs.getString("t.teacher_id"));
			m.put("teacher_name", ListRs.getString("t.teacher_name"));
			m.put("subject_no", ListRs.getInt("ts.subject_no"));
			m.put("subject", ListRs.getString("subject"));
			
			list.add(m);
				
			}
			
		
		return list;
	}
	
	
	//페이징을 위한 메소드 만들기
		public int selectTeacherCnt() throws Exception {
			int row = 0;
			
	//db 접속
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		
	//총 행을 구하는 sql문
		int totalRow = 0;
		String pageSql = "SELECT COUNT(*) FROM teacher";
		PreparedStatement pageStmt = conn.prepareStatement(pageSql);
		ResultSet pageRs = pageStmt.executeQuery();
			if(pageRs.next()) {
				row = pageRs.getInt(1);
			}
			return row;
		}
}
