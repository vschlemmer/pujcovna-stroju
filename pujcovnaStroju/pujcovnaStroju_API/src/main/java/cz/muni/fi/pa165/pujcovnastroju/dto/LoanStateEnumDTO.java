package cz.muni.fi.pa165.pujcovnastroju.dto;

/**
 * Default implementation of LoanStateEnumDTO
 * 
 * @author xguttner
 */
public class LoanStateEnumDTO {
	public Long id;
	public String typeLabel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTypeLabel() {
		return typeLabel;
	}

	public void setTypeLabel(String typeLabel) {
		this.typeLabel = typeLabel;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", typeLabel=" + typeLabel + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		LoanStateEnumDTO other = (LoanStateEnumDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
