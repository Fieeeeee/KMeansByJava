package kmeans;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import model.GetPoints;
public class CanopyKmeans {
	public static ArrayList<String> indata = new ArrayList<>();  //存储从文件中读取的原始数据
    public static ArrayList<double[]> data = new ArrayList<>();  //存储经过处理后的每一个样本的各个属性值和所属分类
    public static ArrayList<double[]> init_kernal = new ArrayList<>();//存储每次迭代产生的聚类核心的每个属性值的均值
    
  /*  public static void main(String[] args){  
    	loadData("D:/Schoolproject/date/bezdekIris.data");
    	pretreatment(indata);//预处理
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("请输入4个初始核心位置");
        //int[] as=new int[4];
        System.out.print("第一个");
        int a = scanner.nextInt();
        System.out.print("第二个");
        int b = scanner.nextInt();
        System.out.print("第三个");
        int c = scanner.nextInt(); 
        System.out.print("第四个");
        int d = scanner.nextInt();
        set_kernal(data,a,b,c,d);
        int numbers=k_means();  
        show_category();
        System.out.println("算法迭代次数："+numbers);
    }*/
    public static ArrayList<double[][]> getCKDatas(int[] a){
    	loadData("D:/Schoolproject/date/bezdekIris.data");
		pretreatment(CanopyKmeans.indata);//预处理
    	ArrayList<double[][]> datas;
    	set_kernal(data,a[0],a[1],a[2],a[3]);
        int numbers=k_means();  
        datas=CanopyKmeans.show_category();
        System.out.println("算法迭代次数："+numbers);
        
        return datas;
    }
    public static boolean loadData(String url) {//加载测试的数据文件
        try {
            Scanner in = new Scanner(new File(url));//读入文件
            while (in.hasNextLine()) {
                String str = in.nextLine();//将文件的每一行存到str的临时变量中
                //System.out.println(str);
                indata.add(str);//将每一个样本点的数据追加到list中
            }
            return true;
        } catch (Exception e) { //如果出错返回false
            return false;
        }
    }
    public static void pretreatment(ArrayList<String> indata) {   //数据预处理，将原始数据中的每一个属性值提取出来存放到Vector<double[]>  data中
        int i = 0;
        String t;
        while (i < indata.size()) {//取出indata中的每一行值
            double[] tem = new double[5];
            t = indata.get(i);
            String[] sourceStrArray = t.split(",", 5);//使用字符串分割函数提取出各属性值

            for (int j = 0; j < 4; j++) {
                tem[j] = Double.parseDouble(sourceStrArray[j]);//将每一个的样本的各属性值类型转换后依次存入到double[]数组中
            }
            tem[4] = 0;//tem的第五个值表示所属类别，1.0表示第一类，2.0表示第二类，3.0表示第三类，初始化为0不属于任何类
            data.add(tem);//将每一个样本加入到data中
            i++;
        }
    }
    
    public static ArrayList<double[]> set_kernal(ArrayList<double[]> data, int a,int b,int c,int d) {//设置初始的聚类核心，a，b，c分别表示一个类的核心在data中的编号
    	init_kernal.add(data.get(a));
    	init_kernal.add(data.get(b));
    	init_kernal.add(data.get(c));
    	init_kernal.add(data.get(d));
        return init_kernal;
    }
    
    public static int k_means(){
    	int number=0;    //计算迭代次数
    	while(true){
    		boolean con=true;
    		ArrayList<double[]> t=onestop(data,init_kernal);
    		for(int i=0;i<t.size();i++){
    			for(int j=0;j<4;j++){
    				if(t.get(i)[j]!=init_kernal.get(i)[j]){
    					con=false;
    					number++;
    				}
    			}
    		}
    		if(con)
    			break;
    		else
    			init_kernal=t;
    	}
    	return number;
    }
    
    public static int choose(double[] data, double[] a, double[] b, double[] c,double[] d) {//判断一个样本属于哪一个类，返回值1表示第一类，2表示第二类，3表示第三类
    	double ta,tb,tc,td;
    	ta = (data[0] - a[0]) * (data[0] - a[0]) + (data[1] - a[1]) * (data[1] - a[1]) + (data[2] - a[2]) * (data[2] - a[2]) + (data[3] - a[3]) * (data[3] - a[3]);
        tb = (data[0] - b[0]) * (data[0] - b[0]) + (data[1] - b[1]) * (data[1] - b[1]) + (data[2] - b[2]) * (data[2] - b[2]) + (data[3] - b[3]) * (data[3] - b[3]);
        tc = (data[0] - c[0]) * (data[0] - c[0]) + (data[1] - c[1]) * (data[1] - c[1]) + (data[2] - c[2]) * (data[2] - c[2]) + (data[3] - c[3]) * (data[3] - c[3]);
        td = (data[0] - d[0]) * (data[0] - d[0]) + (data[1] - d[1]) * (data[1] - d[1]) + (data[2] - d[2]) * (data[2] - d[2]) + (data[3] - d[3]) * (data[3] - d[3]);

        if (ta == Math.min(Math.min(Math.min(ta, tb), tc), td))   //如果到第一类的距离最小返回1
            return 1;
        else if (tb == Math.min(Math.min(Math.min(ta, tb), tc), td))//如果到第二类的距离最小返回2
            return 2;
        else if (tc == Math.min(Math.min(Math.min(ta, tb), tc), td))//如果到第三类的距离最小返回3
            return 3;
        else if(td==Math.min(Math.min(Math.min(ta, tb), tc), td))
        	return 4;
       return 0;
    }
    
