

public class Clases_defensa {

	public abstract class Defense implements MilitaryUnit, Variables{
	
		int armor;
		int initialArmor;
		int baseDamage;
		
		public Defense() {};
		
		public Defense(int initialArmor, int baseDamage) {
			super();
			this.armor = initialArmor;
			this.initialArmor = initialArmor;
			this.baseDamage = baseDamage;
		}
		
	}
	
	public class MissileLauncher extends Defense {
	
		public MissileLauncher(int armor, int baseDamage) {
			super(armor, baseDamage);
			
		}

		public int attack() {
			// Plus tech??
			return this.baseDamage;
		}
	
		
		public void tekeDamage(int receivedDamage) {
			armor -= receivedDamage;
			
		}
	
		
		public int getActualArmor() {
			return this.armor;
		}
	
		
		public int getMetalCost() {
			return METAL_COST_MISSILELAUNCHER;
		}
	
		
		public int getDeuteriumCost() {
			return DEUTERIUM_COST_MISSILELAUNCHER;
		}
	
		
		public int getChanceGeneratinWaste() {
			return CHANCE_GENERATNG_WASTE_MISSILELAUNCHER;
		}
	
		
		public int getChanceAttackAgain() {
			
			return CHANCE_ATTACK_AGAIN_MISSILELAUNCHER;
		}
	
		
		public void resetArmor() {
			this.armor = this.initialArmor;
			
		}
		public String getUnitName() {	
			return UNIT_NAMES[4];
		}
	}
	
	 public class IonCannon extends Defense {
	
		public IonCannon(int initialArmor, int baseDamage) {
			super(initialArmor, baseDamage);
			
		}

		public int getArmor() {
			return armor;
		}
		public void setArmor(int armor) {
			this.armor = armor;
		}
		public int getInitialArmor() {
			return initialArmor;
		}
		public void setInitialArmor(int initialArmor) {
			this.initialArmor = initialArmor;
		}
		public int getBaseDamage() {
			return baseDamage;
		}
		public void setBaseDamage(int baseDamage) {
			this.baseDamage = baseDamage;
		}
	
		public int attack() {
			// Plus tech??
			return this.baseDamage;
		}
	
		
		public void tekeDamage(int receivedDamage) {
			armor -= receivedDamage;
			
		}
	
		
		public int getActualArmor() {
			return this.armor;
		}
	
		
		public int getMetalCost() {
			return METAL_COST_IONCANNON;
		}
	
		
		public int getDeuteriumCost() {
			return DEUTERIUM_COST_IONCANNON;
		}
	
		
		public int getChanceGeneratinWaste() {
			return CHANCE_GENERATNG_WASTE_IONCANNON;
		}
	
		
		public int getChanceAttackAgain() {
			
			return CHANCE_ATTACK_AGAIN_IONCANNON;
		}
	
		
		public void resetArmor() {
			this.armor = this.initialArmor;
			
		}
		
		public String getUnitName() {	
			return UNIT_NAMES[5];
		}
		
	}
	
	 public class PlasmaCannon extends Defense {
	
		public PlasmaCannon(int initialArmor, int baseDamage) {
			super(initialArmor, baseDamage);
			
		}

		public int getArmor() {
			return armor;
		}
		public void setArmor(int armor) {
			this.armor = armor;
		}
		public int getInitialArmor() {
			return initialArmor;
		}
		public void setInitialArmor(int initialArmor) {
			this.initialArmor = initialArmor;
		}
		public int getBaseDamage() {
			return baseDamage;
		}
		public void setBaseDamage(int baseDamage) {
			this.baseDamage = baseDamage;
		}
	
		public int attack() {
			// Plus tech??
			return this.baseDamage;
		}
	
		
		public void tekeDamage(int receivedDamage) {
			armor -= receivedDamage;
			
		}
	
		
		public int getActualArmor() {
			return this.armor;
		}
	
		
		public int getMetalCost() {
			return METAL_COST_PLASMACANNON;
		}
	
		
		public int getDeuteriumCost() {
			return DEUTERIUM_COST_PLASMACANNON;
		}
	
		
		public int getChanceGeneratinWaste() {
			return CHANCE_GENERATNG_WASTE_PLASMACANNON;
		}
	
		
		public int getChanceAttackAgain() {
			
			return CHANCE_ATTACK_AGAIN_PLASMACANNON;
		}
	
		
		public void resetArmor() {
			this.armor = this.initialArmor;
			
		}
		
		public String getUnitName() {	
			return UNIT_NAMES[6];
		}
		
	}
}