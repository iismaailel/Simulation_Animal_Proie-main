import java.awt.Point;
import java.util.ArrayList;

/**
 * Merci à StackOverflow pour sa précieuse contribution !<br><br>
 *
 * Cette class cree un champ graphique, elle a comme attribut : <br>
 *	* champ : un champ graphique (ChampGraphique)
 */
public class Simulation {
	private ChampGraphique champ;
	
	/**
	 * Constructeur qui cree une simulation 
	 * @param largeur La largeur (en nombre de cases) de la grille affichée.
	 * @param hauteur La hauteur (en nombre de cases) de la grille affichée.
	 * @param nbp Nombre de Poule sur le champ
	 * @param nbr Nombre de Renard sur le champ
	 * @param nbv Nombre de Vipere sur le champ
	 * @param j le jour actuelle
	 * @param s la saison actuelle
	 */
	public Simulation(int largeur, int hauteur, int nbp, int nbr, int nbv, int j, String s) {
		champ = new ChampGraphique(largeur, hauteur, nbp, nbr, nbv,j,s);
	}
	
	/**
	 * Constructeur qui cree une simulation <br>
	 * le jour est initialiser a 1 <br>
	 * et la saison en hiver
	 * @param largeur La largeur (en nombre de cases) de la grille affichée.
	 * @param hauteur La hauteur (en nombre de cases) de la grille affichée.
	 * @param nbp Nombre de Poule sur le champ
	 * @param nbr Nombre de Renard sur le champ
	 * @param nbv Nombre de Vipere sur le champ
	 */
	public Simulation(int largeur, int hauteur, int nbp, int nbr, int nbv) {
		this(largeur, hauteur, nbp, nbr, nbv, 1, "Hiver");
	}
	
	/**
	 * Constructeur qui cree une simulation en initialisant les valeur a :<br>
	 * * largeur du champs : 600<br>
	 * * hauteur du champ : 400<br>
	 * * Nombre de Poule sur le champ : 100 <br>
	 * * Nombre de Renard sur le champ : 100 <br>
	 * * Nombre de Vipere sur le champ : 100 <br>
	 * * le jour : 1 <br>
	 * * la saison : hiver <br>
	 */
	public Simulation() {
		this(600,400,100,100,100,1,"Hiver");
	}
	
	/**
	 * Accesseur.
	 * @return Renvoie le chamo graphique
	 */
	public ChampGraphique getChamp() {
		return champ;
	}
		
	//****************************************************************************************************************
	/**
	 * Cette methode renvoi un boolean qui indique la simulation et finie ou pas
	 * @return true si la simulation est finie sinon false
	 */
	public boolean simulationFinie() {
		int nbp = champ.getNbPoule();
		int nbr = champ.getNbRenard();
		int nbv = champ.getNbVipere();
		if((nbp<=0 && nbr<=0) || (nbp<=0 && nbv<=0) || (nbr<=0 && nbv<=0) || !(champ.exiteCaseVide()))
			return true;
		return false;
	}	
	
	/**
	 * cette fonction prend en parametre un animal a et renvoie la liste de ses voisins
	 * @param a un animal
	 * @return la liste des animaux qui se trouvent dans des cases voisines a l'animal a
	 */
	public ArrayList<Animal> getListVoisin(Animal a){
		ArrayList<Animal> listVoisin = new ArrayList<Animal>();
		//on recupere la liste des animaux
		ArrayList<Animal> listAnimal = (ArrayList<Animal>) champ.getListAnimal();
		//on parcourt la la liste des animaux dans le champ
		for(Animal p : listAnimal) {
			//on verifie pour chaque animal p si sa position correspond à l'une des 8 cases voisines à la case de l'animal a
			if(	  ((a.getPosition().x-1 == p.getPosition().x)&&(a.getPosition().y == p.getPosition().y))
				||((a.getPosition().x+1 == p.getPosition().x)&&(a.getPosition().y == p.getPosition().y))
				||((a.getPosition().x == p.getPosition().x)&&(a.getPosition().y+1 == p.getPosition().y))
				||((a.getPosition().x == p.getPosition().x)&&(a.getPosition().y-1 == p.getPosition().y))
				||((a.getPosition().x-1 == p.getPosition().x)&&(a.getPosition().y-1 == p.getPosition().y))
				||((a.getPosition().x-1 == p.getPosition().x)&&(a.getPosition().y+1 == p.getPosition().y))
				||((a.getPosition().x+1 == p.getPosition().x)&&(a.getPosition().y-1 == p.getPosition().y))
				||((a.getPosition().x+1 == p.getPosition().x)&&(a.getPosition().y+1 == p.getPosition().y))) {
					//si oui on ajoute l'animal p à la liste des voisins
					listVoisin.add(p);
			}
		}
		return listVoisin;	
	}
	
