package app.delish.data.plan.repository

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMealPlanRepositoryImpl @Inject constructor(
    private val mealPlanRepository: MealPlanRepository
) : GetMealPlanRepository {

    override fun getMealPlan(): MealPlan {
        return mealPlanRepository.getMealPlan()
    }
}