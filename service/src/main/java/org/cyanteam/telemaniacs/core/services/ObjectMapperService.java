package org.cyanteam.telemaniacs.core.services;

import java.util.Collection;
import java.util.List;

/**
 * Object mapper service
 */
public interface ObjectMapperService {
    /**
     * Get source object mapped to destination object
     *
     * @param sourceObject     Source object
     * @param destinationClass Destination class
     * @param <T>              Destination type
     * @return Destination object
     */
    <T> T map(Object sourceObject, Class<T> destinationClass);

    /**
     * Get list source objects mapped to destination objects
     *
     * @param sourceObjects    Source objects
     * @param destinationClass Destination class
     * @param <T>              Destination type
     * @return List of destination objects
     */
    <T> List<T> map(Collection<?> sourceObjects, Class<T> destinationClass);
}
