package com.library.bookservice.infrastructure.event

import org.slf4j.LoggerFactory

class TopicConsumer {

    private val logger = LoggerFactory.getLogger(TopicConsumer::class.java)


    fun consumeTopic() =
            kafka("meme") {
                // Corpo da func kafkaDSL.Init
                consumer("xd") {
                    // Corpo da func Consumer.doConsume
                    consume {

                    }
                }
            }
}

