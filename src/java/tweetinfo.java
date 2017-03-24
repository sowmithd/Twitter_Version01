/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author sowmith
 */
@ManagedBean
@SessionScoped
public class tweetinfo implements Serializable {

    private String tweet;
    private String fname;
    private String lname;
    private String username;
    private int id;
    StreamedContent pic;

    public tweetinfo(String tweet, String fname, String lname, String username, int id, StreamedContent pic) {
        this.tweet = tweet;
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.id = id;
        this.pic = pic;
    }

    public StreamedContent getPic() {
        return pic;
    }

    public void setPic(StreamedContent pic) {
        this.pic = pic;
    }
    

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
    /**
     * Creates a new instance of tweetinfo
     */
    public tweetinfo() {
    }
    
}
