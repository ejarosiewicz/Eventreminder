package ejarosiewicz.com.eventreminder.presentation.main

import ejarosiewicz.com.eventreminder.domain.entity.Event

data class MainStateHolder(val state: MainState,
                           val events: List<Event> = emptyList())

