package cn.downey.controller;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * ./kafka-topics.sh --create --zookeeper hadoop100:2181 --partitions 2 --replication-factor 2 --topic DSFlinkCreated topic "DSFlink".
 */
public class DsClientTest {
    public static void main(String[] args) {
        String message = "Kafka Test";
        String address = "http://127.0.0.1:6097/DsInfoCollectService/webInfoCollectService";
        try {
            URL url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setAllowUserInteraction(true);
            connection.setUseCaches(false);
            connection.setConnectTimeout(6 * 1000);
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.connect();
            OutputStream outputStream = connection.getOutputStream();
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            bufferedOutputStream.write(message.getBytes());
            bufferedOutputStream.flush();
            StringBuilder temp = new StringBuilder();
            InputStream in = connection.getInputStream();
            byte[] tempBytes = new byte[1024];
            while (in.read(tempBytes, 0, 1024) != -1) {
                temp.append(new String(tempBytes));
            }
            System.out.println(connection.getResponseCode());
            System.out.println(temp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
