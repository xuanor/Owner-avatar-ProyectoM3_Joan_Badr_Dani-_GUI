
import java.util.ArrayList;
import java.util.Scanner;


public class Battle implements Variables{
	// ***VARIABLES***
	
	// Para mostrar info el informe de la batalla
	private boolean planetWins;
	// Para almacenar la flota de nuestro planeta
	private ArrayList<MilitaryUnit>[] planetArmy; 
	// Para almacenar la flota enemiga
	private ArrayList<MilitaryUnit>[] enemyArmy;
	// Fila 1 nuestro ejercito, fila 2 ejercito enemigo
	private ArrayList[][] armies = new ArrayList[2][];
	// Donde guardamos todo el desarrollo de la batalla paso a paso
	private String battleDevelopment = "";
	
	private ArrayList<String[]> battleStats = new ArrayList<String[]>() ;
	//	COSTE de metal de los ejercitos iniciales
	//	[[metal][deuterio],[metal][deuterio]] ,
	//	el 0 es mi ejercito el 1 los enemigos
	private int[][] initialCostFleet;
	
	// n inical de soldados x ejercito
	private int initialNumberUnitsPlanet, initialNumberUnitsEnemy;
	
	// Residuos generados en la batalla [metal, deuterio].***
	private int[] wasteMetalDeuterium = new int[2];
	
	// Muertes x ejercicto
	private int[] enemyDrops, planetDrops = new int[7];
	
	// Array 2x3 perdidas de los 2 recursos
	// Fila 1 recursos de mi ejercito
	// Fila 2 recursos del otro ejercito 
	// recursos ponderados(con su valor) de los dos ejercitos
	
	// Calcular dienero de cada muerto despues de una batalla
	private int[][] resourcesLooses;
	
	// Array 2x7 
	// cada fila representa a un ejercito (0 y 1) 
	// cada columna representa un tipo de soldado y su cantidad incial (0-7)
	private int[][] initialArmies = new int[2][];
	
	// Tipos de unidades que hay 
	private int[] actualNumberUnitsPlanet, actualNumberUnitsEnemy;
	
	private int totalActualUnitsPlanet, totalActualUnitsEnemy;

	// ***METODOS***
	
	public int getInitialNumberUnitsPlanet() {
		return initialNumberUnitsPlanet;
	}

	public ArrayList<String[]> getBattleStats() {
		return battleStats;
	}

	public void setBattleStats(ArrayList<String[]> battleStats) {
		this.battleStats = battleStats;
	}

	public int[] getWasteMetalDeuterium() {
		return wasteMetalDeuterium;
	}

	public void setWasteMetalDeuterium(int[] wasteMetalDeuterium) {
		this.wasteMetalDeuterium = wasteMetalDeuterium;
	}

	public void setInitialNumberUnitsPlanet(int initialNumberUnitsPlanet) {
		this.initialNumberUnitsPlanet = initialNumberUnitsPlanet;
		
	}

	public int getInitialNumberUnitsEnemy() {
		return initialNumberUnitsEnemy;
	}

	public void setInitialNumberUnitsEnemy(int initialNumberUnitsEnemy) {
		this.initialNumberUnitsEnemy = initialNumberUnitsEnemy;
		
	}

	public int[] getActualNumberUnitsPlanet() {
		return actualNumberUnitsPlanet;
	}

	public void setActualNumberUnitsPlanet(int[] actualNumberUnitsPlanet) {
		this.actualNumberUnitsPlanet = actualNumberUnitsPlanet;
	}

	public int[] getActualNumberUnitsEnemy() {
		return actualNumberUnitsEnemy;
	}

	public void setActualNumberUnitsEnemy(int[] actualNumberUnitsEnemy) {
		this.actualNumberUnitsEnemy = actualNumberUnitsEnemy;
	}
	
	
	public int getTotalActualUnitsPlanet() {
		return totalActualUnitsPlanet;
	}

	public void setTotalActualUnitsPlanet(int totalActualUnitsPlanet) {
		this.totalActualUnitsPlanet = totalActualUnitsPlanet;
	}

	public int getTotalActualUnitsEnemy() {
		return totalActualUnitsEnemy;
	}

