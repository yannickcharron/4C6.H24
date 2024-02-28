package ca.qc.cstj.noty.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import ca.qc.cstj.noty.data.repositories.NoteRepository
import ca.qc.cstj.noty.models.Note
import java.util.concurrent.Executors

@Database(entities = [Note::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun noteRepository(): NoteRepository

    //companion object => partie statique de la classe
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "noty"
            )
                // prepopulate the database after onCreate was called
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        ioThread {
                            //insert the data on the IO Thread
                        }
                    }
                })
                .build()

        //val PREPOPULATE_DATA = listOf()

        /**
         * Utility method to run blocks on a dedicated background thread, used for io/database work.
         */
        private val IO_EXECUTOR = Executors.newSingleThreadExecutor()
        fun ioThread(f: () -> Unit) {
            IO_EXECUTOR.execute(f)
        }
    }
}
