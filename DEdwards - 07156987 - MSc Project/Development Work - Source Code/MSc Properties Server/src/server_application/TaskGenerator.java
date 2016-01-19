/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import interfaces.Server;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dwayne
 */
public class TaskGenerator extends TimerTask {

    private final ServerImpl server;
    private final Timer timer;
    private final Calendar date;

    public TaskGenerator(Server server, int time) {
        this.server = (ServerImpl) server;
        this.timer = new Timer();
        this.date = Calendar.getInstance();
        this.setDate();
        this.setSchedule(time);
    }

    @Override
    public void run() {
        System.out.println("Daily Tasks");
        Calendar today = Calendar.getInstance();
        server.processRentTransactions(today.getTime());
        server.processLeaseTransactions(today.getTime());
        if (today.get(Calendar.DAY_OF_MONTH) == 1) {
            try {
                Calendar lastDay = Calendar.getInstance();
                lastDay.set(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.getActualMaximum(Calendar.DAY_OF_MONTH));
                
                server.generateReport(today.getTime(), lastDay.getTime());
                server.processSalaryTransactions();
                System.out.println("Monthly Tasks");
            } catch (RemoteException ex) {
                Logger.getLogger(TaskGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void setDate() {
        date.set(Calendar.HOUR, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
    }
    
    private void setSchedule(int time) {
        timer.schedule(this, date.getTime(), time);
    }
}