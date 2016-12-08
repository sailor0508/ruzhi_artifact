package com.ruzhi.demo.json;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chunlong.wchl on 2015/5/28.
 */
public class CustomDateDeserialize extends JsonDeserializer<Date> {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");

    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {

        Date date = null;
        try {
           /* String d = sdf.format(Long.valueOf(jp.getText()));//get timestamp from file
            date = sdf.parse(d);*/
            date = sdf.parse(jp.getText());//get yyyy-MM-dd HH:mm:SS from file
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
