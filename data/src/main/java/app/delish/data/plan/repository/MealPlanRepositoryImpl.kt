package app.delish.data.plan.repository

import app.delish.data.plan.remote.MealPlanDataSource
import com.elbehiry.model.MealsPlan
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealPlanRepositoryImpl @Inject constructor(
    private val mealPlanDataSource: MealPlanDataSource
) : MealPlanRepository, GetMealPlanRepository { // Implements both

    override fun getMealPlan(): MealsPlan {
        return mealPlanDataSource.getMealPlan() // Ensure this method exists in MealPlanDataSource
    }
}