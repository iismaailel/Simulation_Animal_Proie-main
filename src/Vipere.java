/**
 * Cette class cree des viperes elle herite de animal 
 */
public class Vipere extends Animal {
	
	/**
	 * 
	 * Un construccteur qui cree des viperes et initialise ses valeur a : <br>
	 *  
	 * 	* Couleur : Vert <br>
 	 *	* Probabilité de détection de la proie : 50% <br>
	 *	* Probabilité de reproduction : 50% <br>
	 *	* Bonus d’attaque : 8 <br>
	 *	* Capacité d’attaque : 5 <br>
	 *  * Capacité de défense : 17 <br>
	 *	* Vitesse de déplacement : 1 <br>
	 *	* Endurance alimentaire : 35 jours <br>
	 *	* Espérance de vie : 120 jours <br>
	 *  * Abscisse de l'animal sur le champ : x <br>
	 *  * ordonné de l'animal sur le champ : y <br>
	 * @param x : abscisse de l'animal sur le champ 
	 * @param y : ordonné de l'animal sur le champ 
	 */
	public Vipere(int x, int y) {
		super("Vert", 50, 50, 8, 5, 17, 1, 35, 120, x, y);
	}
	
	@Override
	public String toString() {
		
		return  "Vipere :" + super.toString();
	}
	
	//Cette methode return true si l'animal p est un proie pour l'animal a sinon false
	public boolean estProie(Animal p) {
		//on verifie si l'animal p est une renard ou p est mort par vieillesse ou affame
		if(p.getC().equals("Orange") || (p.getCD() == -1)) {
			return true;
		}
		return false;
	}
}