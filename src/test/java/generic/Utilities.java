package generic;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public abstract class Utilities {

	public static String getProperties(String confpath, String appurl)
	{
		String value = "";
		try 
		{
			Properties prop = new Properties();
			prop.load(new FileInputStream(confpath));
			value = prop.getProperty(appurl);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return value;
	}
	
	public static String getExcelData(String path, String sh, int r, int c) 
	{
		String value= "";
		
		try 
		{
			Workbook wb = WorkbookFactory.create(new FileInputStream(path));
			value= wb.getSheet(sh).getRow(r).getCell(c).toString();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return value;
	}

}

