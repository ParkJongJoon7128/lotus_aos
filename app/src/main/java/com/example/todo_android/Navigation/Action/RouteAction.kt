package com.example.todo_android.Navigation.Action

import android.graphics.Color
import androidx.navigation.NavHostController
import com.example.todo_android.Navigation.NAV_ROUTE
import com.example.todo_android.Response.CategoryResponse.ReadCategoryResponse

// 컴포즈 네비게이션 라우트 액션
class RouteAction(navHostController: NavHostController) {
    fun navigateTo(login: Any) {

    }

    //특정 라우트 이동
    val navTo: (NAV_ROUTE) -> Unit = { route ->
        navHostController.navigate(route.routeName)
    }
    // 뒤로가기 이동
    val goBack: () -> Unit = {
        navHostController.navigateUp()
    }

    val customNavto: (NAV_ROUTE, String, String, Int) -> Unit = { route, name, id, color ->
        navHostController.navigate("${route.routeName}/$name/$id/$color")
    }
}