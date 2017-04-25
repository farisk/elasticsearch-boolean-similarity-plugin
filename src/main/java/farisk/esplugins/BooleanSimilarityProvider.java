package farisk.esplugins;

import org.apache.lucene.search.similarities.BooleanSimilarity;
import org.apache.lucene.search.similarities.Similarity;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.assistedinject.Assisted;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.similarity.*;

public class BooleanSimilarityProvider extends AbstractSimilarityProvider {

    private BooleanSimilarity similarity;

    @Inject
    public BooleanSimilarityProvider(@Assisted String name, @Assisted Settings settings) {
        super(name);
        this.similarity = new BooleanSimilarity();
    }

    @Override
    public Similarity get() {
        return similarity;
    }

}
