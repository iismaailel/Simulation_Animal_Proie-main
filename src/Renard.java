/**
 * Cette class cree des renards elle herite de animal
 */
public class Renard extends Animal {
	
	/**
	 * 
	 * Un construccteur qui cree des Renards et initialise ses valeur a : <br>
	 * 
	 *	* Couleur : Orange <br>
	 *	* Probabilité de détection de la proie : 20% <br>
	 *	* Probabilité de reproduction : 14% <br>
	 *	* Bonus d’attaque : 12 <br>
	 *	* Capacité d’attaque : 20 <br>
	 *	* Capacité de défense : 7 <br>
	 *	* Vitesse de déplacement : 3 <br>
	 *	* Endurance alimentaire : 8 jours <br>
	 *	* Espérance de vie : 250 jours <br>
	 *	* Abscisse de l'animal sur le champ : x <br>
	 *  * ordonné de l'animal sur le champ : y <br>
	 * @param x : abscisse de l'animal sur le champ 
	 * @param y : ordonné de l'animal sur le champ  
	 *  
	 */
	public Renard(int x, int y) {
		super("Orange", 20, 14, 12, 20, 7, 3, 8, 250, x, y);
	}

	@Override
	/**
	 * Redifinir la fonction toString pour Renard
	 */
	public String toString() {
		
		return  "Renard :" + super.toString();
	}
	
	public boolean estProie(Animal p) {
		//on verifie si l'animal p est une poule ou p est mort par vieillesse ou affame
		if(p.getC().equals("Jaune") || (p.getCD() == -1)) {
			return true;
		}
		return false;
	}
}
