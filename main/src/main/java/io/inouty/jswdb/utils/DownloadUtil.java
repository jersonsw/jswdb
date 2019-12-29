package io.inouty.jswdb.utils;

import java.io.*;
import java.net.URL;

public class DownloadUtil {

    public static void download(String src, String outputName) throws IOException {
        URL url = new URL(src);
        InputStream in = url.openStream();
        String[] parts = src.split("\\.");
        String ext = parts[parts.length-1];
        OutputStream out = new BufferedOutputStream(new FileOutputStream("src/main/resources/public/images/" + outputName + "." + ext));
        for (int b; (b = in.read()) != -1; ) {
            out.write(b);
        }
        out.close();
        in.close();
    }

}
