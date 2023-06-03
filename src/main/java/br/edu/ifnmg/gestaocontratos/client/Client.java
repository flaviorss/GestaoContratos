
package br.edu.ifnmg.gestaocontratos.client;

import br.edu.ifnmg.gestaocontratos.entity.Entity;

/**
 * Class Client
 * 
 * @author Flávio Santos
 */
public class Client extends Entity{
    
    private Long cpf;
    private String nome;
    
    public Client(){
        
    }
    
    public Client( Long id, Long cpf, String nome ){
        
        setId(id);
        setCpf(cpf);
        setNome(nome);
        
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        if (cpf == null) {
            throw new IllegalArgumentException("Null CPF");
        }
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null) {
            throw new IllegalArgumentException("Null name");
        } else if (nome.length() > 45) {
            throw new IllegalArgumentException("The name exceeds 45 characters");
        }

        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Client{"
                + "id=" + getId()
                + ", cpf=" + cpf 
                + ", nome=" + nome 
                + '}';
    }
    
    
    
}
