
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.io.InputStream;
	import java.io.OutputStream;
	import java.security.InvalidAlgorithmParameterException;
	import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
	import java.security.SecureRandom;
	import java.security.spec.InvalidKeySpecException;
	import java.util.Scanner;
    import java.security.spec.AlgorithmParameterSpec;
	import javax.crypto.Cipher;
	import javax.crypto.CipherInputStream;
	import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
	import javax.crypto.SecretKey;
	import javax.crypto.SecretKeyFactory;
	import javax.crypto.spec.DESKeySpec;
	import javax.crypto.spec.IvParameterSpec;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class DES{	
		protected static String Password = null;
		static String plaintext=null;
		static File Encrypted=null;
		static File Decrypted=null;
		static String path=null;
		static JFrame f1;
		
		static JLabel l,l0,l1,l2;
		static JButton p, b,b1,b2;
		static ImageIcon icon;
		static JPanel p2,p1,p3,p4,p5,p6,p7,p8,p9;
        static JTextField te;
        static JPasswordField pass;
        
		
		
		
		
		public static void encryptdecrypt(String key,int cipherMode,File in, File out)
	   throws InvalidKeyException,NoSuchAlgorithmException,InvalidKeySpecException,
	  NoSuchPaddingException,IOException


	{
	FileInputStream fis=new FileInputStream(in);
	FileOutputStream fos=new FileOutputStream(out);

	DESKeySpec desKeySpec=new DESKeySpec(key.getBytes());
	SecretKeyFactory skf=SecretKeyFactory.getInstance("DES");
	SecretKey secretKey=skf.generateSecret(desKeySpec);

	Cipher cipher=Cipher.getInstance("DES/ECB/PKCS5Padding");

	if (cipherMode==Cipher.ENCRYPT_MODE)
	{
	 cipher.init(Cipher.ENCRYPT_MODE,secretKey,SecureRandom.getInstance("SHA1PRNG"));

	CipherInputStream cis=new CipherInputStream(fis,cipher);
	write(cis,fos);
	}
	else if(cipherMode==Cipher.DECRYPT_MODE)
	{
	cipher.init(Cipher.DECRYPT_MODE,secretKey,SecureRandom.getInstance("SHA1PRNG"));

	CipherOutputStream cos=new CipherOutputStream(fos,cipher);
	write(fis,cos);
	}
	}
	   
	private static void  write(InputStream in ,OutputStream out)throws IOException
	{
	byte[]buffer = new byte[64];
	int numofBytesRead;
	while((numofBytesRead= in.read(buffer))!= -1){
	out.write(buffer,0,numofBytesRead);
	}
	out.close();
	in.close();
	}


	public static void Start(){
		
		f1=new JFrame("ENCRYPTION");
		
		f1.setResizable(false);
		
		l=new JLabel("***********************DES***********************");
		l.setForeground(new Color(0x00FF00));
		l.setFont(new Font("MV Boli",Font.PLAIN,20)); 
		l1=new JLabel("<html>DES(DATA ENCRYPTION STANDARD) is used for encrypting a large<BR> amount of data with a 8 Byte passkey .</html>");
		l1.setForeground(Color.GREEN);
		l1.setFont(new Font("AvanteGrade",Font.BOLD,14));
		
	
		p=new JButton("Enter");
		p.setFont(new Font("Forte",Font.CENTER_BASELINE,15));
        p.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent be) {
        		Password=te.getText();
        		int l=Password.length();
        		if(l!=8) {
        			JOptionPane.showMessageDialog(null, "Error: Enter a 8 Character Passkey! ");
        		}
        		else {
        			JOptionPane.showMessageDialog(null, "Passkey saved \n Hint:*Remember your Passkey for futher use* ");
        		}
        		//p.setEnabled(false);
        		}
        	}
        );
       l0=new JLabel("<html>How to use: <BR> 1.Enter a PassKey of 8 characters not more than 8. <BR>2.*Click on Enter to save the PassKey* <BR>3.Click on Choose File to select a File from this Device.<BR>4.Click on Encrypt and choose a File on which it should be written. <BR>5.Click on Decrypt and choose a File on which it should be written .</html>" );
       l0.setForeground(Color.yellow);
	   l0.setFont(new Font("MV Boli",Font.ITALIC,14));
		
        
        l2=new JLabel("PassKey:");
        l2.setForeground(Color.GREEN);
		l2.setFont(new Font("AvantGrade",Font.BOLD,14));
		
        te=new JTextField(30);
        te.setColumns(30);
        
        te.setFont(new Font("Consolas",Font.PLAIN,30));
        te.setForeground(new Color(0x00FF00));
        te.setBackground(Color.black);
        te.setCaretColor(Color.white);
        te.setText("Enter Passkey");
        
        b2=new JButton("Choose File");
        b2.setFont(new Font("Forte",Font.CENTER_BASELINE,15));
        b2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent be) {
        		if(be.getSource()==b2) {
        		JFileChooser fileChooser1=new JFileChooser();
    			int response=fileChooser1.showOpenDialog(null);
        		
        		
        		if(response==JFileChooser.APPROVE_OPTION) {
        			 path=fileChooser1.getSelectedFile().getAbsolutePath();
        		}
        		}}
        	}
        );
        
        
		b=new JButton("Encrypt");
		b.setFont(new Font("Forte",Font.CENTER_BASELINE,15));
        b.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ae) {
        		
        		if(ae.getSource()==b) {
        			JFileChooser fileChooser=new JFileChooser();
        			int response=fileChooser.showOpenDialog(null);
        			//b.setEnabled(false);//   one time clickable
        			if (response==JFileChooser.APPROVE_OPTION) {
        				File plaintext=new File(path);
        			
        				File Encrypted=new File(fileChooser.getSelectedFile().getAbsolutePath());

        				try {
							encryptdecrypt(Password,Cipher.ENCRYPT_MODE,plaintext,Encrypted);
							JOptionPane.showMessageDialog(null, "File Encrypted Successfully! ");
						} catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException
								| NoSuchPaddingException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
        			}
        	
        	
        	}
        	}});
		
        b1=new JButton("Decrypt");
		b1.setFont(new Font("Forte",Font.CENTER_BASELINE,15));
		b1.setBounds(200, 200, 20, 20);
		b1.setFocusable(false);
        b1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ae) {
        		
        		if(ae.getSource()==b1) {
        			JFileChooser fileChooser=new JFileChooser();
        			int response=fileChooser.showOpenDialog(null);
        			
        			if (response==JFileChooser.APPROVE_OPTION) {
        				File Encrypted=new File(path);
        				File Decrypted=new File(fileChooser.getSelectedFile().getAbsolutePath());
        				try {
        					encryptdecrypt(Password,Cipher.DECRYPT_MODE,Encrypted,Decrypted);
        					JOptionPane.showMessageDialog(null, "File Decrypted Successfully!! ");
						} catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException
								| NoSuchPaddingException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
        			}
        	
        	
        	}
        	}});	
        
     p1=new JPanel();
     p1.setLayout(new BoxLayout(p1,BoxLayout.Y_AXIS));
     p1.setBackground(Color.BLACK);
     
     p2=new JPanel();
     p2.setLayout(new BoxLayout(p2,BoxLayout.Y_AXIS));
     p2.setBackground(Color.BLACK);
        
     p3=new JPanel();
     p3.setLayout(new BoxLayout(p3,BoxLayout.Y_AXIS));
     p3.setBackground(Color.BLACK);
        
     p4=new JPanel();
     p4.setLayout(new BoxLayout(p4,BoxLayout.Y_AXIS));
     p4.setBackground(Color.BLACK);
     
     p5=new JPanel();
     p5.setLayout(new BoxLayout(p5,BoxLayout.Y_AXIS));
     p5.setBackground(Color.BLACK);
     

     p6=new JPanel();
     p6.setLayout(new BoxLayout(p6,BoxLayout.Y_AXIS));
     p6.setBackground(Color.BLACK);
     

     p7=new JPanel();
     p7.setLayout(new BoxLayout(p7,BoxLayout.Y_AXIS));
     p7.setBackground(Color.BLACK);
     

     p8=new JPanel();
     p8.setLayout(new BoxLayout(p8,BoxLayout.Y_AXIS));
     p8.setBackground(Color.BLACK);
     

     p9=new JPanel();
     p9.setLayout(new BoxLayout(p9,BoxLayout.Y_AXIS));
     p9.setBackground(Color.BLACK);
     
     
		p1.add(l);
		p1.add(l1);
		p1.add(l0);
		p1.add(l2);
		p1.add(te);
		
		p1.add(p);
		p2.add(b2);
		p3.add(b);
		p4.add(b1);
		
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f1.add(p1,BorderLayout.NORTH);
		f1.add(p2,BorderLayout.LINE_START);
		f1.add(Box.createRigidArea(new Dimension(5,0)));
		f1.add(p3,BorderLayout.CENTER);
		f1.add(p4,BorderLayout.LINE_END);
		
		f1.setSize(400,500);
		f1.setVisible(true);
		f1.setLocationRelativeTo(null);
		
	}
	}
	


