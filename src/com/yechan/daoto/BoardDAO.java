package com.yechan.daoto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {

	
	
	private static BoardDAO dao = new BoardDAO();
	
	private DataSource dataSource;
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	private PreparedStatement prestat;
	
	private BoardDAO()
	{
		
		try
		{
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/orcl");
		
		}catch(Exception e)
		{
			System.out.println(e.toString() + "생성자 오류");
		}
		
		
	}
	
	public static BoardDAO getInstance()
	{
		
		return dao;
	}
	
	
	public int getNextNum() {
		
		
		int ri = -1;
		Connection con;
		PreparedStatement pre;
		
		try {
			con = dataSource.getConnection();
			String query = "select bNum from board order by bNum desc";
			 pre = con.prepareStatement(query);
			resultSet = pre.executeQuery();
			
			
			ri = 1;
			
			
			if(resultSet.next())
			{
				ri = resultSet.getInt(1)+1;
			}
			
			con.close();
			pre.close();
			
			
		}
		catch(Exception e)
		{
			System.out.println(e.toString() + "getNextNum 오류");
		}
		
		
	return ri;
	
	}
	
	
	public int InsertBoard(BoardDTO dto)
	{
		int insertResult = 0;
		
		
		try
		{
			connection = dataSource.getConnection();
			String query = "insert into board values (?,?,?,?,?,?)";
			prestat = connection.prepareStatement(query);
			
			prestat.setInt(1, dto.getbNum());
			prestat.setString(2, dto.getbTitle());
			prestat.setString(3, dto.getbId());
			prestat.setTimestamp(4, dto.getbDate());
			prestat.setString(5, dto.getbContent());
			prestat.setInt(6, dto.getbAvailable());
		
			prestat.executeUpdate();
			
			insertResult = 1;
			
			
		}catch(Exception e)
		{
			System.out.println(e.toString() + "insert 오류");
		}
		finally
		{
			try {
				
			if(!prestat.isClosed()) prestat.close();
			if(!connection.isClosed()) connection.close();
			
			}
			catch(Exception e)
			{
				System.out.println(e.toString() + "insert finally");
			}
		}
		
		
		return insertResult;
	}
	
	
	public ArrayList<BoardDTO> getBoards(int pageNumber){
		
		ArrayList<BoardDTO> dtos = new ArrayList<BoardDTO>();

		try {

			connection = dataSource.getConnection();
			String query = "select * from board where bNum < ? and rownum >= 0 and rownum <= 10 order by bNum desc" ;
			
			
			int i = getNextNum()-(pageNumber-1)*10;
			prestat = connection.prepareStatement(query);
			prestat.setInt(1, i);
			resultSet = prestat.executeQuery();


			while (resultSet.next()) {
				
				
				BoardDTO dto = new BoardDTO(
						resultSet.getInt(1),
						resultSet.getString(2),
						resultSet.getString(3),
						resultSet.getTimestamp(4),
						resultSet.getString(5),
						resultSet.getInt(6)	);
				dtos.add(dto);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString()+"getboards 오류");
		} finally {
			try {
				if (!connection.isClosed())
					connection.close();
				if (!prestat.isClosed())
					prestat.close();
				if (!resultSet.isClosed())
					resultSet.close();
			} catch (Exception e) {
				System.out.println(e.toString()+ "getboards finally");
			}
		}

		return dtos;
	}	
	
	
	public boolean nextPage(int pageNumber)
	{
		try {
			connection = dataSource.getConnection();
			String query = "select * from board where bNum < ? and bAvailable = 1";
			prestat = connection.prepareStatement(query);
			prestat.setInt(1, getNextNum() - (pageNumber -1)*10);
			resultSet = prestat.executeQuery();

			if (resultSet.next()) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.toString() +"nextpage 오류");
		} finally {
			try {
				if (!connection.isClosed())
					connection.close();
				if (!prestat.isClosed())
					prestat.close();
				if (!resultSet.isClosed())
					resultSet.close();
			} catch (Exception e) {
				System.out.println(e.toString()+ "nextpage fianally");
			}
		}

		return false;
		
	}
	
	public BoardDTO getBoardFrombNum(int bNum)
	{
		BoardDTO dto = new BoardDTO();
		
		try {
			
			connection = dataSource.getConnection();
			String query = "select * from board where bNum = ?";
			prestat = connection.prepareStatement(query);
			prestat.setInt(1, bNum);
			
			resultSet = prestat.executeQuery();
			
			if(resultSet.next())
			{
				dto.setbAvailable(resultSet.getInt("bAvailable"));
				dto.setbContent(resultSet.getString("bContent"));
				dto.setbDate(resultSet.getTimestamp("bDate"));
				dto.setbId(resultSet.getString("bId"));
				dto.setbNum(resultSet.getInt("bNum"));
				dto.setbTitle(resultSet.getString("bTitle"));
			}
			
			
			connection.close();
			prestat.close();
			
			
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}
		
		
		
		return dto;
	}
	
}
