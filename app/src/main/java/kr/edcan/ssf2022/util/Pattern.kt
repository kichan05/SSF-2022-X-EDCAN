package kr.edcan.ssf2022.util

import java.util.regex.Pattern

object Pattern {
    val email = Pattern.compile("/^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}\$/")
}