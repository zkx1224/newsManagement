package com.hbase.test.servlet.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hbase.test.servlet.entities.User;
import com.hbase.test.servlet.jdbcutil.DatabaseUtil;

public class UserServiceImpl implements UserService {
	@Override
	public boolean checkUserNameIsExit(String userName) throws SQLException {
		Connection conn = null;
		int count = 0;
		try {
			conn = DatabaseUtil.getConnection();
			User user = null;
			String sql = "SELECT * FROM usertable WHERE userName = ?";
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, userName);
			ResultSet rs = ptmt.executeQuery();
			while (rs.next()) {
				count++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DatabaseUtil.closeAll(conn, null, null);
		}
		if (count > 0)
			return true;
		return false;
	}
	
	

	@Override
	public void addUser(User user) throws SQLException {
		Connection conn=null;
		try {
		//获取连接
        conn = DatabaseUtil.getConnection();
        //sql
        String sql = "INSERT INTO usertable (userName, userSex, userPassword, userEmail, userBirthday,userFlag)"
                +" values("+"?,?,?,?,?,?)";
        //预编译
        PreparedStatement ptmt = conn.prepareStatement(sql); //预编译SQL，减少sql执行
        ptmt.setString(1, user.getUserName());
        ptmt.setString(2, user.getUserSex());
        ptmt.setString(3, user.getUserPassword());
        ptmt.setString(4, user.getUserEmail());
        //java.util.Date cannot be cast to java.sql.Date
        ptmt.setDate(5, new java.sql.Date(user.getUserBirthday().getTime()));
        //new java.sql.Date(user.getBirth().getTime())
        ptmt.setInt(6, user.getUserFlag());
        //执行
        ptmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DatabaseUtil.closeAll(conn, null, null);
		}
	}

	//查询成功，则返回user信息，返回null则代表没有该用户
	@Override
	public User getUserByUserName(String userName) throws SQLException {
		Connection conn = null;
		User user = null;
		try {
			conn = DatabaseUtil.getConnection();
			String sql = "SELECT * FROM usertable WHERE userName = ?";
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, userName);
			ResultSet rs = ptmt.executeQuery();
			while (rs.next()) {
				user=new User();
				user.setUserName(rs.getString("userName"));
				user.setUserSex(rs.getString("userSex"));
				user.setUserEmail(rs.getString("userEmail"));
				user.setUserPassword(rs.getString("userPassword"));
				user.setUserBirthday(rs.getDate("userBirthday"));
				user.setUserFlag(rs.getInt("userFlag"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DatabaseUtil.closeAll(conn, null, null);
		}
		return user;

	}
}
