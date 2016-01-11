package servlets;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.TableManager;

/**
 * Servlet implementation class PagesServlet
 */
public class PagesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final String BUTTON_BACK    = "BUTTON_BACK";
	private static final String BUTTON_FORWARD = "BUTTON_FORWARD";
	private static final String PAGE_MAPPING   = "PAGE_MAPPING";
	private static final String ATTR_TABLE_MANAGER = "tableManager";
	private static final String paramsProperties = "resources/params";
	private static final String pagesProperties = "resources/pages";	
	private static final String buttonName = "button";	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PagesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		process(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request,response);
	}
		
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		HttpSession session = null;
		TableManager tabManager = null;
		String page = null;
		try {
			session = request.getSession();					
			tabManager = (TableManager)session.getAttribute(ATTR_TABLE_MANAGER);			
			String valueButton = getValueInput(request);
			page = getAddrPage(valueButton,tabManager);			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);	 
	        dispatcher.forward(request, response);
		}	
	}
	
	private String getAddrPage(String name,TableManager tabManager) {
		String page = null;
		Locale locale = new Locale("ru", "RU");
		ResourceBundle rb = ResourceBundle.getBundle(paramsProperties,locale);
		ResourceBundle br = ResourceBundle.getBundle(pagesProperties,locale);
		
		if(name.equals(rb.getString(BUTTON_BACK))){	
			tabManager.handlerBack();
			page = br.getString(PAGE_MAPPING);
		}  
		else if(name.equals(rb.getString(BUTTON_FORWARD))){	
			tabManager.handlerForward();
			page = br.getString(PAGE_MAPPING);
		}  
		return page;
	}
	
	private String getValueInput(HttpServletRequest request){
		return (String)request.getParameter(buttonName);
	}
}
