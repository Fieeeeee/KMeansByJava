package kmeans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

import canopy.Canopy;
import model.DataLod;
import model.GetPoints;
import model.Point;

public class Kmeans {
	public static ArrayList<String> indata = new ArrayList<>();  //存储从文件中读取的原始数据
    public static ArrayList<Point> data = new ArrayList<>();  //存储经过处理后的每一个样本的各个属性值和所属分类
    public static ArrayList<Point> init_kernal = new ArrayList<>();//存储每次迭代产生的聚类核心的每个属性值的均值
    
    public static ArrayList<double[][]> getStudentCKDatas(int[] a){
    	data.clear();
    	init_kernal.clear();
    	data=DataLod.loadData("D:/Schoolproject/date/studentdata.xlsx");
    	
    	/*Canopy canopy = new Canopy(data);
    	canopy.cluster();*/
        set_kernal(data,a);
        int number=k_means();  
        ArrayList<double[][]> datas=show_category();
        /*for(int i=0;i<canopy.getClusterCenterPoints().size();i++){
        	
        }*/
        System.out.println("算法迭代次数："+number);
        return datas;
    }
    public static ArrayList<double[][]> getStudentsKDatas(int[] a){
    	data.clear();
    	init_kernal.clear();
    	data=DataLod.loadData("D:/Schoolproject/date/studentdata.xlsx");
    	
        set_kernal(data,a);
        int number=k_means();  
        ArrayList<double[][]> datas=show_category();
        System.out.println("算法迭代次数："+number);
        return datas;
    }
    public static ArrayList<Point> setPoints(){
    	data.clear();
    	data=GetPoints.getPoints(1000); 
    	return data;
    }
    public static ArrayList<double[][]> getCKDatas(int[] a){
    	//data=GetPoints.getPoints(1000);          ///修改标识
    	
    	//Canopy canopy = new Canopy(data);
    	//canopy.cluster();
    	init_kernal.clear();
        set_kernal(data,a);
        int number=k_means();  
        ArrayList<double[][]> datas=show_category();
        /*for(int i=0;i<canopy.getClusterCenterPoints().size();i++){
        	
        }*/
        System.out.println("算法迭代次数："+number);
        return datas;
    }
    public static ArrayList<double[][]> getKDatas(int[] a){
    	/*data.clear();
    	init_kernal.clear();
    	data=GetPoints.getPoints(1000);*/
    	init_kernal.clear();
        set_kernal(data,a);
        int number=k_means();  
        ArrayList<double[][]> datas=show_category();
        System.out.println("算法迭代次数："+number);
        return datas;
    }
    
    public static ArrayList<Point> set_kernal(ArrayList<Point> data, int[] as) {//设置初始的聚类核心，a，b，c分别表示一个类的核心在data中的编号
    	for(int i=0;i<as.length;i++){
    		init_kernal.add(data.get(i));
    	}
        return init_kernal;
    }
    public static ArrayList<Point> set_kernal2(ArrayList<Point> as) {//设置初始的聚类核心，a，b，c分别表示一个类的核心在data中的编号
    	for(int i=0;i<as.size();i++){
    		init_kernal.add(as.get(i));
    	}
        return init_kernal;
    }
    
