package com.example.learntesting.unittests

import com.example.outsideintddexample.acceptancetests.MainCoroutineScopeRule
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test


class EngineShould {
  private val engine = Engine()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineScopeRule = MainCoroutineScopeRule()

    @ExperimentalCoroutinesApi
    @Test
    fun turnOn() = runBlockingTest {
        engine.turnOn()
        assert(engine.isTurnOn)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun riseTheTempratureWhenItTurnOn() = runBlockingTest {
        val flow : Flow<Int> = engine.turnOn()
        val actual:List<Int> = flow.toList()
        assertEquals(listOf(25,50,95),actual)
    }
}