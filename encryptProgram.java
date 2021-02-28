
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
public class encryptProgram{
private Scanner scanner;
private Random random;
private ArrayList<Character> list;
private ArrayList<Character> shuffledList;
private char character;
private String line;
private char[] letters;


static JFrame f1;

static JLabel l,l1,l2;
static JButton b, b1,b2,b3;
static ImageIcon icon;
static JPanel p0,p2,p1,p3,p4,p5;
static JTextField te;
static JTextArea textArea;

encryptProgram(){
	f1=new JFrame("One Time Pad");

  scanner=new Scanner(System.in);
  random=new Random();
  list= new ArrayList();
  shuffledList=new ArrayList();
  character=' '; 
  l=new JLabel("******OTP-One Time Pad*******");
  l.setForeground(new Color(0x00FF00));
	l.setFont(new Font("MV Boli",Font.PLAIN,20)); 
l1=new JLabel("<html>How to use:<BR>\r\n" + 
		"1.Click on NewKey to generate a one time key.<BR>\r\n" + 
		"2.Click on GetKey to copy the key.<BR>\r\n" + 
		"3.Enter the Text in the text field.</html>");
l1.setForeground(new Color(0x00FF00));
l1.setFont(new Font("MV Boli",Font.PLAIN,15)); 
  newkey();
  askquestion();
  te=new JTextField(50);
  te.setColumns(50);
  
  te.setFont(new Font("Consolas",Font.PLAIN,20));
  te.setForeground(new Color(0x00FF00));
  te.setBackground(Color.black);
  te.setCaretColor(Color.white);
  
  te.setText("Enter text ");
  
  
  p0=new JPanel();
  p0.setLayout(new BoxLayout(p0,BoxLayout.Y_AXIS));
  p0.setBackground(Color.BLACK);
  

  p1=new JPanel();
  p1.setLayout(new BoxLayout(p1,BoxLayout.Y_AXIS));
  p1.setBackground(Color.BLACK);
  
  p3=new JPanel();
  p3.setLayout(new BoxLayout(p3,BoxLayout.Y_AXIS));
  p3.setBackground(Color.BLACK);
  
  p4=new JPanel();
  p4.setLayout(new BoxLayout(p4,BoxLayout.Y_AXIS));
  p4.setBackground(Color.BLACK);
  
  p2=new JPanel();
  p2.setLayout(new BoxLayout(p2,BoxLayout.Y_AXIS));
  p2.setBackground(Color.BLACK);
  
  p5=new JPanel();
  p5.setLayout(new BoxLayout(p5,BoxLayout.Y_AXIS));
  p5.setBackground(Color.BLACK);
  
  
  p1.add(l);
  p1.add(l1);
  p1.add(b);
  p1.add(te);
  p2.add(b1);
  p3.add(b2);
  p4.add(b3);
  f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f1.add(p1,BorderLayout.NORTH);
	
	f1.add(p2,BorderLayout.LINE_START);
	f1.add(p3,BorderLayout.CENTER);
	f1.add(p4,BorderLayout.LINE_END);
	
	f1.setSize(305,400);
	f1.setVisible(true);
	f1.setLocationRelativeTo(null);
	
}
private void askquestion(){

	b=new JButton("NEW KEY");
	b.setFont(new Font("Forte",Font.CENTER_BASELINE,15));
	
    b.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent be) {
    		newkey();
    		
    		}
    	}
    );
	
    b1=new JButton("GET KEY");
	
    b1.setFont(new Font("Forte",Font.CENTER_BASELINE,15));
    b1.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent be) {
    		getkey();
    		
    		}
    	}
    );
    
    b2=new JButton("ENCRYPT");
	b2.setFont(new Font("Forte",Font.CENTER_BASELINE,15));
    b2.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent be) {
    		encrypt();
    		
    		}
    	}
    );
    
    b3=new JButton("DECRYPT");
	b3.setFont(new Font("Forte",Font.CENTER_BASELINE,15));
    b3.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent be) {
    		decrypt();
    		
    		}
    	}
    );
   }




private void newkey(){

 character= ' ';
 list.clear();
 shuffledList.clear();
 
for(int i=32;i<127;i++){
    list.add(Character.valueOf(character));
    character++;
}
 
shuffledList=new ArrayList(list);
Collections.shuffle(shuffledList);

JOptionPane.showMessageDialog(null,"A new Key is Generated!!");

}

private void getkey(){
	String Getkey="";
	String Sgetkey="";

for(Character x: list){
	Getkey+=x;	

	
}

for(Character x : shuffledList){
	Sgetkey+=x;
}


 textArea=new JTextArea(Getkey+"\n"+Sgetkey);
 textArea.setEditable(true);
JOptionPane.showMessageDialog(null,textArea);	
}
private void encrypt(){


String message=te.getText();

letters=message.toCharArray();

for(int i=0;i<letters.length;i++){

 for(int j=0;j<list.size();j++){
 if(letters[i]==list.get(j)){
letters[i]=shuffledList.get(j);
break;
}
}
}

String en="";
for(char x : letters){
//System.out.print(x);
en+=x;
}
textArea=new JTextArea(en);
textArea.setEditable(true);
JOptionPane.showMessageDialog(null,textArea);	
}
private void decrypt(){

	String message=te.getText();

letters=message.toCharArray();

for(int i=0;i<letters.length;i++){

 for(int j=0;j<shuffledList.size();j++){
 if(letters[i]==shuffledList.get(j)){
letters[i]=list.get(j);
break;
}
}
}

String de="";
for(char x : letters){
de+=x;
}
textArea=new JTextArea(de);
textArea.setEditable(true);
JOptionPane.showMessageDialog(null,textArea);	



}

}
