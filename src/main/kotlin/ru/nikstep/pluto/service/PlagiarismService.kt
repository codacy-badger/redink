package ru.nikstep.pluto.service

import ru.nikstep.pluto.entity.PullRequest

interface PlagiarismService {
    fun analyze(pullRequest: PullRequest)
//            : AnalysisResult
}
