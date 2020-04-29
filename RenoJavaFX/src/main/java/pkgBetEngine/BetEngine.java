package pkgBetEngine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

public class BetEngine {

	private ArrayList<BetRound> BetRound = new ArrayList<BetRound>();

	@JacksonXmlElementWrapper(useWrapping = false)
	public void addBetRound(BetRound br) {
		BetRound.add(br);
	}

	@JacksonXmlElementWrapper(useWrapping = false)
	public ArrayList<BetRound> getBetRound() {
		return BetRound;
	}

	public static void main(String args[]) {
		BetEngine BE = null;
		try {
			BE = LoadBettingEngine("BettingEngine.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			SaveBettingEngine(BE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static BetEngine LoadBettingEngine(String BEFileName) throws IOException {
		BetEngine BE = new BetEngine();
		try {			
			File resourcesDirectory = new File("src/main/resources");
			String basePath = new String(Files
					.readAllBytes(Paths.get(resourcesDirectory + "/PlayerStrategy/" + BEFileName)));
//			String basePath = new String(Files
//					.readAllBytes(Paths.get(BetEngine.class.getResource("/PlayerStrategy/BettingEngine.xml").toURI())));

			XmlMapper xmlMapper = new XmlMapper();
			// deserialize from the XML into a Phone object
			BE = xmlMapper.readValue(basePath, BetEngine.class);

			System.out.println(BE.getBetRound().get(0).getBetRoundNumber());
		} catch (IOException e) {
			e.printStackTrace(); 
			throw e;
		}		
		return BE;
	}

	public static void SaveBettingEngine(BetEngine BE) throws IOException {
		URL dirURL = BetEngine.class.getClassLoader().getResource("PlayerStrategy/");
		// Path resourceDirectory = Paths.get("src","main","resources");
		File resourcesDirectory = new File("src/main/resources");

		File xmlOutput = new File(resourcesDirectory.getAbsolutePath() + "/PlayerStrategy/xserialized.xml");
		XmlMapper xmlMapper = new XmlMapper();
		xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
		xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
		
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(System.out, BE);
		 
        String jsonString = mapper.writeValueAsString(BE);
 
        mapper.writeValue(new File("customer.json"), BE);
        String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(BE);
		
		String xmlString = null;
		try {
			xmlString = xmlMapper.writeValueAsString(BE);
			FileWriter fileWriter = new FileWriter(xmlOutput);
			fileWriter.write(xmlString);
			fileWriter.close();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {			
			e.printStackTrace();
			throw e;
		}
	}
}
