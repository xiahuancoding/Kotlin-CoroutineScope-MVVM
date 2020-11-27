package com.example.reviewmycp.model

data class UserMoneyModel(

    val totalProfit:String,
    val balance:String,
    val usableActiveDot:Double,
    val financeActiveDot:Double,
    val order_data:OrderNumber


)

data class OrderNumber(
     val wait_pay_num:String,
     val wait_rec_num:String,
     val wait_eva_num:String,
     val wait_sales_num:String
)