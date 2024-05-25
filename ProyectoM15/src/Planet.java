import java.util.ArrayList;

public class Planet implements Variables{
	int technologyDefense;
	int technologyAtack;
	int metal;
	int deuterium;
	int upgradeDefenseTechnologyDeuteriumCost;
	int upgradeAttackTechnologyDeuteriumCost;
	ArrayList<MilitaryUnit>[] army = new ArrayList[7];
	private int[] NumberUnits;
	
	// Constructor
	public Planet(int technologyDefense, int technologyAtack, int metal, int deuterium,
		int upgradeDefenseTechnologyDeuteriumCost, int upgradeAttackTechnologyDeuteriumCost) {

		super();
		this.technologyDefense = technologyDefense;
		this.technologyAtack = technologyAtack;
		this.metal = metal;
		this.deuterium = deuterium;
		this.upgradeDefenseTechnologyDeuteriumCost = upgradeDefenseTechnologyDeuteriumCost;
		this.upgradeAttackTechnologyDeuteriumCost = upgradeAttackTechnologyDeuteriumCost;
	}

		
	// Getters and setters
	
	public int getTechnologyDefense() {
		return technologyDefense;
	}

	public int[] getNumberUnits() {
		return NumberUnits;
	}


	public void setNumberUnits(int[] numberUnits) {
		NumberUnits = numberUnits;
	}


	public void setTechnologyDefense(int technologyDefense) {
		this.technologyDefense = technologyDefense;
	}

	public int getTechnologyAtack() {
		return technologyAtack;
	}

	public void setTechnologyAtack(int technologyAtack) {
		this.technologyAtack = technologyAtack;
	}

	public int getMetal() {
		return metal;
	}

	public void setMetal(int metal) {
		this.metal = metal;
	}

	public int getDeuterium() {
		return deuterium;
	}

	public void setDeuterium(int deuterium) {
		this.deuterium = deuterium;
	}

	public int getUpgradeDefenseTechnologyDeuteriumCost() {
		return upgradeDefenseTechnologyDeuteriumCost;
	}

	public void setUpgradeDefenseTechnologyDeuteriumCost(int upgradeDefenseTechnologyDeuteriumCost) {
		this.upgradeDefenseTechnologyDeuteriumCost = upgradeDefenseTechnologyDeuteriumCost;
	}

	public int getUpgradeAttackTechnologyDeuteriumCost() {
		return upgradeAttackTechnologyDeuteriumCost;
	}

	public void setUpgradeAttackTechnologyDeuteriumCost(int upgradeAttackTechnologyDeuteriumCost) {
		this.upgradeAttackTechnologyDeuteriumCost = upgradeAttackTechnologyDeuteriumCost;
	}

	public ArrayList<MilitaryUnit>[] getArmy() {
		return army;
	}

	public void setArmy(ArrayList<MilitaryUnit>[] army) {
		this.army = army;
	}
	
	// Mas metodos
	public void upgradeTechnologyDefense(int coste) throws ResourceException{
		if (this.deuterium - coste > 0) {
			// Añadir desde el setter de la var teckDef?
			this.technologyDefense += 1;
			this.deuterium -= coste;
		}else {
			throw new ResourceException();
		}
	}

	public void upgradeTechnologyAttack(int coste)  throws ResourceException{
		if (this.deuterium - coste > 0) {
			this.technologyAtack += 1;
			this.deuterium -= coste;
		}else {
			throw new ResourceException("You don't have enough deuterium");
		}
	}
	
	// Nuevas flotas de ataque
	public void newLightHunter(int n) throws BuildException{
		int armor;
		int atack;
		Clases_ataque cAtack = new Clases_ataque();
		
		// Formula de nivel de tecnologia para las stats
		// 1000 + (2*5)1000/100 = 1100
		armor = ARMOR_LIGTHHUNTER + 
				(this.technologyDefense * PLUS_ARMOR_LIGTHHUNTER_BY_TECHNOLOGY)
				*ARMOR_LIGTHHUNTER/100;
		atack = BASE_DAMAGE_LIGTHHUNTER + 
				(this.technologyAtack * PLUS_ATTACK_LIGTHHUNTER_BY_TECHNOLOGY)
				*BASE_DAMAGE_LIGTHHUNTER/100;
		
		for (int i = 0; i<n; i++) {
			if (i == n-1) {
				System.out.println("Se han creado "+ n + " Light hunter/s.");
			}
			if ((this.metal - METAL_COST_LIGTHHUNTER) > 0) {
				this.army[0].add(cAtack.new LigthHunter(armor, atack));
				this.metal -= METAL_COST_LIGTHHUNTER;
			}else {
				System.out.println("\nDon't hace enough metal.");
				throw new BuildException("You don't have enough resources\nThey have only been created "+ i + " Heavy hunter/s.\n");	
			}	
		}
	}
	
