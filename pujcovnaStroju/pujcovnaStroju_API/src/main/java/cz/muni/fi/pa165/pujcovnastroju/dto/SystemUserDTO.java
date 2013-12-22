package cz.muni.fi.pa165.pujcovnastroju.dto;

import java.util.List;
import java.util.Objects;

/**
 * Implementation of SysteUser DTO
 * 
 * @author Vojtech Schlemmer
 */
public class SystemUserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String confPassword;
    private UserTypeEnumDTO type;
    private List<LoanDTO> loans;
    private List<RevisionDTO> revisions;

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

    public UserTypeEnumDTO getType() {
        return type;
    }

    public void setType(UserTypeEnumDTO type) {
        this.type = type;
    }

    public List<LoanDTO> getLoans() {
        return loans;
    }

    public void setLoans(List<LoanDTO> loans) {
        this.loans = loans;
    }

    public List<RevisionDTO> getRevisions() {
        return revisions;
    }

    public void setRevisions(List<RevisionDTO> revisions) {
        this.revisions = revisions;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfPassword() {
        return confPassword;
    }

    public void setConfPassword(String confPassword) {
        this.confPassword = confPassword;
    }
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.id);
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
        final SystemUserDTO other = (SystemUserDTO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SystemUserDTO{" + "id=" + id + ", firstName=" + firstName + 
                ", lastName=" + lastName + ", username=" + username + 
                ", type=" + type + ", loans=" + 
                loans + ", revisions=" + revisions + '}';
    }

    

}
