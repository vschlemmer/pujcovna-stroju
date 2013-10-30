/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.pujcovnastroju.serviceimpl;
import java.util.ArrayList;
import java.util.List;

import cz.muni.fi.pa165.pujcovnastroju.converter.RevisionDTOConverter;
import cz.muni.fi.pa165.pujcovnastroju.dao.RevisionDAO;
import cz.muni.fi.pa165.pujcovnastroju.dto.RevisionDTO;
import cz.muni.fi.pa165.pujcovnastroju.entity.Revision;
import cz.muni.fi.pa165.pujcovnastroju.service.RevisionService;
/**
 *
 * @author Matej
 */
public class RevisionServiceImpl {
//    private RevisionDAO rDAO;
//    public void setCustomerDao(RevisionDAO rDAO) {
//        this.rDAO = rDAO;
//    //need to add AplicationContextXML
//    }
//        
//    public Revision createBizRevision(Revision revID){
//         rDAO.create(RevisionDTOConverter.dtoToEntity(revision));
//    
//    }
//
//    public Revision deleteBizRevision(Revision revID){
//        rDAO.delete(RevisionDTOConverter.dtoToEntity(revision));
//    
//    }
//
//    public Revision updateBizRevision(Revision revID){
//        rDAO.update(RevisionDTOConverter.dtoToEntity(revision));
//    }
//    
//
//    public List<Revision> findAllrevisionsBizRevision(){
//    List<Revision> revisions = rDAO.findAllrevisions();
//        List<RevisionDTO> ret = new ArrayList<>();
//
//        for(Revision r : revisions) {
//            ret.add(RevisionDTOConverter.entityToDTO(r));
//        }
//
//        return ret;    
//    
//    }
//
//    public Revision readBizRevision(Long revID){
//        DAO.read(RevisionDTOConverter.dtoToEntity(revision));
//    }
//    
//    public List<Revision> findRevisionsByDateBizRevision(Date dateFrom, Date dateTo){
//        
//        //need to think about this one
//    }
}