package com.library.bookservice.infrastructure.event

import org.slf4j.LoggerFactory

class TopicConsumer {

    fun consumeTopic() {
        val log = LoggerFactory.getLogger(TopicConsumer::class.java)

        kafka("meme") {
            // Corpo da func kafkaDSL.Init

            consumer("xd") {
                // Corpo da func Consumer.doConsume

                consume {

                }
            }
        }
    }

}