	public void setTotalActualUnitsEnemy(int totalActualUnitsEnemy) {
		this.totalActualUnitsEnemy = totalActualUnitsEnemy;
	}

	public ArrayList<MilitaryUnit>[] getPlanetArmy() {
		return planetArmy;
	}

	public void setPlanetArmy(ArrayList<MilitaryUnit>[] planetArmy) {
		this.planetArmy = planetArmy;
		this.armies[0] = planetArmy;
	}

	public ArrayList<MilitaryUnit>[] getEnemyArmy() {
		return enemyArmy;
	}

	public void setEnemyArmy(ArrayList<MilitaryUnit>[] enemyArmy) {
		this.enemyArmy = enemyArmy;
		this.armies[1] = enemyArmy;
	}

	public ArrayList[][] getArmies() {
		return armies;
	}

	public void setArmies(ArrayList[][] armies) {
		this.armies = armies;
		this.planetArmy = armies[0];
		this.enemyArmy = armies[1];
	}
	
	public int[][] getInitialArmies(){
		return initialArmies;
		
	}
	
	public void setInitialArmies(int[][] ints) {
		this.initialArmies = ints;
	}
	
	//  Mostrar batalla paso a paso.
	public int[] getEnemyDrops() {
		return enemyDrops;
	}

	public void setEnemyDrops(int[] ints) {
		this.enemyDrops = ints;
	}

	public int[] getPlanetDrops() {
		return planetDrops;
	}

	public void setPlanetDrops(int[] ints) {
		this.planetDrops = ints;
	} 
	
	public int[][] getResourcesLooses() {
		return resourcesLooses;
	}

	public void setResourcesLooses(int[][] resourcesLooses) {
		this.resourcesLooses = resourcesLooses;
	}
	
	
	public int[][] getInitialCostFleet() {
		return initialCostFleet;
	}

	public void setInitialCostFleet(int[][] initialCostFleet) {
		this.initialCostFleet = initialCostFleet;
	}
	
	
	public boolean isPlanetWins() {
		return planetWins;
	}

	public void setPlanetWins(boolean planetWins) {
		this.planetWins = planetWins;
	}
	
