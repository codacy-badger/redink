package ru.nikstep.pluto.service

import ru.nikstep.pluto.entity.PullRequest

class EmptyPlagiarismService : PlagiarismService {
    override fun analyze(pullRequest: PullRequest)
//            : AnalysisResult
    {
//        return AnalysisResult(URL(""), arrayListOf(), setOf())
    }
}