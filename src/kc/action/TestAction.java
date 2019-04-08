package kc.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.servlet.ServletUtilities;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import canopy.Canopy;
import canopy.KesCanopy;
import kmeans.CanopyKmeans;
import kmeans.KMeansMain;
import kmeans.Kmeans;
import model.DataLod;
import model.GetPoints;
import model.Point;
import model.SCatterPlotChartCK;

@Controller
public class TestAction {

	@RequestMapping("/index")
	public String getIndexPage(Map<String,List> m){
		//System.out.println("testurl111122");
		return "home";
	}
	/*//柱状图
    @RequestMapping("/getChart")
    public String getColumnChart(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
            throws Exception {
    	System.out.println("testurl1111");
    	JFreeChart chart=new SCatterPlotChartCK(Kmeans.getCKDatas()).getChart();
        // 6. 将图形转换为图片，传到前台
        String fileName = ServletUtilities.saveChartAsJPEG(chart, 700, 400, null, request.getSession());
        String chartURL = request.getContextPath() + "/chart?filename=" + fileName;
        System.out.println("testurl"+chartURL);
        modelMap.put("chartURLBar", chartURL);
        return "test";
    }*/
        
    @RequestMapping("/girisIndex")     //iris数据分析页面
	public String getIrisPage(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap){   
		//System.out.println("testurl111122");
    	KesCanopy.pointss.clear();
    	//System.out.println("data长度1："+KesCanopy.pointss.size());
    	KesCanopy.loadData("D:/Schoolproject/date/bezdekIris.data");
    	modelMap.put("size", KesCanopy.pointss.size());
    	//System.out.println("data长度2："+KesCanopy.pointss.size());
    	KesCanopy.cluster();
    	int length=KesCanopy.getClusterNumber();
    	//System.out.println("canopy长度："+length);
    	modelMap.put("length",length);
		return "irisPage";
	}
    @RequestMapping("/gIrisCalculate")
	public String irisGrade(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception{
		String len=request.getParameter("len");
		String mat=request.getParameter("mat");
		//System.out.println("aaaaaaa:"+len+" "+mat);
		int[] a=new int[3];
		String[] array=len.split("\\s+");
		for(int i=0;i<3;i++){
			a[i]=Integer.valueOf(array[i]);
		}
		int[] b=new int[4];
		String[] array2=mat.split("\\s+");
		for(int i=0;i<4;i++){
			b[i]=Integer.valueOf(array2[i]);
		}
		
		ArrayList<double[][]> datas=new ArrayList<>();
		Map<String,Object> map=new HashMap<String,Object>();
		KMeansMain.loadData("D:/Schoolproject/date/bezdekIris.data");  
    	KMeansMain.pretreatment(KMeansMain.indata);//预处理
        
        long startTime = System.currentTimeMillis();
        KMeansMain.set_kernal(KMeansMain.data, a[0], a[1], a[2]);//设置初始核心 
        int number=KMeansMain.k_means();  
        long endTime = System.currentTimeMillis();
        float seconds = (endTime - startTime) / 1000F;       
        System.out.println("基本算法花费时间（毫秒）"+Float.toString(seconds) );
        ArrayList<double[]> a1=new ArrayList<>();
        ArrayList<double[]> b1=new ArrayList<>();
        ArrayList<double[]> c1=new ArrayList<>();
        for(int i=0;i<KMeansMain.data.size();i++){
        	if(KMeansMain.data.get(i)[4]==1.0){
            	a1.add(KMeansMain.data.get(i));
            }else if(KMeansMain.data.get(i)[4]==2.0){
            	b1.add(KMeansMain.data.get(i));
            }else if(KMeansMain.data.get(i)[4]==3.0){
            	c1.add(KMeansMain.data.get(i));
            }
        }
        double[][] dataFormat1 = new double[2][a1.size()];
        for(int j=0; j<a1.size(); j++){
            dataFormat1[0][j] = a1.get(j)[0];
            dataFormat1[1][j] = a1.get(j)[1];
        }
        datas.add(dataFormat1);
        double[][] dataFormat2 = new double[2][b1.size()];
        for(int j=0; j<b1.size(); j++){
            dataFormat2[0][j] = b1.get(j)[0];
            dataFormat2[1][j] = b1.get(j)[1];
        }
        datas.add(dataFormat2);
        double[][] dataFormat3 = new double[2][c1.size()];
        for(int j=0; j<c1.size(); j++){
            dataFormat3[0][j] = c1.get(j)[0];
            dataFormat3[1][j] = c1.get(j)[1];
        }
        datas.add(dataFormat3);
        modelMap.put("time", Float.toString(seconds));
        modelMap.put("number", number);
		JFreeChart chart=new SCatterPlotChartCK(datas).getChart();
        // 6. 将图形转换为图片，传到前台
        String fileName = ServletUtilities.saveChartAsJPEG(chart, 700, 400, null, request.getSession());
        String chartURL = request.getContextPath() + "/chart?filename=" + fileName;
        //System.out.println("testurl"+chartURL);
        long startTime1 = System.currentTimeMillis();
        KesCanopy.loadData("D:/Schoolproject/date/bezdekIris.data");
    	KesCanopy.cluster();
        CanopyKmeans.loadData("D:/Schoolproject/date/bezdekIris.data");
        CanopyKmeans.pretreatment(CanopyKmeans.indata);//预处理
    	CanopyKmeans.set_kernal(CanopyKmeans.data,b[0],b[1],b[2],b[3]);
        int cnumber=CanopyKmeans.k_means(); 
        long endTime2 = System.currentTimeMillis();
        float times = (endTime2 - startTime1) / 1000F; 
        ArrayList<double[][]> cdatas=CanopyKmeans.show_category();
        System.out.println("算法迭代次数："+cnumber);
        JFreeChart chart2=new SCatterPlotChartCK(cdatas).getChart();
        // 6. 将图形转换为图片，传到前台
        String fileName2 = ServletUtilities.saveChartAsJPEG(chart2, 700, 400, null, request.getSession());
        String chartURL2 = request.getContextPath() + "/chart?filename=" + fileName2;
        modelMap.put("ctime", times);
        modelMap.put("cnumber", cnumber);
        modelMap.put("StudentKURLBar", chartURL);
        modelMap.put("StudentCKURLBar", chartURL2);
        
		return "studentResult";
	}
    @RequestMapping("/gsIndex")  //成绩数据分析页面
   	public String getStudentPage(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap){
    	ArrayList<Point> datae=DataLod.loadData("D:/Schoolproject/date/studentdata.xlsx");
    	Canopy canopy = new Canopy(datae);
        canopy.cluster();

        //获取canopy数目
        int clusterNumber = canopy.getClusterNumber();
        modelMap.put("length", clusterNumber);
        modelMap.put("size", datae.size());
   		//System.out.println("testurl111122");
   		return "gradePage";
   	}
    @RequestMapping("/gdataIndex")  //随机生成数据分析页面
	public String getdataPage(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap){
		//System.out.println("testurl111122");
    	ArrayList<Point> datae=GetPoints.getPoints(1000);
    	Canopy canopy = new Canopy(datae);
        canopy.cluster();
        //获取canopy数目
        int clusterNumber = canopy.getClusterNumber();
        modelMap.put("length", clusterNumber);
		return "dataPage";
	}
    @RequestMapping("/gDataCalculate")
	public String dataGrade(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws IOException{
		int len=Integer.valueOf(request.getParameter("len"));
		String mat=request.getParameter("mat");
		//System.out.println("aaaaaaa:"+len+" "+mat);
		int[] a=new int[len];
		String[] array=mat.split("\\s+");
		for(int i=0;i<len;i++){
			a[i]=Integer.valueOf(array[i]);
		}
		int lent=Integer.valueOf(request.getParameter("lent"));
		String matt=request.getParameter("matt");
		int[] b=new int[lent];
		System.out.println("aaaa"+lent);
		String[] array2=matt.split("\\s+");
		for(int i=0;i<lent;i++){
			b[i]=Integer.valueOf(array2[i]);
		}
		ArrayList<Point> cdata=Kmeans.setPoints();
		Kmeans.init_kernal.clear();
		long startTime = System.currentTimeMillis();
		Kmeans.set_kernal(Kmeans.data,a);
        int number=Kmeans.k_means();  
        long endTime = System.currentTimeMillis();
        float seconds = (endTime - startTime) / 1000F;  
        ArrayList<double[][]> datas=Kmeans.show_category();
        System.out.println("算法迭代次数："+number);
        modelMap.put("time", Float.toString(seconds));
        modelMap.put("number", number);
		JFreeChart chart=new SCatterPlotChartCK(datas).getChart();
        // 6. 将图形转换为图片，传到前台
        String fileName = ServletUtilities.saveChartAsJPEG(chart, 700, 400, null, request.getSession());
        String chartURL = request.getContextPath() + "/chart?filename=" + fileName;
        //System.out.println("testurl"+chartURL);
        
        Kmeans.init_kernal.clear();
        long startTime1 = System.currentTimeMillis();
        Canopy canopy = new Canopy(cdata);
        canopy.cluster();
        Kmeans.set_kernal(Kmeans.data,b);
        int cnumber=Kmeans.k_means();  
        long endTime1 = System.currentTimeMillis();
        float ctime = (endTime1 - startTime1) / 1000F; 
        ArrayList<double[][]> cdatas=Kmeans.show_category();
        System.out.println("算法迭代次数："+cnumber);
        modelMap.put("ctime", Float.toString(ctime));
        modelMap.put("cnumber", cnumber);
        JFreeChart chart2=new SCatterPlotChartCK(cdatas).getChart();
        // 6. 将图形转换为图片，传到前台
        String fileName2 = ServletUtilities.saveChartAsJPEG(chart2, 700, 400, null, request.getSession());
        String chartURL2 = request.getContextPath() + "/chart?filename=" + fileName2;
        modelMap.put("StudentKURLBar", chartURL);
        modelMap.put("StudentCKURLBar", chartURL2);
		return "studentResult";
	}
   
	@RequestMapping("/gCalculate")
	public String lenStuGrade(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws IOException{
		int len=Integer.valueOf(request.getParameter("len"));
		String mat=request.getParameter("mat");
		//System.out.println("aaaaaaa:"+len+" "+mat);
		int[] a=new int[len];
		String[] array=mat.split("\\s+");
		for(int i=0;i<len;i++){
			a[i]=Integer.valueOf(array[i]);
		}
		int lent=Integer.valueOf(request.getParameter("lent"));
		String matt=request.getParameter("matt");
		int[] b=new int[lent];
		System.out.println("aaaa"+lent);
		String[] array2=matt.split("\\s+");
		for(int i=0;i<lent;i++){
			b[i]=Integer.valueOf(array2[i]);
		}
		Kmeans.init_kernal.clear();	
		ArrayList<Point> sdata=DataLod.loadData("D:/Schoolproject/date/studentdata.xlsx");
		Kmeans.data=sdata;
		long startTime = System.currentTimeMillis();
    	Canopy canopy = new Canopy(sdata);
        canopy.cluster();
		Kmeans.set_kernal(Kmeans.data,a);
        int number=Kmeans.k_means();  
        long endTime = System.currentTimeMillis();
        float time = (endTime - startTime) / 1000F; 
        ArrayList<double[][]> datas=Kmeans.show_category();
        System.out.println("算法迭代次数："+number);
        modelMap.put("time", Float.toString(time));
        modelMap.put("number", number);
		JFreeChart chart=new SCatterPlotChartCK(datas).getChart();
        // 6. 将图形转换为图片，传到前台
        String fileName = ServletUtilities.saveChartAsJPEG(chart, 700, 400, null, request.getSession());
        String chartURL = request.getContextPath() + "/chart?filename=" + fileName;
        //System.out.println("testurl"+chartURL);
        
        Kmeans.init_kernal.clear();
        Kmeans.data=DataLod.loadData("D:/Schoolproject/date/studentdata.xlsx");
        long startTime1 = System.currentTimeMillis();
        Kmeans.set_kernal(Kmeans.data,b);
        int cnumber=Kmeans.k_means();  
        long endTime1 = System.currentTimeMillis();
        float ctime = (endTime1 - startTime1) / 1000F; 
        ArrayList<double[][]> cdatas=Kmeans.show_category();
        System.out.println("算法迭代次数："+cnumber);
        modelMap.put("ctime", Float.toString(ctime));
        modelMap.put("cnumber", cnumber);
        JFreeChart chart2=new SCatterPlotChartCK(cdatas).getChart();
        // 6. 将图形转换为图片，传到前台
        String fileName2 = ServletUtilities.saveChartAsJPEG(chart2, 700, 400, null, request.getSession());
        String chartURL2 = request.getContextPath() + "/chart?filename=" + fileName2;
        modelMap.put("StudentKURLBar", chartURL);
        modelMap.put("StudentCKURLBar", chartURL2);
		return "studentResult";
	}
}
