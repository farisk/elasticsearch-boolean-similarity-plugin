package farisk.esplugins;

import org.elasticsearch.index.IndexModule;
import org.elasticsearch.plugins.Plugin;

import farisk.esplugins.BooleanSimilarityProvider;


public class BooleanSimilarityPlugin extends Plugin {

	@Override
	public void onIndexModule(IndexModule indexModule) {
	    indexModule.addSimilarity("booleansimilarity", (name,settings)->new BooleanSimilarityProvider(name, settings));	
	}
    
}