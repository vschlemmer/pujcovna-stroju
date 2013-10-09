package cz.muni.fi.pa165.pujcovnastroju;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Vojtech Schlemmer
 */

@Entity
public class SystemUser implements Serializable {
    /**
    * 	auto generated serial id
    */
    private static final long serialVersionUID = -2299546650455665467L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private boolean emploee;
    
    @OneToMany(mappedBy = "systemUser", cascade = CascadeType.ALL)
    private List<Revision> revisions = new ArrayList<Revision>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isEmploee() {
        return emploee;
    }

    public void setEmploee(boolean emploee) {
        this.emploee = emploee;
    }
    
    public List<Revision> getRevisions() {
        return revisions;
    }

    public void setRevisions(List<Revision> revisions) {
        this.revisions = revisions;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SystemUser other = (SystemUser) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emploee=" + emploee + '}';
    }
    
}
