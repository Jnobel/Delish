package app.delish.data.di

import app.delish.data.db.recipes.dao.CuisineDao
import app.delish.data.db.recipes.dao.IngredientDao
import app.delish.data.db.recipes.dao.RecipesDao
import app.delish.data.home.local.HomeLocalDataSource
import app.delish.data.home.local.HomeLocalDataSourceImpl
import app.delish.data.home.remote.HomeDataSource
import app.delish.data.home.remote.HomeRemoteDataSource
import app.delish.data.home.repository.HomeRepository
import app.delish.data.home.repository.HomeRepositoryImpl
import app.delish.data.recipes.local.RecipesLocalDataSource
import app.delish.data.recipes.local.RecipesLocalDataSourceImpl
import app.delish.data.recipes.remote.RecipesDataSource
import app.delish.data.recipes.remote.SearchRecipesDataSource
import app.delish.data.recipes.repository.RecipesRepository
import app.delish.data.recipes.repository.RecipesRepositoryImpl
import app.delish.data.remote.DelishApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RecipesModule {

    @Binds
    @Singleton
    abstract fun bindHomeRepository(
        homeRepositoryImpl: HomeRepositoryImpl
    ): HomeRepository

    @Binds
    @Singleton
    abstract fun bindRecipesRepository(
        recipesRepositoryImpl: RecipesRepositoryImpl
    ): RecipesRepository

    @Binds
    @Singleton
    abstract fun bindHomeLocalDataSource(
        homeLocalDataSourceImpl: HomeLocalDataSourceImpl
    ): HomeLocalDataSource

    @Binds
    @Singleton
    abstract fun bindRecipesLocalDataSource(
        recipesLocalDataSourceImpl: RecipesLocalDataSourceImpl
    ): RecipesLocalDataSource

    @Binds
    @Singleton
    abstract fun bindRecipesDataSource(
        searchRecipesDataSource: SearchRecipesDataSource
    ): RecipesDataSource

    companion object {
        @Provides
        @Singleton
        fun provideHomeDataSource(
            api: DelishApi,
            @Named("INGREDIENTS_DATA_URL") ingredientsUrl: String,
            @Named("CUISINES_DATA_URL") cuisinesUrl: String
        ): HomeDataSource = HomeRemoteDataSource(api, ingredientsUrl, cuisinesUrl)

        @Provides
        @Singleton
        fun provideRecipesDataSource(
            api: DelishApi,
            @Named("SPOONACULAR_KEY") spoonacularKey: String
        ): RecipesDataSource = SearchRecipesDataSource(api, spoonacularKey)
    }
}