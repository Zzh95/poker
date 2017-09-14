package com.pokergame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Game{
	List<cards> card=new ArrayList<cards>();
	player player1=new player();
	player player2=new player();
	/**
	 * 创建扑克牌类
	 * 设置属性：花色和扑克牌的值
	 *
	 */
	public class cards{
		String number;
		String color;
		public cards(String number,String color){
			this.number=number;
			this.color=color;
		}
	}
	/**
	 * 创建玩家类
	 * 设置属性：姓名，ID和获得的牌的List
	 * @author Administrator
	 *
	 */
	
	public class player{
		int ID;
		String name;
		List<cards> list;

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			player other = (player) obj;
			if (ID != other.ID)
				return false;
			return true;
		}
		
		
		public boolean equals1(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			player other = (player) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}
		player(){
			this.list=new ArrayList<cards>();
		}
		player(int ID,String name){
			this.ID=ID;
			this.name=name;
			this.list=new ArrayList<cards>();
		}
	}
	/**
	 * 设置临时比较类
	 * @author Administrator
	 *
	 */
	
	public class Compara implements Comparator<cards>{
		@Override
		public int compare(cards o1, cards o2) {
				String color="黑桃 红桃 梅花 方块";
				String number="2 3 4 5 6 7 8 9 10 J Q K A";
				int i=number.indexOf(o1.number)-number.indexOf(o2.number);
				if(i>0){
					return 1;
				}else if(i<0){
					return -1;
				}else{
					int j=color.indexOf(o1.color)-number.indexOf(o2.color);
					if(j>0){
						return -1;
					}else if(j<0){
						return 1;
					}else{
						return 0;
					}
					}
				}
	}
	
	/**
	 * 创建扑克牌
	 */
	
	
	public void createcard(){
		String[] number={"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
		String[] color={"黑桃","红桃","梅花","方块"};
		System.out.println("-------------创建扑克牌---------------");
		for(int i=0;i<number.length;i++){
			for(int j=0;j<color.length;j++){
				cards cds=new cards(number[i],color[j]);
				card.add(cds);
			}	
		}
		System.out.println("--------------创建完成-------------");
		for (cards cad : card) {
			System.out.print(cad.color+cad.number+",");
		}
	}
	
	/**
	 * 创建玩家
	 */
	
	public void createplayer(){
		System.out.println("--------------创建玩家------------");
		Scanner in=new Scanner(System.in);
		for(int m=0;m<1;){
			try{
				System.out.println("输入玩家1的ID： ");
				player1.ID=in.nextInt();
				}catch(InputMismatchException ex){
					in.next();//把缓存中的错误数据读取，以便重新输入。
					System.out.println("输入错误！！ID必须为整形");
					continue;
			}
			m++;
			}
		System.out.println("输入玩家1的姓名： ");
		player1.name=in.next();
		for(int m=0;m<1;){
			try{
				System.out.println("输入玩家2的ID： ");
		    	player2.ID=in.nextInt();
		    	if(player1.equals1(player2)){
					do{
						System.out.println("输入ID已存在，请重新输入： ");
						player2.ID=in.nextInt();
					}while(player1.equals1(player2));
				}
				}catch(InputMismatchException ex){
					in.next();//把缓存中的错误数据读取，以便重新输入。
					System.out.println("输入错误！！ID必须为整形");
					continue;
			}
			m++;
			}
		System.out.println("输入玩家2的姓名： ");
		player2.name=in.next();
		if(player1.equals(player2)){
			do{
				System.out.println("输入姓名已存在，请重新输入： ");
				player2.name=in.next();
			}while(player1.name.equals(player2.name));
		}
	}
	
	/**
	 * 洗牌
	 */
	
	public void stc(){
		System.out.println("--------------洗牌--------------");
		Collections.shuffle(card);
		System.out.println("--------------洗牌完毕-----------");
	}
	/**
	 * 发牌
	 */
	
	public void licensing(){
		System.out.println("-------------开始发牌------------");
		for(int i=0;i<4;i++){
			if(i%2==0){
				System.out.println("------------玩家"+player1.name+"得牌----------");
				player1.list.add(card.get(i));
			}else{
				System.out.println("------------玩家"+player2.name+"得牌----------");
				player2.list.add(card.get(i));
			}
		}
	}
	
	/**
	 * 遍历玩家的牌
	 */
	public void foreach(){
		System.out.println("玩家"+player1.name+"的牌为： ");
		for (cards cad : player1.list) {
			System.out.println(cad.color+cad.number);
		}
		System.out.println("玩家"+player2.name+"的牌为： ");
		for (cards cad : player2.list) {
			System.out.println(cad.color+cad.number);
		}
	}
	
	/**
	 * 比较玩家的牌确定获胜的玩家
	 */
	
	public void comparetest(){
		List<cards> cop1=new ArrayList<cards>();
		cop1.addAll(player1.list);
		Collections.sort(cop1, new Compara());
		System.out.println("玩家"+player1.name+"的最大牌为: "+cop1.get(1).color+cop1.get(1).number);
		List<cards> cop2=new ArrayList<cards>();
		cop2.addAll(player2.list);
		Collections.sort(cop2, new Compara());
		System.out.println("玩家"+player2.name+"的最大牌为: "+cop2.get(1).color+cop2.get(1).number);
		List<cards> cop3=new ArrayList<cards>();
		cop3.add(player1.list.get(1));
		cop3.add(player2.list.get(1));
		Collections.sort(cop3, new Compara());
		if(cop3.get(1).equals(player1.list.get(1))){
			System.out.println("获胜者为： "+player1.ID+":"+player1.name );
		}else{
			System.out.println("获胜者为： "+player2.ID+":"+player2.name );
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game game=new Game();
		game.createcard();
		game.createplayer();
		game.stc();
		game.licensing();
		game.foreach();
		game.comparetest();

	}


}
