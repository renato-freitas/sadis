package lar.entidade;

/**
 *
 * @author Renato Freitas
 */
public class Database {
    
    private String server;
    private String url;
    private String name;
    private String user;
    private String password;
    
   
    public String getURL() {
        return url;
    }

    public void setURL(String url) {
        this.url = url;
    }

    
    public String getName() {
        return name;
    }

   
    public void setName(String name) {
        this.name = name;
    }

    
    public String getUser() {
        return user;
    }

    
    public void setUser(String user) {
        this.user = user;
    }

    
    public String getPassword() {
        return password;
    }

    
    public void setPassword(String pwd) {
        this.password = pwd;
    }

    
    public String getServer() {
        return server;
    }

    
    public void setServer(String server) {
        this.server = server;
    }
}
