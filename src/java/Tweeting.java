/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.InputStream;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.sql.*;
import java.util.*;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author sowmith
 */
@ManagedBean
@SessionScoped
public class Tweeting implements Serializable {
    String tweet;
    String email;
    String username;
    StreamedContent pic;
    
    
    
    ArrayList<tweetinfo> tweets = new ArrayList<>();

    public StreamedContent getPic() {
        return pic;
    }

    public void setPic(StreamedContent pic) {
        this.pic = pic;
    }
    
    

    public ArrayList<tweetinfo> getTweets() {
        return tweets;
    }

    public void setTweets(ArrayList<tweetinfo> tweets) {
        this.tweets = tweets;
    }
    

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public ArrayList<tweetinfo> showtweets(String fname)
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            
            
        }
        
        final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/joshis6426";
        Connection conn = null;
        Statement stat = null;
        Statement stat1 = null;
        Statement stat2 = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        
        try {
            conn = DriverManager.getConnection(DB_URL, "joshis6426", "1418359");
            stat = conn.createStatement();
            stat1 = conn.createStatement();
            stat2 = conn.createStatement();
            tweets.clear();
            
            stat2 = conn.createStatement();
                    
                    rs2 = stat2.executeQuery("select * from profilepictures where username = '"+ fname + "'");
                    if(rs2.next())
                        
                    {
                        Blob pi;
                        pi = rs2.getBlob("picture");
                        InputStream IS = pi.getBinaryStream();
                        pic = new DefaultStreamedContent(IS, "image/png");
                    }
            
            rs1 = stat1.executeQuery("select * from twitteraccount where firstname = '"+ fname + "'");
            rs1.next();
            rs = stat.executeQuery("select * from tweets where username = '"+ fname + "' order by date desc");
            while(rs.next())
            {
                tweets.add(new tweetinfo(rs.getString("tweet"),rs1.getString("firstname"),rs1.getString("lastname"),rs.getString("username"),rs.getInt("id"), pic));
            }
             
            return tweets;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return tweets;
           
           
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
                if(stat1 != null)
                {
                    stat1.close();
                }
                if(rs1 != null)
                {
                    rs1.close();
                }
                
                if(stat2 != null)
                {
                    stat2.close();
                }
                if(rs2 != null)
                {
                    rs2.close();
                }
                
                
            } catch (SQLException ex) {
                ex.printStackTrace();
             

            }
        }
    }
    
    
    public String tweetsave(String fname)
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return "";
            
        }
        
        final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/joshis6426";
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        
        try {
            conn = DriverManager.getConnection(DB_URL, "joshis6426", "1418359");
            stat = conn.createStatement();
            if(tweet!= null)
            {
                int t = stat.executeUpdate("insert into tweets (username,tweet) values ('" + fname + "', '" + tweet + "')");
            if(t<0)
            {
                int a = t;
                return "";
            }
            else{
                tweet = "";
                return "welcome";
            }
                
            }
            return "welcome";
            
            
            
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
           
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
                
                
            } catch (SQLException ex) {
                ex.printStackTrace();
             

            }
        }
        
        
    }

    
    
}
