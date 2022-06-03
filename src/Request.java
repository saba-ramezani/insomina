import org.apache.hc.client5.http.classic.methods.*;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.io.entity.StringEntity;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;

public class Request implements Serializable {

    private double responseTime = 0 ;
    private double responseSize = 0 ;
    private String result ;
    private String code ;
    private String reasonPhrase ;
    private String binaryFilePath = "" ;
    private String json = "" ;
    private String name;
    private String address;
    private String urlWithQueries = "" ;
    private String method;
    private HashMap<String , String> headers;
    private HashMap<String  ,String> queries;
    private HashMap<String , String> formData;
    //private HashMap<String , String> json ;
    private ClassicHttpRequest request ;
    private CloseableHttpClient httpClient ;
    private CloseableHttpResponse response ;
    private int typeOfMessageBody ; //3 for no messageBody , 0 for form data , 1 for json , 2 for binary
    private HashMap<String , String> responseHeaders ;

    public Request(String method,String name) {
        this.name = name ;
        urlWithQueries = "" ;
        queries = new HashMap<>() ;
        address = "";
        this.method = method;
        headers = new HashMap<>();
        formData = new HashMap<>();
        json = "" ;
        typeOfMessageBody = 3 ;
    }

    public double getResponseTime() {
        return responseTime;
    }

    public double getResponseSize() {
        return responseSize;
    }

    public String getResult() {
        return result;
    }

    public String getCode() {
        return code;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    public String getBinaryFilePath() {
        return binaryFilePath;
    }

    public String getJson() {
        return json;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getUrlWithQueries() {
        return urlWithQueries;
    }

    public String getMethod() {
        return method;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public HashMap<String, String> getQueries() {
        return queries;
    }

    public HashMap<String, String> getFormData() {
        return formData;
    }

    public ClassicHttpRequest getRequest() {
        return request;
    }

    public HashMap<String, String> getResponseHeaders() {
        return responseHeaders;
    }

    public void setMethod(String method) {
        this.method = method ;
    }

    public void setAddress(String address) {
        this.address = address ;
    }

    public void setTypeOfMessageBody(int typeOfMessageBody) {
        this.typeOfMessageBody = typeOfMessageBody;
    }

    public void setBinaryFilePath(String binaryFilePath) {
        this.binaryFilePath = binaryFilePath;
    }

    public void addQueriesToRequest() {
        urlWithQueries = address ;
        if (queries.size() != 0) {
            urlWithQueries = address.concat("?") ;
            for (String temp : queries.keySet()) {
                urlWithQueries = urlWithQueries.concat(temp) ;
                urlWithQueries = urlWithQueries.concat("=") ;
                urlWithQueries = urlWithQueries.concat(queries.get(temp)) ;
                urlWithQueries = urlWithQueries.concat("&") ;
            }
            urlWithQueries = urlWithQueries.substring(0 , urlWithQueries.length() - 1) ;
        }
    }

    public void addHeader(String key, String value) {
        headers.put(key,value) ;
    }

    public void addFormData(String key , String value) {
        formData.put(key , value) ;
    }

    public void addJson(String json) {
        this.json = json ;
    }

    public void addQuery(String key , String value) {
        queries.put(key , value) ;
    }

    public void createRequest() {
        try {
            switch (method) {
                case "GET":
                    request = new HttpGet(urlWithQueries);
                    break;
                case "POST":
                    request = new HttpPost(urlWithQueries);

                    break;
                case "PUT":
                    request = new HttpPut(urlWithQueries);
                    break;
                case "PATCH":
                    request = new HttpPatch(urlWithQueries);
                    break;
                case "DELETE":
                    request = new HttpDelete(urlWithQueries);
                    break;
                default:
                    throw new IllegalArgumentException("invalid method : " + method) ;
            }

            if (headers.size() > 0) {
                for (String key : headers.keySet())
                    request.addHeader(key, headers.get(key));
            }

            setEntityOfRequest();

            try {
                httpClient = HttpClientBuilder.create().build();
                try {
                    long start = System.currentTimeMillis();
                    response = httpClient.execute(request);
                    long time = System.currentTimeMillis() - start;
                    responseTime = time/1000.0 ;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                code = String.valueOf(response.getCode()) ;
                reasonPhrase = response.getReasonPhrase() ;

                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    // return it as a String
                    InputStream inputStream = entity.getContent();
                    int n;
                    byte[] buffer = new byte[1024];
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    while ((n = inputStream.read(buffer, 0, buffer.length)) != -1)
                        byteArrayOutputStream.write(buffer, 0, n);
                    result = byteArrayOutputStream.toString();
                    responseSize = byteArrayOutputStream.toByteArray().length ;
                    responseHeaders = new HashMap<>() ;
                    for (Header temp : response.getHeaders()) {
                        responseHeaders.put(temp.getName() , temp.getValue()) ;
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            httpClient.close();
            response.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void setEntityOfRequest(){
        if(typeOfMessageBody == 0 && formData.size() != 0) {
            MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
            for (String temp : formData.keySet()) {
                entityBuilder.addTextBody(temp, formData.get(temp));
            }
            HttpEntity entity = entityBuilder.build();
            request.setEntity(entity);
        }
        else if (typeOfMessageBody == 1 && json.length() != 0) {
            request.addHeader("Content-Type" , "application/json");
            // send a JSON data
            request.setEntity(new StringEntity(json));
        }
        else if (typeOfMessageBody == 2 && !binaryFilePath.equals("")) {
            MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
            request.addHeader("Content-Type", "application/octet-stream");
            File file = new File(binaryFilePath) ;
            entityBuilder.addBinaryBody("file", file, ContentType.DEFAULT_BINARY, file.getName());
            HttpEntity entity = entityBuilder.build();
            request.setEntity(entity);
        }
    }

    @Override
    public String toString() {
        return "Request{" +
                "responseTime=" + responseTime +
                ", responseSize=" + responseSize +
                ", result='" + result + '\'' +
                ", code='" + code + '\'' +
                ", reasonPhrase='" + reasonPhrase + '\'' +
                ", binaryFilePath='" + binaryFilePath + '\'' +
                ", json='" + json + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", urlWithQueries='" + urlWithQueries + '\'' +
                ", method='" + method + '\'' +
                ", headers=" + headers +
                ", queries=" + queries +
                ", formData=" + formData +
                ", request=" + request +
                ", httpClient=" + httpClient +
                ", response=" + response +
                ", typeOfMessageBody=" + typeOfMessageBody +
                ", responseHeaders=" + responseHeaders +
                '}';
    }
}

