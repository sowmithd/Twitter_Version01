/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Image;
import java.io.InputStream;
import java.sql.*;
import java.math.*;
import java.util.*;

import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author sowmith
 */
@ManagedBean
@SessionScoped
public class Registration implements Serializable {

    /**
     * Creates a new instance of Registration
     */
    private String firstname;
    private String lastname;
    private String email_id;

    private String ans1;
    private String ans2;
    private String secque1;
    private String secque2;
    private String Fans1;
    private String Fans2;
    private String password;
    private String fsecque;
    private boolean emailvalid;
    private double r;
    private String status;
    private boolean secansvalid;
    private sidebars sb;
    private userinfo uinfo;
    private Blob p;
    
    StreamedContent pic;

    public StreamedContent getPic() {
        return pic;
    }

    public void setPic(StreamedContent pic) {
        this.pic = pic;
    }
    
    
    public Blob getP() {
        return p;
    }

    public void setP(Blob p) {
        this.p = p;
    }

     
     

    
    public boolean isSecansvalid() {
        return secansvalid;
    }

    public void setSecansvalid(boolean secansvalid) {
        this.secansvalid = secansvalid;
    }

    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public boolean isEmailvalid() {
        return emailvalid;
    }

    public void setEmailvalid(boolean emailvalid) {
        this.emailvalid = emailvalid;
    }

    public String getFsecque() {
        return fsecque;
    }

    public void setFsecque(String fsecque) {
        this.fsecque = fsecque;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getAns1() {
        return ans1;
    }

    public void setAns1(String ans1) {
        this.ans1 = ans1;
    }

    public String getAns2() {
        return ans2;
    }

    public void setAns2(String ans2) {
        this.ans2 = ans2;
    }

    public String getSecque1() {
        return secque1;
    }

    public void setSecque1(String secque1) {
        this.secque1 = secque1;
    }

    public String getSecque2() {
        return secque2;
    }

    public void setSecque2(String secque2) {
        this.secque2 = secque2;
    }

    public String getFans1() {
        return Fans1;
    }

    public void setFans1(String Fans1) {
        this.Fans1 = Fans1;
    }

    public String getFans2() {
        return Fans2;
    }

    public void setFans2(String Fans2) {
        this.Fans2 = Fans2;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void clearfields() {
        firstname = "";
        lastname = "";
        email_id = "";

        ans1 = "";
        ans2 = "";
        secque1 = "";
        secque2 = "";
        Fans1 = "";
        Fans2 = "";
        password = "";
        fsecque = "";
        emailvalid = false;
        r = 0.0;
        success = false;
        status = "";
        secansvalid = false;
        
    }

    public sidebars getSb() {
        return sb;
    }

    public void setSb(sidebars sb) {
        this.sb = sb;
    }
    
    public userinfo getUinfo() {
        return uinfo;
    }

    public void setUinfo(userinfo uinfo) {
        
        this.uinfo = uinfo;
    }


    public String register() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {
            return ("Internal Error! Please try again later.");
        }

        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        Statement stat1 = null;
        try {
            final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/joshis6426";
            conn = DriverManager.getConnection(DB_URL,
                    "joshis6426", "1418359");
            //create a statement
            stat = conn.createStatement();

            //create the bank account statement string
            rs = stat.executeQuery("Select * from twitteraccount where email_id='" + email_id + "'");
            if (rs.next()) {

                return ("An account is registered with this email address already");

            } else {
                //inserting a record
                stat1 = conn.createStatement();
                int k = stat1.executeUpdate("insert into twitteraccount values ('" + firstname + "', '" + lastname + "', '" + email_id + "', '" + password + "', '" + secque1 + "', '" + secque2 + "', '" + ans1 + "', '" + ans2 + "')");
              
                return ("Registration Successful! Please "
                        + "return to login your account.");

            }
        } catch (SQLException e) {
            e.printStackTrace();
            return ("Internal Error! Please try again later.");

        } finally {
            try {
                rs.close();
                stat.close();
                conn.close();
                if (stat1 != null) {
                    stat1.close();
                }

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }

    public String updatepassword() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/joshis6426";

        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        success = false;
        status = "Update password failed!";
        try {

            conn = DriverManager.getConnection(DB_URL, "joshis6426", "1418359");
            stat = conn.createStatement();
            int t = stat.executeUpdate("update twitteraccount set password = '" + password + "' where email_id = '" + email_id + "'");
            if (t > 0) {
                success = true;
                status = "Password Updated Successfully, Please Login!";
            } else {
                success = false;
            }
            return status;
        } catch (SQLException e) {
            e.printStackTrace();
            return status;

        }

    }

    
    
    public String searchuser() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/joshis6426";

        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        emailvalid = false;

        try {
            conn = DriverManager.getConnection(DB_URL, "joshis6426", "1418359");
            stat = conn.createStatement();
            rs = stat.executeQuery("Select * from twitteraccount where Email_ID= '" + email_id + "'");
            if (rs.next()) {
                emailvalid = true;
                r = Math.random();
                if (r < 0.5) {
                    fsecque = rs.getString("SecQue1");
                } else {
                    fsecque = rs.getString("SecQue2");
                }

                return "User Found!";

            }
            return "User Not Found";

        } catch (SQLException e) {
            e.printStackTrace();
            return "User Not Found!";
        }
    }

    public String login() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            return ("Internal Error! Please try again later.");
        }
        final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/joshis6426";

