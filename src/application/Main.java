package src.application;

import java.util.List;

import java.util.Scanner;

import src.db.DB;

import src.exceptions.CampoObrigatorioException;

import src.exceptions.DBException;

import src.exceptions.DBIntegrityException;

import src.exceptions.EntidadeJaExisteException;

import src.exceptions.EntidadeNaoEncontradaException;

import src.model.dao.DAOFactory;

import src.model.dao.MarcaDAO;
import src.model.dao.PerfumeDAO;
import src.model.entities.Marca;
import src.model.entities.Perfume;

public class Main {

    public static void main(String[] args) {
 
        /* Scanner sc = new Scanner(System.in);

        MarcaDAO marcaDAO = DAOFactory.cMarcaDAO();

        try {

            System.out.println("==== TESTE DAO MARCA ====");

            // INSERT

            System.out.println("\n>> Inserir marca:");

            System.out.print("CNPJ: ");

            String cnpj = sc.nextLine();

            System.out.print("Nome: ");

            String nome = sc.nextLine();

            System.out.print("Origem: ");

            String origem = sc.nextLine();

            System.out.print("Ano de criação: ");

            int ano = sc.nextInt();

            sc.nextLine();

            Marca nova = new Marca(cnpj, nome, origem, ano);

            marcaDAO.insert(nova);

            System.out.println("Marca inserida com sucesso!");

            // FIND BY CNPJ

            System.out.println("\n>> Buscar marca por CNPJ:");

            System.out.print("Digite o CNPJ: ");

            String busca = sc.nextLine();

            Marca encontrada = marcaDAO.findByCnpj(busca);

            System.out.println("Encontrada: " + encontrada);

            // FIND ALL

            System.out.println("\n>> Lista completa de marcas:");

            List<Marca> lista = marcaDAO.findAll();

            lista.forEach(System.out::println);

            // UPDATE

            System.out.println("\n>> Atualizar marca:");

            System.out.print("Digite o CNPJ da marca a atualizar: ");

            String updCnpj = sc.nextLine();

            Marca upd = marcaDAO.findByCnpj(updCnpj);

            System.out.print("Novo nome: ");

            upd.setNome(sc.nextLine());

            System.out.print("Nova origem: ");

            upd.setOrigem(sc.nextLine());

            System.out.print("Novo ano de criação: ");

            upd.setAnoCriacao(sc.nextInt());

            sc.nextLine();

            marcaDAO.update(upd);

            System.out.println("Marca atualizada!");

            // DELETE

            System.out.println("\n>> Deletar marca:");

            System.out.print("CNPJ para deletar: ");

            String del = sc.nextLine();

            marcaDAO.deleteByCnpj(del);

            System.out.println("Marca deletada!");

        } catch (CampoObrigatorioException | EntidadeNaoEncontradaException |

                 EntidadeJaExisteException | DBIntegrityException | DBException e) {

            System.out.println("Erro: " + e.getMessage());

        } finally {

            DB.closeConnection();

            sc.close();

        } */


         Scanner sc = new Scanner(System.in);
        PerfumeDAO perfumeDAO = DAOFactory.cPerfumeDAO();

    try {
        System.out.println("=== INSERIR PERFUME ===");

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("CNPJ da marca: ");
        String cnpjMarca = sc.nextLine();

        System.out.print("Preço: ");
        double preco = sc.nextDouble();

        System.out.print("Estoque: ");
        int estoque = sc.nextInt();
        sc.nextLine();

        Perfume novo = new Perfume(null, nome, cnpjMarca, preco, estoque);
        perfumeDAO.insert(novo);

        System.out.println("\nPerfume inserido! ID gerado = " + novo.getId());

    } catch (Exception e) {
        System.out.println("Erro: " + e.getMessage());
    } finally {
        DB.closeConnection();
        sc.close();
    }
         


    }

}

