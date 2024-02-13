import java.io.*;
import java.sql.*;
import java.util.*;

class CDataBase{
    Connection con;
    Statement st;
    public CDataBase(String dbname){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost : 3306/"+dbname+"?characterEncoding = utf8","root","");
        }
        catch(Exception e){

        }
    }
    public void idu(String sql){
        try {
            st.executeUpdate(sql);
        } catch (Exception e1) {
            System.out.println(e1);
        }
    }
    public ResultSet select(String sql){
        ResultSet rs = null;
        try {
            rs=st.executeQuery(sql);
        } catch (Exception e2) {
            System.out.println(e2);
        }
        return rs;
    }
} 

public class MLogin {
    public static void main(String[] args) {
        CDataBase obj=new CDataBase("login");
        int ch;
        String uname;
        String pass;
        String rePass;
        ResultSet rs=null;
        Scanner s=new Scanner(System.in);
        System.out.println("Enter your choise\n1.login\n2.register");
        try {
            System.out.print("your choise :");
            ch=s.nextInt();
            if (ch==1) {
                System.out.println("Enter username :");
                uname=s.nextLine();
                System.out.println("Enter password :");
                pass=s.nextLine();
                rs=obj.select("select * from login_tbl where uname = "+uname);
                if (rs.getString("password")==pass) {
                    System.out.println("Welcome\n3.Change password\n4.logout");
                    System.out.print("your choise :");
                    ch=s.nextInt();
                    if (ch==3) {
                        System.out.println("Channge password");
                        System.out.println("Enter current password :");
                        pass=s.nextLine();
                        System.out.println("Enter new password :");
                        String newPass=s.nextLine();
                        System.out.println("re Enter new password :");
                        rePass=s.nextLine();
                        if (newPass.equals(rePass)) {
                            String str="update login_tbl set password=("+newPass+"where uname="+uname+" and password="+pass+")";
                            obj.idu(str);
                        } 
                        else {
                            System.out.println("password and repassword must be same");
                        }
                        
                    }
                    else{
                        System.exit(0);
                    }
                }
                 else {
                    System.out.println("invalid user or password");
                }
            }
            else{
                System.out.println("Registration");
                System.out.println("Enter username :");
                uname=s.nextLine();
                System.out.println("Enter password :");
                pass=s.nextLine();
                System.out.println("re Enter password :");
                rePass=s.nextLine();
                if (pass.equals(rePass)) {
                    String str="insert into login_tbl values("+uname+","+pass+")";
                    obj.idu(str);
                } 
                else {
                    System.out.println("password and repassword must be same");
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}





