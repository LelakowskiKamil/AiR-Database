package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    static ObservableList<Student> observableList = FXCollections.observableArrayList();
    private static Connection con = null;
    Stage stage;
    @FXML
    Button buttonAdd;
    @FXML
    Button buttonDelete;
    @FXML
    Button buttonSearch;
    @FXML
    Button buttonRefresh;
    @FXML
    private TableView<Student> tableDB;
    @FXML
    private TableColumn<Student, Integer> columnName;
    @FXML
    private TableColumn<Student, String> columnSurname;
    @FXML
    private TableColumn<Student, String> columnSex;
    @FXML
    private TableColumn<Student, String> columnEmail;
    @FXML
    private TableColumn<Student, String> columnMajor;
    @FXML
    private TableColumn<Student, Integer> columnStudentCard;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet rs = null;

    public static Connection getCon() {
        return con;
    }

    public TableView<Student> getTableDB() {
        return tableDB;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String host = "jdbc:mysql://localhost:3306/db-school?serverTimezone=UTC";
        String uName = "root";
        String uPass = "password";

        // connect to database

        try {
            con = DriverManager.getConnection(host, uName, uPass);
            showAll();

        } catch (Exception e) {
            e.printStackTrace();
        }
        columnName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        columnSurname.setCellValueFactory(new PropertyValueFactory<>("Surname"));
        columnSex.setCellValueFactory(new PropertyValueFactory<>("Sex"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        columnMajor.setCellValueFactory(new PropertyValueFactory<>("Major"));
        columnStudentCard.setCellValueFactory(new PropertyValueFactory<>("StudentCard"));
        System.out.println("Polaczono");
    }

    public void showAll() throws Exception {
        rs = con.createStatement().executeQuery("select * from students ");
        observableList.clear();
        while (rs.next()) {
            observableList.add(new Student(rs.getString("Name"), rs.getString("Surname"), rs.getString("Sex"), rs.getString("Email"), rs.getString("Major"), rs.getInt("student_card")));

        }

        insertList(observableList);
    }

    public void insertList(ObservableList<Student> list) {
        try {
            tableDB.setItems(list);
        } catch (Exception e) {

        }


    }

    public void addStudentWindow() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("addStudentSample.fxml"));

        StackPane stackPane = loader.load();
        Scene scene = new Scene(stackPane);

        addStudentControler controller = (addStudentControler) loader.getController();

        stage.setTitle("Dodaj");
        stage.setScene(scene);
        stage.show();
    }

    public void addSearchControler() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("searchSample.fxml"));

        StackPane stackPane = loader.load();
        Scene scene = new Scene(stackPane);

        SearchController controller = (SearchController) loader.getController();

        stage.setTitle("Search");
        stage.setScene(scene);
        stage.show();
    }

    public void refresh() throws Exception {
        showAll();
    }
}
