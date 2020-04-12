package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class addStudentControler extends Controller implements Initializable {
    static ToggleGroup tg = new ToggleGroup();
    @FXML
    Button buttonAddStudent;
    @FXML
    TextField fieldName;
    @FXML
    TextField fieldSurname;
    @FXML
    TextField fieldEmail;
    @FXML
    TextField fieldMajor;
    @FXML
    TextField fieldStudentCard;
    @FXML
    RadioButton radioMale;
    @FXML
    RadioButton radioFemale;
    @FXML
    Label labelText;
    private Connection con = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet rs = null;

    @FXML
    String setSex() {
        if (radioMale.isSelected()) {
            return radioMale.getText();
        } else if (radioFemale.isSelected()) {
            return radioFemale.getText();
        } else {
            return "blad";
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        radioFemale.setToggleGroup(tg);
        radioMale.setToggleGroup(tg);
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

    public void addStudent() throws SQLException {
        String name = fieldName.getText();
        String surname = fieldSurname.getText();
        String sex = setSex();
        String email = fieldEmail.getText();
        String major = fieldMajor.getText();
        String studentCard = fieldStudentCard.getText();
        int studentCard2 = Integer.parseInt(studentCard);
        preparedStatement = con.prepareStatement("insert into students(name,Surname,sex,email,major,student_card) values (?,?,?,?,?,?)");
        //     preparedStatement.setInt(1, ID);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, surname);
        preparedStatement.setString(3, sex);
        preparedStatement.setString(4, email);
        preparedStatement.setString(5, major);
        preparedStatement.setInt(6, studentCard2);
        preparedStatement.executeUpdate();

    }
}
