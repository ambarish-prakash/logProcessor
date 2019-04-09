# Log Processor
A rest based log processor that parses input files, stores them in a mongo db
and reads outputs on an as of basis

## Build
Checkout the application via
    git clone https://github.com/ambarish-prakash/logProcessor.git <br />
Update application.yaml config files to use your own mongodb connection <br /><br />

Use any IDE to run the LogprocessorApplication Class's main. <br />

Or alternatively, run "gradlew clean build" to generate the jar file and run it using the java jvm
java -jar logprocessor-0.0.1-SNAPSHOT.jar <br />

Access the swagger at http://localhost.com:8090/swagger-ui.html

## Sample Input File
Only accepts csv files<br />
object_id,object_type,timestamp,object_changes<br />
1,Order,1484730554,"{\"customer_name\":\"Jack\",\"customer_address\":\"Trade St.\",\"status\":\"unpaid\"}"<br />
2,Order,1484730623,"{\"customer_name\":\"Sam\",\"customer_address\":\"Gecko St.\",\"status\":\"unpaid\"}"<br />
1,Product,1484731172,"{\"name\":\"Laptop\",\"price\":2100,\"stock_levels\":29}"<br />
1,Order,1484731481,"{\"status\":\"paid\",\"ship_date\":\"2017-01-18\",\"shipping_provider\":\"DHL\"}"
