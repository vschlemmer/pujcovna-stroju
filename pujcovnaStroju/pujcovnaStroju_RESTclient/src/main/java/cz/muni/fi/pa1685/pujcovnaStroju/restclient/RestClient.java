package cz.muni.fi.pa1685.pujcovnaStroju.restclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.xml.sax.SAXException;

import cz.muni.fi.pa165.pujcovnastroju.dto.MachineDTO;
import cz.muni.fi.pa1685.pujcovnaStroju.restclient.util.MessageResolver;

public class RestClient {

	private static final String BASIC_URL = "http://localhost:8080/pa165/rest/";

	private static final String COMMAND_MACHINE = "machine";

	private static final String COMMAND_TYPES = "types";

	private static final String COMMAND_HELP = "help";
	private static final String COMMAND_EXIT = "exit";
	private static final String COMMAND_LIST = "list";
	private static final String COMMAND_ADD = "add";
	private static final String COMMAND_DETAIL = "detail";
	private static final String COMMAND_UPDATE = "update";
	private static final String COMMAND_DELETE = "delete";

	private static String HELP_CONTENT = null;

	private static Options machineAddOptions = new Options();
	private static Options machineUpdateOptions = new Options();
	private static Options machineDetailOptions = new Options();
	private static Options machineDeleteOptions = new Options();

	/*
	 * each command has three parts 1) machine || user || help 2) action (list,
	 * add, update, delete...) 3) arguments of action represented as Option
	 * objects
	 * 
	 * example: machine list machine add --label karel --description karel2
	 * --type buldozer machine add -l karel -d karel2 -t buldozer
	 */

	public static void main(String[] args) {
		machineAddOptions.addOption("l", "label", true, "Label of machine")
				.addOption("d", "description", true, "Description of Machine")
				.addOption("t", "type", true, "Type of Machine");
		machineUpdateOptions.addOption("i", "id", true, "ID of machine")
				.addOption("l", "label", true, "Label of machine")
				.addOption("d", "description", true, "Description of Machine")
				.addOption("t", "type", true, "Type of Machine");
		machineDetailOptions.addOption("i", "id", true, "ID of machine");
		machineDeleteOptions.addOption("i", "id", true, "ID of machine");

		CommandLineParser parser = new BasicParser();
		CommandLine cmd;
		String fixedArgs[] = null;
		boolean exit = false;
		String url = null;
		Scanner scanner = new Scanner(System.in);
		System.out.println("SUper turbo mega hyper ubercool feature:\n");
		do {

			String arg[] = scanner.nextLine().split(" ");
			try {
				switch (arg[0]) {
				case COMMAND_HELP:
					System.out.println(renderHelp());
					continue;
				case COMMAND_EXIT:
					exit = true;
					continue;
				case COMMAND_TYPES:
					url = BASIC_URL + "types";
					break;
				case COMMAND_MACHINE:
					switch (arg[1]) {

					case COMMAND_LIST:
						url = BASIC_URL + COMMAND_MACHINE + "/" + COMMAND_LIST;
						break;
					case COMMAND_DETAIL:
						fixedArgs = Arrays.copyOfRange(arg, 2, arg.length);
						cmd = parser.parse(machineDetailOptions, fixedArgs);

						if (cmd.getOptionValue("i") != null) {
							url = BASIC_URL + COMMAND_MACHINE + "/"
									+ COMMAND_DETAIL + "?id="
									+ cmd.getOptionValue('i');
						} else {
							printParseError();
							continue;
						}
						break;

					case COMMAND_DELETE:
						fixedArgs = Arrays.copyOfRange(arg, 2, arg.length);
						cmd = parser.parse(machineDeleteOptions, fixedArgs);

						if (cmd.getOptionValue("i") != null) {
							url = BASIC_URL + COMMAND_MACHINE + "/"
									+ COMMAND_DELETE + "?id="
									+ cmd.getOptionValue('i');
						} else {
							printParseError();
							continue;
						}
						break;
					case COMMAND_ADD:
						fixedArgs = Arrays.copyOfRange(arg, 2, arg.length);
						cmd = parser.parse(machineAddOptions, fixedArgs);

						if (cmd.getOptionValue("l") != null
								&& cmd.getOptionValue("d") != null
								&& cmd.getOptionValue("t") != null) {
							url = BASIC_URL + COMMAND_MACHINE + "/"
									+ COMMAND_ADD + "" + "?label="
									+ cmd.getOptionValue("l") + "&description="
									+ cmd.getOptionValue("d") + "&type="
									+ cmd.getOptionValue("t");
						} else {
							printParseError();
							continue;
						}
						break;
					case COMMAND_UPDATE:
						fixedArgs = Arrays.copyOfRange(arg, 2, arg.length);
						cmd = parser.parse(machineUpdateOptions, fixedArgs);

						if (cmd.getOptionValue("i") != null) {
							StringBuilder builder = new StringBuilder(BASIC_URL
									+ COMMAND_MACHINE);
							builder.append("/" + COMMAND_ADD + "/?");
							if (cmd.getOptionValue("l") != null) {
								builder.append("label="
										+ cmd.getOptionValue("l") + "&");
							}
							if (cmd.getOptionValue("d") != null) {
								builder.append("description="
										+ cmd.getOptionValue("d") + "&");
							}
							if (cmd.getOptionValue("t") != null) {
								builder.append("type="
										+ cmd.getOptionValue("t"));
							}
							url = builder.toString();
						} else {
							printParseError();
							continue;
						}
						break;
					}
					break;
				default:
					printParseError();
					continue;
				}

				try {
					String responseString = sendRequest(url);

					MessageResolver resolver = new MessageResolver(
							responseString);
					System.out.println(handleResponse(resolver.getResponse()));
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					System.out.println(url);
					e.printStackTrace();
				} catch (ConnectException e) {
					printConnectionError();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}

				// at this point should be URL handled somehow

			} catch (ParseException e) {
				printParseError();
				continue;
			}
		} while (!exit);
		scanner.close();
	}

