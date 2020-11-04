import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
 

/**
 *  Une grosse partie de se code a ete pris a partir de site openclassroom
 * 
 *
 */
public class Fenetre extends JFrame{
 
	//private JPanel pan = new JPanel();
	private JButton start = new JButton("Start");
	private JButton personaliser = new JButton("Personaliser");
	private JPanel container = new JPanel();
	private JFrame f = new JFrame();
	
	public Fenetre(){
	
		f.setTitle("Simulation Champ");
		f.setSize(600, 620);   
		//f.setLocationRelativeTo(null);
		
		container.setLayout(new BorderLayout());
		
		JTextArea area = new JTextArea();
		area.setBounds(15,15,570,590);
		area.append("\n\n  Bienvenue dans le simulateut de l’évolution d’animaux dans un champ fermé.\n\n"
					+"  Chaque animal pourra être une poule, un renard ou une vipère.\n\n"
					+"  Les poules mangent les vipères, les vipères piquent (et mangent) les renards,\n  et les renards mangent les poules.\n\n"
					+"  Pour commencer la simulation initialiser par defaut appuyer sur Start\n\n"
					+"  Pour commencer une simulationtion personaliser appuyer Personaliser \n\n"
					+"  Fait par :\t* BEN YAHIA Ilyes\n\t* EL AMRANI Ismail\n\n\n"
					+"  Malheuresement on a un probleme d'affichage quand on lance les simulations\n  a partir de cette fenetere,\n\n"
					+"  le champ ne s'affiche par a chaque jour mais il s'afficher a la fin quand\n  la simulation est finie\n\n"
					+"  Donc vu qu'on a pas pu resoudre le probleme on a fait un lancement\n  de la simulation dans le main qui demarre des l'execution du programme\n\n");
		Font police = new Font("Tahoma", Font.ITALIC, 16);
		area.setFont(police);
		container.add(area, BorderLayout.CENTER);
		
		
		
		//Ce sont maintenant nos classes internes qui écoutent nos boutons 
		start.addActionListener(new BoutonStart());
		personaliser.addActionListener(new BoutonPersonaliser());
		JPanel south = new JPanel(new FlowLayout());
		
		south.add(start);
		south.add(personaliser);
		container.add(south, BorderLayout.SOUTH);
		f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		f.setContentPane(container);
		f.setVisible(true);
	}
      
	//Classe écoutant notre premier bouton
	class BoutonStart implements ActionListener{
		//Redéfinition de la méthode actionPerformed()
		public void actionPerformed(ActionEvent arg0) {
			Simulation s = new Simulation(20,20,20,30,1);
			s.simulationComplet();
		}
	}
  
	//Classe écoutant notre second bouton
	class BoutonPersonaliser extends JFrame implements ActionListener{
		//Redéfinition de la méthode actionPerformed()
		public void actionPerformed(ActionEvent e) {
			this.setTitle("Personaliser Simulation");
			this.setSize(400, 300);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setLocationRelativeTo(null);
			
			//L' hauteur du champ
			JPanel panH = new JPanel();
			panH.setBackground(Color.white);
			JTextField h = new JTextField();
			h.setPreferredSize(new Dimension(100, 25));
			JLabel hLabel = new JLabel("Saisir l'hauteur du champ :   ");
			panH.add(hLabel);
			panH.add(h);
			    
			//La largeur du champ
			JPanel panL = new JPanel();
			panL.setBackground(Color.white);
			JTextField l = new JTextField();
			l.setPreferredSize(new Dimension(100, 25));
			JLabel lLabel = new JLabel("Saisir la largeur du champ : ");
			panL.add(lLabel);
			panL.add(l);
			    
			//Nombre de poule dans le champ
			JPanel panNbP = new JPanel();
			panNbP.setBackground(Color.white);
			JTextField nbP = new JTextField();
			nbP.setPreferredSize(new Dimension(100, 25));
			JLabel nbPLabel = new JLabel("Saisir le nombre de Poule :   ");
			panNbP.add(nbPLabel);
			panNbP.add(nbP);
			    
			//Nombre de Renard dans le champ
			JPanel panNbR = new JPanel();
			panNbR.setBackground(Color.white);
			JTextField nbR = new JTextField();
			nbR.setPreferredSize(new Dimension(100, 25));
			JLabel nbRLabel = new JLabel("Saisir le nombre de Renard :");
			panNbR.add(nbRLabel);
			panNbR.add(nbR);
			    
			//Nombre de Vipere dans le champ
			JPanel panNbV = new JPanel();
			panNbV.setBackground(Color.white);
			JTextField nbV = new JTextField();
			nbV.setPreferredSize(new Dimension(100, 25));
			JLabel nbVLabel = new JLabel("Saisir le nombre de Vipere : ");
			panNbV.add(nbVLabel);
			panNbV.add(nbV);			
			
			JPanel content = new JPanel();
			content.setBackground(Color.white);
			content.add(panH);
			content.add(panL);
			content.add(panNbP);
			content.add(panNbR);
			content.add(panNbV);
			
			JPanel control = new JPanel();
			JButton startPerso = new JButton("Start Personaliser");
			
			startPerso.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					Simulation s = new Simulation(Integer.valueOf(h.getText()), Integer.valueOf(l.getText()),Integer.valueOf(nbP.getText()), Integer.valueOf(nbR.getText()), Integer.valueOf(nbV.getText())); 
					s.simulationComplet();
					setVisible(false);
				}      
			});
			
			JButton cancelBouton = new JButton("Annuler");
			
			cancelBouton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
				}      
			});
			
			control.add(startPerso);
			control.add(cancelBouton);
			content.add(control);
			this.setContentPane(content);
			this.setVisible(true);
		}
	}      
}