package sa.factory.app1290146.data

import sa.factory.app1290146.model.TikerFormulaPackage
import sa.factory.app1290146.model.FormulaAlgorithmSpec
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

interface TikerFormulaPackageRepository {
    fun getAvailableCatalog(): Flow<List<FormulaAlgorithmSpec>>
    fun getActiveRequest(): Flow<TikerFormulaPackage?>
    suspend fun submitRequest(request: TikerFormulaPackage): Result<String>
}

class InMemoryTikerFormulaPackageRepository : TikerFormulaPackageRepository {
    private val _catalog = MutableStateFlow<List<FormulaAlgorithmSpec>>(listOf(
        FormulaAlgorithmSpec("1", "حزمة معادلات تدقيق السيولة والأحجام الرياضية", "كود برمجي تكرشارت مخصص لحساب المتوسطات الحجمية التراكمية واختبار دقتها الفنية", 5000L, "verified"),
        FormulaAlgorithmSpec("2", "خوارزمية قياس مؤشرات الزخم المخصصة", "صيغة رياضية مبرمجة تقيس تسارع التدفقات السعرية خالية من أخطاء بناء الدوال في المنصة", 9500L, "bolt")
    ))
    private val _active = MutableStateFlow<TikerFormulaPackage?>(null)

    override fun getAvailableCatalog(): Flow<List<FormulaAlgorithmSpec>> = _catalog.asStateFlow()
    override fun getActiveRequest(): Flow<TikerFormulaPackage?> = _active.asStateFlow()

    override suspend fun submitRequest(request: TikerFormulaPackage): Result<String> {
        _active.value = request
        return Result.success(request.requestId)
    }
}
