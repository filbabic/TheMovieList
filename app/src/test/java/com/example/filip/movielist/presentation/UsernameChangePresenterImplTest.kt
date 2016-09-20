package com.example.filip.movielist.presentation

import com.example.filip.movielist.view.UsernameChangeView
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Matchers
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.MockitoAnnotations
import org.mockito.runners.MockitoJUnitRunner

/**
 * Created by Filip Babic @cobe
 */
@RunWith(MockitoJUnitRunner::class)
class UsernameChangePresenterImplTest {

    private lateinit var presenter: UsernameChangePresenterImpl

    @Mock lateinit var usernameChangeView: UsernameChangeView

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = UsernameChangePresenterImpl()
        presenter.setView(usernameChangeView)
    }

    @Test
    fun testChangeUsernameWhenNull() {
        presenter.changeUsername(null)

        verify(usernameChangeView).showInvalidUsernameError()

        verifyNoMoreInteractions(usernameChangeView)
    }

    @Test
    fun testChangeUsernameWhenEmpty() {
        presenter.changeUsername("  ")

        verify(usernameChangeView).showInvalidUsernameError()

        verifyNoMoreInteractions(usernameChangeView)
    }

    @Test
    fun testChangeUsernameWhenValid() {
        presenter.changeUsername("username")

        verify(usernameChangeView).saveUsername(Matchers.anyString())

        verifyNoMoreInteractions(usernameChangeView)
    }

    @Test
    fun testSwitchViews() {
        presenter.switchViews()

        verify(usernameChangeView).animateSwitchViews()

        verifyNoMoreInteractions(usernameChangeView)
    }

    @Test
    fun testCancelSubscriptions() {
        presenter.cancelSubscriptions()

        verify(usernameChangeView).onRequestsCancelled()

        verifyNoMoreInteractions(usernameChangeView)
    }
}