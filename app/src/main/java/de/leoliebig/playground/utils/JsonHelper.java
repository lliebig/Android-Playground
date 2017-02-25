package de.leoliebig.playground.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Provides convenience methods for parsing JSON data with Jackson.
 * Created by Leo on 25.02.2017.
 */
public abstract class JsonHelper {

    private static ObjectMapper rootUnwrappingObjectMapper;
    private static ObjectMapper defaultObjectMapper;

    /**
     * Returns either the default {@link ObjectMapper} singleton or a version with root node
     * wrapping and unwrapping enabled.
     *
     * @param wrapRootEnabled Pass <code>true</code> to get an {@link ObjectMapper} singleton that ignores
     *                        the root node during serialization. Pass <code>false</code> to get the default
     *                        instance.
     * @return
     */
    public static ObjectMapper getObjectMapperInstance(boolean wrapRootEnabled) {

        if (wrapRootEnabled) {
            if (rootUnwrappingObjectMapper == null) {
                rootUnwrappingObjectMapper = createRootUnwrappingObjectMapper();
            }

            return rootUnwrappingObjectMapper;
        } else {
            if (defaultObjectMapper == null) {
                defaultObjectMapper = createDefaultObjectMapper();
            }

            return defaultObjectMapper;
        }

    }

    private static ObjectMapper createRootUnwrappingObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }

    private static ObjectMapper createDefaultObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }

}
