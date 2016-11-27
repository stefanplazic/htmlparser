package stefi.dom;

import java.io.IOException;

import stefi.dom.model.LinkFinder;

public class App 
{
    public static void main( String[] args )
    {
    	final String URL="http://www.blic.rs/vesti";
    	
    	LinkFinder finder = new LinkFinder();
    	finder.setUrl(URL);
    	finder.setDestinationLocation("");
    	
    	try {
			finder.downloadFiles();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
