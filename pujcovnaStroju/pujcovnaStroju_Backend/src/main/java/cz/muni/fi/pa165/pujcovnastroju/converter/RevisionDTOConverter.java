package cz.muni.fi.pa165.pujcovnastroju.converter;

import cz.muni.fi.pa165.pujcovnastroju.dto.RevisionDTO;
import cz.muni.fi.pa165.pujcovnastroju.entity.Revision;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matej fucek
 */
public class RevisionDTOConverter {
    /*
     * Converts RevisionDTO to a Revision entity.
     * @param dto DTO to convert.
     * @return Revision entity according to the DTO. If dto is null, returns null.
     */

    public static Revision dtoToEntity(RevisionDTO dto) {

        if (dto == null) {
            return null;
        }

        Revision revision = new Revision();

        revision.setRevID(dto.getRevID());
        revision.setRevDate(dto.getRevDate());
        revision.setMachine(MachineDTOConverter.dtoToEntity(dto.getMachine()));
        revision.setComment(dto.getComment());
        revision.setSystemUser(SystemUserDTOConverter.dtoToEntity(dto.getSystemUser()));

        return revision;
    }

    /*
     * Converts Revision entity to a RevisionDTO.
     * @param revision Entity to convert.
     * @return RevisionDTO according to the entity. If revision is null, returns null.
     */
    public static RevisionDTO entityToDTO(Revision revision) {

        if (revision == null) {
            return null;
        }

        RevisionDTO revisionDTO = new RevisionDTO(revision.getRevID(),
                MachineDTOConverter.entityToDto(revision.getMachine()),
                revision.getRevDate(),
                revision.getComment(),
                SystemUserDTOConverter.entityToDTO(revision.getSystemUser()));

        return revisionDTO;
    }
    
    public static List<RevisionDTO> listToDto(List<Revision> list) {
		 List<RevisionDTO> resultList = new ArrayList<>();
		 for (Revision entity: list) {
			 resultList.add(RevisionDTOConverter.entityToDTO(entity));
		 }
		 return resultList;
	 }
	 
	 public static List<Revision> listToEntities(List<RevisionDTO> listDTO) {
		List<Revision> resultList = new ArrayList<>();
		for (RevisionDTO dto : listDTO) {
		    resultList.add(RevisionDTOConverter.dtoToEntity(dto));
		}
		return resultList;
	 }
}
