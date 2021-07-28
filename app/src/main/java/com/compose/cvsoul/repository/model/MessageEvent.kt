package com.compose.cvsoul.repository.model

enum class MessageType(value: String) {
    UNAUTHORIZED("unauthorized")
}

data class MessageEvent(val type: MessageType)

