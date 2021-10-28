package com.mksoftware101.notes

import com.mksoftware101.notes.features.allnotes.AllNotesViewModel
import org.junit.Before

class AllNotesViewModelTest {

    lateinit var viewModel: AllNotesViewModel

    @Before
    fun setUp() {
        viewModel = AllNotesViewModel()
    }

//    fun `fetch all notes from database`() {
//        val allNotes = viewModel.fetchAllNotes()
//        assertThat(allNotes)
//    }

    private fun assertThat(allNotes: Any) {
        TODO("Not yet implemented")
    }
}