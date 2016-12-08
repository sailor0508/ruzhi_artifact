/**
 * Copyright (c) 2012 by Taobao.
 */
package com.ruzhi.demo.lifeserverweb;


public class PosUtil {
	public static final double EARTH_RADIUS = 6378.137;

	public static long PosDoubleStrToLong(String posstr){
		try{
			return Math.round(Double.parseDouble(posstr)*100000);
		}catch(Exception er){
			System.out.println("change pos error !");
		}
		return 0;
	}

	public static double PosLongStrToDouble(String posstr){
		try{
			return (Long.parseLong(posstr)+0.0)/100000;
		}catch(Exception er){
			System.out.println("change pos error !");
		}
		return 0.0;
	}

	public static int PosDoubleToInt(double dpos){
		try{
			return Integer.parseInt(String.valueOf(Math.round(dpos*100000)));
		}catch(Exception er){
			System.out.println("change pos error !" + dpos);
		}
		return 0;
	}

	public static double GetDistance(double posx1,double posy1,double posx2,double posy2){
		double distance=0.0;
		double radposy1=getRad(posy1);
		double radposy2=getRad(posy2);
		double Ydis=radposy1-radposy2;
		double Xdis=getRad(posx1)-getRad(posx2);
		distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(Ydis/2),2) + Math.cos(radposy1)*Math.cos(radposy2)*Math.pow(Math.sin(Xdis/2),2)));
		distance = distance * EARTH_RADIUS;
//		distance = Math.round(distance * 10000) / 10000.0;	// km
		distance = Math.round(distance * 10000) / 10.0;	// m
		return distance;
	}

	public static double getRad(double d)
	{
		return d * Math.PI / 180.0;
	}
}
