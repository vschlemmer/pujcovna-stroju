package cz.muni.fi.pa165.pujcovnaStroju.restclient.test;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import junit.framework.TestCase;

import org.junit.Test;
import org.xml.sax.SAXException;

import cz.muni.fi.pa165.pujcovnastroju.dto.MachineDTO;
import cz.muni.fi.pa165.pujcovnastroju.dto.MachineTypeEnumDTO;
import cz.muni.fi.pa165.pujcovnastroju.dto.SystemUserDTO;
import cz.muni.fi.pa165.pujcovnastroju.dto.UserTypeEnumDTO;
import cz.muni.fi.pa1685.pujcovnaStroju.restclient.util.MessageResolver;

public class MessageResolverTest extends TestCase {

	@Test
	public void testWrongMessage1() {
		String message = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><response xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"http://localhost:8080/pa165/xmlt/schema.xsd\" status=\"success\"><machines2 numFound=\"0\"/></response>";
		try {
			MessageResolver resolver = new MessageResolver(message);

		} catch (SAXException e) {
			return;
		} catch (IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}
		fail();
	}

	@Test
	public void testWrongMessage2() {
		String message = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<response xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
				+ " xsi:noNamespaceSchemaLocation=\"http://localhost:8080/pa165/xmlt/schema.xsd\" status=\"success\">"
				+ "<machines numFound=\"1\">" + "<machine2>" + "<id>1</id>"
				+ "<label>karel</label>" + "<description>aaa</description>"
				+ "<type>BULDOZER</type>" + "</machine2>" + "</machines>"
				+ "</response>";

		try {
			MessageResolver resolver = new MessageResolver(message);
		} catch (SAXException e) {
			return;
		} catch (IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}
		fail();
	}

	@Test
	public void testMachineMarshalling1() {
		String message = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<response xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
				+ " xsi:noNamespaceSchemaLocation=\"http://localhost:8080/pa165/xmlt/schema.xsd\" status=\"success\">"
				+ "<machines numFound=\"1\">" + "<machine>" + "<id>1</id>"
				+ "<label>karel</label>" + "<description>aaa</description>"
				+ "<type>BULDOZER</type>" + "</machine>" + "</machines>"
				+ "</response>";

		try {
			MessageResolver resolver = new MessageResolver(message);
			MachineDTO machine = new MachineDTO();
			machine.setId(1L);
			machine.setLabel("karel");
			machine.setDescription("aaa");
			MachineTypeEnumDTO type = new MachineTypeEnumDTO();
			type.setTypeLabel("BULDOZER");
			machine.setType(type);

			List<? extends Object> response = resolver.getResponse();
			assertEquals(1, response.size());
			Object recivedMachine = response.get(0);

			if (recivedMachine instanceof MachineDTO) {
				assertEquals(machine, (MachineDTO) response.get(0));
			} else {
				fail();
			}

		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testMachineMarshalling2() {
		String message = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<response xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
				+ " xsi:noNamespaceSchemaLocation=\"http://localhost:8080/pa165/xmlt/schema.xsd\" status=\"success\">"
				+ "<machine>" + "<id>1</id>" + "<label>karel</label>"
				+ "<description>aaa</description>" + "<type>BULDOZER</type>"
				+ "</machine>" + "</response>";

		try {
			MessageResolver resolver = new MessageResolver(message);
			MachineDTO machine = new MachineDTO();
			machine.setId(1L);
			machine.setLabel("karel");
			machine.setDescription("aaa");
			MachineTypeEnumDTO type = new MachineTypeEnumDTO();
			type.setTypeLabel("BULDOZER");
			machine.setType(type);

			List<? extends Object> response = resolver.getResponse();
			assertEquals(1, response.size());
			Object recivedMachine = response.get(0);

			if (recivedMachine instanceof MachineDTO) {
				assertEquals(machine, (MachineDTO) response.get(0));
			} else {
				fail();
			}

		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testMessageMarchaling1() {
		String mes1 = "aaa";
		String mes2 = "bbb";
		String message = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><response xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"http://localhost:8080/pa165/xmlt/schema.xsd\" status=\"success\">"
				+ "<message>"
				+ mes1
				+ "</message><message>"
				+ mes2
				+ "</message>" + "</response>";

		try {
			MessageResolver resolver = new MessageResolver(message);
			List<? extends Object> response = resolver.getResponse();

			assertEquals(response.size(), 2);
			assertEquals(response.get(0), (String) mes1);
			assertEquals(response.get(1), (String) mes2);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testMessageMarchaling2() {
		String mes1 = "aaa";
		String mes2 = "bbb";
		String message = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><response xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"http://localhost:8080/pa165/xmlt/schema.xsd\" status=\"success\">"
				+ "<message>"
				+ mes1
				+ "</message><message>"
				+ mes2
				+ "</message>" + "</response>";

		try {
			MessageResolver resolver = new MessageResolver(message);
			List<? extends Object> response = resolver.getResponse();

			assertEquals(response.size(), 2);
			assertEquals(response.get(0), (String) mes1);
			assertEquals(response.get(1), (String) mes2);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testParseTypes() {
		String userType1 = "aaa";
		String userType2 = "bbb";
		String machineType1 = "eee";
		String machineType2 = "rrr";
		String message = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><response xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"http://localhost:8080/pa165/xmlt/schema.xsd\" status=\"success\">"
				+ "<availableTypes>"
				+ "<machineTypes>"
				+ "<type>"+machineType1+"</type>"
				+ "<type>"+machineType2+"</type>"
				+ "</machineTypes>"
				+ "<userTypes>"
				+ "<type>"+userType1+"</type>"
				+ "<type>"+userType2+"</type>"
				+ "</userTypes>" + "</availableTypes>" + "</response>";
		
		try {
			MessageResolver resolver = new MessageResolver(message);
			List<? extends Object> response = resolver.getResponse();

			assertEquals(response.size(), 2);
			
			
			List<String> machineTypes = (List<String>) response.get(0);
			assertEquals(machineTypes.get(0), (String) machineType1);
			assertEquals(machineTypes.get(1), (String) machineType2);
			
			
			List<String> userTypes = (List<String>) response.get(1);
			assertEquals(userTypes.get(0), (String) userType1);
			assertEquals(userTypes.get(1), (String) userType2);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testUserMarshaling() {
		String userFirstName = "karel";
		String userSurname = "karel2";
		String typeName = "revisioner";
		String userName = "karel";
		
		String message = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><response xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"http://localhost:8080/pa165/xmlt/schema.xsd\" status=\"success\">"
				+ "<users numFound=\"1\">"
				+ "<user>"
				+ "<id>1</id>"
				+ "<firstName>"+userFirstName+"</firstName>"
				+ "<lastName>"+userSurname+"</lastName>"
				+ "<type>"+typeName+"</type>"
				+ "<userName>"+userName+"</userName>"
				+ "</user></users></response>";

		try {
			MessageResolver resolver = new MessageResolver(message);
			SystemUserDTO user = new SystemUserDTO();
			user.setId(1L);
			user.setFirstName(userFirstName);
			user.setLastName(userSurname);
			user.setUsername(userName);
			UserTypeEnumDTO type = new UserTypeEnumDTO();
			type.setTypeLabel(typeName);
			user.setType(type);

			List<? extends Object> response = resolver.getResponse();
			assertEquals(1, response.size());
			Object recievedUser = response.get(0);

			if (recievedUser instanceof SystemUserDTO) {
				assertEquals(user, (SystemUserDTO) response.get(0));
			} else {
				fail();
			}

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
