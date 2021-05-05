package model.interfaces;

import java.util.List;

import model.entities.SalesMovement;

public interface ISalesMovementDao {
	
	public void createOrUpdateSalesMovement(SalesMovement salesMovement);
	
	public List<SalesMovement> listOfSalesMovements();
}
