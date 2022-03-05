import java.awt.desktop.AboutEvent;
import java.sql.*;
import java.util.Scanner;

public class DB {
    String login = "root";
    String password = "admin";
    String url = "jdbc:mysql://localhost:3306/users"+
            "?verifyServerCertificate=false"+
            "&useSSL=false"+
            "&requireSSL=false"+
            "&useLegacyDatetimeCode=false"+
            "&amp"+
            "&serverTimezone=UTC";
    Connection connection;
    Statement statement;
    Scanner sc = new Scanner(System.in);

    public DB(){
        try {
            connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            System.out.println("Подключение не выполнено");
        }
    }
    public Connection getConnection() {
        return connection;
    }

    void check_input_user(){
        boolean while_true = true;
        while (while_true) {
            System.out.println("Введите ваше имя");
            String input_user = sc.nextLine();
            String sql_check = "SELECT * FROM users where name = '" + input_user + "'";
            try {
                statement = getConnection().createStatement();
                ResultSet rs = statement.executeQuery(sql_check);
                int counter = 0;

                while (rs.next()) {
                    counter++;
                }
                if (counter >= 1) {
                    System.out.println("Добро пожаловать" + " " + input_user);
                    while_true = false;
                    break;
                } else
                    System.out.println("Пользователь не найден");
                    System.out.println("Попробуйте снова");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    void reg_user(){
        Input_User check_input_user = new Input_User();
        boolean while_true = true;
        while (while_true) {
            System.out.println("Введите Ваше имя");
            String input_user_name = sc.nextLine();

            System.out.println("Введите ваш логин");
            String input_user_login = sc.nextLine();
            check_input_user.check_login(input_user_login);

            System.out.println("Введите пароль");
            String input_user_password = sc.nextLine();
            check_input_user.check_password(input_user_password);

            String sql_check = "SELECT * FROM users where name = '" + input_user_name + "'";
            String sql_insert = "INSERT users (Name, login, password) VALUE ('" + input_user_name + "', '" + input_user_login + "', '" + input_user_password + "')";
            try {
                statement = getConnection().createStatement();
                ResultSet rs = statement.executeQuery(sql_check);
                int counter = 0;

                while (rs.next()) {
                    counter++;
                }
                if (counter >= 1) {
                    System.out.println("Пользователь уже существует");
                    return;
                } else
                    statement.executeUpdate(sql_insert);
                System.out.println("Пользователь успешно создан");
                break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

