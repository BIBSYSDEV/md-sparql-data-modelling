package no.unit.md;

import nva.commons.core.ioutils.IoUtils;
import org.apache.jena.riot.Lang;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static no.unit.md.Constants.ORGANIZATION;
import static no.unit.md.Constants.PERSON;
import static no.unit.md.Constants.PROJECT;

public class ModelConstruction {

    public static final String DESCRIBE_COMPLETE_OBJECT_SPARQL = "describe_complete_object.sparql";

    /**
     * Returns everything related to projects. Note lack of repetition.
     */
    @Test
    void constructFullModel() {
        String query = IoUtils.stringFromResources(Path.of(DESCRIBE_COMPLETE_OBJECT_SPARQL));
        ModelTools modelTools = new ModelTools();
        modelTools.addDataToModel(Lang.JSONLD, PERSON, PROJECT, ORGANIZATION);
        modelTools.queryModel(query);
    }
}
