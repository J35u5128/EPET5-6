package org.example.epet56.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción personalizada para indicar que un recurso solicitado no fue encontrado.
 * Esta excepción se mapea automáticamente a un estado HTTP 404 (Not Found).
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecursoNoEncontradoException extends RuntimeException {

    /**
     * Constructor para RecursoNoEncontradoException.
     * @param mensaje El mensaje de error que describe el recurso no encontrado.
     */
    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