    public static int k_means() {//k_means算法的实现

    	int number=0;
    	while(true){
    		boolean con = false;
    		ArrayList<Point> t = onesteps(data, init_kernal);//将 data和当前的init_kernal传入onestep函数进行一次迭代，返回值为迭代后的类的核心

            //判断本次迭代后返回的类的核心是不是和迭代之前的类的核心相同，如果不相同con被设为false，继续迭代。
            //System.out.println("test1sssssssssss"+t.size());
            for (int i = 0; i < t.size(); i++) {
            	if (t.get(i).getX() == init_kernal.get(i).getX()&&t.get(i).getY() == init_kernal.get(i).getY()){
                	//System.out.println("test1"+t.get(i)[j]);
                	//System.out.println("test2"+init_kernal.get(i)[j]);
                    con = true;
                    //number++;
                }else{
                	number++;
                	con=false;
                }
            }
            if (con)//如果con为true说明本次迭代的核心和迭代之前的核心相同，说明聚类完成，退出循环
                break;
            else
                init_kernal = t;//如果本次迭代返回的新的核心和迭代之前的不同，则当前核心设置为返回的新的核心，继续循环迭代

    	}
        return number;
    }
    public static ArrayList<Point> onesteps(ArrayList<Point> data, ArrayList<Point> kernal) {//函数执行一次表示kmeans的一次迭代
    	ArrayList<Point> newkernal = new ArrayList<Point>();//用于存放一次迭代后新的类的核心的各属性值
        
    	//ArrayList<Point> kernalf = new ArrayList<Point>();
        int i = 0;
        /*for(int j=0;j<kernal.size();j++){
        	Point a=kernal.get(j);
        	kernalf.add(a); //a赋值为当前第一个类的核心
        }*/
        //Point temp;

        while (i < data.size()) {//取出data中的每一个样本存放在temp中
        	Point temp = data.get(i);
        	data.get(i).setType(choose(temp, kernal)); //调用choose函数判断当前样本属于哪一个类
            //System.out.println("ss"+data.get(i).getType());
            i++;
        }
        ArrayList<Point> sums=new ArrayList<Point>();
        
        int[] ls=new int[kernal.size()];
        for(int f=0;f<ls.length;f++){
        	Point p=new Point(0,0);
        	sums.add(p);
        	ls[f]=0;
        }
        i = 0;
        while(i < data.size()){
        	int ss=kernal.size();
        	for(int j=0;j<ss;j++){
        		Point t = data.get(i);
        		if(t.getType()==(j+1)){
        			sums.get(j).setX(sums.get(j).getX()+t.getX());
        			sums.get(j).setY(sums.get(j).getY()+t.getY());
        			ls[j]++;    
        			//sums.add(sum);
        			break;
        		}
        	}
        	i++;//指向下一个样本继续循环
        }
        for (int j = 0; j < kernal.size(); j++) {//计算出本次迭代后的每个类的核心的坐标   ///问题
        	sums.get(j).setX(sums.get(j).getX()/ls[j]);
        	sums.get(j).setY(sums.get(j).getY()/ls[j]);
        	//newkernal.add(sums.get(j));
        }
        
      //将新的类的核心添加入到newkernal中
        for(int a=0;a<kernal.size();a++){
        	newkernal.add(sums.get(a));
        }
        sums.clear();
        return newkernal;//返回本次迭代后的新的类的核心
      }
    public static int choose(Point data, ArrayList<Point> as) {//判断一个样本属于哪一个类
        double[] ts=new double[as.size()];
        for(int i=0;i<as.size();i++){
        	ts[i]=(data.getX()-as.get(i).getX())*(data.getX()-as.get(i).getX())+
        			((data.getY()-as.get(i).getY())*(data.getY()-as.get(i).getY()));
        }
        
        double s=ts[0];
        int result=1;
        for(int a=0;a<ts.length;a++){
        	if(ts[a]<s){
        		s=ts[a];
        		result=(a+1);
        	}
        }
        return result;
    }
    
    public static ArrayList<double[][]> show_category() {
    	int[] a=new int[init_kernal.size()];
    	Map<String,ArrayList<Point>> map=new HashMap<String,ArrayList<Point>>();
    	for(int i=0;i<init_kernal.size();i++){
    		ArrayList<Point> pls=new ArrayList<>();    		
    		map.put(String.valueOf(i),pls);
    	}
    	ArrayList<double[][]> datas=new ArrayList<double[][]>();
    	
        for (int i = 0; i < data.size(); i++) {       	
            for(int j=0;j<init_kernal.size();j++){
            	if(data.get(i).getType()==(j+1)){
            		Point p=data.get(i);
            		map.get(String.valueOf(j)).add(p);
            		a[j]++;
            	}
            }
        }
        for(int j=0;j<init_kernal.size();j++){
        	//System.out.println("第"+j+"个");
        	ArrayList<Point> ps=map.get(String.valueOf(j));
        	double[][] as=new double[2][ps.size()];
        	for(int i=0;i<ps.size();i++){
        		//System.out.println("数据测试1"+ps.size());
        		//System.out.println("数据测试2:"+ps.get(i).getX());
        		double x=ps.get(i).getX();
        		double y=ps.get(i).getY();
        		//System.out.println("数据"+(i+1)+":"+"x:"+x+"y:"+y);
        		as[0][i]=x;
    			as[1][i]=y;	
        	}
        	datas.add(as);
        }
        /*for (int i = 0; i < data.size(); i++) {
        	//datas.get(j)[0][g]=data.get(i).getX();
			//datas.get(j)[1][g]=data.get(i).getY();
            for(int j=0;j<init_kernal.size();j++){
            	Point p=data.get(i);
            	if(data.get(i).getType()==(j+1)){
            		System.out.println("数据测试"+data.get(i).getX());
            	}
            }
            //System.out.println();
        }        
        for(int s=0;s<init_kernal.size();s++){        
        	System.out.println("第"+(s+1)+"类数据数量："+a[s]);
        }*/
                
        return datas;
    }
}
