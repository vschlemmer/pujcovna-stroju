package cz.muni.fi.pa165.pujcovnastroju;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
/**
 *
 * @author Matej Fucek
 */

@Entity
public class Revision implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long revID; //PK to identify revsion ID
    private String performedBy; //  who performend the revision
    private String comment; // comment stating additional info about the revision
    private String revDate; // revision date
    private String machine; // machine mane



    public Long getRevID()  {
        return revID;
    }
    
    public void setRevID(Long revID) {
        this.revID = revID;
    }
  
    //PerformedBy
    public String getPerformedBy() {
        return performedBy;    
    }
    
    public void setPerformedBy(String performedBy) {
        this.performedBy = performedBy;
    }

    
    //Comment
    public String getComment() {
        return comment;    
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }

    //Machine
    public String getMachine()  {
        return machine;
    }
    
    public void setMachine(String machine) {
        this.machine = machine;
    }

    
    public String getRevDate() {
        return revDate;    
    }

    public void setRevDate(String revDate) {
        this.revDate = revDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.revID != null ? this.revID.hashCode() : 0);
        hash = 97 * hash + (this.performedBy != null ? this.performedBy.hashCode() : 0);
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
        if (this.revID != other.revID && (this.revID == null || !this.revID.equals(other.revID))) {
            return false;
        }
        if ((this.performedBy == null) ? (other.performedBy != null) : !this.performedBy.equals(other.performedBy)) {
            return false;
        }
        if ((this.comment == null) ? (other.comment != null) : !this.comment.equals(other.comment)) {
            return false;
        }
        if ((this.revDate == null) ? (other.revDate != null) : !this.revDate.equals(other.revDate)) {
            return false;
        }
        if ((this.machine == null) ? (other.machine != null) : !this.machine.equals(other.machine)) {
            return false;
        }
        return true;
    }
  
}