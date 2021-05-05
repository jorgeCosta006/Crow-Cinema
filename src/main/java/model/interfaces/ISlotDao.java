package model.interfaces;

import model.entities.Slot;

public interface ISlotDao {
	
	public void createOrUpdateSlot(Slot slot);
	
	public void deleteSlot(Slot slot);
}
