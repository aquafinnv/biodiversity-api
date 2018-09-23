package be.g00glen00b.apps.biodiversityapi.gbif;

import be.g00glen00b.apps.biodiversityapi.api.APIProperties;
import lombok.AllArgsConstructor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
@AllArgsConstructor
public class GbifReader extends MultiResourceItemReader<GbifRecord> {
    private static final int GBIF_ID_POSITION = 0;
    private static final int LICENSE_POSITION = 36;
    private static final int INSTITUTION_CODE_POSITION = 59;
    private static final int DATASET_NAME_POSITION = 61;
    private static final int INDIVIDUAL_COUNT_POSITION = 71;
    private static final int LATITUDE_POSITION = 132;
    private static final int LONGITUDE_POSITION = 133;
    private static final int VERBATIM_POSITION = 138;
    private static final int SCIENTIFIC_NAME_POSITION = 182;
    private static final int KINGDOM_POSITION = 190;
    private static final int PHYLUS_POSITION = 191;
    private static final int CLASS_POSITION = 192;
    private static final int ORDER_POSITION = 193;
    private static final int FAMILY_POSITION = 194;
    private static final int GENUS_POSITION = 195;
    private static final int SUBGENUS_POSITION = 196;
    private static final int VERNACULAR_NAME_POSITION = 201;
    private APIProperties apiProperties;

    @PostConstruct
    public void initialize() throws IOException {
        ResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = patternResolver.getResources(apiProperties.getGbifLocationPattern());
        setResources(resources);
        setDelegate(getDelegate());
    }

    private FlatFileItemReader<GbifRecord> getDelegate() {
        FlatFileItemReader<GbifRecord> delegate = new FlatFileItemReader<>();
        delegate.setLinesToSkip(1);
        delegate.setLineMapper(getLineMapper());
        delegate.setStrict(false);
        return delegate;
    }

    private DefaultLineMapper<GbifRecord> getLineMapper() {
        DefaultLineMapper<GbifRecord> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(getTokenizer());
        lineMapper.setFieldSetMapper(getFieldSetMapper());
        return lineMapper;
    }

    private BeanWrapperFieldSetMapper<GbifRecord> getFieldSetMapper() {
        BeanWrapperFieldSetMapper<GbifRecord> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(GbifRecord.class);
        return fieldSetMapper;
    }

    private GbifTokenizer getTokenizer() {
        GbifTokenizer tokenizer = new GbifTokenizer(DelimitedLineTokenizer.DELIMITER_TAB);
        tokenizer.setIncludedFields(
            GBIF_ID_POSITION,
            LICENSE_POSITION,
            INSTITUTION_CODE_POSITION,
            DATASET_NAME_POSITION,
            INDIVIDUAL_COUNT_POSITION,
            LATITUDE_POSITION,
            LONGITUDE_POSITION,
            VERBATIM_POSITION,
            SCIENTIFIC_NAME_POSITION,
            KINGDOM_POSITION,
            PHYLUS_POSITION,
            CLASS_POSITION,
            ORDER_POSITION,
            FAMILY_POSITION,
            GENUS_POSITION,
            SUBGENUS_POSITION,
            VERNACULAR_NAME_POSITION);
        tokenizer.setNames(
            "gbifID",
            "license",
            "institutionCode",
            "datasetName",
            "individualCount",
            "decimalLatitude",
            "decimalLongitude",
            "verbatimSRS",
            "scientificName",
            "kingdom",
            "phylus",
            "speciesClass",
            "order",
            "family",
            "genus",
            "subgenus",
            "vernacularName");
        return tokenizer;
    }
}
