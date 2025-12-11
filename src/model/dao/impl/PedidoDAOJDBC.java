package src.model.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import src.db.DB;
import src.exceptions.CampoObrigatorioException;
import src.exceptions.DBIntegrityException;
import src.exceptions.DBException;
import src.exceptions.EntidadeNaoEncontradaException;
import src.model.dao.PedidoDAO;
import src.model.entities.Pedido;

public class PedidoDAOJDBC implements PedidoDAO {

    private Connection conn;

    public PedidoDAOJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Pedido obj) throws CampoObrigatorioException {
        try {
            PreparedStatement st = conn.prepareStatement(
                "INSERT INTO pedido (valor, comissao, cpfCliente, cpfRevendedor) VALUES (?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );

            st.setDouble(1, obj.getValor());
            st.setDouble(2, obj.getValor() * 0.5);
            st.setString(3, obj.getCliente());
            st.setString(4, obj.getRevendedor());

            int rows = st.executeUpdate();

            if (rows > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    obj.setId(rs.getInt(1));
                }
                DB.closeResultSet(rs);
            }

            DB.closeStatement(st);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Pedido obj) {
        try {
            PreparedStatement st = conn.prepareStatement(
                "UPDATE pedido SET valor=?, comissao=?, cpfCliente=?, cpfRevendedor=? WHERE id=?"
            );

            st.setDouble(1, obj.getValor());
            st.setDouble(2, obj.getValor() * 0.5);
            st.setString(3, obj.getCliente());
            st.setString(4, obj.getRevendedor());
            st.setInt(5, obj.getId());

            st.executeUpdate();
            DB.closeStatement(st);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Integer id) throws DBIntegrityException{
        try {
            PreparedStatement st = conn.prepareStatement(
                "DELETE FROM pedido WHERE id=?"
            );

            st.setInt(1, id);
            st.executeUpdate();

            DB.closeStatement(st);
        } catch (SQLException e) {
            throw new DBIntegrityException("Erro ao deletar");
        }
    }

    @Override
    public Pedido findById(Integer id) throws EntidadeNaoEncontradaException, DBException, CampoObrigatorioException {
    try {
        PreparedStatement st = conn.prepareStatement(
            "SELECT * FROM pedido WHERE id=?"
        );
        st.setInt(1, id);

        ResultSet rs = st.executeQuery();

        // Caso não encontre o pedido
        if (!rs.next()) {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
            throw new EntidadeNaoEncontradaException("Pedido com id " + id + " não encontrado.");
        }

        // Encontrou → instanciando o Pedido
        Pedido obj = instantiatePedido(rs);

        DB.closeResultSet(rs);
        DB.closeStatement(st);
        return obj;

    } catch (SQLException e) {
        throw new DBException("Erro ao buscar pedido no banco de dados");
    }
}


    @Override
    public List<Pedido> findAll() throws CampoObrigatorioException {
        try {
            PreparedStatement st = conn.prepareStatement(
                "SELECT * FROM pedido"
            );

            ResultSet rs = st.executeQuery();
            List<Pedido> list = new ArrayList<>();

            while (rs.next()) {
                list.add(instantiatePedido(rs));
            }

            DB.closeResultSet(rs);
            DB.closeStatement(st);
            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Pedido instantiatePedido(ResultSet rs) throws SQLException, CampoObrigatorioException {
        Pedido obj = new Pedido();
        obj.setId(rs.getInt("id"));
        obj.setValor(rs.getDouble("valor"));
        obj.setCliente(rs.getString("cpfCliente"));
        obj.setRevendedor(rs.getString("cpfRevendedor"));
        return obj;
    }
}
