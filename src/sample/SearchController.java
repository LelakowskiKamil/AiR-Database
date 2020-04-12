package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SearchController extends Controller implements Initializable {
    @FXML
    TextField nameField;
    @FXML
    private Connection con = null;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String host = "jdbc:mysql://localhost:3306/db-school?serverTimezone=UTC";
        String uName = "root";
        String uPass = "password";

        // connect to database

        try {
            con = DriverManager.getConnection(host, uName, uPass);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void searchByName() throws Exception {
        observableList.clear();
        ObservableList<Student> list = FXCollections.observableArrayList();
        String text = "'%" + nameField.getText() + "%'";
        ResultSet rs = getCon().createStatement().executeQuery("Select * from students where name like " + text);

        while (rs.next()) {
            observableList.add(new Student(rs.getString("Name"), rs.getString("Surname"), rs.getString("Sex"), rs.getString("Email"), rs.getString("Major"), rs.getInt("student_card")));
        }
        insertList(observableList);


    }
}
