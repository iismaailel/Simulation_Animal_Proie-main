import java.awt.Point;
import java.util.Random;

/**
 * Cette classe representer l'objet animal, elle a 15 attribut :  <br><br>
 * 
 * * C : Couleur <br>
 * * PDP : Probabilité de détection de la proie<br>
 * * PRP : Probabilité de reproduction<br>
 * * BA : Bonus d’attaque<br>
 * * CA : Capacité d’attaque<br>
 * * CD : Capacité de défense<br>
 * * VD : Vitesse de déplacement<br>
 * * EA : Endurance alimentaire ( en jours )<br>
 * * EV : Espérance de vie ( en jours )<br>
 * * postion : Postion de l'animal sur le champs un Point<br>
 * * aDetecter : Un boolean pour dire si l'animal a detecte une proie durant son tour ou pas <br>
 * * reprodruire : Un boolean pour dire si l'animal a tenter de se reproduire ou a reproduit durant un tour  <br>
 * * nbJourApresMort : Nombre de jour aprés la mort de l'animal <br>
 * * nbJourPasManger : Nombre de jour ou l'animal n'a pas mangé  <br>
 * * nbJourVie : Nombre de jour que l'animal a vecu depuis ca naissance <br>
 */
public abstract class Animal implements java.lang.Comparable<Animal>{

	private String C;
	private int PDP, PRP, BA, CA, CD, VD, EA, EV;
	private Point position;
	private boolean aDetecter, reprodruire;
	private int nbJourApresMort;
	private int nbJourPasManger, nbJourVie;
	
	/**
	 * Un Constructeur qui cree des animaux et les palcer dans le champs  
	 *  
	 * @param c : Couleur de l'animal
	 * @param pDP : Probabilité de détection de la proie
	 * @param pRP : Probabilité de reproduction
	 * @param bA : Bonus d’attaque
	 * @param cA : Capacité d’attaque
	 * @param cD : Capacité de défense
	 * @param vD : Vitesse de déplacement
	 * @param eA : Endurance alimentaire
	 * @param eV : Espérance de vie
	 * @param x : abscisse de l'animal sur le champ 
	 * @param y : ordonné de l'animal sur le champ 
	 */
	public Animal(String c, int pDP, int pRP, int bA, int cA, int cD, int vD, int eA, int eV, int x, int y) {
		C = c;
		PDP = pDP;
		PRP = pRP;
		BA = bA;
		CA = cA;
		CD = cD;
		VD = vD;
		EA = eA;
		EV = eV;
		position = new Point(x,y);
		aDetecter = false;
		reprodruire = false;
		nbJourApresMort = 0;
		nbJourPasManger = 0;
		nbJourVie = 0;
	}

	/**
	 * Accesseur.
	 * @return Renvoie la couleur de l'animal
	 */
	public String getC() {
		return C;
	}
	
	/**
	 * Accesseur.
	 * @return Renvoie la probabilité de detection du proie
	 */
	public int getPDP() {
		return PDP;
	}
	
	/**
	 * Accesseur.
	 * @return Renvoie le probabilité de reproduction
	 */
	public int getPRP() {
		return PRP;
	}
	
	/**
	 * Accesseur.
	 * @return Renvoie le bonus d'attaque
	 */
	public int getBA() {
		return BA;
	}
	
	/**
	 * Accesseur.
	 * @return Renvoie la capacité d'attaque
	 */
	public int getCA() {
		return CA;
	}
	
	/**
	 * Accesseur.
	 * @return Renvoie la capacité de defense
	 */
	public int getCD() {
		return CD;
	}
	
	/**
	 * Accesseur.
	 * @return Renvoie la vitesse de deplacement
	 */
	public int getVD() {
		return VD;
	}
	
	/**
	 * Accesseur.
	 * @return Renvoie l'endurance alimentaire
	 */
	public int getEA() {
		return EA;
	}
	
	/**
	 * Accesseur.
	 * @return Renvoie l'endurance de vie 
	 */
	public int getEV() {
		return EV;
	}
	
	/**
	 * Accesseur.
	 * @return Renvoie la position de l'animal 
	 */
	public Point getPosition() {
		return position;
	}
	
	/**
	 * Accesseur.
	 * @return Renvoie le nombre jours aprés la mort de l'animal
	 */
	public int getNbJourApresMort() {
		return nbJourApresMort;
	}
	
	/**
	 * Accesseur.
	 * @return Renvoie le nombre jours ou l'animal n'a pas mangé 
	 */
	public int getNbJourPasManger() {
		return nbJourPasManger;
	}
	
	/**
	 * Accesseur.
	 * @return Renvoie le nombre jours de vie de l'animal
	 */
	public int getNbJourVie() {
		return nbJourVie;
	}
	
	/**
	 * Accesseur.
	 * @return Renvoie true si l'animal a reproduit ou essayer sinon false
	 */
	public boolean isReprodruire() {
		return reprodruire;
	}
	
