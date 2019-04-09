## Log Processor
A rest based log processor that parses input files, stores them in a mongo db
and reads outputs on an as of basis

##Build
Checkout the application via
    git clone https://github.com/ambarish-prakash/logProcessor.git
Update application.yaml config files to use your own mongodb connection

Use any IDE to run the LogprocessorApplication Class's main.

Or alternatively, run "gradlew clean build" to generate the jar file and run it using thhe java jvm
java -jar logprocessor-0.0.1-SNAPSHOT.jar

##Sample Input File
Only accepts csv files
object_id,object_type,timestamp,object_changes
1,Order,1484730554,"{\"customer_name\":\"Jack\",\"customer_address\":\"Trade St.\",\"status\":\"unpaid\"}"
2,Order,1484730623,"{\"customer_name\":\"Sam\",\"customer_address\":\"Gecko St.\",\"status\":\"unpaid\"}"
1,Product,1484731172,"{\"name\":\"Laptop\",\"price\":2100,\"stock_levels\":29}"
1,Order,1484731481,"{\"status\":\"paid\",\"ship_date\":\"2017-01-18\",\"shipping_provider\":\"DHL\"}"
