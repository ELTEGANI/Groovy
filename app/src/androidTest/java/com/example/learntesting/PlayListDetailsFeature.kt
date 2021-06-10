package com.example.learntesting

import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.learntesting.di.idlingResource
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotExist
import org.hamcrest.core.AllOf
import org.junit.Test


class PlayListDetailsFeature : BaseUiTest() {
   @Test
   fun displayPlayListsNameDetails(){
       navigateToPlayListDetails(0)
       assertDisplayed("Hard Rock Cafe")
       assertDisplayed("Rock your senses with this timeless signature vibe list. \\n\\n • Poison \\n • You shook me all night \\n • Zombie \\n • Rock'n Me \\n • Thunderstruck \\n • I Hate Myself for Loving you \\n • Crazy \\n • Knockin' on Heavens Door")
   }

    @Test
    fun displayLoaderWhileFetchingThePlayListDetails(){
        IdlingRegistry.getInstance().unregister(idlingResource)
        Thread.sleep(2000)
        navigateToPlayListDetails(0)
        assertDisplayed(R.id.details_loader)
    }


    @Test
    fun hideLoader(){
        navigateToPlayListDetails(0)
         assertDisplayed(R.id.details_loader)
    }

    @Test
    fun hidesErrorMessage(){
       navigateToPlayListDetails(2)
       Thread.sleep(3000)
       assertNotExist(R.string.generic_error)
    }

    @Test
    fun displayErrorMessageWhenNetworkFails(){
        navigateToPlayListDetails(1)
        assertDisplayed(R.string.generic_error)
    }

    private fun navigateToPlayListDetails(row:Int) {
        Espresso.onView(
            AllOf.allOf(
                ViewMatchers.withId(R.id.image_id),
                ViewMatchers.isDescendantOfA(nthChildOf(ViewMatchers.withId(R.id.playlist), row))
            )
        ).perform(ViewActions.click())
    }

}