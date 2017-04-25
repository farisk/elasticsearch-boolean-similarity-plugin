# elasticsearch-boolean-similarity-plugin
This plugin makes available boolean similarity from lucence in elasticsearch V5+. To quote the lucene API:

`Simple similarity that gives terms a score that is equal to their query boost. This similarity is typically used with disabled norms since neither document statistics nor index statistics are used for scoring. That said, if norms are enabled, they will be computed the same way as SimilarityBase and BM25Similarity with discounted overlaps so that the Similarity can be changed after the index has been created.`

I.E: Given a term, if it is present, count 1 ( * boost ) to the score regardless of frequency or IDF. Sometimes the complex elasticsearch similarity modules are actualy not appropraite for the usecase. For example, if you have a database of dishes and their ingredients, if you query for "eggs" you don't really care about the frequency of the word eggs, or the length of the document, you just care that it contains eggs. 

This plugin can be expected to eventually become obsolete, a commit has been made to the elasticsearch master which adds boolean similarity as part of the default modules, but for now this plugin will sufice. 


## Compiling the code and Installing the plugin
NOTE: 

+If you are compiling against a Java version != 1.8, change pom.xml, and src/main/resurces/plugin-descriptor.properties

+IMPORTANT: If you are using a version of elasticsearch != 5.2.2, you need to go into pom.xml, and set elasticsearch.version to the version you plan to install the plugin to.

Clone the repo, in elasticsearch-boolean-similarity-plugin:
1. `mvn clean install`

2. `./createPlugin.sh` 

(Note: If you are on a non-unix machine, just open createPlugin.sh and what you will have to do will be self-explanatory.

3. If all went to plan, you should see elasticsearch.zip in the directory. This is the installable for elasticsearch.

4. Use the elasticsearch install plugin tool to install elasticsearch.zip

e.g:
/users/h3h3/bin/elasticsearch-5.2.2/bin/elasticsearch-plugin install file:/users/h3h3/esplugins/elasticsearch-boolean-similarity-plugin/elasticsearch.zip

I find that I have to provide elasticsearch-plugin with the full path to elasticsearch.zip.

## Using the plugin
When you create an index, define a similarity using type `booleansimilarity` and set your properties to use it. e.g:

PUT recipes
{
  
  "settings": {
  
        "similarity" : {
        
      	  "custom_similarity_3" : {
          
        		"type" : "booleansimilarity"
            
      	  }
          
        }
  },
  
  "mappings": {
  
    "dish": {
    
      "properties": {
      
        "name" : {
        
          "similarity": "custom_similarity_3",
          
          "type": "text"
        },
        
        "description": {
        
          "similarity": "custom_similarity_3",
          
          "type": "text"
          
        }
      }
      
    }
    
  }
  
}
