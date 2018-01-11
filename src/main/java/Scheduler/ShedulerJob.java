package Scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import java.io.IOException;
import java.sql.SQLException;

import Main.App;

public class ShedulerJob implements Job {

    public void execute(JobExecutionContext jExeCtx)  {
        try {
            App.start(false);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

