package ejarosiewicz.com.eventreminder.data.room.pojo

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "event")
data class EventEntity(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "event_id")var eventId: Long? = null,
        var name: String?
)