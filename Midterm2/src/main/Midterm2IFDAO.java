package main;


import java.sql.SQLException;

import javax.naming.NamingException;

public interface Midterm2IFDAO {
		
	
	public void getConnection() throws SQLException, ClassNotFoundException, NamingException; 
	public void insert(Midterm2Bean m2b) throws SQLException;
	public Midterm2Bean search(int uno) throws SQLException;
	public void delete(int uno) throws  SQLException;
	public void update(Midterm2Bean m2b) throws SQLException;
	public void closeConn () throws SQLException; 
	

}//end of public

