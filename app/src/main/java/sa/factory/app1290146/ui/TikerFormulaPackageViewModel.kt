package sa.factory.app1290146.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import sa.factory.app1290146.data.TikerFormulaPackageRepository
import sa.factory.app1290146.model.TikerFormulaPackage
import sa.factory.app1290146.model.FormulaAlgorithmSpec
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TikerFormulaPackageViewModel(
    private val repository: TikerFormulaPackageRepository
) : ViewModel() {
    val catalog: StateFlow<List<FormulaAlgorithmSpec>> = repository.getAvailableCatalog()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val activeRequest: StateFlow<TikerFormulaPackage?> = repository.getActiveRequest()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun submitNewOrder(request: TikerFormulaPackage) {
        viewModelScope.launch {
            repository.submitRequest(request)
        }
    }
}
