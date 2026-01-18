package com.pasiflonet.mobile.utils

object TextSanitizer {

    private val urlRegex = Regex(
        "(?i)\b((https?://\S+)|(www\.\S+)|(t\.me/\S+)|(telegram\.me/\S+)|(tg://\S+))"
    )

    // Best-effort emoji remover (surrogate-pair emojis + symbols + VS + ZWJ)
    private val emojiRegex = Regex(
        "(" +
            "[\uD83C-\uDBFF][\uDC00-\uDFFF]" +
            "|" +
            "[\u2600-\u27BF]" +
            "|" +
            "[\uFE0E\uFE0F]" +
            "|" +
            "[\u200D]" +
        ")"
    )

    fun stripLinks(text: String): String = text.replace(urlRegex, "")

    fun stripEmojis(text: String): String = text.replace(emojiRegex, "")

    fun cleanIncomingText(text: String): String {
        val noLinks = stripLinks(text)
        val noEmoji = stripEmojis(noLinks)
        return noEmoji
            .replace("\r", "")
            .replace(Regex("[\t ]+"), " ")
            .replace(Regex(" *\n *"), "\n")
            .trim()
    }
}
