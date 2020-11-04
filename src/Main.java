/**
 * Cette class contient la fonction main qui lance le programe
 * @author Ilyes BEN YAHIA
 * @author Ismail EL AMRANI
 * 
 * @since 03/07/2020
 * @version 1.0
 */
public class Main {
	/**
	 * Methode main qui lance la simulation on applant le constructeur de la class Simulation
	 * @param args
	 */
	public static void main(String[] args) {
		Simulation s = new Simulation(125,45,50,50,10); 
		Fenetre f = new Fenetre();
    	s.simulationComplet();
    	s.gagnant();
	}
}
