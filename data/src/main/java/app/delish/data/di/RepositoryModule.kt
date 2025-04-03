package app.delish.data.di

import app.delish.data.home.repository.HomeRepository
import app.delish.data.home.repository.HomeRepositoryImpl
import app.delish.data.recipes.repository.RecipesRepository
import app.delish.data.recipes.repository.RecipesRepositoryImpl
import app.delish.data.plan.repository.MealPlanRepositoryImpl
import app.delish.data.plan.repository.MealPlanRepository
import app.delish.data.plan.repository.GetMealPlanRepository
import app.delish.data.plan.repository.GetMealPlanRepositoryImpl
import app.delish.data.pref.repository.DataStoreRepository
import app.delish.data.pref.repository.DataStoreOperations
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

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
    abstract fun bindMealPlanRepository(
        mealPlanRepositoryImpl: MealPlanRepositoryImpl
    ): MealPlanRepository

    @Binds
    @Singleton
    abstract fun bindGetMealPlanRepository(
        getMealPlanRepositoryImpl: GetMealPlanRepositoryImpl
    ): GetMealPlanRepository

    @Binds
    @Singleton
    abstract fun bindDataStoreOperations(
        dataStoreRepository: DataStoreRepository
    ): DataStoreOperations
}