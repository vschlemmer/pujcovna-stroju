/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.pujcovnastroju.dto;

import java.util.List;

/**
 * Default implementation of Machine DTO
 * 
 * @author Michal Merta 374015
 */
public class MachineDTO {

	private Long id;
	private String label;
	private String decription;
	private MachineTypeEnumDTO type;
	private List<LoanDTO> loans;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getDecription() {
		return decription;
	}
	public void setDecription(String decription) {
		this.decription = decription;
	}
	public MachineTypeEnumDTO getType() {
		return type;
	}
	public void setType(MachineTypeEnumDTO type) {
		this.type = type;
	}
	public List<LoanDTO> getLoans() {
		return loans;
	}
	public void setLoans(List<LoanDTO> loans) {
		this.loans = loans;
	}

	
}
