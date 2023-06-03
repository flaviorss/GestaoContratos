
package br.edu.ifnmg.gestaocontratos.client;

import br.edu.ifnmg.gestaocontratos.repository.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class ClientDao
 * 
 *<code>
 * CREATE TABLE cliente(
 *   id bigint NOT NULL AUTO_INCREMENT,
 *   cpf bigint NOT NULL,
 *   nome varchar(45) NOT NULL,
 *   PRIMARY KEY(id)
 * );
 * </code>  
 * 
 * @author Flávio Santos;
 */
public class ClientDao {
    
    public static final String TABLE = "cliente";
    private Connection connection = DbConnection.getConnection();
    
    // Inseri um cliente no Banco de Dados
    public Long inseriClient(Client client) {
        Long id = 0L;
        
        try {
            String query = "INSERT INTO " + TABLE + " (cpf, nome) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, client.getCpf());
            statement.setString(2, client.getNome());
            statement.executeUpdate();
            
            // Retrieve the generated primary key
            ResultSet resultSet = statement.getGeneratedKeys();

            // Moves to first retrieved data
            if (resultSet.next()) {
                // Retrieve the returned primary key
                id = resultSet.getLong(1);
            }
            
            System.out.println("--> Cliente " + client.getNome() + " Inserido");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
    
    // Remove um cliente do Banco de Dados a partir do Id de um Objeto Client
    public void removeClient(Client client) {
        try {
            String query = "DELETE FROM " + TABLE + " WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, client.getId());
            statement.executeUpdate();
            
            client.setId(null);
            System.out.println("--> Cliente " + client.getNome() + " Removido");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Atualiza os dados de um cliente no Banco de Dados
    public void atualizaClient(Client client) {
        try {
            String query = "UPDATE " + TABLE + " SET cpf = ?, nome = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, client.getCpf());
            statement.setString(2, client.getNome());
            statement.setLong(3, client.getId());
            statement.executeUpdate();
            
            System.out.println("--> Cliente " + client.getNome() + " Atualizado");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Buscar por um cliente no Banco de Dados atraves de um Id
    public Client buscaClient(Long id) {
        try {
            String query = "SELECT * FROM " + TABLE + " WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Long cpf = resultSet.getLong("cpf");
                String nome = resultSet.getString("nome");
                return new Client(id, cpf, nome);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Buscar por todos os cliente no Banco de Dados
    public List<Client> buscaAllClient() {
        List<Client> clientes = new ArrayList<>();
        
        try {
            String query = "SELECT * FROM " + TABLE;
            PreparedStatement statement = connection.prepareStatement(query);
           
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                Long cpf = resultSet.getLong("cpf");
                String nome = resultSet.getString("nome");
                Client cliente = new Client(id, cpf, nome);
                clientes.add(cliente);
            }
            
            return clientes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
