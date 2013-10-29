package cz.muni.fi.pa165.pujcovnastroju.dto;

import java.util.Date;


/**
 * @author Matej Fucek
 *
 */
public class RevisionDTO 
{
    private Long revID;
    private MachineDTO machine;
    private Date revDate;
    private String comment;
    private SystemUserDTO systemUser;

    public RevisionDTO() {
    }

    public RevisionDTO(Long revID, MachineDTO machine, Date revDate, String comment, SystemUserDTO systemUser) {
        this.revID = revID;
        this.machine = machine;
        this.revDate = revDate;
        this.comment = comment;
        this.systemUser = systemUser;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getRevDate() {
        return revDate;
    }

    public void setRevDate(Date revDate) {
        this.revDate = revDate;
    }

    public Long getRevID() {
        return revID;
    }

    public void setRevID(Long revID) {
        this.revID = revID;
    }

    public MachineDTO getMachine() {
        return machine;
    }

    public void setMachine(MachineDTO machine) {
        this.machine = machine;
    }

    public SystemUserDTO getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUserDTO systemUser) {
        this.systemUser = systemUser;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (this.revID != null ? this.revID.hashCode() : 0);
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
        final RevisionDTO other = (RevisionDTO) obj;
        if (this.revID != other.revID && (this.revID == null || !this.revID.equals(other.revID))) {
            return false;
        }
        return true;
    }
    

}

