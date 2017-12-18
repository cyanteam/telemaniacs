package org.cyanteam.telemaniacs.core.services;

import org.dozer.Mapper;
import org.dozer.MappingException;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Implementation of object mapper service using Dozer
 * @author Michael Le
 */
@Named
public class DozerObjectMapperServiceImpl implements ObjectMapperService {
    @Inject
    private Mapper mapper;

    public <T> T map(Object sourceObject, Class<T> destinationClass) {
        try {
            return mapper.map(sourceObject, destinationClass);
        } catch (MappingException e) {
            return null;
        }
    }

    public <T> List<T> map(Collection<?> sourceObjects, Class<T> destinationClass) {
        return sourceObjects.stream()
                .map(o -> map(o, destinationClass))
                .filter(Objects::nonNull)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
