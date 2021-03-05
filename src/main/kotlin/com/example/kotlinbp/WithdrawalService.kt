package com.example.kotlinbp

import com.example.kotlinbp.models.Withdrawal
import com.example.kotlinbp.models.WithdrawalRepo
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class WithdrawalService(val withdrawalRepo: WithdrawalRepo) {
    fun getWithdrawals(): List<Withdrawal?>? {
        return withdrawalRepo.findAll()
    }
    fun updateWithdrawal(id: String?, withdrawal: Withdrawal): ResponseEntity<*>? {
        withdrawal.id= id.toString()
        withdrawalRepo.save(withdrawal)
        return ResponseEntity<Any>(withdrawal, HttpStatus.OK)
    }

    fun deleteWithdrawal(id: String): ResponseEntity<*>? {
        withdrawalRepo.deleteById(id)
        return ResponseEntity<Any>(HttpStatus.OK)
    }
    fun getWithdrawal(id: String): Withdrawal? {
        //val withdrawal: Optional<Withdrawal?> = withdrawalRepo.findById(id)
        // isPresent
        return withdrawalRepo.findById(id).get()
    }

    fun createWithdrawal(withdrawal: Withdrawal): ResponseEntity<*>? {
        withdrawalRepo.save(withdrawal)
        return ResponseEntity<Any>(withdrawal, HttpStatus.CREATED)
    }
}