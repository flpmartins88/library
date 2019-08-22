package com.library.bookservice.infrastructure.event

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer
import org.slf4j.LoggerFactory
import java.time.Duration
import java.util.Properties
import java.util.UUID


class Consumer(bootstrapServers: String, topic: String) {

    private val kafkaConsumer: KafkaConsumer<String, String>

    init {
        val config = Properties()
        config[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
        config[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        config[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        config[ConsumerConfig.GROUP_ID_CONFIG] = UUID.randomUUID().toString()
        kafkaConsumer = KafkaConsumer<String, String>(config).apply {
            subscribe(listOf(topic))
        }
    }

    fun consume(handler: (value: String) -> Unit) = GlobalScope.launch {
        kafkaConsumer.use {
            while (true) {
                it.poll(Duration.ofMillis(100))?.forEach { event ->
                    handler(event.value())
                }
            }
        }
    }
}


class KafkaDSL(private val bootstrapServers: String) {

    fun consumer(topic: String, doConsume: Consumer.() -> Unit) =
            Consumer(bootstrapServers, topic).doConsume()
}

fun kafka(bootstrapServers: String, init: KafkaDSL.() -> Unit) =
        KafkaDSL(bootstrapServers).init()