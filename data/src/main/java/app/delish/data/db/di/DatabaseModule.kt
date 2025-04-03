package app.delish.data.db.di

import android.content.Context
import androidx.room.Room
import app.delish.data.db.Constants
import app.delish.data.db.DelishDataBase
import app.delish.data.db.JsonConverter
import app.delish.data.db.MIGRATIONS
import app.delish.data.db.recipes.dao.CuisineDao
import app.delish.data.db.recipes.dao.IngredientDao
import app.delish.data.db.recipes.dao.RecipesDao
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    @Suppress("SpreadOperator")
    fun provideDelishDatabase(@ApplicationContext context: Context): DelishDataBase {
        return Room.databaseBuilder(
            context,
            DelishDataBase::class.java,
            Constants.DATABASE_NAME
        ).addMigrations(*MIGRATIONS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRecipesTable(delishDataBase: DelishDataBase): RecipesDao {
        return delishDataBase.recipesTable
    }

    @Provides
    @Singleton
    fun provideCuisineDao(delishDataBase: DelishDataBase): CuisineDao {
        return delishDataBase.cuisineDao
    }

    @Provides
    @Singleton
    fun provideIngredientDao(delishDataBase: DelishDataBase): IngredientDao {
        return delishDataBase.ingredientDao
    }

    @Provides
    @Singleton
    fun provideJsonConverter(moshi: Moshi): JsonConverter = JsonConverter(moshi)
}
