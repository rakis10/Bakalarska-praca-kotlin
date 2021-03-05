package com.example.kotlinbp.models

import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Withdrawal (
    var domestic : Boolean,
    var id: String ,
    var iban: String? ,
    var state: State?,
    var price: Long?,
    var money: List<Money>?
){


}