package stefi.dom.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

import javax.print.attribute.standard.Destination;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class LinkFinder {

	private String url;
	private String destinationLocation;
	
	private static final int BUFFER_SIZE = 4096;
	
	public LinkFinder() {}
	
	/**
	 * performs file download to the location specifed from destinationLocation field
	 * it throws IOException
	 */
	public void downloadFiles() throws IOException{
		//Document document = Jsoup.connect(this.url).get();
		Document document = Jsoup.parse(new File("C:\\Users\\stefan\\Desktop\\internet\\DOM\\Bap1 human missense - SNP - NCBI5.html"), "UTF-8");
		Elements links = document.select("a");
		//cycle through links in the page
		int counter =1;
		for (Element link : links) {
			if(link.text().startsWith("rs")){
			  //print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
				System.out.println("{"+counter+"} Downloading: "+link.attr("abs:href"));
			  saveFiles(link.attr("abs:href"), link.text() + ".html");
			  counter++;
			}
		}
		System.out.println("Completed!!!!!");
	}
	
	private void saveFiles(String urlPath,String fileName) throws IOException{
		String DESTINATION ="C:\\Users\\stefan\\Desktop\\internet\\DOM\\DATA";
		
		URL url = new URL(urlPath);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		int responseCode = connection.getResponseCode();
		
		if (responseCode == HttpURLConnection.HTTP_OK) {
			//input stream 
			InputStream inputStream = connection.getInputStream();
			FileOutputStream fileOutputStream = new FileOutputStream(DESTINATION+File.separator+fileName);
			
			 int bytesRead = -1;
	         byte[] buffer = new byte[BUFFER_SIZE];
	         while ((bytesRead = inputStream.read(buffer)) != -1) {
	        	 fileOutputStream.write(buffer, 0, bytesRead);
	            }
	 
	         	fileOutputStream.close();
	            inputStream.close();
	            
		}
		}
	
	
	
	private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }
	
	/**
	 * 
	 * @param s string to be trimmed
	 * @param width the maximum size of string
	 * @return returns the same string if the size if smaller or equal to width, otherwise return substring from 0 to width position
	 */
	private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDestinationLocation() {
		return destinationLocation;
	}
	public void setDestinationLocation(String destinationLocation) {
		this.destinationLocation = destinationLocation;
	}
	
	
}
