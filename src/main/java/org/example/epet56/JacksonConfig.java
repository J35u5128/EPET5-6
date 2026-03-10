package org.example.epet56;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Clase de configuración para personalizar el comportamiento de Jackson,
 * la librería utilizada por Spring Boot para la serialización y deserialización de JSON.
 */
@Configuration
public class JacksonConfig {

    /**
     * Define un bean {@link ObjectMapper} personalizado para toda la aplicación.
     * Esta configuración asegura que los tipos de fecha y hora de Java 8 (como {@link java.time.LocalDate})
     * se serialicen en un formato estándar ISO 8601 (ej. "2024-12-25") en lugar de timestamps numéricos.
     *
     * @return una instancia configurada de {@link ObjectMapper}.
     */
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        // Registra el módulo que añade soporte para los tipos de Java 8 (JSR-310).
        mapper.registerModule(new JavaTimeModule());
        // Deshabilita la escritura de fechas como timestamps (números) y favorece el formato de cadena ISO.
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }
}
