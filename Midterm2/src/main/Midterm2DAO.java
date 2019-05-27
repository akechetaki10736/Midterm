package main;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Midterm2DAO implements Midterm2IFDAO {

	private static final String insertStmt = "insert into newcompany values (?,?,?,?,?,?)"; 
	private static final String qryStmt = "select * from newcompany where uno = ?";
	private static final String upadate = "update newcompany set cname = ?, local = ?, principal = ?, capital = ?, setdate = ? where uno = ?";
	private static final String delete = "delete from newcompany where uno = ?";
	private static final String unoQuery = "select count(uno) from newcompany where uno = ?";
	

	Connection conn = null;

	@Override
	public void getConnection() throws SQLException, ClassNotFoundException, NamingException {
		Context context = new InitialContext();
		DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/midterm");
		conn = ds.getConnection();
	} //end of getConnection



	public void insert(Midterm2Bean m2b) throws SQLException {
		

		PreparedStatement pstmt = conn.prepareStatement(insertStmt);
		pstmt.setInt(1, Integer.parseInt(m2b.getUno()));
		pstmt.setString(2, m2b.getCname());
		pstmt.setString(3, m2b.getLocal());
		pstmt.setString(4, m2b.getPrincipal());
		pstmt.setInt(5, Integer.parseInt(m2b.getCapital()));
		pstmt.setDate(6, m2b.getSetdate());
		pstmt.executeUpdate();

		
	} // end of insert

	

	@Override
	public void closeConn() throws SQLException {
		conn.close();

	} // end of closeConn

	@Override
	public Midterm2Bean search(int uno) throws SQLException {
		PreparedStatement pstmt =  conn.prepareStatement(qryStmt);
		pstmt.setInt(1, uno);
		ResultSet rs = pstmt.executeQuery();
		Midterm2Bean m2b = new Midterm2Bean();
		
		if(rs.next()) {
			m2b.setUno(rs.getString(1));
			m2b.setCname(rs.getNString(2));
			m2b.setLocal(rs.getString(3));
			m2b.setPrincipal(rs.getString(4));
			m2b.setCapital(rs.getString(5));
			m2b.setSetdate(rs.getDate(6));
		}
		return m2b;
		
	} // end of search

	@Override
	public void delete(int uno) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement(delete);
		pstmt.setInt(1, uno);
		int count = pstmt.executeUpdate();
		if(count == 1)
			System.out.println("Delete 1 data.");
		else
			System.out.println("Fail.");
	} //end of delete

	@Override
	public void update(Midterm2Bean m2b) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement(upadate);
		pstmt.setString(1, m2b.getCname());
		pstmt.setString(2, m2b.getLocal());
		pstmt.setString(3, m2b.getPrincipal());
		pstmt.setString(4, m2b.getCapital());
		pstmt.setDate(5, m2b.getSetdate());
		pstmt.setString(6, m2b.getUno());	
		
		int count = pstmt.executeUpdate();
		if(count == 1)
			System.out.println("update 1 data.");
		else
			System.out.println("Fail.");
	} // end of update
	
	public int unoSearch(String uno) throws SQLException{
		PreparedStatement pstmt = conn.prepareStatement(unoQuery);
		pstmt.setInt(1, Integer.parseInt(uno));
		ResultSet rs = pstmt.executeQuery();
		int count = 0;
		if(rs.next()) {
			count = rs.getInt(1);
		}
		return count;
		
	} //end of unoSearch


 
}// end of public
