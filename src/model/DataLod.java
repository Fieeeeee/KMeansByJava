package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataLod {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		loadData("D:/Schoolproject/date/studentdata.xlsx");
	}
	public static ArrayList<Point> loadData(String url){
    	File file = new File(url); 
    	ArrayList<Point> points=new ArrayList<Point>();
    	try {
			InputStream is=new FileInputStream(url);
			try {
				XSSFWorkbook xfwb=new XSSFWorkbook(is);
				List<List<String>> result =new ArrayList<List<String>>();
				for(XSSFSheet xss:xfwb){
					if(xss==null){
						continue;
					}
					for(int rowNum=1;rowNum<=xss.getLastRowNum();rowNum++){
						XSSFRow xssfRow=xss.getRow(rowNum);
						int minColIx=xssfRow.getFirstCellNum();
						int maxColIx=xssfRow.getLastCellNum();
						List<String> rowList=new ArrayList<String>();
						for(int colIx=minColIx;colIx<maxColIx;colIx++){
							XSSFCell cell=xssfRow.getCell(colIx);
							if(cell==null){
								continue;
							}
							rowList.add(cell.toString());
						}
						result.add(rowList);
					}
				}
				for(int i=0;i<result.size();i++){
					for(int j=0;j<result.get(i).size();j++){
						System.out.print("第"+j+":"+result.get(i).get(j));
					}
					System.out.println();
				}
				
				for(int i=2;i<result.size();i++){
					double x=0;
					double y=0;
					int pg=result.get(i).size()-1;
					System.out.println(pg);
					for(int j=0;j<pg;j++){
						System.out.println("eeee"+result.get(i).get(j));
						double ds=Double.parseDouble(result.get(i).get(j));
						x=x+ds;
					}
					int e=result.get(i).size()-1;
					y=Double.parseDouble(result.get(i).get(e));
					System.out.println("第"+i+"行：x:"+x+"y:"+y);
					Point p=new Point(x,y);
					points.add(p);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return points;
    }
}
