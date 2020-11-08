package fr.calculatrice.grp12;

import java.util.ArrayList;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.geometry.*;

public class Vue extends Application implements IVue  {

	EventHandler<MouseEvent> handler;
	
	public Vue(EventHandler<MouseEvent> handler) {
	/*
	 * Enregistrer le controleur comme handler lors de la création de la vue	
	 */
		this.handler = handler;
	}
	
	
	@Override
	public void change(ArrayList<String> data) {
		// TODO changer la donnée de mon historique
		
	}


  @Override
   public void start(Stage primaryStage) throws Exception {
       GridPane root = new GridPane();
 
       root.setPadding(new Insets(20));
       root.setHgap(25);
       root.setVgap(15);
 
       Label labelTitle = new Label("XXXXXXXXXXXXXX");
 
       // Put on cell (0,0), span 2 column, 1 row.
       root.add(labelTitle, 0, 0, 4, 1);
 
       createbutton(root);
     
       Scene scene = new Scene(root, 300, 300);
       primaryStage.setTitle("Calculatrice");
       primaryStage.setScene(scene);
       primaryStage.show();
   }

    /**
     * @param args the command line arguments
     */
   
    public void createbutton(GridPane root){
        //DIGITS
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
        btn.addEventHandler(MouseEvent.MOUSE_CLICKED, this.handler);
        GridPane.setHalignment(btn, HPos.RIGHT);
        root.add(btn,0,4);
        
        //OPERATIONS
        String[] labelop={"+","-","x","/"};
        int i=1;
        for(String op : labelop) {
 	       Button btnop=new Button(op);
           btnop.addEventHandler(MouseEvent.MOUSE_CLICKED, this.handler);
 	       GridPane.setHalignment(btnop, HPos.RIGHT);
 	       root.add(btnop,3,i);
 	       i+=1;
 	       }
        
        //MANIP
        String[] labelmanip={"â†�","â†µ"};
        int k=1;
        for(String manip : labelmanip) {
 	       Button btnmanip=new Button(manip);
           btnmanip.addEventHandler(MouseEvent.MOUSE_CLICKED, this.handler);
 	       GridPane.setHalignment(btnmanip, HPos.RIGHT);
 	       root.add(btnmanip,k,4);
 	       k+=1;
 	       }
        
    }
    
	public static void main(String[] args) {
		Controleur controleur = new Controleur();
		Application.launch(Vue.class, args);
	}
    
}
