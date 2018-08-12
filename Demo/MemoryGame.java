
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

public class MemoryGame extends Application implements EventHandler<ActionEvent> {
	
	int max = 8, score = 0, clicked = 0;
	int mins = 0, secs = 0, millis = 0;
	boolean sos = true;
	ArrayList<cards> tickets;
	ArrayList<Button> tempb = new ArrayList<>();
	ArrayList<String> diro = new ArrayList<>();
	Text text;
	Timeline timeline;
	GridPane grid;
	ArrayList<Button> Buttons = new ArrayList<Button>();
	Button PAbutton, Sbutton;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws IOException {
		try {
			FileInputStream inSC = new FileInputStream("resources\\highscores.txt");
			inSC.close();
		} catch (FileNotFoundException e) {
			PrintWriter pwriter = new PrintWriter("resources\\highscores.txt");
			pwriter.close();
		}

		tickets = new ArrayList<>(cardGenerator());

		PAbutton = new Button();
		PAbutton.setFont(new Font(20));
		PAbutton.setText("Play again");
		Sbutton = new Button();
		Sbutton.setFont(new Font(20));
		Sbutton.setText("Start");

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

		for(int i = 0; i<max*2;i++) {
			Buttons.add(new Button());
			Buttons.get(i).setGraphic(new ImageView("file:resources\\0.png"));
			Buttons.get(i).setOnAction(this);
		}
		GridPane.setConstraints(Buttons.get(0), 0, 0);
		GridPane.setConstraints(Buttons.get(1), 0, 1);
		GridPane.setConstraints(Buttons.get(2), 0, 2);
		GridPane.setConstraints(Buttons.get(3), 0, 3);
		GridPane.setConstraints(Buttons.get(4), 1, 0);
		GridPane.setConstraints(Buttons.get(5), 1, 1);
		GridPane.setConstraints(Buttons.get(6), 1, 2);
		GridPane.setConstraints(Buttons.get(7), 1, 3);
		GridPane.setConstraints(Buttons.get(8), 2, 0);
		GridPane.setConstraints(Buttons.get(9), 2, 1);
		GridPane.setConstraints(Buttons.get(10), 2, 2);
		GridPane.setConstraints(Buttons.get(11), 2, 3);
		GridPane.setConstraints(Buttons.get(12), 3, 0);
		GridPane.setConstraints(Buttons.get(13), 3, 1);
		GridPane.setConstraints(Buttons.get(14), 3, 2);
		GridPane.setConstraints(Buttons.get(15), 3, 3);
		PAbutton.setOnAction(this);
		Sbutton.setOnAction(this);

		MenuBar menuBar = new MenuBar();
		Menu infoMenu = new Menu("Info");

		MenuItem HSMenuButton = new MenuItem("highscores");
		HSMenuButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				ScrollPane scrollPane = new ScrollPane();
				Text headtext = new Text();
				headtext.setText("Name:    \tTime:");
				headtext.setFont(new Font(20));
				TextArea TA = new TextArea();
				TA.setMaxWidth(280);
				String tempData = "";
				FileInputStream inSC;
				try {
					inSC = new FileInputStream("resources\\highscores.txt");
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
				stage.getIcons().add(new Image("file:resources\\Trophy.png"));
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
		for(int i = 0; i<Buttons.size();i++) {
			grid.getChildren().add(Buttons.get(i));
		}
		
		HBox set = new HBox();
		set.setSpacing(40);
		set.setAlignment(Pos.CENTER);
		set.setPadding(new Insets(0, 5, 5, 5));
		set.getChildren().add(Sbutton);
		set.getChildren().add(text);
		set.getChildren().add(PAbutton);

		BorderPane root = new BorderPane();
		root.setCenter(grid);
		root.setBottom(set);
		root.setTop(menuBar);
		
		Scene scene = new Scene(root, 720, 720);

		primaryStage.getIcons().add(new Image("file:resources\\mind.png"));
		primaryStage.setScene(scene);
		primaryStage.setTitle("Memory Game");
		primaryStage.show();
	}

	@Override
	public void handle(ActionEvent event) {

		if (event.getSource() == Sbutton) {
			grid.setDisable(false);
			Sbutton.setDisable(true);

			timeline.play();

		}
		if (event.getSource() == PAbutton) {
			for(int i = 0; i< Buttons.size();i++) {
				Buttons.get(i).setGraphic(new ImageView("file:0.png"));
				Buttons.get(i).setDisable(false);
			}
		
			Collections.shuffle(tickets);
			grid.setDisable(true);
			Sbutton.setDisable(false);
			score = 0;

			mins = 0;
			secs = 0;
			millis = 0;
			timeline.pause();
			text.setText("00:00:00");

		}
		for(int i = 0; i<Buttons.size(); i++) {
			if (event.getSource() == Buttons.get(i)) {
				Buttons.get(i).setGraphic(new ImageView(tickets.get(i).getImg()));
				if (!(tempb.contains(Buttons.get(i)))) {
					tempb.add(Buttons.get(i));
					diro.add(tickets.get(i).getImg());
					clicked++;
				}
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
					tb1.setGraphic(new ImageView("file:resources\\0.png"));
					tb2.setGraphic(new ImageView("file:resources\\0.png"));
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
				FileInputStream inSC = new FileInputStream("resources\\highscores.txt");
				Scanner FileScannerC = new Scanner(inSC);
				while (FileScannerC.hasNextLine()) {
					tempHS.add(new HighScorers(FileScannerC.next(), FileScannerC.next()));
				}
				FileScannerC.close();
				tempHS.add(new HighScorers(result.get(), text.getText()));
				Collections.sort(tempHS);
				PrintWriter pwriter = new PrintWriter("resources\\highscores.txt");
				for (int i = 0; i <= tempHS.size() - 2; i++) {
					pwriter.println(String.format("%-8s \t%s", tempHS.get(i).getName(), tempHS.get(i).getTime()));
				}
				pwriter.print(String.format("%-8s \t%s", tempHS.get(tempHS.size() - 1).getName(),
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
			imgDirectory = "file:resources\\1.png";
			break;
		case 2:
			imgDirectory = "file:resources\\2.png";
			break;
		case 3:
			imgDirectory = "file:resources\\3.png";
			break;
		case 4:
			imgDirectory = "file:resources\\4.png";
			break;
		case 5:
			imgDirectory = "file:resources\\5.png";
			break;
		case 6:
			imgDirectory = "file:resources\\6.png";
			break;
		case 7:
			imgDirectory = "file:resources\\7.png";
			break;
		case 8:
			imgDirectory = "file:resources\\8.png";
			break;
		}
		return imgDirectory;
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
