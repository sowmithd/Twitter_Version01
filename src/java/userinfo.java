/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.sql.Blob;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author sowmith
 */
@ManagedBean
@SessionScoped
public class userinfo implements Serializable {

    private String email;
    private String fname;
    private String lname;
   
    StreamedContent pic;

    public userinfo() {

    }

    
    
    public userinfo(String email, String fname, String lname, StreamedContent pic) {
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.pic = pic;
    }

    public StreamedContent getPic() {
        return pic;
    }

    public void setPic(StreamedContent pic) {
        this.pic = pic;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

 

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFname() {
        return this.fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }
    
    

    
    
}
