package org.cyanteam.telemaniacs.rest.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resource, Long id) {
        super("Resource of type " + resource + " with ID " + id + " cannot be found.");
    }
    
    public ResourceNotFoundException(String resource) {
        super("Resource of type " + resource + " cannot be found.");
    }
}
