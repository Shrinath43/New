package com.model;
import java.util.*;
import java.sql.*;

public class MedDao {

	public static Connection getConnection(){
		Connection con=null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tigar");
		}catch(Exception e){System.out.println(e);}
		return con;
	}
	public static int save(Med e){
		int status=0;
		try{
			Connection con=MedDao.getConnection();
			PreparedStatement ps=con.prepareStatement("insert into user1(name,company,mfddate,expDate) values (?,?,?,?)");
			ps.setString(1,e.getName());
			ps.setString(2,e.getCompany());
			ps.setString(3,e.getMfddate());
			ps.setString(4,e.getExpDate());
			
			status=ps.executeUpdate();
			
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return status;
	}
	public static int update(Med e){
		int status=0;
		try{
			Connection con=MedDao.getConnection();
			PreparedStatement ps=con.prepareStatement("update user1 set name=?,company=?,mfddate=?,expDate=? where id=?");
			ps.setString(1,e.getName());
			ps.setString(2,e.getCompany());
			ps.setString(3,e.getMfddate());
			ps.setString(4,e.getExpDate());
			ps.setInt(5,e.getId());
			
			status=ps.executeUpdate();
			
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return status;
	}
	public static int delete(int id){
		int status=0;
		try{
			Connection con=MedDao.getConnection();
			PreparedStatement ps=con.prepareStatement("delete from user1 where id=?");
			ps.setInt(1,id);
			status=ps.executeUpdate();
			
			con.close();
		}catch(Exception e){e.printStackTrace();}
		
		return status;
	}
	public static Med getEmployeeById(int id){
		Med e=new Med();
		
		try{
			Connection con=MedDao.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from user1 where id=?");
			ps.setInt(1,id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setCompany(rs.getString(3));
				e.setMfddate(rs.getString(4));
				e.setExpDate(rs.getString(5));
			}
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return e;
	}
	public static List<Med> getAllMedicines(){
		List<Med> list=new ArrayList<Med>();
		
		try{
			Connection con=MedDao.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from user1");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Med e=new Med();
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setCompany(rs.getString(3));
				e.setMfddate(rs.getString(4));
				e.setExpDate(rs.getString(5));
				list.add(e);
			}
			con.close();
		}catch(Exception e){e.printStackTrace();}
		
		return list;
	}
}
