package com.virgil.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import com.virgil.util.LogUtil;

/**
 * This class is used to test java socket
 * Created by 午敬 on 2015/3/15.
 */
public class TestSocket {
    private String address_ip = "";
    private int address_port = 0;

    public static void main(String[] args) {
        TestSocket ts = new TestSocket();
        ts.test();
    }

    private void test() {
        try (Socket socket = new Socket("127.0.0.1", 8000)) {
            OutputStream out=socket.getOutputStream();
            OutputStreamWriter writer=new OutputStreamWriter(out,"UTF-8");
            writer.write("Custom JavaFunctionTest");
            writer.flush();

            InputStream in = socket.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            StringBuilder sb = new StringBuilder();
            int c = 0;
            while (c != -1) {
                c = reader.read();
                sb.append((char) c);
            }
            LogUtil.printlnInConsle(sb.toString());

            writer.close();
            out.close();
            reader.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SecretKeySpec s=new SecretKeySpec("".getBytes(),"");
        try {
            Cipher localC=Cipher.getInstance("");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

}
