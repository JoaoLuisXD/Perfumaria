package src.model.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import src.db.DB;
import src.exceptions.DBIntegrityException;
import src.model.dao.IncluiDAO;
import src.model.entities.Inclui;

public class IncluiDAOJDBC implements IncluiDAO {

    private Connection conn;

    public IncluiDAOJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Inclui obj) {
        try {
            PreparedStatement st = conn.prepareStatement(
                "INSERT INTO inclui (id_pedido, id_perfume, qtd_itens, data) VALUES (?, ?, ?, ?)"
            );

            st.setInt(1, obj.getIdPedido());
            st.setInt(2, obj.getIdPerfume());
            st.setInt(3, obj.getQtd_itens());
            st.setDate(4, new java.sql.Date(obj.getData().getTime()));

            st.executeUpdate();
            DB.closeStatement(st);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Inclui obj) {
        try {
            PreparedStatement st = conn.prepareStatement(
                "UPDATE inclui SET qtd_itens=?, data=? WHERE id_pedido=? AND id_perfume=?"
            );

            st.setInt(1, obj.getQtd_itens());
            st.setDate(2, new java.sql.Date(obj.getData().getTime()));
            st.setInt(3, obj.getIdPedido());
            st.setInt(4, obj.getIdPerfume());

            st.executeUpdate();
            DB.closeStatement(st);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int idPedido, int idPerfume) throws DBIntegrityException{
        try {
            PreparedStatement st = conn.prepareStatement(
                "DELETE FROM inclui WHERE id_pedido=? AND id_perfume=?"
            );

            st.setInt(1, idPedido);
            st.setInt(2, idPerfume);
            st.executeUpdate();

            DB.closeStatement(st);

        } catch (SQLException e) {
            throw new DBIntegrityException("Erro ao deletar");
        }
    }

    @Override
    public Inclui findById(int idPedido, int idPerfume) {
        try {
            PreparedStatement st = conn.prepareStatement(
                "SELECT * FROM inclui WHERE id_pedido=? AND id_perfume=?"
            );

            st.setInt(1, idPedido);
            st.setInt(2, idPerfume);

            ResultSet rs = st.executeQuery();
            Inclui obj = null;

            if (rs.next()) {
                obj = instantiateInclui(rs);
            }

            DB.closeResultSet(rs);
            DB.closeStatement(st);
            return obj;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Inclui> findAll() {
        try {
            PreparedStatement st = conn.prepareStatement(
                "SELECT * FROM inclui"
            );

            ResultSet rs = st.executeQuery();
            List<Inclui> list = new ArrayList<>();

            while (rs.next()) {
                list.add(instantiateInclui(rs));
            }

            DB.closeResultSet(rs);
            DB.closeStatement(st);
            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Inclui instantiateInclui(ResultSet rs) throws SQLException {
        Inclui obj = new Inclui();
        obj.setIdPedido(rs.getInt("id_pedido"));
        obj.setIdPerfume(rs.getInt("id_perfume"));
        obj.setQtd_itens(rs.getInt("qtd_itens"));
        obj.setData(rs.getDate("data"));
        return obj;
    }
}
