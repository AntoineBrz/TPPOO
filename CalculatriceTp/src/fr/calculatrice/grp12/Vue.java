package fr.calculatrice.grp12;

import java.util.ArrayList;


import javafx.event.EventHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.geometry.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author Groupe 12 : ANDRIANASOLO et BRUEZ
 */
public class Vue extends Application  implements IVue  {
    
	private static final double LARGEUR = 600;
	private static final double HAUTEUR_RESULTAT = 30;
	private static final double HAUTEUR = 400;	
	private static final double MARGE = 10;
	
	public static EventHandler<MouseEvent> handler;
	ArrayList<String> memoireResultats = new ArrayList<>();
	private static Label x,y,z;

	/**
	 * Constructeur appelé par l'application JavaFX.
	 * Initialise l'historique de calcul et le champ de saisie.
	 */
	public Vue() {
		// Il y a 1 champ de saisie, et 2 labels pour l'historique
		memoireResultats.add("");
		memoireResultats.add("");
		memoireResultats.add("");
	}
	
	/**
	 * Constructeur de la vue qui enregistre le contrôleur
	 * en tant que handler pour les actions liées aux boutons.
	 * L'appel de ce constructeur sert uniquement à cet enregistrement.
	 * L'Application de JavaFX appelera le constructeur Vue()
	 * mais à ce moment là le handler de l'application (handler statique)
	 * aura déjà été enregistré grâce à l'appel de ce constructeur ici-présent 
	 * par le contrôleur.
	 * 
	 * @param eventHandler  le contrôleur qui s'enregistre en tant que handler des actions
	 * liées au bouton
	 */
	public Vue(EventHandler<MouseEvent> eventHandler) {
		Vue.handler = eventHandler;
	}

	public void init() throws Exception {
		if (Vue.handler==null) 
			throw new Exception("Vue doit enregistrer un handler "
					+ "pour les actions des boutons");
	}
	
	/**
	 * Organisation des conteneurs :
	 * Une VBox principale contenant 4 "bandes" horizontales
	 * prenant toute la largeur de l'écran.
	 * La dernière bande contient toutes les touches
	 * de la calculatrice, disposées dans un GridBox.
	 * Les autres contiennent la saisie en cours
	 * ou les saisies précédentes.
	 */
    @Override
    public void start(Stage primaryStage) throws Exception {
    	//Création d'une "boîte verticale" VBox
		VBox rootBox= new VBox();
		rootBox.setSpacing(10);
	    rootBox.setPadding(new Insets(MARGE));
	
		// Déclaration et création des Afficheurs, nommés X, Y, Z de bas en haut (X est le champ de saisie)
		// Afficheur Z
	    z=new Label("Z = " + memoireResultats.get(2));
		z.setMinSize(LARGEUR, HAUTEUR_RESULTAT);
		z.setBackground(new Background(
				new BackgroundFill(Color.rgb(200, 200, 200, 1), null, null))
				);
		rootBox.getChildren().add(z);
		// Afficheur Y
		y=new Label("Y = " + memoireResultats.get(1));
		y.setMinSize(LARGEUR, HAUTEUR_RESULTAT);
		y.setBackground(new Background(
				new BackgroundFill(Color.rgb(200, 200, 200, 1), null, null))
				);
		rootBox.getChildren().add(y);
		// Afficheur X
		x=new Label("X = " + memoireResultats.get(0));
		x.setMinSize(LARGEUR, HAUTEUR_RESULTAT);
		x.setBackground(new Background(
				new BackgroundFill(Color.rgb(200, 200, 200, 1), null, null))
				);
		rootBox.getChildren().add(x);
	
		// Une Grille : GridPane pour disposer tous les boutons
		GridPane numbersGrid = new GridPane();
	    positionnementBoutons(numbersGrid);
	    rootBox.getChildren().add(numbersGrid);
	    
	    // Petit texte en bas de la vue 
	    Label copyrights = new Label("Andrianasolo & Bruez Inc., tous droits réservés");
	    copyrights.snapPositionX(LARGEUR/2);
	    rootBox.getChildren().add(copyrights);
     
	    // Création de la Scene principale qui contiendra notre VBox
        Scene scene = new Scene(rootBox, LARGEUR+2*MARGE, HAUTEUR+MARGE);
        primaryStage.setTitle("⚘ Polish Flower Calculator ⚘ ");
        primaryStage.setScene(scene);
        primaryStage.show();
   }

