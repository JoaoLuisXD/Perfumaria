package src.model.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import src.db.DB;
import src.model.exceptions.DBException;
import src.model.dao.EntregaDAO;
import src.model.entities.Entrega;
import src.model.entities.EntregaNormal;

public class EntregaDAOJDBC implements EntregaDAO {

    private Connection conn;

    public EntregaDAOJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Entrega obj) throws DBException  {
        try {
            PreparedStatement st = conn.prepareStatement(
                "INSERT INTO entrega (endereco, valor_entrega, data, status) VALUES (?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );

            st.setString(1, obj.getEndereco());
            st.setDouble(2, obj.getValorEntrega());
            st.setDate(3, new java.sql.Date(obj.getData().getTime()));
            st.setString(4, obj.getStatus());

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
    public void deleteById(Integer id) throws DBException {
        try {
            PreparedStatement st = conn.prepareStatement(
                "DELETE FROM entrega WHERE id=?"
            );

            st.setInt(1, id);
            st.executeUpdate();

            DB.closeStatement(st);

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
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

        // Aqui você **não pode criar new Entrega()** porque é abstrata.
        // Então precisamos decidir qual tipo usar.

        // Exemplo: criando sempre EntregaNormal (mínimo para funcionar).
        // Você pode depois melhorar isso com tipo no banco (tipo_entrega).
        Entrega obj = new EntregaNormal(
            rs.getInt("id"),
            rs.getString("endereco"),
            rs.getDouble("valor_entrega"),
            rs.getDate("data"),
            rs.getString("status")
        );

        return obj;
    }
}
