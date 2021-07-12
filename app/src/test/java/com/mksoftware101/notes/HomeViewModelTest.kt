package com.mksoftware101.notes

import com.mksoftware101.notes.home.HomeViewModel
import org.junit.Before

class HomeViewModelTest {

    lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        viewModel = HomeViewModel()
    }

    fun `fetch all notes from database`() {
        val allNotes = viewModel.fetchAllNotes()
        assertThat(allNotes)
    }

    private fun assertThat(allNotes: Any) {
        TODO("Not yet implemented")
    }
}