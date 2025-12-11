package src.model.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import src.db.DB;
import src.exceptions.CampoObrigatorioException;
import src.exceptions.*;
import src.exceptions.DBIntegrityException;
import src.model.dao.PerfumeDAO;
import src.model.entities.Perfume;

public class PerfumeDAOJDBC implements PerfumeDAO {

    private Connection conn;

    public PerfumeDAOJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Perfume obj) throws CampoObrigatorioException {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                "INSERT INTO perfume (nome, marca, preco, estoque) VALUES (?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );

            st.setString(1, obj.getNome());
            st.setString(2, obj.getMarca());
            st.setDouble(3, obj.getPreco());
            st.setInt(4, obj.getEstoque());

            int rows = st.executeUpdate();

            if (rows > 0) {
                rs = st.getGeneratedKeys();
                if (rs.next()) {
                    obj.setId(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Perfume obj) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                "UPDATE perfume SET nome=?, marca=?, preco=?, estoque=? WHERE id=?"
            );

            st.setString(1, obj.getNome());
            st.setString(2, obj.getMarca());
            st.setDouble(3, obj.getPreco());
            st.setInt(4, obj.getEstoque());
            st.setInt(5, obj.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) throws DBIntegrityException{
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                "DELETE FROM perfume WHERE id=?"
            );

            st.setInt(1, id);
            st.executeUpdate();

        } catch (SQLException e) {
            throw new DBIntegrityException("Erro ao deletar");
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Perfume findById(Integer id) throws EntidadeNaoEncontradaException, DBException, CampoObrigatorioException {
    PreparedStatement st = null;
    ResultSet rs = null;

    try {
        st = conn.prepareStatement(
            "SELECT * FROM perfume WHERE id=?"
        );

        st.setInt(1, id);
        rs = st.executeQuery();

        if (!rs.next()) {
            throw new EntidadeNaoEncontradaException(
                "Perfume com ID " + id + " não encontrado."
            );
        }

        return instantiatePerfume(rs);

    } catch (SQLException e) {
        throw new DBException("Erro ao buscar perfume");
    } finally {
        DB.closeResultSet(rs);
        DB.closeStatement(st);
    }
}



    @Override
    public List<Perfume> findAll() throws CampoObrigatorioException {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM perfume");
            rs = st.executeQuery();

            List<Perfume> list = new ArrayList<>();

            while (rs.next()) {
                list.add(instantiatePerfume(rs));
            }

            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    private Perfume instantiatePerfume(ResultSet rs) throws SQLException, CampoObrigatorioException {
        Perfume obj = new Perfume();
        obj.setId(rs.getInt("id"));
        obj.setNome(rs.getString("nome"));
        obj.setMarca(rs.getString("marca"));
        obj.setPreco(rs.getDouble("preco"));
        obj.setEstoque(rs.getInt("estoque"));
        return obj;
    }
}
