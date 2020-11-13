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
 * @author 
 */
public class Vue extends Application  implements IVue  {
    
	public static EventHandler<MouseEvent> handler;
	ArrayList<String> memoireResultats = new ArrayList<>();
	private static Label x,y,z;

	public Vue() {
		/*
		 * Constructeur appelé par l'application JavaFX.
		 * Initialise l'historique de calcul et le champ de saisie.
		 */
		// Il y a 1 champ de saisie, et 2 labels pour l'historique
		memoireResultats.add("");
		memoireResultats.add("");
		memoireResultats.add("");
	}
	
	public Vue(EventHandler<MouseEvent> eventHandler) {
		/*
		 * Constructeur de la vue qui enregistre le contrôleur
		 * en tant que handler pour les actions liées aux boutons.
		 * L'appel de ce constructeur sert uniquement à cet enregistrement.
		 * L'Application de JavaFX appelera le constructeur Vue()
		 * mais à ce moment là le handler de l'application (handler statique)
		 * aura déjà été enregistré grâce à l'appel de ce constructeur ici-présent 
		 * par le contrôleur.
		 */
		Vue.handler = eventHandler;
	}

	public void init() throws Exception {
		if (Vue.handler==null) 
			throw new Exception("Vue doit enregistrer un handler "
					+ "pour les actions des boutons");
	}
	
	
    @Override
    public void start(Stage primaryStage) throws Exception {
    	/*
    	 * Organisation des conteneurs :
    	 * Une VBox principale contenant 4 "bandes" horizontales
    	 * prenant toute la largeur de l'écran.
    	 * La dernière bande contient toutes les touches
    	 * de la calculatrice, disposées dans un GridBox.
    	 * Les autres contiennent la saisie en cours
    	 * ou les saisies précédentes.
    	 */
		VBox rootBox= new VBox();
		rootBox.setSpacing(10);
	    rootBox.setPadding(new Insets(15,20, 10,10));
	
		// Afficheurs, nommés X, Y, Z de bas en haut (X est le champ de saisie)
		z=new Label("Z = " + memoireResultats.get(2));
		z.setBackground(new Background(
				new BackgroundFill(Color.rgb(200, 200, 200, 1), null, null))
				);
		rootBox.getChildren().add(z);
		
		y=new Label("Y = " + memoireResultats.get(1));
		y.setBackground(new Background(
				new BackgroundFill(Color.rgb(200, 200, 200, 1), null, null))
				);
		rootBox.getChildren().add(y);
		
		x=new Label("X = " + memoireResultats.get(0));
		x.setBackground(new Background(
				new BackgroundFill(Color.rgb(200, 200, 200, 1), null, null))
				);
		rootBox.getChildren().add(x);
	
		// Un tableau : GridPane pour disposer les chiffres de 0 à 9
		GridPane numbersGrid = new GridPane();
	    createZeroToNine(numbersGrid);
	    rootBox.getChildren().add(numbersGrid);
     
	    // Scene principale
        Scene scene = new Scene(rootBox, 300, 300);
        primaryStage.setTitle("Calculatrice");
        primaryStage.setScene(scene);
        primaryStage.show();
   }

   
    public void createZeroToNine(GridPane root){
        /*
         * Crée et dispose les boutons pour les chiffres de 0 à 9
         * dans le GridPane associé.
         */
        for(int i=1;i<10;i++){
            String title=String.valueOf(i);
            Button btn=new Button(title);   
            btn.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
            
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
       
        Button btn=new Button("0"); 
        btn.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
        GridPane.setHalignment(btn, HPos.RIGHT);
        root.add(btn,0,4);
        
        //OPERATIONS
        String[] btnsOperation={"+","-","x","/"};
        int row=1;
        for(String op : btnsOperation) {
 	       Button btnOperation=new Button(op);
           btnOperation.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
 	       GridPane.setHalignment(btnOperation, HPos.RIGHT);
 	       root.add(btnOperation,3,row);
 	       row+=1;
 	    }
        
        //AUTRES TOUCHES de manip. : retour et entrée
        String[] btnsBackEnter={"←","↵"};
        int col=1;
        for(String manip : btnsBackEnter) {
 	       Button btnManip=new Button(manip);
           btnManip.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
 	       GridPane.setHalignment(btnManip, HPos.RIGHT);
 	       root.add(btnManip,col,4);
 	       col+=1;
 	    }
        
    }
    
    
    

	@Override
	public void affiche() throws Exception {
		/*
		 * Lance l'application JavaFx dans un contexte statique.
		 */
		launch();
	}


	@Override
	public void changeX(String current) {
		/*
		 * TODO : doc ici
		 */
		x.setText(current);
	}
    
	
	@Override
	public void change(ArrayList<String> newData) {
		/* TODO : changer cette description
		 * z à changer lorsqu'un nouveau calcul est fait 
		 * newData contient soit 1 nouveau nombre en entrée, 
		 * soit deux valeurs : l'ancienne valeur en y, la nouvelle valeur en x
		 */
		z.setText(newData.get(0));
		y.setText(newData.get(1));
		}
		
	
	
	
}