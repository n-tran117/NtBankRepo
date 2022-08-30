package components;

import java.util.Date;

public abstract class Flow {
	
	private int identifier;
	private double amount;
	private int tragetAccountNumber;
	private boolean effect;
	private Date flowDate;
	private String Comment;
	
	
	public Flow(int identifier, double amount, int tragetAccountNumber, boolean effect, Date flowDate, String comment) {
		this.identifier = identifier;
		this.amount = amount;
		this.tragetAccountNumber = tragetAccountNumber;
		this.effect = effect;
		this.flowDate = flowDate;
		Comment = comment;
	}


	public int getIdentifier() {
		return identifier;
	}


	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public int getTragetAccountNumber() {
		return tragetAccountNumber;
	}


	public void setTragetAccountNumber(int tragetAccountNumber) {
		this.tragetAccountNumber = tragetAccountNumber;
	}


	public boolean isEffect() {
		return effect;
	}


	public void setEffect(boolean effect) {
		this.effect = effect;
	}


	public Date getFlowDate() {
		return flowDate;
	}


	public void setFlowDate(Date flowDate) {
		this.flowDate = flowDate;
	}


	public String getComment() {
		return Comment;
	}


	public void setComment(String comment) {
		Comment = comment;
	}
	
	

}
