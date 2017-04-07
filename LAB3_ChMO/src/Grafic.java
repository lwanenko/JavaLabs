 
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
 
public class Grafic {
  public static void main(String[] args) {
    XYSeries series = new XYSeries("y=L(x)");
    Lagrang l = new Lagrang();
	
    for(float i = 10; i < 60; i+=0.01){
      series.add(i, l.l(i));
    }
 
    XYDataset xyDataset = new XYSeriesCollection(series);
    JFreeChart chart = ChartFactory
        .createXYLineChart("y = L(x)", "x", "y",
                           xyDataset, 
                           PlotOrientation.VERTICAL,
                           true, true, true);
    JFrame frame = 
        new JFrame("MinimalStaticChart");
    // Помещаем график на фрейм
    frame.getContentPane()
        .add(new ChartPanel(chart));
    frame.setSize(400,300);
    frame.show();
  }
}