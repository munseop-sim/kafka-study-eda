package ms2709.kafka.api.controller

import ms2709.kafka.api.model.CouponDto
import ms2709.kafka.api.model.CouponIssueRequest
import ms2709.kafka.coupon.model.ResolvedCoupon
import ms2709.kafka.coupon_usecase.CouponIssueHistoryUseCase
import ms2709.kafka.coupon_usecase.ListUsableCouponsUseCase
import ms2709.kafka.coupon_usecase.RequestCouponIssueUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/coupons")
class CouponController (
    private val requestCouponIssueUseCase: RequestCouponIssueUseCase,
    private val couponIssueHistoryUseCase: CouponIssueHistoryUseCase,
    private val listUsableCouponsUseCase: ListUsableCouponsUseCase
){
    @PostMapping
    fun issueCoupon(@RequestBody request: CouponIssueRequest): ResponseEntity<String> {
        if (!couponIssueHistoryUseCase.isFirstRequestFromUser(request.couponEventId!!, request.userId!!)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Already tried to issue a coupon\n");
        }

        if(!couponIssueHistoryUseCase.hasRemainingCoupon(request.couponEventId!!)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not enough available coupons\n");
        }

        requestCouponIssueUseCase.queue(request.couponEventId!!, request.userId!!)
        return ResponseEntity.status(HttpStatus.OK).body("Successfully Issued\n");
    }

    @GetMapping
    fun listUsableCoupons(@RequestParam(name = "userId", defaultValue = "0", required = false) userId:Long):ResponseEntity<List<CouponDto>> {
        return listUsableCouponsUseCase.listByUserId(userId).mapNotNull {
            it ?: return@mapNotNull null
            CouponDto(
                id = it.coupon.id!!,
                displayName = it.couponEvent.displayName ?: "",
                expiresAt = it.couponEvent.expiresAt!!
            )
        }.run {
            ResponseEntity.ok(this)
        }
    }
}
