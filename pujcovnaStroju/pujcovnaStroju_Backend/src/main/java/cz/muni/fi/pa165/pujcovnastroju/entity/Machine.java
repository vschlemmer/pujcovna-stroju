package cz.muni.fi.pa165.pujcovnastroju.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * Entity represents a machine in system
 * 
 * @author Michal Merta 374015
 * 
 */
@Entity(name = "Machine")
public class Machine implements Serializable, Cloneable {

	/**
	 * auto generated serial id
	 */
	private static final long serialVersionUID = 5751229799445520078L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, length = 50)
	private String label;

	@Column(nullable = true, length = 255)
	private String decription;

	@Column(nullable = false)
	private MachineTypeEnum type;

	@ManyToMany(mappedBy = "machines")
	private List<Loan> loans;

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

	public MachineTypeEnum getType() {
		return type;
	}

	public void setType(MachineTypeEnum type) {
		this.type = type;
	}
	
	public List<Loan> getLoans() {
		return loans;
	}

	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}

	@Override
	public Object clone() {
		Machine newMachine = new Machine();
		newMachine.setId(this.id);
		newMachine.setLabel(this.getLabel());
		newMachine.setDecription(this.getDecription());
		newMachine.setType(this.getType());
		return newMachine;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((decription == null) ? 0 : decription.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Machine other = (Machine) obj;
		if (decription == null) {
			if (other.decription != null)
				return false;
		} else if (!decription.equals(other.decription))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Machine [id=" + id + ", label=" + label + ", decription="
				+ decription + ", type=" + type + "]";
	}

}
