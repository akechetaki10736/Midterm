package main;

import java.sql.Date;

public class Midterm2Bean {
	
	private String uno;
	private String cname;
	private String local;
	private String principal;
	private String capital;
	private Date setdate;
	
	public Midterm2Bean() {
		
	}
	
	public Midterm2Bean(String uno, String cname, String addr, String principal, String capital, Date setdate) {
		super();
		this.uno = uno;
		this.cname = cname;
		this.local = addr;
		this.principal = principal;
		this.capital = capital;
		this.setdate = setdate;
	}
	public String getUno() {
		return uno;
	}
	public void setUno(String uno) {
		this.uno = uno;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	public Date getSetdate() {
		return setdate;
	}
	public void setSetdate(Date setdate) {
		this.setdate = setdate;
	}
	

}//end of public class MidtermBean