package com.anushka.circlecalc


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class CalcViewModelTest{

    private lateinit var calcViewModel: CalcViewModel
    private lateinit var calculations: Calculations

    @get:Rule //test 전 / 후에 정의된 코드들이 실행되도록 만든다.
    var instantTaskExecutorRule = InstantTaskExecutorRule() // liveData의 synchronous 하게 테스팅 가능하도록 하는 코드.

    @Before
    fun setUp() { //testing 전에 필요한 데이터를 세팅해두는 곳. 예를들어 Mockito를 사용해서 Mock데이터를 만들어 둘 수 있다.
        calculations = Mockito.mock(Calculations::class.java)
        Mockito.`when`(calculations.calculateArea(2.1)).thenReturn(13.8474)
        Mockito.`when`(calculations.calculateCircumference(1.0)).thenReturn(6.28)
        calcViewModel = CalcViewModel(calculations)
    }

    @Test
    fun calculateArea_radiusSent_UpdateLiveData(){
        calcViewModel.calculateArea(2.1)
        val result = calcViewModel.areaValue.value
        assertThat(result).isEqualTo("13.8474")
    }

    @Test
    fun calculateCircumference_radiusSent_UpdateLiveData(){
        calcViewModel.calculateCircumference(1.0)
        val result = calcViewModel.circumferenceValue.value
        assertThat(result).isEqualTo("6.28")
    }
}