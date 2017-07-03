import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.DataInputStream;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.text.html.*;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class chat_server extends JFrame {
	
	
	private JPanel contentPane;
	private JTextField msg_text;
	private boolean isRunnig = false;
	
	
	static ServerSocket ss;
	static Socket s;
	static DataInputStream din;
	static DataOutputStream dout;
	static JTextArea msg_area;
	static JEditorPane msg_area2;
	
	 private List<chat_client> threadList = new ArrayList<>();
	
	
	public chat_server() {
		setTitle("Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		msg_text = new JTextField();
		msg_text.setBounds(10, 230, 297, 20);
		contentPane.add(msg_text);
		msg_text.setColumns(10);
		
		msg_area = new JTextArea();
		msg_area.setEditable(false);
		msg_area.setBounds(10, 11, 392, 208);
		
		contentPane.add(msg_area);
		
		JButton msg_send = new JButton("Send");
		
		

		msg_send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String msgout="";
				
				msgout = msg_text.getText().trim();
				try {
					dout.writeUTF(msgout);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				msg_text.setText("");
			}
			
		});
		msg_send.setBounds(313, 229, 89, 23);
		contentPane.add(msg_send);
		
		msg_area2 = new JEditorPane();
		msg_area2.setBounds(220, 11, 182, 208);
		contentPane.add(msg_area2);
		msg_area2.setContentType("text/html");
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					chat_server frame = new chat_server();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		String msgin="";
		 final Socket[] socket = {null};
		 int counter=0;
		 try {
				ss=new ServerSocket(1201);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		new Thread("Klient"){
			String msgin="";
			public void run(){
			
				
				while(true){
				try{
				//ss=new ServerSocket(1201);
				socket[0]=ss.accept();
				
             
                
				msg_area.setText("New Client Connected");
				
			
				din=new DataInputStream(socket[0].getInputStream());
				dout=new DataOutputStream(socket[0].getOutputStream());
				 SimpleDateFormat dateformat = new SimpleDateFormat( "HH:mm:ss" );
				
			      while(!msgin.equals("exit")){
					msgin=din.readUTF();
					msg_area.setText(msg_area.getText().trim()+"\nClient: \t"+msgin); 
					msg_area2.setText("<html><font size=\"3\"> Last message received: [" + dateformat.format( new Date() ) + "] " +  "</font></html>" + msgin );
				
				/*	int msgtype=din.readInt();			
					if (msgtype==2){
						msg_area.setText(msg_area.getText().trim()+"New Client Connected"); 
					}
					else if(msgtype==3){
						msg_area.setText(msg_area.getText().trim()+"User disconnected"); 
					}*/
					 /*switch( msgtype )
	                    {
	                        case MessageType.REGISTER:
	                           
	                            break;

	                        case MessageType.LOGIN:
	                        	msg_area.setText(msg_area.getText().trim()+"User connected"); 
	                            break;

	                        case MessageType.MESSAGE:
	                            
	                            break;

	                        case MessageType.STATISTICS:
	                            
	                            break;

	                        case MessageType.LOGOUT:
	                        	msg_area.setText(msg_area.getText().trim()+"User disconnected"); 
	                            break;
	                        default:
	                        	int a =1;
	                        	
	                    }*/
				}
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
				}
				}
		}.start();
		/*try{
			//ss=new ServerSocket(1201);
			//s=ss.accept();
			
			din=new DataInputStream(s.getInputStream());
			dout=new DataOutputStream(s.getOutputStream());
			
			
			while(!msgin.equals("exit")){
				msgin=din.readUTF();
				msg_area.setText(msg_area.getText().trim()+"\nClient: \t"+msgin); 
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}*/
	}
	public void start()
	{
		String msgin="";

		
		    
	        final Socket[] socket = {null};

	        try { ss=new ServerSocket(1201);}
	        catch( IOException e ) {e.printStackTrace();}

	       
	        new Thread( "Nowi_klienci" )
	        {
	            public void run()
	            {
	                if( !isRunnig )
	                {
	                    isRunnig = true;
	                    while( isRunnig )
	                    {
	                        try {socket[0] = ss.accept();}
	                        catch( IOException e ) {break;}

	                       
	                    }
	                }
	            }
	        }.start();
	}
	
    public void stop()
    {
        new Thread( "Close" )
        {
            public void run()
            {
                isRunnig = false;

               
            }
        }.start();
        
    }
}
