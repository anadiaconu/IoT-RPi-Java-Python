/* Importing all the necessary modules*/

import java.sql.*;
import java.io.*;
import org.jfree.ui.*;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.*;
import org.jfree.data.jdbc.JDBCCategoryDataset;

public class FlameReading {
        public static void main(String[] args) throws Exception {
                String query = "SELECT `Serial`,`Reading` from `flamereading`"; // column names and table name is given here
                JDBCCategoryDataset dataset = new JDBCCategoryDataset(
                                "jdbc:mysql://localhost:3306/projectsensorreading", "com.mysql.jdbc.Driver",
                                "root", "");    //IP address of the server and the database name is given 

                {
                dataset.executeQuery(query);
                JFreeChart chart = ChartFactory.createBarChart3D("Flame Reading Sensor", "Time Stamps", "Sensor Levels",
                                dataset, PlotOrientation.VERTICAL, true, true, false);   //Selecting AreaChart
                ChartPanel chartPanel = new ChartPanel(chart);
                chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));      //Selecting dimension
                ApplicationFrame f = new ApplicationFrame("Chart");
                f.setContentPane(chartPanel);
                f.pack();
                f.setVisible(true);
                }

        }
        
    
 
        
        
        

}
 
        
        
        
        
        