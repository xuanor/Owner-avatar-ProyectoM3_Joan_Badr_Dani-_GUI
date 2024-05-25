
public interface MilitaryUnit{
	
	// Nos devolverá el poder de ataque que tenga la unidad.
	abstract int attack();
	// Restará a nuestro blindaje el daño recibido por parámetro.
	abstract void tekeDamage(int receivedDamage);
	// Nos devolverá el blindaje que tengamos actualmente, después de haber recibido un ataque.
	abstract int getActualArmor();
	//Nos devolverá el coste de Metal que tiene crear una nueva unidad.
	abstract int getMetalCost();
	// Lo mismo pero con Deuterium
	abstract int getDeuteriumCost();
	// Retorna la probabilidad de generar residuos al ser totalmente eliminada (blindaje 0 o inferior)
	abstract int getChanceGeneratinWaste();
	//Retorna la probabilidad de generar volver a atacar.
	abstract int getChanceAttackAgain();
	//Nos restablecerá nuestro blindaje a su valor original.
	abstract void resetArmor();
	
	abstract String getUnitName();
	
	
}
