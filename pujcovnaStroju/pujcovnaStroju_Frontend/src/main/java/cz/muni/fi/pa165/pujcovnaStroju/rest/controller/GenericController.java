package cz.muni.fi.pa165.pujcovnaStroju.rest.controller;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * generic REST controller
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
		StringBuilder builder = new StringBuilder();
		builder.append("<response>");
		builder.append("<text>Schema is hidden at the end of the rainbow stealing "
				+ "leprechaun's pot of gold. It's quite sad.</text>");
		builder.append("</response>");
		return returnXML(builder.toString());
	}
	
	/**
	 * creates HttpEntity for given xml string
	 * @param xml
	 * @return
	 */
	public static HttpEntity<byte[]> returnXML(String xml) {
		byte content[] = xml.getBytes();
		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType("application", "xml"));
		header.setContentLength(content.length);
		return new HttpEntity<byte[]>(content, header);
	}
	
	/**
	 * creates HttpEntity for given error message
	 * @param message
	 * @return
	 */
	public static HttpEntity<byte[]> returnErrorXML(String message) {
		StringBuilder builder = new StringBuilder();
		builder.append("<response>");
		builder.append("<error>"+ StringEscapeUtils.escapeXml(message) +"</error>");
		builder.append("</response>");
		return returnXML(builder.toString());
	} 
}