    // Méthode d'automatisation de la création des boutons 
    public Button createButton(String title, Double largeur, Double hauteur, String couleurHexa,Boolean background) {
    	Button btn =new Button(title);
    	btn.setMinSize(largeur, hauteur);
    	if(background) {
    		btn.setBackground(new Background(new BackgroundFill(Color.web(couleurHexa),new CornerRadii(15),null)));
    		
    	}
    	btn.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
    	return btn;
    }    
    
    // Méthode d'automatisation de positionnement des boutons 
    public void positionnementBoutons(GridPane root){
        /*
         * Crée et dispose les boutons pour les chiffres de 0 à 9
         * dans le GridPane associé.
         */
        for(int i=1;i<10;i++){
            String title=String.valueOf(i);
            Button btn=createButton(title,LARGEUR/4,(HAUTEUR-4*HAUTEUR_RESULTAT-60)/5,"d5def6",false);            
           
            if(i<4){
            	// de 1 à 3 : tout en haut, en ligne 3 du gridPane
                root.add(btn,i-1,3);
            }
            else if(i<7){
            	// de 4 à 6 : en ligne 2 du gridPane
                root.add(btn,i-4,2);
            }
            else if(i<10){
            	// de 7 à 9 : tout en bas en ligne 1 du gridPane
                root.add(btn,i-7,1);
            }
        }
       
        Button btn = createButton("0",LARGEUR/4,(HAUTEUR-4*HAUTEUR_RESULTAT-60)/5,"d5def6",false); 
        GridPane.setHalignment(btn, HPos.RIGHT);
        root.add(btn,0,4);
        
        // Positionne les boutons d'opérations 
        String[] btnsOperation={"+","-","x","/"};
        int row=1;
        for(String op : btnsOperation) {
 	       Button btnOperation = createButton(op,LARGEUR/4,(HAUTEUR-4*HAUTEUR_RESULTAT-60)/5,"d5def6",false); 
 	       root.add(btnOperation,3,row);
 	       row+=1;
 	    }
        
        // Positionne les touches retour et entrée
        String[] btnsBackEnter={"←",".","↵"};
        int [] col= {1,4};
        for(int i=0;i<2;i++) {
        	 	Button btnManip = createButton(btnsBackEnter[i],LARGEUR/4,(HAUTEUR-4*HAUTEUR_RESULTAT-60)/5,"d5def6",false); 
        		root.add(btnManip,col[0],col[1]);
        		col[0]= 2;
     	      	}
        Button btnManip = createButton(btnsBackEnter[2],LARGEUR,(HAUTEUR-4*HAUTEUR_RESULTAT-60)/5,"a2cdfa",true); 
        root.add(btnManip, 0,5,4,1);
        	
        	      
    }
    
    
    
	/**
	 * Lance l'application JavaFx dans un contexte statique.
	 */
	@Override
	public void affiche() throws Exception {
		launch();
	}

	/**
	 * Actualiser la saisie en cours ou afficher le nouveau résultat de calcul
	 */
	@Override
	public void changeX(String current) {
		x.setText("X = "+current);
	}
    
	/**
	 * Le changement des deux labels à partir du haut de
	 * l'écran se fait grâce à la liste contenant ces valeurs.
	 * Cette liste de deux valeurs est donnée par le contrôleur 
	 * . Elle permet d'intégrer les nouveaux calculs et les nouvelles entrées).
	 */
	@Override
	public void change(ArrayList<String> newData) {
		z.setText("Z = "+newData.get(0));
		y.setText("Y = "+newData.get(1));
		}
		
	
	
	
}