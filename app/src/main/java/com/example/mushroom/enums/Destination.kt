package com.example.mushroom.enums

import com.example.mushroom.R

enum class Destination(
    val route: String,
    val label: String,
    val icon: Int,
    val contentDescription: String
) {
    NOTIFICATIONS("notifications", "notif", R.drawable.resource_default, "Notifications"),
    TASK_HISTORY("history_of_tasks", "h_t", R.drawable.errorswarn, "History of Tasks"),
    START_AUTO_COLLECT("sbordisabled","collect",R.drawable.sbordisabled,"Start Auto Collect"),
    START_TESTING("start_testing","test", R.drawable.test,"Start Testing")
}
