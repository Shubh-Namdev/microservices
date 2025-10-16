

What is kafka ?
Kafka is a tool to manage real time data. It manages a huge data from the real world and deliver approriate information the consumer of the data.

Let's try to understand it with a real world application.As we know that we have uber application where we get to see real time information about riders so Kafka is the one who is responsible for providing this kind of real time informtation to the users. It uses an architecture where it gets some information and it deliver it in a very efficient way to the consumer.

Why it is requried ?
Kafka is required because it has a vey strong capability of ogranizing huge data. It handles crores of messages and delivers it appropriately. 
Lets say we have one application where we have multiple services and all services need to communicate with each other then it will be complex for services to communicate as each service will need information about all the services which they have to communicate but in case a service communicates with Kafka and rest of the things done by kafka only.
It handle this communication in a very efficient way
It has capability of processing crores of messages without any problem
It provides scalability and fault tolerance

Kafka is an application of Event Driven Architecture where one service will produce events and other will consume.

Kafka Components -
    Kafka broker : This is a setup where we will be installing the kafka and starting it
    Kafka Topics : Kind of storage using which communication will be done
    Producer : One who will produce the events
    Consumer : One who will consume the events

Kafka Architecture :
    - Producer generates a kafka topic
    - All consumer who are subsribed to the topic will be active
    - Partitions : 
        - Topics can further devided into partition and we can have as many partitions as required.
        - Messages recieving to kafka can be distributed to different partitions
        - Keys can used to assign a specific information a partition 
        - Multiple consumer can be assigned to multiple partition for parellel processing 
    - Multiple kafka broker can be setup to manage fault tolerance


Using Zookeper -
    - start zookeeper : bin/windows/zookeeper-server-start.bat config/zookeeper.properties
    - start kafka : bin/windows/kafka-server-start.bat config/server.properties

Using Kraft - 
    - Generate a unique random uuid :  bin/windows/kafka-storage.bat random-uuid
    - Format the storage directory with custom uuid :  bin/windows/kafka-storage.bat format -t generated_uuid -c config/kraft/server.properties
    - start kafka : bin/windows/kafka-server-start.bat config/kraft/server.properties

Creating a Topic 
    - bin/windows/kafka-topics.bat --create \
        --topic test-topic \ 
        --bootstrap-server localhost:9092 \
        --partitions 1 \
        --replication-factor 1

Connect a producer
    - bin/windows/kafka-console-producer.bat \
        --bootstrap-server localhost:9092 \
        --topic test-topic 

Connect a Consumer - 
    - bin/windows/kafka-console-consumer.bat \
        --bootstrap-server localhost:9092 \
        --topic test-topic \
        --from-beginning

Partitioning,Connect with key,Consumer group
        - Create topic
        - Create producer : bin/windows/kafka-console-producer.bat \ 
                                --bootstrap-server localhost:9092 \ 
                                --topic order \ 
                                --property parse.key=true \ 
                                --property key.separator=:
        
        - Create different consumer for number of partitions :  bin/windows/kafka-console-consumer.bat \ 
                                                                    --bootstrap-server localhost:9092 \ 
                                                                    --topic order \ 
                                                                    --group og \ 
                                                                    --from-beginning
        
        - Mutliple task can be executed parellely.

      
    Multiple Consumer groups -
        - Create a topic
        - Create a producer
        - Create different groups and each group contains multiple consumer
        - Data is distributed among all groups
        - Useful when we have to share data to different services for different processing

Kafka UI 
    - change advertise-listener property in server.properties
    