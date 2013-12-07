package cz.muni.fi.pa165.pujcovnaStroju.rest.controller;

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

		byte documentBody[] = builder.toString().getBytes();
		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType("application", "xml"));
		header.setContentLength(documentBody.length);
		return new HttpEntity<byte[]>(documentBody, header);
	}
}
