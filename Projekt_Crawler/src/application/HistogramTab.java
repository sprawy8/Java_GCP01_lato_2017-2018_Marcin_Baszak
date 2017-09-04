package application;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tab;

public class HistogramTab extends Tab {

	final BarChart<String,Number> bc;
	
	public HistogramTab()
	{
		super();
		setText("Histogram");
		
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
		
		bc= new BarChart<String,Number>(xAxis,yAxis);
		bc.setTitle("Distribution of marks");
		xAxis.setLabel("Marks");
		xAxis.setTickLabelRotation(90);
		yAxis.setLabel("Count");

		
		
	}
	
	public void updateHistogram(CustomTabPane customTabPane)
	{
		XYChart.Series series1 = new XYChart.Series();
		series1.getData().add(new XYChart.Data("2.0",customTabPane.counter2()));
		series1.getData().add(new XYChart.Data("3.0",customTabPane.counter3()));
		series1.getData().add(new XYChart.Data("3.5",customTabPane.counter3andhalf()));
		series1.getData().add(new XYChart.Data("4.0",customTabPane.counter4()));
		series1.getData().add(new XYChart.Data("4.5",customTabPane.counter4andhalf()));
		series1.getData().add(new XYChart.Data("5.0",customTabPane.counter5()));
		
		
		
		bc.getData().clear();
		bc.getData().add(series1);
		this.setContent(bc);
	}
}
