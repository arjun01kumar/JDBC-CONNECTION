package com.example.spotfiy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class App 
{
	private static final String Driver="com.mysql.cj.jdbc.Driver"; 
	private static final String username="root";
	private static final String password="root";
//	private static final String url="";
	private static Connection conn;
	private static PreparedStatement pmst;
		
	
    public static void main( String[] args )
    {
        Scanner src = new Scanner(System.in);    
       int i;
        do {
        	display();
        	System.out.println("enter your choice ");
        	
        	i = Integer.parseInt(src.next());
        	
        	switch(i) {
        	case 1:
        		Createdatabase();
        		break;
        	case 2:
        		Dropdatabase();
        		break;
        	case 3:
        		Createtable();
        		break;
        	case 4:
        		Droptable();
        		break;
        	case 5:
        		Adddata();
        		break;
        	case 6:
        		Deletedata();
        		break;
        	case 7:
        		Editdata();
        		break;
        	case 8:
        		Getallrecords();
        		break;
        	case 9:
        		Recordbybranch();
        		break;
        	case 10:
        		System.exit(0);
        		break;
        	default:
        			System.out.println("invalid operation");
        			break;
        	}     	     	
        }while(i>0);
    }   
//    #Create Database
	private static void Createdatabase() {
		String Url = "jdbc:mysql://localhost:3306/";
		Scanner src = new Scanner(System.in);
		try {
			Class.forName(Driver);
			conn = DriverManager.getConnection(Url , username, password);
			System.out.println("Enter Database Name ");
			String sql = "create database " + src.next();
			pmst = conn.prepareStatement(sql);
			int i = pmst.executeUpdate();
			if(i>0) {
				System.out.println("Database is successfully created");
			}
			else {
				System.out.println("Database is not created");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
//	#Delete the database
    private static void Dropdatabase() {
		
    	String Url = "jdbc:mysql://localhost:3306/";
		Scanner src = new Scanner(System.in);
		try {
			Class.forName(Driver);
			conn = DriverManager.getConnection(Url , username, password);
			System.out.println("Enter Database Name ");
			String sql = "Drop Database " + src.next();
			pmst = conn.prepareStatement(sql);
			int i = pmst.executeUpdate();
			if(i==0) {
				System.out.println("Database is  dropped successfully ");
			}
			else {
				System.out.println("Database error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
//    #Create table for the database
    private static void Createtable() {
		
    	Scanner src = new Scanner(System.in);
    	System.out.println("enter the database name ");
    	String Url = "jdbc:mysql://localhost:3306/"+src.next();
		try {
			Class.forName(Driver);
			conn = DriverManager.getConnection(Url , username, password);
			System.out.println("Enter table Name ");
			String sql = "Create table " + src.next()+ "(id int primary key,name varchar(20),branch varchar(20))";
			pmst = conn.prepareStatement(sql);
			int i = pmst.executeUpdate();
			if(i==0) {
				System.out.println("Table Created successfully ");
			}
			else {
				System.out.println("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
//    #Drop teh table from the database
    private static void Droptable() {
		// TODO Auto-generated method stub
    	Scanner src = new Scanner(System.in);
    	System.out.println("enter the database name ");
    	String Url = "jdbc:mysql://localhost:3306/"+src.next();
		try {
			Class.forName(Driver);
			conn = DriverManager.getConnection(Url , username, password);
			System.out.println("Enter table Name ");
			String sql = "Drop table " + src.next();
			pmst = conn.prepareStatement(sql);
			int i = pmst.executeUpdate();
			if(i==0) {
				System.out.println("Table Dropped successfully ");
			}
			else {
				System.out.println("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
//   #add data into the table  
    private static void Adddata() {
		// TODO Auto-generated method stub
    	Scanner src = new Scanner(System.in);
    	System.out.println("enter the database name ");
    	String Url = "jdbc:mysql://localhost:3306/"+src.next();		
		try {
			Class.forName(Driver);
			conn = DriverManager.getConnection(Url , username, password);
			System.out.println("Enter table Name ");
			String sql = "insert into "+src.next()+"(id,name,branch) values (?,?,?)";
			pmst = conn.prepareStatement(sql);
			System.out.println("Enter id");
			pmst.setInt(1, src.nextInt());
			System.out.println("Enter name");
			pmst.setString(2, src.next());
			System.out.println("Enter branch");
			pmst.setString(3, src.next());
			
			int i = pmst.executeUpdate();
			if(i>0) {
				System.out.println(" Data inserted successfully ");
			}
			else {
				System.out.println("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//    #delete the data from the table
    private static void Deletedata() {
		// TODO Auto-generated method stub
    	Scanner src = new Scanner(System.in);
		System.out.println("Enter database name");
		String url = "jdbc:mysql://localhost:3306/"+src.next();
		try {
			Class.forName(Driver);
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Enter table name");
			String sql = "delete from "+src.next()+ " where id = ? ";
			pmst = conn.prepareStatement(sql);
			System.out.println("Enter id ");
			pmst.setInt(1, src.nextInt());
			int i = pmst.executeUpdate();
			if(i>0) {
				System.out.println("Deleted");
			}
			else {
				System.out.println("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//    #Update the data from the table
    private static void Editdata() {
		// TODO Auto-generated method stub
    	Scanner src = new Scanner(System.in);
		System.out.println("Enter database name");
		String url = "jdbc:mysql://localhost:3306/"+src.next();
		try {
			Class.forName(Driver);
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Enter Table name:");
			String sql = "update "+src.next()+" set name = ?,branch = ? where id = ?";
			pmst = conn.prepareStatement(sql);
			System.out.println("Enter name:");
			pmst.setString(1, src.next());
			System.out.println("Enter branch:");
			pmst.setString(2, src.next());
			System.out.println("Enter Id:");
			pmst.setInt(3, src.nextInt());
			int i = pmst.executeUpdate();
			if(i>0) {
				System.out.println("Updated");
			}
			else {
				System.out.println("error");
			}
		} catch (Exception e) {
			e.printStackTrace();  
		}
	}
//    #get all records
    private static void Getallrecords() {
		// TODO Auto-generated method stub
    	Scanner src = new Scanner(System.in);
		System.out.println("Enter database name");
		String url = "jdbc:mysql://localhost:3306/"+src.next();
		try {
			Class.forName(Driver);
			conn = DriverManager.getConnection(url,username,password);
			System.out.println("Enter table name");
			String sql = "select * from "+src.next();
			pmst = conn.prepareStatement(sql);
			ResultSet rs = pmst.executeQuery();
			while (rs.next()) {
			System.out.println("id"+ rs.getInt("id")+ 
					"name"+rs.getString("name")+
					"branch"+rs.getString("branch"));
			}      
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//    #get records by branch
    private static void Recordbybranch() {
		// TODO Auto-generated method stub
    	Scanner src = new Scanner(System.in);
		System.out.println("Enter database name");
		String url = "jdbc:mysql://localhost:3306/"+src.next();
		try {
			Class.forName(Driver);
			conn = DriverManager.getConnection(url,username,password);
			System.out.println("Enter table name");
			String sql = "select * from "+ src.next()+ " where branch = ?";
			pmst = conn.prepareStatement(sql);
			System.out.println("Enter branch ");
			pmst.setString(1, src.toString());
			ResultSet rs = pmst.executeQuery();
			while (rs.next()) {
			System.out.println("id"+ rs.getInt("id")+ 
					"name"+rs.getString("name")+
					"branch"+rs.getString("branch"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static  void display() {
    	System.out.println("****************");
    	System.out.println("1.Create Database");
    	System.out.println("2.Drop Databse");
    	System.out.println("3.create table");
    	System.out.println("4.drop table");
    	System.out.println("5.add data");
    	System.out.println("6.delete data");
    	System.out.println("7.edit data");
    	System.out.println("8.get all records");
    	System.out.println("9.record by branch");
    	System.out.println("10.exit");
    	System.out.println("****************");	
	}
}
