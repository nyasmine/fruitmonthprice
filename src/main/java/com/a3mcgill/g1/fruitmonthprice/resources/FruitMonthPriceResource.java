package com.a3mcgill.g1.fruitmonthprice.resources;

import ch.qos.logback.classic.boolex.JaninoEventEvaluator;
import com.a3mcgill.g1.fruitmonthprice.FruitMonthPrice;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/fruitprice")
public class FruitMonthPriceResource {
    private Map<String, Integer> monthToIndex;
    private Map<String, Map<Integer, Double>> fruitToPrice;

    public FruitMonthPriceResource() {
        monthToIndex = new HashMap<>();
        fruitToPrice = new HashMap<>();
        loadDatabase();
    }

    //Method to post response
    @RequestMapping("/{fruit}/{month}")
    public FruitMonthPrice fruitMap(@PathVariable("fruit") String fruits, @PathVariable("month") String month) {
        int monthIndex = monthToIndex.get(month);
        Map<Integer,Double> myFinalMap = fruitToPrice.get(fruits);

        FruitMonthPrice myFruitMonthPriceResponse = new FruitMonthPrice(fruits, month, myFinalMap.get(monthIndex));

        return myFruitMonthPriceResponse;
    }


    private void loadDatabase() {
        int idx = 0;
        try {
            String filePath = "src/main/resources/fruitlist.csv";
            List<String> allLines = Files.readAllLines(Paths.get(filePath));

            for (String fruitRow : allLines) {
                String[] fruitRowArray = fruitRow.split(",");

                //Extract Month Names
                if(idx == 0){
                    for(int i = 1; i < fruitRowArray.length; i++){
                        monthToIndex.put(fruitRowArray[i], i);
                    }
                    idx++;
                    continue;
                }
                String fruitName = fruitRowArray[0];
                Map<Integer, Double> tempIndexToPrice = new HashMap<>();
                for(int i = 1; i < fruitRowArray.length; i++){
                    tempIndexToPrice.put(i, Double.parseDouble(fruitRowArray[i]));
                }
                fruitToPrice.put(fruitName, tempIndexToPrice);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}