	//resumen, battles será el número de batallas que hayamos acumulado ****
	public void makeBattleReport() {
		//String[] informes = new String[5];
		String informeStats = "BATTLE STATISTICS\n";
		String informePasoApaso = "";
		// Hacer un array donde meter los datos de las tropas
		// * Informe stadisticas
		// Tropas inciales y sus bajas
		informeStats += String.format("\n%-16s%12s%12s%26s%8s%12s\n"
				   + "\n%-16s%12d%12d"
				   + "%20s%14d%12d"
				   + "\n%-16s%12d%12d"
				   + "%20s%14d%12d"
				   + "\n%-16s%12d%12d"
				   + "%20s%14d%12d"
				   + "\n%-16s%12d%12d"
				   + "%20s%14d%12d"
				   + "\n%-16s%12d%12d"
				   + "\n%-16s%12d%12d"
				   + "\n%-16s%12d%12d"
				   +"\n",
				   "Army Planet","Units","Drops","Initial Army Enemy", "Units", "Drops",
				   "Ligth Hunter",getInitialArmies()[0][0], getPlanetDrops()[0],
				   "Ligth Hunter",getInitialArmies()[1][0],getEnemyDrops()[0],
				   "Heavy Hunter",getInitialArmies()[0][1], getPlanetDrops()[1],
				   "Heavy Hunter",getInitialArmies()[1][1],getEnemyDrops()[1],
				   "Battle Ship",getInitialArmies()[0][2], getPlanetDrops()[2],
				   "Battle Ship ",getInitialArmies()[1][2],getEnemyDrops()[2],
				   "Armored Ship",getInitialArmies()[0][3], getPlanetDrops()[3],
				   "Armored Ship",getInitialArmies()[1][3],getEnemyDrops()[3],
				   "Missile Launcher",getInitialArmies()[0][4], getPlanetDrops()[4],
				   "Ion Cannon",getInitialArmies()[0][5], getPlanetDrops()[5],
				   "Plasma Cannon",getInitialArmies()[0][6], getPlanetDrops()[6]);
		
		informeStats+= "\n\n**************************************************************************************";
	    
		// Costes de cada army en metal y deuterio
		informeStats += String.format("\n%-30s%30s\n"
				   + "\n%-14s%-31d%-14s%-14d"
				   + "\n%-14s%-31d%-14s%-14d",
				   "Cost Army planet","Cost Army Enemy",
				   "Metal:", getInitialCostFleet()[0][0],
				   "Metal:", getInitialCostFleet()[1][0],
				   "Deuterio:", getInitialCostFleet()[0][1],
				   "Deuterio:", getInitialCostFleet()[1][1]);
			
		informeStats+= "\n\n**************************************************************************************";
		
		// Perdidas de cada army en metal y deuterio
		informeStats += String.format("\n%-32s%30s\n"
				   + "\n%-14s%-31d%-14s%-14d"
				   + "\n%-14s%-31d%-14s%-14d"
				   + "\n%-14s%-31d%-14s%-14d",
				   "Losses Army planet","Losses Army Enemy",
				   "Metal:", getResourcesLooses()[0][0],
				   "Metal:", getResourcesLooses()[1][0],
				   "Deuterio:", getResourcesLooses()[0][1],
				   "Deuterio:",getResourcesLooses()[1][1],
				   "Weighted:", getResourcesLooses()[0][2],
				   "Weighted:",getResourcesLooses()[1][2]);
		
	    informeStats+= "\n\n**************************************************************************************";
		
	    // Residuos generados, quien ha ganado la batalla y si consigue residuos o no
	    if (isPlanetWins()) {
		    informeStats += String.format("\nWaste Generated:\n"
					   + "\n%-14s%-31d"
					   + "\n%-14s%-31d",
					   "Metal:", getWasteMetalDeuterium()[0],
					   "Deuterio:", getWasteMetalDeuterium()[1]);
		 
		    informeStats+= "\nBattle Winned by Planet, We Collect Rubble"
		    		+ "\n\n#######################################################################################";
	    }else {
	    	informeStats+= "\nBattle Winned by Enemy, We Dont't Collect Rubble"
		    		+ "\n\n#######################################################################################";
	    }
	    // Lista de informes 
	    
    	this.battleStats.add(new String[] {informeStats, getBattleDevelopment()});
    	if (battleStats.size() > 5) {
    		// Borras el mas viejo
	    	this.battleStats.remove(0);
	    }
		
	}
	
	public String getBattleDevelopment() {
		return battleDevelopment;
	}
		
	// Devuelvo las % de escojer cada unidad de cada ejercito
	private int[] getChanceUnits(ArrayList<MilitaryUnit>[] army) {

		int[] chanceMyArmy = new int[7];
		
		if (army[4] == null) {
			chanceMyArmy = CHANCE_ATTACK_ENEMY_UNITS;
			
		}
		else {
			// Calcular que porcentaje representa cada unidad en el army
			//100*(Cantidad de cazadores ligeros ) / (total de unidades) = 9000/200 = 45 %  (getInitialArmies()[0][i])
			
			//System.out.println("&& PORCENTAJES = ");
			for (int i= 0; i< chanceMyArmy.length; i++) {
//					if (!(armies[0][i] == null)) {	
					int percent = (int)(100*(army[i].size()) / getTotalActualUnitsPlanet() );
					chanceMyArmy[i] = percent;
					//System.out.print(chanceMyArmy[i] + " * ");
//					}else {
//						chanceMyArmy[i] = 0;
//					}
			}
//			chanceMyArmy = CHANCE_ATTACK_PLANET_UNITS;
		}
		return chanceMyArmy;
	}
	
