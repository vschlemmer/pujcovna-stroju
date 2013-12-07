package cz.muni.fi.pa165.pujcovnaStroju.rest.converter;

import org.apache.commons.lang.StringEscapeUtils;

import cz.muni.fi.pa165.pujcovnastroju.dto.MachineDTO;
import cz.muni.fi.pa165.pujcovnastroju.dto.SystemUserDTO;

/**
 * Class provides simple "marshaling" methods
 * @author Michal Merta
 *
 */
public class DTOtoXMLConverter {
	
	public static String machineDTOtoXML(MachineDTO machine) {
		StringBuilder builder = new StringBuilder();
		builder.append("<machine>");
		builder.append("<id>" + machine.getId() + "</id>");
		builder.append("<label>" + machine.getLabel() + "</label>");
		if (machine.getDescription() != null) {
			builder.append("<description>" + machine.getDescription() + "</description>");
		}
		builder.append("<type>" + machine.getType().getTypeLabel() + "</type>");
		builder.append("</machine>");
		return StringEscapeUtils.escapeXml(builder.toString());
	}
	
	public static String systemUserDTOtoXML(SystemUserDTO user) {
		StringBuilder builder = new StringBuilder();
		builder.append("<user>");
		builder.append("<id>" + user.getId() + "</id>");
		builder.append("<firstName>" + user.getFirstName() + "</firstName>");
		builder.append("<lastName>" + user.getLastName() + "</lastName");
		builder.append("<type>" + user.getType() + "</type>");
		builder.append("</user>");
		return StringEscapeUtils.escapeXml(builder.toString());
	}
}
