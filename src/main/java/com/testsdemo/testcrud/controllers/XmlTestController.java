package com.testsdemo.testcrud.controllers;

import ch.qos.logback.core.joran.spi.XMLUtil;
import com.testsdemo.testcrud.dto.JwtBody;
import com.testsdemo.testcrud.dto.ResponseDto;
import com.testsdemo.testcrud.services.SkillService;
import com.testsdemo.testcrud.services.UsersService;
import com.testsdemo.testcrud.util.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URI;
import java.text.DecimalFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.List;

@RestController
@RequestMapping(path = "/test", produces = "application/json")
public class XmlTestController {
    @Autowired
    private UsersService userService;
    @Autowired
    private SkillService skillService;

    private final String EXAMPLE_URL = "http://localhost:8080/api/users";


    @GetMapping("/kub")
    public ResponseDto index(){
        try{
            HashMap<String, String> data = new HashMap<String,String>();
            data.put("name","test");
            return new ResponseDto(true,"ok",data);
        }catch(Exception ex){
            System.out.println("ERROR");
            ex.printStackTrace();
            return null;
        }
    }

    @GetMapping("/query/{table}")
    public ResponseDto fuckYouBitch(@PathVariable String table){
        try{
            System.out.println("table -->"+table);
            Object result = new Object();

            switch(table.toLowerCase()){
                case "users" :
                    result = (Object) userService.getAll();
                    break;
                case "skill":
                    result = (Object) skillService.getAll();
                    break;
                default:
                    result = (Object) new ArrayList<HashMap<String,String>>();
                    break;
            }
            return new ResponseDto(true,"ok",result);
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @GetMapping("read-xml")
    public ResponseDto readXmlExample(){
        String rawXml =  "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\n" +
                "   <soap:Body>\n" +
                "      <CurrentOilPriceResponse xmlns=\"http://www.pttor.com\">\n" +
                "         <CurrentOilPriceResult><![CDATA[<PTTOR_DS>\n" +
                "            <FUEL>\n" +
                "                <PRICE_DATE>5/25/2024 5:00:00 AM</PRICE_DATE>\n" +
                "                <PRODUCT>Premium Diesel</PRODUCT>\n" +
                "                <PRICE>44.440</PRICE>\n" +
                "            </FUEL>\n" +
                "            <FUEL>\n" +
                "                <PRICE_DATE>5/25/2024 5:00:00 AM</PRICE_DATE>\n" +
                "                <PRODUCT>Gasoline 95</PRODUCT>\n" +
                "                <PRICE>46.040</PRICE>\n" +
                "            </FUEL>\n" +
                "            <FUEL>\n" +
                "                <PRICE_DATE>5/25/2024 5:00:00 AM</PRICE_DATE>\n" +
                "                <PRODUCT>Gasohol 95</PRODUCT>\n" +
                "                <PRICE>38.150</PRICE>\n" +
                "            </FUEL>\n" +
                "            <FUEL>\n" +
                "                <PRICE_DATE>5/25/2024 5:00:00 AM</PRICE_DATE>\n" +
                "                <PRODUCT>Gasohol 91</PRODUCT>\n" +
                "                <PRICE>37.780</PRICE>\n" +
                "            </FUEL>\n" +
                "            <FUEL>\n" +
                "                <PRICE_DATE>5/25/2024 5:00:00 AM</PRICE_DATE>\n" +
                "                <PRODUCT>Gasohol E20</PRODUCT>\n" +
                "                <PRICE>36.040</PRICE>\n" +
                "            </FUEL>\n" +
                "            <FUEL>\n" +
                "                <PRICE_DATE>5/25/2024 5:00:00 AM</PRICE_DATE>\n" +
                "                <PRODUCT>Gasohol E85</PRODUCT>\n" +
                "                <PRICE>35.790</PRICE>\n" +
                "            </FUEL>\n" +
                "            <FUEL>\n" +
                "                <PRICE_DATE>5/25/2024 5:00:00 AM</PRICE_DATE>\n" +
                "                <PRODUCT>Diesel</PRODUCT>\n" +
                "                <PRICE>32.440</PRICE>\n" +
                "            </FUEL>\n" +
                "            <FUEL>\n" +
                "                <PRICE_DATE>5/25/2024 5:00:00 AM</PRICE_DATE>\n" +
                "                <PRODUCT>Super Power GSH95</PRODUCT>\n" +
                "                <PRICE>46.740</PRICE>\n" +
                "            </FUEL>\n" +
                "        </PTTOR_DS>]]></CurrentOilPriceResult>\n" +
                "      </CurrentOilPriceResponse>\n" +
                "   </soap:Body>\n" +
        "</soap:Envelope>";

        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance() ;
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputStream is = new ByteArrayInputStream(rawXml.getBytes());
            Document doc = builder.parse(is);

            NodeList currentOilPriceResult = doc.getElementsByTagName("CurrentOilPriceResult");
            Node innerCurrentOilPriceNode = currentOilPriceResult.item(0).getFirstChild();
            String innerCurrentOilPriceRawString =((CharacterData) innerCurrentOilPriceNode).getData();

            print("currentOilPriceResult LENGTH : "+currentOilPriceResult.getLength());
            print("===========>"+XmlCustom.nodeToString(innerCurrentOilPriceNode)+"<============");

            Document innerCurrentOilPriceResultDocument = builder.parse(new ByteArrayInputStream(innerCurrentOilPriceRawString.getBytes()));
            NodeList fuels = innerCurrentOilPriceResultDocument.getElementsByTagName("FUEL");
            ArrayList<HashMap<String,String>> resultJson = new ArrayList<HashMap<String,String>>();

            for(int index = 0 ;index < fuels.getLength();index++){
                Element fuel = (Element) fuels.item(index);
                HashMap<String,String> innerJson = new HashMap<String,String>();

                innerJson.put("price_date",fuel.getElementsByTagName("PRICE_DATE").item(0).getTextContent());
                innerJson.put("product",fuel.getElementsByTagName("PRODUCT").item(0).getTextContent());
                innerJson.put("price",fuel.getElementsByTagName("PRICE").item(0).getTextContent());

                resultJson.add(innerJson);
            }
            return new ResponseDto(true,"ok",resultJson);
        }catch(Exception ex){
            System.out.println("ERROR");
            ex.printStackTrace();
            return null;
        }
    }

    @GetMapping("/date")
    public ResponseDto testDate() {
        try {
            String result =  DateTimeUtil.toThaiFullDateString(new Date());
            return new ResponseDto(true, "ok",result);
        } catch (Exception ex) {
            System.out.println("ERROR");
            ex.printStackTrace();
            return null;
        }
    }


    @GetMapping("/currency")
    public ResponseDto testCurrency() {
        try {
           // String result =  CurrencyUtil.
            HashMap<String,String> result = new HashMap<String,String>();

            BigDecimal value = new BigDecimal(99999999.23423);

            result.put("comma", CurrencyUtil.toNumberWithThousand(value));
            result.put("toThaiBaht",CurrencyUtil.toThaiBaht(value));

            return new ResponseDto(true, "ok",result);
        } catch (Exception ex) {
            System.out.println("ERROR");
            ex.printStackTrace();
            return null;
        }
    }


    @GetMapping("/common")
    public ResponseDto testCommonUtil() {
        try {
            List<String> testList = new ArrayList<String>();
            // String result =  CurrencyUtil.
            HashMap<String,String> result = new HashMap<String,String>();
            result.put("toFullName", CommonUtil.toFullName("THEWIN","THAMMA"));
            result.put("generateUUIDWithNoHyphen",CommonUtil.generateUUIDWithNoHyphen());
            result.put("validateEmailFormatValid",(CommonUtil.validateEmailFormat("test@ok.com")? "true" : "false") );
            result.put("validateEmailFormatInvalid",(CommonUtil.validateEmailFormat("tes@com")? "true" : "false") );

            return new ResponseDto(true, "ok",result);
        } catch (Exception ex) {
            System.out.println("ERROR");
            ex.printStackTrace();
            return null;
        }
    }

    @GetMapping("/enum")
    public ResponseDto testEnum() {
        try {
            HashMap<String,String> result = new HashMap<String,String>();
            EnumTest.init();

            return new ResponseDto(true, "ok",result);
        } catch (Exception ex) {
            System.out.println("ERROR");
            ex.printStackTrace();
            return null;
        }
    }
    @GetMapping("/req")
    public ResponseDto TestReq() {
        try {
            SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
            factory.setConnectTimeout(0);
            factory.setReadTimeout(0);

            RestTemplate restTemplate = new RestTemplate(factory);
            String result = restTemplate.getForObject(EXAMPLE_URL, String.class);

            return new ResponseDto(true, "ok", result);
        } catch (Exception ex) {
            System.out.println("ERROR");
            ex.printStackTrace();
            return null;
        }
    }

    @GetMapping("/req-builder")
    public ResponseDto TestReqWithBuilder() {
        try {
            String response = new RestApi().exchange(URI.create(EXAMPLE_URL),HttpMethod.GET,null,String.class);
            return new ResponseDto(true, "ok",response);
        } catch (Exception ex) {
            System.out.println("ERROR");
            ex.printStackTrace();
            return null;
        }
    }

    @PostMapping("/jwt-decode")
    @ResponseBody
    public ResponseDto jwtDecode(@RequestBody JwtBody jwtBody){
        try{
            JwtUtil ju = new JwtUtil("src/main/resources/rsa.public");
            String tokenFromSender = jwtBody.getToken();

            System.out.println(jwtBody.token);

            Claims result = ju.deCode(tokenFromSender);

            return new ResponseDto(true,"ok",result);
        }catch(Exception ex){
            System.out.println("ERROR");
            ex.printStackTrace();

            System.out.println("==============>"+ex.getClass().getName());
            String message = "etract token error! ";
            switch(ex.getClass().getName()){
                case "io.jsonwebtoken.SignatureException":
                    message += " Signature Exception";
                    break;
                case "io.jsonwebtoken.ExpiredJwtException" :
                    message += " Token Expired";
                    break;
                case "io.jsonwebtoken.MalformedJwtException":
                    message += " Token Incorrect format ";
                    break;
                default:
                    break;
            }
            return new ResponseDto(false,message);
        }
    }

    private void print(String result){
        System.out.println(result);
    }

}


