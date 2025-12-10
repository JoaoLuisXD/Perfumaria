package src.model.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import src.db.DB;
import src.exceptions.DBException;
import src.exceptions.DBIntegrityException;
import src.exceptions.EntidadeJaExisteException;
import src.model.dao.ClienteDAO;
import src.model.entities.Cliente;

public class ClienteDAOJDBC implements ClienteDAO {

    private Connection conn;

    public ClienteDAOJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Cliente obj) throws DBException, EntidadeJaExisteException{
        try {
            PreparedStatement st = conn.prepareStatement(
                "INSERT INTO cliente (cpf, nome, email, endereco, telefone) VALUES (?, ?, ?, ?, ?)"
            );

            st.setString(1, obj.getCpf());
            st.setString(2, obj.getNome());
            st.setString(3, obj.getEmail());
            st.setString(4, obj.getEndereco());
            st.setString(5, obj.getTelefone());

            st.executeUpdate();
            DB.closeStatement(st);

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                throw new EntidadeJaExisteException("CPF já cadastrado.");
            }
            throw new DBException(e.getMessage());   
        } 
    }

    @Override
    public void update(Cliente obj) {
        try {
            PreparedStatement st = conn.prepareStatement(
                "UPDATE cliente SET nome=?, email=?, endereco=?, telefone=? WHERE cpf=?"
            );

            st.setString(1, obj.getNome());
            st.setString(2, obj.getEmail());
            st.setString(3, obj.getEndereco());
            st.setString(4, obj.getTelefone());
            st.setString(5, obj.getCpf());

            st.executeUpdate();
            DB.closeStatement(st);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteByCpf(String cpf) throws DBIntegrityException{
        try {
            PreparedStatement st = conn.prepareStatement("DELETE FROM cliente WHERE cpf=?");

            st.setString(1, cpf);
            st.executeUpdate();
            DB.closeStatement(st);

        } catch (SQLException e) {
            throw new DBIntegrityException("Erro ao deletar");
        }
    }

    @Override
    public Cliente findByCpf(String cpf) {
        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM cliente WHERE cpf=?");
            st.setString(1, cpf);

            ResultSet rs = st.executeQuery();
            Cliente obj = null;

            if (rs.next()) {
                obj = instantiateCliente(rs);
            }

            DB.closeResultSet(rs);
            DB.closeStatement(st);
            return obj;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Cliente> findAll() {
        try {
            PreparedStatement st = conn.prepareStatement(
                "SELECT * FROM cliente"
            );

            ResultSet rs = st.executeQuery();
            List<Cliente> list = new ArrayList<>();

            while (rs.next()) {
                list.add(instantiateCliente(rs));
            }

            DB.closeResultSet(rs);
            DB.closeStatement(st);
            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Cliente instantiateCliente(ResultSet rs) throws SQLException {
        Cliente obj = new Cliente();
        obj.setCpf(rs.getString("cpf"));
        obj.setNome(rs.getString("nome"));
        obj.setEmail(rs.getString("email"));
        obj.setEndereco(rs.getString("endereco"));
        obj.setTelefone(rs.getString("telefone"));
        return obj;
    }
}
