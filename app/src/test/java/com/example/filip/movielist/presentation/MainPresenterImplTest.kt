package com.example.filip.movielist.presentation

import com.example.filip.movielist.R
import com.example.filip.movielist.view.MainView
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.runners.MockitoJUnitRunner

/**
 * Created by Filip Babic @cobe
 */

@RunWith(MockitoJUnitRunner::class)
class MainPresenterImplTest {

    private lateinit var presenter: MainPresenterImpl

    @Mock lateinit var mainView: MainView

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = MainPresenterImpl()
        presenter.setView(mainView)
    }

    @Test
    fun testHandleConnectionStatusHasInternet() {
        presenter.handleConnectionStatus(true)

        verifyZeroInteractions(mainView)
    }

    @Test
    fun testHandleConnectionStatusNoInternet() {
        presenter.handleConnectionStatus(false)

        verify(mainView).showNoConnectionError()

        verifyNoMoreInteractions(mainView)
    }

    @Test
    fun testHandleHomeButtonClick() {
        presenter.handleHomeButtonClick()

        verify(mainView).openNavigationDrawer()

        verifyNoMoreInteractions(mainView)
    }

    @Test
    fun testHandleFavoriteMoviesNavigationClick() {
        presenter.handleNavigationItemClick(R.id.action_favorite)

        verify(mainView).startFavoriteMoviesActivity()

        verifyNoMoreInteractions(mainView)
    }

    @Test
    fun testHandleDatabaseNavigationClick() {
        presenter.handleNavigationItemClick(R.id.action_database)

        verify(mainView).startDatabaseActivity()

        verifyNoMoreInteractions(mainView)
    }

    @Test
    fun testHandleUsernameNavigationClick() {
        presenter.handleNavigationItemClick(R.id.action_username)

        verify(mainView).startChangeUsernameActivity()

        verifyNoMoreInteractions(mainView)
    }

    @Test
    fun testHandleNavigationClickDefault() {
        presenter.handleNavigationItemClick(-1)

        verify(mainView).closeNavigationDrawer()

        verifyNoMoreInteractions(mainView)
    }

    @Test
    fun testHandleSearchButtonClick() {
        presenter.handleSearchButtonClick()

        verify(mainView).startSearchActivity()

        verifyNoMoreInteractions(mainView)
    }

    @Test
    fun testCancelSubscriptions() {
        presenter.cancelSubscriptions()

        verify(mainView).onRequestsCancelled()

        verifyNoMoreInteractions(mainView)
    }
}