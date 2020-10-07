package no.unit.md;

import nva.commons.utils.IoUtils;
import org.apache.jena.riot.Lang;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static no.unit.md.Constants.ORGANIZATION;

public class InverseRelations {

    public static final String INVERSE_RELATIONSHIPS_SPARQL = "inverse_relationships.sparql";

    /**
     * The point of this test is to demonstrate on-the-fly generation of inverse relationships.
     */
    @Test
    void inverseRelationshipsCanBeCreated() {
        String query = IoUtils.stringFromResources(Path.of(INVERSE_RELATIONSHIPS_SPARQL));
        ModelTools modelTools = new ModelTools();
        modelTools.addDataToModel(Lang.JSONLD, ORGANIZATION);
        modelTools.queryModel(query);
    }
}
