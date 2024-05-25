import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Cargar extends JFrame implements ActionListener{
	private ConnectionDB cdb;
	private JLabel label1, label2, label5;
	private JButton newPlay, resume;
	private Bienvenida bienvenida;
	
	public Cargar(){
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(" OGAME");
		getContentPane().setBackground(new Color(51,0,51));
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
		
		resume = new JButton("PLAY");
		resume.setBounds(150,250,160,30);
		resume.setBackground(new Color(255,255,255));
		resume.setFont(new Font("Andale Mono", 1, 12));
		resume.setForeground(new Color(80,0,80));
		resume.addActionListener(this);
		add(resume);
		
		newPlay = new JButton("RESET PLAY");
		newPlay.setBounds(150,300,160,30);
		newPlay.setBackground(new Color(255,255,255));
		newPlay.setFont(new Font("Andale Mono", 1, 12));
		newPlay.setForeground(new Color(80,0,80));
		newPlay.addActionListener(this);
		add(newPlay);
		
		label5 = new JLabel("©2024 BUGISOFT");
		label5.setBounds(200,520,300,30);
		label5.setFont(new Font("Andale Mono", 1, 12));
		label5.setForeground(new Color(255,255,255));
		add(label5);
	}
		
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == resume) {
			bienvenida.setStartGame(true);
			dispose();
			JOptionPane.showMessageDialog(null,"WELCOME " + bienvenida.user);
		}
		
		if (e.getSource() == newPlay) {
			//Limpio los datos a default
			cdb.conection(4,null,null,null,null, false);
			newPlay.setVisible(false);
			resume.setBounds(150,280,160,30);
			this.revalidate();
		}
	}

	public Bienvenida getBienvenida() {
		return bienvenida;
	}

	public void setBienvenida(Bienvenida bienvenida) {
		this.bienvenida = bienvenida;
	}

	public ConnectionDB getCdb() {
		return cdb;
	}

	public void setCdb(ConnectionDB cdb) {
		this.cdb = cdb;
	}
	
//	public static void main(String[] args) {
//		Cargar ventanaCarga = new Cargar();
//	 	ventanaCarga.setBounds(0,0,500,600);
//	 	ventanaCarga.setVisible(true);
//	 	ventanaCarga.setResizable(false);
//	 	ventanaCarga.setLocationRelativeTo(null);
//	}
	
}
