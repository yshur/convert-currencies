package finalProject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class currencyTable extends JFrame 
{
	public JTable table;
	public JButton button;
	public String[][] data;
	public String[] columnNames = {"name", "unit", "currencycode","country", "rate", "change"};	//names of the columns
	/**
	 * @param table
	 * @param button
	 * @param data
	 * @param columnNames
	 */
	public currencyTable (Currencies currens)
	{
		super();
		setTitle("Currencies Rate Table");														//title of table frame
		setSize(550, 350);																		//size of table frame
		setLayout(new FlowLayout());															//default layout
		JTextArea txtUpdate = new JTextArea(" LAST UPDATE: "+currens.getLast_update()+" ");
		txtUpdate.setSize(300, 200);															//size of lust_update textArea
		data = new String[Currencies.getNum_of_cur()][6];																//content of the table
		for(int i=0; i<Currencies.getNum_of_cur(); i++)		
		{
			data[i][0]=new String(currens.getCurrency(i).getName());
			data[i][1]=new String(Integer.toString(currens.getCurrency(i).getUnit()));
			data[i][2]=new String(currens.getCurrency(i).getCurrencycode());
			data[i][3]=new String(currens.getCurrency(i).getCountry());
			data[i][4]=new String(Double.toString(currens.getCurrency(i).getRate()));
			data[i][5]=new String(Double.toString(currens.getCurrency(i).getChange()));						
		}
		table= new JTable(data,columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(500,225));
		table.setFillsViewportHeight(true);
		table.setSize(600,250);
		JScrollPane scrollPane= new JScrollPane(table);											//panel of table		
		button = new JButton("CONVERT BETWEEN CURRENCIES");										//button to move to the other window
		button.addActionListener(new ActionListener() 
		{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					// TODO Auto-generated method stub
					System.out.println("CONVERT BETWEEN CURRENCIES was pressed");
					CurrencyCalculatorView converter = new CurrencyCalculatorView();
					converter.makeConvertGui();
				}
		});
		add(scrollPane);																		//add panel of table to frame
		add(txtUpdate);
		add(button);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);											//close the window will cause to end of the process
		setVisible(true);
		System.out.println("currencyTable was created");
	}
}
