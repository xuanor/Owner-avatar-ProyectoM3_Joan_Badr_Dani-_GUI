
//PROYECTO

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

public class Principal extends JFrame implements ActionListener{
	private Planet planet;
	private Battle battle;
	private Main back;
	private ConnectionDB cdb;
	
	private int opc = 0;
	private boolean flagBuild, flagTech, flagReport, flagReportLog;
	private boolean attackComing = false;
    private JMenuBar mb;
    private JMenu menuOpciones,menuAcercaDe,menuColorFondo;
    private JMenuItem miBlanco,miNegro,miMorado,miElCreador,miSalir,miVars;
    private JLabel labelLogo,labelPlaneta,labelTitle,labelReports,labelReportsLog,labelfooter, labelDecorado;
    private JTextField datos;
    private JButton opc1, opc2, opc3, opc4, opc5, enter, buttonReportsLog;
    private JComboBox comboTech,comboBuild, subComboDef, subComboAtt;
    private JScrollPane scrollpane1; 
    private JTextArea textarea1;
    
    public Principal(ConnectionDB cdb) {
      this.cdb = cdb;
      setLayout(null);
      setTitle("Menu");
      getContentPane().setBackground(new Color(51,0,51));
      setIconImage(new ImageIcon(getClass().getResource("OGamelogopng.png")).getImage());
     // nombreAdmin = new Bienvenida().user;

      mb = new JMenuBar();
      mb.setBackground(Color.DARK_GRAY);
      setJMenuBar(mb);

      menuOpciones = new JMenu("Opciones");
      menuOpciones.setBackground(new Color(0, 0, 0));
      menuOpciones.setFont(new Font("Andale Mono", 1, 14));
      menuOpciones.setForeground(new Color(255, 255, 255));
      mb.add(menuOpciones);

      menuAcercaDe = new JMenu("Acerca de");
      menuAcercaDe.setBackground(new Color(0, 0, 0));
      menuAcercaDe.setFont(new Font("Andale Mono", 1, 14));
      menuAcercaDe.setForeground(new Color(255, 255, 255));
      mb.add(menuAcercaDe);

      menuColorFondo = new JMenu("Color de fondo");
      menuColorFondo.setFont(new Font("Andale Mono", 1, 14));
      menuColorFondo.setForeground(new Color(0, 0, 0));
      menuOpciones.add(menuColorFondo);

      miBlanco = new JMenuItem("Light");
      miBlanco.setFont(new Font("Andale Mono", 1, 14));
      miBlanco.setForeground(new Color(0, 0, 0));
      menuColorFondo.add(miBlanco);
      miBlanco.addActionListener(this);

      miNegro = new JMenuItem("Dark");
      miNegro.setFont(new Font("Andale Mono", 1, 14));
      miNegro.setForeground(new Color(0, 0, 0));
      menuColorFondo.add(miNegro);
      miNegro.addActionListener(this);

      miMorado = new JMenuItem("Default");
      miMorado.setFont(new Font("Andale Mono", 1, 14));
      miMorado.setForeground(new Color(0, 0, 0));
      menuColorFondo.add(miMorado);
      miMorado.addActionListener(this);

      miVars = new JMenuItem("Configuración");
      miVars.setFont(new Font("Andale Mono", 1, 14));
      miVars.setForeground(new Color(0, 0, 0));
      menuOpciones.add(miVars);
      miVars.addActionListener(this);

      miElCreador = new JMenuItem("El creador");
      miElCreador.setFont(new Font("Andale Mono", 1, 14));
      miElCreador.setForeground(new Color(0, 0, 0));
      menuAcercaDe.add(miElCreador);
      miElCreador.addActionListener(this);

      miSalir = new JMenuItem("Salir");
      miSalir.setFont(new Font("Andale Mono", 1, 14));
      miSalir.setForeground(new Color(0, 0, 0));
      menuOpciones.add(miSalir);
      miSalir.addActionListener(this);

      ImageIcon imagen = new ImageIcon(".\\fotos\\Logo.png");
      labelLogo = new JLabel(imagen);  
      labelLogo.setBounds(10,10,350,100);
      add(labelLogo);
      
      if (cdb.getPlanetName().equals("ALFA CENTAURI")) {
	      ImageIcon imagenPlaneta = new ImageIcon(".\\fotos\\Earth.gif");
	      labelDecorado = new JLabel(imagenPlaneta);  
	      labelDecorado.setBounds(220,100,385,330);
	      add(labelDecorado);
      }else {
    	  ImageIcon imagenPlaneta = new ImageIcon(".\\fotos\\mars.gif");
	      labelDecorado = new JLabel(imagenPlaneta);  
	      labelDecorado.setBounds(220,100,385,300);
	      add(labelDecorado); 
	  }

      labelPlaneta = new JLabel(getCdb().getPlanetName());  
      labelPlaneta.setBounds(380,30,300,50);
      labelPlaneta.setFont(new Font("Andale Mono", 1, 20));
      labelPlaneta.setForeground(new Color(255, 255, 255));
      add(labelPlaneta);

      labelTitle = new JLabel("OPTIONS");
      labelTitle.setBounds(40,105,900,25);
      labelTitle.setFont(new Font("Andale Mono", 0, 15));
      labelTitle.setForeground(new Color(255, 255, 255));
      add(labelTitle);

      opc1 = new JButton("PLANET STATS");
      opc1.setBounds(40,140,140,30);
      opc1.setBackground(new Color(255,255,255));
      opc1.setFont(new Font("Andale Mono", 1, 12));
      opc1.setForeground(new Color(0,0,0));
      opc1.addActionListener(this);
	  add(opc1);
	  
	  opc2 = new JButton("BUID TROOPS");
	  opc2.setBounds(40,180,140,30);
	  opc2.setBackground(new Color(255,255,255));
	  opc2.setFont(new Font("Andale Mono", 1, 12));
	  opc2.setForeground(new Color(0,0,0));
	  opc2.addActionListener(this);
	  add(opc2);
	  
	  opc3 = new JButton("UPGRADE TECH");
	  opc3.setBounds(40,220,140,30);
	  opc3.setBackground(new Color(255,255,255));
	  opc3.setFont(new Font("Andale Mono", 1, 12));
	  opc3.setForeground(new Color(0,0,0));
	  opc3.addActionListener(this);
	  add(opc3);
      
	  opc4 = new JButton("BATTLE REPORS");
	  opc4.setBounds(40,260,140,30);
	  opc4.setBackground(new Color(255,255,255));
	  opc4.setFont(new Font("Andale Mono", 1, 12));
	  opc4.setForeground(new Color(0,0,0));
	  opc4.addActionListener(this);
	  add(opc4);
	  
	  opc5 = new JButton("VIEW THREAD");
	  opc5.setBounds(40,300,140,30);
	  opc5.setBackground(new Color(255,255,255));
	  opc5.setFont(new Font("Andale Mono", 1, 12));
	  opc5.setForeground(new Color(0,0,0));
	  opc5.addActionListener(this);
	  add(opc5);
	  
	  comboBuild = new JComboBox();
	  comboBuild.setBounds(25,350,170,25);
	  comboBuild.setBackground(new java.awt.Color(224, 224, 224));
	  comboBuild.setFont(new java.awt.Font("Andale Mono", 1, 14));
	  comboBuild.setForeground(new java.awt.Color(0, 0, 0));
	  comboBuild.addItem("");
	  comboBuild.addItem("Build attack troops");
	  comboBuild.addItem("Build defense troops");
      add(comboBuild);
      // Desactivado por defecto
      comboBuild.setVisible(false);
      
      subComboAtt = new JComboBox();
      subComboAtt.setBounds(25,350,170,25);
      subComboAtt.setBackground(new java.awt.Color(224, 224, 224));
      subComboAtt.setFont(new java.awt.Font("Andale Mono", 1, 14));
      subComboAtt.setForeground(new java.awt.Color(0, 0, 0));
      subComboAtt.addItem("");
      subComboAtt.addItem("Build Light Hunter");
      subComboAtt.addItem("Build Heavy Hunter");
      subComboAtt.addItem("Build Battle Ship");
      subComboAtt.addItem("Build Armored Ship");
      add(subComboAtt);
      // Desactivado por defecto
      subComboAtt.setVisible(false);
      
      subComboDef = new JComboBox();
      subComboDef.setBounds(25,350,170,25);
      subComboDef.setBackground(new java.awt.Color(224, 224, 224));
      subComboDef.setFont(new java.awt.Font("Andale Mono", 1, 14));
      subComboDef.setForeground(new java.awt.Color(0, 0, 0));
      subComboDef.addItem(" ");
      subComboDef.addItem("Build Missile Louncher");
      subComboDef.addItem("Build Ion Cannon");
      subComboDef.addItem("Build Plasma Cannon");
      add(subComboDef);
      // Desactivado por defecto
      subComboDef.setVisible(false);
      
      comboTech = new JComboBox();
      comboTech.setBounds(25,350,170,25);
      comboTech.setBackground(new java.awt.Color(224, 224, 224));
      comboTech.setFont(new java.awt.Font("Andale Mono", 1, 14));
      comboTech.setForeground(new java.awt.Color(0, 0, 0));
      comboTech.addItem(" ");
      comboTech.addItem("Upgrade Attack Tech");
      comboTech.addItem("Upgrade Defense Tech");
      add(comboTech);
      // Desactivado por defecto
      comboTech.setVisible(false);
  
      
      labelReports = new JLabel("Choose report (1-5)");
      labelReports.setBounds(25,360,150,25);
      labelReports.setFont(new java.awt.Font("Andale Mono", 1, 12));
      labelReports.setForeground(new java.awt.Color(255, 255, 255));
      add(labelReports);
      labelReports.setVisible(false);
      
      labelReportsLog = new JLabel("View Battle development?(S\\n)");
      labelReportsLog.setBounds(25,360,150,25);
      labelReportsLog.setFont(new java.awt.Font("Andale Mono", 1, 12));
      labelReportsLog.setForeground(new java.awt.Color(255, 255, 255));
      add(labelReportsLog);
      labelReportsLog.setVisible(false);
      
      
      datos = new JTextField();
      datos.setBounds(25,390,150,25);
      datos.setBackground(new java.awt.Color(224, 224, 224));
      datos.setFont(new java.awt.Font("Andale Mono", 1, 14));
      datos.setForeground(new java.awt.Color(0, 0, 0));
      add(datos);
      // Desactivado por defecto
      datos.setVisible(false);
     
      enter = new JButton("ENTER");
	  enter.setBounds(45,430,100,20);
	  enter.setBackground(new Color(255,255,255));
	  enter.setFont(new Font("Andale Mono", 1, 9));
	  enter.setForeground(new Color(80,0,80));
	  enter.addActionListener(this);
	  add(enter);
	// Desactivado por defecto
      enter.setVisible(false);
      
      buttonReportsLog = new JButton("VIEW MORE");
      buttonReportsLog.setBounds(45,430,100,20);
      buttonReportsLog.setBackground(new Color(255,255,255));
      buttonReportsLog.setFont(new Font("Andale Mono", 1, 9));
      buttonReportsLog.setForeground(new Color(80,0,80));
      buttonReportsLog.addActionListener(this);
	  add(buttonReportsLog);
	// Desactivado por defecto
	  buttonReportsLog.setVisible(false);
	  
      textarea1 = new JTextArea();
      textarea1.setEditable(false);
      textarea1.setBackground(new Color(224, 224, 224));
      textarea1.setFont(new Font("Andale Mono", 1, 11));
      textarea1.setForeground(new Color(0, 0, 0));
      scrollpane1 = new JScrollPane(textarea1);
      scrollpane1.setBounds(220,100,385,330);
      add(scrollpane1); 
      scrollpane1.setVisible(false);

      labelfooter = new JLabel("©2024 BUGISOFT");
      labelfooter.setBounds(280,445,500,30);
      labelfooter.setFont(new java.awt.Font("Andale Mono", 1, 12));
      labelfooter.setForeground(new java.awt.Color(255, 255, 255));
      add(labelfooter);
      
      addWindowListener(new WindowAdapter() {
    	  public void windowClosing(WindowEvent e) {
			// PUSH
			cdb.conection(3, planet, battle, null, null, false);
			// Apago el timer
			back.stopTimer();
			JOptionPane.showMessageDialog(null,"     Datos guardados");
			
    			
		}
      });
      
    }

