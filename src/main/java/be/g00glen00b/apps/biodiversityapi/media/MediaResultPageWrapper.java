package be.g00glen00b.apps.biodiversityapi.media;

import lombok.Data;

import java.util.Map;

@Data
public class MediaResultPageWrapper {
    private Map<String, MediaResultPage> pages;
}