	public void newHeavyHunter(int n) throws BuildException{
		int armor;
		int atack;
		Clases_ataque cAtack = new Clases_ataque();
		
		// Formula de nivel de tecnologia para las stats
		armor = ARMOR_HEAVYHUNTER + 
				(this.technologyDefense * PLUS_ARMOR_HEAVYHUNTER_BY_TECHNOLOGY)
				*ARMOR_HEAVYHUNTER/100;
		atack = BASE_DAMAGE_HEAVYHUNTER + 
				(this.technologyAtack * PLUS_ATTACK_HEAVYHUNTER_BY_TECHNOLOGY)
				*BASE_DAMAGE_HEAVYHUNTER/100;
		
		for (int i = 0; i<n; i++) {
			if (i == n-1) {
				System.out.println("Se han creado "+ n + " Heavy hunter/s.");
			}
			if ((this.metal - METAL_COST_HEAVYHUNTER) > 0 && (this.deuterium - DEUTERIUM_COST_HEAVYHUNTER) > 0) {
				this.army[1].add(cAtack.new HeavyHunter(armor, atack));
				this.metal -= METAL_COST_HEAVYHUNTER;
				this.deuterium -= DEUTERIUM_COST_HEAVYHUNTER;
			}else {
				System.out.println("\nDon't hace enough resources.");
				throw new BuildException("Solo se han creado "+ i + " Heavy hunter/s.\n");	
			}	
		}
	}
	
	public void newBattleShip(int n) throws BuildException {
		int armor;
		int atack;
		Clases_ataque cAtack = new Clases_ataque();
		
		// Formula de nivel de tecnologia para las stats
		armor = ARMOR_BATTLESHIP + 
				(this.technologyDefense * PLUS_ARMOR_BATTLESHIP_BY_TECHNOLOGY)
				*ARMOR_BATTLESHIP/100;
		atack = BASE_DAMAGE_BATTLESHIP + 
				(this.technologyAtack * PLUS_ATTACK_BATTLESHIP_BY_TECHNOLOGY)
				*BASE_DAMAGE_BATTLESHIP/100;
		
		for (int i = 0; i<n; i++) {
			
				if (i == n-1) {
					System.out.println("Se han creado "+ n + " Battle Ship/s.");
				}
				if ((this.metal - METAL_COST_BATTLESHIP) > 0  && (this.deuterium - DEUTERIUM_COST_BATTLESHIP) > 0) {
					this.army[2].add(cAtack.new BattleShip(armor, atack));
					this.metal -= METAL_COST_BATTLESHIP;
					this.deuterium -= DEUTERIUM_COST_BATTLESHIP;
				}else {
					System.out.println("\nDon't hace enough metal.");
					throw new BuildException("Solo se han creado "+ i + " Battle Ship/s.\n");	
				}
		}
	}
	
	public void newArmoredShip(int n) throws BuildException{
		int armor;
		int atack;
		Clases_ataque cAtack = new Clases_ataque();
		
		// Formula de nivel de tecnologia para las stats
		armor = ARMOR_ARMOREDSHIP + 
				(this.technologyDefense * PLUS_ARMOR_ARMOREDSHIP_BY_TECHNOLOGY)
				*ARMOR_ARMOREDSHIP/100;
		atack = BASE_DAMAGE_ARMOREDSHIP + 
				(this.technologyAtack * PLUS_ATTACK_ARMOREDSHIP_BY_TECHNOLOGY)
				*BASE_DAMAGE_ARMOREDSHIP/100;
		
		for (int i = 0; i<n; i++) {
			if (i == n-1) {
				System.out.println("Se han creado "+ n + " Armored Ship/s.");
			}
			if ((this.metal - METAL_COST_ARMOREDSHIP) > 0) {
				this.army[3].add(cAtack.new ArmoredShip(armor, atack));
				this.metal -= METAL_COST_ARMOREDSHIP;
			}else {
				System.out.println("\nDon't hace enough metal.");
				throw new BuildException("Solo se han creado "+ i + " Armored Ship/s.\n");	
			}	
		}
	}
	// Nueva defensas
	public void newMissileLauncher(int n) throws BuildException {
		int armor;
		int atack;
		Clases_defensa cDef = new Clases_defensa();
		
		// Formula de nivel de tecnologia para las stats
		armor = ARMOR_MISSILELAUNCHER + 
				(this.technologyDefense * PLUS_ARMOR_MISSILELAUNCHER_BY_TECHNOLOGY)
				*ARMOR_MISSILELAUNCHER/100;
		atack = BASE_DAMAGE_MISSILELAUNCHER + 
				(this.technologyAtack * PLUS_ATTACK_MISSILELAUNCHER_BY_TECHNOLOGY)
				*BASE_DAMAGE_MISSILELAUNCHER/100;
		
		for (int i = 0; i<n; i++) {
			if (i == n-1) {
				System.out.println("Se han creado "+ n + " Missile launcher/s.");
			}
			if ((this.metal - METAL_COST_MISSILELAUNCHER) > 0) {
				this.army[4].add(cDef.new MissileLauncher(armor, atack));
				this.metal -= METAL_COST_MISSILELAUNCHER;
			}else {
				System.out.println("\nDon't hace enough metal.");
				throw new BuildException("Solo se han creado "+ i + " Missile launcher/s.\n");	
			}
		}
	}
	
