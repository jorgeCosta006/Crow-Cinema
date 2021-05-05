package model.interfaces;

import model.entities.Wallet;

public interface IWalletDao {
	
	public void createOrUpdateWallet(Wallet wallet);
}
