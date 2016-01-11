package servlets;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;

/**
 * Servlet implementation class FilesServlet
 */
public class FilesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String PAGE_IMPORT  = "PAGE_IMPORT";
	private static final String PAGE_ERROR   = "PAGE_ERROR";
	private static final String ATTR_TABLE_MANAGER = "tableManager";
	private static final String ATTR_DB_MANAGER    = "baseManager";	
	private static final String pagesProperties = "resources/pages";
	//private static final String loadfile = "loadfile";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FilesServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		process(request, response);		
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.setContentType("text/plain");
		HttpSession session = null;
		ServletInputStream input = null;
		String page = PAGE_IMPORT;
		try {				
			synchronized (this) {
				session = request.getSession();		
				input = request.getInputStream();	
				try {	
		            FileManager fileManager = new FileManager(input);			
		            DataBaseManager dbManager = new DataBaseManager();			
					TableManager tabManager = new TableManager();
					
					String tableName = fileManager.getFileName();
					if(!tableName.equals("")){ 						
						dbManager.createTable(tableName); 
						List<Contact> contacts = fileManager.getListContacts();
						if(contacts.size()!=0) { 
							dbManager.addList(contacts); 
							tabManager.setDBManager(dbManager);							
						} 					
						//Boolean param = true;
						//session.setAttribute(loadfile, param);
						session.setAttribute(ATTR_TABLE_MANAGER, tabManager);	
						session.setAttribute(ATTR_DB_MANAGER, dbManager);			
					} else { 
						page = PAGE_ERROR; 
					}					
		         } catch (Exception e) {	 
		            e.printStackTrace();
		         }
		     }						
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Locale locale = new Locale("ru", "RU");
			ResourceBundle rb = ResourceBundle.getBundle(pagesProperties,locale);	
			RequestDispatcher dispatcher = request.getRequestDispatcher(rb.getString(page));
			dispatcher.forward(request, response);	      
		}	
	}
}
