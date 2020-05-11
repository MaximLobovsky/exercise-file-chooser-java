import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Statistiche_Testo extends JFrame
{
	private JFileChooser chooser;
	private JPanel contentPane;
	private JTextField nRighe;
	private JTextField nParole;
	private JTextField nCaratteri_senza_spazi;
	private JTextField nCaratteri_con_spazi;
	private JButton btnNewButton;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Statistiche_Testo frame = new Statistiche_Testo();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Statistiche_Testo()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 522, 376);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setForeground(Color.MAGENTA);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl1 = new JLabel("Righe: ");
		lbl1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl1.setBounds(10, 11, 146, 50);
		contentPane.add(lbl1);
		
		JLabel lblCaratteri = new JLabel("Caratteri:");
		lblCaratteri.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCaratteri.setBounds(10, 269, 146, 50);
		contentPane.add(lblCaratteri);
		
		JLabel lblParole = new JLabel("Parole:");
		lblParole.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblParole.setBounds(10, 142, 146, 50);
		contentPane.add(lblParole);
		
		JLabel lblConSpazi = new JLabel("con spazi");
		lblConSpazi.setHorizontalAlignment(SwingConstants.CENTER);
		lblConSpazi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblConSpazi.setBounds(320, 229, 146, 50);
		contentPane.add(lblConSpazi);
		
		JLabel lblSenzaSpazi = new JLabel("senza spazi");
		lblSenzaSpazi.setHorizontalAlignment(SwingConstants.CENTER);
		lblSenzaSpazi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSenzaSpazi.setBounds(77, 229, 146, 50);
		contentPane.add(lblSenzaSpazi);
		
		nRighe = new JTextField();
		nRighe.setEditable(false);
		nRighe.setHorizontalAlignment(SwingConstants.CENTER);
		nRighe.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nRighe.setBounds(100, 12, 100, 50);
		contentPane.add(nRighe);
		nRighe.setColumns(10);
		
		nParole = new JTextField();
		nParole.setEditable(false);
		nParole.setHorizontalAlignment(SwingConstants.CENTER);
		nParole.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nParole.setColumns(10);
		nParole.setBounds(100, 142, 100, 50);
		contentPane.add(nParole);
		
		nCaratteri_senza_spazi = new JTextField();
		nCaratteri_senza_spazi.setEditable(false);
		nCaratteri_senza_spazi.setHorizontalAlignment(SwingConstants.CENTER);
		nCaratteri_senza_spazi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nCaratteri_senza_spazi.setColumns(10);
		nCaratteri_senza_spazi.setBounds(100, 269, 100, 50);
		contentPane.add(nCaratteri_senza_spazi);
		
		nCaratteri_con_spazi = new JTextField();
		nCaratteri_con_spazi.setEditable(false);
		nCaratteri_con_spazi.setHorizontalAlignment(SwingConstants.CENTER);
		nCaratteri_con_spazi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nCaratteri_con_spazi.setColumns(10);
		nCaratteri_con_spazi.setBounds(342, 269, 100, 50);
		contentPane.add(nCaratteri_con_spazi);
		
		
		chooser = new JFileChooser();
		chooser.setFileFilter(new FileNameExtensionFilter("file di testo txt", "txt"));
		
		btnNewButton = new JButton("APRI FILE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int righe=1, parole=1, caratteri_con_spazi=0, caratteri_senza_spazi=0;
				int stato = chooser.showOpenDialog(contentPane);
				if(stato == JFileChooser.APPROVE_OPTION){
					//si lamentava se non mettevo try catch, importa solo la roba nel try
					try{
						FileReader fr = new FileReader(chooser.getSelectedFile());
						while(true) {
							int fintocarattere; 
							fintocarattere=fr.read();							
							if (fintocarattere==-1)
								break;
							char verocarattere = (char) fintocarattere;
							if(verocarattere=='\r') // a quanto pare windows ha due terminatori, quindi uno lo ignoriamo
								continue;
							if(verocarattere=='\n'){
								righe++;
								parole++;
							}
							if(verocarattere==' ')
								parole++;
							else
								caratteri_senza_spazi++;
							caratteri_con_spazi++;
						} 
						nCaratteri_con_spazi.setText(Integer.toString(caratteri_con_spazi));
						nCaratteri_senza_spazi.setText(Integer.toString(caratteri_senza_spazi));
						nParole.setText(Integer.toString(parole));
						nRighe.setText(Integer.toString(righe));
						fr.close();
					} 
					catch (IOException e){
						e.printStackTrace();
					}
					
					
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(266, 64, 200, 100);
		contentPane.add(btnNewButton);
		

		
		
	}
}
