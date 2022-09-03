package no.unit.md;

import nva.commons.core.ioutils.IoUtils;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;

import java.io.InputStream;
import java.io.StringWriter;
import java.nio.file.Path;
import java.util.Arrays;

public class ModelTools {
    private static final Model model = ModelFactory.createDefaultModel();

    public void addDataToModel( Lang lang, Path... path) {
        Arrays.stream(path).map(IoUtils::inputStreamFromResources)
                .forEach(data -> RDFDataMgr.read(model, data, lang));
    }

    public void queryModel(String queryString) {
        Query query = QueryFactory.create(queryString);

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            if (query.isConstructType()) {

                printModelToSystemOut(queryExecution.execConstruct());
                return;
            }

            if (query.isDescribeType()) {
                printModelToSystemOut(queryExecution.execDescribe());
                return;
            }

            if (query.isSelectType()) {
                printResultSetToSystemOut(queryExecution);
            }
        }
    }

    private void printResultSetToSystemOut(QueryExecution queryExecution) {
        var results = ResultSetFormatter.asText(queryExecution.execSelect());
        System.out.println(results);
    }

    private void printModelToSystemOut(Model model) {
        StringWriter stringWriter = new StringWriter();
        RDFDataMgr.write(stringWriter, model, Lang.TURTLE);
        System.out.println(stringWriter.toString());
    }
}
