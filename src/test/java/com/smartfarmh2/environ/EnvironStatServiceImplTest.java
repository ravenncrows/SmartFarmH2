package com.smartfarmh2.environ;

import com.smartfarmh2.product.Product;
import com.smartfarmh2.product.ProductDao;
import com.smartfarmh2.product.ProductService;
import com.smartfarmh2.product.ProductServiceImpl;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.Mockito.*;
/**
 * Created by Chertpong on 9/11/2016.
 */
public class EnvironStatServiceImplTest {

    @Test
    public void calculateStatOfDayShouldCallEnvironServiceOnce(){
        Environ environDayOne = mock(Environ.class);
        Environ environ2DayOne = mock(Environ.class);
        Environ environDayTwo = mock(Environ.class);
        //Day 1
        when(environDayOne.getHumid()).thenReturn(2.0);
        when(environDayOne.getSoil()).thenReturn(2.0);
        when(environDayOne.getTemp()).thenReturn(2.0);
        when(environ2DayOne.getHumid()).thenReturn(1.0);
        when(environ2DayOne.getSoil()).thenReturn(2.0);
        when(environ2DayOne.getTemp()).thenReturn(3.0);
        //Day 2
        when(environDayTwo.getHumid()).thenReturn(10.0);
        when(environDayTwo.getSoil()).thenReturn(22.0);
        when(environDayTwo.getTemp()).thenReturn(30.0);
        //Mock EnvironService
        EnvironService environService = mock(EnvironService.class);
        LocalDate now = LocalDateTime.now().toLocalDate();
        LocalDateTime today24 = LocalDateTime.of(now,LocalDateTime.now().withHour(23).withMinute(59).withSecond(59).withNano(999_999_999).toLocalTime());
        LocalDateTime today0 =  LocalDateTime.of(now, LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0).toLocalTime());
        when(environService.findByCreatedDateBetween(today0,today24)).thenReturn(Arrays.asList(environDayOne,environ2DayOne));

        EnvironStatServiceImpl environStatService = new EnvironStatServiceImpl();

        environStatService.setEnvironService(environService);

        //Result
//        EnvironStat environStat = new EnvironStat();
//        environStat.setAverageSoil(2.0);
//        verify(environService,times(1));
        assertThat(environStatService.calculateStatOfDay(now).getAverageSoil(),is(2.0));
        assertThat(environStatService.calculateStatOfDay(now).getAverageHumid(),is(1.5));
        assertThat(environStatService.calculateStatOfDay(now).getAverageTemp(),is(2.5));
    }
}
