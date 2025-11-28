package src.application;

import src.model.entities.Perfume;
import src.model.dao.PerfumeDAO;
import src.model.dao.DAOFactory;

public class Main {
    public static void main(String[] args) {

        PerfumeDAO perfumeDAO = DAOFactory.cPerfumeDAO();

        Perfume p = new Perfume(null, "212 VIP", "Carolina Herrera", 599.90, 10);

        perfumeDAO.insert(p);

        System.out.println("Inserido! ID gerado: " + p.getId());
    }
}
