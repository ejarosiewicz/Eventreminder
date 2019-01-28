package ejarosiewicz.com.eventreminder.presentation.navigator

import android.support.annotation.IdRes

interface ContainerProvider {

    @get:IdRes
    val containerId: Int
}