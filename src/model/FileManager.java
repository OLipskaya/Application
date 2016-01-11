package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
	
	private List<Contact> listContacts;
	private String name = "";
	
	private static final String sign_one = ",";
	private static final String sign_two = "\n";
	private static final String delimiter = "------";
	private static final String delimiter_one = "Content-Type: text/csv";
	private static final String delimiter_two = "filename=";

	public FileManager(InputStream stream) {
		listContacts = new ArrayList<Contact>();
		readStream(stream);
	}
	
	private String readStreamRequest(InputStream stream) {
		StringBuffer data = null;
		try {
			BufferedInputStream bf = new BufferedInputStream(stream); 
			data = new StringBuffer(); 
			int bit; 
			while((bit = bf.read()) != -1) { 
				data.append((char)bit); 
			} 		
			return data.toString();
		} catch(IOException e){
			e.printStackTrace();
		}	
		return "";
	}

	private void parseText(String text) {
		String[] rows = text.split(sign_two);
		int count=0;
		for(String row : rows){
			String[] params = row.split(sign_one);
			if(params.length == 5) {
				Contact contact = new Contact();
				contact.setName(params[0]);
				contact.setSurName(params[1]);
				contact.setLogin(params[2]);
				contact.setMail(params[3]);
				contact.setPhoneNumber(params[4]);
				contact.setId(++count);
				listContacts.add(contact);
			} 
		}
	}

	private void readContentFile (String text) {
		String[] parts = text.split(delimiter);		
		for(String part : parts) {
			int index = part.indexOf(delimiter_one);			
			if(index != -1) {
				int start = index + delimiter_one.length();
				String fileContent = part.substring(start, part.length());				
				String infoContent = part.substring(0, start);				
				readFileName(infoContent);	
				parseText(fileContent);
			}
		}		
	}
	
	private void readFileName(String infotext) {
		String[] info = infotext.split("\n");
		for(String line : info) {		
			int j = line.indexOf(delimiter_two);
			if(j != -1) {
				String fileName = line.substring(j + delimiter_two.length()+1, line.length()-2);	
				name = fileName.substring(0, fileName.indexOf("."));
			}
		}
	}
	
	/** parse CSV-file */
	private void readStream(InputStream stream){
		String textStream = readStreamRequest(stream);
		if(!textStream.equals("")) {
			readContentFile(textStream);
		}
	}
		
	public List<Contact> getListContacts() {
		return listContacts;
	}
	
	public String getFileName() {
		return name;
	}

}
