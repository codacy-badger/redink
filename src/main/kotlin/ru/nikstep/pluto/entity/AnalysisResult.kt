package ru.nikstep.pluto.entity

import java.net.URL

class AnalysisResult(
    val url: URL,
    val students: List<String>,
    val matches: Set<AnalysisMatch>
)