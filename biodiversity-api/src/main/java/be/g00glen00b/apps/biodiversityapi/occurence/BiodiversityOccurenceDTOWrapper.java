package be.g00glen00b.apps.biodiversityapi.occurence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BiodiversityOccurenceDTOWrapper {
    private List<BiodiversityOccurenceDTO> occurences;
    private int page;
    private int limit;
    private long totalElements;
}
