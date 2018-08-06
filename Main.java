package application;
	
import java.util.ArrayList;
import java.util.Collections;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class bb extends Application implements EventHandler<ActionEvent> {

	Button b1,b2,b3,b4,b5,b6,b7,b8;
    Button button;
    int min = 1, max = 8;
    int temp1, temp2;    
    ArrayList<cards> cards2;
    Scene scene;
    int clicked = 0;


    public static void main(String[] args) {
        launch(args);
    }
    public ArrayList<cards> cardGenerator() {
    	ArrayList<cards> cards = new ArrayList<>();
        for (int i = 1; i == max; i++){
            cards.add(new cards(i));
            cards.add(new cards(i));
        }
        Collections.shuffle(cards);
        
        return cards;
    }
    @Override
    public void start(Stage primaryStage) {
    	
        GridPane grid =new GridPane();
        grid.setPadding( new Insets(10,10,10,10));
        grid.setVgap(10);
        grid.setHgap(10);
        primaryStage.setTitle("Memory Game");
        cards2 = cardGenerator();
        
        button = new Button();
        GridPane.setConstraints(button,6,6);
        button.setText("Play again");
        b1 = new Button();
        GridPane.setConstraints(b1,0,0);
        b1.setText("0");      
        b2 = new Button();
        GridPane.setConstraints(b2,0,1);
        b2.setText("0");   
        b3 = new Button();
        GridPane.setConstraints(b3,0,2);
        b3.setText("0");  
        b4 = new Button();
        GridPane.setConstraints(b4,0,3);
        b4.setText("0");
        b5 = new Button();
        GridPane.setConstraints(b5,1,0);
        b5.setText("0");
        b6 = new Button();
        GridPane.setConstraints(b6,1,1);
        b6.setText("0");
        b7 = new Button();
        GridPane.setConstraints(b7,1,2);
        b7.setText("0");
        b8 = new Button();
        GridPane.setConstraints(b8,1,3);
        b8.setText("0");
        
        
          b1.onActionProperty();
          button.setOnAction(this);
          b1.setOnAction(this);
          b2.setOnAction(this);
          b3.setOnAction(this);
          b4.setOnAction(this);
          b5.setOnAction(this);
          b6.setOnAction(this);
          b7.setOnAction(this);
          b8.setOnAction(this);

          
        
        grid.getChildren().addAll(button,b1,b2,b3,b4,b5,b6,b7,b8);
        scene = new Scene(grid, 250, 300);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
		
        primaryStage.show();
    }

 
    @Override
    public void handle(ActionEvent event) {
    	if (event.getSource() == button) {
    		b1.setText("0");
    		b2.setText("0");
    		b3.setText("0");
    		b4.setText("0");
    		b5.setText("0");
    		b6.setText("0");
    		b7.setText("0");
    		b8.setText("0");
    		}
    	if (event.getSource() == b1) {
    		b1.setText(cards2.get(0).toString());
    		
    	if (event.getSource() == b2) {
    		
    		}
    	if (event.getSource() == b3) {

			}
    	if (event.getSource() == b4) {

			}
		if (event.getSource() == b5) {

		}
		if (event.getSource() == b6) {

		}
		if (event.getSource() == b7) {

		}
		if (event.getSource() == b8) {

		}

    	}
    }
    

	public class cards{
    	private int numfill;
    	cards(Integer num){
    		setNumfill(num);
    	}
		public int getNumfill() {
			return numfill;
		}
		public void setNumfill(int numfill) {
			this.numfill = numfill;
		}
    }
	
}
