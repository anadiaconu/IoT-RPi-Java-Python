

package graphdynamicplotting;

import java.net.*;
import java.io.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

public   class FlameSensorChart extends ChartPanel implements Runnable
{
private   static TimeSeries timeSeries;
private   long value = 0;

public FlameSensorChart (String chartContent, String title, String yaxisName)
{
    super (createChart (chartContent, title, yaxisName));
}

private   static JFreeChart createChart (String chartContent, String title, String yaxisName) {
// Create the timing diagram object
    timeSeries = new TimeSeries (chartContent, Millisecond. class);
    TimeSeriesCollection timeseriescollection = new TimeSeriesCollection (timeSeries);
    JFreeChart jfreechart = ChartFactory.createTimeSeriesChart(title, "The time (in seconds)", yaxisName, timeseriescollection, true, true, false);
    XYPlot xyplot = jfreechart.getXYPlot ();
// Vertical coordinate set
    ValueAxis valueaxis = xyplot.getDomainAxis ();
// Automatically set data-axis data range
    valueaxis.setAutoRange (true);
// Data axis fixed data range 30s
    valueaxis.setFixedAutoRange (30000D);

    valueaxis = xyplot.getRangeAxis ();
// Valueaxis.setRange (0.0D, 200D);

    return jfreechart;
}

public void run ()
{

    

    try{

    ServerSocket server = new ServerSocket(5456);



    while(true)
    {
           Socket tempSocket = server.accept();
            DataInputStream dis = new DataInputStream(tempSocket.getInputStream());

            String data = dis.readLine();

            String Client = data.split(" ")[0];
            
        
                /* Socket connectionSocket = server.accept();
                 BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                 String Client = inFromClient.readLine();*/

            timeSeries.add (new Millisecond (), Double.parseDouble(Client));
            Thread.sleep (30);
       
        
    }

    }
    catch (Exception e) {}


}



private long randomNum ()
{
    System.out.println ((Math.random () * 20 + 80));
    return (long) (Math.random () * 20 + 80);
}



}