package servlets;

import java.util.*;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class ControlServlet
 */
public class ControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String BUTTON_MENU     = "BUTTON_MENU";
	private static final String BUTTON_CONTACTS = "BUTTON_CONTACTS";
	private static final String BUTTON_LOAD     = "BUTTON_LOAD";	
	private static final String PAGE_MENU    = "PAGE_MENU";
	private static final String PAGE_MAPPING = "PAGE_MAPPING";
	private static final String PAGE_IMPORT  = "PAGE_IMPORT";
	private static final String paramsProperties = "resources/params";
	private static final String pagesProperties = "resources/pages";
	private static final String buttonName = "button";
	
    /**
     * @see HttpServlet#HttpServlet()
     */

	public ControlServlet() {  
    	super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		response.setContentType("text/plain"); 	
		String page = null;		
		try {	
			String valueButton = (String)request.getParameter(buttonName);	
			page = getAddressPage(valueButton, request);		
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);	 
	        dispatcher.forward(request, response);
		}	
	}
		
	/** page selection */
	private String getAddressPage(String name, HttpServletRequest request) {
		String page = null;
		Locale locale = new Locale("ru", "RU");
		ResourceBundle rb = ResourceBundle.getBundle(paramsProperties,locale);
		ResourceBundle br = ResourceBundle.getBundle(pagesProperties,locale);		
		if(name.equals(rb.getString(BUTTON_MENU))) {
			page = br.getString(PAGE_MENU);
		}  
		else if(name.equals(rb.getString(BUTTON_CONTACTS))){	 		
			page = br.getString(PAGE_MAPPING);
		}  
		else if(name.equals(rb.getString(BUTTON_LOAD))){
			page = br.getString(PAGE_IMPORT);
		}	
		return page;
	}
	
}
