package Menu;

import Users.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

public class Main extends Application {
    public static UserManager GU;
    public static String game = "none";
    public static int player1Index;
    public static int player2Index;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("menu-inicial.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Tabletop!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("/MenuImagens/LogoTabletop.png"))));
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        GU = new UserManager();
        GU.SaveUsers();
        launch();
    }
}