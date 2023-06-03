/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.edu.ifnmg.gestaocontratos;

import br.edu.ifnmg.gestaocontratos.client.Client;
import br.edu.ifnmg.gestaocontratos.client.ClientDao;
import br.edu.ifnmg.gestaocontratos.contract.Contract;
import br.edu.ifnmg.gestaocontratos.contract.ContractDao;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Flávio Santos;
 */
public class GestaoContratos {

    public static void main(String[] args) {
        
        // Testes para Clientes
        
        // Criando Clientes
        Client c1 = new Client(null, 11929826303l, "Ana Maria");
        System.out.println("> " + c1);
        
        Client c2 = new Client(null, 26752965030L, "Beatriz Yana");
        System.out.println("> " + c2);
        
        Client c3 = new Client(null, 15298715656L, "Flávio Santos");
        System.out.println("> " + c3);
        
        // Inserindo Cliente c1 e c3
        Long c1Id = new ClientDao().inseriClient(c1);
        c1.setId(c1Id);
        System.out.println("> " + c1);
        Long c3Id = new ClientDao().inseriClient(c3);
        c3.setId(c3Id);
        System.out.println("> " + c3);
        
        // Buscando todos os Clientes do Banco de Dados
        List<Client> clientes = new ClientDao().buscaAllClient();
        System.out.println(">> " + clientes);
        
        // Removendo Cliente c3
        new ClientDao().removeClient(c3);
        
        // Atualizando Cliente c1
        c1.setNome("Ana Zaira");
        new ClientDao().atualizaClient(c1);
        System.out.println("> " + c1);
        
        // Inserindo Cliente c2
        Long c2Id = new ClientDao().inseriClient(c2);
        c2.setId(c2Id);
        
        // Buscando todos os Clientes do Banco de Dados
        clientes = new ClientDao().buscaAllClient();
        System.out.println(">> " + clientes);
        
        // Buscando um Cliente a partir de um Id
        Client busca = new ClientDao().buscaClient(c2.getId());
        System.out.println("--> " + busca);
        
        // Testes para Contratos
        
        // Criando Contratos
        Contract con1 = new Contract(null, "Contrato por tempo determinado", LocalDate.of(2023, 5, 21));
        System.out.println("> " + con1);
        Contract con2 = new Contract(null, "Contrato por tempo indeterminado", LocalDate.of(2023, 5, 01));
        System.out.println("> " + con2);
        Contract con3 = new Contract(null, "Contrato de trabalho", LocalDate.of(2023, 5, 26));
        System.out.println("> " + con3);
        Contract con10 = new Contract(null, "Contrato Indevido", LocalDate.of(2023, 9, 14));
        System.out.println("> " + con10);
        
        Contract con4 = new Contract(null, "Contrato de estagio", LocalDate.of(2023, 10, 15));
        System.out.println("> " + con4);
        Contract con5 = new Contract(null, "Contrato de experiencia", LocalDate.of(2023, 9, 16));
        System.out.println("> " + con5);
        Contract con6 = new Contract(null, "Contrato de teletrabalho", LocalDate.of(2023, 8, 17));
        System.out.println("> " + con6);
        Contract con7 = new Contract(null, "Contrato Intermitente", LocalDate.of(2023, 7, 15));
        System.out.println("> " + con7);
        
        // Inserindo Contrato 1, 2, 3 e 10 associados a cliente c1(Ana Zaira)
        Long con1Id = new ContractDao().inserirContrato(c1.getId(), con1);
        con1.setId(con1Id);
        System.out.println("> " + con1);
        Long con2Id = new ContractDao().inserirContrato(c1.getId(), con2);
        con2.setId(con2Id);
        System.out.println("> " + con2);
        Long con3Id = new ContractDao().inserirContrato(c1.getId(), con3);
        con3.setId(con3Id);
        System.out.println("> " + con3);
        Long con10Id = new ContractDao().inserirContrato(c1.getId(), con10);
        con10.setId(con10Id);
        System.out.println("> " + con10);
        
        // Buscando todos os Contratos do Banco de Dados
        new ContractDao().buscaAllContract();
        
        // Removendo Contrato Indevido con10
        new ContractDao().removeContract(con10);
        
        // Atualizando con3
        con3.setRedacao("Contrato de trabalho eventual");
        new ContractDao().atualizaContract(c1.getId(), con3);
        System.out.println("> " + con3);
        
        // Inserindo Contrato 4, 5, 6 e 7 associados a cliente c2(Beatriz Yana)
        Long con4Id = new ContractDao().inserirContrato(c2.getId(), con4);
        con4.setId(con4Id);
        System.out.println("> " + con4);
        Long con5Id = new ContractDao().inserirContrato(c2.getId(), con5);
        con5.setId(con5Id);
        System.out.println("> " + con5);
        Long con6Id = new ContractDao().inserirContrato(c2.getId(), con6);
        con6.setId(con6Id);
        System.out.println("> " + con6);
        Long con7Id = new ContractDao().inserirContrato(c2.getId(), con7);
        con7.setId(con7Id);
        System.out.println("> " + con7);
        
        // Buscando todos os Contratos do Banco de Dados
        new ContractDao().buscaAllContract();
        
        // Buscando um Contrato a partir de um Id
        new ContractDao().buscaContract(con5.getId());
        
    }
}
