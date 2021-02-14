package org.company;

import io.javalin.Javalin;
import org.company.controller.FileController;

import java.io.InputStream;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Main {
    public static void main(String[] args) {
        FileController controller = new FileController();
        
        Javalin.create(config -> {
            config.defaultContentType = "application/octet-stream";
        }).routes(() ->
                path("file/:filename", () -> {
                    get(handler -> {
                        String filename = handler.pathParam("filename");
                        InputStream inputStream = controller.download(filename);
                        handler.result(inputStream);
                    });
                    post(handler -> {
                        String filename = handler.pathParam("filename");
                        InputStream inputStream = handler.bodyAsInputStream();
                        controller.upload(filename, inputStream);
                        handler.result("File has been uploaded");
                    });
                })
        ).start(8089);
    }
}
