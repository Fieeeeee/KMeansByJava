package model;

import java.awt.GridLayout;

import javax.swing.JFrame;

import kmeans.Kmeans;

public class MainTest {
	public static void main(String args[]) throws Exception{
		JFrame frame=new JFrame("K-means数据统计图");
		frame.setLayout(new GridLayout(1,1,10,10));
		//frame.add(new SCatterPlotChartCK(Kmeans.getKmeansDatas()).getChartPanel()); 
//		frame.add(new SCatterPlotChartCK(Kmeans.getCKDatas()).getChartPanel());           //添加柱形图
		frame.setBounds(50, 50, 800, 600);
		frame.setVisible(true);
	}
}
