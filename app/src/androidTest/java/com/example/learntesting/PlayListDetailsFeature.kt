package com.example.learntesting

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import org.hamcrest.core.AllOf
import org.junit.Test


class PlayListDetailsFeature : BaseUiTest() {
   @Test
   fun displayPlayListsNameDetails(){
       Espresso.onView(
           AllOf.allOf(
               ViewMatchers.withId(R.id.image_id),
               ViewMatchers.isDescendantOfA(nthChildOf(ViewMatchers.withId(R.id.playlist), 0))
           )
       ).perform(ViewActions.click())
       assertDisplayed("Hard Rock Cafe")
       assertDisplayed("Rock your senses with this timeless signature vibe list. \\n\\n • Poison \\n • You shook me all night \\n • Zombie \\n • Rock'n Me \\n • Thunderstruck \\n • I Hate Myself for Loving you \\n • Crazy \\n • Knockin' on Heavens Door")
   }
}