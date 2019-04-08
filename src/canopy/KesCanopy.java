package canopy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import model.Points;

public class KesCanopy {
	public static List<String> indata = new ArrayList<>();  //存储从文件中读取的原始数据
	public static List<Points> pointss = new ArrayList<Points>(); // 进行聚类的点
    private static List<List<Points>> clusterss = new ArrayList<List<Points>>(); // 存储簇
    public static int clusterNumber;
    private static double T2 = -1; // 阈值
   /* public static void main(String[] args) {
    	KesCanopy canopy = new KesCanopy();
    	//获取数据并初始化
    	canopy.loadData("D:/Schoolproject/date/bezdekIris.data");
        //获取canopy数目
    	
        canopy.cluster();
        clusterNumber = canopy.getClusterNumber();
        System.out.println("canopy数目:"+clusterNumber);

                //获取canopy中T2的值
        System.out.println("canopy中T2的值:"+canopy.getThreshold());
    }*/
    
    //读取文件中的数据
    public static  boolean loadData(String url) {//加载测试的数据文件
        try {
        	indata.clear();
        	pointss.clear();
            Scanner in = new Scanner(new File(url));//读入文件
            while (in.hasNextLine()) {
                String str = in.nextLine();//将文件的每一行存到str的临时变量中
                indata.add(str);//将每一个样本点的数据追加到list 中
            }
            int i = 0;
            String t;
            while (i < indata.size()) {//取出indata中的每一行值
                double[] tem = new double[5];
                t = indata.get(i);
                String[] sourceStrArray = t.split(",", 5);//使用字符串分割函数提取出各属性值

                for (int j = 0; j < 4; j++) {
                    tem[j] = Double.parseDouble(sourceStrArray[j]);//将每一个的样本的各属性值类型转换后依次存入到double[]数组中
                }
                Points p=new Points();
                p.setX(Double.parseDouble(sourceStrArray[0]));
                p.setY(Double.parseDouble(sourceStrArray[1]));
                p.setZ(Double.parseDouble(sourceStrArray[2]));
                p.setN(Double.parseDouble(sourceStrArray[3]));
                
                pointss.add(p);//将每一个样本加入到data中
                i++;
            }
            return true;
        } catch (Exception e) { //如果出错返回false
            return false;
        }
    }
    
   /* public KesCanopy(List<Points> pointss) {
        for (Points points : pointss)
            // 进行深拷贝
            this.pointss.add(points);
    }*/
    
    /**
     * 进行聚类，按照Canopy算法进行计算，将所有点进行聚类
     */
    public static void cluster() {
    	clusterss.clear();
        T2 = getAverageDistance(pointss);
        while (pointss.size() != 0) {
            List<Points> cluster = new ArrayList<Points>();
            Points basePoint = pointss.get(0); // 基准点
            cluster.add(basePoint);
            pointss.remove(0);
            int index = 0;
            while (index < pointss.size()) {
                Points anotherPoint = pointss.get(index);
                double distance = Math.sqrt((basePoint.x - anotherPoint.x)
                        * (basePoint.x - anotherPoint.x)
                        + (basePoint.y - anotherPoint.y)
                        * (basePoint.y - anotherPoint.y)
                        + (basePoint.z - anotherPoint.z)
                        * (basePoint.z - anotherPoint.z)
                        + (basePoint.n - anotherPoint.n)
                        * (basePoint.n - anotherPoint.n));
                if (distance <= T2) {
                    cluster.add(anotherPoint);
                    pointss.remove(index);
                } else {
                    index++;
                }
            }
            clusterss.add(cluster);
        }
        System.out.println("canopy长度test："+clusterss.size());
    }
    
    /**
     * 得到Cluster的数目
     * 
     * @return 数目
     */
    public static int getClusterNumber() {
    	System.out.println("canopy长度："+clusterss.size());
        return clusterss.size();
    }
    /**
     * 得到Cluster的数目
     * 
     * @return 数目
     */
    public int[] getClusterPoint() {
        int[] points=new int[clusterss.size()];
        for(int i=0;i<clusterss.size();i++){
        	for(int j=0;j<clusterss.size();j++){
        		
        	}
        }
    	return points;
    }
    
    /**
     * 获取Cluster对应的中心点(各点相加求平均)
     * 
     * @return
     */
    public List<Points> getClusterCenterPoints() {
        List<Points> centerPoints = new ArrayList<Points>();
        for (List<Points> cluster : clusterss) {
            centerPoints.add(getCenterPoint(cluster));
        }
        return centerPoints;
    }
    
    /**
     * 得到的阈值T2()       
     * 
     * @return 返回T2阈值
     */
    private static double getAverageDistance(List<Points> points) {
        double sum = 0;
        int pointSize = pointss.size();
        for (int i = 0; i < pointSize; i++) {
            for (int j = 0; j < pointSize; j++) {
                if (i == j)
                    continue;
                Points pointA = pointss.get(i);
                Points pointB = pointss.get(j);
                sum += Math.sqrt((pointA.x - pointB.x) * (pointA.x - pointB.x)
                        + (pointA.y - pointB.y) * (pointA.y - pointB.y)
                        +(pointA.z - pointB.z) * (pointA.z - pointB.z));
            }
        }
        int distanceNumber = pointSize * (pointSize + 1) / 2;
        double T2 = sum / distanceNumber / 2; // 平均距离的一半
        System.out.println("canopyT2："+T2);
        return T2;
    }
    
    /**
     * 得到的中心点(各点相加求平均)
     * 
     * @return 返回中心点
     */
    private Points getCenterPoint(List<Points> points) {
        double sumX = 0;
        double sumY = 0;
        double sumZ=0;
        double sumN=0;
        for (Points point : points) {
            sumX += point.x;
            sumY += point.y;
            sumZ+=point.z;
            sumN+=point.n;
        }
        int clusterSize = points.size();
        Points centerPoint = new Points(sumX / clusterSize, sumY / clusterSize,sumZ/clusterSize,sumN/clusterSize);
        return centerPoint;
    }

    /**
     * 获取阈值T2
     * 
     * @return 阈值T2
     */
    public double getThreshold() {
        return T2;
    }

}
