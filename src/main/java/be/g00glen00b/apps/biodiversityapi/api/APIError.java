package be.g00glen00b.apps.biodiversityapi.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class APIError {
	private String[] codes;
	private String message;
}
