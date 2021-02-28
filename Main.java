
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Main {
    
	static JFrame f;
	static JRadioButton r1,r2;
	static JLabel l,l1,l2,l3;
	static JButton b;
	static ImageIcon ic;

	
	public static void main(String[] args) {
		boolean n;
		
		// TODO Auto-generated method stub
		
		f=new JFrame("ENCRYPTION");
		l=new JLabel("<html>...............F.E.A.D..............<BR></html>");
		l.setForeground(new Color(0x00FF00));
		l.setFont(new Font("MV Boli",Font.PLAIN,20)); 
	    ic=new ImageIcon(Main.class.getResource("Enc (2).png"));
		l1=new JLabel("",ic,JLabel.TRAILING);
		
		l2=new JLabel("<html>DES(DATA ENCRYPTION STANDARD) is used for encrypting a large<BR> amount of data with a 8 Byte passkey .</html>");
		l2.setForeground(Color.GREEN);
		l2.setFont(new Font("MV Boli",Font.ITALIC,14));
		l3=new JLabel("<html>OTP(One Time Pad) is used for encrypting small texts <BR>It generates a one time key.<BR></html>");
		l3.setForeground(Color.GREEN);
		l3.setFont(new Font("MV Boli",Font.ITALIC,14));
		r1=new JRadioButton("DES");
		r1.setFont(new Font("Forte",Font.CENTER_BASELINE,15));
        r1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent ae) {
				
				 DES de=new DES ();
				 de.Start();
				
			}

			
			
		});
		r2=new JRadioButton("OTP"); 
		r2.setFont(new Font("Forte",Font.CENTER_BASELINE,16));
		r2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent ae) {
				encryptProgram ep =new encryptProgram();
			
				
			}

			
			
		});
		
	
		 JPanel p=new JPanel();
		 p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
		ButtonGroup group=new ButtonGroup();
		group.add(r1);
		group.add(r2);
		p.setBackground(Color.BLACK);
		p.add(l);
		p.add(l1);
		p.add(r1);
		p.add(l2);
		p.add(r2);
		p.add(l3);
		
	    
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(p);
		f.setSize(300,500);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		
		
	}

}
