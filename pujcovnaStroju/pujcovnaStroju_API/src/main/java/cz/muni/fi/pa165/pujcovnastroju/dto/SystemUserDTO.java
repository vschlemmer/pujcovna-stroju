package cz.muni.fi.pa165.pujcovnastroju.dto;

import java.util.List;

/**
 * Implementation of SysteUser DTO
 * 
 * @author Vojtech Schlemmer
 */
public class SystemUserDTO {
    
    private Long id;
    private String firstName;
    private String lastName;
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
}
