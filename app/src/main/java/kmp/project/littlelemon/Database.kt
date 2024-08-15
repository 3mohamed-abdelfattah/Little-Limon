package kmp.project.littlelemon

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

@Entity(tableName = "menu_item")
data class MenuItemEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val image: String,
)

@Dao
interface MenuItemDao {
    @Insert
    suspend fun insertAll(menuItems: List<MenuItemEntity>)

    @Query("SELECT * FROM menu_item")
    suspend fun getAllMenuItems(): List<MenuItemEntity>
}

@Database(entities = [MenuItemEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun menuItemDao(): MenuItemDao
}
