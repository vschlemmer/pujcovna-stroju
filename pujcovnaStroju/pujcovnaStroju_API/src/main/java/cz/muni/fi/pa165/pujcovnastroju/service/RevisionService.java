/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.pujcovnastroju.service;

import java.sql.Date;
import java.util.List;

import cz.muni.fi.pa165.pujcovnastroju.dto.RevisionDTO;

/**
 *
 * @author Matej
 */
public interface RevisionService {

    /**
     * Creates a new Revision
     *
     * @param revisionDTO to be created
     * @return created RevisionDTO
     * @throws IllegalArgumentException if the Revision is null
     */
    public RevisionDTO createBizRevision(RevisionDTO revisionDTO);

    /**
     *
     * Updates given revID
     *
     * @param revisionDTO to be updated
     * @return updated RevisionDTO
     * @throws IllegalArgumentException if the RevID is null
     */
    public RevisionDTO updateBizRevision(RevisionDTO revisionDTO);

    /**
     * Deletes an Revision
     *
     * @param revisionDTO to be deleted
     *
     * @throws IllegalArgumentException if the revisionDTO is null
     */
    public void deleteBizRevision(RevisionDTO revisionDTO);

    /**
     * Returns all revisions
     *
     * @throws IllegalArgumentException if the revID is null
     * @return list of all Revision
     */
    public List<RevisionDTO> findAllrevisionsBizRevision();

    /**
     * Reads Revision with given revID
     *
     * @param revID of the Revision
     * @return Revision with the given revID
     * @throws IllegalArgumentException if the revID is null
     */
    public RevisionDTO readBizRevision(Long revID);

    /**
     * Retrieves all revisions that were made in given time interval
     *
     * @param dateFrom date from which the revisions should be retrieved
     * @param dateTo date to which the revisions should be retrieved
     * @return list of revisions that suit the filter
     * @throws IllegalArgumentException if the revID is null
     */
    public List<RevisionDTO> findRevisionsByDateBizRevision(Date dateFrom, Date dateTo);
}
