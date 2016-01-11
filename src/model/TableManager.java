package model;

import java.util.*;

public class TableManager {
	
	private List<Contact> contacts = new ArrayList<Contact>();
	private List<Contact> pageContacts = new ArrayList<Contact>();
	
	private int countRows = 0;
	private int rowPage = 5;
	private int numPages = 0;
	public int currentPage = 1;
	
	public TableManager() {
		contacts = new ArrayList<Contact>();
		pageContacts = new ArrayList<Contact>();
	}
	
	public void setDBManager(DataBaseManager dbManager){
		contacts = dbManager.getDBList();
		currentPage = 1;
		countRows = contacts.size();
		rowPage = 5;
		calculateNumPages();
		calculateListPage();
	}
	
	/** calculation of the total number of pages*/
	public void calculateNumPages() {
		numPages = countRows/rowPage;	
		if((countRows%rowPage) > 0){
			numPages++;
		}
	}

	/** create a list of records on the current page */
	public void calculateListPage() {
		int start = 1;
		int end = rowPage*currentPage;
		if(currentPage!=1) { 
			start = rowPage*(currentPage-1)+1;
		}
		if(numPages==currentPage) {
			if(countRows<end){
				end=countRows;
			}
		}
		start--;
		pageContacts.clear();
		for(int i=start; i<end; i++){
			pageContacts.add(contacts.get(i));
		}
	}
	
	public void setAllList(List<Contact> list){		
		contacts = list;
		currentPage = 1;
		countRows = contacts.size();
		rowPage = 5;
		calculateNumPages();
		calculateListPage();
	}
	
	public List<Contact> getPageContacts(){
		return pageContacts;
	}
	
	public int getSizePageContacts(){
		return pageContacts.size();
	}

	public List<Contact> getAllList(){
		return contacts;
	}
	
	public int getCurrentPage(){
		return currentPage;
	}

	/** back - page */
	public void handlerBack(){
		if(currentPage!=1){
			currentPage--;
		}
		calculateListPage();
	}
	
	/** forward - page */
	public void handlerForward(){
		if(currentPage<numPages){
			currentPage++;
		}
		calculateListPage();
	}		
}
