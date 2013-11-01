package cz.muni.fi.pa165.pujcovnastroju.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;

import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Matej Fucek
 */
@Entity
public class Revision implements Serializable {

    /**
     * auto generated serial id
     */
    private static final long serialVersionUID = -1600141775151321009L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long revID;
    private String comment;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date revDate;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
        CascadeType.REFRESH})
    private Machine machine;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
        CascadeType.REFRESH})
    private SystemUser systemUser;

    public Long getRevID() {
        return revID;
    }

    public void setRevID(Long revID) {
        this.revID = revID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public Date getRevDate() {
        return revDate;
    }

    public void setRevDate(Date revDate) {
        this.revDate = revDate;
    }

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.revID != null ? this.revID.hashCode() : 0);
        hash = 97 * hash + (this.comment != null ? this.comment.hashCode() : 0);
        hash = 97 * hash + (this.revDate != null ? this.revDate.hashCode() : 0);
        hash = 97 * hash + (this.machine != null ? this.machine.hashCode() : 0);
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
        final Revision other = (Revision) obj;
        if (this.revID != other.revID
                && (this.revID == null || !this.revID.equals(other.revID))) {
            return false;
        }
        if ((this.comment == null) ? (other.comment != null) : !this.comment
                .equals(other.comment)) {
            return false;
        }
        if ((this.revDate == null) ? (other.revDate != null) : !this.revDate
                .equals(other.revDate)) {
            return false;
        }
        if ((this.machine == null) ? (other.machine != null) : !this.machine
                .equals(other.machine)) {
            return false;
        }
        return true;
    }
}