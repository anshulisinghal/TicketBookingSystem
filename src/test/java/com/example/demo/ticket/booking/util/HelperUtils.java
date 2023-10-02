package com.example.demo.ticket.booking.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import com.example.demo.ticket.booking.model.entity.Shows;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class HelperUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public static List<Shows> getMockShows(String filePath) throws IOException {
        File file = getFile(filePath);
        return mapper.readValue(file, new TypeReference<>() {} );
    }

    public static <T> T getObjectFromRes(Class<T> className, String filePath) throws IOException {
        File file = getFile(filePath);
        return mapper.readValue(file, className);
    }

    private static File getFile(String filePath) {
        ClassLoader classLoader = HelperUtils.class.getClassLoader();
        URL resource = classLoader.getResource(filePath);
        File file = null;
        if (resource != null) {
            file = new File(resource.getFile());
        }
        return file;
    }

}
