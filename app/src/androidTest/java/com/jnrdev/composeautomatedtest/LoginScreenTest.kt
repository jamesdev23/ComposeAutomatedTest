package com.jnrdev.composeautomatedtest

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun login_with_correct_credentials() {
        composeTestRule.setContent {
            MaterialTheme {
                LoginScreen { email, password ->
                    assert(email == "jnrdevappdev@gmail.com" && password == "123456")
                }
            }
        }

        composeTestRule
            .onNodeWithTag("EmailTextField")
            .performTextInput("jnrdevappdev@gmail.com")

        composeTestRule
            .onNodeWithTag("PasswordTextField")
            .performTextInput("123456")

        composeTestRule
            .onNodeWithTag("LoginButton")
            .performClick()

        composeTestRule
            .onNodeWithText("Login successful")
            .assertIsDisplayed()
    }

    @Test
    fun login_with_incorrect_credentials() {
        composeTestRule.setContent {
            MaterialTheme {
                LoginScreen { email, password ->
                    assert(email != "jnrdevappdev@gmail.com" && password != "123456")
                }
            }
        }

        composeTestRule
            .onNodeWithTag("EmailTextField")
            .performTextInput("otheremail@gmail.com")

        composeTestRule
            .onNodeWithTag("PasswordTextField")
            .performTextInput("random123")

        composeTestRule
            .onNodeWithTag("LoginButton")
            .performClick()

        composeTestRule
            .onNodeWithText("Wrong credentials")
            .assertIsDisplayed()
    }

}