	public void updateUnitDrops() {
		// tropas iniciales - tropas actuales
		int[] enemyDrops = new int[7];
		int[] planetDrops = new int[7];
		
		
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < getInitialArmies()[i].length; j ++) {
				
				if (i == 0) {
					planetDrops[j] = getInitialArmies()[i][j] - getArmies()[i][j].size();
					//System.out.println("Units del paneta = " + getArmies()[i][j].size());
					//System.out.println("\nPlanet drops = " + planetDrops[j] );
				}else {
					if (!(getArmies()[i][j] == null)) {
						enemyDrops[j] = getInitialArmies()[i][j] - getArmies()[i][j].size();
					}
					//System.out.println("\nEnemy drops = " + enemyDrops[j] );
				}
			}
		}
		
		setEnemyDrops(enemyDrops);
		setPlanetDrops(planetDrops);
	}
	 
	// Para generar el array de residuos.
	public void updateWasteMetalDeuterium() {
		int totalDeu = 0;
		int totalMet = 0;
		int [] resources = new int[2];
		int [] drops = getPlanetDrops();
		int[] vars = {CHANCE_GENERATNG_WASTE_LIGTHHUNTER,
				CHANCE_GENERATNG_WASTE_HEAVYHUNTER,
				CHANCE_GENERATNG_WASTE_BATTLESHIP,
				CHANCE_GENERATNG_WASTE_ARMOREDSHIP,
				CHANCE_GENERATNG_WASTE_MISSILELAUNCHER,
				CHANCE_GENERATNG_WASTE_IONCANNON,
				CHANCE_GENERATNG_WASTE_PLASMACANNON};
		
		// Version con tropas
//		for (ArrayList<MilitaryUnit> unitGroup : getPlanetArmy()) {
//			for (MilitaryUnit unit : unitGroup) {
//				int randomNum = (int)(Math.random()*100);
//				// Si suena la flauta... calcula
//				if (randomNum < unit.getChanceGeneratinWaste()) {
//					totalMet += unit.getMetalCost() * PERCENTATGE_WASTE;
//					totalDeu += unit.getDeuteriumCost()  * PERCENTATGE_WASTE; 
//				}
//			}
//		
//		}
		
		for (int units = 0; units< drops.length; units++) {
//			System.out.println("Drops = " + drops[units]);
			if (units > 0) {
				
				for(int unit = 0; unit < drops[units]; unit++) {
					int randomNum = (int)(Math.random()*100);
					// Si suena la flauta... calcula
					if (randomNum < vars[units]) {
						totalMet += (METAL_COST_UNITS[units] * PERCENTATGE_WASTE) / 100;
						totalDeu += (DEUTERIUM_COST_UNITS[units]  * PERCENTATGE_WASTE) / 100; 
					}
				}
			}
		}
		resources[0] = totalMet;
		resources[1] = totalDeu;
		
		setWasteMetalDeuterium(resources);
//		System.out.println("TOTAL METAL RECUPERADO = " + totalMet);
//		System.out.println("TOTAL DEUTERIO RECUPERADO = " + totalDeu);
	}
	
	// Para calcular costes de las flotas iniciales.
	public void fleetResourceCost(ArrayList<MilitaryUnit>[][] army) {
		int[][] resourcesCost = new int[2][2];
		
		// Ejercito
		for (int pos = 0; pos < 2; pos++) {	
			int costeMetal = 0;
			int costeDeuterio = 0;
			int totalMetal = 0;
			int totalDeuterio = 0;
			for (int j = 0; j < army[pos].length; j++) {
				if (!(army[pos][j] == null)) {
					for (int i = 0; i < army[pos][j].size(); i++) {
						
							// Suma de cada unidad de un grupo
							costeMetal += army[pos][j].get(i).getMetalCost();
							costeDeuterio += army[pos][j].get(i).getDeuteriumCost();
						
					}// Suma de cada grupo
					totalMetal += costeMetal;
					totalDeuterio += costeDeuterio;
				}
			}
			//System.out.println("Pos = " + pos);
			resourcesCost[pos][0] = totalMetal;
			resourcesCost[pos][1] = totalDeuterio;
//			System.out.println("\nTotal metal = " + resourcesCost[pos][0]);$
//			System.out.println("\nTotal deuterio = " + resourcesCost[pos][1]);$
		}
		setInitialCostFleet(resourcesCost);
	}
		
	// Calcular coste de las unidades perdidas
	public void updateResourcesLooses() {

		int[][] armiesDrops = new int[2][7];
		int[][] resourceLooses = new int[2][3];
		
		// {perdidas metal planeta, perdidas deuterio planeta, perdidas metal planeta + 5* perdidas deuterio planeta}
		armiesDrops[0] = getPlanetDrops();
		armiesDrops[1] = getEnemyDrops();
		
		// Ejercito
		for (int pos = 0; pos < 2; pos++) {	
			int costeMetal = 0;
			int costeDeuterio = 0;
			int totalMetal = 0;
			int totalDeuterio = 0;
			for (int j = 0; j < armiesDrops[pos].length; j++) {
				if (armiesDrops[pos][j] != 0) {
					for (int i = 0; i < armiesDrops[pos][j]; i++) {
						
							// Suma de cada unidad de un grupo
							costeMetal += METAL_COST_UNITS[j];
							costeDeuterio += DEUTERIUM_COST_UNITS[j];
						
					}// Suma de cada grupo
					totalMetal += costeMetal;
					totalDeuterio += costeDeuterio;
				}
			}
			//initialCostFleet
			resourceLooses[pos][0] = totalMetal;
			resourceLooses[pos][1] = totalDeuterio;
			resourceLooses[pos][2] = totalMetal + (totalDeuterio * 5);
//			System.out.println("\nTotal ponderado = " + resourceLooses[pos][2]);$
		}
		setResourcesLooses(resourceLooses);
	}
	// Para calcular unidades de cada flota 
	public void actualFleetNumber(boolean initFlag) {
		// Calcular total de unidades
		int totalGrupo = 0;
		int totalUnidades = 0;
		// Sumar total de tropas ARMIES ***
		ArrayList[][] armies = getArmies();
//		System.out.println("Longitud armies = " + armies.length);
		int[][] units = new int[2][7];
		
		for (int i = 0; i < 2; i++) {	
			totalGrupo = 0;
			totalUnidades = 0;
			for (int j = 0; j < armies[i].length; j++) {
				// Por cada fila de la columna armies[i]
				if (!(armies[i][j] == null)) {
					totalGrupo += armies[i][j].size();
					units[i][j] = armies[i][j].size();;
					
				}
			}
			totalUnidades = totalGrupo;
			
			if (initFlag) {
				if (i==0) {
					this.initialNumberUnitsPlanet = totalUnidades;
				}else {
					this.initialNumberUnitsEnemy = totalUnidades;
				}
				setInitialArmies(units);
			}else {
				
				if (i==0) {
					setActualNumberUnitsPlanet(units[i]);
					setTotalActualUnitsPlanet(totalUnidades);
					
				}else {
					setActualNumberUnitsEnemy(units[i]);
					setTotalActualUnitsEnemy(totalUnidades);
				}
			}
		}
		
		//System.out.println("\nPLANETA= "+ initialNumberUnitsPlanet + "\nENEMIGOS= "+ initialNumberUnitsEnemy);

	} 
	
	// Para calcular los porcentajes de unidades que quedan respecto los ejércitos iniciales.
	public int remainderPercentageFleet(ArrayList<MilitaryUnit>[] army){
		int totalUnits = 0; 
		int resultado;
		for (ArrayList<MilitaryUnit> arrayList : army) {
			if (!(arrayList == null)){
				totalUnits += arrayList.size();
			}
			
		}
		// Calculo el porcentaje (tiene que ser con los totales inciales)
		if (army[4] == null) {
			resultado = totalUnits*100/getInitialNumberUnitsEnemy();
		}else {
			resultado = totalUnits*100/getInitialNumberUnitsPlanet();
		}
//		System.out.println("Porcentaje de ejercito = " + resultado);$
		return resultado;
	}
	
	//	Para que dado un ejército, nos devuelva el grupo defensor, 
	//	0-3 en el caso de la flota enemiga, 0-6 en el caso del ejército de nuestro planeta. REVISAR A FONDO**
	public int getGroupDefender(ArrayList<MilitaryUnit>[] army) {
		// Calculo probabilidades
		int[] chanceMyArmy = getChanceUnits(army);
		//ArrayList<int[]> posVacias = new ArrayList<int[]>();
		// Por ultimo escojeremos que unidad sera el defensor dentro de ese grupo
		// EJERCITO > UNIDADES > NAVE
		int cont = 0;
		boolean checkSize = false;
//		int contBucle = 0;
		
		while (!checkSize) {
			int randomNum = (int)(Math.random()*98);
			//System.out.println("*** Numero random defender = " + randomNum);$
			int sumTotal = 0;
			cont = -1;
			// Ecoger grupo def
			for (int i = 0; i< chanceMyArmy.length;i++) {
				sumTotal += chanceMyArmy[i];
				if (sumTotal <=randomNum) {
					cont ++;
					//System.out.println("CONTADOR DEFENDER = " + cont);
				}else {
					break;
				}
			}
			if (cont == -1) {
				cont = 0;
			}
			// Revisar **
			else if (cont != 6){
				cont++;
			}
			//|| contBucle == 6
			if (!(army[cont] == null) && army[cont].size() > 0 ) {
				checkSize = true;
			}
//			else {
//				System.out.println("\nTropa de defensa erronea " + cont + " \n");
//				contBucle ++;
//				if (army[cont].size() == 0) {
//					army[cont] = null;
//				}
//				if (!(army[cont] == null)) {
//					// Borro ese arrayList
//					System.out.println("\n\nSize del arrayList defender = " + army[cont].size() + "\n");
//			}
		}
		return cont;
	}
	
	// Que nos servirán para escoger el GRUPO atacante (a que tropa ataca) tanto del planeta...
	public int getPlanetGroupAttacker() {
		// Añadir check size aquiS
		boolean checkSize = false;
		int cont = 0;
		
		while (!checkSize) {
			int randomNum = (int)(Math.random()*98);			
			int sumTotal = 0;
			cont = -1;
			
			// Ecoger grupo atack
			for (int i = 0;i< CHANCE_ATTACK_PLANET_UNITS.length;i++) {
				sumTotal += CHANCE_ATTACK_PLANET_UNITS[i];
				
				if (sumTotal <= randomNum) {
					cont ++;
				}
			}
			
			if (cont == -1) {
				cont = 0;
			}
			else {
				cont++;
			}
			//Comporbar que ese arraylist tiene tantas posiciones.
			//System.out.println("1 " + getPlanetArmy());
			if (!(getPlanetArmy()[cont] == null) && getPlanetArmy()[cont].size() > 0) {
				checkSize = true;
			}
//			else {
////				// Borro ese arrayList ( no se si funcionara esto con el GET)
////				if (getPlanetArmy()[cont].size() == 0) {
////					getPlanetArmy()[cont] = null;
////				}
//				System.out.println("\nTropa de ataque 1 erronea\n");	
//			}
		}
		return cont;
	}		
	
	//... como de la flota enemiga.
	public int getEnemyGroupAttacker() {
		// Añadir check size aqui
		boolean checkSize = false;
		int cont = 0;
//		int contBucle =0;
		
		while (!checkSize) {
			int randomNum = (int)(Math.random()*98);			
			int sumTotal = 0;
			cont = -1;
			
			// Ecoger grupo atack
			for (int i = 0;i< CHANCE_ATTACK_ENEMY_UNITS.length;i++) {
				sumTotal += CHANCE_ATTACK_ENEMY_UNITS[i];
				
				if (sumTotal <= randomNum) {
					cont ++;
				}
			}
			
			if (cont == -1) {
				cont = 0;
			}
			else {
				cont++;
			}
			//|| contBucle == 6
			if (!(getEnemyArmy()[cont] == null) && getEnemyArmy()[cont].size() > 0 ) {
				checkSize = true;
			}
//			else {
//				// Borro ese arrayList ( no se si funcionara esto con el GET)
//				if (getEnemyArmy()[cont].size() == 0) {
//					getEnemyArmy()[cont] = null;
//				}
//				System.out.println("\nTropa de ataque 2 erronea\n");$
//				contBucle ++;
//			}
			//System.out.println("\n\nSize del arrayList Enemigo = " + getEnemyArmy()[cont].size() + "\n");
		}	
		return cont;
	}

	//  Restablecer los blindajes de nuestro ejército. 
	public void resetArmyArmor() {
		for (ArrayList<MilitaryUnit> group : getPlanetArmy()) {
			for (MilitaryUnit unit : group) {
				unit.resetArmor();
			}
		}
	}
	
	public void batalla() {
		//System.out.println("ESTIC A LA BATALLA");
		ArrayList<MilitaryUnit>[][] armies = this.armies;
		// Contar total de tropas iniciales
		actualFleetNumber(true);
		actualFleetNumber(false);
		// Calculos los costes de las tropas inciales para el informe
		fleetResourceCost(armies);
		
		boolean zeroUnits = false;
		boolean chanceAttack = false;
		boolean playBattle = true;
		boolean checkSize = false;
		// Si no tienes tropas
		if (getTotalActualUnitsPlanet() <= 1) {
			//System.out.println("No tine suficientes tropas para atacar, pierdes la batalla");$
			// Le quito dinero por perder ( revisar porque le suma aunque pierda la batalla)
//			int [] updateCosts = {getWasteMetalDeuterium()[0] + 20000
//					,getWasteMetalDeuterium()[1] + 4000};
//			setWasteMetalDeuterium(updateCosts); 
			playBattle = false;
		}
		// BATALLA
		// Escoget quien ataca primero
		// Luego llamas a un ataket o a otro en funcion de eso y el defender igual
		// de la poisicion que te devuelven escojes una tropa aleatoria 
		// El atacante le va metiendo ostias al defensor hasta que no vueva a repetir
		// Despues volvemos a hacer lo mismo pero invirtiendo roles (defensor--> atacante y viceversa)
		// Cuando uno se muerte lo borramos y añadimos +1 al contador de muertes de se grupo (enemyDarps[indice grupo] += 1)
		// Despues de la batalla calculamos coste de perdidas por equipo...
		int empieza = (int)(Math.random()*2);
		int cont = 0;
		int auxiliar;
		battleDevelopment = "";
		while (playBattle) {
			
			//System.out.println("****EMPIEZA = " + empieza);$
			// Aqui guardo el grupo att y el grupo def de cada jugada
			ArrayList<MilitaryUnit> attDef = new ArrayList<MilitaryUnit>();
			
			// EL ORDEN DE ATTDEF ESTABA MAL Y EL CONT Y RANDOMNUM DE LA PELEA TAMBIEN **
			int randomNum;
			// pos del grupo att
			if (empieza == 1) {
				auxiliar = 0;
				
			}else {
				auxiliar = 1;
			}
			// Grupo atack
			battleDevelopment += "\n\n********************CHANGE ATTACKER********************\n";
			if (armies[auxiliar][4] == null) {
				//System.out.println("===Grupo atacker es enemigo===");
				cont = getEnemyGroupAttacker();
				randomNum = (int)(Math.random()*armies[1][cont].size());	
				attDef.add(armies[1][cont].get(randomNum));
				battleDevelopment+= "Attacks fleet enemy: ";
			}else {
				cont = getPlanetGroupAttacker();
				randomNum = (int)(Math.random()*armies[0][cont].size());	
				attDef.add(armies[0][cont].get(randomNum));
				battleDevelopment+= "Attacks Planet: ";
			}
			battleDevelopment+= attDef.get(0).getUnitName() + " attacks ";
			
			// Grupo def
			cont = getGroupDefender(armies[empieza]);
			// Añado grupo enemigo
			randomNum = (int)(Math.random()*armies[empieza][cont].size());
			//System.out.println("SIZE DEL ARMY DEFENDER = " + armies[empieza%2][cont].size());
			attDef.add(armies[empieza][cont].get(randomNum));	
			battleDevelopment+= attDef.get(1).getUnitName() + "\n";

						
			// Atacante resta a defensa lo que tiene de poder de ataque
			// Hasta 0 o -0, que se eliminara
			// PELEA DE DOS
			int chanceWaste;
			boolean pelea = true;
			
			while (pelea) {
//				System.out.println("\nGuantazo\n");$
				//attDef.get(1).getActualArmor() > 0 &&  attDef.get(0).getActualArmor() > 0
				// El atacante le pega al defensor
				attDef.get(1).tekeDamage(attDef.get(0).attack());
				
				//Fuerza del golpe att
				battleDevelopment+= attDef.get(0).getUnitName() 
						+" generates the damage = " 
						+ attDef.get(0).attack() + "\n";
				// Armadura del def
				battleDevelopment+= attDef.get(1).getUnitName() 
						+" stays with armor = " 
						+ attDef.get(1).getActualArmor() + "\n";
		
				//System.out.println("DEFENSOR = " + attDef.get(1).getActualArmor());$
				//System.out.println("ATACANTE = " + attDef.get(0).getActualArmor());$
				
				if (attDef.get(1).getActualArmor() <= 0 ) {
					// Lo elimino
//					System.out.println("Tu tropa ha muerto");$
					battleDevelopment+= "we eliminate " + attDef.get(1).getUnitName() + "\n";
					// Sumar +1 a las bajas **** hacer la resta luego
					//setPlanetDrops(getPlanetDrops()[cont]+1, cont);
					// Borrar tropa del grupo defensor
					armies[empieza][cont].remove(randomNum);
					
					// Sincronizo la variable
					setArmies(armies);
//					System.out.println("ARMY 1 " + armies[0]);
//					System.out.println("ARMY 2 " + armies[1]);
					//Añado un nuevo defensor
					//System.out.println("3 " + armies[empieza%2]);
					
					// Calcular cada iteracion los numeros act de tropas
					actualFleetNumber(false);
//					if (getTotalActualUnitsPlanet() <= 1) {
////						System.out.println("No tine suficientes tropas para atacar, pierdes la batalla");$
//						// Le quito dinero por perder simepre que no sea < 0 
////						if (getWasteMetalDeuterium()[0] > 0 && getWasteMetalDeuterium()[1] > 0) {
////							int [] updateCosts = {getWasteMetalDeuterium()[0] - 20000
////									,getWasteMetalDeuterium()[1] - 4000};
////							setWasteMetalDeuterium(updateCosts); 
////					}
//						pelea = false;
//						playBattle = false;
//					}else {
						
						attDef.remove(attDef.get(1));
						cont = getGroupDefender(armies[empieza]);
						// Añado grupo enemigo
						randomNum = (int)(Math.random()*armies[empieza][cont].size());
						attDef.add(armies[empieza][cont].get(randomNum));	
					//}
					
				// Comprovar que tengo + del 20% de mis tropas (meter en pelela***)
				if (remainderPercentageFleet(armies[0]) <= PERCENTATGE_LIMIT_BATTLE_UNITS
						|| remainderPercentageFleet(armies[1]) <= PERCENTATGE_LIMIT_BATTLE_UNITS) {
					// Se acaba la batalla y se hace recuento
//						System.out.println("Fin pelea, menos del 20%");$
					pelea = false;
					playBattle = false;
					setPlanetWins(false);
				}	
					
				}
				if (pelea) {
					// Mirar si vuleve a pegar
					int chance = attDef.get(0).getChanceAttackAgain();
					if ((int)(Math.random() *100 ) > chance) {
						pelea = false;
//						System.out.println("No vuelve a atacar, nueva pelea");$
					}
				}
			}if (playBattle) {
//				// Comprovar que tengo + del 20% de mis tropas (meter en pelela***)
//				if (remainderPercentageFleet(armies[0]) <= PERCENTATGE_LIMIT_BATTLE_UNITS
//						|| remainderPercentageFleet(armies[1]) <= PERCENTATGE_LIMIT_BATTLE_UNITS) {
//					// Se acaba la batalla y se hace recuento
////					System.out.println("Fin pelea, menos del 20%");$
//					playBattle = false;
//				}
				// Cambia el turno
				if (empieza == 1) {
					empieza = 0;
					
				}else {
					empieza = 1;
				}	
			}
		}
		battleDevelopment += "\n";
		// Fin batalla
//		System.out.println("**Ahora hare un recuento de los recursos**");
		
		if (!(zeroUnits)) {
			// Calcular bajas
			updateUnitDrops();
			
			// Recursor perdidos
			updateResourcesLooses();
	
			// El que menos recuros pierde GANA 
			if (getResourcesLooses()[0][2] <= getResourcesLooses()[1][2]) {
//				System.out.println("\n¡El planeta ha ganado!");$
				setPlanetWins(true);
				// Calcular residuos que me quedo 
				updateWasteMetalDeuterium();
				
			} else {
//				System.out.println("\n¡El enemigo ha ganado!");$
				setPlanetWins(false);
				// Limpio el array de residuos para el informe
				setWasteMetalDeuterium(new int[2]);
			}
		}
		makeBattleReport();
	}
}

