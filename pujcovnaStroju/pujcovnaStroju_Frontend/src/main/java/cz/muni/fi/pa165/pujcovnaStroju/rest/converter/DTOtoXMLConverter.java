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
		builder.append("<id>" + StringEscapeUtils.escapeXml(machine.getId().toString()) + "</id>");
		builder.append("<label>" + StringEscapeUtils.escapeXml(machine.getLabel()) + "</label>");
		if (machine.getDescription() != null) {
			builder.append("<description>" + StringEscapeUtils.escapeXml(machine.getDescription()) + "</description>");
		}
		builder.append("<type>" + StringEscapeUtils.escapeXml(machine.getType().getTypeLabel()) + "</type>");
		builder.append("</machine>");
		return builder.toString();
	}
	
	public static String systemUserDTOtoXML(SystemUserDTO user) {
		StringBuilder builder = new StringBuilder();
		builder.append("<user>");
		builder.append("<id>" + StringEscapeUtils.escapeXml(user.getId().toString()) + "</id>");
		builder.append("<firstName>" + StringEscapeUtils.escapeXml(user.getFirstName()) + "</firstName>");
		builder.append("<lastName>" + StringEscapeUtils.escapeXml(user.getLastName()) + "</lastName>");
		builder.append("<type>" + StringEscapeUtils.escapeXml(user.getType().getTypeLabel()) + "</type>");
		builder.append("</user>");
		return builder.toString();
	}
}
