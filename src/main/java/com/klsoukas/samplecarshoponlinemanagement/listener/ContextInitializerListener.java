
package com.klsoukas.samplecarshoponlinemanagement.listener;

import com.klsoukas.samplecarshoponlinemanagement.model.CarBean;
import com.klsoukas.samplecarshoponlinemanagement.model.CarDao;
import com.klsoukas.samplecarshoponlinemanagement.model.CarDaoImpl;
import com.klsoukas.samplecarshoponlinemanagement.util.BackgroundContextAttributeUpdate;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class ContextInitializerListener implements ServletContextListener {

    ScheduledExecutorService scheduledSingleThreadExecutor;
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        
        scheduledSingleThreadExecutor = Executors.newSingleThreadScheduledExecutor();
        

        ServletContext sc = sce.getServletContext();       
//        CarDao carDao = new CarDaoImpl();
//        List<CarBean> carlist = carDao.findAllCars();
//        sc.setAttribute("carList",carlist);
        
       scheduledSingleThreadExecutor.scheduleAtFixedRate(new BackgroundContextAttributeUpdate(sc), 0, 15, TimeUnit.SECONDS);
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        scheduledSingleThreadExecutor.shutdownNow();
    }
}
