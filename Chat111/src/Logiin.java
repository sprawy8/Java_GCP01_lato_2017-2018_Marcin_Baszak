
/*
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Logiin extends JFrame {

	private JPanel contentPane;
	private JTextField login_area;
	private JTextField pass_area;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Logiin frame = new Logiin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Logiin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		login_area = new JTextField();
		login_area.setBounds(89, 80, 86, 20);
		contentPane.add(login_area);
		login_area.setColumns(10);
		
		pass_area = new JTextField();
		pass_area.setBounds(89, 146, 86, 20);
		contentPane.add(pass_area);
		pass_area.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = login_area.getText();
				String address = pass_area.getText();
				login(name, address);
			}
		});
		btnLogin.setBounds(89, 223, 89, 23);
		contentPane.add(btnLogin);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(89, 54, 46, 14);
		contentPane.add(lblLogin);
		
		JLabel lblHaslo = new JLabel("Haslo:");
		lblHaslo.setBounds(89, 123, 46, 14);
		contentPane.add(lblHaslo);
		
		JButton btnSignUp = new JButton("Sign up");
		btnSignUp.setBounds(167, 29, 89, 23);
		contentPane.add(btnSignUp);
	}
	
	private void login(String name, String password) {
		dispose();
		new chat_client(name, password);
	}
}*/
