
// PROYECTO
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Bienvenida extends JFrame implements ActionListener{
	private boolean startGame = false;
	private ConnectionDB cdb;
	private JPasswordField passFiled;
	private JTextField textfield2;
	private JLabel label1, label2, label3, label4,label5,label6, label7, fondoLabel;
	private JButton boton1, boton2, newUser;
	// Var comaprtida por las diferentes interfaces
	public static String user ="";
	public static String password = "";
	 
	public Bienvenida(){
	setLayout(null);
    // Cada vez que cierres la ventana el proceso se detendra para no quedan en 2do plano
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	// Titulo de la ventana
	setTitle(" OGAME");
	// Color de fondo
	getContentPane().setBackground(new Color(51,0,51));
	// Icono de la vantana
	setIconImage(new ImageIcon(getClass().getResource("OGamelogopng.png")).getImage());
	
	// Meto la otra imagen en un label
	ImageIcon imagen = new ImageIcon(".\\fotos\\Logo.png");
	label1 = new JLabel();
	label1.setBounds(100,0,400,200);
	label1.setIcon(imagen);
	add(label1);
	
	label2 = new JLabel("WELCOME TO THE SPACE ODYSSEY");
	label2.setBounds(150,150,400,30);
	label2.setFont(new Font("Andale Mono", 3, 10));
	label2.setForeground(Color.gray);
	add(label2);
	
	label3 = new JLabel("Username");
	label3.setBounds(110,212,200,30);
	label3.setFont(new Font("Andale Mono", 1, 12));
	label3.setForeground(new Color(255,255,255));
	add(label3);
	
	label4 = new JLabel("Password");
	label4.setBounds(110,282,200,30);
	label4.setFont(new Font("Andale Mono", 1, 12));
	label4.setForeground(new Color(255,255,255));
	add(label4);
	
	label6 = new JLabel("New Username");
	label6.setBounds(110,212,200,30);
	label6.setFont(new Font("Andale Mono", 1, 12));
	label6.setForeground(new Color(255,255,255));
	add(label6);
	label6.setVisible(false);
	
	label7 = new JLabel("New Password");
	label7.setBounds(110,282,200,30);
	label7.setFont(new Font("Andale Mono", 1, 12));
	label7.setForeground(new Color(255,255,255));
	add(label7);
	label7.setVisible(false);
	
	label5 = new JLabel("©2024 BUGISOFT");
	label5.setBounds(190,520,300,30);
	label5.setFont(new Font("Andale Mono", 1, 12));
	label5.setForeground(new Color(255,255,255));
	add(label5);
	
	textfield2 = new JTextField ();
	textfield2.setBounds(110,240,255,25);
	textfield2.setBackground(new Color(224,224,224));
	textfield2.setFont(new Font("Andale Mono", 1, 14));
	textfield2.setForeground(new Color(0,0,0));
	add(textfield2);
	
	passFiled = new JPasswordField ();
	passFiled.setBounds(110,310,255,25);
	passFiled.setBackground(new Color(224,224,224));
	passFiled.setFont(new Font("Andale Mono", 1, 14));
	passFiled.setForeground(new Color(0,0,0));
	passFiled.setEchoChar('*');
	add(passFiled);
	
	boton1 = new JButton("ENTER");
	boton1.setBounds(185,380,100,30);
	boton1.setBackground(new Color(255,255,255));
	boton1.setFont(new Font("Andale Mono", 1, 14));
	boton1.setForeground(new Color(0,0,0));
	boton1.addActionListener(this);
	add(boton1);
	
	boton2 = new JButton("CREATE");
	boton2.setBounds(185,380,100,30);
	boton2.setBackground(new Color(255,255,255));
	boton2.setFont(new Font("Andale Mono", 1, 14));
	boton2.setForeground(new Color(0,0,0));
	boton2.addActionListener(this);
	add(boton2);
	boton2.setVisible(false);
	
	newUser = new JButton("NEW USER");
	newUser.setBounds(20,525,90,20);
	newUser.setBackground(Color.lightGray);
	newUser.setFont(new Font("Andale Mono", 1, 8));
	newUser.setForeground(new Color(80,0,80));
	newUser.addActionListener(this);
	add(newUser);
	
	
	
	setVisible(true);
  }


   public void actionPerformed(ActionEvent e){
	if(e.getSource() == newUser){
		label3.setVisible(false);
		label4.setVisible(false);
		newUser.setVisible(false);
		boton1.setVisible(false);
		
		textfield2.setText("");
		passFiled.setText("");
		boton2.setVisible(true);
		label6.setVisible(true);
		label7.setVisible(true);
		this.revalidate();
		
	}
	
	
 	if(e.getSource() == boton1){
   	  // <.trim(>) es como un <replace(" ","")>
	   	user = textfield2.getText().trim();
	   	char[] passwordChars = passFiled.getPassword();
	   	String password = new String(passwordChars);
	   
	   	 //No ha escrito nada
	   	if (user.equals("") || password.equals("")){
	   		JOptionPane.showMessageDialog(null, "   Complete the fields.");
	   	}// Ha escrito sus credenciales
	   	else {
	   		//LOGIN
	   		cdb.conection(1, null, null, user, password, false);
	   		if (cdb.getUser_id() == -1) {
	   			JOptionPane.showMessageDialog(null,"INCORRECT USER OR PASSWORD, TRY AGAIN.");
	   			textfield2.setText("");
	   			passFiled.setText("");
	   			this.revalidate();
			}
			// LOGIN CORRECTO, SALGO
			else {
				// Empiezo el juego
				
			 	Cargar ventanaCarga = new Cargar();
			 	ventanaCarga.setBounds(0,0,500,600);
			 	ventanaCarga.setVisible(true);
			 	ventanaCarga.setResizable(false);
			 	ventanaCarga.setLocationRelativeTo(null);
			 	ventanaCarga.setCdb(cdb);
			 	ventanaCarga.setBienvenida(this);
			 	ventanaCarga.setNewUser(false);
			 	
				//setStartGame(true);
			 	//dispose();
			}
	   	} 
 	}
	//Enter new user
   	if(e.getSource() == boton2){
   		user = textfield2.getText().trim();
	   	char[] passwordChars2 = passFiled.getPassword();
	   password = new String(passwordChars2);
	   
	   	 //No ha escrito nada
	   	if (user.equals("") || password.equals("")){
	   		JOptionPane.showMessageDialog(null, "   Complete the fields.");
	   	}// Ha escrito sus credenciales
	   	else {
	   		//TRUE para nuevo usuario
	   		//Subir datos
	   		cdb.conection(4, null, null, user, password, true);
	   		// Comprobar login
	   		cdb.conection(1, null, null, user, password, false);
	   		if (!(cdb.getUser_id() == -1)) {
		   		JOptionPane.showMessageDialog(null, "  NUEVO USUARIO AÑADIDO");
		   		// Empiezo el juego
			 	Cargar ventanaCarga = new Cargar();
			 	ventanaCarga.setBounds(0,0,500,600);
			 	ventanaCarga.setVisible(true);
			 	ventanaCarga.setResizable(false);
			 	ventanaCarga.setLocationRelativeTo(null);
			 	ventanaCarga.setCdb(cdb);
			 	ventanaCarga.setBienvenida(this);
			 	ventanaCarga.setNewUser(true);
	   		}else {
	   			JOptionPane.showMessageDialog(null, " ALGO HA IDO MAL");
	   		}
		 	
	   	}
   	}
   }

//   public static void main(String args[]){
// 	Bienvenida ventanabienvenida = new Bienvenida();
// 	ventanabienvenida.setBounds(0,0,500,600);
// 	ventanabienvenida.setVisible(true);
// 	ventanabienvenida.setResizable(false);
// 	ventanabienvenida.setLocationRelativeTo(null);
//   }
   
	public boolean isStartGame() {
		return startGame;
	}


	public void setStartGame(boolean startGame) {
		this.startGame = startGame;
	}


	public ConnectionDB getCdb() {
		return cdb;
	}


	public void setCdb(ConnectionDB cdb) {
		this.cdb = cdb;
	}   
  
}