	/**
	 * Accesseur.
	 * @return Renvoie true si l'animal a detecte une proie sinon false
	 */
	public boolean isaDetecter() {
		return aDetecter;
	}

	/**
	 * Mutateur .
	 * @param cd Capacité de défense (elle sera mis a -1 si l'animal meurt)
	 */
	public void setCD(int cd) {
		CD = cd;
	}
	
	/**
	 * Mutateur .
	 * @param x : abscisse de l'animal sur le champ 
	 * @param y : ordonné de l'animal sur le champ 	 
	 */
	public void setPosition(int x, int y) {
		position = new Point(x,y);
	}
	
	/**
	 * Mutateur .
	 * @param position : changer la position par la nouvelle
	 */
	public void setPosition(Point position) {
		this.position = position;
	}
	
	/**
	 * Mutateur.
	 * @param nbJourPasManger : changer le nombre jours ou l'animal n'a pas mangé par la nouvelle valeur
	 */
	public void setNbJourPasManger(int nbJourPasManger) {
		this.nbJourPasManger = nbJourPasManger;
	}
	
	/**
	 * Mutateur.
	 * @param nbJourVie : changer le nombre jours de vie de l'animal par la nouvelle valeur
	 */
	public void setNbJourVie(int nbJourVie) {
		this.nbJourVie = nbJourVie;
	}

	/**
	 * Mutateur.
	 * @param nbJourApresMort : changer le nombre jours aprés la mort de l'animal par la nouvelle valeur
	 */
	public void setNbJourApresMort(int nbJourApresMort) {
		this.nbJourApresMort = nbJourApresMort;
	}
	
	/**
	 * Mutateur.
	 * @param aDetecter : changer la valeur de variable qui indique si l'animal a detecte une proie par la nouvelle valeur
	 */
	public void setaDetecter(boolean aDetecter) {
		this.aDetecter = aDetecter;
	}
	
	/**
	 * Mutateur.
	 * @param reprodruire : changer la valeur de variable qui indique si l'animal a reproduit ou essayer par la nouvelle valeur
	 */
	public void setReprodruire(boolean reprodruire) {
		this.reprodruire = reprodruire;
	}
	
	@Override
	/**
	 * Redifinir la fonction toString pour animal
	 */
	public String toString() {
		return "\n\t* Couleur : " + getC() +
		"\n\t* Probabilité de détection de la proie : " + getPDP() + "%" +
		"\n\t* Probabilité de reproduction : " + getPRP() + "%" +
		"\n\t* Bonus d’attaque : " + getBA() +
		"\n\t* Capacité d’attaque : " + getCA() +
		"\n\t* Capacité de défense : " + getCD() +
		"\n\t* Vitesse de déplacement : " + getVD() +
		"\n\t* Endurance alimentaire : " + getEA() + "jours" +
		"\n\t* Espérance de vie : " + getEV() + "jours" + 
		"\n\t* Position : ( " + getPosition().x + " , " + getPosition().y + " )\n";
	}
	
	@Override
	/**
	 * Redifinir la fonction equals pour animal
	 */
	public boolean equals(Object obj) {
		Animal a = (Animal) obj;
		if((a.getPosition().x == position.x) && (a.getPosition().y == position.y) && a.getC().equals(C)) {
			return true;
		}
		return false;
	}
	
	@Override
	/**
	 * Redifinir la fonction compareTo pour animal
	 */
	public int compareTo(Animal a) {
		return this.getNbJourVie()-a.getNbJourVie();
	}
	
	//******************************************************************************************//
	
	/**
	 * Cette methode return true si l'animal p est proie sinon false
	 * @param p un animl pour le verifier si il est une proie ou pas
	 * @return true si p estune proie pour l'animal sinon false
	 */
	public abstract boolean estProie(Animal p);
	
	/**
	 * Cette methode renvoie true si notre animal (this) a reussi a manger la proie p sinon false<br>
	 * Elle compare la capacite d'attaque de l'animal plus un bonus et la capacite de defense
	 * @param p la proie a manger
	 * @return true si l'animal a reussi a manger la proie p sinon false
	 */
	public boolean manger(Animal p) {
		Random r = new Random();
		int atk = r.nextInt(this.getBA())+1+getCA();	
		if(atk>p.getCD()) {
			return true ;
		}
		return false;
	}
	
	/**
	 * Cette Methode indique si l'animal a detcte la proie ou pas
	 * @return true si l'animal detecte la proie sinon faut
	 */
	public boolean detecterProie() {
		Random r = new Random();
		int dp = r.nextInt(100)+1;
		if(dp <= this.getPDP()) {
			return true;
		}
		return false;
	}
	
	/**
	 * cette methode indique si l'animal a reussi a ce reproduire ou pas
	 * @return true si l'animal reussi a ce reproduire sinon false
	 */
	public boolean reproduire() {
		Random r=new Random();
		int j = r.nextInt(100)+1;
		if(j<= this.getPRP()) {
			return true;
		}
		return false;
	}	
}
