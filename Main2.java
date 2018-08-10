
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.Scanner;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Alert;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Main2 extends Application implements EventHandler<ActionEvent> {
	GridPane grid;

	Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16;
	Button button;
	Button button2;
	int max = 8, score = 0;
	int temp1, temp2;
	ArrayList<cards> cards2;
	int clicked = 0;
	ArrayList<Button> tempb = new ArrayList<>();
	ArrayList<String> diro = new ArrayList<>();
	Text text;
	Timeline timeline;
	int mins = 0, secs = 0, millis = 0;
	boolean sos = true;

	public static void main(String[] args) {
		launch(args);
	}

	void change(Text text) {
		if (millis == 100) {
			secs++;
			millis = 0;
		}
		if (secs == 60) {
			mins++;
			secs = 0;
		}
		text.setText((((mins / 10) == 0) ? "0" : "") + mins + ":" + (((secs / 10) == 0) ? "0" : "") + secs + ":"
				+ (((millis / 10) == 0) ? "0" : "") + millis++);
	}

	public void WinWindow() throws IOException {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("You have won");
		dialog.setHeaderText(String.format("You have won, with a time of: %s .\n Do went to save it as highscore? ",
				text.getText()));
		dialog.setContentText("Please enter your name:");

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			if (result.get().isEmpty() || result.get().contains(" ") || result.get().length() > 8) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Wrong name format");
				alert.setHeaderText(null);
				alert.setContentText("An invalid name has been entered!\nTry agine.");
				alert.showAndWait();
				WinWindow();
			} else {
				ArrayList<HighScorers> tempHS = new ArrayList<>();
				FileInputStream inSC = new FileInputStream("highscores.txt");
				Scanner FileScannerC = new Scanner(inSC);
				while (FileScannerC.hasNextLine()) {
					tempHS.add(new HighScorers(FileScannerC.next(), FileScannerC.next()));
				}
				FileScannerC.close();
				tempHS.add(new HighScorers(result.get(), text.getText()));
				Collections.sort(tempHS);
				PrintWriter pwriter = new PrintWriter("highscores.txt");
				for (int i = 0; i <= tempHS.size() - 2; i++) {
					pwriter.println(String.format("%-8s %s", tempHS.get(i).getName(), tempHS.get(i).getTime()));
				}
				pwriter.print(String.format("%-8s %s", tempHS.get(tempHS.size() - 1).getName(),
						tempHS.get(tempHS.size() - 1).getTime()));
				pwriter.close();

			}
		}
	}

	final public ArrayList<cards> cardGenerator() {
		ArrayList<cards> cards = new ArrayList<>();
		for (int i = 1; i <= max; i++) {
			cards.add(new cards(switcher(i)));
			cards.add(new cards(switcher(i)));
		}
		Collections.shuffle(cards);

		return cards;
	}

	public String switcher(int i) {
		String imgDirectory = null;
		switch (i) {
		case 1:
			imgDirectory = "file:1.png";
			break;
		case 2:
			imgDirectory = "file:2.png";
			break;
		case 3:
			imgDirectory = "file:3.png";
			break;
		case 4:
			imgDirectory = "file:4.png";
			break;
		case 5:
			imgDirectory = "file:5.png";
			break;
		case 6:
			imgDirectory = "file:6.png";
			break;
		case 7:
			imgDirectory = "file:7.png";
			break;
		case 8:
			imgDirectory = "file:8.png";
			break;
		}
		return imgDirectory;
	}

	@Override
	public void start(Stage primaryStage) throws IOException {

		try {
			FileInputStream inSC = new FileInputStream("highscores.txt");
			inSC.close();
		} catch (FileNotFoundException e) {
			PrintWriter pwriter = new PrintWriter("highscores.txt");
			pwriter.close();
		}

		cards2 = new ArrayList<>(cardGenerator());

		button = new Button();
		button.setFont(new Font(20));
		button.setText("Play again");
		button2 = new Button();
		button2.setFont(new Font(20));
		button2.setText("Start");

		text = new Text("00:00:00");
		text.setFont(new Font(20));

		timeline = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				change(text);
			}
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.setAutoReverse(false);

		b1 = new Button();
		GridPane.setConstraints(b1, 0, 0);
		b1.setGraphic(new ImageView("file:0.png"));
		b2 = new Button();
		GridPane.setConstraints(b2, 0, 1);
		b2.setGraphic(new ImageView("file:0.png"));
		b3 = new Button();
		GridPane.setConstraints(b3, 0, 2);
		b3.setGraphic(new ImageView("file:0.png"));
		b4 = new Button();
		GridPane.setConstraints(b4, 0, 3);
		b4.setGraphic(new ImageView("file:0.png"));
		b5 = new Button();
		GridPane.setConstraints(b5, 1, 0);
		b5.setGraphic(new ImageView("file:0.png"));
		b6 = new Button();
		GridPane.setConstraints(b6, 1, 1);
		b6.setGraphic(new ImageView("file:0.png"));
		b7 = new Button();
		GridPane.setConstraints(b7, 1, 2);
		b7.setGraphic(new ImageView("file:0.png"));
		b8 = new Button();
		GridPane.setConstraints(b8, 1, 3);
		b8.setGraphic(new ImageView("file:0.png"));
		b9 = new Button();
		GridPane.setConstraints(b9, 2, 0);
		b9.setGraphic(new ImageView("file:0.png"));
		b10 = new Button();
		GridPane.setConstraints(b10, 2, 1);
		b10.setGraphic(new ImageView("file:0.png"));
		b11 = new Button();
		GridPane.setConstraints(b11, 2, 2);
		b11.setGraphic(new ImageView("file:0.png"));
		b12 = new Button();
		GridPane.setConstraints(b12, 2, 3);
		b12.setGraphic(new ImageView("file:0.png"));
		b13 = new Button();
		GridPane.setConstraints(b13, 3, 0);
		b13.setGraphic(new ImageView("file:0.png"));
		b14 = new Button();
		GridPane.setConstraints(b14, 3, 1);
		b14.setGraphic(new ImageView("file:0.png"));
		b15 = new Button();
		GridPane.setConstraints(b15, 3, 2);
		b15.setGraphic(new ImageView("file:0.png"));
		b16 = new Button();
		GridPane.setConstraints(b16, 3, 3);
		b16.setGraphic(new ImageView("file:0.png"));

		// b1.onActionProperty();
		button.setOnAction(this);
		button2.setOnAction(this);
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

		MenuBar menuBar = new MenuBar();
		Menu infoMenu = new Menu("Info");

		MenuItem HSMenuButton = new MenuItem("highscores");
		HSMenuButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				ScrollPane scrollPane = new ScrollPane();
				Text headtext = new Text();
				headtext.setText("Name:    Time:");
				headtext.setFont(new Font(20));
				TextArea TA = new TextArea();
				TA.setMaxWidth(280);
				String tempData = "";
				FileInputStream inSC;
				try {
					inSC = new FileInputStream("highscores.txt");
					Scanner FileScannerC = new Scanner(inSC);
					while (FileScannerC.hasNextLine()) {
						tempData = tempData + FileScannerC.nextLine() + "\n";
					}
					FileScannerC.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				TA.setText(tempData);
				TA.setFont(new Font(20));
				VBox vpane = new VBox();
				vpane.setAlignment(Pos.CENTER);
				vpane.getChildren().add(headtext);
				scrollPane.setContent(TA);
				scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
				scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
				vpane.getChildren().add(scrollPane);
				Scene subroot = new Scene(vpane, 300, 300);
				Stage stage = new Stage();
				stage.setTitle("highscores");
				stage.setScene(subroot);
				stage.show();
			}
		});
		MenuItem exitMenuButton = new MenuItem("Exit");
		exitMenuButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				System.exit(0);
			}
		});
		infoMenu.getItems().addAll(HSMenuButton, exitMenuButton);
		menuBar.getMenus().add(infoMenu);

		grid = new GridPane();
		grid.setDisable(true);
		grid.setPadding(new Insets(0, 5, 5, 5));
		grid.setVgap(5);
		grid.setHgap(5);
		grid.setAlignment(Pos.CENTER);
		grid.getChildren().addAll(b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16);

		HBox set = new HBox();
		set.setSpacing(40);
		set.setAlignment(Pos.CENTER);
		set.setPadding(new Insets(0, 5, 5, 5));
		set.getChildren().add(button2);
		set.getChildren().add(text);
		set.getChildren().add(button);

		BorderPane root = new BorderPane();

		root.setCenter(grid);
		root.setBottom(set);
		root.setTop(menuBar);
		// root.setPadding(new Insets(10, 10, 10, 10));
		Scene scene = new Scene(root, 720, 720);

		primaryStage.getIcons().add(new Image("file:mind.png"));
		primaryStage.setScene(scene);
		primaryStage.setTitle("Memory Game");
		primaryStage.show();
	}

	@Override
	public void handle(ActionEvent event) {

		if (event.getSource() == button2) {
			grid.setDisable(false);
			button2.setDisable(true);

			timeline.play();

		}
		if (event.getSource() == button) {
			b1.setGraphic(new ImageView("file:0.png"));
			b1.setDisable(false);
			b2.setGraphic(new ImageView("file:0.png"));
			b2.setDisable(false);
			b3.setGraphic(new ImageView("file:0.png"));
			b3.setDisable(false);
			b4.setGraphic(new ImageView("file:0.png"));
			b4.setDisable(false);
			b5.setGraphic(new ImageView("file:0.png"));
			b5.setDisable(false);
			b6.setGraphic(new ImageView("file:0.png"));
			b6.setDisable(false);
			b7.setGraphic(new ImageView("file:0.png"));
			b7.setDisable(false);
			b8.setGraphic(new ImageView("file:0.png"));
			b8.setDisable(false);
			b9.setGraphic(new ImageView("file:0.png"));
			b9.setDisable(false);
			b10.setGraphic(new ImageView("file:0.png"));
			b10.setDisable(false);
			b11.setGraphic(new ImageView("file:0.png"));
			b11.setDisable(false);
			b12.setGraphic(new ImageView("file:0.png"));
			b12.setDisable(false);
			b13.setGraphic(new ImageView("file:0.png"));
			b13.setDisable(false);
			b14.setGraphic(new ImageView("file:0.png"));
			b14.setDisable(false);
			b15.setGraphic(new ImageView("file:0.png"));
			b15.setDisable(false);
			b16.setGraphic(new ImageView("file:0.png"));
			b16.setDisable(false);
			Collections.shuffle(cards2);
			grid.setDisable(true);
			button2.setDisable(false);
			score = 0;

			mins = 0;
			secs = 0;
			millis = 0;
			timeline.pause();
			text.setText("00:00:00");

		}
		if (event.getSource() == b1) {
			b1.setGraphic(new ImageView(cards2.get(0).getImg()));
			if (!(tempb.contains(b1))) {
				tempb.add(b1);
				diro.add(cards2.get(0).getImg());
				clicked++;
			}
		} else if (event.getSource() == b2) {
			b2.setGraphic(new ImageView(cards2.get(1).getImg()));
			if (!(tempb.contains(b2))) {
				tempb.add(b2);
				diro.add(cards2.get(1).getImg());
				clicked++;
			}
		} else if (event.getSource() == b3) {
			b3.setGraphic(new ImageView(cards2.get(2).getImg()));
			if (!(tempb.contains(b3))) {
				tempb.add(b3);
				diro.add(cards2.get(2).getImg());
				clicked++;
			}
		} else if (event.getSource() == b4) {
			b4.setGraphic(new ImageView(cards2.get(3).getImg()));
			if (!(tempb.contains(b4))) {
				tempb.add(b4);
				diro.add(cards2.get(3).getImg());
				clicked++;
			}
		} else if (event.getSource() == b5) {
			b5.setGraphic(new ImageView(cards2.get(4).getImg()));
			if (!(tempb.contains(b5))) {
				tempb.add(b5);
				diro.add(cards2.get(4).getImg());
				clicked++;
			}
		} else if (event.getSource() == b6) {
			b6.setGraphic(new ImageView(cards2.get(5).getImg()));
			if (!(tempb.contains(b6))) {
				tempb.add(b6);
				diro.add(cards2.get(5).getImg());
				clicked++;
			}
		} else if (event.getSource() == b7) {
			b7.setGraphic(new ImageView(cards2.get(6).getImg()));
			if (!(tempb.contains(b7))) {
				tempb.add(b7);
				diro.add(cards2.get(6).getImg());
				clicked++;
			}
		} else if (event.getSource() == b8) {
			b8.setGraphic(new ImageView(cards2.get(7).getImg()));
			if (!(tempb.contains(b8))) {
				tempb.add(b8);
				diro.add(cards2.get(7).getImg());
				clicked++;
			}
		} else if (event.getSource() == b9) {
			b9.setGraphic(new ImageView(cards2.get(8).getImg()));
			if (!(tempb.contains(b9))) {
				tempb.add(b9);
				diro.add(cards2.get(8).getImg());
				clicked++;
			}
		} else if (event.getSource() == b10) {
			b10.setGraphic(new ImageView(cards2.get(9).getImg()));
			if (!(tempb.contains(b10))) {
				tempb.add(b10);
				diro.add(cards2.get(9).getImg());
				clicked++;
			}
		} else if (event.getSource() == b11) {
			b11.setGraphic(new ImageView(cards2.get(10).getImg()));
			if (!(tempb.contains(b11))) {
				tempb.add(b11);
				diro.add(cards2.get(10).getImg());
				clicked++;
			}
		} else if (event.getSource() == b12) {
			b12.setGraphic(new ImageView(cards2.get(11).getImg()));
			if (!(tempb.contains(b12))) {
				tempb.add(b12);
				diro.add(cards2.get(11).getImg());
				clicked++;
			}
		} else if (event.getSource() == b13) {
			b13.setGraphic(new ImageView(cards2.get(12).getImg()));
			if (!(tempb.contains(b13))) {
				tempb.add(b13);
				diro.add(cards2.get(12).getImg());
				clicked++;
			}
		} else if (event.getSource() == b14) {
			b14.setGraphic(new ImageView(cards2.get(13).getImg()));
			if (!(tempb.contains(b14))) {
				tempb.add(b14);
				diro.add(cards2.get(13).getImg());
				clicked++;
			}
		} else if (event.getSource() == b15) {
			b15.setGraphic(new ImageView(cards2.get(14).getImg()));
			if (!(tempb.contains(b15))) {
				tempb.add(b15);
				diro.add(cards2.get(14).getImg());
				clicked++;
			}
		} else if (event.getSource() == b16) {
			b16.setGraphic(new ImageView(cards2.get(15).getImg()));
			if (!(tempb.contains(b16))) {
				tempb.add(b16);
				diro.add(cards2.get(15).getImg());
				clicked++;
			}
		}
		if (clicked == 2) {
			Button tb1 = tempb.get(0);
			Button tb2 = tempb.get(1);
			String str1 = diro.get(0);
			String str2 = diro.get(1);

			if (str1.compareTo(str2) == 0) {
				tb1.setDisable(true);
				tb2.setDisable(true);
				score++;
			} else {
				Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
					tb1.setGraphic(new ImageView("file:0.png"));
					tb2.setGraphic(new ImageView("file:0.png"));
				}));
				timeline.play();
			}
			tempb.removeAll(tempb);
			diro.removeAll(diro);
			clicked = 0;
		}
		if (score == 8) {
			timeline.pause();
			try {
				WinWindow();
			} catch (IOException e) {
				e.printStackTrace();
			}
			score = 0;
		}

	}

	public class cards {
		private String img;

		cards(String num) {
			setImg(num);
		}

		public String getImg() {
			return img;
		}

		public void setImg(String numfill) {
			this.img = numfill;
		}
	}

	public class HighScorers implements Comparable<HighScorers> {
		private String name, time;

		HighScorers(String name, String time) {
			this.name = name;
			this.time = time;
		}

		public String getName() {
			return name;
		}

		public String getTime() {
			return time;
		}

		@Override
		public int compareTo(HighScorers h) {
			return time.compareTo(h.getTime());
		}
	}

}
