import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.text.html.*;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import javax.swing.JEditorPane;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class chat_client extends JFrame {

	private JPanel contentPane;
	private JTextField msg_text;
	static JTextArea  msg_area;
    
	static Socket s;
	static DataInputStream din;
	static DataOutputStream dout;
	
	private String name, password;
	private static JEditorPane msg_area2;
	
	/**
	 * Launch the application.
	 */
	public chat_client() {
		
		
		
		setTitle("Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		msg_text = new JTextField();
		msg_text.setBounds(10, 230, 318, 20);
		contentPane.add(msg_text);
		msg_text.setColumns(10);
		
		msg_area = new JTextArea();
		msg_area.setEditable(false);
		msg_area.setBounds(10, 11, 414, 208);
		contentPane.add(msg_area);
		
		JButton msg_send = new JButton("Send");
		
		
		msg_send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String msgout ="";
				msgout = msg_text.getText().trim();
				try {
					if(msgout.equals("exit")){	
						
					dout.writeUTF("User disconnected");
					disconnect();
					}
					else
					dout.writeUTF(msgout);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				msg_text.setText("");
			}
		});
		msg_send.setBounds(338, 229, 89, 23);
		contentPane.add(msg_send);
		
		msg_area2 = new JEditorPane();
		msg_area2.setBounds(228, 11, 196, 208);
		contentPane.add(msg_area2);
		msg_area2.setContentType("text/html");
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					chat_client frame = new chat_client();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		
		try{
			s= new Socket("127.0.0.1", 1201);
			din=new DataInputStream(s.getInputStream());
			dout=new DataOutputStream(s.getOutputStream());
			SimpleDateFormat dateformat = new SimpleDateFormat( "HH:mm:ss" );
			//dout.writeInt(MessageType.LOGIN);
			
			String msgin="";
			while(!msgin.equals("exit")){
				msgin=din.readUTF();
				msg_area.setText(msg_area.getText().trim()+"\nServer: \t"+msgin); 
				msg_area2.setText("<html><font size=\"3\"> Last message received: [" + dateformat.format( new Date() ) + "] " +  "</font></html>" + msgin );
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public void connect(){
		try{
			s= new Socket("127.0.0.1", 1201);
			din=new DataInputStream(s.getInputStream());
			dout=new DataOutputStream(s.getOutputStream());
			dout.writeInt( MessageType.LOGIN );
			String msgin="";
			while(!msgin.equals("exit")){
				msgin=din.readUTF();
				msg_area.setText(msg_area.getText().trim()+"\nServer: \t"+msgin); 
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void disconnect(){
	      if( !s.isClosed() )
	        {
	            try
	            {
	               
	            	dout.writeInt( MessageType.LOGOUT );
	                s.close();
	                dout.close();
	                din.close();
	            }
	            catch( IOException e ) {e.printStackTrace();}
	        }
	}

}
