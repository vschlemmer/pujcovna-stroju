package cz.muni.fi.pa165.pujcovnastroju.converter;

import cz.muni.fi.pa165.pujcovnastroju.dto.RevisionDTO;
import cz.muni.fi.pa165.pujcovnastroju.entity.Revision;

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
}
