package DesignPatterns.StructuralPatterns;

// Target interface
interface JsonDataConverter {
    void convertData(String dataFormat, String data);
}

// Adaptee interface
interface DataConverter {
    void convertData(String data);
}

class XmlToJsonConverter implements DataConverter {

    @Override
    public void convertData(String data) {
        System.out.println("Converting XML data format to JSON format");
    }
}

class ExcelToJsonConverter implements DataConverter {

    @Override
    public void convertData(String data) {
        System.out.println("Converting Excel data format to JSON format");
    }
}

// Adapter
class ClientDataConverter implements JsonDataConverter {

    private DataConverter dataConverter;


    @Override
    public void convertData(String dataFormat, String data) {
        if (dataFormat.equalsIgnoreCase("xml")) {
            dataConverter = new XmlToJsonConverter();
        }else if(dataFormat.equalsIgnoreCase("excel")){
            dataConverter= new ExcelToJsonConverter();
        }

        dataConverter.convertData(data);
    }
}
// adapter is helpful when we have to use an incompatible interface for my usecase
// where we put up an adapter in between to make use of it
public class AdapterPattern {
    public static void main(String[] args) {
       ClientDataConverter clientDataConverter=new ClientDataConverter();
       clientDataConverter.convertData("xml","xml-data");
       clientDataConverter.convertData("excel","excel-data");
    }
}