        Connection conn = null;
        Statement stat = null;
        Statement stat1 = null;
        ResultSet rs = null;
        ResultSet rs1 = null;

        try {
            conn = DriverManager.getConnection(DB_URL, "joshis6426", "1418359");
            stat = conn.createStatement();
            rs = stat.executeQuery("Select * from twitteraccount where Email_ID= '" + email_id + "'");
            if (rs.next()) {
                if (password.equals(rs.getString(4))) {
                    sb = new sidebars(email_id);
                    firstname = rs.getString("firstname");
                    lastname = rs.getString("lastname");
                    
                    stat1 = conn.createStatement();
                    
                    rs1 = stat1.executeQuery("select * from profilepictures where username = '"+ firstname + "'");
                    if(rs1.next())
                        
                    {
                        Blob pi;
                        pi = rs1.getBlob("picture");
                        InputStream IS = pi.getBinaryStream();
                        pic = new DefaultStreamedContent(IS, "image/png");
                    }
                  
                    uinfo = new userinfo(email_id, firstname, lastname,pic);
                    return "welcome";
                } else if (!email_id.equals(rs.getString(3)) || (!password.equals(rs.getString(4)))) {
                    email_id = "";
                    password = "";
                    //display loginNotOK.xhtml
                    return "loginNotOK";
                } else {
                    email_id = "";
                    password = "";
                    return "loginNotOK";
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
            email_id = "";
            password = "";
            return ("internalError");
        } finally {
            try {
                if(conn != null)
                {
                    conn.close();
                }
                if(stat != null)
                {
                     stat.close(); 
                }
                if(rs != null)
                {
                    rs.close();
                }
                if (stat1 != null)
                {
                    stat1.close();
                }
                if(rs1 != null)
                {
                    rs1.close();
                }
              
                
            } catch (SQLException ex) {
                ex.printStackTrace();
                return ("internalError");

            }
        }
        return "";

    }

    public String forgotPassword() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            return "Connection Failed!";
        }
        final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/joshis6426";

        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        secansvalid = false;

        try {
            conn = DriverManager.getConnection(DB_URL, "joshis6426", "1418359");
            stat = conn.createStatement();
            rs = stat.executeQuery("select * from twitteraccount where email_id =  '" + email_id + "'");
            if (rs.next()) {
                if (r < 0.5) {
                    if (!Fans1.isEmpty() && Fans1.equals(rs.getString("ans1"))) {
                        secansvalid = true;
                        return ("Correct!");
                    }
                } else if (!Fans1.isEmpty() && Fans1.equals(rs.getString("ans2"))) {
                    secansvalid = true;
                    return ("Correct!");
                }
                return ("Wrong Answer!");

            }

            return ("Wrong Answer!");
        } catch (SQLException e) {
            e.printStackTrace();
           return ("Internal Error Catch");
        } finally {
            try {
                conn.close();
                stat.close();
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                return ("Internal Error!");
            }
        }
    }

}
