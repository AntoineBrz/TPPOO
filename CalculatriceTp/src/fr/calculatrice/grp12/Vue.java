package fr.calculatrice.grp12;

import java.util.ArrayList;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
			throw new Exception("Le controleur "
					+ "doit s'enregistrer "
					+ "en tant que handler");
	
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
         * dans le GridPane associé
         */
    	int s=0;
        for(int i=1;i<10;i++){
            String title=String.valueOf(i);
            Button btn=new Button(title);   
            btn.addEventHandler(MouseEvent.MOUSE_CLICKED, this.handler);
            
            if(s<3){
                GridPane.setHalignment(btn, HPos.RIGHT);
                root.add(btn,s,3);
                s+=1;
            }
            else if(s<6){
                GridPane.setHalignment(btn, HPos.RIGHT);
                root.add(btn,s-3,2);
                s+=1;
            }
            else if(s<9){
                GridPane.setHalignment(btn, HPos.RIGHT);
                root.add(btn,s-6,1);
                s+=1;
            }
         
         
           
        }
       
        Button btn=new Button("0"); 
        btn.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
        GridPane.setHalignment(btn, HPos.RIGHT);
        root.add(btn,0,4);
        
        //OPERATIONS
        String[] labelop={"+","-","x","/"};
        int i=1;
        for(String op : labelop) {
 	       Button btnop=new Button(op);
           btnop.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
 	       GridPane.setHalignment(btnop, HPos.RIGHT);
 	       root.add(btnop,3,i);
 	       i+=1;
 	       }
        
        //MANIP
        String[] labelmanip={"←","↵"};
        int k=1;
        for(String manip : labelmanip) {
 	       Button btnmanip=new Button(manip);
           btnmanip.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
 	       GridPane.setHalignment(btnmanip, HPos.RIGHT);
 	       root.add(btnmanip,k,4);
 	       k+=1;
 	       }
        
    }
    
    
    

	@Override
	public void affiche() throws Exception {
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