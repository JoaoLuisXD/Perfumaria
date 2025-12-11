package src.application;

import java.util.Scanner;
import java.util.Date;

import src.db.DB;
import src.model.dao.*;
import src.model.entities.*;
import src.exceptions.*;

public class Main {

    public static void menuInsert(
        Scanner sc,
        MarcaDAO marcaDAO,
        ClienteDAO clienteDAO,
        RevendedorDAO revendedorDAO,
        PerfumeDAO perfumeDAO,
        PedidoDAO pedidoDAO,
        IncluiDAO incluiDAO,
        EntregaDAO entregaDAO) {

    int op = -1;

    while (op != 0) {

        System.out.println("\n===== MENU DE INSERTS =====");
        System.out.println("1 - Inserir Marca");
        System.out.println("2 - Inserir Cliente");
        System.out.println("3 - Inserir Revendedor");
        System.out.println("4 - Inserir Perfume");
        System.out.println("5 - Inserir Pedido");
        System.out.println("6 - Inserir Itens do Pedido (Inclui)");
        System.out.println("7 - Inserir Entrega");
        System.out.println("0 - Voltar");
        System.out.print("Escolha: ");

        op = sc.nextInt();
        sc.nextLine();

        try {
            switch (op) {

                case 1:
                    System.out.println("\n=== Inserir Marca ===");
                    System.out.print("CNPJ: ");
                    String cnpj = sc.nextLine();
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Origem: ");
                    String origem = sc.nextLine();
                    System.out.print("Ano de criação: ");
                    int ano = sc.nextInt();
                    sc.nextLine();

                    marcaDAO.insert(new Marca(cnpj, nome, origem, ano));
                    System.out.println("Marca inserida!");
                    break;

                case 2:
                    System.out.println("\n=== Inserir Cliente ===");
                    System.out.print("CPF: ");
                    String cpf = sc.nextLine();
                    System.out.print("Nome: ");
                    String nomeC = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Endereço: ");
                    String end = sc.nextLine();
                    System.out.print("Telefone: ");
                    String tel = sc.nextLine();

                    clienteDAO.insert(new Cliente(cpf, nomeC, email, end, tel));
                    System.out.println("Cliente inserido!");
                    break;

                case 3:
                    System.out.println("\n=== Inserir Revendedor ===");
                    System.out.print("CPF: ");
                    String cpfR = sc.nextLine();
                    System.out.print("Nome: ");
                    String nomeR = sc.nextLine();
                    System.out.print("Email: ");
                    String emailR = sc.nextLine();
                    System.out.print("Salário: ");
                    float sal = sc.nextFloat();
                    sc.nextLine();
                    System.out.print("Telefone: ");
                    String telR = sc.nextLine();

                    revendedorDAO.insert(new Revendedor(cpfR, nomeR, emailR, sal, telR));
                    System.out.println("Revendedor inserido!");
                    break;

                case 4:
                    System.out.println("\n=== Inserir Perfume ===");
                    System.out.print("Nome: ");
                    String nomeP = sc.nextLine();
                    System.out.print("CNPJ da marca: ");
                    String cnpjM = sc.nextLine();
                    System.out.print("Preço: ");
                    double preco = sc.nextDouble();
                    System.out.print("Estoque: ");
                    int est = sc.nextInt();
                    sc.nextLine();

                    Perfume novoP = new Perfume(null, nomeP, cnpjM, preco, est);
                    perfumeDAO.insert(novoP);
                    System.out.println("Perfume inserido! ID = " + novoP.getId());
                    break;

                case 5:
                    System.out.println("\n=== Inserir Pedido ===");
                    System.out.print("Valor total: ");
                    double valor = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("CPF do cliente: ");
                    String cpfc = sc.nextLine();
                    System.out.print("CPF do revendedor: ");
                    String cpfr = sc.nextLine();

                    Pedido ped = new Pedido(null, valor, cpfc, cpfr);
                    pedidoDAO.insert(ped);
                    System.out.println("Pedido inserido! ID = " + ped.getId());
                    break;

                case 6:
                    System.out.println("\n=== Inserir Itens ao Pedido (Inclui) ===");
                    System.out.print("ID do pedido: ");
                    int idPed = sc.nextInt();
                    System.out.print("ID do perfume: ");
                    int idPerf = sc.nextInt();
                    System.out.print("Quantidade: ");
                    int qtd = sc.nextInt();
                    sc.nextLine();

                    Inclui inc = new Inclui(
                        idPed,
                        idPerf,
                        qtd,
                        new java.sql.Date(new java.util.Date().getTime())
                    );

                    incluiDAO.insert(inc);
                    System.out.println("Item inserido no pedido!");
                    break;

                case 7:
                    System.out.println("\n=== Inserir Entrega ===");
                    System.out.print("Endereço: ");
                    String endE = sc.nextLine();
                    System.out.print("Valor da entrega: ");
                    double vE = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Status: ");
                    String status = sc.nextLine();
                    System.out.print("ID do pedido: ");
                    int idPedido = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Tipo (1 - Rápida / 2 - Normal): ");
                    int tipo = sc.nextInt();
                    sc.nextLine();

                    Entrega ent;

                    if (tipo == 1) {
                        ent = new EntregaRapida(null, endE, vE, new java.util.Date(), status, idPedido);
                    } else {
                        ent = new EntregaNormal(null, endE, vE, new java.util.Date(), status, idPedido);
                    }

                    entregaDAO.insert(ent);
                    System.out.println("Entrega inserida! ID = " + ent.getId());
                    break;

                case 0:
                    System.out.println("Voltando...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void menuUpdate(
        Scanner sc,
        MarcaDAO marcaDAO,
        ClienteDAO clienteDAO,
        RevendedorDAO revendedorDAO,
        PerfumeDAO perfumeDAO,
        PedidoDAO pedidoDAO,
        IncluiDAO incluiDAO,
        EntregaDAO entregaDAO) {

    int op = -1;

    while (op != 0) {

        System.out.println("\n===== MENU DE UPDATES =====");
        System.out.println("1 - Atualizar Marca");
        System.out.println("2 - Atualizar Cliente");
        System.out.println("3 - Atualizar Revendedor");
        System.out.println("4 - Atualizar Perfume");
        System.out.println("5 - Atualizar Pedido");
        System.out.println("6 - Atualizar Itens do Pedido (Inclui)");
        System.out.println("7 - Atualizar Entrega");
        System.out.println("0 - Voltar");
        System.out.print("Escolha: ");

        op = sc.nextInt();
        sc.nextLine();

        try {
            switch (op) {

                case 1:
                    System.out.println("\n=== Atualizar Marca ===");
                    System.out.print("CNPJ da marca: ");
                    String cnpj = sc.nextLine();

                    Marca m = marcaDAO.findByCnpj(cnpj);
                    if (m == null) {
                        System.out.println("Marca não encontrada!");
                        break;
                    }

                    System.out.print("Novo nome: ");
                    m.setNome(sc.nextLine());
                    System.out.print("Nova origem: ");
                    m.setOrigem(sc.nextLine());
                    System.out.print("Novo ano de criação: ");
                    m.setAnoCriacao(sc.nextInt());
                    sc.nextLine();

                    marcaDAO.update(m);
                    System.out.println("Marca atualizada!");
                    break;

                case 2:
                    System.out.println("\n=== Atualizar Cliente ===");
                    System.out.print("CPF: ");
                    String cpfC = sc.nextLine();

                    Cliente c = clienteDAO.findByCpf(cpfC);
                    if (c == null) {
                        System.out.println("Cliente não encontrado!");
                        break;
                    }

                    System.out.print("Novo nome: ");
                    c.setNome(sc.nextLine());
                    System.out.print("Novo email: ");
                    c.setEmail(sc.nextLine());
                    System.out.print("Novo endereço: ");
                    c.setEndereco(sc.nextLine());
                    System.out.print("Novo telefone: ");
                    c.setTelefone(sc.nextLine());

                    clienteDAO.update(c);
                    System.out.println("Cliente atualizado!");
                    break;

                case 3:
                    System.out.println("\n=== Atualizar Revendedor ===");
                    System.out.print("CPF: ");
                    String cpfR = sc.nextLine();

                    Revendedor r = revendedorDAO.findByCpf(cpfR);
                    if (r == null) {
                        System.out.println("Revendedor não encontrado!");
                        break;
                    }

                    System.out.print("Novo nome: ");
                    r.setNome(sc.nextLine());
                    System.out.print("Novo email: ");
                    r.setEmail(sc.nextLine());
                    System.out.print("Novo salário: ");
                    r.setSalario(sc.nextFloat());
                    sc.nextLine();
                    System.out.print("Novo telefone: ");
                    r.setTelefone(sc.nextLine());

                    revendedorDAO.update(r);
                    System.out.println("Revendedor atualizado!");
                    break;

                case 4:
                    System.out.println("\n=== Atualizar Perfume ===");
                    System.out.print("ID: ");
                    int idP = sc.nextInt();
                    sc.nextLine();

                    Perfume p = perfumeDAO.findById(idP);
                    if (p == null) {
                        System.out.println("Perfume não encontrado!");
                        break;
                    }

                    System.out.print("Novo nome: ");
                    p.setNome(sc.nextLine());
                    System.out.print("Novo CNPJ da marca: ");
                    p.setMarca(sc.nextLine());
                    System.out.print("Novo preço: ");
                    p.setPreco(sc.nextDouble());
                    System.out.print("Novo estoque: ");
                    p.setEstoque(sc.nextInt());
                    sc.nextLine();

                    perfumeDAO.update(p);
                    System.out.println("Perfume atualizado!");
                    break;

                case 5:
                    System.out.println("\n=== Atualizar Pedido ===");
                    System.out.print("ID: ");
                    int idPed = sc.nextInt();
                    sc.nextLine();

                    Pedido ped = pedidoDAO.findById(idPed);
                    if (ped == null) {
                        System.out.println("Pedido não encontrado!");
                        break;
                    }

                    System.out.print("Novo valor: ");
                    ped.setValor(sc.nextDouble());
                    sc.nextLine();
                    System.out.print("Novo CPF cliente: ");
                    ped.setCliente(sc.nextLine());
                    System.out.print("Novo CPF revendedor: ");
                    ped.setRevendedor(sc.nextLine());

                    pedidoDAO.update(ped);
                    System.out.println("Pedido atualizado!");
                    break;

                case 6:
                    System.out.println("\n=== Atualizar Itens do Pedido ===");
                    System.out.print("ID do pedido: ");
                    int idPedido = sc.nextInt();
                    System.out.print("ID do perfume: ");
                    int idPerf = sc.nextInt();
                    sc.nextLine();

                    Inclui inc = incluiDAO.findById(idPedido, idPerf);
                    if (inc == null) {
                        System.out.println("Item não encontrado!");
                        break;
                    }

                    System.out.print("Nova quantidade: ");
                    inc.setQtd_itens(sc.nextInt());
                    inc.setData(new java.util.Date());
                    sc.nextLine();

                    incluiDAO.update(inc);
                    System.out.println("Item atualizado!");
                    break;

                case 7:
                    System.out.println("\n=== Atualizar Entrega ===");
                    System.out.print("ID: ");
                    int idE = sc.nextInt();
                    sc.nextLine();

                    Entrega e = entregaDAO.findById(idE);
                    if (e == null) {
                        System.out.println("Entrega não encontrada!");
                        break;
                    }

                    System.out.print("Novo endereço: ");
                    e.setEndereco(sc.nextLine());
                    System.out.print("Novo valor da entrega: ");
                    e.setValorEntrega(sc.nextDouble());
                    sc.nextLine();
                    System.out.print("Novo status: ");
                    e.setStatus(sc.nextLine());

                    entregaDAO.update(e);
                    System.out.println("Entrega atualizada!");
                    break;

                case 0:
                    System.out.println("Voltando...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public static void menuDelete(
        Scanner sc,
        MarcaDAO marcaDAO,
        ClienteDAO clienteDAO,
        RevendedorDAO revendedorDAO,
        PerfumeDAO perfumeDAO,
        PedidoDAO pedidoDAO,
        IncluiDAO incluiDAO,
        EntregaDAO entregaDAO) {

    int op = -1;

    while (op != 0) {

        System.out.println("\n===== MENU DE DELETES =====");
        System.out.println("1 - Deletar Marca");
        System.out.println("2 - Deletar Cliente");
        System.out.println("3 - Deletar Revendedor");
        System.out.println("4 - Deletar Perfume");
        System.out.println("5 - Deletar Pedido");
        System.out.println("6 - Deletar Item do Pedido (Inclui)");
        System.out.println("7 - Deletar Entrega");
        System.out.println("0 - Voltar");
        System.out.print("Escolha: ");

        op = sc.nextInt();
        sc.nextLine();

        try {

            switch (op) {

                case 1: // Marca
                    System.out.println("\n=== Deletar Marca ===");
                    System.out.print("CNPJ: ");
                    String cnpj = sc.nextLine();

                    marcaDAO.deleteByCnpj(cnpj);
                    System.out.println("Marca deletada!");
                    break;

                case 2: // Cliente
                    System.out.println("\n=== Deletar Cliente ===");
                    System.out.print("CPF: ");
                    String cpf = sc.nextLine();

                    clienteDAO.deleteByCpf(cpf);
                    System.out.println("Cliente deletado!");
                    break;

                case 3: // Revendedor
                    System.out.println("\n=== Deletar Revendedor ===");
                    System.out.print("CPF: ");
                    String cpfR = sc.nextLine();

                    revendedorDAO.deleteByCpf(cpfR);
                    System.out.println("Revendedor deletado!");
                    break;

                case 4: // Perfume
                    System.out.println("\n=== Deletar Perfume ===");
                    System.out.print("ID: ");
                    int idP = sc.nextInt();
                    sc.nextLine();

                    perfumeDAO.deleteById(idP);
                    System.out.println("Perfume deletado!");
                    break;

                case 5: // Pedido
                    System.out.println("\n=== Deletar Pedido ===");
                    System.out.print("ID: ");
                    int idPed = sc.nextInt();
                    sc.nextLine();

                    pedidoDAO.deleteById(idPed);
                    System.out.println("Pedido deletado!");
                    break;

                case 6: // Inclui
                    System.out.println("\n=== Deletar Item do Pedido ===");
                    System.out.print("ID do pedido: ");
                    int idPedido = sc.nextInt();
                    System.out.print("ID do perfume: ");
                    int idPerf = sc.nextInt();
                    sc.nextLine();

                    incluiDAO.delete(idPedido, idPerf);
                    System.out.println("Item deletado!");
                    break;

                case 7: // Entrega
                    System.out.println("\n=== Deletar Entrega ===");
                    System.out.print("ID: ");
                    int idE = sc.nextInt();
                    sc.nextLine();

                    entregaDAO.deleteById(idE);
                    System.out.println("Entrega deletada!");
                    break;

                case 0:
                    System.out.println("Voltando...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

    }
}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public static void menuFind(
        Scanner sc,
        MarcaDAO marcaDAO,
        ClienteDAO clienteDAO,
        RevendedorDAO revendedorDAO,
        PerfumeDAO perfumeDAO,
        PedidoDAO pedidoDAO,
        IncluiDAO incluiDAO,
        EntregaDAO entregaDAO
) {
    int op;

    do {
        System.out.println("\n=== MENU FIND ===");
        System.out.println("1 - Buscar Marca por CNPJ");
        System.out.println("2 - Listar Todas as Marcas");
        System.out.println("3 - Buscar Cliente por CPF");
        System.out.println("4 - Listar Todos os Clientes");
        System.out.println("5 - Buscar Revendedor por CPF");
        System.out.println("6 - Listar Todos os Revendedores");
        System.out.println("7 - Buscar Perfume por ID");
        System.out.println("8 - Listar Todos os Perfumes");
        System.out.println("9 - Buscar Pedido por ID");
        System.out.println("10 - Listar Todos os Pedidos");
        System.out.println("0 - Voltar");
        System.out.print("Opção: ");

        op = sc.nextInt();
        sc.nextLine();

        try {

            switch (op) {

                case 1:
                    System.out.print("CNPJ: ");
                    String cnpj = sc.nextLine();
                    System.out.println(marcaDAO.findByCnpj(cnpj));
                    break;

                case 2:
                    marcaDAO.findAll().forEach(System.out::println);
                    break;

                case 3:
                    System.out.print("CPF do cliente: ");
                    String cpfC = sc.nextLine();
                    System.out.println(clienteDAO.findByCpf(cpfC));
                    break;

                case 4:
                    clienteDAO.findAll().forEach(System.out::println);
                    break;

                case 5:
                    System.out.print("CPF do revendedor: ");
                    String cpfR = sc.nextLine();
                    System.out.println(revendedorDAO.findByCpf(cpfR));
                    break;

                case 6:
                    revendedorDAO.findAll().forEach(System.out::println);
                    break;

                case 7:
                    System.out.print("ID do perfume: ");
                    int idP = sc.nextInt();
                    sc.nextLine();
                    System.out.println(perfumeDAO.findById(idP));
                    break;

                case 8:
                    perfumeDAO.findAll().forEach(System.out::println);
                    break;

                case 9:
                    System.out.print("ID do pedido: ");
                    int idPed = sc.nextInt();
                    sc.nextLine();
                    System.out.println(pedidoDAO.findById(idPed));
                    break;

                case 10:
                    pedidoDAO.findAll().forEach(System.out::println);
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

    } while (op != 0);
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        MarcaDAO marcaDAO = DAOFactory.cMarcaDAO();
        ClienteDAO clienteDAO = DAOFactory.cClienteDAO();
        RevendedorDAO revendedorDAO = DAOFactory.cRevendedorDAO();
        PerfumeDAO perfumeDAO = DAOFactory.cPerfumeDAO();
        PedidoDAO pedidoDAO = DAOFactory.cPedidoDAO();
        IncluiDAO incluiDAO = DAOFactory.cIncluiDAO();
        EntregaDAO entregaDAO = DAOFactory.createEntregaDAO();

        int op = -1;

        while (op != 0) {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1 - Inserir");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Deletar");
            System.out.println("4 - Encontrar");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    menuInsert(sc, marcaDAO, clienteDAO, revendedorDAO, perfumeDAO, pedidoDAO, incluiDAO, entregaDAO);
                    break;

                case 2:
                    menuUpdate(sc, marcaDAO, clienteDAO, revendedorDAO, perfumeDAO, pedidoDAO, incluiDAO, entregaDAO);
                    break;

                case 3:
                    menuDelete(sc, marcaDAO, clienteDAO, revendedorDAO, perfumeDAO, pedidoDAO, incluiDAO, entregaDAO);
                    break;

                case 4:
                    menuFind(sc, marcaDAO, clienteDAO, revendedorDAO, perfumeDAO, pedidoDAO, incluiDAO, entregaDAO);
                    break;

                case 0:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }

        sc.close();
        
    }
}
