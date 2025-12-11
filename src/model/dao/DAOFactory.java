package src.model.dao;

import src.db.DB;
import src.model.dao.impl.ClienteDAOJDBC;
import src.model.dao.impl.EntregaDAOJDBC;
import src.model.dao.impl.MarcaDAOJDBC;
import src.model.dao.impl.PedidoDAOJDBC;
import src.model.dao.impl.PerfumeDAOJDBC;
import src.model.dao.impl.RevendedorDAOJDBC;
import src.model.dao.impl.IncluiDAOJDBC;

public class DAOFactory {

    public static PerfumeDAO cPerfumeDAO() {
        return new PerfumeDAOJDBC(DB.getConn());
    }

    public static ClienteDAO cClienteDAO() {
        return new ClienteDAOJDBC(DB.getConn());
    }

    public static MarcaDAO cMarcaDAO() {
        return new MarcaDAOJDBC(DB.getConn());
    }

    public static PedidoDAO cPedidoDAO() {
        return new PedidoDAOJDBC(DB.getConn());
    }

    public static RevendedorDAO cRevendedorDAO() {
        return new RevendedorDAOJDBC(DB.getConn());
    }

    public static IncluiDAO cIncluiDAO() {
        return new IncluiDAOJDBC(DB.getConn());
    }

    public static EntregaDAO createEntregaDAO() {
    return new EntregaDAOJDBC(DB.getConn());
    }

}
