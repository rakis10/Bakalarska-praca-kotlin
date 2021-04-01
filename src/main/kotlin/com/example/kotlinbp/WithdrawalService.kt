package com.example.kotlinbp

import com.example.kotlinbp.models.Withdrawal
import com.example.kotlinbp.models.WithdrawalRepo
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class WithdrawalService(val withdrawalRepo: WithdrawalRepo) {
    fun getWithdrawals(): List<Withdrawal?>? {
        try {
           val withdrawals : MutableList<Withdrawal?> = withdrawalRepo.findAll();
            for (w in withdrawals  ){
                checkWithdrawal(w)
            }
        }catch (e : WithdrawalException){
            println(e.toString());
        }
        return withdrawalRepo.findAll()
    }

    private fun checkWithdrawal(w: Withdrawal?) {
        var checksum : Long = 0L
        for( m in w?.money!!){
            checksum += m.value * m.pieces
        }
        if (checksum != w.price){
            throw  WithdrawalException("Checksum of price failed")
        }
    }

    fun updateWithdrawal(id: String?, withdrawal: Withdrawal): ResponseEntity<*>? {
        withdrawal.id= id.toString()
        withdrawalRepo.save(withdrawal)
        return ResponseEntity<Any>(withdrawal, HttpStatus.OK)
    }

    fun deleteWithdrawal(id: String): ResponseEntity<*>? {
        checkExist(id)
        withdrawalRepo.deleteById(id)
        return ResponseEntity<Any>(HttpStatus.OK)
    }
    fun getWithdrawal(id: String): ResponseEntity<*>? {
        //val withdrawal: Optional<Withdrawal?> = withdrawalRepo.findById(id)
        // isPresent
        val w: Withdrawal
        w = try {
            checkExist(id)
            withdrawalRepo.findById(id).get()
        } catch (e: NoSuchElementException) {
            return ResponseEntity("No withdrawal with ID $id", HttpStatus.NOT_FOUND)
        } catch (e: WithdrawalException) {
            println(e.toString())
            return ResponseEntity("No withdrawal with ID + $id", HttpStatus.NOT_FOUND)
        }
        return ResponseEntity<Any>(w, HttpStatus.NOT_FOUND)
//        return withdrawalRepo.findById(id).get()
    }

    fun createWithdrawal(withdrawal: Withdrawal): ResponseEntity<*>? {
        var sum = 0L
        for (m in withdrawal.money!!) {
            sum += m.value + m.pieces
        }
        withdrawal.price = sum
        try {
            checkWithdrawal(withdrawal)
//            chechWithdrawal(withdrawal)
        } catch (e: WithdrawalException) {
            println(e.toString())
            return ResponseEntity<Any>(e, HttpStatus.NOT_ACCEPTABLE)
        }

        withdrawalRepo.save(withdrawal)
        return ResponseEntity<Any>(withdrawal, HttpStatus.CREATED)

//        withdrawalRepo.save(withdrawal)
//        return ResponseEntity<Any>(withdrawal, HttpStatus.CREATED)
    }
    fun checkExist(id: String){
        if (withdrawalRepo.findById(id).isEmpty()) {
            throw WithdrawalException("Checksum of price failed")
        }
    }
}