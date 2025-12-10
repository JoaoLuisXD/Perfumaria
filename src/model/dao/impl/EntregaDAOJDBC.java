package src.model.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import src.db.DB;
import src.exceptions.DBException;
import src.exceptions.DBIntegrityException;
import src.model.dao.EntregaDAO;
import src.model.entities.Entrega;
import src.model.entities.EntregaNormal;
import src.model.entities.EntregaRapida;

public class EntregaDAOJDBC implements EntregaDAO {

    private Connection conn;

    public EntregaDAOJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Entrega obj) throws DBException{
    PreparedStatement st = null;

    try {
        st = conn.prepareStatement(
            "INSERT INTO entrega (endereco, valor_entrega, data, status, idPedido, tipo) " +
            "VALUES (?, ?, ?, ?, ?, ?)",
            Statement.RETURN_GENERATED_KEYS
        );

        st.setString(1, obj.getEndereco());
        st.setDouble(2, obj.getValorEntrega());
        st.setDate(3, new java.sql.Date(obj.getData().getTime()));
        st.setString(4, obj.getStatus());
        st.setInt(5, obj.getIdPedido());
        st.setString(6, (obj instanceof EntregaRapida) ? "RAPIDA" : "NORMAL");

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();
        if (rs.next()) {
            obj.setId(rs.getInt(1));
        }

        DB.closeStatement(st);
        DB.closeResultSet(rs);

    } catch (SQLException e) {
        throw new DBException(e.getMessage());
    }
}


    @Override
    public void update(Entrega obj) throws DBException {
        try {
            PreparedStatement st = conn.prepareStatement(
                "UPDATE entrega SET endereco=?, valor_entrega=?, data=?, status=? WHERE id=?"
            );

            st.setString(1, obj.getEndereco());
            st.setDouble(2, obj.getValorEntrega());
            st.setDate(3, new java.sql.Date(obj.getData().getTime()));
            st.setString(4, obj.getStatus());
            st.setInt(5, obj.getId());

            st.executeUpdate();
            DB.closeStatement(st);

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer id) throws DBIntegrityException {
        try {
            PreparedStatement st = conn.prepareStatement(
                "DELETE FROM entrega WHERE id=?"
            );

            st.setInt(1, id);
            st.executeUpdate();

            DB.closeStatement(st);

        } catch (SQLException e) {
            throw new DBIntegrityException("Erro ao deletar");
        }
    }

    @Override
    public Entrega findById(Integer id) throws DBException {
        try {
            PreparedStatement st = conn.prepareStatement(
                "SELECT * FROM entrega WHERE id=?"
            );
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            Entrega obj = null;

            if (rs.next()) {
                obj = instantiateEntrega(rs);
            }

            DB.closeStatement(st);
            DB.closeResultSet(rs);
            return obj;

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    @Override
public Entrega findByPedidoId(int pedidoId) throws DBException{
    PreparedStatement st = null;
    ResultSet rs = null;

    try {
        st = conn.prepareStatement(
            "SELECT * FROM entrega WHERE idPedido = ?"
        );

        st.setInt(1, pedidoId);
        rs = st.executeQuery();

        if (rs.next()) {
            return instantiateEntrega(rs);
        }

        return null;

    } catch (SQLException e) {
        throw new DBException("Erro ao buscar entrega por pedido: " + e.getMessage());
    } finally {
        DB.closeResultSet(rs);
        DB.closeStatement(st);
    }
}


    @Override
    public List<Entrega> findAll() throws DBException {
        try {
            PreparedStatement st = conn.prepareStatement(
                "SELECT * FROM entrega"
            );

            ResultSet rs = st.executeQuery();
            List<Entrega> list = new ArrayList<>();

            while (rs.next()) {
                list.add(instantiateEntrega(rs));
            }

            DB.closeStatement(st);
            DB.closeResultSet(rs);

            return list;

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    private Entrega instantiateEntrega(ResultSet rs) throws SQLException {

    String tipo = rs.getString("tipo");
    Entrega obj;

    if (tipo.equalsIgnoreCase("RAPIDA")) {
        obj = new EntregaRapida(
            rs.getInt("id"),
            rs.getString("endereco"),
            rs.getDouble("valor_entrega"),
            rs.getDate("data"),
            rs.getString("status"),
            rs.getInt("idPedido")
        );
    } else {
        obj = new EntregaNormal(
            rs.getInt("id"),
            rs.getString("endereco"),
            rs.getDouble("valor_entrega"),
            rs.getDate("data"),
            rs.getString("status"),
            rs.getInt("idPedido")
        );
    }

    return obj;
}

}
