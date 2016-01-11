package servlets;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Contact;
import model.TableManager;

/**
 * Servlet implementation class SorterServlet
 */
public class SorterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String PAGE_MAPPING = "PAGE_MAPPING";	
	private static final String ATTR_TABLE_MANAGER = "tableManager";
	
	private static final String buttonName = "button";	    
	private static final String pagesProperties = "resources/pages";
	
	private List<Contact> sortList = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SorterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		HttpSession session = null;
		TableManager tabManager = null;
		try {
			session = request.getSession();	
			tabManager = (TableManager)session.getAttribute(ATTR_TABLE_MANAGER);			
			sortList = (List<Contact>)tabManager.getAllList();			
			String valueButton = (String)request.getParameter(buttonName);	
			sortColumn(valueButton);			
			tabManager.setAllList(sortList);											
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			ResourceBundle rb = ResourceBundle.getBundle(pagesProperties,new Locale("ru", "RU"));	
			RequestDispatcher dispatcher = request.getRequestDispatcher(rb.getString(PAGE_MAPPING));
		    dispatcher.forward(request, response);
		}		
	}
	
	private void sortColumn(String buttName) {
		setSortParam(buttName);				
		Collections.sort(sortList);	
	}
	
	private void setSortParam(String prmName){
		for(Contact cn : sortList){
			cn.setSortParam(prmName);
		}
	}
	
}