	public void newIonCannon(int n)  throws BuildException{
		int armor;
		int atack;
		Clases_defensa cDef = new Clases_defensa();
		
		// Formula de nivel de tecnologia para las stats
		armor = ARMOR_IONCANNON + 
				(this.technologyDefense * PLUS_ARMOR_IONCANNON_BY_TECHNOLOGY)
				*ARMOR_IONCANNON/100;
		atack = BASE_DAMAGE_IONCANNON + 
				(this.technologyAtack * PLUS_ATTACK_IONCANNON_BY_TECHNOLOGY)
				*BASE_DAMAGE_IONCANNON/100;
		
		for (int i = 0; i<n; i++) {
			if (i == n-1) {
				System.out.println("Se han creado "+ n + " Ion cannon/s.");
			}
			if ((this.metal - METAL_COST_IONCANNON) > 0) {
				this.army[5].add(cDef.new IonCannon(armor, atack));
				this.metal -= METAL_COST_IONCANNON;
			}else {
				System.out.println("\nDon't hace enough metal.");
				throw new BuildException("Solo se han creado "+ i + " Ion cannonr/s.\n");	
			}
		}
	}
	
	public void newPlasmaCannon(int n) throws BuildException{
		int armor;
		int atack;
		Clases_defensa cDef = new Clases_defensa();
		
		// Formula de nivel de tecnologia para las stats
		armor = ARMOR_PLASMACANNON + 
				(this.technologyDefense * PLUS_ARMOR_PLASMACANNON_BY_TECHNOLOGY)
				*ARMOR_PLASMACANNON/100;
		atack = BASE_DAMAGE_PLASMACANNON + 
				(this.technologyAtack * PLUS_ATTACK_PLASMACANNON_BY_TECHNOLOGY)
				*BASE_DAMAGE_PLASMACANNON/100;
		
		for (int i = 0; i<n; i++) {
			if (i == n-1) {
				System.out.println("Se han creado "+ n + " Plasma cannon/s.");
			}
			if ((this.metal - METAL_COST_PLASMACANNON) > 0) {
				this.army[6].add(cDef.new PlasmaCannon(armor, atack));
				this.metal -= METAL_COST_PLASMACANNON;
			}else {
				
				System.out.println("\nDon't hace enough metal.");
				throw new BuildException("Solo se han creado "+ i + " Plasma cannon/s.\n");	
			}
		}
	}
	
	public String printStats() {
		
	    int cantidadLight = this.army[0].size();
	    int cantidadHeavy = this.army[1].size();
	    int cantidadBattle = this.army[2].size();
	    int cantidadArmored = this.army[3].size();
	    int cantidadMissile = this.army[4].size();
	    int cantidadIon = this.army[5].size();
	    int cantidadPlasma = this.army[6].size();
		
	    String stats = String.format("Planet Stats:\n"
	    						   + "\nTECHNOLOGY\n"
	    						   + "\nAtack Technology%14d\n"
	    						   + "Defense Technology%12d\n"
	    						   + "\nDEFENSES\n"
	    						   + "\nMissile Launcher%14d\n"
	    						   + "Ion Cannon%20d\n"
	    						   + "Plasma Cannon%17d\n"
	    						   + "\nFLEET\n"
	    						   + "\nLight Hunter%18d\n"
	    						   + "Heavy Hunter%18d\n"
	    						   + "Battle Ship%19d\n"
	    						   + "Armored Ship%18d\n"
	    						   + "\nRESOURCES\n"
	    						   + "\nMetal%25d\n"
	    						   + "Deuterium%21d",
						this.technologyAtack, this.technologyDefense,
						cantidadMissile, cantidadIon, 
						cantidadPlasma, cantidadLight, 
						cantidadHeavy, cantidadBattle, 
						cantidadArmored, this.metal, this.deuterium);
	        // Retorna las estadísticas
	        return stats;
	}	
	
}
	
