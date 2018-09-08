package be.g00glen00b.apps.biodiversityapi.media;

import be.g00glen00b.apps.biodiversityapi.specie.Species;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MediaService {
    private RestTemplate restTemplate;

    public Map<String, Optional<String>> getMedia(List<Species> species) {
        String vernacularNames = species.stream()
            .map(Species::getVernacularName)
            .filter(Objects::nonNull)
            .collect(Collectors.joining("|"));
        String url = UriComponentsBuilder
            .fromHttpUrl("https://nl.wikipedia.org/w/api.php")
            .queryParam("action", "query")
            .queryParam("titles", vernacularNames)
            .queryParam("prop", "pageimages")
            .queryParam("format", "json")
            .build().toUriString();
        return restTemplate
            .getForObject(url, MediaResult.class)
            .getQuery().getPages()
            .values().stream()
            .collect(Collectors.toMap(MediaResultPage::getTitle, page -> getImage(page.getThumbnail())));
    }

    private Optional<String> getImage(MediaResultThumbnail thumbnail) {
        if (thumbnail == null) {
            return Optional.empty();
        } else {
            return Optional.of(thumbnail.getSource().replaceAll("\\d+px", "400px"));
        }
    }
}
