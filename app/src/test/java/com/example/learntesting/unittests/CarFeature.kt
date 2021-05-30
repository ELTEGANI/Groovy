package com.example.learntesting.unittests

import com.example.outsideintddexample.acceptancetests.MainCoroutineScopeRule
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

class CarFeature {

    private val engine = Engine()
    val car = Car(6.0,engine)

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineScopeRule = MainCoroutineScopeRule()

    @ExperimentalCoroutinesApi
    @Test
    fun carIsLoosingFuelWhenItTurnsOn() = runBlockingTest{
          car.turnOn()
          assertEquals(5.5,car.fuel)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun carIsTurningOnItsEngineIncreseThetemputure() = runBlockingTest{
        car.turnOn()
        coroutineScopeRule.advanceTimeBy(2000)
        assertEquals(25,car.engine.temperature)

        coroutineScopeRule.advanceTimeBy(2000)
        assertEquals(50,car.engine.temperature)

        coroutineScopeRule.advanceTimeBy(1000)
        assertEquals(95,car.engine.temperature)


        assertTrue(car.engine.isTurnOn)


    }

}