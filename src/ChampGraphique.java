import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Merci à StackOverflow pour sa précieuse contribution !<br><br>
 *
 * Cette class cree un champ graphique, elle a comme attribut : <br>
 * 	* largeur : largeur du champ <br>
 *	* hauteur : hauteur du champ <br>
 *	* nbPoule : Nombre de Poule sur le champ<br>
 *	* nbRenard : Nombre de Renard sur le champ<br>
 *	* nbVipere : Nombre de Vipere sur le champ<br>
 *	* jour : le jour <br>
 *	* saison : la saison actuelle <br>
 *	* listeAnimal : un ArrayList qui contient la liste des animaux dans le champ<br>
 *	* hiver, printemps, ete, automne : quatre image contient chacune une image qui represente la saison 
 */

public class ChampGraphique extends JPanel
{

	private int largeur, hauteur, nbPoule, nbRenard, nbVipere;
	private int jour;
	private Saison saison;
	private BufferedImage hiver, printemps, ete, automne;
	private List<Animal> listeAnimal;

	/**
	 * Constructeur qui cree une fenetre avec un champ
	 * @param largeur La largeur (en nombre de cases) de la grille affichée.
	 * @param hauteur La hauteur (en nombre de cases) de la grille affichée.
	 * @param nbp Nombre de Poule sur le champ
	 * @param nbr Nombre de Renard sur le champ
	 * @param nbv Nombre de Vipere sur le champ
	 * @param j le jour actuelle
	 * @param s la saison actuelle
	 */
	public ChampGraphique(int largeur, int hauteur, int nbp, int nbr, int nbv, int j, String s) 
	{
		this.largeur = largeur;
		this.hauteur = hauteur;
		nbPoule = 0;
		nbRenard = 0;
		nbVipere = 0;
		listeAnimal = new ArrayList<Animal>();
		initialiserChampAvecAnimal (nbp, nbr, nbv);
		jour = j;
		saison = Saison.valueOf(s);
		try {
			hiver = ImageIO.read(new File("hiver.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			printemps = ImageIO.read(new File("printemps.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ete = ImageIO.read(new File("ete.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			automne = ImageIO.read(new File("automne.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JFrame window = new JFrame();		
		window.setTitle("Simulation Champ");
		window.setSize(largeur*15+50, hauteur*15+400);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setLocationRelativeTo(null);
		window.add(this);
		window.setVisible(true);
	}
	
	/**
	 * Cette methode initialise le champ avec les animaux en les ajoutant dans des case aleatoire
	 * @param nbp Nombre de Poule sur le champ
	 * @param nbr Nombre de Renard sur le champ
	 * @param nbv Nombre de Vipere sur le champ
	 */
	private void initialiserChampAvecAnimal (int nbp, int nbr, int nbv){
		Point p,r,v;
		int i;
		for(i=0; i<nbp; i++){	
			//on tire une case au hasard dans la grille
			p = getCaseVide();
			//et on la colorie en jaune
			this.colorierCase(new Poule(p.x, p.y));
		}
		for(i=0; i<nbr; i++)
		{
			//on tire une case au hasard dans la grille
			r = getCaseVide();
			//et on la colorie en orange
			this.colorierCase(new Renard(r.x, r.y));
		}
		for(i=0; i<nbv; i++)
		{
			//on tire une case au hasard dans la grille
			v = getCaseVide();
			//et on la colorie en vert
			this.colorierCase(new Vipere(v.x, v.y));
		}
	}

	@Override
	/**
	 * Fonction d'affichage de la grille.
	 */
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		for (Animal a : listeAnimal) 
		{
			int cellX = 15 + (a.getPosition().x * 15);
			int cellY = 15 + (a.getPosition().y * 15);
			if(a instanceof Poule) {
				g.setColor(Color.YELLOW);
			}
			if(a instanceof Renard) {
				g.setColor(Color.ORANGE);
			}
			if(a instanceof Vipere) {
				g.setColor(Color.GREEN);
			}
			g.fillRect(cellX, cellY, 15, 15);
		}
		g.setColor(Color.BLACK);
		
		for (int i = 15; i <= largeur*15+15; i += 15) {
			g.drawLine(i, 15, i, hauteur*15+15);
		}

		for (int i = 15; i <= hauteur*15+15; i += 15) {
			g.drawLine(15, i, largeur*15+15, i);
		}
		
		//g.drawRect(15, hauteur*15+50, largeur*15, 50);
		switch (saison) {
	        case Hiver: 
	        	g.drawImage(hiver, 15, hauteur*15+50, largeur*15, 250, null);
	            break;
	
	        case Printemps: 
	        	g.drawImage(printemps, 15, hauteur*15+50, largeur*15, 250, null);
	        	break;
	
	        case Ete:
	        	g.drawImage(ete, 15, hauteur*15+50, largeur*15, 250, null);
	        	break;
	            
	        case Automne: 
	        	g.drawImage(automne, 15, hauteur*15+50, largeur*15, 250, null);
	        	break;
	
	        default:
	            System.out.println("Saison non reconue");
	            break;
		}
	}

	/**
	 * Methode permettant d'ajouter un animale a à la liste des animaux si 'il existe pas et colorie sa case avec le couleur qui convient<br>
	 * Jaune = Poule<br>
	 * Orange = Renard<br>
	 * Vert = Vipere<br>
	 * @param a animal ajouter a la liste
	 */
	public void colorierCase(Animal a) 
	{
		if(! listeAnimal.contains(a)) {
			listeAnimal.add(a);
			if(a instanceof Poule)
				setNbPoule(this.getNbPoule()+1);
			if(a instanceof Renard)
				setNbRenard(this.getNbRenard()+1);
			if(a instanceof Vipere)
				setNbVipere(this.getNbVipere()+1);
		}
		repaint();
	}
	
	/**
	 * Accesseur.
	 * @return Renvoie la largeur de la grille
	 */
	public int getLargeur()
	{
		return largeur;
	}
	
	/**
	 * Accesseur.
	 * @return Renvoie la hauteur de la grille
	 */
	public int getHauteur()
	{
		return hauteur;
	}
	
	/**
	 * Accesseur.
	 * @return Renvoie le jour 
	 */
	public int getJour() {
		return jour;
	}
	
	/**
	 * Accesseur.
	 * @return Renvoie la saison
	 */
	public Saison getSaison() {
		return saison;
	}
	
	/**
	 * Accesseur.
	 * @return Renvoie la liste des animaux dans le champ
	 */
	public List<Animal> getListAnimal(){
		return listeAnimal;
	}
	
	/**
	 * Accesseur.
	 * @return Renvoie la liste des animaux triée
	 */
	public List<Animal> getListAnimalTrier(){
		Collections.sort(listeAnimal);
		return listeAnimal;
	}
	
	/**
	 * Accesseur.
	 * @return Renvoie le nombre de poule
	 */
	public int getNbPoule() {
		return nbPoule;
	}
	
	/**
	 * Accesseur.
	 * @return Renvoie le nombre de renard
	 */
	public int getNbRenard() {
		return nbRenard;
	}
	
	/**
	 * Accesseur.
	 * @return Renvoie le nombre de vipere
	 */
	public int getNbVipere() {
		return nbVipere;
	}

	/**
	 * Mutateur,mettre a jour le nombre de poule par la nouvelle valeur
	 * @param nbPoule 
	 */
	public void setNbPoule(int nbPoule) {
		this.nbPoule = nbPoule;
	}

	/**
	 * Mutateur,mettre a jour le nombre de Renard par la nouvelle valeur
	 * @param nbRenard 
	 */
	public void setNbRenard(int nbRenard) {
		this.nbRenard = nbRenard;
	}
	
	/**
	 * Mutateur,mettre a jour le nombre de Vipere par la nouvelle valeur
	 * @param nbVipere 
	 */
	public void setNbVipere(int nbVipere) {
		this.nbVipere = nbVipere;
	}
	
	/**
	 * Mutateur,mettre a jour le jour actuelle par la nouvelle valeur
	 * @param jour 
	 */
	public void setJour(int jour) {
		this.jour = jour;
	}
	
	/**
	 * Mutateur,mettre a jour la saison actuelle par la nouvelle valeur
	 * @param saison 
	 */
	public void setSaison(Saison saison) {
		this.saison = saison;
	}
	
	//**********************************************************************************************************
	
	/**
	 * cette methode supprime l'animal p de la liste des animaux et libere la case dans le champ
	 * @param p animal a supprimer
	 */
	public void animalMort(Animal p) {
		//On supprime l'animal p de la liste des animaux grace à remove
		if(listeAnimal.remove(p)) {
			//Si la supprision est faite on mis a jour les nombres des animaux dans le champ
			if(p instanceof Poule)
				setNbPoule(this.getNbPoule()-1);
			if(p instanceof Renard)
				setNbRenard(this.getNbRenard()-1);
			if(p instanceof Vipere)
				setNbVipere(this.getNbVipere()-1);
		}
	}
	
	/**
	 * cette methode renvoi true si il existe encore des case vide dans le champ sinon false
	 * @return true si il existe encore des cases vide sinon false
	 */
	public boolean exiteCaseVide() {
		//on verifie si la taille de la liste des animaux est plus petite que haueur*largeur
		if(listeAnimal.size()<(hauteur*largeur))
			return true;
		return false;
	}
	
	/**
	 * cette fonction prend deux entiers a,b et verifie si la case(a,b) est vide ou non
	 * @param a : abscisse
	 * @param b : ordonné 
	 * @return true si la case(a,b) est vide sinon false
	 */
	public boolean verifiercasevide(int a,int b) {
		for(Animal p: listeAnimal) {
			//on verifie si les positions de chaque animal correspondent à la position(a,b)
			if(p.getPosition().x==a && p.getPosition().y==b) {
				return false;
			}
		}
		//on vérifie si a est entre 0 et largeur et si b est entre 0 et hauteur
		if(a<0 || a>=largeur || b<0 || b>=hauteur)
			return false;
		return true;
	}
	
	/**
	 * Cette methode renvoi une case vide dans le champ
	 * @return un Point vide dans le champ
	 */
	public Point getCaseVide() {
		Random r=new Random();
		//on verifie si y'a une case vide dans le champ
		if(exiteCaseVide()) {
			//tant que il trouve pas une case vide
			while(true) {
				int x = r.nextInt(largeur);
				int y = r.nextInt(hauteur);
				if(verifiercasevide(x,y)) {
					//des qu'il trouve une vide il renvoi le point
					return new Point(x,y);
				}
			}
		}
		//si il existe pas de case vide on retourn null
		return null;
	}
	
	/**
	 * cette fonction prend en parametre un animal et renvoi la liste des cases vides voisines  à sa propre case
	 * @param a l'animal autour de qui la methode cherche des case vide
	 * @return la liste des cases vides voisines  à la case de l'animal a si il  ya pas de case vide elle renvoi une liste qui contient une seule case celle de l'animal
	 */
	public ArrayList<Point> getListCaseVideVoisine(Animal a){
		ArrayList<Point> lp = new ArrayList<Point>();
		int i,j;
		//on parcourt les 8 cases voisines à la case d'animal a
		for(i=a.getPosition().x-1;i <= a.getPosition().x+1;i++) {
			for(j=a.getPosition().y-1;j <= a.getPosition().y+1;j++) {
				//si on trouve une case vide on l'ajoute a la liste lp
				if(verifiercasevide(i,j)) {
					lp.add(new Point(i,j));
				}
			}
		}
		//Si la liste lp est vide ca vaut dire y'a pas de case vide voisin donc l'animal donc il ne bouge pas et on renvoi ca une liste qui contient ca propre case
		if(lp.size()==0) {
			lp.add(new Point(a.getPosition().x,a.getPosition().y));
		}
		return lp;
	}
	
	
	/**
	 * Cette methode deplace l'animal a N fois dans le champ avec N = ca vitesse de déplacement
	 * @param a l'animal a deplacer
	 */
	public void seDeplacer(Animal a) {
		// on repete l'operation N fois avec N=vitesse de deplacement=a.getCD()
		for( int i=0;i< a.getVD();i++) {
			deplacerUneSeuleCase(a);
		}
	}
	
	/**
	 * cette fonction prend en parametre un animal et le deplace dans une case voisine a ca case qu'est vide sinon il reste a sa place
	 * @param a l'animal a deplacer
	 */
	public void deplacerUneSeuleCase(Animal a) {
		Random r=new Random();
		//on appelle la fonction getListCaseVideVoisine(p) pour avoir la liste des cases vides voisines à la case de a
		ArrayList<Point> lp = getListCaseVideVoisine(a);
		//on tire un nombre j entre 0 et la taille de lp-1
		int j = r.nextInt(lp.size());
		//on recupere le point p situé dans la position j de la liste
		Point p = lp.get(j);
		//on change la position de a au nouvel point recupere p 
		a.setPosition(p.x, p.y);
	}
}