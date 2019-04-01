package ejarosiewicz.com.eventreminder.data.room.pojo

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "event")
data class EventEntity(
        @PrimaryKey
        @ColumnInfo(name = "event_id")var eventId: Int = -1,
        var name: String?
)