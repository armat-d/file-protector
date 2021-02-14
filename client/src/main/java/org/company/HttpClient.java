package org.company;

import okhttp3.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class HttpClient {
    private static final String CLIENT_FILE_PATH = "/Users/ajabassov/Downloads/fileprotector/client/";
    
    private final OkHttpClient client = new OkHttpClient();
    
    public void upload(File encryptedFile) throws IOException {
        RequestBody requestBody = RequestBody.create(encryptedFile, MediaType.parse("application/octet-stream"));
        
        Request request = new Request.Builder()
                .url("http://localhost:8089/file/" + encryptedFile.getName())
                .post(requestBody)
                .build();
    
        client.newCall(request).execute();
    }
    
    public File download(String fileName) throws IOException {
        Request request = new Request.Builder()
                .url("http://localhost:8089/file/" + fileName)
                .build();
    
        Call call = client.newCall(request);
        Response response = call.execute();
        if (response.isSuccessful()) {
            File destination = new File(CLIENT_FILE_PATH + fileName);
            // copyInputStreamToFile should close the inputStream from the response
            FileUtils.copyInputStreamToFile(response.body().byteStream(), destination);
            return destination;
        }
        
        return null;
    }
}