	/**
	 * renders help content and returns as String
	 * 
	 * @return
	 */
	private static String renderHelp() {
		if (HELP_CONTENT == null) {
			StringBuilder builder = new StringBuilder();
			builder.append(String.format("%s\t\t%s\n", COMMAND_HELP,
					"print this help"));
			builder.append(String.format("%s\t\t%s\n", COMMAND_EXIT,
					"exit application"));
			builder.append(String.format("%s\t\t%s\n", COMMAND_TYPES,
					"display supported types of users and machines"));
			builder.append(String.format("%s %s\t%s", COMMAND_MACHINE,
					COMMAND_LIST, "show list of machines\n"));
			builder.append(getOptionHelp(COMMAND_MACHINE + " " + COMMAND_ADD,
					machineAddOptions));
			builder.append(getOptionHelp(
					COMMAND_MACHINE + " " + COMMAND_DETAIL,
					machineDetailOptions));
			builder.append(getOptionHelp(
					COMMAND_MACHINE + " " + COMMAND_DELETE,
					machineDeleteOptions));
			builder.append(getOptionHelp(
					COMMAND_MACHINE + " " + COMMAND_UPDATE,
					machineUpdateOptions));
			HELP_CONTENT = builder.toString();
		}
		return HELP_CONTENT;

	}

	private static String getOptionHelp(String begin, Options options) {
		StringBuilder builder = new StringBuilder();
		Object[] optionArray = options.getOptions().toArray();
		Option current;
		builder.append(begin);
		builder.append("\n");
		for (int i = 0; i < optionArray.length; i++) {
			current = (Option) optionArray[i];
			builder.append(String.format("\t\t-%s  --%s\t%s\n",
					current.getOpt(), current.getLongOpt(),
					current.getDescription()));
		}
		return builder.toString();
	}

	/**
	 * sends request represented by given url
	 * 
	 * @param url
	 * @return String - response
	 * @throws ConnectException
	 *             - if connection fails
	 * @throws MalformedURLException
	 */
	private static String sendRequest(String url) throws ConnectException,
			MalformedURLException {
		BufferedReader reader = null;
		StringBuffer response = null;
		try {
			URL restURL = new URL(url);

			reader = new BufferedReader(new InputStreamReader(
					restURL.openStream(), "UTF-8"));
			response = new StringBuffer();
			for (String line; (line = reader.readLine()) != null;) {
				response.append(line);
			}
		}

		catch (IOException e) {
			printConnectionError();
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (IOException ignore) {
				}
		}
		return response.toString();
	}
	
	/**
	 * Transforms response object into readable form
	 * @param response
	 * @return
	 */
	private static String handleResponse(List<? extends Object> response) {
		if (response == null || response.isEmpty()) {
			return "Error occured during processing of response";
		}
		Object sample = response.get(0);
		StringBuilder builder = new StringBuilder();
		if (sample instanceof MachineDTO) {
			builder.append("---machines---");
			for (Object machine:  response.toArray()) {
				if (machine instanceof MachineDTO) {
					builder.append(formatMachine((MachineDTO) machine));
				}
			}
			return builder.toString();
		}
		if (sample instanceof String) {
			for (Object message: response.toArray()) {
				if (message instanceof String) {
					builder.append(formatMessage((String) message));
				}
			}
			return builder.toString();
		}
		if (sample instanceof List<?>) {
			builder.append("---machine types---\n");
			List<?> machineTypes = (List<?>) response.get(0);
			for (Object mType: machineTypes.toArray()) {
				if (mType instanceof String) {
					builder.append(mType);
				}
			}
			
			builder.append("---user types---\n");
			List<?> userTypes = (List<?>) response.get(1);
			for (Object uType: userTypes.toArray()) {
				if (uType instanceof String) {
					builder.append(uType);
				}
			}
			return builder.toString();
		}
		return "";
	}

	private static void printParseError() {
		System.out
				.println("Invalid command, help can by displayed by typing 'help'");
	}

	private static void printConnectionError() {
		System.out.println("Connection to server failed.");
	}
	
	/**
	 * returns printable String of {@link MachineDTO}
	 * @param messages
	 * @return
	 */
	private static String formatMachine(MachineDTO machine) {
		return machine.toString() + "\n";
	}
	
	/**
	 * returns printable String of message
	 * @param messages
	 * @return
	 */
	private static String formatMessage(String message) {
		return message + "\n";
	}
	


}