    public static ArrayList<double[]> onestop(ArrayList<double[]> data,ArrayList<double[]> kernal){//一次Kmeans算法的执行过程
    	ArrayList<double[]> newkernal=new ArrayList<>();//用于存放一次迭代后的新的核心的各属性值
    	int i=0;
    	double[] a = kernal.get(0); //a赋值为当前第一个类的核心
    	double[] b = kernal.get(1);
    	double[] c = kernal.get(2);
    	double[] d = kernal.get(3);
    	double[] temp;
    	while(i<data.size()){
    		temp=data.get(i);
    		temp[4] = (double) choose(temp, a,b,c,d);//调用choose函数判断当前样本属于哪一个类
    		System.out.println("test1g  "+data.get(i)[4]);
            i++;
    	}
    	double[] suma = {0, 0, 0, 0};
        int al = 0;//表示当前第一类的样本个数
        double[] sumb = {0, 0, 0, 0};
        int bl = 0;//当前第二类的样本个数
        double[] sumc = {0, 0, 0, 0};
        int cl = 0;//当前第三类的样本个数
        double[] sumd = {0, 0, 0, 0};
        int dl = 0;//当前第四类的样本个数
        i = 0;
    	while(i<data.size()){
    		double[] t = data.get(i);
            if (t[4] == 1.0) {     //如果当前样本属于第一类
                for (int j = 0; j < 4; j++) {
                    suma[j] = suma[j] + t[j];
                }
                al++;             //该类的样本个数加一
            } else if (t[4] == 2.0) {
                for (int j = 0; j < 4; j++) {
                    sumb[j] = sumb[j] + t[j];
                }
                bl++;
            } else if (t[4] == 3.0) {
                for (int j = 0; j < 4; j++) {
                    sumc[j] = sumc[j] + t[j];
                }
                cl++;
            } else if(t[4]==4.0){
            	for(int j=0;j<4;j++){
            		sumd[j] = sumd[j] + t[j];
            	}
            	dl++;
            }
            i++;//指向下一个样本继续循环
    	}
    	for (int j = 0; j < 4; j++) {//计算出本次迭代后的每个类的核心的坐标
    		suma[j] = suma[j] / al;
            sumb[j] = sumb[j] / bl;
            sumc[j] = sumc[j] / cl;
            sumd[j] = sumd[j] / dl;
        }
    	newkernal.add(suma);
        newkernal.add(sumb);
        newkernal.add(sumc);
        newkernal.add(sumd);
    	return newkernal;
    }
    public static ArrayList<double[][]> show_category() {//打印出所有的样本的属性和所属类别
    	ArrayList<double[][]> datas=new ArrayList<>();
    	ArrayList<double[]> a1=new ArrayList<>();
        ArrayList<double[]> b1=new ArrayList<>();
        ArrayList<double[]> c1=new ArrayList<>();
        ArrayList<double[]> d1=new ArrayList<>();
    	int a=0,b=0,c=0,d=0;
        for (int i = 0; i < data.size(); i++) {
            //System.out.print((i + 1) + "  ");
            for (int j = 0; j < 5; j++) {
                //System.out.print(data.get(i)[j] + "  ");
            }
            if(data.get(i)[4]==1.0){
            	a1.add(data.get(i));
            	a++;
            }else if(data.get(i)[4]==2.0){
            	b1.add(data.get(i));
            	b++;
            }else if(data.get(i)[4]==3.0){
            	c1.add(data.get(i));
            	c++;
            }else if(data.get(i)[4]==4.0){
            	d1.add(data.get(i));
            	d++;
            }
            //System.out.println();
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
        double[][] dataFormat4 = new double[2][d1.size()];
        for(int j=0; j<d1.size(); j++){
            dataFormat4[0][j] = d1.get(j)[0];
            dataFormat4[1][j] = d1.get(j)[1];
        }
        datas.add(dataFormat4);
        System.out.println("第一类数据数量："+a);
        System.out.println("第二类数据数量："+b);
        System.out.println("第三类数据数量："+c);
        System.out.println("第四类数据数量："+d);
        return datas;
    }
    
}
