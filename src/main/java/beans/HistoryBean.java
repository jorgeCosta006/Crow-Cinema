package beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import model.entities.MoneyMovement;
import model.entities.SalesMovement;

@ManagedBean(name = "historyBean", eager = true)
@ViewScoped
public class HistoryBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<SalesMovement> listOfSalesMovement;
	private List<MoneyMovement> listMoneyMovement;
	
	public List<SalesMovement> getListOfSalesMovement() {
		return listOfSalesMovement;
	}
	public void setListOfSalesMovement(List<SalesMovement> listOfSalesMovement) {
		this.listOfSalesMovement = listOfSalesMovement;
	}
	public List<MoneyMovement> getListMoneyMovement() {
		return listMoneyMovement;
	}
	public void setListMoneyMovement(List<MoneyMovement> listMoneyMovement) {
		this.listMoneyMovement = listMoneyMovement;
	}
	
	
}
