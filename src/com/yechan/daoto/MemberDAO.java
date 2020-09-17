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


public class MemberDAO {
	
	private static MemberDAO dao = new MemberDAO();
	
	public static final int MEMBER_JOIN_SUCCESS = 1;
	public static final int MEMBER_JOIN_FAIL = 0;
	public static final int MEMBER_NONEXISTENT = 0;
	public static final int MEMBER_EXISTENT = 1;
	public static final int MEMBER_MODIFY_FAIL = 0;
	public static final int MEMBER_MODIFY_SUCCESS = 1;
	
	private DataSource dataSource;
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	private PreparedStatement prestat;
	
	private MemberDAO()
	{
		
		try
		{
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/orcl");
		
		}catch(Exception e)
		{
			System.out.println(e.toString());
		}
		
		
	}
	
	public int confirmId(String id)
	{
		int duplicationResult = 0;
		
		try
		{
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			String query = "select id from members where id = '" + id + "'";
			resultSet = statement.executeQuery(query);
			
			if(resultSet.next())
			{
				duplicationResult = MEMBER_EXISTENT;
			}
			else
			{
				duplicationResult = MEMBER_NONEXISTENT;
			}			
			
		}catch(Exception e)
		{
			
			System.out.println(e.toString());
		}
		finally
		{
			try {
			if(!connection.isClosed()) connection.close();
			if(!statement.isClosed()) statement.close();
			if(!resultSet.isClosed()) resultSet.close();
			}
			catch(Exception e)
			{
				System.out.println(e.toString());
			}
		}
		
		
		return duplicationResult;
	}
	
	public static MemberDAO getInstance()
	{
		return dao;
	}
	
	public MemberDTO getMembersFromId(String p_id)
	{
		
		MemberDTO dto = null;
		
		try {
			
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			String query = "select * from members where id ='" +p_id+ "'";
			
			resultSet = statement.executeQuery(query);
			
			if(resultSet.next())
			{
				String id = resultSet.getString("id");
				String pw = resultSet.getString("pw");
				String name = resultSet.getString("name");
				String eMail = resultSet.getString("eMail");
				Timestamp rDate = resultSet.getTimestamp("rDate");
				String address = resultSet.getString("address");
				
				dto = new MemberDTO(id,pw,name,eMail,rDate,address);
				
			}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		finally
		{
			try {
				if(!connection.isClosed()) connection.close();
				if(!statement.isClosed()) statement.close();
				if(!resultSet.isClosed()) resultSet.close();
				}
				catch(Exception e)
				{
					System.out.println(e.toString());
				}
		}
		
		
		return dto;
		
		
	}
	
	
	
	public ArrayList<MemberDTO> getMembers()
	{
		ArrayList<MemberDTO> dtos = new ArrayList<MemberDTO>();
		
		
		try {
		
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from members");
		
			while(resultSet.next())
			{
				
				String id = resultSet.getString("id");
				String pw = resultSet.getString("pw");
				String name = resultSet.getString("name");
				String eMail = resultSet.getString("eMail");
				Timestamp rDate = resultSet.getTimestamp("rDate");
				String address = resultSet.getString("address");
				MemberDTO dto = new MemberDTO(id,pw,name,eMail,rDate,address);
				
				dtos.add(dto);
				
			}
						
		}catch(Exception e)	{			
			System.out.println(e.toString());
		}
		finally
		{
			try {
				if(!connection.isClosed()) connection.close();
				if(!statement.isClosed()) statement.close();
				if(!resultSet.isClosed()) resultSet.close();
				}
				catch(Exception e)
				{
					System.out.println(e.toString());
				}
			
		}
		
		return dtos;
		
	}
	
	public int ModifyMembers(MemberDTO dto)
	{
		int modifyResult = MEMBER_MODIFY_FAIL;
		
		try
		{
			connection = dataSource.getConnection();
			String query = "update members set pw = ?, name = ?,eMail = ?, address = ? where id = ?";
			prestat = connection.prepareStatement(query);
			
			prestat.setString(1, dto.getPw());
			prestat.setString(2, dto.getName());
			prestat.setString(3, dto.geteMail());
			prestat.setString(4, dto.getAddress());
			prestat.setString(5, dto.getId());
			
			
			int i = prestat.executeUpdate();
			
			
			if(i>0)
			{
				modifyResult = MEMBER_MODIFY_SUCCESS;
			}
		}catch(Exception e)
		{
			
			System.out.println(e.toString());
		}
		finally
		{
			try {
				
				if(!prestat.isClosed()) prestat.close();
				if(!connection.isClosed()) connection.close();
				
				}
				catch(Exception e)
				{
					System.out.println(e.toString());
				}
			
		}
		
		
		return modifyResult;
		
	}
	
	public int InsertMembers(MemberDTO dto)
	{
		int insertResult = 0;
		
		
		try
		{
			connection = dataSource.getConnection();
			String query = "insert into members values (?,?,?,?,?,?)";
			prestat = connection.prepareStatement(query);
			
			prestat.setString(1, dto.getId());
			prestat.setString(2, dto.getPw());
			prestat.setString(3, dto.getName());
			prestat.setString(4, dto.geteMail());
			prestat.setTimestamp(5, dto.getrDate());
			prestat.setString(6, dto.getAddress());
		
			prestat.executeUpdate();
			
			insertResult = MemberDAO.MEMBER_JOIN_SUCCESS;
			
			
		}catch(Exception e)
		{
			System.out.println(e.toString());
		}
		finally
		{
			try {
				
			if(!prestat.isClosed()) prestat.close();
			if(!connection.isClosed()) connection.close();
			
			}
			catch(Exception e)
			{
				System.out.println(e.toString());
			}
		}
		
		
		return insertResult;
	}
	

}
