package Menu;

import Users.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.Node;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class UserMenuController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TableView<User> users_table;
    @FXML
    private TableColumn<User, String> namesColumn;
    @FXML
    private TableColumn<User, Integer> winsColumn;
    @FXML
    private TableColumn<User, Integer> lossesColumn;
    @FXML
    private TableColumn<User, Integer> tiesColumn;
    @FXML
    private Button cancel_button;
    @FXML
    private Button create_button;
    @FXML
    private Button delete_button;
    @FXML
    private Label label_nome_usuario;
    @FXML
    private TextField user_name_input;
    @FXML
    private Label label_create_user;
    @FXML
    private Label label_delete_user;
    public ObservableList<User> list;

    public void open_window_menu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Menu/menu-inicial.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void create_user_initialize(){
        //400 height, y 000;
        //233 height, y 150;
        reset_user_menu();
        users_table.setPrefHeight(233);
        users_table.setLayoutY(150);

        label_create_user.setOpacity(1);
        cancel_button.setDisable(false);
        cancel_button.setOpacity(1);
        create_button.setDisable(false);
        create_button.setOpacity(1);
        label_nome_usuario.setOpacity(1);
        user_name_input.setDisable(false);
        user_name_input.setOpacity(1);
    }
    public void delete_user_initialize(){
        //400 height, y 000;
        //233 height, y 150;
        reset_user_menu();
        users_table.setPrefHeight(233);
        users_table.setLayoutY(150);

        label_delete_user.setOpacity(1);
        cancel_button.setDisable(false);
        cancel_button.setOpacity(1);
        delete_button.setDisable(false);
        delete_button.setOpacity(1);
        label_nome_usuario.setOpacity(1);
        user_name_input.setDisable(false);
        user_name_input.setOpacity(1);
    }
    public void reset_user_menu(){
        users_table.setPrefHeight(400);
        users_table.setLayoutY(0);

        label_create_user.setOpacity(0);
        label_delete_user.setOpacity(0);
        cancel_button.setDisable(true);
        cancel_button.setOpacity(0);
        create_button.setDisable(true);
        create_button.setOpacity(0);
        delete_button.setDisable(true);
        delete_button.setOpacity(0);
        label_nome_usuario.setOpacity(0);
        user_name_input.setDisable(true);
        user_name_input.setOpacity(0);
        user_name_input.clear();

        label_delete_user.setOpacity(0);

        invalidNameLabel.setOpacity(0);

        setUsersList();
    }
    @FXML
    private Label invalidNameLabel;
    void nameInputError(){
        invalidNameLabel.setOpacity(1);
    }
    public void create_user() throws IOException {
        String name = user_name_input.getText();

        if (name.indexOf(' ') >= 0) nameInputError();
        if (Main.GU.findUser(name) != -1) nameInputError();
        else {
            Main.GU.CreateUser(name);

            reset_user_menu();
        }
    }
    public void cancel_creation(){
        reset_user_menu();
    }
    public void delete_user() throws IOException {
        String name = user_name_input.getText();

        Main.GU.DeleteUser(name);
        reset_user_menu();
    }
    private void setUsersList(){
        list = FXCollections.observableArrayList(Main.GU.users);
        users_table.setItems(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        namesColumn.setCellValueFactory(new PropertyValueFactory<User,String>("name"));
        winsColumn.setCellValueFactory(new PropertyValueFactory<User,Integer>("wins"));
        lossesColumn.setCellValueFactory(new PropertyValueFactory<User,Integer>("losses"));
        tiesColumn.setCellValueFactory(new PropertyValueFactory<User,Integer>("ties"));
        setUsersList();
    }
}
