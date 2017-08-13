package finalProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ProtocolException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class Currencies 
{

	private static ArrayList<Currency> currencies = new ArrayList<Currency>();
	private static int num_of_cur;
	private String last_update;

	/**
	 * @param currencies
	 * @param last_update
	 */
	public Currencies() 
	{
		try
		{
			/**
			 * @parse data from xml
			 */
			DocumentBuilderFactory docBuilderFactory= DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder= docBuilderFactory.newDocumentBuilder();
			Document doc;
			try
			{
				/**
				 * @take xml from bank israel
				 */
					URL url= new URL("http://www.boi.org.il/currency.xml");
					InputStream stream= url.openStream();
					doc= docBuilder.parse(stream);
					doc.getDocumentElement().normalize();
					System.out.println("parse data from internet");
					NodeList update = doc.getElementsByTagName("LAST_UPDATE");
					setLast_update(update.item(0).getChildNodes().item(0).getTextContent());
					updateRateCurrencyFile(stream);
			}
			catch (ProtocolException e)	
			{
				/**
				 * there is no conectivity to internet
				 * @take xml from file of project
				 */
				System.out.println("there is no connectivity");				
				File f  = new File("currency.xml");
				doc =docBuilder.parse(f);
				doc.getDocumentElement().normalize();
				System.out.println("parse data from file currency.xml");
				NodeList update = doc.getElementsByTagName("LAST_UPDATE");
				setLast_update(update.item(0).getChildNodes().item(0).getTextContent());
			}
			catch (FileNotFoundException e)
			{
				/**
				 * the page was not found
				 * @take xml from file of project
				 */
				System.out.println("page was not found");
				File f  = new File("currency.xml");
				doc =docBuilder.parse(f);
				doc.getDocumentElement().normalize();
				System.out.println("parse data from file currency.xml");
				NodeList update = doc.getElementsByTagName("LAST_UPDATE");
				setLast_update(update.item(0).getChildNodes().item(0).getTextContent());
			}
			NodeList cur_list= doc.getElementsByTagName("CURRENCY");
			setNum_of_cur(cur_list.getLength());
			for(int i= 0;i < num_of_cur;i++)	{
				String[] some = new String[6];
				for(int j= 1;j < 12;j+=2)	{
					some[j/2] = cur_list.item(i).getChildNodes().item(j).getTextContent();
				}
				/**
				 * @constructor of all currencies
				 */
				currencies.add(new Currency(some[0], some[1], some[2], some[3], some[4], some[5]));
			}
			System.out.println("currencies was created");
		}
		
		catch (ParserConfigurationException e)
		{
			e.printStackTrace();
		}
		catch (SAXException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (Throwable t)
		{
			t.printStackTrace();
		}
	}

	@Override
	public String toString() 
	{
		return "Currencies [currencies=" + currencies.toString() + ", last_update=" + last_update + "]";
	}
	/**
	 * @return currency of currencies[i]
	 */
	public static Currency getCurrency(int i)	
	{
		return currencies.get(i);
	}
	/**
	 * @return the last_update
	 */
	public String getLast_update() 
	{
		return last_update;
	}
	/**
	 * @param last_update the last_update to set
	 */
	public void setLast_update(String last_update) 
	{
		this.last_update = last_update;
		System.out.println("last_update set on "+this.getLast_update());
	}

	/**
	 * @return the Num_of_cur
	 */

	public static int getNum_of_cur()
	{
		return num_of_cur;
	}
	/**
	 * @param Num_of_cur the Num_of_cur to set
	 */
	public static void setNum_of_cur(int num_of_cur)
	{
		Currencies.num_of_cur = num_of_cur;
		System.out.println("There is "+num_of_cur+" currencies in the Currencies source file");
	}
	public void updateRateCurrencyFile(InputStream is) 
	{
		// TODO Auto-generated method stub
		/**
		 * @update file if last_update
		 *  is not up to date
		 */
		try
		{
			/**
			 * @check last_update of file
			 */
			DocumentBuilderFactory docBuilderFactory= DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder= docBuilderFactory.newDocumentBuilder();
			PrintWriter pw = null;
			File f  = new File("currency.xml");
			Document fdoc =docBuilder.parse(f);
			fdoc.getDocumentElement().normalize();
			NodeList update = fdoc.getElementsByTagName("LAST_UPDATE");
			/**
			 * @create string with date of update file
			 */
			String fileUpdate = new String(update.item(0).getChildNodes().item(0).getTextContent());
			/**
			 * @check if last_update == date of update file
			 */
			if(fileUpdate.equals(last_update))
			{
				System.out.println("last_update equals to update file");
				System.out.println("there is no need to update file");
				return;
			}
			else
			{
				System.out.println("last_update not equals to update file");
				try
				{
					pw = new PrintWriter(f);
					URL ob = new URL("http://www.boi.org.il/currency.xml");
					is=ob.openStream();
					int temp = is.read();
					while(temp!=-1)
					{
						pw.print((char)temp);
						temp=is.read();
					}
					System.out.println("file was updated");
				}
				catch (ProtocolException e)	
				{
					/**
					 * @there is no internet
					 */
					System.out.println("there is no connectivity");				
				}
				catch (FileNotFoundException e)
				{
					/**
					 * @page was not found
					 */
					System.out.println("page not found");
				}
				finally
				{
					if(is != null)
					{
						is.close();
						System.out.println("input stream of file was closed");
					}
					if(pw != null)
					{
						pw.close();
						System.out.println("print writer of file was closed");
					}
				}
			}
		}

		catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	/**
	 * @return amount*currncy_from = res*currency_to
	 */
	public static double convertCurrencies(Currency from, double amount, Currency to)
	{
		double result = (from.getRate()*amount/from.getUnit())/(to.getRate()/to.getUnit());
		System.out.println("convert: "+amount+" * "+from.getName()+" = "+result+" * "+to.getName());
		return result;
	}
}
