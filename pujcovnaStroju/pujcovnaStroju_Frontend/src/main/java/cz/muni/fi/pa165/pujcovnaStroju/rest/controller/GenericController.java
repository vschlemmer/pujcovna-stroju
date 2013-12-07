package cz.muni.fi.pa165.pujcovnaStroju.rest.controller;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * generic REST controller
 * 
 * @author Michal Merta
 * 
 */
@Controller
@RequestMapping(value = "/rest")
public class GenericController {

	/**
	 * returns XML schema of REST responses
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("schema.xml")
	public HttpEntity<byte[]> getSchema(ModelMap model) {
		StringBuilder builder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		builder.append("<response staus=\"success\">");
		builder.append("<text>Schema is hidden at the end of the rainbow stealing "
				+ "leprechaun's pot of gold. It's quite sad.</text>");
		builder.append("</response>");
		return returnXML(builder.toString());
	}

	/**
	 * creates {@link HttpEntity} for given xml string
	 * 
	 * @param xml
	 * @return
	 */
	public static HttpEntity<byte[]> returnXML(String xml) {
		byte content[] = formatStringAsXML(xml).getBytes();
		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType("application", "xml"));
		header.setContentLength(content.length);
		return new HttpEntity<byte[]>(content, header);
	}

	/**
	 * creates {@link HttpEntity} for given error message
	 * 
	 * @param message
	 * @return
	 */
	public static HttpEntity<byte[]> returnErrorXML(String message) {
		StringBuilder builder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		builder.append("<response status=\"error\">");
		builder.append("<message>" + StringEscapeUtils.escapeXml(message)
				+ "</message>");
		builder.append("</response>");
		return returnXML(builder.toString());
	}
	
	public static HttpEntity<byte[]> returnErrorXML(List<String> messages) {
		StringBuilder builder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		builder.append("<response status=\"error\">");
		for (String message : messages) {
			builder.append("<message>" + StringEscapeUtils.escapeXml(message)
					+ "</message>");
		}
		builder.append("</response>");
		return returnXML(builder.toString());
	}
	
	/**
	 * creates {@link HttpEntity} for given success message
	 * @param message
	 * @return
	 */
	public static HttpEntity<byte[]> returnSuccessXML(String message) {
		StringBuilder builder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		builder.append("<response status=\"success\">");
		builder.append("<message>" + StringEscapeUtils.escapeXml(message)
				+ "</message>");
		builder.append("</response>");
		return returnXML(builder.toString());
	}

	
	/**
	 * formats output XML document
	 * @param input
	 * @return
	 */
	public static String formatStringAsXML(String input) {
	    try {
	        Source xmlInput = new StreamSource(new StringReader(input));
	        StringWriter stringWriter = new StringWriter();
	        StreamResult xmlOutput = new StreamResult(stringWriter);
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        transformerFactory.setAttribute("indent-number", 4);
	        Transformer transformer = transformerFactory.newTransformer(); 
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.transform(xmlInput, xmlOutput);
	        return xmlOutput.getWriter().toString();
	    } catch (TransformerException e) {
	        // TODO log better
	    	e.printStackTrace();;
	    	return input;
	    }
	}
}
