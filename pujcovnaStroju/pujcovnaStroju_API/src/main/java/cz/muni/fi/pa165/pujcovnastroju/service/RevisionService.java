package cz.muni.fi.pa165.pujcovnastroju.service;

import cz.muni.fi.pa165.pujcovnastroju.dto.MachineDTO;
import java.util.Date;
import java.util.List;

import cz.muni.fi.pa165.pujcovnastroju.dto.RevisionDTO;
import cz.muni.fi.pa165.pujcovnastroju.dto.SystemUserDTO;

/**
 *
 * @author Matej
 */
public interface RevisionService {

	/**
	 * Creates a new Revision
	 * 
	 * @param revisionDTO
	 *            to be created
	 * @return created RevisionDTO
	 * @throws DataAccessException
	 *             if the Revision is null
	 */
	public RevisionDTO createBizRevision(RevisionDTO revisionDTO);

	/**
	 * 
	 * Updates given revID
	 * 
	 * @param revisionDTO
	 *            to be updated
	 * @return updated RevisionDTO
	 * @throws DataAccessException
	 *             if the RevID is null
	 */
	public RevisionDTO updateBizRevision(RevisionDTO revisionDTO);

	/**
	 * Deletes an Revision
	 * 
	 * @param revisionDTO
	 *            to be deleted
	 * @return empty deleted Revision
	 * @throws DataAccessException
	 *             if the revisionDTO is null
	 */
	public RevisionDTO deleteBizRevision(RevisionDTO revisionDTO);

	/**
	 * Returns all revisions
	 * 
	 * @throws DataAccessException
	 *             if the revID is null
	 * @return list of all Revision
	 */
	public List<RevisionDTO> findAllrevisionsBizRevision();

	/**
	 * Reads Revision with given revID
	 * 
	 * @param revID
	 *            of the Revision
	 * @return Revision with the given revID
	 * @throws DataAccessException
	 *             if the revID is null
	 */
	public RevisionDTO readBizRevision(Long revID);

	/**
	 * Retrieves all revisions that were made in given time interval
	 * 
	 * @param dateFrom
	 *            date from which the revisions should be retrieved
	 * @param dateTo
	 *            date to which the revisions should be retrieved
	 * @return list of revisions that suit the filter
	 * @throws DataAccessException
	 *             if the revID is null
	 */
	public List<RevisionDTO> findRevisionsByDateBizRevision(Date dateFrom,
			Date dateTo);
        
        /**
	 * Retrieves revisions by the given parameters
	 * 
	 * @param comment revision's comment
	 * @param revDate when was the revision created
	 * @param machine revisioned machine
	 * @param systemUser the revisioner
	 * @return list of the revisions satisfying the parameters
	 */
        public List<RevisionDTO> findRevisionsByParams(String comment, Date revDate,
                            MachineDTO machine, SystemUserDTO systemUser);
}
