package com.kiblerdude.achievements.service;

import java.io.IOException;
import java.util.Optional;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Service for serializing domain objects to and from JSON.
 * 
 * @author kiblerj
 *
 */
public class SerializationService {

	private static final Logger LOG = Logger.getLogger(SerializationService.class);

	private final ObjectMapper mapper;

	public SerializationService() {
		mapper = new ObjectMapper();
	}

	/**
	 * Serializes the provided <code>object</code> to JSON.
	 * 
	 * @param object
	 *            The object to serialize to JSON.
	 * @return The JSON representation of the object or empty if the object
	 *         could not be serialized.
	 */
	public <T extends Object> Optional<String> serialize(T object) {
		try {
			return Optional.of(mapper.writeValueAsString(object));
		} catch (JsonProcessingException e) {
			LOG.error(e);
			return Optional.empty();
		}
	}

	/**
	 * Deserializes the provided <code>json</code> string to a new instance of
	 * <code>clazz</code>.
	 * 
	 * @param json
	 *            The JSON string to deserialize.
	 * @param clazz
	 *            The class type to deserialize the object to.
	 * @return An new instance of the deserialized object or empty if the JSON
	 *         could not be deserialized to an instance of the provided class
	 *         type.
	 */
	public <T extends Object> Optional<T> deserialize(String json, Class<T> clazz) {
		try {
			return Optional.of(mapper.readValue(json, clazz));
		} catch (IOException e) {
			LOG.error(e);
			return Optional.empty();
		}
	}
}
