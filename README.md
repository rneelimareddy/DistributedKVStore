# DistributedKVStore
This is a 3-tier distributed RESTful application which works mainly as a database or cache, where the system maintains eventual consistency, replication and concurrency and also the storage is durable. 
This project is developed in JAVA, stored data as JSON, used Postman Application to test API, used Apache Kafka as the message bus to send and receive messages between multiple systems

KV Store is a simple Key-value store that can be used to store and retrieve data between multiple systems. It can be used as database and cache.

Implemented POST, PUT, GET, DELETE API’s.

POST API works like Create, where a key with a value can be added to the store

GET API works like retrieve, when a particular key is given and a value is requested then the corresponding value will be retrieved from the store

PUT API works like update, where a value can be modified for any given key and the updated value will be stored back in the store

DELETE API works like delete, where when a key is provided then the value for that key and the key will be deleted from the store.

Used Apache Kafka as the message bus to send and receive messages between multiple systems.

Implemented Clocks to maintain Eventual consistency.

Maintained Replication by backing up the data in multiple systems.

Synchronized the methods in order to avoid race conditions and concurrency.
