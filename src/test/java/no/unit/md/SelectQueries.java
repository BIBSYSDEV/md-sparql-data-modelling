package no.unit.md;

import nva.commons.utils.IoUtils;
import org.apache.jena.riot.Lang;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static no.unit.md.Constants.ORGANIZATION;
import static no.unit.md.Constants.PERSON;
import static no.unit.md.Constants.PROJECT;

public class SelectQueries {

    public static final String CROSS_DEPARTMENT = "people_who_have_participated_in_projects_with_other_depts.sparql";
    public static final String CROSS_ORGANIZATIONAL = "cross_organizational_collaboration.sparql";
    public static final String PROJECT_REPORT_SPARQL = "project_report.sparql";

    /**
     * Returns nothing with the data as it is.
     */
    @Test
    void crossDepartmentalCollaboration() {
        String query = IoUtils.stringFromResources(Path.of(CROSS_DEPARTMENT));
        ModelTools modelTools = new ModelTools();
        modelTools.addDataToModel(Lang.JSONLD, ORGANIZATION, PERSON, PROJECT);
        modelTools.queryModel(query);
    }

    /**
     * Returns nothing with the data as it is.
     */
    @Test
    void crossOrganizationalCollaboration() {
        String query = IoUtils.stringFromResources(Path.of(CROSS_ORGANIZATIONAL));
        ModelTools modelTools = new ModelTools();
        modelTools.addDataToModel(Lang.JSONLD, ORGANIZATION, PERSON, PROJECT);
        modelTools.queryModel(query);
    }

    @Test
    void simpleReport() {
        String query = IoUtils.stringFromResources(Path.of(PROJECT_REPORT_SPARQL));
        ModelTools modelTools = new ModelTools();
        modelTools.addDataToModel(Lang.JSONLD, ORGANIZATION, PERSON, PROJECT);
        var start = Instant.now();
        modelTools.queryModel(query);
        var end = Instant.now();
        var elapsed = ChronoUnit.MILLIS.between(start, end);
        String additive;
        if (elapsed < 300) {
            additive = "😅";
        } else {
            additive = "😭";
        }
        System.out.println("Query took: " + elapsed + " milliseconds " + additive);
    }
}
