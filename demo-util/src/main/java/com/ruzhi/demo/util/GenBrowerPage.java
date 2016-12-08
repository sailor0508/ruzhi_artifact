package com.ruzhi.demo.util;

import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by chunlong.wchl on 2015/4/28.
 */
public class GenBrowerPage {

    public GenBrowerPage(String urlAndParameter) {
        openURL(urlAndParameter);
    }

    public static void openURL(String url) {

        try {
            browse(url);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error attempting to launch web browser:\n" + e.getLocalizedMessage());
        }
    }

    private static void browse(String url) throws ClassNotFoundException, IllegalAccessException,
            IllegalArgumentException, InterruptedException, InvocationTargetException, IOException,
            NoSuchMethodException {
        String osName = System.getProperty("os.name", "");

        if (osName.startsWith("Mac OS")) {

            Class fileMgr = Class.forName("com.apple.eio.FileManager");
            Method openURL = fileMgr.getDeclaredMethod("openURL", new Class[]{String.class});
            openURL.invoke(null, new Object[]{url});

        } else if (osName.startsWith("Windows")) {

            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);

        } else { // assume Unix or Linux

            String[] browsers = {"firefox", "opera", "konqueror", "epiphany", "mozilla", "netscape"};
            String browser = null;
            for (int count = 0; count < browsers.length && browser == null; count++)
                if (Runtime.getRuntime().exec(new String[]{"which", browsers[count]}).waitFor() == 0)
                    browser = browsers[count];
            if (browser == null) {
                throw new NoSuchMethodException("Could not find web browser");
            } else {
                Runtime.getRuntime().exec(new String[]{browser, url});
            }
        }

    }

    public static void main(String[] args) {
        //String url = "\"http://www.163.com/\"";
        String url = "http://hadooprmvip.cm6:60010/tableQuery.jsp?name=opensearch_in&key=01599%3A6100701594440730%3A+++++++++++++++++++++++++++94581%3A++++++++++++main%3A1138239&max_versions=1";
        GenBrowerPage testUrl = new GenBrowerPage(url);
    }
}