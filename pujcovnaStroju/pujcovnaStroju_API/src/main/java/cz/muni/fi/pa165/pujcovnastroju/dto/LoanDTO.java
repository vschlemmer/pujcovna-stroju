/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.pujcovnastroju.dto;

import java.util.Date;
import java.util.List;

/**
 *
 * @author xguttner
 */
public class LoanDTO {
    
	private Long id;
	private SystemUserDTO customer;
	private List<MachineDTO> machines;
	private Date loanTime;
	private Date returnTime;
	private LoanStateEnumDTO loanState;

	public SystemUserDTO getCustomer() {
	    return customer;
	}

	public void setCustomer(SystemUserDTO customer) {
	    this.customer = customer;
	}

	public Long getId() {
	    return id;
	}

	public void setId(Long id) {
	    this.id = id;
	}

	public LoanStateEnumDTO getLoanState() {
	    return loanState;
	}

	public void setLoanState(LoanStateEnumDTO loanState) {
	    this.loanState = loanState;
	}

	public Date getLoanTime() {
	    return loanTime;
	}

	public void setLoanTime(Date loanTime) {
	    this.loanTime = loanTime;
	}

	public List<MachineDTO> getMachines() {
	    return machines;
	}

	public void setMachines(List<MachineDTO> machines) {
	    this.machines = machines;
	}

	public Date getReturnTime() {
	    return returnTime;
	}

	public void setReturnTime(Date returnTime) {
	    this.returnTime = returnTime;
	}
}
