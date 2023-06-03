
package br.edu.ifnmg.gestaocontratos.contract;

import br.edu.ifnmg.gestaocontratos.repository.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

/**
 * Class ContractDao
 * 
 *<code>
 * CREATE TABLE contrato(
 *   id bigint NOT NULL AUTO_INCREMENT,
 *   redacao mediumtext NOT NULL,
 *   ultimaAtualizacao date,
 *   idCliente bigint NOT NULL,
 *   PRIMARY KEY(id),
 *   FOREIGN KEY(idCliente) REFERENCES cliente(id)
 * );
 * </code>  
 * 
 * @author Flávio Santos;
 */
public class ContractDao {
    
    public static final String TABLE = "contrato";
    private Connection connection = DbConnection.getConnection();
    
    // Inseri um contrato associado ao Id de um Cliente no Banco de Dados
    public Long inserirContrato(Long idCliente, Contract contract) {
        Long id = 0L;
        
        try {
            String query = "INSERT INTO " + TABLE + " (idCliente, redacao, ultimaAtualizacao) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, idCliente);
            statement.setString(2, contract.getRedacao());
            statement.setObject(3, contract.getUltimaAtualizacao(), java.sql.Types.DATE);
            statement.executeUpdate();
            
            ResultSet resultSet = statement.getGeneratedKeys();
            
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
            
            System.out.println("--> Contrato Inserido");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
    
    // Remove um contrato do Banco de Dados a partir do Id de um Objeto Contract
    public void removeContract(Contract contract) {
        try {
            String query = "DELETE FROM " + TABLE + " WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, contract.getId());
            statement.executeUpdate();
            
            contract.setId(null);
            System.out.println("--> Contrato Removido");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Atualiza os dados de um contrato no Banco de Dados
    public void atualizaContract(Long idClient, Contract contract) {
        try {
            String query = "UPDATE " + TABLE + " SET idCliente = ?, redacao = ?, ultimaAtualizacao = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, idClient);
            statement.setString(2, contract.getRedacao());
            statement.setObject(3, contract.getUltimaAtualizacao(), java.sql.Types.DATE);
            statement.setLong(4, contract.getId());
            statement.executeUpdate();
            
            System.out.println("--> Contrato  Atualizado");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Buscar por um contrato no Banco de Dados atraves de um Id
    public void buscaContract(Long id) {
        try {
            String query = "SELECT * FROM " + TABLE + " WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String redacao = resultSet.getString("redacao");
                Object ultimaAtualizacao = resultSet.getObject("ultimaAtualizacao");
                Long idCliente = resultSet.getLong("idCliente");
                System.out.println("Contract{"
                                    + "id=" + id
                                    + ", redacao=" + redacao 
                                    + ", ultimaAtualizacao=" + ultimaAtualizacao
                                    + ", IdClient=" + idCliente
                                    + '}');
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    // Buscar por todos os contratos no Banco de Dados
    public void buscaAllContract() {
        List<Contract> contratos = new ArrayList<>();
        
        try {
            String query = "SELECT * FROM " + TABLE;
            PreparedStatement statement = connection.prepareStatement(query);
           
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String redacao = resultSet.getString("redacao");
                Object ultimaAtualizacao = resultSet.getObject("ultimaAtualizacao");
                Long idCliente = resultSet.getLong("idCliente");
                System.out.println("Contract{"
                                    + "id=" + id
                                    + ", redacao=" + redacao 
                                    + ", ultimaAtualizacao=" + ultimaAtualizacao
                                    + ", IdClient=" + idCliente
                                    + '}');
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
