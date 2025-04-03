package app.delish.domain.di

import app.delish.data.home.repository.HomeRepository
import app.delish.data.recipes.repository.RecipesRepository
import app.delish.domain.usecases.*
import app.delish.domain.usecases.recipes.bookmark.*
import app.delish.domain.usecases.recipes.information.GetRecipeInformationUseCase
import app.delish.domain.usecases.recipes.random.GetRandomRecipesUseCase
import app.delish.domain.usecases.search.SearchRecipesUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    @Singleton
    abstract fun bindGetAvailableCuisinesUseCase(
        useCase: GetAvailableCuisinesUseCase
    ): GetAvailableCuisinesUseCase

    @Binds
    @Singleton
    abstract fun bindGetIngredientsUseCase(
        useCase: GetIngredientsUseCase
    ): GetIngredientsUseCase

    @Binds
    @Singleton
    abstract fun bindInitHomeUseCase(
        useCase: InitHomeUseCase
    ): InitHomeUseCase

    @Binds
    @Singleton
    abstract fun bindSearchRecipesUseCase(
        useCase: SearchRecipesUseCase
    ): SearchRecipesUseCase

    @Binds
    @Singleton
    abstract fun bindGetRecipeInformationUseCase(
        useCase: GetRecipeInformationUseCase
    ): GetRecipeInformationUseCase

    @Binds
    @Singleton
    abstract fun bindSaveRecipeSuspendUseCase(
        useCase: SaveRecipeSuspendUseCase
    ): SaveRecipeSuspendUseCase
}