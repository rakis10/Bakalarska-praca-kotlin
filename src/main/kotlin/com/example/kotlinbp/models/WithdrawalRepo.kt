package com.example.kotlinbp.models

import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface WithdrawalRepo : MongoRepository<Withdrawal?, String?> {
    override fun findById(id: String): Optional<Withdrawal?>
}