import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
//first version of xml <-> json mapper, using jackson library
public class XmlToJson {
    public static void main(String[] args) throws IOException {

        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "\n" +
                "<electronic_shop>\n" +
                "  <smartphones_and_gadgets>\n" +
                "    <product category=\"smartphones\">\n" +
                "      <vendor>TOPZASVOIDENGI</vendor>\n" +
                "      <model>MI-124</model>\n" +
                "      <manufacturing_date>13-07-2020</manufacturing_date>\n" +
                "      <colour>Pink</colour>\n" +
                "      <price stock_balance=\"ended\">13999</price>\n" +
                "    </product>\n" +
                "    <product category=\"powerbanks\">\n" +
                "      <vendor>Bura</vendor>\n" +
                "      <model>BR-567</model>\n" +
                "      <manufacturing_date>01-02-2019</manufacturing_date>\n" +
                "      <colour>Red</colour>\n" +
                "      <price stock_balance=\"333\">390</price>\n" +
                "    </product>\n" +
                "  </smartphones_and_gadgets>\n" +
                "  <home_electronics>\n" +
                "    <product category=\"blenders\">\n" +
                "      <vendor>Sinba</vendor>\n" +
                "      <model>SH-119</model>\n" +
                "      <manufacturing_date>01-01-2019</manufacturing_date>\n" +
                "      <colour>Silver</colour>\n" +
                "      <price stock_balance=\"8\">999</price>\n" +
                "    </product>\n" +
                "    <product category=\"hoovers\">\n" +
                "      <vendor>Starwing</vendor>\n" +
                "      <model>SW-789</model>\n" +
                "      <manufacturing_date>22-12-2018</manufacturing_date>\n" +
                "      <colour>Blue</colour>\n" +
                "      <price stock_balance=\"3\">2750</price>\n" +
                "    </product>\n" +
                "  </home_electronics>\n" +
                "  <computers>\n" +
                "    <product category=\"motherboards\">\n" +
                "      <vendor>AUSS</vendor>\n" +
                "      <model>BA-121</model>\n" +
                "      <manufacturing_date>28-12-2019</manufacturing_date>\n" +
                "      <colour>Black</colour>\n" +
                "      <price stock_balance=\"3\">3960</price>\n" +
                "    </product>\n" +
                "    <product category=\"cpu\">\n" +
                "      <vendor>AD</vendor>\n" +
                "      <model>AD-666</model>\n" +
                "      <manufacturing_date>05-07-2020</manufacturing_date>\n" +
                "      <colour>Silver</colour>\n" +
                "      <price stock_balance=\"1\">6666</price>\n" +
                "    </product>\n" +
                "    <product category=\"hdd\">\n" +
                "      <vendor>TOSHKA</vendor>\n" +
                "      <model>TT-353</model>\n" +
                "      <manufacturing_date>03-03-2019</manufacturing_date>\n" +
                "      <colour>Silver</colour>\n" +
                "      <price stock_balance=\"20\">4700</price>\n" +
                "    </product>\n" +
                "  </computers>\n" +
                "</electronic_shop>";

        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new SimpleModule().addDeserializer(
                JsonNode.class,
                new DuplicateToArrayJsonNodeDeserializer()));
        JsonNode node = xmlMapper.readTree(xml.getBytes());

        ObjectMapper jsonMapper = new ObjectMapper();
        String json = jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);

        System.out.println("Json from XML: " + json);
    }
}
