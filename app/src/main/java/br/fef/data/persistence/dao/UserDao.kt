package br.fef.data.persistence.dao

import androidx.room.*
import br.fef.data.persistence.entity.User

@Dao
interface UserDao {

    @Delete
    fun delete(user: User)

    @Query("DELETE FROM user")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Query("SELECT * FROM user")
    fun queryAll(): List<User>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(user: User)

}