	/**
	 * Cette methode renvoie la list des proies autour de l'animal a
	 * @param a l'animal pour le quel la methode fait la recherche
	 * @return la liste des proies autour de l'animal a
	 */
	public ArrayList<Animal> getListProie(Animal a){
		ArrayList<Animal> listeProie= new ArrayList<Animal>();
		//on recupere la liste des voisins de a en appelant la fonction getListVoisin(a)
		ArrayList<Animal> listVoisin = getListVoisin(a);
		//on parcourt la liste des voisins de a
		for(Animal p : listVoisin) {
			//si p est proie de a on l'ajoute p à la liste des proies
			if(a.estProie(p)) {
				listeProie.add(p);
			}
		}
		return listeProie;
	}
	
	/**
	 * cette methode renvoi un boolean pour indiquer si il ya un animal de la meme espece dans la voisinage de l'animale a
	 * @param a Animal sur le quel la methode fait la recherche
	 * @return true si l'animal a à un voisin de la meme espece
	 */
	public boolean VoisinMemeEspece(Animal a) {
		//on recupere la liste des voisins de a en appellant getListVoisin(a)
		ArrayList<Animal> listVoisin = getListVoisin(a);
		//on parcourt la liste des voisins de a 
		for(Animal av : listVoisin) {
			//si av a la meme couleur que a 
			if(av.getC().equals(a.getC())) {
				//si oui on retourne true
				return true;
			}
		}
		return false;
	}
		
	/**
	 * cette methode prend en parametre un animal et renvoie un animal av voisin à a et de meme espece que a 
	 * @param a Animal 
	 * @return Un animal voisin à a de la meme espece
	 */
	public Animal getVoisinMemeEspece(Animal a) {
		//on recupere une liste lv des animaux voisins de a
		ArrayList<Animal> lv = getListVoisin(a);
		//on parcoure la liste lv
		for(Animal av : lv) {
			//pour chaque animal dans lv on verifie si av a la meme couleur que a 
			if(av.getC()==a.getC()) {
				//si oui on retourne l'animal av
				return av;
			}
		}
		//si y'a pas des voisins de meme espece on retourne null 
		return null;
	}
	
	/**
	 * cette Methode prend en parametre un animal et ajoute dans le champ un animal de  la meme espece
	 * @param a Animal
	 */
	public void ajouterNewAnimal(Animal a){
		if(champ.exiteCaseVide()) {
			//on recupere une case vide dans le champ
			Point c = champ.getCaseVide();
			//Selon l'animal on cree un nouveau dans le champ
			if (a instanceof Poule) 
				champ.colorierCase(new Poule(c.x,c.y));
			if (a instanceof Vipere) 
				champ.colorierCase(new Vipere(c.x,c.y));
			if (a instanceof Renard) 
				champ.colorierCase(new Renard(c.x,c.y));
		}
	}
	
