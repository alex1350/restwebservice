package com.example.essai;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class CarRental {
    public List<Car> cars = new ArrayList<Car>();

    public CarRental(){
        cars.add(new Car("11AA22","Ferrari", 100));
        cars.add(new Car("11AA12","Ferrari", 1000));
    }

    @GetMapping("/cars")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Car> cars(){
        return cars;
    }

    @GetMapping("/cars/{plateNumber}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Car aCar(@PathVariable("plateNumber") String plateNumber) throws Exception {
        for (Car car : cars){
            if(car.getPlateNumber().equals(plateNumber)){
                return car;
            }
        }
        return null;
    }

    @PutMapping(value="/voiure/{plateNumber}")
    @ResponseStatus(HttpStatus.OK)
    public void rent(
            @PathVariable("plateNumber") String plateNumber,
            @RequestParam(value="rent", required = true)boolean rent,
            @RequestBody Dates dates) throws Exception {

        Car car = this.aCar(plateNumber);
        if(car != null){
            car.setRented(true);
        }
    }
}
