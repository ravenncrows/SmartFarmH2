package com.smartfarmh2.environ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EnvironStatServiceImpl implements EnvironStatService{
    @Autowired
    EnvironStatDao environStatDao;
    @Autowired
    EnvironService environService;

    @Override
    public EnvironStat create(EnvironStat environStat) {
        return environStatDao.create(environStat);
    }

    @Override
    public List<EnvironStat> list() {
        return environStatDao.list();
    }

    @Override
    public EnvironStat calculateStatOfCurrentHour() {
        LocalDateTime startDateTime = LocalDateTime.now().withMinute(0).withSecond(0).withNano(0);
        LocalDateTime finishDateTime = LocalDateTime.now().withMinute(59).withSecond(59).withNano(999999999);
        List<Environ> environList = environService.findByCreatedDateBetween(startDateTime, finishDateTime);
        EnvironStat environStat = createEnvironStatFromEnvironList(environList);
        environStat.setCreatedAt(LocalDateTime.now());
        return environStat;
    }

    @Override
    public EnvironStat calculateStatOfHour(LocalDateTime dateTime) {
        LocalDateTime finishDate = dateTime.plusHours(1).withMinute(0).withSecond(0).withNano(0);
        List<Environ> environList = environService.findByCreatedDateBetween(dateTime,finishDate);
        EnvironStat environStat = createEnvironStatFromEnvironList(environList);
        environStat.setCreatedAt(LocalDateTime.now());
        return environStat;
    }

    @Override
    public EnvironStat calculateStatOfDay(LocalDate date) {
        LocalDateTime startDate = LocalDateTime.of(date, LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).toLocalTime());
        LocalDateTime finishDate = LocalDateTime.of(date, LocalDateTime.now().withHour(23).withMinute(59).withSecond(59).toLocalTime());
        List<Environ> environList = environService.findByCreatedDateBetween(startDate,finishDate);
        EnvironStat environStat = createEnvironStatFromEnvironList(environList);
        environStat.setCreatedAt(LocalDateTime.now());
        return environStat;
    }

    @Override
    public EnvironStat createEnvironStatFromEnvironList(List<Environ> environList) {
        EnvironStat environStat = new EnvironStat();
        // return if there is nothing to calculate;
        if(environList.size() == 0) return environStat;

        Double tempSum = 0.0, humidSum = 0.0, soilSum = 0.0;
        for (Environ environ : environList) {
            // Set temperature
            if(environStat.getLowestTemp() > environ.getTemp())
                environStat.setLowestTemp(environ.getTemp());
            if(environStat.getHighestTemp() < environ.getTemp())
                environStat.setHighestTemp(environ.getTemp());
            tempSum += environ.getTemp();

            // Set humid
            if(environStat.getLowestHumid() > environ.getHumid())
                environStat.setLowestHumid(environ.getHumid());
            if(environStat.getHighestHumid() < environ.getHumid())
                environStat.setHighestHumid(environ.getHumid());
            humidSum += environ.getHumid();

            // Set soil
            if(environStat.getLowestSoil() > environ.getSoil())
                environStat.setLowestSoil(environ.getSoil());
            if(environStat.getHighestSoil() < environ.getSoil())
                environStat.setHighestSoil(environ.getSoil());
            soilSum += environ.getSoil();
        }
        environStat.setAverageTemp(tempSum / environList.size());
        environStat.setAverageHumid(humidSum / environList.size());
        environStat.setAverageSoil(soilSum / environList.size());
        return environStat;
    }
}
