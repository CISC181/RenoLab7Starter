package pkgUtil;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Crunchify.com
 * 
 */
 
public class CrunchifyReadConfigMain {
 
	public static void main(String[] args) throws IOException {
		
		CrunchifyGetPropertyValues properties = new CrunchifyGetPropertyValues();		
		Properties props = properties.getPropertyFile();
		
		String company1 = props.getProperty("CardImgPath");
		System.out.println(company1);
		
		//properties.getPropValues();
	}
}