package com.saeyan.dto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//싱글톤
public class MemberDAO {
	private static MemberDAO instance = new MemberDAO();
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private MemberDAO() {
		
	}
	public static MemberDAO getInstance() {
		return instance;
	}
	
	public Connection getConnection() throws Exception {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "ezen";
		String password = "1234";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");	//1. 드라이브 로드
		return DriverManager.getConnection(url, user, password);	//2. DB연결
	}
	public int confirmID(String userid) {	//ID중복체크
		int result = -1;
		String sql = "select userid from member where userid = ?";
		
		try {
			con = getConnection();	//1. DB 연결
			pstmt = con.prepareStatement(sql);	//2. SQL문 전송
			pstmt.setString(1, userid);	//3. 맵핑
			rs = pstmt.executeQuery();	//4. 실행 및 결과값 받기
			
			if(rs.next()) {
				result = 1;
			} else {
				result = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public int insertMember(MemberVO vo) {
		int result = -1;
		String sql = "insert into member values(?, ?, ?, ?, ?, ?)";
		try {
			
		} catch(Exception e) {                    
			e.printStackTrace();
		}finally {
			try {
				con = getConnection();	//연결
				pstmt = con.prepareStatement(sql);	//sql구문 전송
				pstmt.setString(1, vo.getName());	//3. 맵핑
				pstmt.setString(2, vo.getUserid());	//3. 맵핑
				pstmt.setString(3, vo.getPwd());	//3. 맵핑
				pstmt.setString(4, vo.getEmail());	//3. 맵핑
				pstmt.setString(5, vo.getPhone());	//3. 맵핑
				pstmt.setInt(6, vo.getAdmin());	//3. 맵핑
				
				result = pstmt.executeUpdate();	//구문 실행
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	public int userCheck(String userid, String pwd) {
		int result = -1;
		
		String sql = "select pwd from member where userid = ? ";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				if (rs.getString("pwd").equals(pwd)) {
					result = 1;		//로그인 성공
				} else {
					result = -1;	//비밀번호 불일치
				}
			} else {
				result = 0;	//아이디 불일치
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	//로그인 한 회원정보 가져오기
	public MemberVO getMember(String userid) {
		String sql = "select * from member where userid = ?";
		MemberVO vo = new MemberVO();
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				String name = rs.getString("name");
				
				vo.setName(name);
				vo.setUserid(rs.getString("userid"));
				vo.setEmail(rs.getString("email"));
				vo.setPwd(rs.getString("pwd"));
				vo.setPhone(rs.getString("phone"));
				vo.setAdmin(rs.getInt("admin"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return vo;
	}
	public int updateMember(MemberVO vo) {
		int result = -1;
		String sql = "update member set name=?, pwd=?, email=? "
				+ "phone=?, admin=? where userid=?";
		try {
			
		} catch(Exception e) {                    
			e.printStackTrace();
		}finally {
			try {
				con = getConnection();	//연결
				pstmt = con.prepareStatement(sql);	//sql구문 전송
				pstmt.setString(1, vo.getName());	//3. 맵핑
				pstmt.setString(6, vo.getUserid());	//3. 맵핑
				pstmt.setString(2, vo.getPwd());	//3. 맵핑
				pstmt.setString(3, vo.getEmail());	//3. 맵핑
				pstmt.setString(4, vo.getPhone());	//3. 맵핑
				pstmt.setInt(5, vo.getAdmin());	//3. 맵핑
				
				result = pstmt.executeUpdate();	//구문 실행
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
}
