/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.sql.*;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author sowmith
 */
@ManagedBean
@SessionScoped
public class sidebars implements Serializable {

    private Blob b;
    private String loginid;
    private boolean imgpresent;
    private String suggname1;
    private String suggname2;
    StreamedContent suggpic1;
    StreamedContent suggpic2;

    public String getSuggname1() {
        return suggname1;
    }

    public void setSuggname1(String suggname1) {
        this.suggname1 = suggname1;
    }

    public String getSuggname2() {
        return suggname2;
    }

    public void setSuggname2(String suggname2) {
        this.suggname2 = suggname2;
    }

    public StreamedContent getSuggpic1() {
        return suggpic1;
    }

    public void setSuggpic1(StreamedContent suggpic1) {
        this.suggpic1 = suggpic1;
    }

    public StreamedContent getSuggpic2() {
        return suggpic2;
    }

    public void setSuggpic2(StreamedContent suggpic2) {
        this.suggpic2 = suggpic2;
    }

    public boolean isImgpresent() {
        return imgpresent;
    }

    public void setImgpresent(boolean imgpresent) {
        this.imgpresent = imgpresent;
    }

    public sidebars() {
        imgpresent = false;
    }

    public sidebars(String loginid) {
        this.loginid = loginid;

    }

    private StreamedContent myImage;

    public StreamedContent getMyImage() {
        return myImage;
    }

    public void setMyImage(StreamedContent myImage) {
        this.myImage = myImage;
    }

    public Blob getB() {
        return b;
    }

    public void setB(Blob b) {
        this.b = b;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public void showsuggestions(String email) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {

        }

        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        ResultSet resfollow = null;

        try {
            final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/joshis6426";
            conn = DriverManager.getConnection(DB_URL,
                    "joshis6426", "1418359");
            //create a statement
            stat = conn.createStatement();
            rs = stat.executeQuery("select * from twitteraccount where email_id != '" + email + "'");
            String temp = "";
            suggname1 = "";
            suggname2 = "";
            boolean found = false;
            while (rs.next()) {
                temp = rs.getString("firstname");
                Statement stat1 = conn.createStatement();
                resfollow = stat1.executeQuery("select * from twitter_follow where followee = '" + email + "'");
                while (resfollow.next()) {
                    if (resfollow.getString("follower").equals(temp)) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    if (suggname1.equals("")) {
                        suggname1 = temp;
                    } else {
                        suggname2 = temp;
                        break;
                    }
                }

            }

            
                rs = stat.executeQuery("select * from profilepictures where username = '" + suggname1 + "'");
                if (rs.next()) {

                    b = rs.getBlob("picture");
                    InputStream IS = b.getBinaryStream();
                    suggpic1 = new DefaultStreamedContent(IS, "image/png");
                } 
                
                rs = stat.executeQuery("select * from profilepictures where username = '" + suggname2 + "'");
                if (rs.next()) {

                    b = rs.getBlob("picture");
                    InputStream IS = b.getBinaryStream();
                    suggpic2 = new DefaultStreamedContent(IS, "image/png");
                } 
                
            

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs != null)
                {
                    rs.close();
                }
                
                if(stat != null)
                stat.close();
                if(conn != null)
                conn.close();
                if(resfollow != null)
                resfollow.close();

            } catch (Exception e) {

                e.printStackTrace();
            }
        }

    }

    public StreamedContent displaypic(String email) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {

        }

        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;

        try {
            final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/joshis6426";
            conn = DriverManager.getConnection(DB_URL,
                    "joshis6426", "1418359");
            //create a statement
            stat = conn.createStatement();
            String name = "";
            imgpresent = false;

            rs = stat.executeQuery("select * from twitteraccount where email_id = '" + email + "'");
            if (rs.next()) {
                name = rs.getString("firstname");
            }

            //create the bank account statement string
            rs = stat.executeQuery("select * from profilepictures where username = '" + name + "'");
            if (rs.next()) {

                b = rs.getBlob("picture");
                InputStream IS = b.getBinaryStream();
                myImage = new DefaultStreamedContent(IS, "image/png");
                imgpresent = true;
                return myImage;

            } else {
                return myImage;

            }
        } catch (SQLException e) {
            e.printStackTrace();
            return myImage;

        } finally {
            try {
                rs.close();
                stat.close();
                conn.close();

            } catch (Exception e) {

                e.printStackTrace();
            }
        }

    }

}
