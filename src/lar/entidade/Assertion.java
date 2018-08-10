package lar.entidade;

/**
 * @author Renato Freitas 
 * @since 2018-06-28
 */
public class Assertion {

    private long id;
    private String origem;
    private String alvo;
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    
    public String getOrigem() {
        return origem;
    }
    public void setOrigem(String origem) {
        this.origem = origem;
    }
    
    public String getAlvo() {
        return alvo;
    }
    public void setAlvo(String alvo) {
        this.alvo = alvo;
    }
}
