package org.cyanteam.telemaniacs.core.services;

import org.dozer.Mapper;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of object mapper service using Dozer
 */
@Named
public class DozerObjectMapperServiceImpl implements ObjectMapperService {
    @Inject
    private Mapper mapper;

    public <T> T map(Object sourceObject, Class<T> destinationClass) {
        return mapper.map(sourceObject, destinationClass);
    }

    public <T> List<T> map(Collection<?> sourceObjects, Class<T> destinationClass) {
        return sourceObjects.stream()
                .map(o -> map(o, destinationClass))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
