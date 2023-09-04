package com.calculator.evaluation_4.dataView;

import com.calculator.evaluation_4.dataModel.DataModel
import com.calculator.evaluation_4.R;



public class DataView {
    companion object{
        var list = listOf<DataModel>(DataModel(R.string.offer1,R.drawable.frame),
            DataModel(R.string.offer2,R.drawable.frame),
            DataModel(R.string.offer3,R.drawable.frame),
            DataModel(R.string.offer4,R.drawable.frame),
            DataModel(R.string.offer5,R.drawable.frame))
    }
    fun loadOffers():List<DataModel>{
        return list
    }
}
