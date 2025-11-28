package src.model.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import src.db.DB;
import src.model.dao.PerfumeDAO;
import src.model.entities.Perfume;

public class PerfumeDAOJDBC implements PerfumeDAO {

    private Connection conn;

    public PerfumeDAOJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Perfume obj) {
        try {
            PreparedStatement st = conn.prepareStatement(
                "INSERT INTO perfume (nome, marca, preco, estoque) VALUES (?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );

            st.setString(1, obj.getNome());
            st.setString(2, obj.getMarca());
            st.setDouble(3, obj.getPreco());
            st.setInt(4, obj.getEstoque());

            int rows = st.executeUpdate();

            if (rows > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            }

            DB.closeStatement(st);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Perfume obj) {
        try {
            PreparedStatement st = conn.prepareStatement(
                "UPDATE perfume SET nome=?, marca=?, preco=?, estoque=? WHERE id=?"
            );

            st.setString(1, obj.getNome());
            st.setString(2, obj.getMarca());
            st.setDouble(3, obj.getPreco());
            st.setInt(4, obj.getEstoque());
            st.setInt(5, obj.getId());

            st.executeUpdate();
            DB.closeStatement(st);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Integer id) {
        try {
            PreparedStatement st = conn.prepareStatement(
                "DELETE FROM perfume WHERE id=?"
            );

            st.setInt(1, id);
            st.executeUpdate();
            DB.closeStatement(st);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Perfume findById(Integer id) {
        try {
            PreparedStatement st = conn.prepareStatement(
                "SELECT * FROM perfume WHERE id=?"
            );
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            Perfume obj = null;

            if (rs.next()) {
                obj = instantiatePerfume(rs);
            }

            DB.closeResultSet(rs);
            DB.closeStatement(st);
            return obj;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Perfume> findAll() {
        try {
            PreparedStatement st = conn.prepareStatement(
                "SELECT * FROM perfume"
            );

            ResultSet rs = st.executeQuery();
            List<Perfume> list = new ArrayList<>();

            while (rs.next()) {
                list.add(instantiatePerfume(rs));
            }

            DB.closeResultSet(rs);
            DB.closeStatement(st);
            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Perfume instantiatePerfume(ResultSet rs) throws SQLException {
        Perfume obj = new Perfume();
        obj.setId(rs.getInt("id"));
        obj.setNome(rs.getString("nome"));
        obj.setMarca(rs.getString("marca"));
        obj.setPreco(rs.getDouble("preco"));
        obj.setEstoque(rs.getInt("estoque"));
        return obj;
    }
}
