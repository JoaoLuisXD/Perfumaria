package src.model.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import src.db.DB;
import src.exceptions.CampoObrigatorioException;
import src.exceptions.DBIntegrityException;
import src.exceptions.EntidadeNaoEncontradaException;
import src.model.dao.PerfumeDAO;
import src.model.entities.Perfume;

public class PerfumeDAOJDBC implements PerfumeDAO {

    private Connection conn;

    public PerfumeDAOJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Perfume obj) throws CampoObrigatorioException{
        try {
            PreparedStatement st = conn.prepareStatement(
                "INSERT INTO perfume (nome, preco, estoque, cnpj_marca) VALUES (?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );

            st.setString(1, obj.getNome());
            st.setDouble(2, obj.getPreco());
            st.setInt(3, obj.getEstoque());
            st.setString(4, obj.getMarca()); // <<< AQUI CORRIGIDO

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
    public void update(Perfume obj) {
        try {
            PreparedStatement st = conn.prepareStatement(
                "UPDATE perfume SET nome=?, preco=?, estoque=?, cnpj_marca=? WHERE id=?"
            );

            st.setString(1, obj.getNome());
            st.setDouble(2, obj.getPreco());
            st.setInt(3, obj.getEstoque());
            st.setString(4, obj.getMarca());
            st.setInt(5, obj.getId());

            st.executeUpdate();
            DB.closeStatement(st);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Integer id) throws DBIntegrityException {
        try {
            PreparedStatement st = conn.prepareStatement(
                "DELETE FROM perfume WHERE id=?"
            );

            st.setInt(1, id);
            st.executeUpdate();

            DB.closeStatement(st);
        } catch (SQLException e) {
            throw new DBIntegrityException("Erro ao deletar perfume.");
        }
    }

    @Override
    public Perfume findById(Integer id) throws EntidadeNaoEncontradaException {
        try {
            PreparedStatement st = conn.prepareStatement(
                "SELECT * FROM perfume WHERE id=?"
            );
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();

            if (!rs.next()) {
                throw new EntidadeNaoEncontradaException("Perfume não encontrado.");
            }

            Perfume obj = instantiatePerfume(rs);

            DB.closeResultSet(rs);
            DB.closeStatement(st);
            return obj;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar perfume.");
        }
    }

    @Override
    public List<Perfume> findAll() {
        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM perfume");

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
        Perfume obj = new Perfume(
            rs.getInt("id"),
            rs.getString("nome"),
            rs.getString("cnpj_marca"),  // <<< AQUI CORRIGIDO
            rs.getDouble("preco"),
            rs.getInt("estoque")
        );
        return obj;
    }
}
