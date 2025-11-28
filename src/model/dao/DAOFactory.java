package src.model.dao;

import src.db.DB;
import src.model.dao.impl.PerfumeDAOJDBC;

public class DAOFactory {
    public static PerfumeDAO cPerfumeDAO(){
        return new PerfumeDAOJDBC(DB.getConn());
    }
}
