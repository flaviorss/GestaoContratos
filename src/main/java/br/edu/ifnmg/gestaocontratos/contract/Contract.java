
package br.edu.ifnmg.gestaocontratos.contract;

import br.edu.ifnmg.gestaocontratos.entity.Entity;
import java.time.LocalDate;

/**
 * Class Contract
 * 
 * @author Flávio Santos;
 */
public class Contract extends Entity{
    
    private String redacao;
    private LocalDate ultimaAtualizacao;
    
    public Contract(){
        
    }
    
    public Contract( Long id, String redacao, LocalDate ultimaAtualizacao ){
        
        setId(id);
        setRedacao(redacao);
        setUltimaAtualizacao(ultimaAtualizacao);
        
    }

    public String getRedacao() {
        return redacao;
    }

    public void setRedacao(String redacao) {
        if (redacao == null) {
            throw new IllegalArgumentException("Null redacao");
        } else if (redacao.length() > 100000) {
            throw new IllegalArgumentException("The redacao exceeds 100000 characters");
        }
        
        this.redacao = redacao;
    }

    public LocalDate getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(LocalDate ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    @Override
    public String toString() {
        return "Contract{"
                + "id=" + getId()
                + ", redacao=" + redacao 
                + ", ultimaAtualizacao=" + ultimaAtualizacao 
                + '}';
    }
    
    
    
}
