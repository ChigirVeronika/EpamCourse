package client.by.epam.fullparser.client.start;

import client.by.epam.fullparser.client.view.View;
import org.apache.log4j.PropertyConfigurator;

/**
 * Start point of client.
 */
public class Start {
	private static final String LOGGER_CONFIG = "E:\\EpamCourse\\FullParser\\out\\production\\FullParser\\log4j.properties";

	public static void main(String[] args) {
		PropertyConfigurator.configure(LOGGER_CONFIG);
		new View().start();
	}

}
