package application;

import java.util.ArrayList;
import java.util.Collections;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;

public class Main extends Application implements EventHandler<ActionEvent> {

	Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16;
	Button button;
	int max = 8;
	int temp1, temp2;
	ArrayList<cards> cards2;
	Scene scene;
	int clicked = 0;
	ArrayList<Button> tempb = new ArrayList<>();

	public static void main(String[] args) {
		launch(args);
	}

	final public ArrayList<cards> cardGenerator() {
		ArrayList<cards> cards = new ArrayList<>();
		for (int i = 1; i <= max; i++) {
			cards.add(new cards(i));
			cards.add(new cards(i));
		}
		Collections.shuffle(cards);

		return cards;
	}

	@Override
	public void start(Stage primaryStage) {

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(10);
		grid.setHgap(10);
		primaryStage.setTitle("Memory Game");
		cards2 = new ArrayList<>(cardGenerator());

		button = new Button();
		GridPane.setConstraints(button, 1, 6);
		button.setText("Play again");
		b1 = new Button();
		GridPane.setConstraints(b1, 0, 0);
		b1.setText("0");
		b2 = new Button();
		GridPane.setConstraints(b2, 0, 1);
		b2.setText("0");
		b3 = new Button();
		GridPane.setConstraints(b3, 0, 2);
		b3.setText("0");
		b4 = new Button();
		GridPane.setConstraints(b4, 0, 3);
		b4.setText("0");
		b5 = new Button();
		GridPane.setConstraints(b5, 1, 0);
		b5.setText("0");
		b6 = new Button();
		GridPane.setConstraints(b6, 1, 1);
		b6.setText("0");
		b7 = new Button();
		GridPane.setConstraints(b7, 1, 2);
		b7.setText("0");
		b8 = new Button();
		GridPane.setConstraints(b8, 1, 3);
		b8.setText("0");
		b9 = new Button();
		GridPane.setConstraints(b9, 2, 0);
		b9.setText("0");
		b10 = new Button();
		GridPane.setConstraints(b10, 2, 1);
		b10.setText("0");
		b11 = new Button();
		GridPane.setConstraints(b11, 2, 2);
		b11.setText("0");
		b12 = new Button();
		GridPane.setConstraints(b12, 2, 3);
		b12.setText("0");
		b13 = new Button();
		GridPane.setConstraints(b13, 3, 0);
		b13.setText("0");
		b14 = new Button();
		GridPane.setConstraints(b14, 3, 1);
		b14.setText("0");
		b15 = new Button();
		GridPane.setConstraints(b15, 3, 2);
		b15.setText("0");
		b16 = new Button();
		GridPane.setConstraints(b16, 3, 3);
		b16.setText("0");

		b1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {

			}
		});

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
		b9.setOnAction(this);
		b10.setOnAction(this);
		b11.setOnAction(this);
		b12.setOnAction(this);
		b13.setOnAction(this);
		b14.setOnAction(this);
		b15.setOnAction(this);
		b16.setOnAction(this);

		grid.getChildren().addAll(b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, button);
		scene = new Scene(grid, 220, 220);
		// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
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
			Collections.shuffle(cards2);
		}
		if (event.getSource() == b1) {
			b1.setText(Integer.toString(cards2.get(0).getNumfill()));
			if (!(tempb.contains(b1))) {
				tempb.add(b1);
				clicked++;
			}
		} else if (event.getSource() == b2) {
			b2.setText(Integer.toString(cards2.get(1).getNumfill()));
			if (!(tempb.contains(b2))) {
				tempb.add(b2);
				clicked++;
			}
		} else if (event.getSource() == b3) {
			b3.setText(Integer.toString(cards2.get(2).getNumfill()));
			if (!(tempb.contains(b3))) {
				tempb.add(b3);
				clicked++;
			}
		} else if (event.getSource() == b4) {
			b4.setText(Integer.toString(cards2.get(3).getNumfill()));
			if (!(tempb.contains(b4))) {
				tempb.add(b4);
				clicked++;
			}
		} else if (event.getSource() == b5) {
			b5.setText(Integer.toString(cards2.get(4).getNumfill()));
			if (!(tempb.contains(b5))) {
				tempb.add(b5);
				clicked++;
			}
		} else if (event.getSource() == b6) {
			b6.setText(Integer.toString(cards2.get(5).getNumfill()));
			if (!(tempb.contains(b6))) {
				tempb.add(b6);
				clicked++;
			}
		} else if (event.getSource() == b7) {
			b7.setText(Integer.toString(cards2.get(6).getNumfill()));
			if (!(tempb.contains(b7))) {
				tempb.add(b7);
				clicked++;
			}
		} else if (event.getSource() == b8) {
			b8.setText(Integer.toString(cards2.get(7).getNumfill()));
			if (!(tempb.contains(b8))) {
				tempb.add(b8);
				clicked++;
			}
		} else if (event.getSource() == b9) {
			b9.setText(Integer.toString(cards2.get(8).getNumfill()));
			if (!(tempb.contains(b9))) {
				tempb.add(b9);
				clicked++;
			}
		} else if (event.getSource() == b10) {
			b10.setText(Integer.toString(cards2.get(9).getNumfill()));
			if (!(tempb.contains(b10))) {
				tempb.add(b10);
				clicked++;
			}
		} else if (event.getSource() == b11) {
			b11.setText(Integer.toString(cards2.get(10).getNumfill()));
			if (!(tempb.contains(b11))) {
				tempb.add(b11);
				clicked++;
			}
		} else if (event.getSource() == b12) {
			b12.setText(Integer.toString(cards2.get(11).getNumfill()));
			if (!(tempb.contains(b12))) {
				tempb.add(b12);
				clicked++;
			}
		} else if (event.getSource() == b13) {
			b13.setText(Integer.toString(cards2.get(12).getNumfill()));
			if (!(tempb.contains(b13))) {
				tempb.add(b13);
				clicked++;
			}
		} else if (event.getSource() == b14) {
			b14.setText(Integer.toString(cards2.get(13).getNumfill()));
			if (!(tempb.contains(b14))) {
				tempb.add(b14);
				clicked++;
			}
		} else if (event.getSource() == b15) {
			b15.setText(Integer.toString(cards2.get(14).getNumfill()));
			if (!(tempb.contains(b15))) {
				tempb.add(b15);
				clicked++;
			}
		} else if (event.getSource() == b16) {
			b16.setText(Integer.toString(cards2.get(15).getNumfill()));
			if (!(tempb.contains(b16))) {
				tempb.add(b16);
				clicked++;
			}
		}
		if (clicked == 2) {
			Button tb1 = tempb.get(0);
			Button tb2 = tempb.get(1);
			if (tb1.getText().compareTo(tb2.getText()) == 0) {
				
			} else {		
				Timeline timeline = new Timeline(
					    new KeyFrame(Duration.seconds(1), e -> {
							tb1.setText("0");
							tb2.setText("0");
					    })
					);
					timeline.play();
			}
			tempb.removeAll(tempb);
			clicked = 0;
		}

	}

	public class cards {
		private int numfill;

		cards(Integer num) {
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