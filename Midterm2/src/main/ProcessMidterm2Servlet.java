package main;

import java.io.IOException;
import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/main/ProcessMidterm2Servlet")
public class ProcessMidterm2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		// 存放錯誤訊息的Collection
		Map<String, String> errorMessage = new HashMap<>();
		request.setAttribute("ErrorMsg", errorMessage);
		// 設定輸入資料的編碼
		request.setCharacterEncoding("UTF-8");
		// 讀取使用者輸入的資料
		String uno = request.getParameter("uno");
		String cname = request.getParameter("cname");
		String addr = request.getParameter("address");
		String principal = request.getParameter("principal");
		String capital = request.getParameter("capital");
		String setdate = request.getParameter("setdate");

		if (request.getParameter("s1").equals("1")) {
			// 判斷使用者輸入的資料
			// 統一編號
			if (uno == null || uno.trim().length() == 0) {
				errorMessage.put("uno", "");
			}
			if (uno.trim().length() < 8 || uno.trim().length() > 8) {
				errorMessage.put("uno", "");
			}
			// 公司名稱
			if (cname == null || cname.trim().length() == 0) {
				errorMessage.put("cname", "新增資料此欄不得空白");
			}
			// 地址
			if (addr == null || addr.trim().length() == 0) {
				errorMessage.put("addr", "新增資料此欄不得空白");
			}
			// 負責人
			if (principal == null || principal.trim().length() == 0) {
				errorMessage.put("principal", "新增資料此欄不得空白");
			}
			// 資本額
			if (capital == null || capital.trim().length() == 0) {
				errorMessage.put("capital", "新增資料此欄不得空白");
			} else if (true) {
				try {
					Integer.parseInt(capital);
				} catch (NumberFormatException e) {
					errorMessage.put("capital", "資本額欄位須為合法數值");
					RequestDispatcher rd = request.getRequestDispatcher("/main/Form.jsp");
					rd.forward(request, response);
					return;
				}
			}
			// 設立日期
			java.sql.Date date = null;
			if (setdate != null && setdate.trim().length() > 0) {
				try {
					date = java.sql.Date.valueOf(setdate);
				} catch (IllegalArgumentException e) {
					errorMessage.put("setdate", "不合法日期");
					RequestDispatcher rd = request.getRequestDispatcher("/main/Form.jsp");
					rd.forward(request, response);
					return;
				} 
			} else {
				errorMessage.put("setdate", "新增資料此欄不得空白");
			}

			// 如果有錯誤，呼叫view元件，送回錯誤訊息
			if (!errorMessage.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("/main/Form.jsp");
				rd.forward(request, response);
				return;
			}

			// 呼叫Bean封裝資料
			Midterm2Bean m2b = new Midterm2Bean(uno, cname, addr, principal, capital, date);
			Midterm2DAO m2d = new Midterm2DAO();

			try {
				m2d.getConnection();
				m2d.insert(m2b);
				session.setAttribute("Midterm2Bean", m2b);
				RequestDispatcher rd = request.getRequestDispatcher("/main/Success.jsp");
				rd.forward(request, response);
				return;
			} catch (SQLException e) {
				if (e.getMessage().indexOf("重複的索引鍵") != -1 || e.getMessage().indexOf("Duplicate entry") != -1) {
					errorMessage.put("uno", "統一編號重複，請重新輸入");
				} else {
					errorMessage.put("exception", "資料庫存取錯誤:" + e.getMessage());
				}
				RequestDispatcher rd = request.getRequestDispatcher("/main/Form.jsp");
				rd.forward(request, response);
				return;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}catch (NumberFormatException e) {
				errorMessage.put("exception", "必須輸入數字");
			}finally {
				try {
					if (m2d.conn != null) {
						m2d.closeConn();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} // end of 新增

		if (request.getParameter("s1").equals("2")) {
			// 判斷使用者輸入的資料
			// 統一編號
			if (uno == null || uno.trim().length() == 0) {
				errorMessage.put("uno", "");
			}
			if (uno.trim().length() < 8 || uno.trim().length() > 8) {
				errorMessage.put("uno", "統一編號長度須為8");
			}
			if (!errorMessage.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("/main/Form.jsp");
				rd.forward(request, response);
				return;
			}

			Midterm2Bean m2b = new Midterm2Bean();
			Midterm2DAO m2d = new Midterm2DAO();
			try {
				m2d.getConnection();
				m2b = m2d.search(Integer.parseInt(uno));
				session.setAttribute("Midterm2Bean", m2b);
				RequestDispatcher rd = request.getRequestDispatcher("/main/Success.jsp");
				rd.forward(request, response);
				return;
			} catch (NumberFormatException e) {
				errorMessage.put("exception", "必須輸入數字");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			} finally {
				try {
					if (m2d.conn != null) {
						m2d.closeConn();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		} // end of 查詢

		if (request.getParameter("s1").equals("3")) {
			if (uno == null || uno.trim().length() == 0) {
				errorMessage.put("uno", "");
			}
			if (uno.trim().length() < 8 || uno.trim().length() > 8) {
				errorMessage.put("uno", "統一編號長度須為8");
			}
			if (!errorMessage.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("/main/Form.jsp");
				rd.forward(request, response);
				return;
			}
			Midterm2Bean m2b = new Midterm2Bean();
			Midterm2DAO m2d = new Midterm2DAO();
			try {
				m2d.getConnection();
				m2b = m2d.search(Integer.parseInt(uno));
			} catch (NumberFormatException e) {
				errorMessage.put("exception", "必須輸入數字");
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			} catch (NamingException e1) {
				e1.printStackTrace();
			}		
			if (cname.trim().length() != 0 && cname != null) {
				m2b.setCname(cname);
			}
			if (addr.trim().length() != 0 && addr != null ) {
				m2b.setLocal(addr);
			}
			if (principal.trim().length() != 0 && principal != null) {
				m2b.setPrincipal(principal);
			}
			if(capital.trim().length() != 0 & capital != null) {
				m2b.setCapital(capital);
			}
			if(setdate != null && setdate.trim().length() > 0) {
				java.sql.Date date = null;
				try {
					date = java.sql.Date.valueOf(setdate);					
				} catch (IllegalArgumentException e) {
					errorMessage.put("setdate", "不合法日期");
					RequestDispatcher rd = request.getRequestDispatcher("/main/Form.jsp");
					rd.forward(request, response);
					return;
				}
				m2b.setSetdate(date);
			}
			
				try {
					m2d.update(m2b);
					m2d.search(Integer.parseInt(uno));
					session.setAttribute("Midterm2Bean", m2b);
					RequestDispatcher rd = request.getRequestDispatcher("/main/Success.jsp");
					rd.forward(request, response);
					return;
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally{
					try {
						if (m2d.conn != null) {
							m2d.closeConn();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			
		} // end of 修改

		if (request.getParameter("s1").equals("4")) {
			// 判斷使用者輸入的資料
			// 統一編號
			if (uno == null || uno.trim().length() == 0) {
				errorMessage.put("uno", "");
			}
			if (uno.trim().length() < 8 || uno.trim().length() > 8) {
				errorMessage.put("uno", "");
			}
			if (!errorMessage.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("/main/Form.jsp");
				rd.forward(request, response);
				return;
			}

			Midterm2Bean m2b = new Midterm2Bean();
			Midterm2DAO m2d = new Midterm2DAO();
			try {
				m2d.getConnection();
				m2b = m2d.search(Integer.parseInt(uno));
				m2d.delete(Integer.parseInt(uno));
				session.setAttribute("Midterm2Bean", m2b);
				RequestDispatcher rd = request.getRequestDispatcher("/main/Success.jsp");
				rd.forward(request, response);
				return;
			} catch (NumberFormatException e) {
				errorMessage.put("exception", "必須輸入數字");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			} finally {
				try {
					if (m2d.conn != null) {
						m2d.closeConn();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		} // end of 刪除

	} // end of doPost
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean passed = unoExist(request.getParameter("uno"));
		response.getWriter().print(passed);
	} // end of doGet
	
	private boolean unoExist(String uno) {
		boolean isExist = false;
		if(uno != null && uno.length() != 0) {
			Midterm2DAO m2d = new Midterm2DAO();
			int count = 0;	
			try {
				m2d.getConnection();
				count = m2d.unoSearch(uno);
				if(count == 1) {
					isExist = true;
					return isExist;
				} else {
					isExist = false;
					return isExist;
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			} finally {			
				try {
					if (m2d.conn != null) {
						m2d.closeConn();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}			
		}
		return isExist;		
	} // end of UnoExist

	
}// end of ProcessDataServlet
