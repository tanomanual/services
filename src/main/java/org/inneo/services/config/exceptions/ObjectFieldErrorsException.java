package org.inneo.services.config.exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.ArrayList;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ObjectFieldErrorsException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private List<FieldErrorItem> fieldErrors = new ArrayList<>();

    public ObjectFieldErrorsException(String msg, List<FieldErrorItem> fieldErrors) {
        super( msg );
        this.fieldErrors = fieldErrors;
    }

    public ObjectFieldErrorsException(String msg, List<FieldErrorItem> fieldErrors, Throwable ceuse ) {
        super( msg, ceuse );
        this.fieldErrors = fieldErrors;
    }
}
