# MessageProducer
Spring Boot + RabbitMQ Produce Message Example (multiple or rest)

Lokal bilgisayarınıza RabbitMQ kurulması gerekmektedir.
https://www.rabbitmq.com/install-windows.html

RabbitMq çalıştığını test etmek için:
http://localhost:15672/
guest guest

Rest olarak kuyruğa veri göndermek için:
http://localhost:8080/sendMsg-rabbitMQ/producer?content=test&messageId=1&price=100
