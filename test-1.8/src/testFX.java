import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.Label;

//import java.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.control.Button;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.util.Arrays;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class testFX {

	private JFrame frame;
	private JTextField userinput;
	//public String input="";
	public float x=0;
    public String[] binaryAnswer=new String[65];	
    
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testFX window = new testFX();
					window.frame.setVisible(true);
				} catch (Exception e) {
					
					//e.printStackTrace();
					
				}
			}
		});
	}
	public String Binary(float x) {
        String str="";
		float integer=0;
		float decimal=0;
		if(x>=0) {
			integer=(float)Math.floor(x);
			decimal=x-integer;
		}
		
		boolean flag=false;
		//處理整數部分
		long mask=1;
		mask<<=31;
		for(int i=0;i<32;i++) {
			
 			if(integer>=mask) {
 				str+="1";
				integer=integer%(float)mask;
				flag=true;
			}else {
				if(flag==true) {
					str+="0";
				}
			}
			mask>>=1;
		}
        str+=".";
		//處理小數部分
		for(int i=0;i<32;i++) { 
			if((decimal*2)>=1) {
				str+="1";
				
				decimal=decimal*2-1;
			}else {
				str+="0";
				
				decimal*=2;
			}
			if(decimal==0.0) {
				break;
			} 
		}
        return str;
	}

	public String Oct(float x) {
		String str="";
		String tmp="";
		float integer=0;
		float decimal=0;
		if(x>=0) {
			integer=(float)Math.floor(x);
			decimal=x-integer;
		}else {
			integer=(float)Math.ceil(x);
			decimal=x-integer;
		}
		
		//處理整數
		long mask=1;
		mask<<=30;
		for(int i=0;i<30;i++) {
			if(integer>=mask) {
				int quo=(int)(integer/mask);	//商
				if(quo==0) {
					break;
				}else {
					tmp=String.valueOf(quo);
					str+=tmp;
				}
				integer=integer%mask;
				mask>>=3;			
			}else {
				if(integer==0) {
					break;
				}
				//System.out.print("0");
				mask>>=3;
			}		
		}
		str+=".";
		
		//處理小數
		for(int i=0;i<32;i++) { 
			if((decimal*8)>=1) {
				int quoD=(int)(Math.floor(decimal*8)); //小數的商
				tmp=String.valueOf(quoD);
				str+=tmp;
				decimal=decimal*8-quoD;
			}else {
				str+="0";
				decimal*=8;
			}
			if(decimal==0.0) {
				break;
			} 
		}
		return str;
	}
	
	public String Hex(float x) {
		String str="";
		String tmp="";
		float integer=0;
		float decimal=0;
		if(x>=0) {
			integer=(float)Math.floor(x);
			decimal=x-integer;
		}else {
			integer=(float)Math.ceil(x);
			decimal=x-integer;
		}
		//處理整數
		long mask=1;
		mask<<=28;
		for(int i=0;i<28;i++) {
			if(integer>=mask) {
				int quo=(int)(integer/mask);	//商
				tmp=String.valueOf(quo);
				str+=tmp;
				if(quo==0) {
					break;
				}else {
					HexValue(quo);
				}
				integer=integer%mask;
				mask>>=4;			
			}else {
				if(integer==0) {
					break;
				}
				//System.out.print("0");
				mask>>=4;
			}		
		}
		str+=".";
		//處理小數
		for(int i=0;i<28;i++) { 
			if((decimal*16)>=1) {
				int quoD=(int)(Math.floor(decimal*16)); //小數的商
				str+=HexValue(quoD);
				decimal=decimal*16-quoD;
			}else {
				str+="0";
				decimal*=16;
			}
			if(decimal==0.0) {
				break;
			} 
		}
		return str;
				
	}
	
	public String HexValue(int y) {
		String tmp="";
		if(y<10) {
			tmp=String.valueOf(y);
		}
		else {
			switch(y) {
				case 10:
					tmp="A";
					break;
				case 11:
					tmp="B";
					break;
				case 12:
					tmp="C";
					break;
				case 13:
					tmp="D";
					break;
				case 14:
					tmp="E";
					break;
				case 15:
					tmp="F";
					break;
			}
		}
		return tmp;
		
	}
	
	
	
	/**
	 * Create the application.
	 */
	public testFX() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\eclipse-workspace\\test-1.8\\icon.png"));
		frame.setTitle("進制轉換  作者:陳宥錡");
		frame.setBounds(100, 100, 629, 501);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel labelName = new JLabel("\u9032\u5236\u8F49\u63DB\u5C0F\u5DE5\u5177");
		labelName.setHorizontalAlignment(SwingConstants.CENTER);
		labelName.setFont(new Font("微軟正黑體", Font.BOLD, 30));
		labelName.setBounds(189, 33, 221, 53);
		frame.getContentPane().add(labelName);
		
		userinput = new JTextField();
		
		
		userinput.setToolTipText("請輸入數字於此");
		userinput.setFont(new Font("新細明體", Font.PLAIN, 15));
		userinput.setBounds(196, 140, 214, 42);
		frame.getContentPane().add(userinput);
		userinput.setColumns(10);
		
		//確定按鈕
		JButton sure = new JButton("確定");
		
		sure.setFont(new Font("微軟正黑體", Font.BOLD | Font.ITALIC, 17));
		sure.setBounds(429, 140, 73, 42);
		frame.getContentPane().add(sure);
		
		JLabel label = new JLabel("輸入數字：");
		label.setFont(new Font("微軟正黑體 Light", Font.BOLD, 17));
		label.setBounds(115, 198, 115, 28);
		frame.getContentPane().add(label);
		
		JLabel inputValue = new JLabel("");
		inputValue.setToolTipText("input number");
		
		inputValue.setFont(new Font("Segoe UI Black", Font.PLAIN, 17));
		inputValue.setBounds(236, 198, 278, 28);
		frame.getContentPane().add(inputValue);
		
		final JLabel label_binary = new JLabel("\u4E8C\u9032\u5236\uFF1A");
		label_binary.setFont(new Font("微軟正黑體 Light", Font.BOLD, 17));
		label_binary.setBounds(115, 251, 94, 28);
		frame.getContentPane().add(label_binary);
		
		final JLabel label_oct = new JLabel("\u516B\u9032\u5236\uFF1A");
		label_oct.setFont(new Font("微軟正黑體 Light", Font.BOLD, 17));
		label_oct.setBounds(115, 306, 94, 28);
		frame.getContentPane().add(label_oct);
		
		final JLabel label_hex = new JLabel("\u5341\u516D\u9032\u5236\uFF1A");
		label_hex.setFont(new Font("微軟正黑體 Light", Font.BOLD, 17));
		label_hex.setBounds(99, 363, 110, 28);
		frame.getContentPane().add(label_hex);
		
		//二位元輸出
		JLabel binary = new JLabel("");
		binary.setForeground(new Color(160, 82, 45));
		binary.setFont(new Font("Segoe UI Black", Font.PLAIN, 17));
		binary.setBounds(215, 251, 299, 28);
		frame.getContentPane().add(binary);
		
		//八位元輸出
		JLabel oct = new JLabel("");
		oct.setForeground(new Color(25, 25, 112));
		oct.setFont(new Font("Segoe UI Black", Font.PLAIN, 17));
		oct.setBounds(215, 306, 278, 28);
		frame.getContentPane().add(oct);
		
		//十六位元輸出
		JLabel hex = new JLabel("");
		hex.setForeground(new Color(47, 79, 79));
		hex.setFont(new Font("Segoe UI Black", Font.PLAIN, 17));
		hex.setBounds(215, 363, 278, 28);
		frame.getContentPane().add(hex);
		
		JLabel labelName_1 = new JLabel("\u8F38\u5165\u975E\u8CA0\u5341\u9032\u4F4D\u6578\u5B57\u65BC\u4E0B\u5217\u65B9\u6846\u4E2D");
		labelName_1.setForeground(new Color(106, 90, 205));
		labelName_1.setHorizontalAlignment(SwingConstants.CENTER);
		labelName_1.setFont(new Font("微軟正黑體", Font.BOLD, 17));
		labelName_1.setBounds(156, 96, 299, 28);
		frame.getContentPane().add(labelName_1);
		
		JButton restart = new JButton("重新");
		
		restart.setFont(new Font("微軟正黑體 Light", Font.BOLD | Font.ITALIC, 17));
		restart.setBounds(387, 417, 73, 37);
		frame.getContentPane().add(restart);
		
		sure.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                x=Float.parseFloat(userinput.getText());
                inputValue.setText(Float.toString(x));
                binary.setText(Binary(x));

            }
        });
		
        sure.addActionListener(new ActionListener() {
            
            public void actionPerformed(java.awt.event.ActionEvent e) {
                x=Float.parseFloat(userinput.getText());
                Binary(x);
                inputValue.setText(Float.toString(x));
                binary.setText(String.valueOf(Binary(x)));
                oct.setText(String.valueOf(Oct(x)));
                hex.setText(String.valueOf(Hex(x)));
                
            }
        });
		
        restart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				userinput.setText("");
				inputValue.setText("");
				binary.setText("");
				oct.setText("");
				hex.setText("");
			}
		});
        
        
	}
	
}



