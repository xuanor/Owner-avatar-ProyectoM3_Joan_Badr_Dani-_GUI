import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class ConnectionDB {
	private String planetName;
	private int user_id = -1;
	private int contId = -1;
	private String userName = "pepe";
	private String pswr = "pepe";
	private ArrayList<int[]> arrayReasources;
	private ArrayList<String[]> arrayReportesBatalla;
	private ArrayList<int[]> arrayHeavytHunter;
	private ArrayList<int[]> arrayLightHunter;
	private ArrayList<int[]> arrayBattleShip;
	private ArrayList<int[]> arrayArmoredShip;
	private ArrayList<int[]> arrayMissileLouncher;
	private ArrayList<int[]> arrayIonCannon;
	private ArrayList<int[]> arrayPlasmaCannon;
	
	
	
	
	public int getContId() {
		return contId;
	}


	public void setContId(int contId) {
		this.contId = contId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPswr() {
		return pswr;
	}


	public void setPswr(String pswr) {
		this.pswr = pswr;
	}


	public String getPlanetName() {
		return planetName;
	}


	public void setPlanetName(String planetName) {
		this.planetName = planetName;
	}


	public ArrayList<int[]> getArrayReasources() {
		return arrayReasources;
	}
	
	
	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}


	public ArrayList<String[]> getArrayReportesBatalla() {
		return arrayReportesBatalla;
	}


	public void setArrayReportesBatalla(ArrayList<String[]> arrayReportesBatalla) {
		this.arrayReportesBatalla = arrayReportesBatalla;
	}


	public ArrayList<int[]> getArrayLightHunter() {
		return arrayLightHunter;
	}

	public void setArrayLightHunter(ArrayList<int[]> arrayLightHunter) {
		this.arrayLightHunter = arrayLightHunter;
	}

	public void setArrayReasources(ArrayList<int[]> arrayReasources) {
		this.arrayReasources = arrayReasources;
	}

	public ArrayList<int[]> getArrayHeavytHunter() {
		return arrayHeavytHunter;
	}

	public void setArrayHeavytHunter(ArrayList<int[]> arrayHeavytHunter) {
		this.arrayHeavytHunter = arrayHeavytHunter;
	}

	public ArrayList<int[]> getArrayBattleShip() {
		return arrayBattleShip;
	}

	public void setArrayBattleShip(ArrayList<int[]> arrayBattleShip) {
		this.arrayBattleShip = arrayBattleShip;
	}

	public ArrayList<int[]> getArrayArmoredShip() {
		return arrayArmoredShip;
	}

	public void setArrayArmoredShip(ArrayList<int[]> arrayArmoredShip) {
		this.arrayArmoredShip = arrayArmoredShip;
	}

	public ArrayList<int[]> getArrayMissileLouncher() {
		return arrayMissileLouncher;
	}

	public void setArrayMissileLouncher(ArrayList<int[]> arrayMissileLouncher) {
		this.arrayMissileLouncher = arrayMissileLouncher;
	}

	public ArrayList<int[]> getArrayIonCannon() {
		return arrayIonCannon;
	}

	public void setArrayIonCannon(ArrayList<int[]> arrayIonCannon) {
		this.arrayIonCannon = arrayIonCannon;
	}

	public ArrayList<int[]> getArrayPlasmaCannon() {
		return arrayPlasmaCannon;
	}

	public void setArrayPlasmaCannon(ArrayList<int[]> arrayPlasmaCannon) {
		this.arrayPlasmaCannon = arrayPlasmaCannon;
	}

	// Metodo de conexion y selector de la accion que quiero (push/pull)
	public void conection( int opc, Planet p, Battle b, String usr, String pswd , boolean setNewUser) {
		String urlDatos =  "jdbc:oracle:thin:@localhost:1521/orcl";
		String usuario = "ogame";
		String pass = "ogame";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection conn = DriverManager.getConnection(urlDatos, usuario, pass);
//			if (conn != null) {
//				System.out.println("Connected with connection");
//			}
			String query = "SELECT * FROM user_credentials";
			Statement stmnt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmnt.executeQuery(query);
			rs.last();
			setContId(rs.getInt(1));
			//System.out.println("HOLA = " + getContId());
			
			switch (opc) {
			case 1:
				login(conn, usr, pswd);
				break;
			case 2:
				metodoPull(conn);
				break;
				
			case 3:
				metodoPush(conn, p, b);
				break;
				
			case 4:
				metodoClear(conn, setNewUser, usr, pswd );
				break;
			default:
				System.out.println("Invalid option");
				break;
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("Driver no cargado");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Conexion no realizada");
			e.printStackTrace();
		}	
	}
	
	
	public void login(Connection conn, String usr, String pswd) {
		try {
			String query = "SELECT * FROM user_credentials";
			Statement stmnt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmnt.executeQuery(query);
			
			while (rs.next()) {
				//System.out.println(rs.getString(1) +" "+ rs.getString(2) +" "+ rs.getString(3));
				if (rs.getString(2).equals(usr) && rs.getString(3).equals(pswd)) {
					//System.out.println("**************************************************");
					setUser_id(rs.getInt(1));
					//System.out.println(getUser_id());
					break;
				}
			}
			
		} catch (SQLException e) {
			//System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public void metodoPull(Connection conn) {
		try {
			
			String query = "SELECT * FROM planet_stats WHERE USER_ID = " + getUser_id();
			Statement stmnt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmnt.executeQuery(query);	
			
			//{Metal, deuterio, techD, techA}
			ArrayList<int[]> arrayReasources = new ArrayList<int[]>();
			
			rs.next();
			arrayReasources.add(new int[] {rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getInt(7)});
			setPlanetName(rs.getString(3));
			this.setArrayReasources(arrayReasources);
			
			//Battle Reports
			String query0 = "SELECT * FROM battle WHERE PLANET_ID = " + getUser_id();
			rs = stmnt.executeQuery(query0);	
			ArrayList<String[]> arrayReportesBatalla = new ArrayList<String[]>();
			
			while (rs.next()) {
				
				arrayReportesBatalla.add(new String[] {rs.getString(3),rs.getString(4)});
			}
			this.setArrayReportesBatalla(arrayReportesBatalla);
			
			//LighHunter
			String query1 = "SELECT * FROM light_hunter WHERE PLANET_ID = " + getUser_id();
			rs = stmnt.executeQuery(query1);	
			ArrayList<int[]> arrayLightHunter = new ArrayList<int[]>();
			
			while (rs.next()) {
				
				arrayLightHunter.add(new int[] {rs.getInt(2),rs.getInt(3)});
			}
			this.setArrayLightHunter(arrayLightHunter);
			
			
//			System.out.println("\nSize =" + arrayLightHunter.size());
//			for (int[] is : arrayLightHunter) {
//				for (int i : is) {
//					System.out.println(i);
//				}
//			}
			
			//HeavyHunter
			String query2 = "SELECT * FROM heavy_hunter WHERE PLANET_ID = " + getUser_id();
			rs = stmnt.executeQuery(query2);	
			ArrayList<int[]> arrayHeavytHunter = new ArrayList<int[]>();
			
			while (rs.next()) {
				
				arrayHeavytHunter.add(new int[] {rs.getInt(2),rs.getInt(3)});
			}
			this.setArrayHeavytHunter(arrayHeavytHunter);
			
			
			//BattleShip
			String query3 = "SELECT * FROM battle_ship WHERE PLANET_ID = " + getUser_id();
			rs = stmnt.executeQuery(query3);	
			ArrayList<int[]> arrayBattleShip = new ArrayList<int[]>();
			
			while (rs.next()) {
				
				arrayBattleShip.add(new int[] {rs.getInt(2),rs.getInt(3)});
			}
			
			this.setArrayBattleShip(arrayBattleShip);
			
			//ArmoredShip
			String query4 = "SELECT * FROM armored_ship WHERE PLANET_ID = " + getUser_id();
			rs = stmnt.executeQuery(query4);	
			ArrayList<int[]> arrayArmoredShip = new ArrayList<int[]>();
			
			while (rs.next()) {
				
				arrayArmoredShip.add(new int[] {rs.getInt(2),rs.getInt(3)});
			}
			
			this.setArrayArmoredShip(arrayArmoredShip);
			
			//MissileLouncher
			String query5 = "SELECT * FROM missile_launcher WHERE PLANET_ID = " + getUser_id();
			rs = stmnt.executeQuery(query5);	
			ArrayList<int[]> arrayMissileLouncher = new ArrayList<int[]>();
			
			while (rs.next()) {
				
				arrayMissileLouncher.add(new int[] {rs.getInt(2),rs.getInt(3)});
			}
			
			this.setArrayMissileLouncher(arrayMissileLouncher);
			
			//IonCannon
			String query6 = "SELECT * FROM ion_cannon WHERE PLANET_ID = " + getUser_id();
			rs = stmnt.executeQuery(query6);	
			ArrayList<int[]> arrayIonCannon = new ArrayList<int[]>();
			
			while (rs.next()) {
				
				arrayIonCannon.add(new int[] {rs.getInt(2),rs.getInt(3)});
			}
			
			this.setArrayIonCannon(arrayIonCannon);
			
			//PlasmaCannon
			String query7 = "SELECT * FROM plasma_cannon WHERE PLANET_ID = " + getUser_id();
			rs = stmnt.executeQuery(query7);	
			ArrayList<int[]> arrayPlasmaCannon = new ArrayList<int[]>();
			
			while (rs.next()) {
				
				arrayPlasmaCannon.add(new int[] {rs.getInt(2),rs.getInt(3)});
			}
			
			this.setArrayPlasmaCannon(arrayPlasmaCannon);
			
			// ArrayList que guarda cada grupo-unidad-stats
			
		} catch (SQLException e) {
			//System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	

	public void metodoPush(Connection conn, Planet p, Battle b) {

		try {
			//Recursos
			String update = "UPDATE planet_stats SET RESOURCE_METAL_AMOUNT = ?, RESOURCE_DAUTERION_AMOUNT = ?,"
					+ "RESOURCE_DEFENSE = ?, RESOURCE_ATTACK = ?"
					+ "WHERE  USER_ID = " + getUser_id();
			
			PreparedStatement ps = conn.prepareStatement(update);
			ps.setInt(1, p.getMetal());
			ps.setInt(2, p.getDeuterium());
			ps.setInt(3, p.getTechnologyDefense());
			ps.setInt(4, p.getTechnologyAtack());
			ps.executeUpdate();
			
			// Reportes Batalla
			int cont = 1;
			String delete = "DELETE FROM battle WHERE PLANET_ID = " + getUser_id();
			update = "INSERT INTO battle (planet_id, num_battles, battle_stats, battle_log)"
					 + "VALUES (?,?,?,?)";
			
			// Borrar anteriores tropas
			ps = conn.prepareStatement(delete);
			ps.executeUpdate();
			
			ps = conn.prepareStatement(update);
			
			for (String[] infoBatalla : b.getBattleStats()) {
				
				ps.setInt(1,getUser_id());
				ps.setInt(2, cont);
				ps.setString(3, infoBatalla[0]);
				ps.setString(4, infoBatalla[1]);
				ps.executeUpdate();
				cont++;
			}
			
			// Light Hunters
			cont = 1;
			delete = "DELETE FROM light_hunter WHERE PLANET_ID = " + getUser_id();
			update = "INSERT INTO light_hunter (id, planet_id,armour,atack)"
					+ "VALUES (?,?,?,?)";
			
			// Borrar anteriores tropas
			ps = conn.prepareStatement(delete);
			ps.executeUpdate();
			
			ps = conn.prepareStatement(update);
			
			for (MilitaryUnit unit : p.getArmy()[0]) {
				
				ps.setInt(1,cont);
				ps.setInt(2, getUser_id());
				ps.setInt(3, unit.getActualArmor());
				ps.setInt(4, unit.attack());
				ps.executeUpdate();
				cont++;
			}
			
			// Heavy Hunters
			delete = "DELETE FROM heavy_hunter WHERE PLANET_ID = " + getUser_id();
			update = "INSERT INTO heavy_hunter (id, planet_id,armour,atack)"
					+ "VALUES (?,?,?,?)";
			
			// Borrar anteriores tropas
			ps = conn.prepareStatement(delete);
			ps.executeUpdate();
			cont = 1;			
			ps = conn.prepareStatement(update);
			
			for (MilitaryUnit unit : p.getArmy()[1]) {
				
				ps.setInt(1,cont);
				ps.setInt(2, getUser_id());
				ps.setInt(3, unit.getActualArmor());
				ps.setInt(4, unit.attack());
				ps.executeUpdate();
				cont++;
			}
			
			
			// Battle Ship
			delete = "DELETE FROM battle_ship WHERE PLANET_ID = " + getUser_id();
			update = "INSERT INTO battle_ship (id, planet_id,armour,atack)"
					+ "VALUES (?,?,?,?)";
			
			// Borrar anteriores tropas
			ps = conn.prepareStatement(delete);
			ps.executeUpdate();
			cont = 1;	
			ps = conn.prepareStatement(update);
			
			for (MilitaryUnit unit : p.getArmy()[2]) {
				
				ps.setInt(1,cont);
				ps.setInt(2, getUser_id());
				ps.setInt(3, unit.getActualArmor());
				ps.setInt(4, unit.attack());
				ps.executeUpdate();
				cont++;
			}
			
			// Armored Ship
			delete = "DELETE FROM armored_ship WHERE PLANET_ID = " + getUser_id();
			update = "INSERT INTO armored_ship (id, planet_id,armour,atack)"
					+ "VALUES (?,?,?,?)";
			
			// Borrar anteriores tropas
			ps = conn.prepareStatement(delete);
			ps.executeUpdate();
			cont = 1;	
			
			ps = conn.prepareStatement(update);
			
			for (MilitaryUnit unit : p.getArmy()[3]) {
				
				ps.setInt(1,cont);
				ps.setInt(2, getUser_id());
				ps.setInt(3, unit.getActualArmor());
				ps.setInt(4, unit.attack());
				ps.executeUpdate();
				cont++;
			}
			
			// Missile Louncher
			delete = "DELETE FROM missile_launcher WHERE PLANET_ID = " + getUser_id();
			update = "INSERT INTO missile_launcher (id, planet_id,armour,atack)"
					+ "VALUES (?,?,?,?)";
			
			// Borrar anteriores tropas
			ps = conn.prepareStatement(delete);
			ps.executeUpdate();
			cont = 1;	
			
			ps = conn.prepareStatement(update);
			
			for (MilitaryUnit unit : p.getArmy()[4]) {
				
				ps.setInt(1,cont);
				ps.setInt(2, getUser_id());
				ps.setInt(3, unit.getActualArmor());
				ps.setInt(4, unit.attack());
				ps.executeUpdate();
				cont++;
			}
			
			// Ion cannon
			delete = "DELETE FROM ion_cannon WHERE PLANET_ID = " + getUser_id();
			update = "INSERT INTO ion_cannon (id, planet_id,armour,atack)"
					+ "VALUES (?,?,?,?)";
					
			
			// Borrar anteriores tropas
			ps = conn.prepareStatement(delete);
			ps.executeUpdate();
			cont = 1;	
			
			ps = conn.prepareStatement(update);
			
			for (MilitaryUnit unit : p.getArmy()[5]) {
				
				ps.setInt(1,cont);
				ps.setInt(2, getUser_id());
				ps.setInt(3, unit.getActualArmor());
				ps.setInt(4, unit.attack());
				ps.executeUpdate();
				cont++;
			}
			
			// Plasma cannon
			delete = "DELETE FROM plasma_cannon WHERE PLANET_ID = " + getUser_id();
			update = "INSERT INTO plasma_cannon (id, planet_id,armour,atack)"
					+ "VALUES (?,?,?,?)";
					
			
			// Borrar anteriores tropas
			ps = conn.prepareStatement(delete);
			ps.executeUpdate();
			cont = 1;	
			
			ps = conn.prepareStatement(update);
			
			for (MilitaryUnit unit : p.getArmy()[6]) {
				
				ps.setInt(1,cont);
				ps.setInt(2, getUser_id());
				ps.setInt(3, unit.getActualArmor());
				ps.setInt(4, unit.attack());
				ps.executeUpdate();
				cont++;
			}
				
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void metodoClear(Connection conn, boolean setNewUsr, String usr, String pswd) {
		// Subir datos por cada tabla
		try {
			String insert;
			Statement stmnt = conn.createStatement();
			//Default
			int user_id = getUser_id();
			
			// BORRAR DATOS ANTIUGOS
			if (!(setNewUsr)) {
				String delete = "DELETE FROM plasma_cannon WHERE PLANET_ID = " + getUser_id();
				stmnt.executeQuery(delete);
				
				delete = "DELETE FROM ion_cannon WHERE PLANET_ID = " + getUser_id();
				stmnt.executeQuery(delete);
				
				delete = "DELETE FROM missile_launcher WHERE PLANET_ID = " + getUser_id();
				stmnt.executeQuery(delete);
				
				delete = "DELETE FROM armored_ship WHERE PLANET_ID = " + getUser_id();
				stmnt.executeQuery(delete);
				
				delete = "DELETE FROM battle_ship WHERE PLANET_ID = " + getUser_id();
				stmnt.executeQuery(delete);
				
				delete = "DELETE FROM heavy_hunter WHERE PLANET_ID = " + getUser_id();
				stmnt.executeQuery(delete);
				
				delete = "DELETE FROM light_hunter WHERE PLANET_ID = " + getUser_id();
				stmnt.executeQuery(delete);
				
				
				delete = "DELETE FROM battle WHERE PLANET_ID = " + getUser_id();
				stmnt.executeQuery(delete);
				
				delete = "DELETE FROM planet_stats WHERE PLANET_ID = " + getUser_id();
				stmnt.executeQuery(delete);
				
				delete = "DELETE FROM user_credentials WHERE USER_ID = " + getUser_id();
				stmnt.executeQuery(delete);
			
			
				// USER CREDENTIALS
				insert = "INSERT INTO user_credentials (user_id,user_password,user_name)"
						+ "VALUES ( "+ getUser_id()+",'pepe', 'pepe')";
				stmnt.executeQuery(insert);
				
			}else if (setNewUsr) {
				setUserName(usr);
				setPswr(pswd);
				user_id = getContId() + 1;
				//System.out.println("USER_ID = " + user_id);
			}
			
			
			
				// USER CREDENTIALS ** añadir nombre y contraseña nuevos
				insert = "INSERT INTO user_credentials (user_id,user_password,user_name)"
						+ "VALUES ( "+ user_id +",'" + getUserName() + "', '" + getPswr() + "')";
				stmnt.executeQuery(insert);
			
			
				// Planeta aleatorio
				String[] gamePlanets = {"ALFA CENTAURI", "ANDROMEDA-5"};
				int num = (int)(Math.random()*2);
				
				
				//PLANET STATS
				insert = "INSERT INTO planet_stats (user_id ,planet_id ,"
						+ "planet_name , resource_metal_amount ,"
						+ " resource_dauterion_amount , resource_defense ,"
						+ "resource_attack) "
						+ "VALUES ( "+ user_id+","+ user_id+",'" + gamePlanets[num] + "' ,180000, 26000, 0, 0)";
				stmnt.executeQuery(insert);
			
			
			// Light Hunters (15)
			int cont = 1;
			for (int i = 0; i<15;i++) {
				insert="INSERT INTO light_hunter (id, planet_id,armour,atack)VALUES ("+cont+","+ user_id+",400,80)";
				stmnt.executeQuery(insert);
				cont++;
			}
				
			// Heavy Hunters
			cont = 1;
			for (int i = 0; i<5;i++) {
				insert="INSERT INTO heavy_hunter (id, planet_id,armour,atack)VALUES ("+cont+","+ user_id+ ",1000,150)";
				stmnt.executeQuery(insert);
				cont++;
			}

			// Battle Ship
			cont = 1;
			for (int i = 0; i<0;i++) {
				insert="INSERT INTO battle_ship (id, planet_id,armour,atack)VALUES ("+cont+","+ user_id+",1000,150)";
				stmnt.executeQuery(insert);
				cont++;
			}
			
			// Armored Ship
			cont = 1;
			for (int i = 0; i<1;i++) {
				insert="INSERT INTO armored_ship (id, planet_id,armour,atack)VALUES ("+cont+","+ user_id+",8000,700)";
				stmnt.executeQuery(insert);
				cont++;
			}
			
			// Missile Louncher
			cont = 1;
			for (int i = 0; i<10;i++) {
				insert="INSERT INTO missile_launcher (id, planet_id,armour,atack)VALUES ("+cont+","+ user_id+",200,80)";
				stmnt.executeQuery(insert);
				cont++;
			}
			
			// Ion cannon
			cont = 1;
			for (int i = 0; i<2;i++) {
				insert="INSERT INTO ion_cannon (id, planet_id,armour,atack)VALUES ("+cont+","+ user_id+",1200,250)";
				stmnt.executeQuery(insert);
				cont++;
			}
			
			// Plasma cannon
			
			cont = 1;
			for (int i = 0; i<0;i++) {
				insert="INSERT INTO plasma_cannon (id, planet_id,armour,atack)VALUES ("+cont+","+ user_id+",2000,7000)";
				stmnt.executeQuery(insert);
				cont++;
			}
			
				
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		
	}

//	public static void main(String[] args) {
//		ConnectionDB cdb = new ConnectionDB();
//		cdb.conection(4, null, null, "jordi", "jordi", true);
//		cdb.conection(1, null, null, "jordi", "jordi", false);
//		cdb.conection(2, null, null, null, null, false);
//		System.out.println(cdb.getPlanetName());
//	}

}