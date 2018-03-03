package com.cryptotalk.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Util
{
    public static <T> T parseJsonFromUrl(String content, Class<T> cl) throws IOException
    {
        ObjectMapper mapper = JacksonMapper.INSTANCE.getObjectMapper();
        URL jsonUrl = new URL(content);
        return mapper.readValue(jsonUrl, cl);
    }

    public static <T> T parseJsonAsStream(InputStream input, Class<T> cl)
        throws JsonParseException, JsonMappingException, IOException
    {
        ObjectMapper mapper = JacksonMapper.INSTANCE.getObjectMapper();
        return mapper.readValue(input, cl);
    }

}
