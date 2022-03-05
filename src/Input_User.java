import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Input_User {
    void check_login(String login){
        Pattern pattern= Pattern.compile(("^[A-Za-z]([.A-Za-z0-9-]{1,18})([A-Za-z0-9])$"));
        Matcher matcher = pattern.matcher(login);
        boolean islogin = matcher.matches();

        if(islogin) {
            return;
        } else
            System.out.println("Ошибка при вводе, попробуйте снова");
        Scanner sc = new Scanner(System.in);
        String login_new = sc.next();
        check_login(login_new);
    }

    void check_password(String password){
        Pattern pattern = Pattern.compile("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$");
        Matcher matcher = pattern.matcher(password);
        boolean isPassword = matcher.matches();
        if (isPassword){
            return;
        }
        else System.out.println("Ошибка, введите снова");
        Scanner sc = new Scanner(System.in);
        String password_new = sc.nextLine();
        check_password(password_new);
    }



}
