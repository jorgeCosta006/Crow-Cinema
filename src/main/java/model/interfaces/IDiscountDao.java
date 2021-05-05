package model.interfaces;

import java.util.List;

import model.entities.Discount;

public interface IDiscountDao {
	
	public void createOrUpdateDiscount(Discount discount);
	
	public List<Discount> listOfDiscounts();
}
