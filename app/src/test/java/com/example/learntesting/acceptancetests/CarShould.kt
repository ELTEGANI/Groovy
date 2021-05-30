package com.example.learntesting.acceptancetests

import com.example.outsideintddexample.acceptancetests.MainCoroutineScopeRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CarShould {

    private val engine:Engine = mock()
    private val car : Car

    init {
        runBlockingTest {
            whenever(engine.turnOn()).thenReturn(flow {
                delay(2000)
                emit(25)
                delay(2000)
                emit(50)
                delay(2000)
                emit(95)
            })
        }
        car = Car(4.5,engine)
    }

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineScopeRule = MainCoroutineScopeRule()
    @ExperimentalCoroutinesApi
    @Test
    fun loosFuelWhenItTurnOn() = runBlockingTest{
        car.turnOn()
        assertEquals(5.5,car.fuel)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun turnOnItsEngine() = runBlockingTest{
        car.turnOn()
        verify(engine, times(1)). turnOn()
    }
}