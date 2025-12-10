package src.model.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import src.db.DB;
import src.exceptions.DBException;
import src.exceptions.DBIntegrityException;
import src.exceptions.EntidadeJaExisteException;
import src.model.dao.RevendedorDAO;
import src.model.entities.Revendedor;

public class RevendedorDAOJDBC implements RevendedorDAO {

    private Connection conn;

    public RevendedorDAOJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Revendedor obj) throws EntidadeJaExisteException, DBException {
        try {
            PreparedStatement st = conn.prepareStatement(
                "INSERT INTO revendedor (cpf, nome, email, salario, telefone) VALUES (?, ?, ?, ?, ?)"
            );

            st.setString(1, obj.getCpf());
            st.setString(2, obj.getNome());
            st.setString(3, obj.getEmail());
            st.setFloat(4, obj.getSalario());
            st.setString(5, obj.getTelefone());

            st.executeUpdate();
            DB.closeStatement(st);

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
            throw new EntidadeJaExisteException("CPF de revendedor já cadastrado.");
            }
            throw new DBException("Erro ao inserir revendedor: " + e.getMessage());
        }
    }


    @Override
    public void update(Revendedor obj) {
        try {
            PreparedStatement st = conn.prepareStatement(
                "UPDATE revendedor SET nome=?, email=?, salario=?, telefone=? WHERE cpf=?"
            );

            st.setString(1, obj.getNome());
            st.setString(2, obj.getEmail());
            st.setFloat(3, obj.getSalario());
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
            PreparedStatement st = conn.prepareStatement(
                "DELETE FROM revendedor WHERE cpf=?"
            );

            st.setString(1, cpf);
            st.executeUpdate();
            DB.closeStatement(st);

        } catch (SQLException e) {
            throw new DBIntegrityException("Erro ao deletar");
        }
    }

    @Override
    public Revendedor findByCpf(String cpf) {
        try {
            PreparedStatement st = conn.prepareStatement(
                "SELECT * FROM revendedor WHERE cpf=?"
            );
            st.setString(1, cpf);

            ResultSet rs = st.executeQuery();
            Revendedor obj = null;

            if (rs.next()) {
                obj = instantiateRevendedor(rs);
            }

            DB.closeResultSet(rs);
            DB.closeStatement(st);
            return obj;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Revendedor> findAll() {
        try {
            PreparedStatement st = conn.prepareStatement(
                "SELECT * FROM revendedor"
            );

            ResultSet rs = st.executeQuery();
            List<Revendedor> list = new ArrayList<>();

            while (rs.next()) {
                list.add(instantiateRevendedor(rs));
            }

            DB.closeResultSet(rs);
            DB.closeStatement(st);
            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Revendedor instantiateRevendedor(ResultSet rs) throws SQLException {
        Revendedor obj = new Revendedor();
        obj.setCpf(rs.getString("cpf"));
        obj.setNome(rs.getString("nome"));
        obj.setEmail(rs.getString("email"));
        obj.setSalario(rs.getFloat("salario"));
        obj.setTelefone(rs.getString("telefone"));
        return obj;
    }
}
