package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;

import kmeans.Kmeans;

public class SCatterPlotChartCK {
	ChartPanel frame1;
	JFreeChart chartas;
	//ArrayList<double[][]> datas;
	public  SCatterPlotChartCK(ArrayList<double[][]> datas){
		DefaultXYDataset xydataset = new DefaultXYDataset();
		/*ArrayList<Point> data=GetPoints.getPoints(1000);
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("请输入参数K");
    	int k = scanner.nextInt();
    	System.out.println("请输入"+k+"个初始核心位置");
        int[] as=new int[k];
        for(int i=0;i<k;i++){
        	System.out.print("第"+(i+1)+"个");
        	as[i]=scanner.nextInt();
        }
        long startTime = System.currentTimeMillis();  
        Kmeans.set_kernal(data,as);
        int numbers=Kmeans.k_means();  
        long endTime = System.currentTimeMillis();
        float seconds = (endTime - startTime) / 1000F;       
        System.out.println("优化算法花费时间（毫秒）"+Float.toString(seconds) );  */
		 
        //datas=Kmeans.getKmeansDatas();
		for(int i=0;i<datas.size();i++){
	    	   int n=i+1;
	    	   xydataset.addSeries(n, datas.get(i));
	    }
	       //xydataset.addSeries("第二种", data);

	       JFreeChart chart = ChartFactory.createScatterPlot("Kmeans—result", "x", "y",   //图标名称。目录轴的显示标签(X轴标题)。数值轴的显示标签(Y轴标题)
	                                              xydataset,              //数据集  
	                                              PlotOrientation.VERTICAL,    //图标方向：水平，垂直
	                                              true,    //是否显示图例
	                                              false,   //是否显示tooltip
	                                              false);  //是否制定url。
	   //    ChartFrame frame = new ChartFrame("散点图", chart, true);  
	       chart.setBackgroundPaint(Color.white);  //设置背景颜色  
	       chart.setBorderPaint(Color.GREEN);     //设置边框颜色
	       chart.setBorderStroke(new BasicStroke(1.5f));    //设置边框宽度(整个最外层的边框)
	       XYPlot xyplot = (XYPlot) chart.getPlot();    

	       xyplot.setBackgroundPaint(new Color(255, 253, 246));     // 设置图表背景颜色
	       //xyplot.setBackgroundPaint(new Color(255, 253, 246));
	       ValueAxis vaaxis = xyplot.getDomainAxis();    
	       vaaxis.setAxisLineStroke(new BasicStroke(1.5f));    

	       ValueAxis va = xyplot.getDomainAxis(0);    

	       va.setAxisLineStroke(new BasicStroke(1.5f)); // 坐标轴粗细    
	       va.setAxisLinePaint(new Color(215, 215, 215)); // 坐标轴颜色    
	       xyplot.setOutlineStroke(new BasicStroke(1.5f)); // 边框粗细    
	       va.setLabelPaint(new Color(10, 10, 10)); // 坐标轴标题颜色    
	       va.setTickLabelPaint(new Color(102, 102, 102)); // 坐标轴标尺值颜色    
	       ValueAxis axis = xyplot.getRangeAxis();     //对y轴操作  
	       axis.setAxisLineStroke(new BasicStroke(1.5f));    

	       XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer) xyplot    
	               .getRenderer();    
	       xylineandshaperenderer.setSeriesOutlinePaint(0, Color.WHITE); 
	       xylineandshaperenderer.setUseOutlinePaint(true);    
	       NumberAxis numberaxis = (NumberAxis) xyplot.getDomainAxis();    //
	       numberaxis.setAutoRangeIncludesZero(false);    
	       numberaxis.setTickMarkInsideLength(2.0F);    
	       numberaxis.setTickMarkOutsideLength(0.0F);    
	       numberaxis.setAxisLineStroke(new BasicStroke(1.5f));    
	       
	       frame1=new ChartPanel(chart,true);        //这里也可以用chartFrame,可以直接生成一个独立的Frame
	       chartas=chart;
	}
	public ChartPanel getChartPanel(){
		return frame1;
	}
	public JFreeChart getChart(){
		return chartas;
		
	}
}
