package com.example.learntesting

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.learntesting.di.idlingResource
import com.schibsted.spain.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.schibsted.spain.barista.internal.matcher.DrawableMatcher.Companion.withDrawable
import org.hamcrest.core.AllOf.allOf
import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule


class PlayListFeature : BaseUiTest(){

    @Test
    fun displayScreenTitle(){
        assertDisplayed("Playlists")
    }

    @Test
    fun displayListOfPlayLists(){
        assertRecyclerViewItemCount(R.id.playlist,5)
        onView(allOf(withId(R.id.playlist_name), isDescendantOfA(nthChildOf(withId(R.id.playlist),0))))
            .check(matches(withText("Hard Rock Cafe")))
            .check(matches(isDisplayed()))

        onView(allOf(withId(R.id.playlist_category), isDescendantOfA(nthChildOf(withId(R.id.playlist),0))))
            .check(matches(withText("rock")))
            .check(matches(isDisplayed()))

        onView(allOf(withId(R.id.image_id), isDescendantOfA(nthChildOf(withId(R.id.playlist),1))))
            .check(matches(withDrawable(R.drawable.ic_play)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun displaysLoaderWhileFetchingThePlayList(){
        IdlingRegistry.getInstance().unregister(idlingResource)
        assertDisplayed(R.id.loader)
    }


    @Test
    fun displayRockImageForRockListsItems(){
        onView(allOf(withId(R.id.image_id), isDescendantOfA(nthChildOf(withId(R.id.playlist),0))))
            .check(matches(withDrawable(R.drawable.rock)))
            .check(matches(isDisplayed()))
        onView(allOf(withId(R.id.image_id), isDescendantOfA(nthChildOf(withId(R.id.playlist),3))))
            .check(matches(withDrawable(R.drawable.rock)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun hidesLoader(){
        assertNotDisplayed(R.id.loader)
    }

    @Test
    fun navigateToDetailsScreen(){
        onView(allOf(withId(R.id.image_id), isDescendantOfA(nthChildOf(withId(R.id.playlist),0))))
            .perform(click())
        assertDisplayed(R.id.playlist_detail_root)
    }


}