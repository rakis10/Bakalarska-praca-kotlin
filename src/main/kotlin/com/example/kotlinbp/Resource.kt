package com.example.kotlinbp
import com.example.kotlinbp.models.Withdrawal
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class Resource( val withdrawalService: WithdrawalService) {

        @GetMapping("/")
        fun withdrawals(): List<Withdrawal?>? {
                return withdrawalService.getWithdrawals()
        }

        @GetMapping(value = ["/{id}"])
        fun getSingleTransaction(@PathVariable("id") id: String?): Withdrawal? {
                //return  id;
                return withdrawalService.getWithdrawal(id!!)
        }

        @DeleteMapping(value = ["/{id}"])
        fun deleteWithdrawal(@PathVariable("id") id: String?): ResponseEntity<*>? {
                return withdrawalService.deleteWithdrawal(id!!)
        }

        @PostMapping("/")
        fun addWithdrawal(@RequestBody withdrawal: Withdrawal?): ResponseEntity<*>? {
                return withdrawalService.createWithdrawal(withdrawal!!)
        }

        @PutMapping(value = ["/{id}"])
        fun updateWithdrawal(@PathVariable("id") id: String?, @RequestBody withdrawal: Withdrawal?): ResponseEntity<*>? {
                return withdrawalService.updateWithdrawal(id, withdrawal!!)
        }


}