    public void actionPerformed(ActionEvent e) {
    	
    	//COLORES
       if (e.getSource() == miBlanco){
           getContentPane().setBackground(new Color(255,255,255));
           opc1.setBackground(Color.gray);
           opc1.setForeground(new Color(255,255,255));
           opc2.setBackground(Color.gray);
           opc2.setForeground(new Color(255,255,255));
           opc3.setBackground(Color.gray);
           opc3.setForeground(new Color(255,255,255));
           opc4.setBackground(Color.gray);
           opc4.setForeground(new Color(255,255,255));
           opc5.setBackground(Color.gray);
           opc5.setForeground(new Color(255,255,255));
           mb.setBackground(Color.gray);
           this.revalidate();
       }
       if (e.getSource() == miNegro){
           getContentPane().setBackground(new Color(0,0,0));
           opc1.setBackground(Color.gray);
           opc1.setForeground(new Color(255,255,255));
           opc2.setBackground(Color.gray);
           opc2.setForeground(new Color(255,255,255));
           opc3.setBackground(Color.gray);
           opc3.setForeground(new Color(255,255,255));
           opc4.setBackground(Color.gray);
           opc4.setForeground(new Color(255,255,255));
           opc5.setBackground(Color.gray);
           opc5.setForeground(new Color(255,255,255));
           mb.setBackground(Color.gray);
           mb.setBackground(Color.DARK_GRAY);
           this.revalidate();
       }
       if (e.getSource() == miMorado){
           getContentPane().setBackground(new Color(51,0,51));
           mb.setBackground(Color.DARK_GRAY);
           this.revalidate();
       }
       
       //RESET CAMPOS
       if (e.getSource() == miVars){	
    	   JOptionPane.showMessageDialog(null,"PROXIMAMENTE");

       }
       // Vuelvo a la GUI Bienvenida
       if (e.getSource() == miSalir){
    	  // Main principal2 = new Main(); ****
//    	   Bienvenida ventanabienvenida = new Bienvenida();
//    	   ventanabienvenida.setBounds(0,0,500,600);
//    	   ventanabienvenida.setVisible(true);
//    	   ventanabienvenida.setResizable(false);
//    	   ventanabienvenida.setLocationRelativeTo(null);
    	   dispose();
    	   // PUSH
			cdb.conection(3, planet, battle, null, null, false);
			JOptionPane.showMessageDialog(null,"     Datos guardados");

       }
       // DATOS CREADOR
       if (e.getSource() == miElCreador){

           JOptionPane.showMessageDialog(null,"Desarrollado por BUGISOFT\n"+
                                              "     GITHUB: xuanor");
       }
       
       //*** OPCIONES JUEGO ***
       // ALERTAS 
       if(back.getAttackComing()) {
    	   AlertThread alert1 = new AlertThread();
    	   alert1.getMessage();

       }
       if (back.isAttacked()) {
    	   AlertAttack alert2 = new AlertAttack();
    	   alert2.getMessage();
    	   back.setAttacked(false);
    	   
       }

       // STATS
       if(e.getSource() == opc1){
    	   // Quitar elemento
    	   comboBuild.setVisible(false);
    	   comboTech.setVisible(false);
    	   subComboAtt.setVisible(false);
     	   subComboDef.setVisible(false);
     	   labelReports.setVisible(false);
    	   datos.setVisible(false);
    	   enter.setVisible(false);
    	   buttonReportsLog.setVisible(false);
    	   labelDecorado.setVisible(false);
    	   
    	   this.revalidate();  
    	   
    	   textarea1.setText(planet.printStats());
    	   textarea1.revalidate();
	       scrollpane1.setVisible(true);   
      	 
       }
       
       // BUILD
       if(e.getSource() == opc2){
    	  flagBuild = true;
    	  flagReport = false;
    	  flagTech = false;
    	   
    	  scrollpane1.setVisible(false);
    	  comboTech.setVisible(false);
    	  subComboAtt.setVisible(false);
    	  subComboDef.setVisible(false);
    	  labelReports.setVisible(false);
    	  buttonReportsLog.setVisible(false);
    	  
    	  labelDecorado.setVisible(true);
    	  comboBuild.setBounds(25,390,170,25);
    	  comboBuild.setVisible(true);
    	  //datos.setText("");
    	  datos.setVisible(false);
    	  enter.setText("ENTER");
    	  enter.setVisible(true);
    	  this.revalidate();
	 	 
      }
      // TECH
      if(e.getSource() == opc3){
    	  flagBuild = false;
    	  flagReport = false;
    	  flagTech = true;
    	  
    	   // Limpiar elementos
    	   comboBuild.setVisible(false);
    	   scrollpane1.setVisible(false);
    	   subComboAtt.setVisible(false);
     	   subComboDef.setVisible(false);
     	   labelReports.setVisible(false);
     	   buttonReportsLog.setVisible(false);
     	  
     	   labelDecorado.setVisible(true);
    	   comboTech.setVisible(true);
    	   datos.setText("");
    	   datos.setVisible(true);
    	   enter.setText("ENTER");
    	   enter.setVisible(true);
    	   this.revalidate();
    	   	 
       }
      
     // BATTLE REPORTS
      if(e.getSource() == opc4){
    	  flagBuild = false;
    	  flagReport = true;
    	  flagTech = false;
    	   // Limpiar elementos
    	   scrollpane1.setVisible(false);
    	   comboBuild.setVisible(false);
    	   comboTech.setVisible(false);
    	   subComboAtt.setVisible(false);
     	   subComboDef.setVisible(false);
     	   
     	   labelDecorado.setVisible(true);
    	   labelReports.setVisible(true);
    	   datos.setText("");
     	   datos.setVisible(true);
     	   enter.setText("ENTER");
     	   enter.setVisible(true);
     	   this.revalidate();
      	 
       }
      
      // VIEW THREADS
      if(e.getSource() == opc5){
    	// Limpiar elementos
	   	   comboBuild.setVisible(false);
	   	   comboTech.setVisible(false);
	   	   subComboAtt.setVisible(false);
	       subComboDef.setVisible(false);
	   	   datos.setVisible(false);
	   	   enter.setVisible(false);
	   	   buttonReportsLog.setVisible(false);
	   	   labelReports.setVisible(false);
	   	   
	   	   this.revalidate();
	   	   
    	  if (back.getAttackComing()) {
    		  textarea1.setText(back.ViewThreat(battle.getEnemyArmy()));
       	      textarea1.revalidate();
	       	  scrollpane1.setVisible(true);
	       	  labelDecorado.setVisible(false);
	       	  this.revalidate();
	       	  
    	  }
    	  else {
    		  JOptionPane.showMessageDialog(null,"   Don't have threads.");
    	  }	
    	  back.setAttackComing(false);
      }
      
      
      if(e.getSource() == buttonReportsLog){
    	 labelReports.setVisible(false);
		 datos.setVisible(false);
		 buttonReportsLog.setVisible(false);
		 
		 enter.setVisible(true);
		 enter.setText("GO BACK");
    	 textarea1.setText(battle.getBattleStats().get(getOpc()-1)[1]);
	     textarea1.revalidate();
	     scrollpane1.setVisible(true);
      }
      // Meter flags para saber de que opcion esta pulsada
      if(e.getSource() == enter){
    	  String textoDatos;
    	  if (flagBuild) { 
        	  String typeBuild = comboBuild.getSelectedItem().toString();
        	  
        	  textoDatos = datos.getText();
        	  // Compruebo que haya rellenado todos los campos
    		  
    		  if(typeBuild.equals("")){
    			  JOptionPane.showMessageDialog(null,"   Choose an option.");
    		 }else {
    			 
    			if (typeBuild.equals("Build attack troops")) {
    				comboBuild.setVisible(false);
    				subComboAtt.setVisible(true);
    				subComboDef.setVisible(false);
    				datos.setText("");
    				datos.setVisible(true);
    				this.revalidate();
    				String typeAtt = subComboAtt.getSelectedItem().toString();
    				
    				// Algun campo vacio
    				if(!(typeAtt.equals("")) || !(textoDatos.equals(""))){
    					int amount = 0;
    					boolean checkNumber = false;
    					// Comprobar que introduce un numero
    					try {
    						amount = Integer.parseInt(textoDatos);
    						checkNumber = true;
    						
    					}catch (Exception x) {
    						JOptionPane.showMessageDialog(null,"  Only numbers are allowed.");
						}
    					
    					//Introduce un input valido
    					if ( checkNumber) {
	    					String[] uniades = {"Build Light Hunter",
	    							"Build Heavy Hunter",
	    							"Build Battle Ship",
	    							"Build Armored Ship"};
	    					int option = 0;
	    					
	    					for (int i = 0;i < uniades.length; i++) {
								if (typeAtt.equals(uniades[i])) {
									option = i+1;
								}
							}
	    					switch(option) {
	    					case 1:
	    						try {
	    							planet.newLightHunter(amount);
	    						}catch (BuildException a) {
	    							JOptionPane.showMessageDialog(null,a.getMessage());
	    						}
	    						break;
	    						
	    					case 2:
	    						try {
	    							planet.newHeavyHunter(amount);
	    						}catch (BuildException b) {
	    							JOptionPane.showMessageDialog(null,b.getMessage());
	    						}
	    						break;
	    						
	    					case 3:
	    						try {
	    							planet.newBattleShip(amount);
	    						}catch (BuildException c) {
	    							JOptionPane.showMessageDialog(null,c.getMessage());
	    						}
	    						break;
	    					case 4:
	    						
	    						try {
	    							planet.newArmoredShip(amount);
	    						}catch (BuildException d) {
	    							JOptionPane.showMessageDialog(null,d.getMessage());
	    						}
	    						break;		
	    				}
					}
				}
    				
    			}else {
    				comboBuild.setVisible(false);
    				subComboDef.setVisible(true);
    				subComboAtt.setVisible(false);
    				datos.setText("");
    				datos.setVisible(true);
    				this.revalidate();
    				String typeDef = subComboDef.getSelectedItem().toString();
    				// Algun campo vacio
    				if(!(typeDef.equals("")) || !(textoDatos.equals(""))){
    					int amount = 0;
    					boolean checkNumber = false;
    					// Comprobar que introduce un numero
    					try {
    						amount = Integer.parseInt(textoDatos);
    						checkNumber = true;
    						
    					}catch (Exception x) {
    						JOptionPane.showMessageDialog(null,"  Only numbers are allowed.");
						}
    					
    					//Introduce un input valido
    					if ( checkNumber) {
	    					String[] uniades = {"Build Missile Louncher",
	    							"Build Ion Cannon",
	    							"Build Battle Ship",
	    							"Build Plasma Cannon"};
	    					int option = 0;
	    					
	    					for (int i = 0;i < uniades.length; i++) {
								if (typeDef.equals(uniades[i])) {
									option = i+1;
								}
							}
	    					switch(option) {
	    					case 1:
	    						try {
	    							planet.newMissileLauncher(amount);
	    						}catch (BuildException a) {
	    							JOptionPane.showMessageDialog(null,a.getMessage());
	    						}
	    						break;
	    						
	    					case 2:
	    						try {
	    							planet.newIonCannon(amount);
	    						}catch (BuildException b) {
	    							JOptionPane.showMessageDialog(null,b.getMessage());
	    						}
	    						break;
	    						
	    					case 3:
	    						try {
	    							planet.newPlasmaCannon(amount);
	    						}catch (BuildException c) {
	    							JOptionPane.showMessageDialog(null,c.getMessage());
	    						}
	    						break;
	    							
	    					}
    					}
    				}
				}	    		
			}  
    	  }
    	  if (flagTech) {
    		  String typeTech = comboTech.getSelectedItem().toString();
         	  textoDatos = datos.getText();
         	  // Compruebo que haya rellenado todos los campos
         	  
     		  if(typeTech.equals("")){
     			  JOptionPane.showMessageDialog(null,"   Choose an option.");
     		 }else {
     			 // Subir Attack tech
     			if (typeTech.equals("Upgrade Attack Tech")) {
     			// Campo vacio
    				if(!(textoDatos.equals(""))){
    					int amount = 0;
    					boolean checkNumber = false;
    					// Comprobar que introduce un numero
    					try {
    						amount = Integer.parseInt(textoDatos);
    						checkNumber = true;
    						
    					}catch (Exception x) {
    						JOptionPane.showMessageDialog(null,"  Only numbers are allowed.");
						}
    					
    					// Accion
    					if (checkNumber) {
    						try {
    							amount*= back.costUpgradeTechnology(planet)[0];
    							planet.upgradeTechnologyAttack(amount);
    							JOptionPane.showMessageDialog(null,"Attack technology upgraded");
    							
    						}catch (ResourceException f) {
    							JOptionPane.showMessageDialog(null, f.getMessage());
    						}
    					}
    				}		
     			}
     			 // Subir Defense tech
     			if (typeTech.equals("Upgrade Defense Tech")) {
     			// Campo vacio
    				if(!(textoDatos.equals(""))){
    					int amount = 0;
    					boolean checkNumber = false;
    					// Comprobar que introduce un numero
    					try {
    						amount = Integer.parseInt(textoDatos);
    						checkNumber = true;
    						
    					}catch (Exception x) {
    						JOptionPane.showMessageDialog(null,"  Only numbers are allowed.");
						}
    					
    					// Accion
    					if (checkNumber) {
    						try {
    							amount *= back.costUpgradeTechnology(planet)[1];
    							planet.upgradeTechnologyDefense(amount);
    							JOptionPane.showMessageDialog(null,"Defense technology upgraded");
    						}catch (ResourceException g) {
    							JOptionPane.showMessageDialog(null, g.getMessage());
    						}
    					}
    				}		
     			}
     		 }
         	  
    	  }
    	  if (flagReport) {
    		  textoDatos = datos.getText();
         	  // Compruebo que haya rellenado todos los campos
    		  if(!(textoDatos.equals(""))) {
    			int opc = 0;
				boolean checkNumber = false;
				// Comprobar que introduce un numero
				try {
					opc = Integer.parseInt(textoDatos);
					
					if (opc <= battle.getBattleStats().size() && opc > 0) {
						checkNumber = true;
					}else {
						JOptionPane.showMessageDialog(null,"  Option out of range.");
					}
				}catch (Exception x) {
					JOptionPane.showMessageDialog(null,"  Only numbers are allowed.");
				}
     			 
				if (checkNumber) {
	     			if (battle.getBattleStats().size() > 0 ) {
	     				textarea1.setText(battle.getBattleStats().get(opc-1)[0]);
	        	    	textarea1.revalidate();
	        	    	scrollpane1.setVisible(true);
	        	    	
	        	    	// Activo menu de paso a paso
	        	    	setOpc(opc);
	        	    	// Activo el boton de log
	        	    	labelDecorado.setVisible(false);
	        	    	labelReports.setVisible(false);
	        	    	datos.setVisible(false);
	        	    	enter.setVisible(false);
	        	    	buttonReportsLog.setVisible(true);
	        	    	this.revalidate();
	        	    	
					}
					else {
						JOptionPane.showMessageDialog(null,"You don't have any battle report available.");
					}
				}

     		 }
    	  }
      }
	}


//    public static void main(String args[]) {
//    	//ConnectionDB x = new ConnectionDB();
//        Principal ventanaPrincipal = new Principal();
//        ventanaPrincipal.setBounds(0,0,640,550);
//        ventanaPrincipal.setVisible(true);
//        ventanaPrincipal.setResizable(false);
//        ventanaPrincipal.setLocationRelativeTo(null);
//    }

    public Planet getPlanet() {
		return planet;
	}

	public void setPlanet(Planet planet) {
		this.planet = planet;
	}

	public Battle getBattle() {
		return battle;
	}

	public void setBattle(Battle battle) {
		this.battle = battle;
	}

	public Main getBack() {
		return back;
	}

	public void setBack(Main back) {
		this.back = back;
	}

	public int getOpc() {
		return opc;
	}

	public void setOpc(int opc) {
		this.opc = opc;
	}

	public ConnectionDB getCdb() {
		return cdb;
	}

	public void setCdb(ConnectionDB cdb) {
		this.cdb = cdb;
	}
	
	
}
