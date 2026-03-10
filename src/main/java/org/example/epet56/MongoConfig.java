package org.example.epet56;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

/**
 * Clase de configuración para la conexión con MongoDB.
 * Personaliza la creación del {@link MongoTemplate} para asegurar la compatibilidad
 * con MongoDB Atlas, especialmente en lo que respecta a la configuración SSL.
 */
@Configuration
public class MongoConfig {

    /**
     * La URI de conexión a MongoDB, inyectada desde `application.properties`.
     */
    @Value("${spring.mongodb.uri}")
    private String mongoUri;

    /**
     * El nombre de la base de datos, inyectado desde `application.properties`.
     * Se utiliza como fallback si la URI no especifica una base de datos.
     */
    @Value("${spring.mongodb.database}")
    private String mongoDatabase;

    /**
     * Define un bean {@link MongoTemplate} personalizado.
     * Este método configura el cliente de MongoDB para:
     * 1. Usar la URI de conexión proporcionada.
     * 2. Habilitar SSL y permitir nombres de host no válidos para evitar errores de handshake con Atlas.
     * 3. Asegurar que se utilice el nombre de la base de datos correcto, ya sea desde la URI o desde la propiedad `spring.mongodb.database`.
     *
     * @return una instancia configurada de {@link MongoTemplate}.
     */
    @Bean
    public MongoTemplate mongoTemplate() {
        // Parsea la cadena de conexión de MongoDB.
        ConnectionString connectionString = new ConnectionString(mongoUri);

        // Configura los ajustes del cliente de MongoDB, incluyendo SSL.
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .applyToSslSettings(builder -> builder.enabled(true).invalidHostNameAllowed(true)) // Habilita SSL y permite hosts no válidos.
                .build();

        // Determina el nombre de la base de datos a utilizar.
        String databaseName = connectionString.getDatabase();
        if (databaseName == null || databaseName.isEmpty()) {
            databaseName = mongoDatabase; // Usa el valor de la propiedad si no está en la URI.
        }

        // Crea y devuelve el MongoTemplate con la fábrica de cliente y el nombre de la base de datos.
        return new MongoTemplate(new SimpleMongoClientDatabaseFactory(MongoClients.create(mongoClientSettings), databaseName));
    }
}
