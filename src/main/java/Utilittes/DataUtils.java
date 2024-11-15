package Utilittes;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DataUtils {

    private static final String Test_Data_Path = "src/test/resources/TestData/";

    //Todo: Reading data form jason file
    public static String getJasonData(String fileName, String field) throws FileNotFoundException {
        try {
            //Define Object Of file reader
            FileReader reader = new FileReader(Test_Data_Path + fileName + ".json");
            // parse the jason directly into a jasonElement
            JsonElement jsonElement = JsonParser.parseReader(reader);
            return jsonElement.getAsJsonObject().get(field).getAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }

    //Todo: reading data from properties file
    public static String getPropertyValue(String fileName, String Key) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(Test_Data_Path + fileName));
        return properties.getProperty(Key);
        
    }
}
