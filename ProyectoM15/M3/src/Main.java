import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main implements Variables{
	private boolean attackComing = false;
	private boolean attacked = false;
	private boolean  start = false;
	private Timer timer;
	

	public void stopTimer() {
		this.timer.cancel();
	}
	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public boolean isAttacked() {
		return attacked;
	}

	public void setAttacked(boolean attacked) {
		this.attacked = attacked;
	}

	public void setAttackComing(boolean flag) {
		this.attackComing = flag;
	}
	
	public boolean getAttackComing() {
		return this.attackComing;
	}

	
	public static void main(String[] args) {
		int[] DBresources;
		
		// Instancio clase principal
		Main principal = new Main();
		//Cargar datos de la bbdd
		ConnectionDB cdb = new ConnectionDB();
		
		// Abro ventana LOGIN
		Bienvenida ventanabienvenida = new Bienvenida();
	 	ventanabienvenida.setBounds(0,0,500,600);
	 	ventanabienvenida.setVisible(true);
	 	ventanabienvenida.setResizable(false);
	 	ventanabienvenida.setLocationRelativeTo(null);
	 	ventanabienvenida.setCdb(cdb);
	 	
	 	while (!principal.isStart()) {
	 		System.out.println(principal.isStart());
		 	principal.setStart(ventanabienvenida.isStartGame());
			// EMPIEZA EL JUEGO SI INTRODUCES UN USER VALIDO EN LA VENTANA DE LOGINs
			if (principal.isStart()) {
				ventanabienvenida.dispose();
				cdb.conection(2, null, null, null, null, false);
				
				// PULL
				DBresources = cdb.getArrayReasources().get(0);
				// Cargar array de Reportes de Batalla
				Battle b = new Battle();
				b.setBattleStats(cdb.getArrayReportesBatalla());
				
				// Cargar datos aqui
				Planet mainPlanet = new Planet(DBresources[2],
						DBresources[3],
						DBresources[0], 
						DBresources[1],
						UPGRADE_BASE_DEFENSE_TECHNOLOGY_DEUTERIUM_COST,
						UPGRADE_BASE_ATTACK_TECHNOLOGY_DEUTERIUM_COST);	
				
				// Me crea mi ejercito y se lo añade al planeta
				principal.createMyArmyInit(mainPlanet, cdb);
				// Empieza el menu
				principal.startTasks(mainPlanet, b);
				
				// Llamo a la interfaz del juego
			   	Principal ventanaPrincipal = new Principal(cdb); 
			    ventanaPrincipal.setPlanet(mainPlanet);
			    ventanaPrincipal.setBattle(b);
			    ventanaPrincipal.setBack(principal);	
			    //ventanaPrincipal.setCdb(cdb);
			    ventanaPrincipal.setBounds(0,0,640,550); 
			    ventanaPrincipal.setResizable(false);
			    ventanaPrincipal.setLocationRelativeTo(null);
			    ventanaPrincipal.setVisible(true);
			}
	 	}
	}
	

	// CREO MI EJERCITO INICIAL

	public void createMyArmyInit(Planet myPlanet, ConnectionDB cdb) {
		ArrayList<MilitaryUnit>[] mainArmy = new ArrayList[7];
		
		//*** UNIDADES DE BASE EN MI EJERCITO ***
//		final int BASE_UNIT_LIGHT_HUNTER = 15;
//		final int BASE_UNIT_HEAVY_HUNTER = 5;
//		final int BASE_UNIT_BATTLE_SHIP = 0;
//		final int BASE_UNIT_ARMORED_SHIP = 1;
//		final int BASE_UNIT_MISSILE_LOUNCHER = 10;
//		final int BASE_UNIT_ION_CANNON = 2;
//		final int BASE_UNIT_PLASMA_CANNON = 0;
		
		ArrayList<int[]> statsHeavytHunter = cdb.getArrayLightHunter();
		ArrayList<int[]> statsLightHunter = cdb.getArrayHeavytHunter();
		ArrayList<int[]> statsBattleShip = cdb.getArrayBattleShip();
		ArrayList<int[]> statsArmoredShip = cdb.getArrayArmoredShip();
		ArrayList<int[]> statsMissileLouncher = cdb.getArrayMissileLouncher();
		ArrayList<int[]> statsIonCannon = cdb.getArrayIonCannon();
	    ArrayList<int[]> statsPlasmaCannon = cdb.getArrayPlasmaCannon();
	    
//	    System.out.println("Prueba = " +  statsHeavytHunter.size());
//	    
//		for (int[] i : statsHeavytHunter) {
//			for (int j : i) {
//				System.out.println(j);
//			}
//			
//		}
	    
		Clases_ataque cAtack = new Clases_ataque();
		// Flota
		ArrayList<MilitaryUnit> arrayLigthHunter = new ArrayList<MilitaryUnit>();
		// myPlanet.newLightHunter(1) hacer asi con nivel de tech ??
		for (int i = 0; i < statsLightHunter.size(); i++) {
			arrayLigthHunter.add(cAtack.new LigthHunter(statsLightHunter.get(i)[0], statsLightHunter.get(i)[1]));
		}
		ArrayList<MilitaryUnit> arrayHeavyHunter = new ArrayList<MilitaryUnit>();
		for (int i = 0; i < statsHeavytHunter.size(); i++) {
			arrayHeavyHunter.add(cAtack.new HeavyHunter(statsHeavytHunter.get(i)[0], statsHeavytHunter.get(i)[1]));
		}
		ArrayList<MilitaryUnit> arrayBattleShip = new ArrayList<MilitaryUnit>();
		for (int i = 0; i < statsBattleShip.size(); i++) {
			arrayBattleShip.add(cAtack.new BattleShip(statsBattleShip.get(i)[0], statsBattleShip.get(i)[1]));
		}
		
		ArrayList<MilitaryUnit> arrayArmoredShip = new ArrayList<MilitaryUnit>();
		for (int i = 0; i < statsArmoredShip.size(); i++) {
			arrayArmoredShip.add(cAtack.new ArmoredShip(statsArmoredShip.get(i)[0], statsArmoredShip.get(i)[1]));
		}
		// Defensas
		Clases_defensa d = new Clases_defensa();
		
		ArrayList<MilitaryUnit> arrayMissileLouncher = new ArrayList<MilitaryUnit>();
		for (int i = 0; i < statsMissileLouncher.size(); i++) {
			arrayMissileLouncher.add(d.new MissileLauncher(statsMissileLouncher.get(i)[0], statsMissileLouncher.get(i)[1]));
		}
		ArrayList<MilitaryUnit> arrayIonCannon = new ArrayList<MilitaryUnit>();
		for (int i = 0; i < statsIonCannon.size(); i++) {
			arrayIonCannon.add(d.new IonCannon(statsIonCannon.get(i)[0], statsIonCannon.get(i)[1]));
		}
		ArrayList<MilitaryUnit> arrayPlasmaCannon = new ArrayList<MilitaryUnit>();
		for (int i = 0; i < statsPlasmaCannon.size(); i++) {
			arrayPlasmaCannon.add(d.new PlasmaCannon(statsPlasmaCannon.get(i)[0], statsPlasmaCannon.get(i)[1]));
		}
		
		mainArmy[0] = arrayLigthHunter;
		mainArmy[1] = arrayHeavyHunter;
		mainArmy[2] = arrayBattleShip;
		mainArmy[3] = arrayArmoredShip;
		
		mainArmy[4] = arrayMissileLouncher;
		mainArmy[5] = arrayIonCannon;
		mainArmy[6] = arrayPlasmaCannon;
		
		myPlanet.setArmy(mainArmy);
	}

	// CREO EJRCITO ENEMIGA
	public  ArrayList<MilitaryUnit>[] createEnemyArmy() {
		// Camabiar metodo a boolean ???
		Battle b = new Battle();
		// Num aleatorio del 0.0 al 10.0
		
//		Para crear el ejército enemigo, dispondremos de unos recursos iniciales, que conforme vayan
//		sucediendo batallas, serán mayores .
//		Iremos creando unidades enemigas aleatoriamente pero con las siguientes probabilidades:
//			● Cazador ligero 35%
//			● Cazador pesado 25%
//			● Nave de Batalla 20%
//			● Acorazado 20%.
//		
//		Mientras tengamos suficientes recursos para crear la unidad con menor coste, es decir, cazador
//		ligero, iremos creando unidades aleatoriamente según las probabilidades anteriores
		Clases_ataque cAtack = new Clases_ataque();
		ArrayList<MilitaryUnit>[] enemyArmy = new ArrayList[7];
		//*** UNIDADES DE BASE EN MI EJERCITO ***
		int BASE_UNIT_LIGHT_HUNTER = 0;
		int BASE_UNIT_HEAVY_HUNTER = 0;
		int BASE_UNIT_BATTLE_SHIP = 0;
		int BASE_UNIT_ARMORED_SHIP = 0;

		
		// Para no gastar el metal al iniciar partida
		int dinero = METAL_BASE_ENEMY_ARMY;
		
		// Mientras haya reservas de Metal  puedes gastar ** AÑADIR DEUTERIO
		
		while ( dinero > (METAL_COST_LIGTHHUNTER) ) {
			
			// Mientras puedas comprar la tropa más barata continua
			if (dinero > METAL_COST_LIGTHHUNTER) {
			
				float num = (float)(Math.random() * 10);
				// Flota
				if (num <= 3.5) {
					BASE_UNIT_LIGHT_HUNTER += 1;
					dinero -= METAL_COST_LIGTHHUNTER;
					
				}else if (num > 3.5 && num <= 6.0) {
					BASE_UNIT_HEAVY_HUNTER += 1;
					dinero -= METAL_COST_HEAVYHUNTER;
				
				}else if (num > 6.0 && num <= 8.0) {
					BASE_UNIT_BATTLE_SHIP += 1;
					dinero -= METAL_COST_BATTLESHIP;
					
				}else if (num > 8.0 && num <= 10.0) {
					BASE_UNIT_ARMORED_SHIP += 1;
					dinero -= METAL_COST_ARMOREDSHIP;
					
				}
				
			}
			
		}
//		System.out.println("metal = " + dinero);
		
		// Añadir plus de tecnologia por cada creacion de ejercito
		
		// Flota
		ArrayList<MilitaryUnit> arrayLigthHunter = new ArrayList<MilitaryUnit>();
		for (int i = 0; i < BASE_UNIT_LIGHT_HUNTER; i++) {
			arrayLigthHunter.add(cAtack.new LigthHunter(ARMOR_LIGTHHUNTER, BASE_DAMAGE_LIGTHHUNTER));
		}
		ArrayList<MilitaryUnit> arrayHeavyHunter = new ArrayList<MilitaryUnit>();
		for (int i = 0; i < BASE_UNIT_HEAVY_HUNTER; i++) {
			arrayHeavyHunter.add(cAtack.new HeavyHunter(ARMOR_HEAVYHUNTER, BASE_DAMAGE_HEAVYHUNTER));
		}
		ArrayList<MilitaryUnit> arrayBattleShip = new ArrayList<MilitaryUnit>();
		for (int i = 0; i < BASE_UNIT_BATTLE_SHIP; i++) {
			arrayBattleShip.add(cAtack.new BattleShip(ARMOR_BATTLESHIP, BASE_DAMAGE_BATTLESHIP));
		}
		
		ArrayList<MilitaryUnit> arrayArmoredShip = new ArrayList<MilitaryUnit>();
		for (int i = 0; i < BASE_UNIT_ARMORED_SHIP; i++) {
			arrayArmoredShip.add(cAtack.new ArmoredShip(ARMOR_ARMOREDSHIP, BASE_DAMAGE_ARMOREDSHIP));
		}
		
//		System.out.println("Hola " + BASE_UNIT_LIGHT_HUNTER + 
//				BASE_UNIT_HEAVY_HUNTER +
//				BASE_UNIT_BATTLE_SHIP +
//			BASE_UNIT_ARMORED_SHIP);
		enemyArmy[0] = arrayLigthHunter;
		enemyArmy[1] = arrayHeavyHunter;
		enemyArmy[2] = arrayBattleShip;
		enemyArmy[3] = arrayArmoredShip;
		
		b.setEnemyArmy(enemyArmy);
		//System.out.println("Longitud flota enemiga = " + enemyArmy[3].size());
		return enemyArmy;
	}
	
	// VER EL EJERCITO QUE TE AMENAZA
	public  String ViewThreat(ArrayList<MilitaryUnit>[] enemyArray) {
		
		// Me llaman desde la opc 5 del menu
		// Miro la army actual del ejercito enemigo desde un objeto Battle
//		ArrayList<MilitaryUnit>[] enemyArray = new ArrayList[7];
//		enemyArray = b.getEnemyArmy();
		
		String datos = String.format("\nNEW THREAT COMING\n"
				   + "\nLigth Hunter%12d\n"
				   + "\nHeavy Hunter%10d\n"
				   + "\nBattle Ship%14d\n"
				   + "\nArmored Ship%10d\n"
				   +"\n",
				   enemyArray[0].size(), 
				   enemyArray[1].size(),
				   enemyArray[2].size(),
				   enemyArray[3].size());
		return datos;
	}

	
	// MENU PRINCIPAL
	public  void startTasks(Planet mainPlanet, Battle b) {
		boolean AtackFlag;
		
		
	    //System.out.println("Estoy en el menu main");
		// Set reportes de batalla BBDD
		
		// Set ejercitos en la battle
		b.setPlanetArmy(mainPlanet.getArmy());
		timer = new Timer();
	    TimerTask taskThreat = new TimerTask() {
	    

	         public void run() {
	        	 System.out.println("\n\nNEW THREAD IS COMMING" );
	        	 // Creo el nuevo ejercito enemigo
	        	 ArrayList<MilitaryUnit>[] army = createEnemyArmy();
	        	 // Añades al obj batalla la army enemiga
	        	 b.setEnemyArmy(army);
	        	 setAttackComing(true);

	         }

	    };
	    
	    TimerTask taskAtack = new TimerTask() {
		    

	         public void run() {
	        	 
	        	 System.out.println("\n\nWE HAVE BEEN ATTACKED!!");
	        	 setAttacked(true);
	        	 b.batalla();
	        	 // Añado total a variable de planet para el push de BBDD
	        	 mainPlanet.setNumberUnits(b.getActualNumberUnitsPlanet());
	        	 // Le añado lo ganado a mis recursos (si no gano es(0,0))
//	        	 System.out.println(mainPlanet.getMetal());
//	        	 System.out.println(mainPlanet.getDeuterium());
	        	 mainPlanet.setMetal( mainPlanet.getMetal() + b.getWasteMetalDeuterium()[0]);
	        	 mainPlanet.setDeuterium(mainPlanet.getDeuterium() + b.getWasteMetalDeuterium()[1]);
//	        	 System.out.println(mainPlanet.getMetal());$
//	        	 System.out.println(mainPlanet.getDeuterium());$
//	        	 System.out.println("Ganancias añadidas !!");$
	         }

	    };
	    
	    TimerTask taskUpdateResources = new TimerTask() {
		    

	         public void run() {
	        	 //System.out.println("**Recursos aumentados**");
	        	 mainPlanet.setMetal((int)(mainPlanet.getMetal()*1.1));
	        	 mainPlanet.setDeuterium((int)(mainPlanet.getDeuterium()*1.1));
	         }

	    };

	    timer.schedule(taskThreat, 10000, 60000);
	    timer.schedule(taskAtack, 15000, 60000);
	    timer.schedule(taskUpdateResources, 60000, 60000);
		
	}

	
	// SUB MENU DE MEJORA DE TECNOLOGIAS
	public int[] costUpgradeTechnology(Planet mainPlanet) {

		// Calculo de lo que aumenta el coste de subir de nivel la def tech 
		int costeUpDef = UPGRADE_BASE_DEFENSE_TECHNOLOGY_DEUTERIUM_COST;
		for (int i = 0; i < mainPlanet.technologyDefense; i++) {
			
			costeUpDef += (costeUpDef * (UPGRADE_PLUS_DEFENSE_TECHNOLOGY_DEUTERIUM_COST / 100));
		}
		
		// Calculo de lo que aumenta el coste de subir de nivel la attack tech
		int costeUpAtt = UPGRADE_BASE_ATTACK_TECHNOLOGY_DEUTERIUM_COST;
		for (int i = 0; i < mainPlanet.technologyAtack; i++) {
			
			costeUpAtt += (costeUpAtt * (UPGRADE_PLUS_ATTACK_TECHNOLOGY_DEUTERIUM_COST / 100));
		}
		return new int[] {costeUpAtt,costeUpDef};
		
	}
	
	
}