	/**
	 * Cette methode represente une journee de simulation en respectant les quatre etape demander dans le cahier de charge
	 */
	public void unJourDeSimulation() {
		ArrayList<Animal> listAnimal = (ArrayList<Animal>) this.getChamp().getListAnimalTrier();
		ArrayList<Animal> listProie;
		ArrayList<Animal> listAnimalMort = new ArrayList<Animal>();
		ArrayList<Animal> listAnimalNe = new ArrayList<Animal>();

		for (Animal a : listAnimal){
			//Etape 1
			listProie = this.getListProie(a);
			for(Animal p : listProie) {
				if(a.detecterProie() && !a.isaDetecter()) {
					a.setaDetecter(true);
					if(a.manger(p)) {
						listAnimalMort.add(p);
						//Mettre a 0 la date de drenier fois ou l'animal a manger
						a.setNbJourPasManger(0);
					}else {
						//Deplacer la proie si elle se fait pas manger par l'animal a
						this.getChamp().deplacerUneSeuleCase(p);
					}
				}
			}
			//Si l'animal ne detecte pas de paroie il passe a l'etape 2
			if(!a.isaDetecter()) {
				if(this.getChamp().getSaison().equals(Saison.valueOf("Printemps")) && this.getChamp().exiteCaseVide()) {
					if(this.VoisinMemeEspece(a)) {
						a.setReprodruire(true);
						if(a.reproduire()) {
							Animal ar = this.getVoisinMemeEspece(a);
							ar.setReprodruire(true);
							listAnimalNe.add(a);
						}
					}
				}
				//Si l'animal ne se reproduire il passe a l'etape 3 et 4
				if(!a.isReprodruire()){
					this.getChamp().seDeplacer(a);
					if(a.getCD() != -1) {
						if((a.getNbJourPasManger() >= a.getEA()) || (a.getNbJourVie() >= a.getEV())) {
							a.setCD(-1);
						}
					}else{
						if(a.getNbJourApresMort() == 10)
							listAnimalMort.add(a);
						else
							a.setNbJourApresMort(a.getNbJourApresMort()+1);
					}
				}
				//ajouter un jour pour l'animal qui n'a pas manger
				a.setNbJourPasManger(a.getNbJourPasManger()+1);
			}
			//ajouter un jour de vie a l'animal a
			a.setNbJourVie(a.getNbJourVie()+1);
		}
		//Incrementer le jour actuelle 
		this.getChamp().setJour(this.getChamp().getJour()+1);
		//Verifier si le nombre de jour arrive a 31 on le remit a 1 et on change de saison
		if(this.getChamp().getJour() > 30) {
			this.getChamp().setJour(1);
			switch (this.getChamp().getSaison()) {
	            case Hiver: 
	                this.getChamp().setSaison(Saison.valueOf("Printemps"));
	                break;
	
	            case Printemps: 
	            	this.getChamp().setSaison(Saison.valueOf("Ete"));
	                break;
	
	            case Ete:
	            	this.getChamp().setSaison(Saison.valueOf("Automne"));
	                break;
	                
	            case Automne: 
	            	this.getChamp().setSaison(Saison.valueOf("Hiver"));
	                break;
	
	            default:
	                System.out.println("Saison non reconue");
	                break;
	        }
		}
		//avant la fin de la journee 
		//on remet le variable boolean a false
		for (Animal a : listAnimal){
			a.setaDetecter(false);
			a.setReprodruire(false);
		}
		//on supprime les animaux qui sont mort (etait manger par un autre animal ou 10 apres la mort par vieillesse ou affame 
		for (Animal a : listAnimalMort) {
			this.getChamp().animalMort(a);
		}
		//on ajoute les animaux qui vient de naitre
		for (Animal a : listAnimalNe) {
			this.ajouterNewAnimal(a);
		}
		//metre a jour les couleurs
		for (Animal a : listAnimal){
			this.getChamp().colorierCase(a);
		}
	}
	
	/**
	 * Cette methode lance la simulation jusqu'a la fin
	 */
	public void simulationComplet() {
		//tant que la simulation n'est pas finie on repete une journee de simulation
		while(! this.simulationFinie()) {
			this.unJourDeSimulation();
			//Puis, pause de 1s
			try 
			{
				Thread.sleep(1000);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 * cette methode affiche le survivant si il y a
	 */
	public void gagnant() {
		int nbp = champ.getNbPoule();
		int nbr = champ.getNbRenard();
		int nbv = champ.getNbVipere();
		
		if(nbp<=0 && nbr<=0)
			System.out.println("Les Viperes qui ont survécu\n");
		
		else if(nbp<=0 && nbv<=0)
			System.out.println("Les Renards qui ont survécu\n");
		
		else if(nbr<=0 && nbv<=0)
			System.out.println("Les Poules qui ont survécu\n");
		else {
			System.out.println("Y'a pas de vainqueur, la Simulation est fini parce que y'a plus de case vide dans le champ");
			System.out.println("Nombre de Poule restant : "+this.getChamp().getNbPoule());
			System.out.println("Nombre de Renard restant : "+this.getChamp().getNbRenard());
			System.out.println("Nombre de Vipere restant : "+this.getChamp().getNbVipere());
		}
	}
}
