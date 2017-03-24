/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author namo
 */
@ManagedBean
@RequestScoped
public class Follow {
    private String user_name;
    private String email_id;
    public static String follow_confirm;
    private boolean followsug1 = false;
    private boolean followsug2 = false;

    public boolean isFollowsug1() {
        return followsug1;
    }

    public void setFollowsug1(boolean followsug1) {
        this.followsug1 = followsug1;
    }

    public boolean isFollowsug2() {
        return followsug2;
    }

    public void setFollowsug2(boolean followsug2) {
        this.followsug2 = followsug2;
    }
    
  
    // check if the user name exists in databse
    
    public String check_user()
    {
    try
    {
    Class.forName("com.mysql.jdbc.Driver");
    }
    catch(Exception e)
    {
         e.printStackTrace();
    return ("Internal Error! Please try again later");
    }
    
    final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/joshis6426";
    Connection conn = null;
    Statement stat = null;
    ResultSet rs = null;
    
    try
    {
        conn= DriverManager.getConnection(DB_URL,"joshis6426","1418359");
        stat = conn.createStatement();
        
        rs = stat.executeQuery("Select * from twitteraccount where FirstName = '"+user_name+"'");
        if(rs.next())
        {
            if(user_name.equals(rs.getString(1)))
            {
        return "searched_page";
        }
            else
            {
            return "internalError";
            }
        }
        else
        {
        return "internalError";
        }
    }
    catch(SQLException e)
    {
        e.printStackTrace();
        return "internalError";
    }
    finally
    {
    try
    {
        conn.close();
        stat.close();
        rs.close();
    }
    catch(SQLException e)
    {
        e.printStackTrace();
        return "internalError";
    }
    }
    }
    
    
    public String follow_user(String email_id, String followee)
    {
        
    try
    {
    Class.forName("com.mysql.jdbc.Driver");
    }
    catch(Exception e)
    {
         e.printStackTrace();
    //return ("Internal Error! Please try again later");
    }
    
    final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/joshis6426";
    Connection conn = null;
    Statement stat = null;
    ResultSet rs = null;
    
    try
    {
        conn = DriverManager.getConnection(DB_URL, "joshis6426", "1418359");
        stat = conn.createStatement();
         rs = stat.executeQuery("Select * from twitteraccount where FirstName = '"+followee+"'");
        
       if (rs.next())
        {   
          int r = stat.executeUpdate("insert into twitter_follow  values ('"+followee+"','"+email_id+"')");
          followsug1 = true;
           return "welcome";
        }
       else
       {
       return "following";
       }
        
    }
    catch(SQLException e)
    {
        e.printStackTrace();
       return "following";
    }
    finally
    {
        try
        {
            conn.close();
            stat.close();
            rs.close();
        }
        catch(SQLException e)
        {
        e.printStackTrace();
       // return "internalError";
        }
    }
    
      }
    
    
    public String unfollow_user(String email_id, String followee)
    {
        
    try
    {
    Class.forName("com.mysql.jdbc.Driver");
    }
    catch(Exception e)
    {
         e.printStackTrace();
    //return ("Internal Error! Please try again later");
    }
    
    final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/joshis6426";
    Connection conn = null;
    Statement stat = null;
    ResultSet rs = null;
    
    try
    {
        conn = DriverManager.getConnection(DB_URL, "joshis6426", "1418359");
        stat = conn.createStatement();
           
          int r = stat.executeUpdate("delete from twitter_follow where followee = '"+followee+"' and follower ='"+email_id+"'");
          followsug1 = false;
           return "welcome";
 
        
    }
    catch(SQLException e)
    {
        e.printStackTrace();
       return "following";
    }
    finally
    {
        try
        {
            conn.close();
            stat.close();
            rs.close();
        }
        catch(SQLException e)
        {
        e.printStackTrace();
       // return "internalError";
        }
    }
    
      }
    
    // follow and unfollow page in search result
    public String searchResultPage_follow()
    {
    try
    {
    Class.forName("com.mysql.jdbc.Driver");
    }
    catch(Exception e)
    {
        e.printStackTrace();
    return ("Internal Error! Please try again later.");
    }
    
    final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/joshis6426";
    Connection conn = null;
    Statement stat = null;
    ResultSet rs = null;
    
    try
    {
        conn = DriverManager.getConnection(DB_URL,"joshis6426","1418359");
        stat = conn.createStatement();
        rs = stat.executeQuery("Select * from twitter_follow");
        while(rs.next())
        {
        String followee = rs.getString(1);
        String follower = rs.getString(2);
        String a = email_id;
        if(followee.equals(rs.getString(1)) && follower.equals(rs.getString(2)))
        {
        follow_confirm = "You are following this person";
        return follow_confirm;
        }
        
        }       
    }
    catch(SQLException e)
    {
        e.printStackTrace();
        return "internalError";
    }
    finally
    {
        try
        {
            conn.close();
            stat.close();
            rs.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return "internalError";
            
        }
    }
    return follow_confirm;
    }
    
    //Check the count of users following each other
    
//    public int count_follower()
//    {
//    int num = 0;
//   try
//    {
//    Class.forName("com.mysql.jdbc.Driver");
//    }
//    catch(Exception e)
//    {
//        e.printStackTrace();
//    //return ("Internal Error! Please try again later.");
//    }
//   final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/joshis6426";
//   Connection conn = null;
//   Statement stat = null;
//   ResultSet rs = null;
//   
//   try
//   {
//       conn = DriverManager.getConnection(DB_URL,"joshis6426","1418359");
//       stat = conn.createStatement();
//       rs = stat.executeQuery("Select count(follower) from twitter_follow where followee = '"+user_name+"'");
//       while(rs.next())
//       {
//       num = rs.getInt(1);
//       }
//   }
//   catch(SQLException e)
//   {
//       e.printStackTrace();
//       
//   }
//   finally
//   {
//   try
//   {
//       conn.close();
//       stat.close();
//       rs.close();
//   }
//   catch(SQLException e)
//   {
//       e.printStackTrace();
//   }
//   }
//   return num;
//    }
//    
//    // count number of following
//    public int count_following()
//    {
//    int num = 0;
//   try
//    {
//    Class.forName("com.mysql.jdbc.Driver");
//    }
//    catch(Exception e)
//    {
//        e.printStackTrace();
//    //return ("Internal Error! Please try again later.");
//    }
//   final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/joshis6426";
//   Connection conn = null;
//   Statement stat = null;
//   ResultSet rs = null;
//   
//   try
//   {
//       conn = DriverManager.getConnection(DB_URL,"joshis6426","1418359");
//       stat = conn.createStatement();
//       rs = stat.executeQuery("Select count(followee) from twitter_follow where follower = '"+user_name+"'");
//       while(rs.next())
//       {
//       num = rs.getInt(1);
//       }
//   }
//   catch(SQLException e)
//   {
//       e.printStackTrace();
//       
//   }
//   finally
//   {
//   try
//   {
//       conn.close();
//       stat.close();
//       rs.close();
//   }
//   catch(SQLException e)
//   {
//       e.printStackTrace();
//   }
//   }
//   return num;
//    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

   

    public static String getFollow_confirm() {
        return follow_confirm;
    }

    public static void setFollow_confirm(String follow_confirm) {
        Follow.follow_confirm = follow_confirm;
    }

   
    
        
    
    
   
    
}
