package app.delish.data.plan.repository

import com.elbehiry.model.MealsPlan
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMealPlanRepositoryImpl @Inject constructor(
    private val mealPlanRepository: MealPlanRepository
) : GetMealPlanRepository {

    override fun getMealPlan(): MealsPlan {
        return mealPlanRepository.getMealPlan() // Ensure this method exists in MealPlanRepository
    }