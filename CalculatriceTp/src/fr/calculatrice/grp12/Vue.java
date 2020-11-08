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
	
	public void init() throws Exception {
		if (Vue.handler==null) 
			throw new Exception("Le controleur "
					+ "doit s'enregistrer "
					+ "en tant que handler");
		
	}
	
	
	@Override
	public void change(ArrayList<String> data) {
		// TODO changer la donnée de mon historique
		
	}
	
	
    @Override
    public void start(Stage primaryStage) throws Exception {
    	VBox rootbox= new VBox();
    	rootbox.setSpacing(10);
        rootbox.setPadding(new Insets(15,20, 10,10));
    
    	//Afficheurs
    	Label z=new Label("Z ="+" valeur de z");
    	z.setBackground(new Background(new BackgroundFill(Color.rgb(200, 200, 200, 1), null, null)));
    	rootbox.getChildren().add(z);
    	
    	Label y=new Label("Y ="+" valeur de y");
    	y.setBackground(new Background(new BackgroundFill(Color.rgb(200, 200, 200, 1), null, null)));
    	rootbox.getChildren().add(y);
    	
    	Label x=new Label("X ="+" valeur de x");
    	x.setBackground(new Background(new BackgroundFill(Color.rgb(200, 200, 200, 1), null, null)));
    	rootbox.getChildren().add(x);
    	//GridPane
    	GridPane rootGrid = new GridPane();
        createbutton(rootGrid);
        rootbox.getChildren().add(rootGrid);
     
       Scene scene = new Scene(rootbox, 300, 300);
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
        String[] labelmanip={"←","↵"};
        int k=1;
        for(String manip : labelmanip) {
 	       Button btnmanip=new Button(manip);
           btnmanip.addEventHandler(MouseEvent.MOUSE_CLICKED, this.handler);
 	       GridPane.setHalignment(btnmanip, HPos.RIGHT);
 	       root.add(btnmanip,k,4);
 	       k+=1;
 	       }
        
    }
    
    
    

	@Override
	public void affiche() throws Exception {
		launch();
	}
    
}