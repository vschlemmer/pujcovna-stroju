package cz.muni.fi.pa165.pujcovnastroju.dto;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Default implementation of LoanDTO
 * 
 * @author xguttner
 */
public class LoanDTO {

	private Long id;
	private SystemUserDTO customer;
	private MachineDTO machines;
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

	public MachineDTO getMachine() {
		return machines;
	}

	public void setMachine(MachineDTO machine) {
		this.machines = machine;
	}

	public Date getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final LoanDTO other = (LoanDTO) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		return true;
	}

	public int hashCode() {
		int hash = 7;
		hash = 23 * hash + Objects.hashCode(this.id);
		return hash;
	}

	public String toString() {
		return "LoanDTO{" + "id=" + id + ", customer=" + customer
				+ ", machines=" + machines + ", loanTime=" + loanTime
				+ ", returnTime=" + returnTime + ", loanState=" + loanState
				+ '}';
	}
}
