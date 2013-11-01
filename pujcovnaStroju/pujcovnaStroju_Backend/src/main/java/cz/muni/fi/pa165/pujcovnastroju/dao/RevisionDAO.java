package cz.muni.fi.pa165.pujcovnastroju.dao;

import java.sql.Date;
import java.util.List;

import cz.muni.fi.pa165.pujcovnastroju.entity.Revision;

/**
 * * @author Matej Fucek
 */
public interface RevisionDAO {

	/**
	 * Creates a new Revision
	 * 
	 * @param revID
	 *            to be created
	 * @return created Revision
	 * @throws IllegalArgumentException
	 *             if the Revision is null
	 */
	public Revision create(Revision revID);

	/**
	 * Deletes an Revision
	 * 
	 * @param revID
	 *            to be deleted
	 * @return deleted RevID
	 * @throws IllegalArgumentException
	 *             if the RevID is null
	 */
	public Revision delete(Revision revID);

	/**
	 * Updates given revID
	 * 
	 * @param revID
	 *            to be updated
	 * @return updated RevID
	 * @throws IllegalArgumentException
	 *             if the RevID is null
	 */
	public Revision update(Revision revID);

	/**
	 * Returns all Revisions
	 * 
	 * @return list of all Revision
	 * @throws IllegalArgumentException
	 *             if the RevID is null
	 */
	public List<Revision> findAllrevisions();

	/**
	 * Reads Revision with given revID
	 * 
	 * @param revID
	 *            of the Revision
	 * @return Revision with the given revID
	 * 
	 */
	public Revision read(Long revID);

	/**
	 * Retrieves all revisions that were made in given time interval
	 * 
	 * @param dateFrom
	 *            date from which the revisions should be retrieved
	 * @param dateTo
	 *            date to which the revisions should be retrieved
	 * @return list of revisions that suit the filter
	 */
	public List<Revision> findRevisionsByDate(Date dateFrom, Date dateTo);
}