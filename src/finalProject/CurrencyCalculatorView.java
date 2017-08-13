package finalProject;

//package com.shmueli.roi;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class CurrencyCalculatorView extends JFrame 
{
	private JPanel cur_from, amount, cur_to, toconvert, result, convrt_from;
	private JButton convert_butt, reset_butt;
	private JTextArea choosefrom, chooseamount, chooseto, textresult;
    private JTextField writeamnt, txtrslt;
    private JComboBox listfrom, listto;
 
	public void clear()	
	{
		/**
		 * @delete text fields of
		 *  amount and result
		 */
		writeamnt.setText("");
		txtrslt.setText("");
		textresult.setText("");
		System.out.println("text fields were cleaned");
	}

	public void makeConvertGui()
	/**
	 * @create JFrame with converter
	 */
	{
		setTitle("Currency Converter");
		convrt_from = new JPanel();
		cur_from = new JPanel();
		amount = new JPanel();
		cur_to = new JPanel();
		toconvert = new JPanel();
		result = new JPanel();
		convert_butt = new JButton("CONVERT");
		reset_butt = new JButton("RESET");
		choosefrom = new JTextArea("from currency: ");
		chooseamount = new JTextArea("amount: ");
		chooseto = new JTextArea("to cuurency: ");
		textresult = new JTextArea();
		writeamnt = new JTextField(10);
		txtrslt = new JTextField(15);
		reset_butt.addActionListener(new ActionListener() 
		{
			/**
			 * @set the reset_but actionListener
			 */
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				System.out.println("RESET was pressed");
				clear();				//delete text fields of amount and result
				e = null;				//ActionEvent = null
			}
		});
	    convert_butt.addActionListener(new ActionListener() 
	    {
	    	/**
			 * @set the convert_but actionListener
			 */
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				System.out.println("CONVERT was pressed");
				String amount_txt = writeamnt.getText();
				char c = 'A';							//c is some char
				int j = 0;								//j counts the char '.'
				Currency fromc = null, toc = null;		//new temp currencies for convert function
				for (int i = 0; i < amount_txt.length(); i++) 
				{
					c = amount_txt.charAt(i);
					/**
					 * @check all string if it is number
					 */
					if (c < '0' || c > '9')
					{
						if (c == '.')
							/**
							 * @if char isnt number
							 * check if it is .
							 */
						{
							if (j > 0)						//'.' typed more than once
							{
								/**
								 * @if char is .
								 * check if it typed more than once
								 */
								System.out.println(". typed more than once");
								JOptionPane.showMessageDialog(null, "you can write '.' only once..", "Dot Error", JOptionPane.WARNING_MESSAGE);
								clear();					//delete text fields of amount and result
								e = null;					//ActionEvent = null
								break;
							}
							j++;							//increase number of '.'
							continue;						//move to next iteration
						}
						System.out.println("typed char whos not number");
						JOptionPane.showMessageDialog(null, "you need write only numbers..", "Letter Error", JOptionPane.WARNING_MESSAGE);
						clear();					//delete text fields of amount and result
						e = null;					//ActionEvent = null
						break;
					}
				}
				double dbl_amount = 0;
				dbl_amount = Double.parseDouble(amount_txt);
				for(int i=0; i<Currencies.getNum_of_cur(); i++)	
				{
					if (listfrom.getSelectedIndex()==i)		//currencies[i] choosen
					{
						fromc = new Currency(mainControler.currens.getCurrency(i));
						System.out.println(fromc.getName()+" choosen to convert from");
						break;
					}
				}
				for(int i=0; i<Currencies.getNum_of_cur(); i++)	
				{
					if (listto.getSelectedIndex()==i)	//currencies[i] choosen
					{
						toc = new Currency(mainControler.currens.getCurrency(i));
						System.out.println(toc.getName()+" choosen to convert to");
						break;
					}
				}
				double dresult = Currencies.convertCurrencies(fromc, dbl_amount, toc);
				/**
				 * @change double to 2 numbers after dot
				 */
				double ires = (int)(dresult*100);			//change double to 2 numbers after dot
				dresult = ires/100;
				txtrslt.setText(Double.toString(dresult));
				System.out.println(dbl_amount+" "+fromc.getCountry()+"-"+fromc.getName()+" = "+dresult+" "+toc.getCountry()+"-"+toc.getName());
				textresult.setText(dbl_amount+" "+fromc.getCountry()+"-"+fromc.getName()+" = "+dresult+" "+toc.getCountry()+"-"+toc.getName());
		}
	    });
		String[] nameOfCurr = new String[14];			//list of strings for JComboBox
		for(int i=0; i<Currencies.getNum_of_cur(); i++)	
		{
			/**
			 * @set two currency list
			 * for the two JComboBox
			 */
			nameOfCurr[i]=new String(Currencies.getCurrency(i).getCountry()+" - "+Currencies.getCurrency(i).getName());
		}
		listfrom=new JComboBox(nameOfCurr);
		listfrom.setSelectedIndex(0); 
		listto=new JComboBox(nameOfCurr);
		listto.setSelectedIndex(1);
		    
		cur_from.add(choosefrom);
		cur_from.add(listfrom);
		amount.add(chooseamount);
		amount.add(writeamnt);
		cur_to.add(chooseto);
		cur_to.add(listto);
		toconvert.add(convert_butt);
		result.setLayout(new BorderLayout());
		result.add(BorderLayout.NORTH, txtrslt);
		result.add(BorderLayout.CENTER, textresult);
		result.add(BorderLayout.SOUTH, reset_butt);
		  
		convrt_from.setLayout(new BorderLayout());
		convrt_from.add(BorderLayout.NORTH, cur_from);
		convrt_from.add(BorderLayout.CENTER, amount);
		convrt_from.add(BorderLayout.SOUTH, cur_to);
    
		setLayout(new BorderLayout());
		add(BorderLayout.NORTH, convrt_from);
		add(BorderLayout.CENTER, toconvert);
		add(BorderLayout.SOUTH, result);
		setSize(350, 250);
		setVisible(true);	
		System.out.println("convertGui was created");

	}
}
