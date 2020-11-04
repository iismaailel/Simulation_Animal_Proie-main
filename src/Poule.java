/**
 * Cette class cree des Poules elle herite de animal
 */
public class Poule extends Animal {
	
	/**
	 * 
	 * c'est un construccteur qui cree des poules et initialise ses valeur a : <br>
	 * 
	 *	* Couleur : Jaune <br>
	 *  * Probabilité de détection de la proie : 10% <br>
	 *	* Probabilité de reproduction : 40% <br>
	 *	* Bonus d’attaque : 6 <br>
	 *	* Capacité d’attaque : 15 <br>
	 *	* Capacité de défense : 23 <br>
	 *	* Vitesse de déplacement : 2 <br>
	 *	* Endurance alimentaire : 20 jours <br>
	 *	* Espérance de vie : 150 jours <br>
	 *	* Abscisse de l'animal sur le champ : x <br>
	 *  * ordonné de l'animal sur le champ : y <br>
	 * @param x : abscisse de l'animal sur le champ 
	 * @param y : ordonné de l'animal sur le champ 
	 */
	public Poule(int x, int y) {
		super("Jaune", 10, 40, 6, 15, 23, 2, 20, 150, x, y);
	}
	
	@Override
	/**
	 * Redifinir la fonction toString pour animal
	 */
	public String toString() {
		return  "Poule :" + super.toString();		
	}
	
	public boolean estProie(Animal p) {
		//on verifie si l'animal p est une vipere ou p est mort par vieillesse ou affame
		if(p.getC().equals("Vert") || (p.getCD() == -1)) {
			return true;
		}
		return false;
	}
}
