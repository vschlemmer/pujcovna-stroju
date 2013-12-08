package cz.muni.fi.pa1685.pujcovnaStroju.restclient.util;

import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import cz.muni.fi.pa165.pujcovnastroju.dto.MachineDTO;
import cz.muni.fi.pa165.pujcovnastroju.dto.MachineTypeEnumDTO;

public class MessageResolver {

	List<Object> result;
	private static final String STATUS_SUCCESS = "success";
	private static final String STATUS_ERROR = "error";
	
	private static final String ELEMENT_MACHINE = "machine";
	private static final String ELEMENT_MACHINES = "machines";
	private static final String ELEMENT_MESSAGE = "message";
	
	private static final String MACHINE_ID = "id";
	private static final String MACHINE_LABEL = "label";
	private static final String MACHINE_TYPE = "type";
	private static final String MACHINE_DESCRIPTION = "description";
	
	private static ErrorHandler handler = new XMLerrorHandler();
	private List<? extends Object> response;

	public MessageResolver(String messageBody) throws SAXException,
			IOException, ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(true);
		factory.setNamespaceAware(true);

		DocumentBuilder builder = factory.newDocumentBuilder();
		builder.setErrorHandler(handler);
		
		
		
		InputSource is = new InputSource();
	    is.setCharacterStream(new StringReader(messageBody));
		Document document = builder.parse(is);
		Element root = (Element) document.getDocumentElement();
		
		String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
		SchemaFactory schemaFactory = SchemaFactory.newInstance(language);
		URL url = this.getClass().getResource("/schema.xsd");
		Schema schema = schemaFactory.newSchema( url);
		Validator validator = schema.newValidator();
		validator.validate(new DOMSource(document));

		switch (root.getAttribute("status")) {
		case STATUS_SUCCESS:
			response = parseSuccess(root);
			break;
		case STATUS_ERROR:
			response = parseErrorResponse(root);
			break;
		default:
			break;
		}
	}
	
	public List<? extends Object> getResponse() {
		return response;
	}
	
	/**
	 * returns list of objects 
	 * @param root
	 * @return
	 */
	private List<? extends Object> parseSuccess(Element root) {
		Element element = (Element) root.getFirstChild();
		switch (element.getNodeName()) {
			case ELEMENT_MACHINES: return parseMachines(element);
			case ELEMENT_MACHINE:
				ArrayList <MachineDTO> list = new ArrayList<>();
				list.add(parseMachine(element));
				return list;
			default: break;
		}
		return null;
	}
	
	
	/**
	 * creates List of {@link MachineDTO} from <machines></machines> element
	 * 
	 * @param element
	 * @return
	 */
	private List<MachineDTO> parseMachines(Element element) {
		List<MachineDTO> machineList = new ArrayList<>();
		NodeList nodes = element.getElementsByTagName(ELEMENT_MACHINE);
		for (int i = 0; i < nodes.getLength(); i++) {
			machineList.add(parseMachine((Element) nodes.item(i)));
		}
		return machineList;
	}

	/**
	 * creates List of error messages
	 * @param element
	 * @return
	 */
	private List<String> parseErrorResponse(Element element) {
		List<String> errorList = new ArrayList<>();
		NodeList messageList = element.getElementsByTagName(ELEMENT_MESSAGE);
		for (int i = 0; i < messageList.getLength(); i++) {
			errorList.add(messageList.item(i).getTextContent());
		}
		return errorList;
	}

	/**
	 * creates {@link MachineDTO} object from <machine></machine> element
	 * 
	 * @param element
	 * @return
	 */
	private MachineDTO parseMachine(Element element) {
		MachineDTO machine = new MachineDTO();
		machine.setId(Long.valueOf(element.getElementsByTagName(MACHINE_ID)
				.item(0).getTextContent()));
		machine.setLabel(element.getElementsByTagName(MACHINE_LABEL).item(0)
				.getTextContent());
		machine.setDescription(element
				.getElementsByTagName(MACHINE_DESCRIPTION).item(0)
				.getTextContent());
		MachineTypeEnumDTO type = new MachineTypeEnumDTO();
		type.setTypeLabel(element.getElementsByTagName(MACHINE_TYPE).item(0)
				.getTextContent());
		machine.setType(type);
		return machine;
	}
}
