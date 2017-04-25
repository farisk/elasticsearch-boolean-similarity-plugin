# elasticsearch-boolean-similarity-plugin
## Installing the plugin
Compiling the code, and runnig the plugin:
NOTE: If you are compiling against a Java version != 1.8, change pom.xml, and src/main/resurces/plugin-descriptor.properties

In the elasticsearch-boolean-similarity-plugin, run:
1. `mvn clean install`

2. `./createPlugin.sh`

3. If all went to plan, you should see elasticsearch.zip in the directory. This is the installable for elasticsearch.

4. Use the elasticsearch install plugin tool to install elasticsearch.zip
e.g:
/users/h3h3/bin/elasticsearch-5.2.2/bin/elasticsearch-plugin install file:/users/h3h3/esplugins/elasticsearch-boolean-similarity-plugin/elasticsearch.zip
I find that I have to provide the full path to elasticsearch.zip.

##Using the plugin
When you create an index, change define the similarity `booleansimilarity` and set your properties to use it. e.g:
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