package business;

import java.util.List;

import model.dao.SalesMovementDao;
import model.entities.SalesMovement;
import model.interfaces.ISalesMovementDao;

public class HistoryBusiness {

	public List<SalesMovement> returnSalesMovementList() {
		ISalesMovementDao smd = new SalesMovementDao();
		List<SalesMovement> list = smd.listOfSalesMovements();
		return list;
	}
}
