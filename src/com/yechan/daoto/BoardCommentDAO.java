package com.yechan.daoto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardCommentDAO {


	
	
	private static BoardCommentDAO dao = new BoardCommentDAO();
	
	private DataSource dataSource;
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	private PreparedStatement prestat;
	
	private BoardCommentDAO() {
		
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/orcl");
		
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		
	}
	
	public static BoardCommentDAO getInstance()	{
		return dao;
	}
	
	
	public ArrayList<BoardCommentDTO> getComments(int bNum)	{
		
		ArrayList<BoardCommentDTO> dtos = new ArrayList<BoardCommentDTO>();
				
		try {
			
			connection = dataSource.getConnection();			
			String query = "Select * from BoardComment where cNum = ? order by cDate asc";
			prestat = connection.prepareStatement(query);
			prestat.setInt(1, bNum);
			
			resultSet = prestat.executeQuery();
						
			while(resultSet.next())	{
				BoardCommentDTO dto = new BoardCommentDTO();
				
				dto.setcAvailable(resultSet.getInt("cAvailable"));
				dto.setcContent(resultSet.getString("cContent"));
				dto.setcDate(resultSet.getTimestamp("cDate"));
				dto.setcId(resultSet.getString("cId"));
				dto.setcNum(resultSet.getInt("cNum"));
				dtos.add(dto);
			}
			
		}catch(Exception e)	{
			
			e.printStackTrace();
		}
		finally		{	
			try {
			connection.close();
			prestat.close();
			resultSet.close();
			}
			catch(Exception e)	{
				e.printStackTrace();
			}
			
		}
		return dtos;
	}
	
	public int insertComment(BoardCommentDTO dto) {	
		
		int result = -1;
		
		try	{
			
			connection = dataSource.getConnection();
			String query = "insert into BoardComment values (?,?,?,?,?)";			
			prestat = connection.prepareStatement(query);
			
			prestat.setInt(1,dto.getcNum());
			prestat.setString(2, dto.getcContent());
			prestat.setString(3, dto.getcId());
			prestat.setTimestamp(4,dto.getcDate());
			prestat.setInt(5,dto.getcAvailable());
			
			result = prestat.executeUpdate();
			
			connection.close();
			prestat.close();
		}
		catch(Exception e)	{
			e.printStackTrace();
		}
				
		return result;
	}
	
	
	
}
