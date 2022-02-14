### Prerequisite 

Java 11, Maven



### Build

Under project root fold run the following command to generate jar file

```bash
mvn clean package
```



### Run

```bash
# run the jar file
java -jar target/log-analytics.jar <log_file_path>

# e.g 
java -jar target/log-analytics.jar  programming-task-example-data.log


# sample output

----------------------------------------------------------
The number of unique IP addresses: 11
The top 3 most visited URLs: [(4 visits: [168.41.191.40]), (3 visits: [50.112.00.11, 177.71.128.21, 72.44.32.10]), (2 visits: [168.41.191.34, 168.41.191.43, 168.41.191.9])]
The top 3 most active IP addresses: [(2 visits: [/docs/manage-websites/]), (1 visits: [/blog/2018/08/survey-your-opinion-matters/, /newsletter/, /this/page/does/not/exist/, http://example.net/blog/category/meta/, /asset.css, /asset.js, /to-an-error, /faq/how-to-install/, /blog/category/community/, /hosting/, /intranet-analytics/, /docs/, /faq/, /translations/, /, /moved-permanently, http://example.net/faq/, /temp-redirect, /docs/manage-users/, /download/counter/, /faq/how-to/])]
----------------------------------------------------------

```

