package sa.factory.app1290146.model

import kotlinx.serialization.Serializable

@Serializable
data class FormulaAlgorithmSpec(
    val id: String,
    val title: String,
    val description: String,
    val priceMinorUnits: Long,
    val iconVector: String = "verified"
)

@Serializable
data class TikerFormulaPackage(
    val requestId: String,
    val items: List<FormulaAlgorithmSpec>,
    val customerPhone: String,
    val totalAmountMinorUnits: Long,
    val status: TikerFormulaPackageStatus = TikerFormulaPackageStatus.PENDING,
    val createdAtEpochMs: Long = System.currentTimeMillis()
)

enum class TikerFormulaPackageStatus {
    PENDING,
    ACCEPTED,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED
}
