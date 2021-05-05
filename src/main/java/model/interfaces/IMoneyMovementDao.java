package model.interfaces;

import java.util.List;

import model.entities.MoneyMovement;

public interface IMoneyMovementDao {
	
	public void createOrUpdateMoneyMovement(MoneyMovement moneyMovement);
	
	public List<MoneyMovement> listOfMoneyMovements();
}
